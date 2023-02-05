// /map
// 로그박스 설치 위치 안내 페이지

import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import NavBar from "../components/NavBar";
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';
import MapBar from '../components/MapBar'

const { kakao } = window;

const Map = () => {
  const navigate = useNavigate();
  const [select, setSelect] = useState(0);
  useEffect(() => {
    const positions = [
      {
        content: "<div>서울</div>",
        latlng: new kakao.maps.LatLng(37.5013068, 127.0396597),
      },
      {
        content: "<div>강릉</div>",
        latlng: new kakao.maps.LatLng(37.7731572, 128.9472735),
      },
      {
        content: "<div>부산</div>",
        latlng: new kakao.maps.LatLng(35.1631139, 129.1635509),
      },
      {
        content: "<div>경주</div>",
        latlng: new kakao.maps.LatLng(35.8341593, 129.2265835),
      },
      {
        content: "<div>전주</div>",
        latlng: new kakao.maps.LatLng(35.8147105, 127.1526312),
      },
    ];
    const container = document.getElementById("Map");
    const options = {
      center: new kakao.maps.LatLng(36.300701, 127.570667),
      level: 13,
    };
    const map = new kakao.maps.Map(container, options);

    const normalImage = new kakao.maps.MarkerImage(
      'assets/marker.png',
      new kakao.maps.Size(30, 44), new kakao.maps.Point(15, 44));
    const clickedImage = new kakao.maps.MarkerImage(
      'assets/marker.png',
      new kakao.maps.Size(45, 66), new kakao.maps.Point(22.5, 66));

    for (var i = 0; i < positions.length; i++) {
      // 마커를 생성합니다
      const marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커의 위치
        image: normalImage
      });
    
      if (select === i) {
        marker.setImage(clickedImage)
      }
      
      // new kakao.maps.event.addListener(marker, 'click', setSelect(i))
    }
  }, []);


  return (
    <div>
      <NavBar
        navText={"로그박스 위치"}
        leftChild={<ArrowBackIosNewIcon text={"<"} onClick={() => navigate(-1)} />}
      />
      <MapBar/>
      <div
        id="Map"
        style={{
          display: "flex",
          width: "90%",
          paddingBottom: "90%",
        }}
      >
        {select ? <></> : null}
      </div>
    </div>
  );
};

export default Map;

//.
