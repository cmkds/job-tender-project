import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import axios from "axios";

import Profile from "../Profile";

import Stack from "@mui/material/Stack";
import IconButton from "@mui/material/IconButton";
import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";

const FeedItem = (feed) => {
  //로그인 유저 정보  이거 나중에 바꿔야함.
  const loginUser = 1;

  const navigate = useNavigate();

  // 게시물의 좋아요 개수
  const [likes, setLikes] = useState(0);

  const [likesLen, setLikesLen] = useState(likes);
  //댓글 수 확인
  const [comments, setComments] = useState([]);
  //좋아요 여부 체크
  const [likeCheck, setLikeCheck] = useState(false);

  // 좋아요 수 받아오기
  useEffect(() => {
    axios.get(`/api/feed/heart/${feed.feedSeq}`).then(function (response) {
      // console.log(response.data.length);
      setLikes(response.data.length);
    });
  }, [likeCheck]);
  // console.log(`likes : ${likes}`);

  // 댓글 받아오기
  useEffect(() => {
    axios.get(`/api/comment/${feed.feedSeq}`).then(function (response) {
      // console.log(response.data);
      setComments(response.data);
    });
  }, []);

  // 로그인한 유저의 좋아요 상태 가져오기
  useEffect(() => {
    axios
      .get(`/api/heart/${feed.feedSeq}/${loginUser}`)
      .then(function (response) {
        setLikeCheck(response.data);
      });
  }, []);

  // 좋아요 눌렀을 때 처리, 좋아요 되있으면 취소 보내고 안되있으면 생성 보냄.
  useEffect(() => {
    setLikesLen(likes);
  }, [likes]);

  const heartButton = () => {
    // console.log(likeCheck);
    if (likeCheck) {
      // 좋아요가 되어있으므로 취소 요청을 보낸다.
      axios
        .delete(`/api/heart/${feed.feedSeq}/${loginUser}`)
        .then(function (response) {
          console.log(response);
        });
      setLikeCheck(false);
      setLikesLen(likesLen - 1);
    } else {
      axios
        .post(`/api/heart`, {
          feedSeq: feed.feedSeq,
          memberSeq: loginUser,
        })
        .then(function (response) {
          console.log(response);
        });
      setLikesLen(likesLen + 1);
      setLikeCheck(true);
    }
  };

  return (
    <div>
      <p>{feed.feedSeq}번 째 피드</p>
      {/* 유저 아이디 넣기 */}
      <Profile id={feed.memberSeq} />
      {/* array는 [인덱스] 객체는 .key이름 */}
      <div className="post_wrapper">
        <div>
          <img src={feed.post} alt={feed.post} />
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
        {likeCheck === false ? (
          <IconButton
            onClick={() => {
              heartButton();
            }}
          >
            <FavoriteBorderOutlinedIcon />
          </IconButton>
        ) : (
          <IconButton
            color="primary"
            onClick={() => {
              heartButton();
            }}
          >
            <FavoriteIcon sx={{ color: "red" }} />
          </IconButton>
        )}
        {/* 댓글 넣기 요건 네비게이트로 감.*/}
        <IconButton onClick={() => navigate(`/comment/${feed.feedSeq}`)}>
          <ChatBubbleOutlineIcon />
        </IconButton>

        <div style={{ display: "flex", alignItems: "center" }}>
          · 좋아요 {likesLen}개 · {comments.length}개의 댓글
        </div>
      </Stack>
      <hr />
    </div>
  );
};

export default FeedItem;
