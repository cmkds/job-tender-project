// /:user/follower
import { useContext, useEffect, useState } from "react";
import { UserStateContext } from "../../pages/User";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

import NavBar from "../../components/NavBar";
// 해당 유저의 팔로워 확인 페이지
import FollowerList from "../../components/follow/FollowerList";
import Profile from "../../components/Profile";

const Follower = () => {
  const navigate = useNavigate();
  const params = useParams();

  const userData = useContext(UserStateContext)[0][0];
  const [followerList, setFollowerList] = useState([]);

  useEffect(() => {
    axios.get(`/api/search/follower/${params.user}`).then(function (response) {
      setFollowerList(response.data);
    });
  }, [followerList.length]);

  // 팔로워 가져오기

  // console.log(params);
  // console.log(followerList);
  // console.log(followerList.map(it =>))

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
      {/* <FollowerList /> */}
    </div>
  );
};

export default Follower;
