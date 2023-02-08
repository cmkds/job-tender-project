import * as React from "react";
import Fab from "@mui/material/Fab";
import PhotoIcon from "@mui/icons-material/Photo";
import VideocamIcon from "@mui/icons-material/Videocam";
import IosShareIcon from "@mui/icons-material/IosShare";
import IconButton from "@material-ui/core/IconButton";
import { useParams, useNavigate } from "react-router-dom";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  root: {
    display: "flex",
    justifyContent: "center",
  },
  buttonContainer: {
    display: "flex",
    justifyContent: "space-evenly",
    width: "100%",
    paddingTop: "10%",
  },
  largeIcon: {
    fontSize: "4rem !important",
    color: "#FF9999",
  },
}));

const StorageDownload = () => {
  const classes = useStyles();

  const navigate = useNavigate();
  const { id } = useParams();
  return (
    <div className={classes.root}>
      <div className={classes.buttonContainer}>
        <IconButton>
          <PhotoIcon className={classes.largeIcon} />
        </IconButton>
        <IconButton>
          <VideocamIcon className={classes.largeIcon} />
        </IconButton>
        <IconButton
          className={classes.largeIcon}
          onClick={() => navigate(`/storage/${id}/share`)}
        >
          <IosShareIcon className={classes.largeIcon} />
        </IconButton>
      </div>
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
