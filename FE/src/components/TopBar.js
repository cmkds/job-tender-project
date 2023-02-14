//상단바
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";

import axios from "axios";

import styled from "@emotion/styled";
import AppBar from "@mui/material/AppBar";
import PersonSearchIcon from "@mui/icons-material/PersonSearch";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import { Avatar } from "@mui/material";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import PersonIcon from "@mui/icons-material/Person";
import LogoutIcon from "@mui/icons-material/Logout";

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
  const loginUser = sessionStorage.getItem("loginUser");
  //
  const loginCheck = !(sessionStorage.getItem("loginUser") === null);
  const topButton = () => {
    if (loginCheck) {
      navigate("/main/hot/0");
    } else {
      navigate("/");
    }
  };
  const [profileImg, setProfileImg] = useState("");

  useEffect(() => {
    axios.get(`/api/account/${loginUser}`).then(function (response) {
      // console.log(response.data.memberProfile);
      setProfileImg(response.data.memberProfile);
    });
  });
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  const logout = () => {
    sessionStorage.removeItem("loginUser");
    navigate(`/`);
  };
  const mypage = () => {
    handleClose();
    navigate(`/user/${loginUser}`);
  };

  return (
    <div>
      <MyAppBar>
        <Toolbar>
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
        </Toolbar>
      </MyAppBar>
      <IconButton
        id="basic-button"
        aria-controls={open ? "basic-menu" : undefined}
        aria-haspopup="true"
        aria-expanded={open ? "true" : undefined}
        onClick={handleClick}
        sx={{ position: "fixed", right: "3%", zIndex: "1300", top: "1%" }}
      >
        <Avatar>
          <img
            src={profileImg}
            alt=""
            style={{ width: "100%", height: "100%", objectFit: "cover" }}
          ></img>
        </Avatar>
      </IconButton>
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          "aria-labelledby": "basic-button",
        }}
      >
        <MenuItem
          onClick={mypage}
          sx={{ fontFamily: "GangwonEduAll", alignContent: "end" }}
        >
          마이페이지
          <PersonIcon sx={{ marginLeft: "3%" }} />
        </MenuItem>
        <MenuItem
          onClick={logout}
          sx={{ fontFamily: "GangwonEduAll", justifyContent: "end" }}
        >
          로그아웃
          <LogoutIcon sx={{ marginLeft: "3%" }} />
        </MenuItem>
      </Menu>
      <div style={{ height: "70px" }}></div>
    </div>
  );
}
