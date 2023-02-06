import React, { useState, useEffect } from "react";
import axios from "axios";
import UserList from "../components/search/UserList";

const Search = () => {
  const [users, setUsers] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [copy, setCopy] = useState([]);

  useEffect(() => {
    const fetch = async () => {
      const { data } = await axios.get(
        "https://jsonplaceholder.typicode.com/users"
      );
      console.log(data);
      data.map(
        (e) => (e.image = `https://robohash.org/${e.id}?set=set2&size=180x180`)
      ); // 불러온 API 파일에 따로 이미지 src가 없어서, data에 추가 저장함

      console.log(data);
      setUsers(data);
      setCopy(data);
    };
    fetch();
    // console.log(users);
  }, []);

  console.log(users.map((it) => it.id));
  // console.log(copy.length);

  const handleInputChange = (e) => {
    setSearchTerm(e.target.value);
  };

  useEffect(() => {
    setUsers(
      copy.filter(
        (e) =>
          e.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
          e.phone.includes(searchTerm) ||
          e.email.toLowerCase().includes(searchTerm.toLowerCase())
      )
    );
  }, [searchTerm, copy]);

  // console.log(`users : ${users}`);
  // console.log(`searchTerm : ${searchTerm}`);
  // console.log(`copy : ${copy}`);
  return (
    <div>
      검색페이지다
      <div>
        <div>
          <title>연락처</title>
          <div>
            <input
              placeholder="이름, 연락처, 이메일 검색"
              onChange={handleInputChange}
            />
          </div>
        </div>
        {parseInt(copy.length) === parseInt(users.length) ? (
          <p>유저 닉네임을 검색하세요</p>
        ) : parseInt(users.length) === 0 ? (
          <p>해당하는 유저가 없습니다.</p>
        ) : (
          <UserList key={users.filter((it) => it.id)} item={users} />
        )}
        {/* users.map((it) => it.id) */}
        {/* {parseInt(copy.length) === parseInt(users.length) || (
          <UserList key={users.idx} item={users} />
        )} */}
      </div>
    </div>
  );
};

export default Search;
