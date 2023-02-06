import { width } from "@material-ui/system";
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

const FeedItem = (feedId) => {
  console.log(feedId);
  // const followersData = useContext(FeedStateContext)[0];
  // const feedsData = useContext(FeedStateContext)[1];
  const navigate = useNavigate();

  // const findFeed = () => {
  //   let temp = feedsData.filter((it) => Object.values(feedId).includes(it.id));
  //   return temp;
  // };
  const check = findFeed();

  const [likeCheck, setLikeCheck] = useState(false);

  if (check.length) {
    return (
      <div>
        <p>{check[0].id}번 째 피드</p>
        {/* 유저 아이디 넣기 */}
        <Profile />
        {/* array는 [인덱스] 객체는 .key이름 */}
        <div className="post_wrapper">
          <div>
            <img src={check[0].context.post_image} alt="postCard" />
          </div>
        </div>
        {/* 게시글 여기에 출력 */}
        <div>{"여기 게시글을 넣어주세여 동수님 :)"}</div>
        {/* 좋아요, 댓글 버튼 */}
        <Stack
          direction="row"
          spacing={1}
          style={{ paddingLeft: "2%", paddingBottom: "1%" }}
        >
          {likeCheck === true ? (
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
            </IconButton>
          )}
          {/* 댓글 넣기 요건 네비게이트로 감.*/}
          <IconButton onClick={() => navigate(`/comment/${check[0].id}`)}>
            <ChatBubbleOutlineIcon />
          </IconButton>

          <div style={{ display: "flex", alignItems: "center" }}>
            · 좋아요 {check[0].context.likes.length}개 ·{" "}
            {check[0].context.comments.data.length}개의 댓글
          </div>
        </Stack>
        <hr />
      </div>
    );
  }
};

export default FeedItem;
