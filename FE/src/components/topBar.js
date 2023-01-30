//상단바
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import styled from '@emotion/styled';



export default function ButtonAppBar() {
  const MyAppBar = styled(AppBar)`
    display: flex;
    background-color: #FFB9B9;
    height: 60px
    `
  const UnknownAccount = styled(AccountCircleIcon)(({
    position: 'absolute',
    width: 40,
    height: 40,
    color: 'black',
    right: 20,
    top: 20,
  }))
    

  return (
      <MyAppBar position="static">
        <img className="logoTop" src={process.env.PUBLIC_URL + `assets/logo2.png`}></img>
        <UnknownAccount></UnknownAccount>
        {/* <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
          </IconButton>
          <div className="logo2">
            <img src={process.env.PUBLIC_URL + `assets/logo.png`}></img>
          </div>
          <Button>Login</Button>
        </Toolbar> */}
      </MyAppBar> 
  );
}

