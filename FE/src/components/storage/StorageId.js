import { useParams, Routes, Route, useNavigate } from "react-router-dom";
import { useContext } from "react";
import { StorageStateContext } from "../Storage";
import StorageDownload from "./StorageDownload";
import StorageShare from "./StorageShare";

const StorageId = () => {
  const navigate = useNavigate();

  const storageList = useContext(StorageStateContext);
  // 파일의 id와 해당 id에 소유 id가 다르면
  // 홈으로 돌려보냄.
  const { id } = useParams();

  //개별 포스트 추출
  const targetStorageItem = storageList.find(
    (it) => parseInt(it.id) === parseInt(id)
  );

  console.log(targetStorageItem);

  return (
    <div>
      <div>
        <img
          // style={"width: 10%;"}
          src={`${targetStorageItem["post_url"]}`}
          alt="사진이 없습니다."
        />
        <img src={`${targetStorageItem["photo_url"]}`} alt="사진이 없습니다." />
        <img src={`${targetStorageItem["video_url"]}`} alt="사진이 없습니다." />
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
