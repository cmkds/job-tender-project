import Profile from "../Profile";
import { Box, TextField } from "@mui/material";
import Grid from "@mui/material/Grid";
import Modal from "@mui/material/Modal";
import Button from "@mui/material/Button";
import { useState, useRef } from "react";
import CheckIcon from "@mui/icons-material/Check";
import ClearIcon from "@mui/icons-material/Clear";

const style = {
  position: "absolute",

  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "90%",
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  pt: 2,
  px: 2,
  pb: 2,
};

const CommentItem = ({
  commentSeq,
  content,
  createTime,
  modifyTime,
  feedSeq,
  memberSeq,
}) => {
  const [open, setOpen] = useState(false);

  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };
  const timeoutRef = useRef(null);

  const handleTouchStart = () => {
    timeoutRef.current = setTimeout(() => {
      handleOpen();
    }, 500);
  };

  const handleTouchEnd = () => {
    clearTimeout(timeoutRef.current);
  };

  return (
    <div>
      <div>
        <Modal open={open} onClose={handleClose}>
          {/* 아래 코드가 모달창 코드 */}
          <Box sx={{ ...style }}>
            <TextField
              variant="outlined"
              defaultValue={content}
              fullWidth
              multiline
            />
            {/* 수정 버튼 */}
            <Button
              variant="contained"
              style={{ width: "50%", marginTop: "5%" }}
              color="success"
            >
              {/* <CheckIcon /> */}
              수정
            </Button>
            {/* 삭제 버튼 */}
            <Button
              variant="contained"
              style={{ width: "50%", marginTop: "5%" }}
              color="error"
            >
              {/* <ClearIcon /> */}
              삭제
            </Button>
          </Box>
        </Modal>
      </div>
      <Grid container>
        <Grid item xs={5}>
          <div>
            <Profile id={memberSeq} />
          </div>
        </Grid>
        <Grid item xs={7}>
          {" "}
          <Box
            style={{
              marginRight: "5%",
              marginTop: "10%",
              width: "170px",
              display: "block",
            }}
            sx={{
              wordBreak: "break-all",
            }}
          >
            <div onTouchStart={handleTouchStart} onTouchEnd={handleTouchEnd}>
              {content}
            </div>
          </Box>
        </Grid>
      </Grid>

      {/* <div>{memberSeq} 번 회원</div> */}
      {/* <img src={apiUser.userProfileImg} alt="" /> */}
      {/* <br /> */}

      {/* 여기서 만약 댓글이 내 댓글 이라면 삭제 버튼 들어가야함. */}
      {/* if 로그인id === id . 삭제버튼 보여짐. */}
      {/*  */}
    </div>
  );
};

export default CommentItem;
