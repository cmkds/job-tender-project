import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
//
import TopBar from "../components/TopBar";
import axios from "axios";
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

//  1. 로그인한 사람의 팔로워 목록 가져옴
//  2. 해당아이디 하나하나 피드 데이터 가져옴
//  3. 피드데이터
//
//

export const FeedStateContext = React.createContext();

const Feed = () => {
  const [feedsData, setFeedsData] = useState([]);
  // 로그인한 유저 아이디
  const userId = 1;
  const params = {
    memberSeq: userId,
  };
  useEffect(() => {
    axios.get(`/api/social/${userId}`, { params }).then(function (response) {
      // console.log(response.data);
      setFeedsData(response.data);
    });
  }, []);
  // console.log(feedsData.length);
  const navigate = useNavigate();
  return (
    <div>
      <TopBar />
      <h2>Feed Page</h2>

      <FeedStateContext.Provider value={feedsData}>
        {/* 여기 수정 봐야됨 */}
        {feedsData.length !== 0 ? (
          <FeedList feedsData={feedsData} />
        ) : (
          <div>
            <p>새로운 엽서가 없습니다.</p>
            <p>엽서를 올리거나 지역별 인기 엽서를 확인하세요.</p>
            <br />
            <span
              onClick={() => {
                navigate("/storage");
              }}
            >
              엽서 올리기
            </span>
            <span
              onClick={() => {
                navigate("/main");
              }}
            >
              지역별 인기엽서 확인하기
            </span>
          </div>
        )}
      </FeedStateContext.Provider>
    </div>
  );
};

export default Feed;
