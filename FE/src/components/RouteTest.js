import { Link } from "react-router-dom";

const RouteTest = () => {
  return (
    <>
      <Link to={"/"}>Home</Link>
      <br />
      <Link to={"/menu"}>menu</Link>
      <br />
      <Link to={"/map"}>map</Link>
      <br />
      <Link to={"/menual"}>menual</Link>
      <br />
      <Link to={"/main/hot/0"}>main</Link>
      <br />
      <Link to={"/logbox"}>logbox</Link>
      <br />
      <Link to={"/feed/:id"}>feed</Link>
      <br />
      <Link to={"/storage"}>storage</Link>
      <br />
      <Link to={"/user/1"}>user</Link>
      <br />
      {/* 테스트용 comment/1 */}
      <Link to={"/comment/1"}>comment</Link>
      <br />
      <Link to={"/sign-up"}>sign-up</Link>
      <br />
      <Link to={"/search"}>search</Link>
      <br />
    </>
  );
};

export default RouteTest;
