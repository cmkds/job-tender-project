// /:userId

// 개인 피드 페이지.
// 로그인 유저와 동일 할 시 마이 피드 페이지
// 아니면 일반 개인 피드 페이지
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Follower from "./user/Follower";
import Following from "./user/Following";
import UserItem from "./user/UserItem";
import UserList from "./user/UserList";

const User = () => {
  return (
    <BrowserRouter>
      <h2>User Page</h2>
      <h2>App.js</h2>

      <Routes>
        <Route path="/:user/list" element={<UserList />} />
        <Route path="/:user/:itemId" element={<UserItem />} />
        <Route path="/:user/following" element={<Following />} />
        <Route path="/:user/follower" element={<Follower />} />
      </Routes>
    </BrowserRouter>
  );
};

export default User;
