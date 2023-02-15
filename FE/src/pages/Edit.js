import { useState, useRef, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

import axios from "axios";

import TopBar from "../components/TopBar";
import PushImage from "../components/PushImage";
import BottomBar from "../components/BottomBar";

import Card from "@mui/material/Card";
import { Avatar } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Alert from "@mui/material/Alert";
import AlertTitle from "@mui/material/AlertTitle";

const style = {
  position: "absolute",
  fontFamily: "GangwonEduAll",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "90%",
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  borderRadius: 2,
  pt: 2,
  px: 2,
  pb: 2,
};
const useStyles = makeStyles((theme) => ({
  profileImage: {
    width: theme.spacing(15),
    height: theme.spacing(15),
    margin: "auto",
  },
}));

const Edit = () => {
  const navigate = useNavigate();
  const loginUser = sessionStorage.getItem("loginUser");

  //커서 움직이는 용
  const nicknameInput = useRef();
  const memberStateInput = useRef();
  const [nicknameCheck, setNicknameCheck] = useState();
  const [state, setState] = useState({
    memberProfile: "",
    nickname: "",
    memberState: "",
  });
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  useEffect(() => {
    axios.get(`/api/account/${loginUser}`).then(function (response) {
      setState({
        memberProfile: response.data.memberProfile,
        // memberProfile:
        //   "https://xsgames.co/randomusers/assets/avatars/pixel/1.jpg",
        nickname: response.data.nickname,
        memberState: response.data.memberState,
      });
      setNicknameCheck(response.data.nickname);
    });
  }, []);

  // console.log();

  // 값 변화 확인용
  const handleChangeState = (e) => {
    setState({
      ...state,
      [e.target.name]: e.target.value,
    });
  };

  const editUp = () => {
    if (state.nickname === nicknameCheck) {
      axios.put(`/api/account/${loginUser}`, state).then(function (response) {
        navigate("/main/hot/0");
      });
    } else {
      axios
        .get(`/api/account/nickname/${state.nickname}`)
        .then(function (response) {
          if (response.data) {
            alert("중복된 닉네임 입니다. 다른 닉네임을 입력해 주세요.");
            return;
          } else {
            axios
              .put(`/api/account/${loginUser}`, state)
              .then(function (response) {
                navigate("/main/new/0");
              });
          }
        });
    }
  };

  const deleteUp = () => {
    axios.delete(`/api/account/${loginUser}`).then(function (response) {
      alert("탈퇴가 완료되었습니다.");
      navigate(`/`);
    });
  };
  return (
    <div>
      {/* 회원정보 수정 화면 */}
      <Card
        sx={{
          maxWidth: "70%",
          margin: "auto",
          marginTop: "20%",
        }}
      >
        <div style={{ paddingTop: "5%", paddingLeft: "5%", fontSize: 24 }}>
          닉네임
        </div>
        <div style={{ display: "flex" }}>
          <TextField
            ref={nicknameInput}
            name="nickname"
            value={state.nickname}
            color="error"
            onChange={handleChangeState}
            placeholder="최대 10 글자"
            inputProps={{
              maxLength: 10,
              style: { fontFamily: "GangwonEduAll" },
            }}
            style={{ margin: "auto", display: "flex", width: "90%" }}
          />
        </div>
        <div style={{ paddingTop: "5%", paddingLeft: "5%", fontSize: 24 }}>
          상태 메세지
        </div>
        <div style={{ display: "flex" }}>
          <TextField
            ref={memberStateInput}
            name="memberState"
            value={state.memberState}
            onChange={handleChangeState}
            multiline
            rows={4}
            placeholder="최대 20 글자"
            color="error"
            inputProps={{
              maxLength: 20,
              style: { fontFamily: "GangwonEduAll" },
            }}
            style={{ margin: "auto", width: "90%", display: "flex" }}
          />
        </div>
        <Button
          variant="contained"
          style={{
            display: "flex",
            margin: "auto",
            width: "90%",
            height: "40px",
            backgroundColor: "#FFB9B9",
            marginBottom: "10%",
            marginTop: "10%",
          }}
          onClick={editUp}
        >
          <p style={{ fontSize: "150%", fontFamily: "GangwonEduAll" }}>
            회원정보 수정하기
          </p>
        </Button>
        <Button
          variant="text"
          color="error"
          sx={{
            fontFamily: "GangwonEduAll",
            display: "flex",
            marginLeft: "70%",
          }}
          onClick={handleOpen}
        >
          탈퇴하기
        </Button>
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box sx={{ ...style }}>
            <h3 id="child-modal-title">정말 탈퇴하시겠습니까?</h3>
            <div style={{ marginLeft: "50%" }}>
              <Button onClick={deleteUp} color="error">
                탈퇴하기
              </Button>
              <Button onClick={handleClose}>취소</Button>
            </div>
          </Box>
        </Modal>
      </Card>
      <BottomBar />
    </div>
  );
};

export default Edit;
