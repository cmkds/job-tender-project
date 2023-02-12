// 팔로우 언팔로우 버튼 컴포넌트
// 팔로잉 페이지에 들어가는 언팔로우 버튼
import Profile from "../Profile";

import axios from "axios";

import { useState, useEffect } from "react";

import Grid from "@mui/material/Grid";
import { Button } from "@mui/material";
import Modal from "@mui/material/Modal";
import Box from "@mui/material/Box";

const style = {
  position: "absolute",

  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "90%",
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  pt: 2,
  px: 2,
  pb: 2,
};

const UnFollowButton = (userId) => {
  // console.log(userId.id);
  const [open, setOpen] = useState(false);
  const [userData, setUserData] = useState({});

  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  useEffect(() => {
    axios.get(`/api/account/${userId.id}`).then(function (response) {
      setUserData(response.data);
    });
  }, []);
  return (
    // <Modal open={open} onClose={handleClose}>
    <Grid container>
      <Grid item xs={9}>
        <Profile id={userId.id} />
      </Grid>
      <Grid item xs={3}>
        <Button
          style={{
            height: "50%",
            display: "flex",
            marginTop: "25%",
            marginLeft: "10%",
            color: "red",
          }}
          onClick={handleOpen}
        >
          언팔로우
        </Button>
        <Modal open={open} onClose={handleClose}>
          <Box sx={{ ...style }}>
            <h4>{userData.nickname}님을 언팔로우 하시겠습니까?</h4>
            <div style={{ marginLeft: "55%" }}>
              <Button color="error">언팔로우</Button>
              <Button onClick={handleClose}>취소</Button>
            </div>
          </Box>
        </Modal>
      </Grid>
    </Grid>
    // </Modal>
  );
};

export default UnFollowButton;
