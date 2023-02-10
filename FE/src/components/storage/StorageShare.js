// /storage/:card-no/share
// 엽서 게시물 작성해서 피드에 공유하는 페이지.
import React, { useState } from "react";

import TextField from "@mui/material/TextField";
import { makeStyles } from "@material-ui/core/styles";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";

const useStyles = makeStyles((theme) => ({
  textField: {
    width: 370,
  },
  characterCount: {
    marginTop: theme.spacing(1),
    color: "gray",
    marginLeft: "78%",
    fontFamily: "GangwonEduAll",
  },
}));

const StorageShare = () => {
  const classes = useStyles();
  const [value, setValue] = useState("");
  return (
    <div>
      <div style={{ paddingTop: "5%", paddingLeft: "5%", fontSize: 24 }}>
        내용
      </div>
      <div style={{ display: "flex", margin: "3%" }}>
        <TextField
          id="outlined-multiline-flexible"
          value={value}
          onChange={(event) => setValue(event.target.value)}
          multiline
          rows={6}
          className={classes.textField}
          placeholder="내용을 입력하세요"
          inputProps={{
            style: { fontFamily: "GangwonEduAll" },
          }}
        />
      </div>
      <div className={classes.characterCount}>{value.length} / 1,000</div>
      {/* <textarea
        name=""
        id=""
        cols="30"
        rows="10"
        placeholder="할 말을 입력하세요"
      ></textarea> */}
      <div style={{ marginTop: "5%" }}>
        <Button
          style={{
            display: "flex",
            margin: "auto",
            width: "90%",
            height: "60px",
            backgroundColor: "#FFB9B9",
          }}
          variant="contained"
        >
          <SendIcon />
        </Button>
      </div>
    </div>
  );
};

export default StorageShare;

// import React, { useState } from "react";
// import { makeStyles } from "@material-ui/core/styles";
// import TextField from "@material-ui/core/TextField";

// const useStyles = makeStyles((theme) => ({
//   textField: {
//     width: 400,
//   },
//   characterCount: {
//     marginTop: theme.spacing(1),
//     color: "gray",
//     marginLeft: "70%",
//   },
// }));

// export default function CharacterCountTextField() {
//   const classes = useStyles();
//   const [value, setValue] = useState("");

//   return (
//     <div>
//       <TextField
//         className={classes.textField}
//         label="내용"
//         variant="outlined"
//         value={value}
//         onChange={(event) => setValue(event.target.value)}
//       />
//       <div className={classes.characterCount}>{value.length} / 1,000</div>
//     </div>
//   );
// }
