import FeedItem from "./FeedItem";

const FeedList = (feedsData) => {
  const feedList = feedsData.feedsData;

  return (
    <div style={{ paddingTop: "70px" }}>
      {feedList.map((it) => (
        <FeedItem key={it.feedSeq} {...it} />
      ))}
    </div>
  );
};

export default FeedList;
