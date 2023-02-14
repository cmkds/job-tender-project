// /user/:id
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import axios from "axios";
import qs from "qs";

import UserFeedList from "../../components/user/UserFeedList";
import TopBar from "../../components/TopBar";

import { makeStyles } from "@material-ui/core/styles";
import Avatar from "@material-ui/core/Avatar";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
// 게시물 리스트를 보여줄 페이지.
// 해당 유저의 아이디와 로그인한 아이디가 같다면
// 팔로우 팔로잉 버튼 안보임
// 사진변경, 닉네임 변경 가능
// 팔로워 페이지에서 팔로워 취소 버튼 생김

//다르면
// 메인 페이지에 팔로우, 팔로잉 버튼 보이고 언팔로우 가능
// 팔로잉 페이지에   팔로잉 버튼에 본인이 있으면 언팔로우 가능

//

const useStyles = makeStyles((theme) => ({
  root: {
    alignItems: "center",
    paddingLeft: "8%",
    paddingTop: "8%",
  },
  profile: {
    margin: "auto",
    width: theme.spacing(13),
    height: theme.spacing(13),
  },
  stateMessage: {
    paddingLeft: "8%",
    paddingBottom: "3%",
  },
}));

const UserMain = () => {
  const classes = useStyles();

  const params = useParams();
  const [userProfileData, setUserProfileData] = useState({});
  const [feedLen, setFeedLen] = useState(0);
  const [followerLen, setFollowerLen] = useState(0);
  const [followingLen, setFollowingLen] = useState(0);
  const navigate = useNavigate();

  //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  const loginUser = sessionStorage.getItem("loginUser");
  // console.log(loginUser)
  const [followCheck, setFollowCheck] = useState(true);
  const [followButton, setFollowButton] = useState(followCheck);
  useEffect(() => {
    axios
      .get(`/api/profile/${loginUser}/${params.user}`)
      .then(function (response) {
        console.log(response);
        setFollowCheck(response.data);
      });
  }, []);
  useEffect(() => {
    setFollowButton(followCheck);
  }, [followCheck]);

  console.log(followCheck);
  console.log(followButton);
  // 유저정보 가져오기
  useEffect(() => {
    axios.get(`/api/account/${params.user}`).then(function (response) {
      console.log(response.data);
      setUserProfileData(response.data);
    });
  }, [params]);

  // 게시글수 가져오기
  useEffect(() => {
    axios.get(`/api/main/${params.user}`).then(function (response) {
      // console.log(response.data);
      setFeedLen(response.data.length);
    });
  }, [params]);
  // 팔로워 수 가져오기
  useEffect(() => {
    axios.get(`/api/search/follower/${params.user}`).then(function (response) {
      setFollowerLen(response.data.length);
    });
  }, [followerLen, params, followButton]);

  // 팔로잉 수 가져오기
  useEffect(() => {
    axios.get(`/api/search/following/${params.user}`).then(function (response) {
      setFollowingLen(response.data.length);
    });
  }, [followingLen, params, followButton]);

  // 해당 유저 팔로우 하기
  // console.log(params);

  const followPost = () => {
    const data = {
      follower: loginUser,
      following: params.user,
    };

    axios
      .post(`/api/profile/follow`, qs.stringify(data))
      .then(function (response) {
        console.log(response.data);
      });
    setFollowButton(true);
  };
  // setFollowButton(followCheck);

  return (
    <div>
      <TopBar />
      <div
        style={{
          display: "flex",
          textAlign: "center",
        }}
      >
        <div style={{ marginLeft: "5%", marginTop: "5%", marginBottom: "5%" }}>
          <Avatar
            className={classes.profile}
            src={userProfileData.memberProfile}
          />
          <div
            style={{
              fontFamily: "GangwonEduAll",
              fontWeight: "bold",
              fontSize: 20,
              paddingTop: "3%",
            }}
          >
            {" "}
            {userProfileData.nickname}
          </div>
        </div>
        <div
          style={{
            paddingLeft: "3%",
            margin: "auto",
            fontFamily: "GangwonEduAll",
            fontWeight: "bold",
            fontSize: 16,
          }}
        >
          게시글
          <br />
          {/* 여기 수정 */}
          {feedLen}
        </div>
        <div
          style={{
            margin: "auto",
            fontFamily: "GangwonEduAll",
            fontWeight: "bold",
            fontSize: 16,
          }}
          onClick={() => navigate(`/user/${params.user}/follower`)}
        >
          <p>
            팔로워
            <br />
            {/* 여기수정 */}
            {followerLen}
          </p>
        </div>
        <div
          style={{
            margin: "auto",
            fontFamily: "GangwonEduAll",
            fontWeight: "bold",
            fontSize: 16,
          }}
          onClick={() => navigate(`/user/${params.user}/following`)}
        >
          <p>
            팔로잉
            <br />
            {/* 여기수정 */}
            {followingLen}
          </p>

          {/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */}
        </div>
      </div>
      <Grid container>
        <Grid item xs={9}>
          <div className={classes.stateMessage}>
            {" "}
            {userProfileData.memberState}
          </div>
        </Grid>
        {/*로그인한 사람과 해당 페이지 유저가 같으면 팔로우 버튼 표시 안함  */}

        {loginUser === params.user ? (
          <Grid item xs={3} />
        ) : followButton ? (
          <Grid item xs={3}>
            <Button disabled>팔로우 중</Button>
          </Grid>
        ) : (
          <Grid item xs={3}>
            <Button
              style={{ color: "#6892FF", paddingLeft: "20%" }}
              onClick={followPost}
            >
              팔로우
            </Button>
          </Grid>
        )}
      </Grid>

      <hr style={{ marginTop: "5%" }} />
      <UserFeedList />
    </div>
  );
};
{
  /* 팔로우, 팔로잉 중 여기 */
}
// <Grid item xs={3}>
//   <Button style={{ color: "#6892FF", paddingLeft: "20%" }} onClick={followPost>
//     팔로우
//   </Button>
// </Grid>
// <Grid item xs={3}>
//   <Button disabled>팔로우 중</Button>
// </Grid>

export default UserMain;
