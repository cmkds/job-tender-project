import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import axios from "axios";

import UserList from "../components/search/UserList";
import BottomBar from "../components/BottomBar";

import InputAdornment from "@material-ui/core/InputAdornment";
import Paper from "@mui/material/Paper";
import InputBase from "@mui/material/InputBase";
import Divider from "@mui/material/Divider";
import SearchIcon from "@mui/icons-material/Search";

export default function CustomizedInputBase() {
  const navigate = useNavigate();
  // useEffect(() => {
  //   if (!sessionStorage.getItem("loginUser")) {
  //     navigate("/");
  //   }
  // });
  const [users, setUsers] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [copy, setCopy] = useState([]);

  // useEffect(() => {
  //   const fetch = async () => {
  //     const { data } = await axios.get(
  //       "https://jsonplaceholder.typicode.com/users"
  //     );
  //     data.map(
  //       (e) => (e.image = `https://robohash.org/${e.id}?set=set2&size=180x180`)
  //     ); // 불러온 API 파일에 따로 이미지 src가 없어서, data에 추가 저장함

  //     setUsers(data);
  //     setCopy(data);
  //   };
  //   fetch();
  // }, []);

  useEffect(() => {
    axios.get(`/api/accounts`).then(function (response) {
      setUsers(response.data);
      setCopy(response.data);
    });
  }, []);

  const handleInputChange = (e) => {
    setSearchTerm(e.target.value);
  };

  useEffect(() => {
    setUsers(
      copy.filter(
        (e) => e.nickname.toLowerCase().includes(searchTerm.toLowerCase())
        // e.nickname.toLowerCase().includes(searchTerm.toLowerCase()) ||
        // e.phone.includes(searchTerm) ||
        // e.email.toLowerCase().includes(searchTerm.toLowerCase())
      )
    );
  }, [searchTerm, copy]);
  console.log(users);

  return (
    <div>
      <Paper
        component="form"
        sx={{
          p: "2px 4px",
          display: "flex",
          alignItems: "center",
          width: "100%",
        }}
      >
        <InputBase
          sx={{
            ml: 1,
            flex: 1,
            height: "50px",
            bgcolor: "#ECECEC",
            borderRadius: "10px",
            paddingLeft: "3%",
          }}
          placeholder="이름, 연락처, 이메일 검색"
          inputProps={{
            style: { fontFamily: "GangwonEduAll" },
          }}
          onChange={handleInputChange}
          startAdornment={
            <InputAdornment position="start">
              <SearchIcon />
              <Divider
                sx={{ height: 28, m: 0.5, paddingLeft: "3%" }}
                orientation="vertical"
              />
            </InputAdornment>
          }
        ></InputBase>
        <div
          style={{
            fontSize: "100%",
            padding: "3%",
          }}
          onClick={() => navigate(-1)}
        >
          취소
        </div>
      </Paper>
      {parseInt(copy.length) === parseInt(users.length) ? (
        <p style={{ marginLeft: "5%" }}>유저 닉네임을 검색하세요</p>
      ) : parseInt(users.length) === 0 ? (
        <p>해당하는 유저가 없습니다.</p>
      ) : (
        <UserList key={users.filter((it) => it.memberSeq)} item={users} />
      )}
      <BottomBar />
    </div>
  );
}

// const Search = () => {
//   const [users, setUsers] = useState([]);
//   const [searchTerm, setSearchTerm] = useState("");
//   const [copy, setCopy] = useState([]);

//   useEffect(() => {
//     const fetch = async () => {
//       const { data } = await axios.get(
//         "https://jsonplaceholder.typicode.com/users"
//       );
//       console.log(data);
//       data.map(
//         (e) => (e.image = `https://robohash.org/${e.id}?set=set2&size=180x180`)
//       ); // 불러온 API 파일에 따로 이미지 src가 없어서, data에 추가 저장함

//       console.log(data);
//       setUsers(data);
//       setCopy(data);
//     };
//     fetch();
//     // console.log(users);
//   }, []);

//   console.log(users.map((it) => it.id));
//   // console.log(copy.length);

//   const handleInputChange = (e) => {
//     setSearchTerm(e.target.value);
//   };

//   useEffect(() => {
//     setUsers(
//       copy.filter(
//         (e) =>
//           e.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
//           e.phone.includes(searchTerm) ||
//           e.email.toLowerCase().includes(searchTerm.toLowerCase())
//       )
//     );
//   }, [searchTerm, copy]);

//   // console.log(`users : ${users}`);
//   // console.log(`searchTerm : ${searchTerm}`);
//   // console.log(`copy : ${copy}`);
//   return (
//     <div>
//       <div>
//         <div>
//           <title>연락처</title>
//           <div>
//             <input
//               placeholder="이름, 연락처, 이메일 검색"
//               onChange={handleInputChange}
//             />
//           </div>
//         </div>
//         {parseInt(copy.length) === parseInt(users.length) ? (
//           <p>유저 닉네임을 검색하세요</p>
//         ) : parseInt(users.length) === 0 ? (
//           <p>해당하는 유저가 없습니다.</p>
//         ) : (
//           <UserList key={users.filter((it) => it.id)} item={users} />
//         )}
//         {/* users.map((it) => it.id) */}
//         {/* {parseInt(copy.length) === parseInt(users.length) || (
//           <UserList key={users.idx} item={users} />
//         )} */}
//       </div>
//     </div>
//   );
// };

// export default Search;
