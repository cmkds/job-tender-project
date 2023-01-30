// feed, main, menu 버튼 컴포넌트
import { useNavigate } from 'react-router-dom';

import * as React from 'react';
import Box from '@mui/material/Box';
import CssBaseline from '@mui/material/CssBaseline';
import BottomNavigation from '@mui/material/BottomNavigation';
import BottomNavigationAction from '@mui/material/BottomNavigationAction';
import MailOutlineIcon from '@mui/icons-material/MailOutline';
import HomeIcon from '@mui/icons-material/Home';
import MenuIcon from '@mui/icons-material/Menu';
import Paper from '@mui/material/Paper';
// import { Link } from "react-router-dom";
import Fab from '@mui/material/Fab'

export default function FixedBottomNavigation() {
  const [value, setValue] = React.useState(null);
  const ref = React.useRef(null);
  const navigate = useNavigate()
  const goMenu = navigate('/menu')

  return (
    <Box sx={{ pb: 7 }} ref={ref}>
      <CssBaseline />
      <Paper sx={{ position: 'fixed', display: 'flex', bottom: 0 }} elevation={3}>
        <BottomNavigation
          className="BottomBar"
          showLabels
          value={value}
          
          onChange={(event, newValue) => {
            setValue(newValue);
          }}
          sx={{
            ' .Mui-selected':{
              color:'#FFFFFF',
              bgcolor:'#FFB9B9',
              borderRadius: '7px'
            }
          }}
        >
          <BottomNavigationAction icon={<MailOutlineIcon />} />
          <BottomNavigationAction icon={<HomeIcon />} />
          <BottomNavigationAction icon={<MenuIcon />} />
        </BottomNavigation>
      </Paper>
    </Box>
  );
}
