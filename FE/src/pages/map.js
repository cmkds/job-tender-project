// /map
// 로그박스 설치 위치 안내 페이지

import React, { useEffect, useState } from "react";

const {kakao} = window

const MapContainer = () => {
  const [select, setSelect] = useState(false)
  useEffect(() => {
    const positions = [
      {
        content: '<div>카카오</div>', 
        latlng: new kakao.maps.LatLng(33.450705, 126.570677)
      },
      {
        content: '<div>생태연못</div>', 
        latlng: new kakao.maps.LatLng(33.450936, 126.569477)
      },
      {
        content: '<div>텃밭</div>', 
        latlng: new kakao.maps.LatLng(33.450879, 126.569940)
      },
      {
        content: '<div>근린공원</div>',
        latlng: new kakao.maps.LatLng(33.451393, 126.570738)
      }
    ]
    const container = document.getElementById('Map');
    const options = {
      center: new kakao.maps.LatLng(35.750701, 127.570667),
      level: 14
    };
    const map = new kakao.maps.Map(container, options);
    for (var i = 0; i < positions.length; i ++) {
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: positions[i].latlng // 마커의 위치
      });
  
      // 마커에 표시할 인포윈도우를 생성합니다 
      var infowindow = new kakao.maps.InfoWindow({
          content: positions[i].content // 인포윈도우에 표시할 내용
      });
      kakao.maps.event.addListener(marker, 'mouseover', function(){
        infowindow.open(map, marker)
      });
      kakao.maps.event.addListener(marker, 'mouseout', function(){
        infowindow.close(map, marker)
      });
      kakao.maps.event.addListener(marker, 'click', function(){
        markerDetail(positions.content)
      })
    }
  //   function makeOverListener(map, marker, infowindow) {
  //     return function() {
  //       infowindow.open(map, marker);
  //     };
  //   }
  
  // // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
  //   function makeOutListener(infowindow) {
  //     return function() {
  //       infowindow.close();
  //     };
  //   }
  }, []);

  const markerDetail = (content) => {
  }

  return (
    <div id='Map' style={{
      display: 'flex',
      width: '90%',
      paddingBottom: '90%'
    }}>
      {select ?
      <></>: null  
    }
    </div>
  )
}

export default MapContainer

//.
