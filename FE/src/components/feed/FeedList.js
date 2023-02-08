import { useContext } from "react";
import FeedItem from "./FeedItem";
// import { FeedStateContext } from "../../pages/Feed";

const FeedList = (feedsData) => {
  // const feed = useContext(FeedStateContext)[0];
  const feedList = feedsData.feedsData;
  // console.log(feedList);

  // console.log(a);
  // const feedList = [];
  // const makeFeedList = feed.map((it) =>
  //   it.feeds.map((that) => feedList.push({ id: that }))
  // );

  //비교용 함수.
  // 나중에 피드 생성시간생기면 생성시간으로 정렬하기
  // const compare = (a, b) => {
  //   return parseInt(b.id) - parseInt(a.id);
  // };

  // const feedArr = feedList.sort(compare);

  // console.log(feedList);
  // console.log(feedArr);
  return (
    <div style={{ paddingTop: "70px" }}>
      {feedList.map((it) => (
        <FeedItem key={it.feedSeq} {...it} />
      ))}
    </div>
  );
};

export default FeedList;
