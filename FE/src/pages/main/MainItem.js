import { useContext } from "react";
import { MainStateContext } from "../Main";

const MainItem = (feedId) => {
  const followersData = useContext(MainStateContext)[0];
  const feedsData = useContext(MainStateContext)[1];
  // console.log(feedId);
  // console.log(followersData);
  // console.log(feedId.id);
  // console.log(feedsData[0].id);
  // console.log(feedsData[1].id);

  const findFeed = () => {
    let temp = feedsData.filter((it) => Object.values(feedId).includes(it.id));
    return temp;
  };
  const check = findFeed();

  console.log(findFeed());

  if (check.length) {
    return (
      <div>
        <img src={check[0].context.post_image} />
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

export default MainItem;
