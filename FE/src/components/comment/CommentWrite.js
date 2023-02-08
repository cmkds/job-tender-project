import { useState, useRef } from "react";
import axios from "axios";

import { useParams } from "react-router-dom";

const CommentWrite = () => {
  const [state, setState] = useState("");
  const params = useParams();
  const commentInput = useRef();

  const handleChangeState = (e) => {
    setState(e.target.value);
  };
  console.log(params);
  const handleSubmit = () => {
    if (!state) {
      commentInput.current.focus();
      return;
    }
    console.log("확인");
    console.log(state);
    console.log(params.feedId);
    console.log(state);

    // @@@ API 통신 보내기.
    axios.post(`/api/comment`, {
      content: state,
      feedSeq: params.feedId,
      memberSeq: 1,
    });

    alert("댓글 작성 완료");

    setState("");

    // 통신후 페이지 리렌더링 하기
  };

  return (
    <div>
      {/* 로그인한 유저의 이미지 */}
      {/* 댓글 달기 버튼 */}

      <input
        ref={commentInput}
        name="comment"
        value={state}
        onChange={handleChangeState}
      />
      {/* 댓글 쓰기 버튼. */}
      <button onClick={handleSubmit}>게시</button>
    </div>
  );
};

export default CommentWrite;
