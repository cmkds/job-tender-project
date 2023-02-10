// 팔로우 언팔로우 버튼 컴포넌트
// 팔로잉 페이지에 들어가는 언팔로우 버튼
import Profile from "../Profile";

const UnFollowButton = (userId) => {
  // console.log(userId.id);
  return (
    <div>
      <Profile id={userId.id} />
      <button>언팔로우</button>
    </div>
  );
};

export default UnFollowButton;
