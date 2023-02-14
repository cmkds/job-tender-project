import { useParams, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import "../App.css";
import NavBar from "../components/NavBar";
// import MyButton from "../components/MyButton";
import CommentWrite from "../components/comment/CommentWrite";
import CommentList from "../components/comment/CommentList";

import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";

const Comment = () => {
  // const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (!sessionStorage.getItem("loginUser")) {
      navigate("/");
    }
  });

  return (
    <div>
      <NavBar
        navText={"댓글"}
        leftChild={<ArrowBackIosNewIcon onClick={() => navigate(-1)} />}
      />
      <CommentWrite />
      {/* <CommentList /> */}
    </div>
  );
};

export default Comment;
