import Profile from "../Profile";
import { Box } from "@mui/material";
const CommentItem = ({
  commentSeq,
  content,
  createTime,
  modifyTime,
  feedSeq,
  memberSeq,
}) => {
  return (
    <div>
      <div>
        <Profile id={memberSeq} />
      </div>
      {/* <div>{memberSeq} 번 회원</div> */}
      {/* <img src={apiUser.userProfileImg} alt="" /> */}
      {/* <br /> */}
      <Box
        style={{
          marginLeft: "5%",
          marginRight: "5%",
          width: "90%",
          display: "flex",
        }}
        sx={{ border: 1, borderRadius: 4, borderColor: "#C1BCBC" }}
      >
        {content}
      </Box>

      {/* 여기서 만약 댓글이 내 댓글 이라면 삭제 버튼 들어가야함. */}
      {/* if 로그인id === id . 삭제버튼 보여짐. */}
      {/*  */}
    </div>
  );
};

export default CommentItem;
