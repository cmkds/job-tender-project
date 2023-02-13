//상단바
import { useNavigate } from "react-router-dom";

import styled from "@emotion/styled";
import AppBar from "@mui/material/AppBar";
import PersonSearchIcon from "@mui/icons-material/PersonSearch";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import { Avatar } from "@mui/material";

export default function ButtonAppBar() {
  const navigate = useNavigate();

  const MyAppBar = styled(AppBar)`
    background-color: #ffb9b9;
    height: 70px;
    z-index: 1200;
    max-width: 640px;
    right: auto;
    margin: auto;
  `;

  //
  const loginCheck = false;
  const topButton = () => {
    if (loginCheck) {
      navigate("/main/hot/0");
    } else {
      navigate("/");
    }
  };

  return (
    <div>
      <MyAppBar>
        <Toolbar>
          {/* <Box></Box> */}
          {/*  */}
          <img
            onClick={topButton}
            className="logoTop"
            src={process.env.PUBLIC_URL + `/assets/logo2.png`}
            alt="logoTop"
            style={{ height: "60px", marginTop: "5px" }}
          ></img>
          <IconButton
            sx={{ position: "absolute", left: "5%" }}
            onClick={() => navigate("/search")}
          >
            <PersonSearchIcon
              sx={{ color: "white", fontSize: "150%" }}
            ></PersonSearchIcon>
          </IconButton>

          {/* 유저 프로필 가져오기 해야함 */}
          <IconButton
            sx={{ position: "absolute", right: "5%" }}
            // navigate 설정 userId로
            onClick={() => navigate("/user/1")}
          >
            <Avatar sx={{ color: "white", fontSize: "150%" }}>
              {loginCheck && <img src="assets/profile.png" alt=""></img>}

              {/* 여기에 유저 프로필 이미지가 들어가야함 */}
            </Avatar>
          </IconButton>
        </Toolbar>
      </MyAppBar>
      <div style={{ height: "70px" }}></div>
    </div>
  );
}
