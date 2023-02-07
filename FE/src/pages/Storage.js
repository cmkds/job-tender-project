// /storage
// 기본 스토리지
import NavBar from "../components/NavBar";
import MyButton from "../components/MyButton";
import React, { useState } from "react";
import { useNavigate, Routes, Route } from "react-router-dom";
// /
import StorageList from "../components/storage/StorageList";
import StorageId from "../components/storage/StorageId";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
// list

// /:card-no

// storage내부의 데이터 Context
export const StorageStateContext = React.createContext();

const dummyData = [
  {
    id: 1,
    date: "2022-12-03",
    location: "강릉",
    photo_url:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmMPQb8IJeXeHn_Fxj8HN19mDbRKEFCmCjwQ&usqp=CAU",
    video_url: "https://img.lovepik.com/element/40170/8604.png_860.png",
    post_url: "http://img.khan.co.kr/newsmaker/947/20111025_947_60a.jpg",
  },
  {
    id: 2,
    date: "2022-12-04",
    location: "서울",
    photo_url:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmMPQb8IJeXeHn_Fxj8HN19mDbRKEFCmCjwQ&usqp=CAU",
    video_url: "https://img.lovepik.com/element/40170/8604.png_860.png",
    post_url: "http://img.khan.co.kr/newsmaker/947/20111025_947_60a.jpg",
  },
  {
    id: 3,
    date: "2022-12-05",
    location: "부산",
    photo_url:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmMPQb8IJeXeHn_Fxj8HN19mDbRKEFCmCjwQ&usqp=CAU",
    video_url: "https://img.lovepik.com/element/40170/8604.png_860.png",
    post_url: "http://img.khan.co.kr/newsmaker/947/20111025_947_60a.jpg",
  },
  {
    id: 4,
    date: "2022-12-06",
    location: "전주",
    photo_url:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmMPQb8IJeXeHn_Fxj8HN19mDbRKEFCmCjwQ&usqp=CAU",
    video_url: "https://img.lovepik.com/element/40170/8604.png_860.png",
    post_url: "http://img.khan.co.kr/newsmaker/947/20111025_947_60a.jpg",
  },
  {
    id: 5,
    date: "2022-12-07",
    location: "경주",
    photo_url:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmMPQb8IJeXeHn_Fxj8HN19mDbRKEFCmCjwQ&usqp=CAU",
    video_url: "https://img.lovepik.com/element/40170/8604.png_860.png",
    post_url: "http://img.khan.co.kr/newsmaker/947/20111025_947_60a.jpg",
  },
];

const Storage = () => {
  const navigate = useNavigate();
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

        <div style={{ paddingTop: "75px" }}>
          <Routes>
            <Route path="/" element={<StorageList />} />
            <Route path="/:id/*" element={<StorageId />} />
          </Routes>
        </div>
      </StorageStateContext.Provider>
    </div>
  );
};

export default Storage;
