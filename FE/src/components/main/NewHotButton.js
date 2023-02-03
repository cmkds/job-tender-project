import * as React from 'react';
import Fab from '@mui/material/Fab';
import EditIcon from '@mui/icons-material/Edit';
import FavoriteIcon from '@mui/icons-material/Favorite';
import NavigationIcon from '@mui/icons-material/Navigation';
import FiberNewOutlinedIcon from '@mui/icons-material/FiberNewOutlined';
import {useState} from "react"

export default function FloatingActionButtons() {
  const [category, setCategory] = useState('hot')
  return (
    <div className='switch-position'>
      {category === 'hot' ?
        <Fab 
          style={{
            backgroundColor:"white"
          }}
          onClick={() => {setCategory("new")}}
        >
          <div
            style={{fontFamily: 'GangwonEduAll', fontSize: '30px', fontWeight: "600", marginBottom: "10%", color: "#201F1F"
          }}>
            뉴
          </div>
        </Fab>:
        <Fab 
          style={{
            backgroundColor:"white"
          }}
          onClick={() => {setCategory("hot")}}  
        >
          <div
            style={{fontFamily: 'GangwonEduAll', fontSize: '30px', fontWeight: "600", marginBottom: "10%", color: "#201F1F"
          }}>
            핫
          </div>
        </Fab>
      }
        {/* <div 
          src="/assets/new.png" 
          style={{
            display:'flex',
            width: '100%',
          }}
        /> */}
    </div>
  );
}