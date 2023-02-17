// /storage
// 기본 스토리지
import React, { useState, useEffect } from "react";
import { useNavigate, Routes, Route } from "react-router-dom";

import axios from "axios";

import BottomBar from "../components/BottomBar";
import NavBar from "../components/NavBar";
import StorageList from "../components/storage/StorageList";
import StorageId from "../components/storage/StorageId";
// import MyButton from "../components/MyButton";

import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
// list

// /:card-no

// storage내부의 데이터 Context

const Storage = () => {
  const navigate = useNavigate();
  const loginUser = sessionStorage.getItem("loginUser");

  useEffect(() => {
    if (!sessionStorage.getItem("loginUser")) {
      navigate("/");
    }
  });
  // useEffect(() => {
  //   console.log("aaaaa");
  //   axios.get(`/api/mypage/post-new/${loginUser}`).then(function (response) {
  //     setStorageData(response.data);
  //     console.log(response.data);
  //   });
  // }, []);

  // const [storageData, setStorageData] = useState([]);

  // console.log(storageData);
  // useEffect로 로그인을 안했다면 로그인 페이지로 복귀하도록함.

  // API 호출.
  // 헤더에 현재 로그인한 유저를 붙혀서
  // 해당 유저의 정보를 가져옴

  //여기서 context에 넣어두고
  //사용.
  //StorageId에서 context에 없는 id를 호출한다면 돌아오도록 할 수 있음.
  return (
    <div>
      <NavBar
        navText={"보관함"}
        leftChild={<ArrowBackIosNewIcon onClick={() => navigate(-1)} />}
      />

      <Routes>
        <Route path="/" element={<StorageList />} />
        <Route path="/:id/*" element={<StorageId />} />
      </Routes>
      <BottomBar />
    </div>
  );
};

export default Storage;
