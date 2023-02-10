import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

import axios from "axios";

import ImageList from "@mui/material/ImageList";
import ImageListItem from "@mui/material/ImageListItem";

const UserFeedList = () => {
  const params = useParams();
  const navigate = useNavigate();

  // 유저 피드 가져오기
  useEffect(() => {
    axios.get(`/api/main/${params.user}`).then(function (response) {
      setUserFeedData(response.data);
    });
  }, []);

  const [userFeedData, setUserFeedData] = useState([]);

  return (
    <>
      <ImageList>
        {userFeedData.map((it) => (
          <ImageListItem
            key={it.feedSeq}
            // {...it}
            onClick={() =>
              navigate(`/user/${params.user}/${it.feedSeq}`, {
                state: { ...it },
              })
            }
          >
            <img className="post_wrapper" src={it.post} alt={it.post} />
          </ImageListItem>
        ))}
      </ImageList>
    </>
  );
};

export default UserFeedList;
