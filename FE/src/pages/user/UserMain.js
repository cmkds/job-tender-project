// /user/:id
import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserStateContext } from "../User";
import UserFeedList from "../../components/user/UserFeedList";

// 게시물 리스트를 보여줄 페이지.
// 해당 유저의 아이디와 로그인한 아이디가 같다면
// 팔로우 팔로잉 버튼 안보임
// 사진변경, 닉네임 변경 가능
// 팔로워 페이지에서 팔로워 취소 버튼 생김

//다르면
// 메인 페이지에 팔로우, 팔로잉 버튼 보이고 언팔로우 가능
// 팔로잉 페이지에   팔로잉 버튼에 본인이 있으면 언팔로우 가능

const UserMain = () => {
  const userData = useContext(UserStateContext)[0][0];
  const navigate = useNavigate();
  console.log(userData);
  return (
    <div>
      게시물 리스트 페이지
      <br />
      <div>
        <img src={userData.profile_img} alt="" />
        <p>{userData.nickname}</p>
      </div>
      <div>
        <p>
          게시글
          <br />
          {userData.feeds.length}
        </p>
        <div onClick={() => navigate(`/user/${userData.id}/follower`)}>
          <p>
            팔로워
            <br />
            {userData.followers.length}
          </p>
        </div>
        <div onClick={() => navigate(`/user/${userData.id}/following`)}>
          <p>
            팔로잉
            <br />
            {userData.followings.length}
          </p>
        </div>
      </div>
      <br />
      <UserFeedList />
    </div>
  );
};

export default UserMain;
