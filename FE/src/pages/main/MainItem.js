import { useContext } from "react";
import { MainStateContext } from "../Main";

const MainItem = (feedId) => {
  const followersData = useContext(MainStateContext)[0];
  const feedsData = useContext(MainStateContext)[1];
  console.log(feedId);
  console.log(followersData);
  console.log(feedsData);

  const findFeed = () => {};

  return (
    <div>
      {feedId.id}
      {feedsData.map((it) => (
        <p></p>
      ))}
    </div>
  );
};

export default MainItem;
