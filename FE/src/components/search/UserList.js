// import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

// import { Key } from "@mui/icons-material";
import { Avatar } from "@mui/material";
import { makeStyles } from "@material-ui/core/styles";
import { Key } from "@mui/icons-material";

// import Card from "./Card";
// import Detail from "./Detail";
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

const UserList = ({ item }) => {
  const classes = useStyles();
  const navigate = useNavigate();

  return (
    <div>
      {item.map((it) => (
        <div
          className={classes.root}
          onClick={() => {
            navigate(`/user/${it.memberSeq}`);
          }}
        >
          <Avatar>
            <img
              style={{ width: "100%", height: "100%", objectFit: "cover" }}
              src={it.memberProfile}
              alt={"프로필 이미지가 없습니다."}
            ></img>
          </Avatar>
          <div className={classes.root}>
            <div style={{ display: "flex", flexDirection: "column" }}>
              <div>{it.nickname}</div>
              <div style={{ color: "lightgrey" }}>{it.memberState}</div>
            </div>
          </div>
          {/* <div>{it.name}</div>
            <div>{it.email}</div>
          <p> {it.id}</p>
          <p> {it.phone}</p>
          <p> {it.email}</p> */}
        </div>
        // onClick={() => handleCardClick(monster.id)}
        // clicked={clicked}
        // setClicked={setClicked}
      ))}
      {/* {clicked && <Detail clicked={clicked} setClicked={setClicked} />} */}
    </div>
  );
};
export default UserList;
