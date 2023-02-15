// /storage/:card-no/share
// 엽서 게시물 작성해서 피드에 공유하는 페이지.
import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";

import TextField from "@mui/material/TextField";
import { makeStyles } from "@material-ui/core/styles";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";
import axios from "axios";

const useStyles = makeStyles((theme) => ({
  textField: {
    width: 370,
  },
  characterCount: {
    marginTop: theme.spacing(1),
    color: "gray",
    marginLeft: "78%",
    fontFamily: "GangwonEduAll",
  },
}));

const StorageShare = (store) => {
  const classes = useStyles();
  const [value, setValue] = useState("");
  const messageInput = useRef();
  const navigate = useNavigate();
  console.log(store);

  // const handleChangeState = (e) => {
  //   setValue({
  //     ...value,
  //     [e.target.name]: e.target.value,
  //   });
  // };

  const handleSubmit = () => {
    // API 요청 보내기
    const data = {
      content: value,
      machineLocationSeq: store.data.machineLocationSeq,
      memberSeq: store.data.memberSeq,
      post: store.data.post,
    };
    console.log(data);
    axios.post(`/api/main`, data).then(function (response) {
      console.log(response);
      navigate(-1);
    });

    // 성공시 State 다 지우고 메인페이지로 이동

    setValue("");

    alert("피드가 생성되었습니다.");
  };

  return (
    <div>
      <div style={{ paddingTop: "5%", paddingLeft: "5%", fontSize: 24 }}>
        피드에 엽서 공유하기
      </div>
      <div style={{ display: "flex", margin: "3%" }}>
        <TextField
          id="outlined-multiline-flexible"
          value={value}
          onChange={(event) => setValue(event.target.value)}
          multiline
          rows={6}
          className={classes.textField}
          placeholder="내용을 입력하세요"
          inputProps={{
            style: { fontFamily: "GangwonEduAll" },
          }}
        />
      </div>
      <div className={classes.characterCount}>{value.length} / 1,000</div>

      <div style={{ marginTop: "5%" }}>
        <Button
          style={{
            display: "flex",
            margin: "auto",
            width: "90%",
            height: "60px",
            backgroundColor: "#FFB9B9",
          }}
          variant="contained"
          onClick={handleSubmit}
        >
          <SendIcon />
        </Button>
      </div>
    </div>
  );
};

export default StorageShare;
