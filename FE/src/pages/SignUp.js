//회원가입 정보등록 페이지
// 닉네임, 상태메세지, 사진 등록

import { useState, useRef, useEffect } from "react";

import axios from "axios";

import TopBar from "../components/TopBar";

// import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
// import CardActions from "@mui/material/CardActions";
// import CardContent from "@mui/material/CardContent";

import { Avatar } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";

// 유저 프로필 이미지 style code
const useStyles = makeStyles((theme) => ({
  profileImage: {
    width: theme.spacing(15),
    height: theme.spacing(15),
    margin: "auto",
  },
}));

const SignUp = () => {
  //프로필사진
  // 닉네임
  // 상태메세지 정보 필요
  const classes = useStyles();

  //커서 움직이는 용
  const nickNameInput = useRef();
  const statusMessageInput = useRef();

  const [state, setState] = useState({
    image_file: "",
    preview_URL: "/assets/unknown.png",
    nickName: "",
    statusMessage: "",
  });

  let inputRef;

  const saveImage = (e) => {
    e.preventDefault();
    if (e.target.files[0]) {
      // 새로운 이미지를 올리면 createObjectURL()을 통해 생성한 기존 URL을 폐기
      URL.revokeObjectURL(state.preview_URL);
      const preview_URL = URL.createObjectURL(e.target.files[0]);
      setState(() => ({
        ...state,
        image_file: e.target.files[0],
        preview_URL: preview_URL,
      }));
    }
  };

  const deleteImage = () => {
    // createObjectURL()을 통해 생성한 기존 URL을 폐기
    URL.revokeObjectURL(state.preview_URL);
    setState({
      ...state,
      image_file: "",
      preview_URL:
        "https://png.pngtree.com/png-vector/20191115/ourmid/pngtree-beautiful-profile-line-vector-icon-png-image_1990469.jpg",
    });
  };

  // 값 변화 확인용
  const handleChangeState = (e) => {
    setState({
      ...state,
      [e.target.name]: e.target.value,
    });
  };

  useEffect(() => {
    // 컴포넌트가 언마운트되면 createObjectURL()을 통해 생성한 기존 URL을 폐기
    return () => {
      URL.revokeObjectURL(state.preview_URL);
    };
  }, []);

  //제출 함수

  //제출
  // const handleSubmit = () => {
  //   if (!state.nickName) {
  //     authorInput.current.focus();
  //     alert("닉네임은 필수 입력사항입니다.");
  //     return;
  //   }
  //   if (!state.nickName.length > 1) {
  //     authorInput.current.focus();
  //     alert("닉네임은 2글자 이상으로 해주세요");
  //     return;
  //   }
  //   // API 요청 보내기

  //   // 성공시 State 다 지우고 메인페이지로 이동

  //   setState({
  //     profileImg: "",
  //     nickName: "",
  //     statueMessage: "",
  //   });

  //   // 실패시 (닉네임 중복입니다. 닉네임 부분만 빈칸으로 재입력 되게.)

  //   setState({
  //     ...state,
  //     nickName: "",
  //   });
  // };
  const sendImageToServer = async () => {
    if (state.image_file) {
      const formData = new FormData();
      // formData.append("file", state.image_file);
      formData.append("file", state);
      for (var key of formData.keys()) {
        console.log(key);
      }
      for (var value of formData.values()) {
        console.log(value);
      }
      console.log(state);
      console.log(formData);
      await axios.post("/api/image/upload", formData);
      alert("서버에 등록이 완료되었습니다!");
      setState({
        image_file: "",
        preview_URL:
          "https://png.pngtree.com/png-vector/20191115/ourmid/pngtree-beautiful-profile-line-vector-icon-png-image_1990469.jpg",
        nickName: "",
        statusMessage: "",
      });
    } else {
      alert("사진을 등록하세요!");
    }
  };

  return (
    <div>
      <TopBar></TopBar>
      회원가입 정보등록 페이지
      <Card
        sx={{
          maxWidth: "70%",
          margin: "auto",
          marginTop: "20%",
        }}
      >
        <div className="uploader-wrapper">
          <input
            type="file"
            accept="image/*"
            onChange={saveImage}
            // 클릭할 때 마다 file input의 value를 초기화 하지 않으면 버그가 발생할 수 있다
            // 사진 등록을 두개 띄우고 첫번째에 사진을 올리고 지우고 두번째에 같은 사진을 올리면 그 값이 남아있음!
            onClick={(e) => (e.target.value = null)}
            ref={(refParam) => (inputRef = refParam)}
            style={{ display: "none" }}
          />
          <div className="img-wrapper">
            <Avatar
              className={classes.profileImage}
              src={state.preview_URL}
            ></Avatar>
          </div>

          <div className="upload-button">
            <button
              type="primary"
              variant="contained"
              onClick={() => inputRef.click()}
            >
              프로필 이미지 등록
            </button>
            <button color="error" variant="contained" onClick={deleteImage}>
              사진 삭제
            </button>
            <button
              color="success"
              variant="contained"
              onClick={sendImageToServer}
            >
              등록하기
            </button>
          </div>
        </div>
        <div>
          <input
            ref={nickNameInput}
            name="nickName"
            value={state.nickName}
            onChange={handleChangeState}
          />
        </div>
        <div>
          <textarea
            ref={statusMessageInput}
            name="statusMessage"
            value={state.statueMessage}
            onChange={handleChangeState}
          />
        </div>
      </Card>
    </div>
  );
};

export default SignUp;
