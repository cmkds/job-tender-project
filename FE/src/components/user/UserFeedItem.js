const UserFeedItem = ({ id, post_image }) => {
  return (
    <div>
      <p>유저피드아이템 {id}</p>
      <div>
        <img src={post_image} alt="" />
      </div>
    </div>
  );
};

export default UserFeedItem;
