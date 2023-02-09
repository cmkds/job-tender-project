import FeedList from "../../components/feed/FeedList";
import axios from "axios";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import MainNavBar from "../../components/main/MainNavBar";
import NewHotButton from "../../components/main/NewHotButton";

const MainAllHot = () => {
  const [feedsData, setFeedsData] = useState([]);
  const params = useParams();
  console.log(params);
  console.log("메인올핫");
  useEffect(() => {
    axios.get(`/api/main/hot`).then(function (response) {
      // console.log(response.data);
      setFeedsData(response.data);
    });
  }, [params]);

  return (
    <div style={{ paddingTop: "70px" }}>
      <MainNavBar></MainNavBar>
      <NewHotButton></NewHotButton>
      <FeedList feedsData={feedsData} />
    </div>
  );
};

export default MainAllHot;
