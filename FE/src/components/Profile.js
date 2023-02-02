// 프로필 사진 이름
// 댓글 유저의 아이디만 프롭스로 받아서 해당 정보를 가져온다.

// 유저아이디로 해당 유저의 정보를 통신해서 가져옴
const Profile = (userId) => {
  return (
    <div>
      <img src="http://dummyimage.com/87x56.png/ff4444/ffffff" alt="" />
      <span> userId</span>
    </div>
  );
};

export default Profile;
