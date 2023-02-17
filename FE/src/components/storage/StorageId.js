import { useParams, Routes, Route } from "react-router-dom";
import { useEffect, useState } from "react";

import axios from "axios";

import StorageDownload from "./StorageDownload";
import StorageShare from "./StorageShare";

const StorageId = () => {
  // const navigate = useNavigate();

  // 파일의 id와 해당 id에 소유 id가 다르면
  // 홈으로 돌려보냄.
  const loginUser = sessionStorage.getItem("loginUser");
  const s3 = "https://team-a502-bucket.s3.ap-northeast-2.amazonaws.com/";
  const params = useParams();
  const [data, setData] = useState({});

  useEffect(() => {
    axios
      .get(`/api/mypage/post-new/store/${params.id}`)
      .then(function (response) {
        console.log(response.data);
        setData(response.data);
      });
  }, []);

  const location = (sep) => {
    if (sep == 1) {
      return "서울";
    } else if (sep == 2) {
      return "강릉";
    } else if (sep == 3) {
      return "경주";
    } else if (sep == 4) {
      return "전주";
    } else if (sep == 5) {
      return "부산";
    }
  };

  // console.log(storage);

  //개별 포스트 추출
  // const targetStorageItem = storageList.find(
  //   (it) => parseInt(it.id) === parseInt(id)
  // );

  return (
    <div>
      <div
        style={{
          display: "flex",
          height: "100%",
          paddingLeft: "5%",
          fontFamily: "GangwonEduAll",
          fontSize: "25px",
          marginTop:"21%"
        }}
      >
        <div>{data.machineDataCreateTime}</div>
        <div style={{ paddingLeft: "5%" }}>
          [{location(data.machineLocationSeq)}]
        </div>
      </div>
      <div>
        <a href={`${s3}${data.post}`} download>
          <img
            // style={"width: 10%;"}
            src={`${s3}${data.post}`}
            alt="사진이 없습니다."
            style={{ width: "100%" }}
          />
        </a>
      </div>

      <div>
        <Routes>
          {/* 저장용 */}
          <Route path="/" element={<StorageDownload />} />
          {/* 공유용 */}
          <Route path="/share" element={<StorageShare data={data} />} />
        </Routes>
      </div>
    </div>
  );
};

export default StorageId;
