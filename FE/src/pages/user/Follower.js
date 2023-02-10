// /:user/follower
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import axios from "axios";

// import { UserStateContext } from "../../pages/User";
import NavBar from "../../components/NavBar";
import Profile from "../../components/Profile";
// import FollowerList from "../../components/follow/FollowerList";

// 해당 유저의 팔로워 확인 페이지

import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
const Follower = () => {
  const navigate = useNavigate();
  const params = useParams();

  // const userData = useContext(UserStateContext)[0][0];
  const [followerList, setFollowerList] = useState([]);

  useEffect(() => {
    axios.get(`/api/search/follower/${params.user}`).then(function (response) {
      setFollowerList(response.data);
    });
  }, [followerList.length]);

  // 팔로워 가져오기

  return (
    <div>
      <NavBar
        navText={"팔로워"}
        leftChild={<ArrowBackIosNewIcon onClick={() => navigate(-1)} />}
      />
      {params.user}의 팔로워 페이지다
      {followerList.map((it) => (
        <Profile key={it.memberSeq} id={it.memberSeq} />
      ))}
    </div>
  );
};

export default Follower;
