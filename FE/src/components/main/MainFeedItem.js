// ///안쓰임

// import { useState } from "react";
// import { useNavigate } from "react-router-dom";

// import Profile from "../Profile";

// import IconButton from "@mui/material/IconButton";
// import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
// import FavoriteIcon from "@mui/icons-material/Favorite";
// import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";

// const MainFeedItem = (feedId) => {
//   const navigate = useNavigate();
//   const [like, setLike] = useState(false);
//   return (
//     <div>
//       {/* <p>{feed.id}번 째 피드</p> */}
//       {/* 유저 아이디 넣기 */}
//       <Profile />
//       {/* array는 [인덱스] 객체는 .key이름 */}
//       <div className="post_wrapper">
//         <img className="post_wrapper" src={feedId.context.post_image} />
//       </div>
//       {/* 댓글 넣기 요건 네비게이트로 감.*/}
//       <p className="logbox_text">{feedId.context.content}</p>
//       <IconButton
//         sx={{ paddingLeft: "5%" }}
//         onClick={() => {
//           setLike(!like);
//         }}
//       >
//         {like ? (
//           <FavoriteIcon sx={{ color: "red" }} />
//         ) : (
//           <FavoriteBorderOutlinedIcon />
//         )}
//       </IconButton>
//       <IconButton>
//         <ChatBubbleOutlineIcon
//           onClick={() => navigate(`/comment/${feedId.id}`)}
//         />
//       </IconButton>
//       <span className="logbox_text">
//         ·좋아요 {feedId.context.likes.length}개
//       </span>
//       <span className="logbox_text">
//         ·댓글 {feedId.context.comments.data.length}개
//       </span>
//       <hr />
//     </div>
//   );
// };

// export default MainFeedItem;
