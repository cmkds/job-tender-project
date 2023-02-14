import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import axios from "axios";

import TopBar from "../components/TopBar";
import FeedList from "../components/feed/FeedList";

const Feed = () => {
  const navigate = useNavigate();

  useEffect(() => {
    if (!sessionStorage.getItem("loginUser")) {
      navigate("/");
    }
  });

  const loginUser = sessionStorage.getItem("loginUser");
  const params = {
    memberSeq: loginUser,
  };
  const [feedsData, setFeedsData] = useState([]);

  // 로그인한 유저 아이디
  useEffect(() => {
    axios.get(`/api/social/${loginUser}`, { params }).then(function (response) {
      setFeedsData(response.data);
    });
  }, []);
  return (
    <div>
      <TopBar />

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
    </div>
  );
};

export default Feed;
