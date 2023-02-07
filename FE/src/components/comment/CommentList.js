// 댓글 컴포넌트
// 해당 댓글 id를 가져와서
// 해당 유저의 목록과 글의 내용을 가져온다
import React, { useState, useEffect } from "react";

import axios from "axios";

import CommentItem from "./CommentItem";
import { useParams } from "react-router-dom";
// api로 통신
// 해당 url을 가져온다.
//댓글 정보들이 올 것이다.

// const dummyData = [
//   {
//     id: 1,
//     userId: 1,
//     // 작성시간도 올것인데 작성시간으로 정렬해준다.
//     // userProfileImg: "http://dummyimage.com/153x100.png/ff4444/ffffff",
//     content: "안녕 나는 댓글 1이야",
//   },
//   {
//     id: 2,
//     userId: 1,
//     // userProfileImg: "http://dummyimage.com/184x100.png/cc0000/ffffff",
//     content: "안녕 나는 댓글 2이야",
//   },
// ];

const CommentList = () => {
  const [commentData, setCommentData] = useState([]);
  //파라미터가져오기
  const paramFeed = useParams().feedId;

  // console.log(paramFeed);
  useEffect(() => {
    axios.get(`/api/comment/${paramFeed}`).then(function (response) {
      // console.log(response.data);
      setCommentData(response.data);
    });
  }, []);
  console.log(commentData.map((it) => it));
  // console.log(commentData.map((comments) => comments[0].commentSeq));
  // commentData.map((it) => console.log(it.filter((that) => that.commentSeq)));

  return (
    <div>
      <p>asas</p>
      {commentData.map((it) => (
        // <CommentItem {...it} />
        <CommentItem key={it.commentSeq} {...it} />
      ))}
    </div>
  );
};

export default CommentList;
