// /:userId/following

import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import axios from "axios";

import NavBar from "../../components/NavBar";
import Profile from "../../components/Profile";
import UnFollowButton from "../../components/follow/UnFollowButton";
// import MyButton from "../../components/MyButton";

import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";

// 해당 유저를 팔로우 하는 사람을 보여주는 페이지.

const Following = () => {
  const navigate = useNavigate();
  const params = useParams();
  //@@@@
  const loginUser = sessionStorage.getItem("loginUser");
  const [change, setChange] = useState(0);
  const moveChange = () => {
    setChange(change + 1);
  };

  const [followingList, setFollowingList] = useState([]);
  // 팔로잉 가져오기
  useEffect(() => {
    axios.get(`/api/search/following/${params.user}`).then(function (response) {
      setFollowingList(response.data);
    });
  }, [followingList.length, change]);
  return (
    <div>
      <NavBar
        navText={"팔로잉"}
        leftChild={<ArrowBackIosNewIcon onClick={() => navigate(-1)} />}
      />
      {followingList.length ? (
        loginUser === params.user ? (
          <span>
            {followingList.map((it) => (
              <UnFollowButton
                key={it.memberSeq}
                id={it.memberSeq}
                change={moveChange}
              />
            ))}
          </span>
        ) : (
          <span>
            {followingList.map((it) => (
              <Profile key={it.memberSeq} id={it.memberSeq} />
            ))}
          </span>
        )
      ) : (
        <span>팔로잉 유저가 없습니다.</span>
      )}
    </div>
  );
};

export default Following;
