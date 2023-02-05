// 댓글 컴포넌트
// 해당 댓글 id를 가져와서
// 해당 유저의 목록과 글의 내용을 가져온다
import CommentItem from "./CommentItem";
// api로 통신
// 해당 url을 가져온다.
//댓글 정보들이 올 것이다.
const dummyData = [
  {
    id: 1,
    userId: 1,
    // 작성시간도 올것인데 작성시간으로 정렬해준다.
    // userProfileImg: "http://dummyimage.com/153x100.png/ff4444/ffffff",
    content: "안녕 나는 댓글 1이야",
  },
  {
    id: 2,
    userId: 1,
    // userProfileImg: "http://dummyimage.com/184x100.png/cc0000/ffffff",
    content: "안녕 나는 댓글 2이야",
  },
];

const CommentList = () => {
  console.log(dummyData);
  return (
    <div>
      <p>asas</p>
      {dummyData.map((it) => (
        <CommentItem key={it.id} {...it} />
      ))}
    </div>
  );
};

export default CommentList;
