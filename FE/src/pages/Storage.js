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

  // useEffect(() => {
  //   if (!sessionStorage.getItem("loginUser")) {
  //     navigate("/");
  //   }
  // });

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
