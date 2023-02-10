// /menual
// 로그 박스 사용밥법 페이지
import { useNavigate } from "react-router-dom";

import NavBar from "../components/NavBar";

import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";

const Menual = () => {
  const navigate = useNavigate();
  return (
    <div>
      <NavBar
        navText={"로그박스 사용방법"}
        leftChild={<ArrowBackIosNewIcon onClick={() => navigate(-1)} />}
      />
    </div>
  );
};

export default Menual;
