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

export const UserStateContext = React.createContext();

//더미데이터
const userApiDummy = [
  {
    id: 1,
    nickname: "seungGD",
    profile_img: "https://robohash.org/velquout.png?size=50x50&set=set1",
    feeds: [
      {
        id: 1,
        post_image:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIX8OqhErUwQkSi87GyYjiyCINfpxvvbzUIQ&usqp=CAU",
        // 나머진 굳이 받을 필요 없을것 같음
      },
      {
        id: 5,
        post_image:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIX8OqhErUwQkSi87GyYjiyCINfpxvvbzUIQ&usqp=CAU",
        // 나머진 굳이 받을 필요 없을것 같음
      },
      {
        id: 10,
        post_image:
          "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIX8OqhErUwQkSi87GyYjiyCINfpxvvbzUIQ&usqp=CAU",
        // 나머진 굳이 받을 필요 없을것 같음
      },
    ],
    followers: [2, 4, 5, 6, 7],
    followings: [5, 7, 8, 9],
  },
];

const User = () => {
  return (
    <div>
      {/* <h2>User Page</h2> */}
      <UserStateContext.Provider value={[userApiDummy]}>
        <Routes>
          <Route path="/:user" element={<UserMain />} />
          <Route path="/:user/:itemId" element={<UserPost />} />
          <Route path="/:user/following" element={<Following />} />
          <Route path="/:user/follower" element={<Follower />} />
        </Routes>
      </UserStateContext.Provider>
    </div>
  );
};

export default User;
