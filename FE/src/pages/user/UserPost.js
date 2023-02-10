// /:userId/:itemId
import { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";

import NavBar from "../../components/NavBar";
import MyButton from "../../components/MyButton";
import FeedItem from "../../components/feed/FeedItem";

// 개별 아이템 보여주는 페이지.
// 여기 어떻게 처리할지 고민.
const UserPost = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const [feedData, setFeedData] = useState(location.state);
  return (
    <div>
      <NavBar
        navText={"내엽서"}
        leftChild={<MyButton text={"<"} onClick={() => navigate(-1)} />}
      />
      <FeedItem {...feedData}></FeedItem>
    </div>
  );
};

export default UserPost;
