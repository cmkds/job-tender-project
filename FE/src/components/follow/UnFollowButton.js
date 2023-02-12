// 팔로우 언팔로우 버튼 컴포넌트
// 팔로잉 페이지에 들어가는 언팔로우 버튼
import axios from "axios";

import Profile from "../Profile";

const UnFollowButton = ({ id, change }) => {
  // console.log(id, change);
  // 여기에 버튼 누르면 언팔로우 하도록 한다음에

  const loginUser = 1;
  // 상위 태그

  const unFollow = () => {
    // 앞에 path가 지울사람 뒤에 path가 행위자
    axios
      .delete(`/api/profile/follow/${id}/${loginUser}`)
      .then(function (response) {
        console.log(response);
      });
    change();
  };

  return (
    <div>
      <Profile id={id} />

      <button onClick={unFollow}>언팔로우</button>
    </div>
  );
};

export default UnFollowButton;
