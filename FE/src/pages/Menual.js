// /menual
import { useNavigate } from "react-router-dom";

import NavBar from "../components/NavBar";
import MyButton from "../components/MyButton";

import TopBar from "../components/TopBar";
// 로그 박스 사용밥법 페이지

const Menual = () => {
  const navigate = useNavigate();
  return (
    <div>
      <TopBar />
      <NavBar
        navText={"로그박스 사용방법"}
        leftChild={<MyButton text={"<"} onClick={() => navigate(-1)} />}
      />
      여기에 사용방법 들어감 ㅋ
    </div>
  );
};

export default Menual;
