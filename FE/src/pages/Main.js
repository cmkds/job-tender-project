//  /main
// 메인 페이지
// /main/:전국
//없을 시 전국.
//우편 눌렀을 때 보이는 화면
//new hot location 쿼리로 보내야함
// 없을시 디폴트 값은 전국, 핫
import React, { useEffect, useReducer, useRef, useState } from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
import MainAllHot from "./main/MainAllHot";
import MainAllNew from "./main/MainAllNew";
import MainLocationHot from "./main/MainLocationHot";
import MainLocationNew from "./main/MainLocationNew";
import MainNavBar from "../components/main/MainNavBar";
import NewHotButton from "../components/main/NewHotButton";
// 리스트를 받아와서 지역별 보여주기는 상세 컴포넌트에서
// api 1
// 피드 최신 100개 가져오기 나중에 페이지 크기에 따라 수치 조정
//
// 뉴핫 별 보여주기

// 피드 최신 20개 보여주기

// 여기서 api를 받아서 MainState를 교체할수 있도록 해야함.
const dummyData = [
  {
    id: 1, //피드 아이디
    context: {
      userId: 1, //유저 아이디
      post_image:
        "https://i.postimg.cc/wjsCsRCD/Kakao-Talk-20230131-090510363.jpg", //포스트 이미지
      content: "hot 게시물이다",
      likes: [1, 2, 3, 4, 5], //좋아요 누른 유저의 pk
      comments: {
        //피드에 달린 댓글 들
        data: [
          //피드에 달린 댓글들 리스트
          { commentId: 1, userId: 1, content: "Im GD" },
          { commentId: 2, userId: 1, content: "Im Hot" },
          { commentId: 3, userId: 1, content: "Im Cool" },
          { commentId: 4, userId: 1, content: "Im Sexy" },
          { commentId: 5, userId: 1, content: "Im Handsome" },
        ],
      },
    },
  },
  {
    id: 2, //피드 아이디
    context: {
      userId: 1, //유저 아이디
      post_image:
        "https://i.postimg.cc/wjsCsRCD/Kakao-Talk-20230131-090510363.jpg", //포스트 이미지
      content: "hot 게시물이다222222222222",
      likes: [1, 2, 3, 4, 5],
      comments: {
        data: [{ commentId: 6, userId: 1, content: "Im G-Dragon" }],
      },
    },
  },
];

const reducer = (state, action) => {
  switch (action.type) {
    case "CHANGE": {
      state = [];
      return action.data;
    }
    default:
      return state;
  }
};

export const MainStateContext = React.createContext();
export const MainDispatchContext = React.createContext();

const Main = () => {
  const [apiFeedData, dispatch] = useReducer(reducer, dummyData);

  //navigate
  const navigate = useNavigate();

  //EDIT
  const onChangeApi = (api) => {
    dispatch({
      type: "CHANGE",
      data: {
        ...api,
      },
    });
  };
  return (
    <div>
      {/* 돋보기 버튼  이거 상단바 왼쪽에 위치 해야함.*/}
      <button onClick={() => navigate("/search")}> 유저 검색 </button>

      <MainNavBar></MainNavBar>
      <NewHotButton></NewHotButton>
      <h2>main</h2>

      <MainStateContext.Provider value={apiFeedData}>
        <MainDispatchContext.Provider value={onChangeApi}>
          <Routes>
            <Route path="/" element={<MainAllHot />} />
            <Route path="/all/hot" element={<MainAllHot />} />
            <Route path="/all/new" element={<MainAllNew />} />
            <Route path="/:locationId/hot" element={<MainLocationHot />} />
            <Route path="/:locationId/new" element={<MainLocationNew />} />
          </Routes>
        </MainDispatchContext.Provider>
      </MainStateContext.Provider>
    </div>
  );
};

export default Main;
