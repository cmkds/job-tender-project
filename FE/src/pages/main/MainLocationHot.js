import FeedList from "../../components/feed/FeedList";
import axios from "axios";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
const MainLocationHot = () => {
  const [feedsData, setFeedsData] = useState([]);

  const params = useParams();

  console.log(params);

  // 아직 api는 제대로 작동안함. 버튼 별로 파라미터 보내주는 거 구현

  useEffect(() => {
    axios.get(`/api/main/hot/${params.locationId}`).then(function (response) {
      console.log(response.data);
      setFeedsData(response.data);
    });
  }, []);

  return (
    <div>
      <FeedList feedsData={feedsData} />
    </div>
  );
};

export default MainLocationHot;
