// /:userId

// 개인 피드 페이지.
// 로그인 유저와 동일 할 시 마이 피드 페이지
// 아니면 일반 개인 피드 페이지
import React from "react";
import { Routes, Route } from "react-router-dom";

import Follower from "./user/Follower";
import Following from "./user/Following";
import UserPost from "./user/UserPost";
import UserMain from "./user/UserMain";

const User = () => {
  return (
    <div>
      <Routes>
        <Route path="/:user" element={<UserMain />} />
        <Route path="/:user/:itemId" element={<UserPost />} />
        <Route path="/:user/following" element={<Following />} />
        <Route path="/:user/follower" element={<Follower />} />
      </Routes>
    </div>
  );
};

export default User;
