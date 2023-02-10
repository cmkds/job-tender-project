// 댓글 컴포넌트
// 해당 댓글 id를 가져와서
// 해당 유저의 목록과 글의 내용을 가져온다
import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import axios from "axios";

// import CommentList from "../Profile";

import CommentItem from "./CommentItem";

const CommentList = () => {
  const [commentData, setCommentData] = useState([]);
  const paramFeed = useParams().feedId;

  useEffect(() => {
    axios.get(`/api/comment/${paramFeed}`).then(function (response) {
      setCommentData(response.data);
    });
  }, [commentData]);

  return (
    <div>
      {commentData.map((it) => (
        <CommentItem key={it.commentSeq} {...it} />
      ))}
    </div>
  );
};

export default CommentList;
