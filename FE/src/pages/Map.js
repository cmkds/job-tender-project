// /map
// 로그박스 설치 위치 안내 페이지

import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import NavBar from "../components/NavBar";

import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import { Box, Container } from "@mui/system";
import { Tab } from "@mui/material";

const { kakao } = window;

const Map = () => {
  const locationInfo = [
    {
      name: "서울",
      address: "서울 강남구 테헤란로 212 멀티캠퍼스 12층",
      img_url: "assets/seoul.jpg",
    },
    {
      name: "강릉",
      address: "강원 강릉시 창해로14번길 20-1",
      img_url: "assets/gangreung.png",
    },
    {
      name: "경주",
      address: "경북 경주시 원화로 102 안압지",
      img_url: "assets/kyungju.png",
    },
    {
      name: "부산",
      address: "부산 해운대구 우동",
      img_url: "assets/busan.png",
    },
    {
      name: "전주",
      address: "전북 전주시 완산구 기린대로 99",
      img_url: "assets/jeonju.png",
    },
  ];

  const LocationDetail = () => {
    if (selectNo == null) {
      return <div></div>;
    }
    return (
      <Container>
        <Box
          sx={{
            marginTop: "3%",
            bgcolor: "#E9E9E9",
            height: "80vw",
            boxShadow: "0px 4px 4px rgba(0, 0, 0, 0.25)",
          }}
        >
          <h2 className="blue_title">
            {`로그박스 ${locationInfo[selectNo].name}`}
          </h2>
          <h4 className="logbox_text">{locationInfo[selectNo].address}</h4>
          <img
            className="location_img"
            src={`${locationInfo[selectNo].img_url}`}
          ></img>
        </Box>
      </Container>
    );
  };
  const navigate = useNavigate();
  const [select, setSelect] = useState(null);
  const [selectNo, setSelectNo] = useState(0);
  const positions = [
    {
      no: "0",
      latlng: new kakao.maps.LatLng(37.5013068, 127.0396597),
    },
    {
      no: "1",
      latlng: new kakao.maps.LatLng(37.7731572, 128.9472735),
    },
    {
      no: "2",
      latlng: new kakao.maps.LatLng(35.8341593, 129.2265835),
    },
    {
      no: "3",
      latlng: new kakao.maps.LatLng(35.1631139, 129.1635509),
    },
    {
      no: "4",
      latlng: new kakao.maps.LatLng(35.8147105, 127.1526312),
    },
  ];
  useEffect(() => {
    const container = document.getElementById("Map");
    const options = {
      center: new kakao.maps.LatLng(36.300701, 127.570667),
      level: 13,
    };
    const map = new kakao.maps.Map(container, options);

    const normalImage = new kakao.maps.MarkerImage(
      "assets/marker.png",
      new kakao.maps.Size(30, 44),
      new kakao.maps.Point(15, 44)
    );
    const clickedImage = new kakao.maps.MarkerImage(
      "assets/marker.png",
      new kakao.maps.Size(45, 66),
      new kakao.maps.Point(22.5, 66)
    );
    const markers = [];
    for (var i = 0; i < positions.length; i++) {
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커의 위치
        image: normalImage,
        title: positions[i].no,
      });
      markers.push(marker);
      markers.forEach((marker) => {
        kakao.maps.event.addListener(marker, "click", function () {
          markers.forEach((anotherMarker) => {
            if (marker !== anotherMarker) {
              anotherMarker.setImage(normalImage);
            }
          });
          if (!select || select !== marker) {
            if (select !== null) {
              select.setImage(normalImage);
            }
            marker.setImage(clickedImage);
          }

          setSelectNo(parseInt(marker.Gb));
          setSelect(marker);
        });
      });
    }
  }, []);

  return (
    <div>
      <NavBar
        navText={"로그박스 위치"}
        leftChild={<ArrowBackIosNewIcon onClick={() => navigate(-1)} />}
      />
      {/* <div
        style={{
          height: "75px",
        }}
      /> */}
      <Tab>
        <ArrowBackIosNewIcon></ArrowBackIosNewIcon>
      </Tab>
      <div
        id="Map"
        style={{
          display: "flex",
          width: "90%",
          paddingBottom: "90%",
          margin: "auto",
          marginTop: "10%",
        }}
      ></div>
      <LocationDetail />
    </div>
  );
};

export default Map;
