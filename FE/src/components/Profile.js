// 프로필 사진 이름
// 댓글 유저의 아이디만 프롭스로 받아서 해당 정보를 가져온다.
import React, { useEffect, useState } from "react";

import axios from "axios";

import { makeStyles } from "@material-ui/core/styles";
import { useNavigate } from "react-router-dom";

// import Avatar from "@material-ui/core/Avatar";
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
  const classes = useStyles();
  const navigate = useNavigate();
  // console.log(userId);
  // const a = userId.id;

  // 이미지 파일 가져오는 api 따로 있음.
  useEffect(() => {
    axios.get(`/api/account/${userId.id}`).then(function (response) {
      setUserData(response.data);
    });
  }, []);
  const [userData, setUserData] = useState({});

  return (
    <div
      className={classes.root}
      onClick={() => {
        navigate(`/user/${userId.id}`);
      }}
    >
      {/* <Avatar src={} className={classes.large}></Avatar> */}
      <div className={classes.root}> {userData.nickname}</div>
    </div>
  );
};

export default Profile;
