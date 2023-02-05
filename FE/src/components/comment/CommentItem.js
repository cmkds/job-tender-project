const CommentItem = ({ userId, content }) => {
  //여기서 유저 id값으로 통신해서 닉네임이랑, 프로필 이미지를 가져온다.
  //
  const apiUser = {
    id: 1,
    userProfileImg: "http://dummyimage.com/153x100.png/ff4444/ffffff",
  };

  return (
    <div>
      <img src={apiUser.userProfileImg} alt="" />
      <span>{userId}</span>
      <br />
      <span>{content}</span>

      {/* 여기서 만약 댓글이 내 댓글 이라면 삭제 버튼 들어가야함. */}
      {/* if 로그인id === id . 삭제버튼 보여짐. */}
      {/*  */}
    </div>
  );
};

export default CommentItem;
