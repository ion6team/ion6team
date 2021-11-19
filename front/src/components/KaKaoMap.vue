<template>
<div>
    <div id="map"></div>
{{address}}zz
</div>
</template>

<script>
    export default {

        props:{
            address:String
        },
        mounted() {
            if (window.kakao && window.kakao.maps) {
                this.initMap();
            } else {
                const script = document.createElement('script');

                script.onload = () => kakao.maps.load(this.initMap);
                //카카오 개발자 서비스에서 서버 주소 등록해야 함
                script.src =
                    '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=cd703b274a2c9f5f12e5ba577c033d0c&libraries=services';
                document.head.appendChild(script);
            }
        },
        methods: {

            initMap() {
                var container = document.getElementById('map');
                var options = {
                    center: new kakao.maps.LatLng(33.450701, 126.570667),
                    level: 3
                };
                var map = new kakao.maps.Map(container, options);
                var marker = new kakao.maps.Marker({
                    position: map.getCenter()
                });

                var geocoder = new kakao.maps.services.Geocoder();

                geocoder.addressSearch(this.address, function (result, status) {
                    // 정상적으로 검색이 완료됐으면 
                    if (status === kakao.maps.services.Status.OK) {
                        var container = document.getElementById('map');
                        var coords = {center: new kakao.maps.LatLng(result[0].y, result[0].x), level: 5};


                     var map = new kakao.maps.Map(container, coords);
                        // 결과값으로 받은 위치를 마커로 표시합니다
                       var marker = new kakao.maps.Marker({
                          position: map.getCenter()
                      });

                        // 인포윈도우로 장소에 대한 설명을 표시합니다
                        var infowindow = new kakao.maps.InfoWindow({
                            content: '<div style="width:150px;text-align:center;padding:6px 0;">장소</div>'
                        });
                        infowindow.open(map, marker);

                        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                         var map = new kakao.maps.Map(container, coords);
                        marker.setMap(map)
                    }else{
                        console.log('failezzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzd')
                    }
                });
            },
            //   addScript() {
            //     const script = document.createElement('script'); /* global kakao */
            //     script.onload = () => kakao.maps.load(this.initMap);
            //     script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=cd703b274a2c9f5f12e5ba577c033d0c';
            //     document.head.appendChild(script);
            //   }
        }
    }
</script>

<style scoped>
    #map {
        width: 500px;
        height: 500px;
    }
</style>