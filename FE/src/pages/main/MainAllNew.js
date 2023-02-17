// import FeedList from "../../components/feed/FeedList";
// import axios from "axios";
// import { useState, useEffect } from "react";
// import { useParams } from "react-router-dom";
// import MainNavBar from "../../components/main/MainNavBar";
// import NewHotButton from "../../components/main/NewHotButton";

// const MainAllNew = () => {
//   const [feedsData, setFeedsData] = useState([]);
//   const params = useParams();
//   console.log("newnwenwenwenewnewnwenwenewnwen");

//   useEffect(() => {
//     axios.get(`/api/main/new`).then(function (response) {
//       // console.log(response.data);
//       setFeedsData(response.data);
//     });
//   }, [params]);

//   return (
//     <div>
//       <MainNavBar></MainNavBar>
//       <NewHotButton></NewHotButton>
//       <FeedList feedsData={feedsData} />
//     </div>
//   );
// };

// export default MainAllNew;
