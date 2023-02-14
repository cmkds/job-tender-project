// /storage
// 기본 스토리지
import React, { useState, useEffect } from "react";
import { useNavigate, Routes, Route } from "react-router-dom";

import NavBar from "../components/NavBar";
import StorageList from "../components/storage/StorageList";
import StorageId from "../components/storage/StorageId";
// import MyButton from "../components/MyButton";

import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
// list

// /:card-no

// storage내부의 데이터 Context
export const StorageStateContext = React.createContext();

const dummyData = [
  {
    createTime: "2023-02-12T09:15:11.530Z",
    machineLocationSeq: "1",
    memberSeq: 1,
    photo:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmMPQb8IJeXeHn_Fxj8HN19mDbRKEFCmCjwQ&usqp=CAU",
    post: "http://img.khan.co.kr/newsmaker/947/20111025_947_60a.jpg",
    video: "https://img.lovepik.com/element/40170/8604.png_860.png",
    recentTime: "2023-02-12T09:15:11.530Z",
    storeSeq: 1,
  },
  {
    createTime: "2023-02-13T09:15:11.530Z",
    machineLocationSeq: "1",
    memberSeq: 1,
    photo:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmMPQb8IJeXeHn_Fxj8HN19mDbRKEFCmCjwQ&usqp=CAU",
    post: "http://img.khan.co.kr/newsmaker/947/20111025_947_60a.jpg",
    video: "https://img.lovepik.com/element/40170/8604.png_860.png",
    recentTime: "2023-02-13T09:15:11.530Z",
    storeSeq: 2,
  },
  {
    createTime: "2023-02-13T09:15:11.530Z",
    machineLocationSeq: "1",
    memberSeq: 1,
    photo:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmMPQb8IJeXeHn_Fxj8HN19mDbRKEFCmCjwQ&usqp=CAU",
    post: "http://img.khan.co.kr/newsmaker/947/20111025_947_60a.jpg",
    video: "https://img.lovepik.com/element/40170/8604.png_860.png",
    recentTime: "2023-02-13T09:15:11.530Z",
    storeSeq: 3,
  },
  {
    createTime: "2023-02-14T09:15:11.530Z",
    machineLocationSeq: "1",
    memberSeq: 1,
    photo:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmMPQb8IJeXeHn_Fxj8HN19mDbRKEFCmCjwQ&usqp=CAU",
    post: "http://img.khan.co.kr/newsmaker/947/20111025_947_60a.jpg",
    video: "https://img.lovepik.com/element/40170/8604.png_860.png",
    recentTime: "2023-02-14T09:15:11.530Z",
    storeSeq: 4,
  },
  {
    createTime: "2023-02-15T09:15:11.530Z",
    machineLocationSeq: "1",
    memberSeq: 1,
    photo:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmMPQb8IJeXeHn_Fxj8HN19mDbRKEFCmCjwQ&usqp=CAU",
    post: "http://img.khan.co.kr/newsmaker/947/20111025_947_60a.jpg",
    video: "https://img.lovepik.com/element/40170/8604.png_860.png",
    recentTime: "2023-02-15T09:15:11.530Z",
    storeSeq: 5,
  },
];

const Storage = () => {
  const navigate = useNavigate();
  useEffect(() => {
    if (!sessionStorage.getItem("loginUser")) {
      navigate("/");
    }
  });
  const [storageData, setStorageData] = useState(dummyData);

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
      <StorageStateContext.Provider value={storageData}>
        <NavBar
          navText={"보관함"}
          leftChild={<ArrowBackIosNewIcon onClick={() => navigate(-1)} />}
        />

        <Routes>
          <Route path="/" element={<StorageList />} />
          <Route path="/:id/*" element={<StorageId />} />
        </Routes>
      </StorageStateContext.Provider>
    </div>
  );
};

export default Storage;
