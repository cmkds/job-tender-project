import CommentList from "../components/comment/CommentList";

import { useParams } from "react-router-dom";

const Comment = () => {
  const { id } = useParams();
  return (
    <div>
      Comment
      <p>댓글 상단바 컴포넌트 들어감</p>
      <p>댓글달기 들어감</p>
      <p>댓글 리스트 보여줌</p>
      <CommentList />
    </div>
  );
};

export default Comment;
