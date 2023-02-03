import { useContext } from "react";
import { UserStateContext } from "../../pages/User";
import UserFeedItem from "./UserFeedItem";

const UserFeedList = () => {
  const userData = useContext(UserStateContext)[0][0];
  return (
    <div>
      유저피드리스트다.
      {userData.feeds.map((it) => (
        <UserFeedItem key={it.id} {...it} />
      ))}
    </div>
  );
};

export default UserFeedList;
