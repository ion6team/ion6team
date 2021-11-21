<template>
    <div style="margin:10px; padding:10px;">
        <!-- {{this.chatid}}
        {{this.$store.state.member.id}} 
        {{this.$store.state.member.name}} -->
<br>
            <div v-for="(item, idx) in before" :key="idx" style="background-color:#fbf7f2;">
                <div v-if="before[idx].sender==loginname" align="right">
                    <b-row align-h="end" class="pb-3"> 
                        
                        <b-col cols="6">
                        <div style="border:1px solid #ff8a3d; border-radius:5px;
                        text-align:center; max-width:500px; 
                        background-color:white; min-height:100px;
                        padding:10px;"> 
                            <span> {{ before[idx].message }}</span>
                        </div>
                        </b-col>
                        <b-col cols="2" align-self="center" align="center">
                        <h5>{{ before[idx].sender }}</h5> <br> 
                        </b-col>
                    </b-row>
                </div>

                <div v-if="before[idx].sender!=loginname" align="left"> 
                  <b-row align-h="start" class="pb-3"> 

                        <b-col cols="2" align-self="center" align="center">
                        <h5>{{ before[idx].sender }}</h5> <br> 
                        </b-col>

                        <b-col cols="6">
                        <div style="border:1px solid #ff8a3d; border-radius:5px;
                        text-align:center; max-width:500px; 
                        background-color:white; min-height:100px;
                        padding:10px;"> 
                            <span> {{ before[idx].message }}</span>
                        </div>
                        </b-col>
                        
                    </b-row>
                </div>

            </div>

<p style="font-size:12px; color:#999;">이전 채팅</p>
<hr style="background-color:#ff8a3d;">

            <div v-for="(item, idx) in recvList" :key="idx" style="background-color:#fbf7f2;">
                <div v-if="item.sender==loginname" align="right"> 
                <b-row align-h="end" class="pb-3"> 
                        
                        <b-col cols="6">
                        <div style="border:1px solid #ff8a3d; border-radius:5px;
                        text-align:center; max-width:500px; 
                        background-color:white; min-height:100px;
                        padding:10px;"> 
                            <span> {{item.message }}</span>
                        </div>
                        </b-col>
                        <b-col cols="2" align-self="center" align="center">
                        <h5>{{ item.sender }}</h5> <br> 
                        </b-col>
                    </b-row>
                </div>
            </div>

             <input v-model="message" type="text" @keyup="sendMessage" placeholder="내용을 입력하세요" style="position:fixed; bottom:20px; left:50%; 
             margin-left:-250px;
             min-width:500px;
             min-height:100px;
             border-color:#ff8a3d;
             background-color:#fbf7f2;
             ">
        </div>
    
</template>

<script>
    import Stomp from 'webstomp-client'
    import SockJS from 'sockjs-client'
    import axios from 'axios'

    export default {
        name: 'App',
        props: {
            chatid: {
                type: String
            },
            myaddress: String
        },
        data() {
            return {
                userName: "",
                message: "",
                before:[],
                recvList: [],
                loginname:'',
            }
        },
        created() {
            // App.vue가 생성되면 소켓 연결을 시도합니다.
            this.connect()
            this.loginname = this.$store.state.member.name
            axios.get("/api/chat/open/" + this.chatid, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + this.$store.state.token
                    }
                })
                .then((res) => {
                    console.log ('이전대화내용' + res)
                    this.before = res.data;
                })
        },
        methods: {
            sendMessage(e) {
                if (e.keyCode === 13 && this.message !== '') {
                    this.send()
                    this.message = ''
                }
                       document.body.scrollTop = document.body.scrollHeight;
            },
            send() {
                console.log("Send message:" + this.message);
                if (this.stompClient && this.stompClient.connected) {
                    const msg = {
                        chatid: this.chatid,
                        sender: this.$store.state.member.name,
                        message: this.message
                    };
                    this.stompClient.send("http://localhost:8080/pub/api/chat/message", JSON.stringify(msg), {});
                }
         
            },
            connect() {
                const serverURL = "http://localhost:8080/ws-stomp"
                let socket = new SockJS(serverURL);
                this.stompClient = Stomp.over(socket);
                console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
                this.stompClient.connect({},
                    frame => {
                        // 소켓 연결 성공
                        this.connected = true;
                        console.log('소켓 연결 성공', frame);
                        // 서버의 메시지 전송 endpoint를 구독합니다.
                        // 이런형태를 pub sub 구조라고 합니다.
                        this.stompClient.subscribe("http://localhost:8080/sub/chat/room", res => {
                            console.log('구독으로 받은 메시지 입니다.', res.body);

                            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                            this.recvList.push(JSON.parse(res.body))

                        });
                    },
                    error => {
                        // 소켓 연결 실패
                        console.log('소켓 연결 실패', error);
                        this.connected = false;
                    }
                );
            }
        }
    }
</script>

<style scoped>

</style>