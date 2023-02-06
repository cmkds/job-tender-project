import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { FeedStateContext } from "../../pages/Feed";
import Profile from "../Profile";
import * as React from "react";
import Stack from "@mui/material/Stack";
import IconButton from "@mui/material/IconButton";
import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";

const FeedItem = (feed) => {
  // 좋아요 수 받아와 야함

  // 댓글 수 받아와야함.

  console.log(feed);
  const navigate = useNavigate();

  return (
    <div>
      <p>{feed.feedSeq}번 째 피드</p>
      {/* 유저 아이디 넣기 */}
      <Profile id={feed.memberSeq} />
      {/* array는 [인덱스] 객체는 .key이름 */}
      <div className="post_wrapper">
        <div>
          <img src={feed.post} alt="postCard" />
        </div>
      </div>
      {/* 게시글 여기에 출력 */}
      <div>{feed.content}</div>
      {/* 좋아요, 댓글 버튼 */}
      <Stack
        direction="row"
        spacing={1}
        style={{ paddingLeft: "2%", paddingBottom: "1%" }}
      >
        {/* {likeCheck === true ? (
          <IconButton
            onClick={() => {
              setLikeCheck(!likeCheck);
            }}
          >
            <FavoriteBorderOutlinedIcon />
          </IconButton>
        ) : (
          <IconButton
            color="primary"
            onClick={() => {
              setLikeCheck(!likeCheck);
            }}
          >
            <FavoriteIcon sx={{ color: "red" }} />
          </IconButton> */}
        {/* )} */}
        {/* 댓글 넣기 요건 네비게이트로 감.*/}
        <IconButton onClick={() => navigate(`/comment/${feed.feedSeq}`)}>
          <ChatBubbleOutlineIcon />
        </IconButton>

        <div style={{ display: "flex", alignItems: "center" }}>
          {/* · 좋아요 {check[0].context.likes.length}개 ·{" "}
          {check[0].context.comments.data.length}개의 댓글 */}
        </div>
      </Stack>
      <hr />
    </div>
  );
};

export default FeedItem;
