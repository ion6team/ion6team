<template>
    <div>
        {{this.chatid}}
        {{this.$store.state.member.id}} 
        {{this.$store.state.member.name}}
            <div v-for="(item, idx) in before" :key="idx">
                <div v-if="before[idx].sender==loginname" align="right"> 
                {{ before[idx].sender }} <br>  
                <h3> {{ before[idx].message }}</h3>
                </div>

                <div v-if="before[idx].sender!=loginname" align="left"> 
                {{ before[idx].sender }} <br>  
                <h3> {{ before[idx].message }}</h3>
                </div>

            </div>
        <div id="app">


            <div v-for="(item, idx) in recvList" :key="idx">
                <div v-if="item.sender==loginname" align="right"> 
                {{ item.sender }} <br>  
                <h3> {{item.message }}</h3>
                </div>

                <div v-if="item.sender!=loginname" align="left"> 
                {{ item.sender }} <br>  
                <h3> {{ item.message }}</h3>
                </div>


            </div>

             <input v-model="message" type="text" @keyup="sendMessage" placeholder="내용" style="position:fixed;bottom:0px;">
        </div>
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

<style>

</style>