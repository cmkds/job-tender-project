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
      <Link to={"/main"}>main</Link>
      <br />
      <Link to={"/logbox"}>logbox</Link>
      <br />
      <Link to={"/feed/:id"}>feed</Link>
      <br />
      <Link to={"/storage"}>storage</Link>
      <br />
      <Link to={"/:user"}>user</Link>
      <textarea name="" id="" cols="30" rows="10"></textarea>
    </>
  );
};

export default RouteTest;
