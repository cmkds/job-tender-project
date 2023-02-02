// /:userId/following

import { useContext } from "react";
import { UserStateContext } from "../../pages/User";

// 해당 유저를 팔로우 하는 사람을 보여주는 페이지.

const Following = () => {
  const userData = useContext(UserStateContext)[0][0];
  return <div>{userData.id}의 팔로잉 페이지다</div>;
};

export default Following;
