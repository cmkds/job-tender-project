//  /main
// 메인 페이지
// /main/:전국
//없을 시 전국.
//우편 눌렀을 때 보이는 화면
//new hot location 쿼리로 보내야함
// 없을시 디폴트 값은 전국, 핫
import React, { useEffect, useReducer, useRef, useState } from "react";
import { Routes, Route, useNavigate } from "react-router-dom";

import TopBar from "../components/TopBar";
import MainAll from "./main/MainAll";
import MainAllHot from "./main/MainAllHot";
import MainAllNew from "./main/MainAllNew";
import MainLocationHot from "./main/MainLocationHot";
import MainLocationNew from "./main/MainLocationNew";
import MainNavBar from "../components/main/MainNavBar";
import NewHotButton from "../components/main/NewHotButton";

const Main = () => {
  return (
    <div>
      <TopBar />
      {/* <MainNavBar></MainNavBar>
      <NewHotButton></NewHotButton> */}
      {/* <h2>main</h2> */}
      <div>
        <Routes>
          <Route path="/:state/:locationId" element={<MainAll />} />
          {/* <Route path="/:state/0" element={<MainAllHot />} />
          <Route path="/hot/0" element={<MainAllHot />} />
          <Route path="/new/0" element={<MainAllNew />} />
          <Route path="/hot/:locationId" element={<MainLocationHot />} />
          <Route path="/new/:locationId" element={<MainLocationNew />} /> */}
        </Routes>
      </div>
    </div>
  );
};

export default Main;
