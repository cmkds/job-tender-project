import { useContext } from "react";
import { UserStateContext } from "../../pages/User";
import UserFeedItem from "./UserFeedItem";
import ImageList from "@mui/material/ImageList";
import ImageListItem from "@mui/material/ImageListItem";

const UserFeedList = () => {
  const userData = useContext(UserStateContext)[0][0];
  return (
    <ImageList>
      {userData.feeds.map((it) => (
        <ImageListItem key={it.id} {...it}>
          <img className="post_wrapper" src={it.post_image} />
        </ImageListItem>
      ))}
    </ImageList>
  );
};

export default UserFeedList;
