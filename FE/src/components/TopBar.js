//상단바

import * as React from "react";
import AppBar from "@mui/material/AppBar";
import styled from "@emotion/styled";
import PersonSearchIcon from "@mui/icons-material/PersonSearch";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import { useNavigate } from "react-router-dom";

export default function ButtonAppBar() {
  const navigate = useNavigate();

  const MyAppBar = styled(AppBar)`
    background-color: #ffb9b9;
    height: 70px;
  `;

  return (
    <MyAppBar sx={{ justifyItems: "space-between" }}>
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <Box></Box>
          <img
            className="logoTop"
            src={process.env.PUBLIC_URL + `/assets/logo2.png`}
            alt="logoTop"
            style={{ marginTop: "5px", height: "60px" }}
          ></img>
          <IconButton sx={{ position: "fixed", right: "5%" }}>
            <PersonSearchIcon
              sx={{ color: "white" }}
              onClick={() => navigate("/search")}
            ></PersonSearchIcon>
          </IconButton>
        </Toolbar>
      </Container>
    </MyAppBar>
  );
}
