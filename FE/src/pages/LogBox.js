// logbox 설명 페이지
// /logbox
import { useNavigate } from "react-router-dom";

import NavBar from "../components/NavBar";

import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";

const LogBox = () => {
  const navigate = useNavigate();
  return (
    <div>
      {/* <TopBar /> */}
      <NavBar
        navText={"로그박스 소개"}
        leftChild={<ArrowBackIosNewIcon onClick={() => navigate(-1)} />}
      />
    </div>
  );
};

export default LogBox;