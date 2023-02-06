import { useContext } from "react";
import MainFeedItem from "./MainFeedItem";
import { MainStateContext } from "../../pages/Main";

const MainFeedList = () => {
  const mainFeedList = useContext(MainStateContext);
  console.log(mainFeedList);
  return (
    <div>
      <p>메인 피드 리스트다</p>
      {mainFeedList.map((it) => (
        <MainFeedItem key={it.id} {...it} />
      ))}
    </div>
  );
};

export default MainFeedList;
