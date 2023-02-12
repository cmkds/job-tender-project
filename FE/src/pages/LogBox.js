// // logbox 설명 페이지
// // /logbox
// import { useNavigate } from "react-router-dom";

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
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import KeyboardArrowLeft from "@mui/icons-material/KeyboardArrowLeft";
import KeyboardArrowRight from "@mui/icons-material/KeyboardArrowRight";
import SwipeableViews from "react-swipeable-views";
import KeyboardDoubleArrowDownRoundedIcon from "@mui/icons-material/KeyboardDoubleArrowDownRounded";

const images = [
  {
    label: "logbox는 여행지에서의 소중한 추억을",
    imgPath: "assets/logbox/travel2.jpg",
  },
  {
    label: "어쩌구 저쩌구",
    imgPath: "assets/logbox/travel3.jpg",
  },
  {
    label: "우리 팀원 다같이 멘트 짜주기",
    imgPath: "assets/logbox/memorize.jpg",
  },
  {
    label: "약속~~~",
    imgPath: "assets/logbox/post.jpg",
  },
];

const images1 = [
  {
    label: "logbox는 여행지에서의 소중한 추억을",
    imgPath: "assets/logbox/custom1.jpg",
  },
  {
    label: "어쩌구 저쩌구",
    imgPath: "assets/logbox/custom2.jpg",
  },
  {
    label: "우리 팀원 다같이 멘트 짜주기",
    imgPath: "assets/logbox/custom3.jpg",
  },
  {
    label: "약속~~~",
    imgPath: "assets/logbox/custom4.jpg",
  },
  {
    label: "꼭이요~~~~",
    imgPath: "assets/logbox/share.jpg",
  },
  {
    label: "꼭이요~~~~",
    imgPath: "assets/logbox/share2.jpg",
  },
];

const images2 = [
  {
    label: "logbox는 여행지에서의 소중한 추억을",
    imgPath: "assets/logbox/travel2.jpg",
  },
  {
    label: "어쩌구 저쩌구",
    imgPath: "assets/logbox/travel3.jpg",
  },
  {
    label: "우리 팀원 다같이 멘트 짜주기",
    imgPath: "assets/logbox/photocard.jpg",
  },
  {
    label: "약속~~~",
    imgPath: "assets/logbox/post.jpg",
  },
];
function SwipeableTextMobileStepper() {
  const myRef = useRef(null);
  const theme = useTheme();
  const [activeStep, setActiveStep] = useState(0);
  const maxSteps = images.length;
  const maxSteps1 = images1.length;

  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleStepChange = (step) => {
    setActiveStep(step);
  };

  const scrollToElement = () => {
    setActiveStep(0);
    myRef.current.scrollToElement();
  };

  return (
    <div>
      <Box sx={{ width: "100vw", height: "100vh" }}>
        <TopBar></TopBar>
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
            <div key={step.label}>
              {Math.abs(activeStep - index) <= 2 ? (
                <Box
                  component="img"
                  sx={{
                    height: "50vh",
                    display: "block",
                    maxWidth: 400,
                    overflow: "hidden",
                    width: "100%",
                    objectFit: "cover",
                  }}
                  src={step.imgPath}
                  alt={step.label}
                />
              ) : null}
            </div>
          ))}
        </SwipeableViews>
        <Paper
          square
          elevation={0}
          sx={{
            display: "flex",
            alignItems: "center",
            justifyitem: "center",
            height: "10vh",
            pl: 2,
            bgcolor: "background.default",
            wordBreak: "keep-all",
          }}
        >
          <div
            style={{
              fontSize: 30,
              display: "flex",
              margin: "auto",
              marginTop: "20%",
              textAlign: "center",
            }}
          >
            {images[activeStep].label}
          </div>
        </Paper>
        {activeStep === 3 ? (
          <div className="arrow-icon">
            <i className="fas fa-arrow-right">
              <KeyboardDoubleArrowDownRoundedIcon
                sx={{ fontSize: "100px", color: "grey", width: "100vw" }}
                onClick={scrollToElement}
              />
            </i>
          </div>
        ) : null}
      </Box>
      <Box sx={{ width: "100vw", height: "100vh" }} ref={myRef}>
        <MobileStepper
          steps={maxSteps1}
          position="static"
          activeStep={activeStep}
          nextButton={
            <Button
              size="small"
              onClick={handleNext}
              disabled={activeStep === maxSteps1 - 1}
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
          {images1.map((step, index) => (
            <div key={step.label}>
              {Math.abs(activeStep - index) <= 2 ? (
                <Box
                  component="img"
                  sx={{
                    height: "50vh",
                    display: "block",
                    maxWidth: 400,
                    overflow: "hidden",
                    width: "100%",
                    objectFit: "cover",
                  }}
                  src={step.imgPath}
                  alt={step.label}
                />
              ) : null}
            </div>
          ))}
        </SwipeableViews>
        <Paper
          square
          elevation={0}
          sx={{
            display: "flex",
            alignItems: "center",
            justifyitem: "center",
            height: "10vh",
            pl: 2,
            bgcolor: "background.default",
            wordBreak: "keep-all",
          }}
        >
          <div
            style={{
              fontSize: 30,
              display: "flex",
              margin: "auto",
              marginTop: "20%",
              textAlign: "center",
            }}
          >
            {images1[activeStep].label}
          </div>
        </Paper>
        {activeStep === 3 ? (
          <div className="arrow-icon">
            <i className="fas fa-arrow-right">
              <KeyboardDoubleArrowDownRoundedIcon
                sx={{ fontSize: "100px", color: "grey", width: "100vw" }}
              />
            </i>
          </div>
        ) : null}
      </Box>
      <Box sx={{ width: "100vw", height: "100vh" }} ref={myRef}>
        <Box
          component="img"
          sx={{
            height: "50vh",
            display: "block",
            maxWidth: 400,
            overflow: "hidden",
            width: "100%",
            objectFit: "cover",
          }}
          src="assets/logbox/memorized.jpg"
          alt=""
        />
      </Box>
    </div>
  );
}

export default SwipeableTextMobileStepper;
