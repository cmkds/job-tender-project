// 시작 페이지 로그인 화면 뜸
// 로그인시 메인 페이지로 가도록 해야함.
import React from "react";
import { Box } from "@mui/material";

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
  return (
    <div className="main" style={{ height: "100vh", paddingTop: "15%" }}>
      <div style={{ display: "flex" }}>
        <img
          className="logo"
          src={process.env.PUBLIC_URL + `assets/logo2.png`}
          alt="logo"
        ></img>
        <Box sx={{ ...style }}>
          <h2
            style={{
              textAlign: "center",
              wordBreak: "keep-all",
              paddingTop: "5%",
            }}
          >
            "모바일로 접속해 주세요"
          </h2>
        </Box>
      </div>
    </div>
  );
};

export default Home;
