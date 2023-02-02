import { useParams, useNavigate } from "react-router-dom";
const StorageDownload = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  return (
    <div>
      스토리지 다운로드
      <div>
        <button>사진저장</button>
        <button>영상저장</button>
        <button>우편저장</button>
      </div>
      <div>
        <button onClick={() => navigate(`/storage/${id}/share`)}>
          피드에 공유하기
        </button>
      </div>
    </div>
  );
};

export default StorageDownload;
