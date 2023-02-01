const CommentsItem = ({ id, userId, userProfileImg, content }) => {
  return (
    <div>
      <img src={userProfileImg} alt="" />
      <span>{userId}</span>
      <br />
      <span>{content}</span>
    </div>
  );
};

export default CommentsItem;
