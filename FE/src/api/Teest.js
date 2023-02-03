import axios from "axios";

const Teest = () => {
  const a = () => {
    axios
      .get("https://jsonplaceholder.typicode.com/users")
      .then(function (response) {
        console.log(response);
        console.log(222);
      });
  };
  a();
  return <div></div>;
};

export default Teest;
