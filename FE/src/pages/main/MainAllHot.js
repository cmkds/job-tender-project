import FeedList from "../../components/feed/FeedList";
import axios from "axios";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

const MainAllHot = () => {
  const [feedsData, setFeedsData] = useState([]);
  const params = useParams();

  console.log(params);
  useEffect(() => {
    axios.get(`/api/main/hot`).then(function (response) {
      // console.log(response.data);
      setFeedsData(response.data);
    });
  }, []);

  return (
    <div>
      <FeedList feedsData={feedsData} />
    </div>
  );
};

export default MainAllHot;
