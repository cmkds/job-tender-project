import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { FeedStateContext } from "../../pages/Feed";
import Profile from "../Profile";

const FeedItem = (feedId) => {
  const followersData = useContext(FeedStateContext)[0];
  const feedsData = useContext(FeedStateContext)[1];
  // console.log(feedId);
  // console.log(followersData);
  // console.log(feedId.id);
  // console.log(feedsData[0].id);
  // console.log(feedsData[1].id);
  const navigate = useNavigate();

  const findFeed = () => {
    let temp = feedsData.filter((it) => Object.values(feedId).includes(it.id));
    return temp;
  };
  const check = findFeed();

  console.log(findFeed());

  if (check.length) {
    return (
      <div>
        <p>{check[0].id}번 째 피드</p>
        {/* 유저 아이디 넣기 */}
        <Profile />
        {/* array는 [인덱스] 객체는 .key이름 */}
        <img src={check[0].context.post_image} />
        {/* 댓글 넣기 요건 네비게이트로 감.*/}
        <p>{check[0].context.content}</p>
        <button>좋아요 버튼</button>
        <button onClick={() => navigate(`/comment/${check[0].id}`)}>
          댓글 보기
        </button>
        <span>{check[0].context.likes.length}개의 좋아요 </span>
        <span>{check[0].context.comments.data.length}개의 댓글</span>
        <hr />
      </div>
    );
  }

  // if (feedsData.map((it) => parseInt(it.id) === parseInt(feedId.id))) {
  //   let temp = feedId.id;
  //   return (
  //     <div>
  //       제발나와라
  //       {temp}
  //     </div>
  //   );
  // } else {
  //   return null;
  // }
  // return <div>1</div>;

  // return (
  //   <div>
  //     {feedId.id}
  //     {if}
  //     {feedsData.map((it) => (
  //       // <profile/>
  //       <img src={it.context["post_image"]} alt="" />
  //     ))}
  //   </div>
  // );
};

export default FeedItem;
