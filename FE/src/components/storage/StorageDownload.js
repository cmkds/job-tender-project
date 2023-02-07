import * as React from "react";
import Box from "@mui/material/Box";
import Fab from "@mui/material/Fab";
import PhotoIcon from "@mui/icons-material/Photo";
import VideocamIcon from "@mui/icons-material/Videocam";
import IosShareIcon from "@mui/icons-material/IosShare";

import { useParams, useNavigate } from "react-router-dom";

const StorageDownload = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  return (
    <div>
      <div style={{ height: "30px" }}></div>
      <Fab>
        <PhotoIcon />
      </Fab>
      <Fab>
        <VideocamIcon />
      </Fab>
      <Fab>
        <IosShareIcon />
      </Fab>
    </div>
  );
};

export default StorageDownload;
{
  /* <div>
  <button>사진저장</button>
  <button>영상저장</button>
  <button>우편저장</button>
</div>
<div>
  <button onClick={() => navigate(`/storage/${id}/share`)}>
    피드에 공유하기
  </button>
</div> */
}
