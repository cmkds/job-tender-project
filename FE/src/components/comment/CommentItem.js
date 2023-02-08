import Profile from "../Profile";

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
      <Profile id={memberSeq} />
      <span>{memberSeq} 번 회원</span>
      {/* <img src={apiUser.userProfileImg} alt="" /> */}
      <br />
      <span>{content}</span>

      {/* 여기서 만약 댓글이 내 댓글 이라면 삭제 버튼 들어가야함. */}
      {/* if 로그인id === id . 삭제버튼 보여짐. */}
      {/*  */}
    </div>
  );
};

export default CommentItem;
