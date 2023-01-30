// /menu 메뉴 페이지 보관함 공지사항 로그박스 사용법등 나오는 곳
import { useNavigate } from "react-router-dom";

import MenuBar from "../components/MenuBar";
import MyButton from "../components/MyButton";

const Menu = () => {
  const navigate = useNavigate();
  return (
    <div>
      <MenuBar leftChild={"보관함"} rightChild={">"} />
      <MenuBar leftChild={"공지사항"} rightChild={">"} />
      <MenuBar leftChild={"로그박스 사용방법"} rightChild={">"} />
      <MenuBar leftChild={"로그박스 위치"} rightChild={">"} />
    </div>
  );
};

export default Menu;
