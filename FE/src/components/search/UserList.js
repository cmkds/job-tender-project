import React, { useState } from "react";

// import Card from "./Card";
// import Detail from "./Detail";

const UserList = ({ item }) => {
  // const [clicked, setClicked] = useState();
  // const handleCardClick = (id) => {
  //   setClicked(item.find((el) => el.id === id));
  // };
  console.log(item);

  return (
    <div>
      <p>유저리스트다</p>
      {item.map((it) => (
        <div>
          <p> {it.name}</p>
          <p> {it.id}</p>
          <p> {it.phone}</p>
          <p> {it.email}</p>
          <img src={it.image}></img>
        </div>
        // onClick={() => handleCardClick(monster.id)}
        // clicked={clicked}
        // setClicked={setClicked}
      ))}
      {/* {clicked && <Detail clicked={clicked} setClicked={setClicked} />} */}
    </div>
  );
};
export default UserList;
