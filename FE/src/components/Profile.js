// 프로필 사진 이름
// 댓글 유저의 아이디만 프롭스로 받아서 해당 정보를 가져온다.
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import axios from "axios";

import Avatar from "@mui/material/Avatar";
import { makeStyles } from "@material-ui/core/styles";

// import Avatar from "@material-ui/core/Avatar";
const useStyles = makeStyles((theme) => ({
  root: {
    display: "flex",
    alignItems: "center",
    padding: "2%",
    objectFit: "cover",
    whiteSpace: "nowrap",
    overflow: "hidden",
    textOverflow: "ellipsis",
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
  // 유저아이디로 유저데이터 가져오기
  const [userData, setUserData] = useState({});

  // console.log(userId);
  // const a = userId.id;

  // 이미지 파일 가져오는 api 따로 있음.
  useEffect(() => {
    axios.get(`/api/account/${userId.id}`).then(function (response) {
      setUserData(response.data);
    });
  }, []);

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
      </div>
      <div className={classes.root}>
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            width: "100px",
          }}
        >
          <div
            style={
              {
                // display: "block",
              }
            }
            className={classes.root}
          >
            {userData.nickname}
          </div>
          <div
            style={{ color: "lightgrey", display: "block" }}
            className={classes.root}
          >
            {userData.memberState}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile;
