import { useNavigate } from "react-router-dom";

const StorageItem = ({ id, photo_url, video_url, post_url }) => {
  const navigate = useNavigate();
  // console.log(photo_url);
  return (
    <div className="storage_wrapper">
      {/* 날짜로 바뀜. */}
      <p>{`${id}번 째 포스트`}</p>
      <img
        src={`${photo_url}`}
        alt="사진이 없습니다."
        onClick={() => navigate(`/storage/${id}`)}
      />
    </div>
  );
};

export default StorageItem;
