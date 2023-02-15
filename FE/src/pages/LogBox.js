// // logbox 설명 페이지
// // /logbox

import TopBar from "../components/TopBar";

// import Grid from "@mui/material/Grid";

// import React from "react";
// import Box from "@mui/material/Box";

// const style = {
//   position: "absolute",

//   top: "50%",
//   left: "50%",
//   transform: "translate(-50%, -50%)",
//   width: "90%",
//   height: "75%",
//   bgcolor: "rgba(255, 230, 230, 0.5)",
//   borderRadius: "15px",
//   boxShadow: 24,
//   px: 0,
//   pb: 5,
// };

// const LogBox = () => {
//   const navigate = useNavigate();
//   return (
//     <div
//       style={{
//         width: "100vw",
//         height: "100vh",
//         backgroundColor: "rgba(255, 240, 240, 0.7)",
//       }}
//     >
//       {/* <TopBar /> */}

//       <Box sx={{ ...style }}>
//         <img
//           src={process.env.PUBLIC_URL + `assets/logbox/travel.jpg`}
//           alt="img가 없습니다."
//           style={{ width: "100%", height: "50vh", objectFit: "cover" }}
//         ></img>
//       </Box>
//     </div>
//   );
// };

// export default LogBox;

import { useState, useRef } from "react";
import { useTheme } from "@mui/material/styles";
import Box from "@mui/material/Box";
import MobileStepper from "@mui/material/MobileStepper";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import KeyboardArrowLeft from "@mui/icons-material/KeyboardArrowLeft";
import KeyboardArrowRight from "@mui/icons-material/KeyboardArrowRight";
import SwipeableViews from "react-swipeable-views";
import NavBar from "../components/NavBar";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import { useNavigate } from "react-router-dom";
const images = [
  {
    label: "여행지에서의 행복한 기억을",
    imgPath: "assets/logbox/travel2.jpg",
  },
  {
    label: "특별하게 간직하는 방법 !",
    imgPath: "assets/logbox/travel3.jpg",
  },
  {
    label: "사진으로만 남기긴 아쉽지 않았나요?",
    imgPath: "assets/logbox/memorize.jpg",
  },
  {
    label: "나만의 엽서를 만들어 미래로 선물하세요",
    imgPath: "assets/logbox/post.jpg",
  },
  {
    label: "엽서 프레임을 선택하고, 글과 그림을 추가할 수 있습니다",
    imgPath: "assets/logbox/custom_collection.jpg",
  },
  {
    label: "내 엽서를 logbox 웹을 이용하여 다른 사람들과 공유할 수 있습니다",
    imgPath: "assets/logbox/share.jpg",
  },
  {
    label: "지역별 인기 게시물을 확인할 수 있습니다",
    imgPath: "assets/logbox/share2.jpg",
  },
  {
    label: "당신의 소중한 추억을 logbox로 오래 간직하세요",
    imgPath: "assets/logbox/memorized.jpg",
  },
];

function LogBox() {
  const theme = useTheme();
  const [activeStep, setActiveStep] = useState(0);
  const maxSteps = images.length;
  const navigate = useNavigate();

  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleStepChange = (step) => {
    setActiveStep(step);
  };

  const state = 1;
  const CLIENT_ID = "1cdhp17WpXR_m9BDcOcE"; // 호성이 새로운거
  const redirectURI = "http://localhost:3000/naver";
  const naverLogin = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${CLIENT_ID}&redirect_uri=${redirectURI}&state=${state}`;

  return (
    <div>
      <Box sx={{ width: "100vw", height: "100vh", flexGrow: 1 }}>
        <NavBar
          navText={"LogBox?"}
          leftChild={<ArrowBackIosNewIcon onClick={() => navigate(-1)} />}
        />
        {/* <TopBar></TopBar> */}
        <MobileStepper
          steps={maxSteps}
          position="static"
          activeStep={activeStep}
          nextButton={
            <Button
              size="small"
              onClick={handleNext}
              disabled={activeStep === maxSteps - 1}
            >
              {theme.direction === "rtl" ? (
                <KeyboardArrowLeft />
              ) : (
                <KeyboardArrowRight />
              )}
            </Button>
          }
          backButton={
            <Button
              size="small"
              onClick={handleBack}
              disabled={activeStep === 0}
            >
              {theme.direction === "rtl" ? (
                <KeyboardArrowRight />
              ) : (
                <KeyboardArrowLeft />
              )}
            </Button>
          }
        />
        <SwipeableViews
          axis={theme.direction === "rtl" ? "x-reverse" : "x"}
          index={activeStep}
          onChangeIndex={handleStepChange}
          enableMouseEvents
        >
          {images.map((step, index) => (
            <div key={step.label} style={{ margin: "1%" }}>
              {activeStep < maxSteps - 1 ? (
                <Box
                  component="img"
                  sx={{
                    height: "60vh",
                    display: "block",
                    overflow: "hidden",
                    width: "100%",
                    objectFit: "cover",
                    borderRadius: "10px",
                  }}
                  src={step.imgPath}
                  alt={step.label}
                />
              ) : (
                <div>
                  <Box
                    component="img"
                    sx={{
                      height: "40vh",
                      display: "block",
                      overflow: "hidden",
                      width: "100%",
                      objectFit: "cover",
                      borderRadius: "10px",
                    }}
                    src={step.imgPath}
                    alt={step.label}
                  />
                  <Box
                    sx={{
                      height: "40vh",
                      marginTop: "10%",
                      width: "100%",
                    }}
                  >
                    <Box
                      sx={{
                        height: "20%",
                        display: "block",
                        overflow: "hidden",
                        width: "80%",
                        objectFit: "cover",
                        borderRadius: "10px",
                      }}
                    >
                      <Button
                        sx={
                          {
                            // display: "flex",
                            // margin: "auto",
                            // position: "absolute",
                            // left: "50%",
                            // top: "85%",
                            // transform: "translate(-50%, -50%)",
                            // width: "80%",
                          }
                        }
                        onClick={() => {}}
                      >
                        <a href={naverLogin}>
                          <img
                            src={
                              process.env.PUBLIC_URL + `assets/naverLogin.png`
                            }
                            alt="naver"
                            style={{ width: "80%", objectFit: "fill" }}
                          ></img>
                        </a>
                      </Button>
                    </Box>
                  </Box>
                </div>
              )}
            </div>
          ))}
        </SwipeableViews>

        <Paper
          square
          elevation={0}
          sx={{
            display: "flex",
            height: "10vh",
            pl: 2,
            bgcolor: "background.default",
            wordBreak: "keep-all",
            marginTop: "5%",
          }}
        >
          <div
            style={{
              fontSize: 25,
              display: "flex",
              margin: "auto",
              marginTop: "3%",
              textAlign: "center",
            }}
          >
            {images[activeStep].label}
          </div>
        </Paper>
      </Box>
    </div>
  );
}

export default LogBox;
