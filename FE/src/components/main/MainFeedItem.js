import Profile from "../Profile";
import { useNavigate } from "react-router-dom";
import * as React from "react";
import Stack from "@mui/material/Stack";
import IconButton from "@mui/material/IconButton";
import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";

const MainFeedItem = (feedId) => {
  const navigate = useNavigate();

  return (
    <div>
      {/* <p>{feed.id}번 째 피드</p> */}
      {/* 유저 아이디 넣기 */}
      <Profile />
      {/* array는 [인덱스] 객체는 .key이름 */}
      <div className="post_wrapper">
        <img className="post_wrapper" src={feedId.context.post_image} />
      </div>
      {/* 댓글 넣기 요건 네비게이트로 감.*/}
      <p>{feedId.context.content}</p>
      <FavoriteBorderOutlinedIcon>좋아요 버튼</FavoriteBorderOutlinedIcon>
      <ChatBubbleOutlineIcon onClick={() => navigate(`/comment/${feedId.id}`)}>
        댓글 보기
      </ChatBubbleOutlineIcon>
      <span>{feedId.context.likes.length}개의 좋아요 </span>
      <span>{feedId.context.comments.data.length}개의 댓글</span>
      <hr />
    </div>
  );
};

export default MainFeedItem;
