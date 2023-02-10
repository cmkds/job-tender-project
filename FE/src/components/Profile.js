// 프로필 사진 이름
// 댓글 유저의 아이디만 프롭스로 받아서 해당 정보를 가져온다.
import Avatar from "@material-ui/core/Avatar";
import React, { useEffect, useState } from "react";
import axios from "axios";
import { makeStyles } from "@material-ui/core/styles";
import { useNavigate } from "react-router-dom";

const useStyles = makeStyles((theme) => ({
  root: {
    display: "flex",
    alignItems: "center",
    padding: "2%",
    objectFit: "cover",
  },
  small: {
    width: theme.spacing(3),
    height: theme.spacing(3),
  },
  large: {
    width: theme.spacing(7),
    height: theme.spacing(7),
  },
}));

// 유저아이디로 해당 유저의 정보를 통신해서 가져옴
const Profile = (userId) => {
  const navigate = useNavigate();
  // 유저아이디로 유저데이터 가져오기
  const [userData, setUserData] = useState({});

  // console.log(userId);
  useEffect(() => {
    axios.get(`/api/account/${userId.id}`).then(function (response) {
      // console.log(response.data);
      setUserData(response.data);
    });
  }, []);
  // 이미지 파일 가져오는 api 따로 있음.
  // console.log(userData);
  const classes = useStyles();
  console.log(userData);
  return (
    <div
      className={classes.root}
      onClick={() => {
        navigate(`/user/${userId.id}`);
      }}
    >
      {/* <Avatar src={} className={classes.large}></Avatar> */}
      {/* <div className={classes.root}> {userData.nickname}</div> */}
      <div className={classes.root}>
        <Avatar>
          {/* <img src={it.image} alt={"프로필 이미지가 없습니다."}></img> */}
        </Avatar>
        <div className={classes.root}>
          <div style={{ display: "flex", flexDirection: "column" }}>
            <div>{userData.nickname}</div>
            <div style={{ color: "lightgrey" }}>{userData.memberState}</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile;
