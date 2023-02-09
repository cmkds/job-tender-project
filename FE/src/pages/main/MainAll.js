import FeedList from "../../components/feed/FeedList";
import axios from "axios";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import MainNavBar from "../../components/main/MainNavBar";
import NewHotButton from "../../components/main/NewHotButton";

const MainAll = () => {
  const [feedsData, setFeedsData] = useState([]);
  const params = useParams();
  console.log("메인 올");
  console.log(params);

  useEffect(() => {
    if (params.locationId === "0") {
      axios.get(`/api/main/${params.state}`).then(function (response) {
        console.log("올로보냈다");
        console.log(response);
        setFeedsData(response.data);
      });
    } else {
      axios
        .get(`/api/main/${params.state}/${params.locationId}`)
        .then(function (response) {
          console.log("로케이션으로 보냈다");
          console.log(response);
          setFeedsData(response.data);
        });
    }
  }, [params]);

  // const apiAll = () =>
  //   axios.get(`/api/main/${params.state}`).then(function (response) {
  //     console.log(response.data);
  //     setFeedsData(response.data);
  //   });

  // // 뉴핫에 따른 분류  // 전국, 지역별로 나눠주면됨
  // if (params.locationId === "0") {
  //   apiAll();
  // useEffect(() => {
  //   axios.get(`/api/main/${params.state}`).then(function (response) {
  //     console.log(response.data);
  //     setFeedsData(response.data);
  //   });
  // }, []);
  return (
    <div style={{ paddingTop: "70px" }}>
      <MainNavBar></MainNavBar>
      <NewHotButton></NewHotButton>
      <FeedList feedsData={feedsData} />
    </div>
  );
};
// 지역에 따른 분류  // 전국, 지역인 경우 나눠주면됨

// console.log("메인올핫");
// useEffect(() => {
//   axios.get(`/api/main/hot`).then(function (response) {
//     // console.log(response.data);
//     setFeedsData(response.data);
//   });
// }, [params]);

export default MainAll;
