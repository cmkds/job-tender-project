import FeedList from "../../components/feed/FeedList";
import axios from "axios";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

const MainLocationNew = () => {
  const [feedsData, setFeedsData] = useState([]);

  const params = useParams();

  // console.log(params);

  useEffect(() => {
    axios.get(`/api/main/new/${params.locationId}`).then(function (response) {
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

export default MainLocationNew;
