// 댓글 컴포넌트
// 해당 댓글 id를 가져와서
// 해당 유저의 목록과 글의 내용을 가져온다

// api로 통신
const dummyData = [
  {
    id: 1,
    userId: 1,
    userProfileImg: "http://dummyimage.com/153x100.png/ff4444/ffffff",
    content: "안녕 나는 댓글 1이야",
  },
  {
    id: 2,
    userId: 1,
    userProfileImg: "http://dummyimage.com/184x100.png/cc0000/ffffff",
    content: "안녕 나는 댓글 2이야",
  },
];

const Comment = () => {
  <div>
    {dummyData.map((it) => (
      <CommentsItem key={it.id} {...it} />
    ))}
  </div>;
  return;
};

export default Comment;
