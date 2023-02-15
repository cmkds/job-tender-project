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
  const [storage, setStorage] = useState({});

  useEffect(() => {
    axios
      .get(`/api/mypage/post-new/store/${params.id}`)
      .then(function (response) {
        console.log(response.data);
        setStorage(response.data);
      });
  }, []);

  // console.log(storage);

  //개별 포스트 추출
  // const targetStorageItem = storageList.find(
  //   (it) => parseInt(it.id) === parseInt(id)
  // );

  return (
    <div>
      <h2
        style={{
          display: "flex",
          height: "100%",
          paddingLeft: "5%",
          fontFamily: "GangwonEduAll",
        }}
      >
        <div>{storage.machineDataCreateTime}</div>
        <div style={{ paddingLeft: "5%" }}>{storage.machineLocationSeq}</div>
      </h2>
      <div>
        <img
          // style={"width: 10%;"}
          src={`${s3}${storage.post}`}
          alt="사진이 없습니다."
          style={{ width: "100%" }}
        />
      </div>

      <div>
        <Routes>
          {/* 저장용 */}
          <Route path="/" element={<StorageDownload />} />
          {/* 공유용 */}
          <Route path="/share" element={<StorageShare />} />
        </Routes>
      </div>
    </div>
  );
};

export default StorageId;
