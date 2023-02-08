import FeedList from "../../components/feed/FeedList";
import axios from "axios";
import { useState, useEffect } from "react";
import MainNavBar from "../../components/main/MainNavBar";
import NewHotButton from "../../components/main/NewHotButton";

const MainAllNew = () => {
  const [feedsData, setFeedsData] = useState([]);

  useEffect(() => {
    axios.get(`/api/main/new`).then(function (response) {
      // console.log(response.data);
      setFeedsData(response.data);
    });
  }, []);

  return (
    <div style={{ paddingTop: "70px" }}>
      <MainNavBar></MainNavBar>
      <NewHotButton></NewHotButton>
      <FeedList feedsData={feedsData} />
    </div>
  );
};

export default MainAllNew;
