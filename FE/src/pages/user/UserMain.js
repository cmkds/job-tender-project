// /user/:id
import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserStateContext } from "../User";
import UserFeedList from "../../components/user/UserFeedList";
import TopBar from "../../components/TopBar";
import { makeStyles } from "@material-ui/core/styles";
import Avatar from "@material-ui/core/Avatar";

// 게시물 리스트를 보여줄 페이지.
// 해당 유저의 아이디와 로그인한 아이디가 같다면
// 팔로우 팔로잉 버튼 안보임
// 사진변경, 닉네임 변경 가능
// 팔로워 페이지에서 팔로워 취소 버튼 생김

//다르면
// 메인 페이지에 팔로우, 팔로잉 버튼 보이고 언팔로우 가능
// 팔로잉 페이지에   팔로잉 버튼에 본인이 있으면 언팔로우 가능
const useStyles = makeStyles((theme) => ({
  root: {
    alignItems: "center",
    paddingLeft: "8%",
    paddingTop: "8%",
    width: theme.spacing(13),
    height: theme.spacing(13),
  },
}));

const UserMain = () => {
  const classes = useStyles();

  const userData = useContext(UserStateContext)[0][0];
  const navigate = useNavigate();
  console.log(userData);
  return (
    <div>
      <TopBar />
      <div
        style={{
          display: "flex",
          justifyContent: "space-around",
          textAlign: "center",
        }}
      >
        <div style={{ marginLeft: "5%", marginTop: "5%", marginBottom: "5%" }}>
          <Avatar className={classes.root} src="/assets/profile.png" />
          <div
            style={{
              fontFamily: "GangwonEduAll",
              fontWeight: "bold",
              fontSize: 20,
              paddingTop: "3%",
            }}
          >
            {" "}
            {userData.nickname}
          </div>
        </div>
        <div
          style={{
            paddingLeft: "3%",
            margin: "auto",
            fontFamily: "GangwonEduAll",
            fontWeight: "bold",
            fontSize: 16,
          }}
        >
          게시글
          <br />
          {userData.feeds.length}
        </div>
        <div
          style={{
            margin: "auto",
            fontFamily: "GangwonEduAll",
            fontWeight: "bold",
            fontSize: 16,
          }}
          onClick={() => navigate(`/user/${userData.id}/follower`)}
        >
          <p>
            팔로워
            <br />
            {userData.followers.length}
          </p>
        </div>
        <div
          style={{
            margin: "auto",
            fontFamily: "GangwonEduAll",
            fontWeight: "bold",
            fontSize: 16,
          }}
          onClick={() => navigate(`/user/${userData.id}/following`)}
        >
          <p>
            팔로잉
            <br />
            {userData.followings.length}
          </p>
        </div>
      </div>
      <hr />
      <UserFeedList />
    </div>
  );
};

export default UserMain;
