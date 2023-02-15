// // import * as React from "react";
// import { useState, useParams, useNavigate } from "react-router-dom";
// import { useEffect } from "react";

// import axios from "axios";

// // import Fab from "@mui/material/Fab";
// import PhotoIcon from "@mui/icons-material/Photo";
// import VideocamIcon from "@mui/icons-material/Videocam";
// import IosShareIcon from "@mui/icons-material/IosShare";
// import IconButton from "@material-ui/core/IconButton";
// import { makeStyles } from "@material-ui/core/styles";

// const useStyles = makeStyles((theme) => ({
//   root: {
//     display: "flex",
//     justifyContent: "center",
//   },
//   buttonContainer: {
//     display: "flex",
//     justifyContent: "space-evenly",
//     width: "100%",
//     paddingTop: "10%",
//   },
//   largeIcon: {
//     fontSize: "4rem !important",
//     color: "#FF9999",
//   },
// }));

// const QR = () => {
//   const navigate = useNavigate();
//   const classes = useStyles();

//   const params = useParams();
//   const [state, setState] = useState({
//     machine_location_seq: "",
//     member_seq: "",
//     photo: "",
//     post: "",
//     video: "",
//     voice: "",
//   });

//   useEffect(() => {
//     axios
//       .get(`/api/machine/data/${params.machineDataSeq}`)
//       .then(function (response) {

//         axios(`/api/machine/loc/`)

//         setState({
//           ...state,
//           machine_location_seq: {response.data.},
//           member_seq: "",
//           photo: "",
//           post: "",
//           video: "",
//           voice: "",
//         })

//       });
//   });

//   return (
//     <div>
//       <h2
//         style={{
//           display: "flex",
//           height: "100%",
//           paddingLeft: "5%",
//           fontFamily: "GangwonEduAll",
//         }}
//       >
//         <div>{targetStorageItem["date"]}</div>
//         <div style={{ paddingLeft: "5%" }}>{targetStorageItem["location"]}</div>
//       </h2>
//       <div>
//         <img
//           // style={"width: 10%;"}
//           src={`${targetStorageItem["photo_url"]}`}
//           alt="사진이 없습니다."
//           style={{ width: "100%" }}
//         />
//       </div>
//       // @@@@@@@@@@@@@@@@@@@@
//       <div className={classes.root}>
//         <div className={classes.buttonContainer}>
//           <IconButton>
//             <PhotoIcon className={classes.largeIcon} />
//           </IconButton>
//           <IconButton>
//             <VideocamIcon className={classes.largeIcon} />
//           </IconButton>
//           <IconButton
//             className={classes.largeIcon}
//             onClick={() => navigate(`/storage/${machineDataSeq}/share`)}
//           >
//             <IosShareIcon className={classes.largeIcon} />
//           </IconButton>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default QR;
