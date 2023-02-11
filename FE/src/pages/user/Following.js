// /:userId/following

import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import axios from "axios";

// import { UserStateContext } from "../../pages/User";
import NavBar from "../../components/NavBar";
import Profile from "../../components/Profile";
import UnFollowButton from "../../components/follow/UnFollowButton";
// import MyButton from "../../components/MyButton";

import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";

// 해당 유저를 팔로우 하는 사람을 보여주는 페이지.

const Following = () => {
  const navigate = useNavigate();
  const params = useParams();

  // const userData = useContext(UserStateContext)[0][0];

  const [followingList, setFollowingList] = useState([]);

  // 팔로잉 가져오기
  useEffect(() => {
    axios.get(`/api/search/following/${params.user}`).then(function (response) {
      setFollowingList(response.data);
    });
  }, [followingList.length]);

  return (
    <div>
      <NavBar
        navText={"팔로잉"}
        leftChild={<ArrowBackIosNewIcon onClick={() => navigate(-1)} />}
      />
      {params.user}의 팔로잉 페이지다
      {followingList.map((it) => (
        <UnFollowButton key={it.memberSeq} id={it.memberSeq} />
        // <Profile key={it.memberSeq} id={it.memberSeq} />
      ))}
    </div>
  );
};

export default Following;
