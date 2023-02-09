// /menu 메뉴 페이지 보관함 공지사항 로그박스 사용법등 나오는 곳
import { useNavigate } from "react-router-dom";

import MenuBar from "../components/MenuBar";
import MyButton from "../components/MyButton";

import TopBar from "../components/TopBar";

const Menu = () => {
  const navigate = useNavigate();
  return (
    <div>
      <TopBar />
      {/* <div
        style={{
          height: "65px",
        }}
      ></div> */}
      <div
        onClick={() => {
          navigate("/storage");
        }}
      >
        <MenuBar leftChild={"보관함"} rightChild={">"} />
      </div>
      <div
        onClick={() => {
          navigate("/logbox");
        }}
      >
        <MenuBar leftChild={"로그박스 소개"} rightChild={">"} />
      </div>
      <div
        onClick={() => {
          navigate("/menual");
        }}
      >
        <MenuBar leftChild={"로그박스 사용방법"} rightChild={">"} />
      </div>
      <div
        onClick={() => {
          navigate("/map");
        }}
      >
        <MenuBar leftChild={"로그박스 위치"} rightChild={">"} />
      </div>
    </div>
  );
};

export default Menu;
