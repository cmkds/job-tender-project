import FeedList from "../../components/feed/FeedList";
import axios from "axios";
import { useState, useEffect } from "react";

const MainAllHot = () => {
  const [feedsData, setFeedsData] = useState([]);

  useEffect(() => {
    axios.get(`/api/main/hot`).then(function (response) {
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

export default MainAllHot;
