import { useContext } from "react";
import { useNavigate } from "react-router-dom";

import { StorageStateContext } from "../../pages/Storage";

import ImageList from "@mui/material/ImageList";
import ImageListItem from "@mui/material/ImageListItem";

const StorageList = () => {
  const storageList = useContext(StorageStateContext);
  // console.log(storageList);
  const navigate = useNavigate();
  const s3 = "https://team-a502-bucket.s3.ap-northeast-2.amazonaws.com/";
  // const [storageList, setStorageList] = useState([])

  // useEffect(() => {
  //   axios.get(`/api/mypage/post-new/${loginUser}`).then(function (response) {
  //     setStorageData(response.data);
  //   });
  // }, []);

  return (
    <div style={{ width: "100%", height: "100%" }}>
      <ImageList>
        {storageList.map((it) => (
          <ImageListItem key={it.storeSeq} {...it}>
            <img
              src={`${s3}${it.photo}`}
              // srcSet={`${it.photo_url}?w=248&fit=crop&auto=format&dpr=2 2x`}
              alt={it.id}
              loading="lazy"
              onClick={() => navigate(`/storage/${it.storeSeq}`)}
            />
            <div
              style={{
                display: "flex",
                backgroundColor: "#C1BCBC",
                color: "white",
                height: "100%",
                padding: "3% 5% 3%",
                justifyContent: "space-between",
                fontFamily: "GangwonEduAll",
              }}
            >
              {/* <div>{it.location}</div> */}
              <div>{it.machineDataCreateTime}</div>
            </div>
          </ImageListItem>
        ))}
      </ImageList>
    </div>
  );
};

export default StorageList;
