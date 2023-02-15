import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import axios from "axios";

import FeedList from "../../components/feed/FeedList";
import MainNavBar from "../../components/main/MainNavBar";
import NewHotButton from "../../components/main/NewHotButton";

const MainAll = () => {
  const params = useParams();

  const [feedsData, setFeedsData] = useState([]);

  useEffect(() => {
    if (params.locationId === "0") {
      axios.get(`/api/main/${params.state}`).then(function (response) {
        setFeedsData(response.data);
      });
    } else {
      axios
        .get(`/api/main/${params.state}/${params.locationId}`)
        .then(function (response) {
          setFeedsData(response.data);
        });
    }
  }, [params]);

  return (
    <div>
      <MainNavBar></MainNavBar>
      <div style={{ paddingTop: "70px" }}></div>
      <NewHotButton></NewHotButton>
      <FeedList feedsData={feedsData} />
    </div>
  );
};

export default MainAll;
