//상단바

import * as React from "react";
import AppBar from "@mui/material/AppBar";
import styled from "@emotion/styled";
import PersonSearchIcon from "@mui/icons-material/PersonSearch";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import { useNavigate } from "react-router-dom";
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

  return (
    <div>
      <MyAppBar>
        <Toolbar>
          {/* <Box></Box> */}
          <img
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
            onClick={() => navigate("/user/1")}
          >
            <Avatar sx={{ color: "white", fontSize: "150%" }}></Avatar>
          </IconButton>
        </Toolbar>
      </MyAppBar>
      <div style={{ height: "70px" }}></div>
    </div>
  );
}
