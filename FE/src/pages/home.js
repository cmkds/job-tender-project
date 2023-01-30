// 시작 페이지 로그인 화면 뜸
// 로그인시 메인 페이지로 가도록 해야함.
import styled from '@emotion/styled';
import Button from '@mui/material/Button';
import React, {useState} from 'react'
import { motion } from "framer-motion"

const Home = () => {
  const MyBtn = styled(Button)`
    display: flex;
    margin: auto;
  `

  const [clicked, setClicked] = useState(false)

  const printImg = () => {
    if (clicked) {
      return <motion.img initial={{ scale: 1 }} animate={{ scale: 1.3 }} className='postOpen' onClick={()=>{setClicked(!clicked)}} src={process.env.PUBLIC_URL + `assets/open2.png`}/>
    } else {
      return <motion.img initial={{ scale: 1.3 }} animate={{ scale: 1 }} className='postClosed' onClick={()=>{setClicked(!clicked)}} src={process.env.PUBLIC_URL + `assets/post.png`}/>
    }
  }

  return (
    <div>
      <img className='logo' src={process.env.PUBLIC_URL + `assets/logo.png`} alt="logo"></img>
      <MyBtn className='naver' onClick={() => {}}>
        <img src={process.env.PUBLIC_URL + `assets/naverLogin.png`} alt="naver"></img>  
      </MyBtn>
      {printImg()}
      {/* <img className='postClosed' src={process.env.PUBLIC_URL + `assets/post.png`}/> */}
    </div>
  );
};

export default Home;
