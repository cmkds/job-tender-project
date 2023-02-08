import { useContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { FeedStateContext } from "../../pages/Feed";
import axios from "axios";
import Profile from "../Profile";
import * as React from "react";
import Stack from "@mui/material/Stack";
import IconButton from "@mui/material/IconButton";
import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";

const FeedItem = (feed) => {
  //로그인 유저 정보
  const loginUser = 1;

  //좋아요 여부 체크
  const [likeCheck, setLikeCheck] = useState(false);

  // console.log(feed);
  // 좋아요 받아와 야함
  useEffect(() => {
    axios.get(`/api/feed/heart/${feed.feedSeq}`).then(function (response) {
      // console.log(response.data.length);
      setLikes(response.data.length);
    });
  }, [likeCheck]);

  // 댓글 받아와야함.

  useEffect(() => {
    axios.get(`/api/comment/${feed.feedSeq}`).then(function (response) {
      // console.log(response.data);
      setComments(response.data);
    });
  }, []);

  // 로그인한 유저의 좋아요 상태 가져오기
  // 유저 아이디 파라미터로 써야함. 아직 없어서 주석처리 해 둠.
  // console.log(feed.feedSeq);
  // console.log(loginUser);

  useEffect(() => {
    axios
      .get(`/api/heart/${feed.feedSeq}/${loginUser}`)
      .then(function (response) {
        // console.log(response.data);
        setLikeCheck(response.data);
      });
  }, []);

  /////////////////////////
  // 좋아요 눌렀을 대 처리 좋아요 되있으면 취소 보내고 안되있으면 생성 보냄.

  const heartButton = () => {
    // console.log(likes);
    if (likeCheck) {
      // 좋아요가 되어있으므로 취소 요청을 보낸다.
      axios
        .delete(`/api/heart/${feed.feedSeq}/${loginUser}`)
        .then(function (response) {
          // console.log(response);
          setLikeCheck(!likeCheck);
        });
    } else {
      setLikeCheck(!likeCheck);
      axios
        .post(`/api/heart`, {
          feedSeq: feed.feedSeq,
          memberSeq: loginUser,
        })
        .then(function (response) {
          // console.log(response);
        });
    }
  };

  const navigate = useNavigate();

  // 게시물의 좋아요 개수
  const [likes, setLikes] = useState(0);

  //댓글 수 확인
  const [comments, setComments] = useState([]);

  // console.log(likeCheck);
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
          · 좋아요 {likes}개 · {comments.length}개의 댓글
        </div>
      </Stack>
      <hr />
    </div>
  );
};

export default FeedItem;
