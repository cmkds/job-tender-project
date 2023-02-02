// /:user/follower
import { useContext } from "react";
import { UserStateContext } from "../../pages/User";
// 해당 유저의 팔로워 확인 페이지

const Follower = () => {
  const userData = useContext(UserStateContext)[0][0];
  return <div>{userData.id}의 팔로워 페이지다</div>;
};

export default Follower;
