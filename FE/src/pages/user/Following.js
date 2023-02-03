// /:userId/following

import { useContext } from "react";
import { UserStateContext } from "../../pages/User";

import { useNavigate } from "react-router-dom";

import NavBar from "../../components/NavBar";
import MyButton from "../../components/MyButton";

// 해당 유저를 팔로우 하는 사람을 보여주는 페이지.

const Following = () => {
  const navigate = useNavigate();
  const userData = useContext(UserStateContext)[0][0];
  return (
    <div>
      <NavBar
        navText={"팔로잉"}
        leftChild={<MyButton text={"<"} onClick={() => navigate(-1)} />}
      />
      ;{userData.id}의 팔로잉 페이지다
    </div>
  );
};

export default Following;
