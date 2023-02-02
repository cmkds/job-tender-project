import React, { useState } from "react";

//
import FeedList from "../components/feed/FeedList";
// api 1
// 로그인한 사람의 팔로워 목록을 겟요청으로 받고
// api 2
// 팔로워 목록 id 값으로 다시 겟요청을 보내
// 해당 사람의 게시글 목록 프로필을 받는다.
// api 3 게시글 목록으로 게시글 상세 정보를 받는다.
// 시간 순서로 정렬 한후. state에 담겨진 프로필 이미지, 게시물 정보 등을 이용하여
// 화면에 보여준다.

//@@@일단 더미데이터로
// 2번 더미 데이터 데이터 만들기

export const FeedStateContext = React.createContext();

const api2 = [
  {
    id: 1,
    profile_img: "https://robohash.org/quirepellatad.png?size=50x50&set=set1",
    nickname: "Seung GD",
    feeds: [1],
  },
  {
    id: 2,
    profile_img:
      "https://robohash.org/officiasintdelectus.png?size=50x50&set=set1",
    nickname: "Ji Sun",
    feeds: [2, 6],
  },
  {
    id: 3,
    profile_img:
      "https://robohash.org/mollitiaquosdelectus.png?size=50x50&set=set1",
    nickname: "Ho Sung",
    feeds: [3, 8],
  },
  {
    id: 4,
    profile_img: "https://robohash.org/harumestqui.png?size=50x50&set=set1",
    nickname: "Jun Hyuk",
    feeds: [7],
  },
  {
    id: 5,
    profile_img:
      "https://robohash.org/facilisnecessitatibusipsam.png?size=50x50&set=set1",
    nickname: "Dongsu",
    feeds: [],
  },
];
/// api3
const api3 = [
  {
    id: 1, //피드 아이디
    context: {
      userId: 1, //유저 아이디
      post_image:
        "https://i.postimg.cc/wjsCsRCD/Kakao-Talk-20230131-090510363.jpg", //포스트 이미지

      content: "피드 게시물이다.",
      likes: [1, 2, 3, 4, 5],
      comments: {
        data: [
          { commentId: 1, userId: 1, content: "Im GD" },
          { commentId: 2, userId: 1, content: "Im Hot" },
          { commentId: 3, userId: 1, content: "Im Cool" },
          { commentId: 4, userId: 1, content: "Im Sexy" },
          { commentId: 5, userId: 1, content: "Im Handsome" },
        ],
      },
    },
  },
  {
    id: 2, //피드 아이디
    context: {
      userId: 1, //유저 아이디
      post_image:
        "https://i.postimg.cc/wjsCsRCD/Kakao-Talk-20230131-090510363.jpg", //포스트 이미지
      content: "I'm GD.",
      likes: [1, 2, 3, 4, 5],
      comments: {
        data: [{ commentId: 6, userId: 1, content: "Im G-Dragon" }],
      },
    },
  },
];

// 시간이 여유가 되면 무한 스크롤 구현
// 아니면 상위 20개 목록.

const Feed = () => {
  const [followersData, setFollowersData] = useState(api2);
  // console.log(followersData);
  const [feedsData, setFeedsData] = useState(api3);
  // console.log(feedsData);

  return (
    <div>
      <h2>Feed Page</h2>

      <FeedStateContext.Provider value={[followersData, feedsData]}>
        <FeedList />
      </FeedStateContext.Provider>
    </div>
  );
};

export default Feed;
