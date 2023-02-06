//상단바
import * as React from "react";
import AppBar from "@mui/material/AppBar";
import styled from "@emotion/styled";

export default function ButtonAppBar() {
  const MyAppBar = styled(AppBar)`
    background-color: #ffb9b9;
    height: 8vh;
  `;

  return (
    <MyAppBar sx={{ marginBottom: "18vw" }}>
      <img
        className="logoTop"
        src={process.env.PUBLIC_URL + `/assets/logo2.png`}
        alt="logoTop"
      ></img>
    </MyAppBar>
  );
}
