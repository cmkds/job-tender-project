import { useNavigate } from "react-router-dom";

const StorageItem = ({
  id,
  date,
  location,
  photo_url,
  video_url,
  post_url,
}) => {
  const navigate = useNavigate();
  // console.log(photo_url);
  return (
    // <div>
    //   {/* 날짜로 바뀜. */}
    //   <p>{`${date} ${location}`}</p>
    //   <img
    //     src={`${photo_url}`}
    //     alt="사진이 없습니다."
    //     onClick={() => navigate(`/storage/${id}`)}
    //   />
    // </div>
  // );
};

export default StorageItem;
