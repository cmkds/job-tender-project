import CommentList from "../components/comment/CommentList";

import { useParams, useNavigate } from "react-router-dom";

import NavBar from "../components/NavBar";
import MyButton from "../components/MyButton";
import CommentWrite from "../components/comment/CommentWrite";

const Comment = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  return (
    <div>
      <NavBar
        navText={"댓글"}
        leftChild={<MyButton text={"<"} onClick={() => navigate(-1)} />}
      />
      <CommentWrite />
      <CommentList />
    </div>
  );
};

export default Comment;
