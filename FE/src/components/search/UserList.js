// import React, { useState } from "react";

// import { Key } from "@mui/icons-material";
import { Avatar } from "@mui/material";
import { makeStyles } from "@material-ui/core/styles";

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

  // const [clicked, setClicked] = useState();
  // const handleCardClick = (id) => {
  //   setClicked(item.find((el) => el.id === id));
  // };
  console.log(item);

  return (
    <div>
      <p>유저리스트다</p>
      {item.map((it) => (
        <div className={classes.root}>
          <Avatar>
            <img src={it.image} alt={"프로필 이미지가 없습니다."}></img>
          </Avatar>
          <div className={classes.root}>
            <div style={{ display: "flex", flexDirection: "column" }}>
              <div>{it.name}</div>
              <div style={{ color: "lightgrey" }}>{it.email}</div>
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
