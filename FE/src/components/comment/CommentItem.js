import { useState, useRef, Fragment, useEffect } from "react";

import axios from "axios";
import qs from "qs";

import Profile from "../Profile";

import { Box, TextField } from "@mui/material";
import Grid from "@mui/material/Grid";
import Modal from "@mui/material/Modal";
import Button from "@mui/material/Button";

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
  change,
}) => {
  const [open, setOpen] = useState(false);
  function ChildModal() {
    const [open, setOpen] = useState(false);
    const handleOpen = () => {
      setOpen(true);
    };
    const handleClose = () => {
      setOpen(false);
    };
    
    return (
      <Fragment>
        <Button
          onClick={handleOpen}
          variant="contained"
          style={{ width: "50%", marginTop: "5%" }}
          color="error"
        >
          삭제하기
        </Button>
        <Modal
          hideBackdrop
          open={open}
          onClose={handleClose}
          aria-labelledby="child-modal-title"
          aria-describedby="child-modal-description"
        >
          <Box sx={{ ...style }}>
            <h3 id="child-modal-title">댓글을 삭제하시겠습니까?</h3>
            <div style={{ marginLeft: "50%" }}>
              <Button onClick={commentRemove} variant="contained" color="error">
                삭제하기
              </Button>
              <Button onClick={handleClose}>취소</Button>
            </div>
          </Box>
        </Modal>
      </Fragment>
    );
  }
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

  ////////////////////
  const [contentState, setContentState] = useState(content);
  const handleChangeState = (e) => {
    setContentState(e.target.value);
  };

  // 수정하기
  const commentUpdate = () => {
    const data = {
      context: contentState,
    };
    axios
      .put(`/api/comment/${commentSeq}`, qs.stringify(data))
      .then(function (response) {});
    // 수정은 완료되는데 돔을 재가동해야함
    change();
    setOpen(false);
  };

  // 삭제하기
  const commentRemove = () => {
    axios.delete(`/api/comment/${commentSeq}`).then(function (response) {});
    // 수정은 완료되는데 돔을 재가동해야함
    change();
    setOpen(false);
  };

  // @@@@@@@@@@@@@@@@
  const loginUser = sessionStorage.getItem("loginUser");

  return (
    <div>
      {/* 로그인 유저 */}
      {parseInt(loginUser) === memberSeq && (
        <div>
          <Modal open={open} onClose={handleClose}>
            {/* 아래 코드가 모달창 코드 */}
            <Box sx={{ ...style }}>
              <TextField
                variant="outlined"
                defaultValue={contentState}
                fullWidth
                multiline
                // value={contentState}
                onChange={handleChangeState}
              />
              {/* 수정 버튼 */}
              <Button
                variant="contained"
                style={{ width: "50%", marginTop: "5%" }}
                onClick={commentUpdate}
                color="success"
              >
                수정하기
              </Button>
              {/* 삭제 버튼 */}

              <ChildModal />
            </Box>
          </Modal>
        </div>
      )}

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
    </div>
  );
};

export default CommentItem;
