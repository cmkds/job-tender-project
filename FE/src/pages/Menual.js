// /menual
import { useNavigate } from "react-router-dom";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import NavBar from "../components/NavBar";
// 로그 박스 사용밥법 페이지

const Menual = () => {
  const navigate = useNavigate();
  return (
    <div>
      <NavBar
        navText={"로그박스 사용방법"}
        leftChild={<ArrowBackIosNewIcon onClick={() => navigate(-1)} />}
      />
      여기에 사용방법 들어감 ㅋ
    </div>
  );
};

export default Menual;
