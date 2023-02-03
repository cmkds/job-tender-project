import Profile from "../Profile";
import { useNavigate } from "react-router-dom";

const MainFeedItem = (feed) => {
  console.log(feed);

  const navigate = useNavigate();

  return (
    <div>
      <p>{feed.id}번 째 피드</p>
      {/* 유저 아이디 넣기 */}
      <Profile />
      {/* array는 [인덱스] 객체는 .key이름 */}
      <img src={feed.context.post_image} />
      {/* 댓글 넣기 요건 네비게이트로 감.*/}
      <p>{feed.context.content}</p>
      <button>좋아요 버튼</button>
      <button onClick={() => navigate(`/comment/${feed.id}`)}>댓글 보기</button>
      <span>{feed.context.likes.length}개의 좋아요 </span>
      <span>{feed.context.comments.data.length}개의 댓글</span>
      <hr />
    </div>
  );
};

export default MainFeedItem;
