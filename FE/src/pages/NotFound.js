import React from "react";
import { useNavigate } from "react-router-dom";

const NotFoundPage = () => {
  const navigate = useNavigate();

  return (
    <div>
      <h2 style={{ marginLeft: "5%", marginTop: "10%" }} >
        해당 페이지는 존재하지 않습니다.
      </h2>
      <a href="/">
        <h3 style={{ marginLeft: "5%", color: 'blue' }} onClick={() => {
              navigate("/")}}>로그박스 홈으로 돌아가기</h3>
      </a>
      <img
        src="assets/notfound.png"
        style={{
          width: "100vw",
          height: "85vh",
          objectFit: "cover",
        }}
      ></img>
    </div>
  );
};

export default NotFoundPage;
