import { useParams, Routes, Route } from "react-router-dom";
import { useContext } from "react";
import { StorageStateContext } from "../../pages/Storage";
import StorageDownload from "./StorageDownload";
import StorageShare from "./StorageShare";

const StorageId = () => {
  // const navigate = useNavigate();

  const storageList = useContext(StorageStateContext);
  // 파일의 id와 해당 id에 소유 id가 다르면
  // 홈으로 돌려보냄.
  const { id } = useParams();

  //개별 포스트 추출
  const targetStorageItem = storageList.find(
    (it) => parseInt(it.id) === parseInt(id)
  );

  // console.log(targetStorageItem);

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
        <div>{targetStorageItem["date"]}</div>
        <div style={{ paddingLeft: "5%" }}>{targetStorageItem["location"]}</div>
      </h2>
      <div>
        <img
          // style={"width: 10%;"}
          src={`${targetStorageItem["photo_url"]}`}
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
