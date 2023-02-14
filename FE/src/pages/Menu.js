// /menu 메뉴 페이지 보관함 공지사항 로그박스 사용법등 나오는 곳
import { useNavigate } from "react-router-dom";

import MenuBar from "../components/MenuBar";
// import MyButton from "../components/MyButton";

import TopBar from "../components/TopBar";
import { useEffect } from "react";
const Menu = () => {
  const navigate = useNavigate();
  useEffect(() => {
    if (!sessionStorage.getItem("loginUser")) {
      navigate("/");
    }
  });
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
      <div
        onClick={() => {
          navigate("/edit");
        }}
      >
        <MenuBar leftChild={"회원 정보 수정"} rightChild={">"} />
      </div>
    </div>
  );
};

export default Menu;
