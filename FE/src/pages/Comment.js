import { useParams, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import "../App.css";
import NavBar from "../components/NavBar";
// import MyButton from "../components/MyButton";
import CommentWrite from "../components/comment/CommentWrite";
import CommentList from "../components/comment/CommentList";
import BottomBar from "../components/BottomBar";

import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";

const Comment = () => {
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
      <BottomBar />
    </div>
  );
};

export default Comment;
