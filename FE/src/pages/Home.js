// 시작 페이지 로그인 화면 뜸
// 로그인시 메인 페이지로 가도록 해야함.
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

import { motion } from "framer-motion";

import styled from "@emotion/styled";
import Button from "@mui/material/Button";
import { Box } from "@mui/material";
import KeyboardDoubleArrowDownRoundedIcon from "@mui/icons-material/KeyboardDoubleArrowDownRounded";
import { WidthWideTwoTone } from "@mui/icons-material";
const style = {
  position: "absolute",

  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "80%",
  bgcolor: "rgba(255, 255, 255, 0.5)",
  borderRadius: "8px",
  boxShadow: 24,
  px: 0,
  pb: 5,
};

const Home = () => {
  const MyBtn = styled(Button)`
    display: flex;
    margin: auto;
  `;

  const navigate = useNavigate();
  const state = 1;
  const CLIENT_ID = "DJ94WWgE_wFepnxDsIQa"; // 호성이꺼
  // const CLIENT_ID = "XxdvqpScfLYk66JOWwTB"; // 내꺼
  const redirectURI = "http://localhost:3000/sign-up";
  const naverLogin = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${CLIENT_ID}&redirect_uri=${redirectURI}&state=${state}`;
  // const [clicked, setClicked] = useState(false);

  // const printImg = () => {
  //   if (clicked) {
  //     return (
  //       <div>
  //         <motion.img
  //           initial={{ scale: 1 }}
  //           animate={{ scale: 1.2 }}
  //           className="postOpen"
  //           onClick={() => {
  //             navigate("/logbox");
  //           }}
  //           src={process.env.PUBLIC_URL + `assets/open2.png`}
  //         />
  //         <h2
  //           style={{
  //             textAlign: "center",
  //             wordBreak: "keep-all",
  //             // paddingBottom: "20%",
  //           }}
  //         >
  //           "소중한 추억을 특별하게 기록하는 방법 알아보기"
  //         </h2>
  //       </div>
  //     );
  //   } else {
  //     return (
  //       <div>
  //         <motion.img
  //           initial={{ scale: 1.2 }}
  //           animate={{ scale: 1 }}
  //           className="postClosed"
  //           onClick={() => {
  //             setClicked(!clicked);
  //           }}
  //           src={process.env.PUBLIC_URL + `assets/post.png`}
  //         />
  //         <h2
  //           style={{
  //             textAlign: "center",
  //             wordBreak: "keep-all",
  //             // paddingBottom: "20%",
  //           }}
  //         >
  //           "소중한 추억을 특별하게 기록하는 방법 알아보기"
  //         </h2>
  //       </div>
  //     );
  //   }
  // };

  return (
    <div className="main" style={{ height: "100vh", paddingTop: "15%" }}>
      <div style={{ display: "flex" }}>
        <img
          className="logo"
          src={process.env.PUBLIC_URL + `assets/logo2.png`}
          alt="logo"
        ></img>
        <Box sx={{ ...style }}>
          <div className="arrow-icon">
            <i className="fas fa-arrow-right">
              <KeyboardDoubleArrowDownRoundedIcon
                sx={{ fontSize: "100px", color: "grey", width: "80vw" }}
              />
            </i>
          </div>
          <img
            className="postClosed"
            src={process.env.PUBLIC_URL + `assets/post.png`}
            alt="img가 없습니다."
            onClick={() => {
              navigate("/logbox");
            }}
          ></img>
          <h2
            style={{
              textAlign: "center",
              wordBreak: "keep-all",
              paddingTop: "5%",
            }}
          >
            "소중한 추억을 특별하게 기록하는 방법 알아보기"
          </h2>
        </Box>
      </div>
      <MyBtn
        sx={{
          position: "absolute",
          left: "50%",
          top: "85%",
          transform: "translate(-50%, -50%)",
          width: "80%",
        }}
        onClick={() => {}}
      >
        <a href={naverLogin}>
          <img
            src={process.env.PUBLIC_URL + `assets/naverLogin.png`}
            alt="naver"
            style={{ width: "100%" }}
          ></img>
        </a>
      </MyBtn>
    </div>
  );
};

export default Home;
