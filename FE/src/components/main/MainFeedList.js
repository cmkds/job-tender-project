///안쓰임

import { useContext } from "react";
import MainFeedItem from "./MainFeedItem";
import { MainStateContext } from "../../pages/Main";

const MainFeedList = () => {
  const mainFeedList = useContext(MainStateContext);
  return (
    <div style={{ paddingTop: "28vw" }}>
      {mainFeedList.map((it) => (
        <MainFeedItem key={it.id} {...it} />
      ))}
    </div>
  );
};

export default MainFeedList;
