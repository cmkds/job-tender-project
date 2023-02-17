// /:user/follower
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import axios from "axios";

import NavBar from "../../components/NavBar";
import Profile from "../../components/Profile";
// import FollowerList from "../../components/follow/FollowerList";

// 해당 유저의 팔로워 확인 페이지

import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";

const Follower = () => {
  const navigate = useNavigate();
  const params = useParams();

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
      {followerList.length ? (
        followerList.map((it) => (
          <div>
            <Profile key={it.memberSeq} id={it.memberSeq} />
          </div>
        ))
      ) : (
        <span> 팔로워가 없습니다.</span>
      )}
    </div>
  );
};

export default Follower;
