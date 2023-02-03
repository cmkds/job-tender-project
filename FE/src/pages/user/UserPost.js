// /:userId/:itemId
import { useNavigate } from "react-router-dom";

import NavBar from "../../components/NavBar";
import MyButton from "../../components/MyButton";

// 개별 아이템 보여주는 페이지.
// 여기 어떻게 처리할지 고민.
const UserPost = () => {
  const navigate = useNavigate();
  return (
    <div>
      <NavBar
        navText={"내엽서"}
        leftChild={<MyButton text={"<"} onClick={() => navigate(-1)} />}
      />
      ;
    </div>
  );
};

export default UserPost;
