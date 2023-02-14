import { useState, useRef, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

import axios from "axios";

import TopBar from "../components/TopBar";
import PushImage from "../components/PushImage";

import Card from "@mui/material/Card";
import { Avatar } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";

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

  const [state, setState] = useState({
    memberProfile: "",
    nickname: "",
    memberState: "",
  });
  useEffect(() => {
    axios.get(`/api/account/${loginUser}`).then(function (response) {
      setState({
        memberProfile: response.data.memberProfile,
        // memberProfile:
        //   "https://xsgames.co/randomusers/assets/avatars/pixel/1.jpg",
        nickname: response.data.nickname,
        memberState: response.data.nickname,
      });
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
  // image 파일
  // useEffect(() => {
  //   const imageNum = Math.floor(Math.random() * 52 + 1);
  //   setState({
  //     ...state,
  //     memberProfile: `https://xsgames.co/randomusers/assets/avatars/pixel/${imageNum}.jpg`,
  //   });
  // }, []);

  // 받은 파라미터 쿼리 값으로 보내서
  // api 데이터 가져오고
  // 이 데이터로
  // 회원 정보 추가입력 하도록함.
  // sign Up 페이지에서 추가 정보 입력 하도록 함.
  //

  const editUp = () => {
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
              console.log(response.data);

              navigate("/main/hot/0");
            });
        }
      });
  };
  return (
    <div>
      회원정보 수정 화면
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
      </Card>
    </div>
  );
};

export default Edit;
