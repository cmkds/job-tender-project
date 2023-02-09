const NavBar = ({ navText, leftChild, rightChild }) => {
  return (
    <div style={{ marginTop: "75px" }}>
      <div className="navbar">
        <div className="nav_btn_left">{leftChild}</div>
        <div className="nav_btn_center">{navText}</div>
        <div className="nav_btn_right">{rightChild}</div>
      </div>
    </div>
  );
};
export default NavBar;
