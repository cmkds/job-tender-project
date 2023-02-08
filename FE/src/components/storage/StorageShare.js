// /storage/:card-no/share
// 엽서 게시물 작성해서 피드에 공유하는 페이지.
import TextField from "@mui/material/TextField";
import { makeStyles } from "@material-ui/core/styles";
import React, { useState } from "react";

const useStyles = makeStyles((theme) => ({
  textField: {
    width: 400,
  },
  characterCount: {
    marginTop: theme.spacing(1),
    color: "gray",
    marginLeft: "70%",
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
      <TextField
        id="outlined-multiline-flexible"
        value={value}
        onChange={(event) => setValue(event.target.value)}
        multiline
        rows={8}
        maxRows={8}
        className={classes.textField}
        placeholder="내용을 입력하세요"
      />
      <div className={classes.characterCount}>{value.length} / 1,000</div>
      {/* <textarea
        name=""
        id=""
        cols="30"
        rows="10"
        placeholder="할 말을 입력하세요"
      ></textarea> */}
      <div>
        <button>올리기</button>
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
