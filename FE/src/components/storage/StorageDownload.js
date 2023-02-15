import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

import axios from "axios";

// import Fab from "@mui/material/Fab";
import PhotoIcon from "@mui/icons-material/Photo";
import VideocamIcon from "@mui/icons-material/Videocam";
import IosShareIcon from "@mui/icons-material/IosShare";
import IconButton from "@material-ui/core/IconButton";
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
  const navigate = useNavigate();
  const classes = useStyles();
  const [data, setData] = useState({});

  const params = useParams();
  const s3 = "https://team-a502-bucket.s3.ap-northeast-2.amazonaws.com/";
  console.log(params);
  useEffect(() => {
    axios
      .get(`/api/mypage/post-new/store/${params.id}`)
      .then(function (response) {
        setData(response.data);
      });
  }, []);
  return (
    <div>
      <div className={classes.root}>
        <div className={classes.buttonContainer}>
          <a href={`${s3}${data.photo}`} download>
            <IconButton>
              <PhotoIcon className={classes.largeIcon} />
            </IconButton>
          </a>
          {data.video ? (
            <a href={`${s3}${data.video}`} download>
              <IconButton>
                <VideocamIcon className={classes.largeIcon} />
              </IconButton>
            </a>
          ) : null}
          <IconButton
            className={classes.largeIcon}
            onClick={() => navigate(`/storage/${params.id}}/share`)}
          >
            <IosShareIcon className={classes.largeIcon} />
          </IconButton>
        </div>
      </div>
    </div>
  );
};

export default StorageDownload;
