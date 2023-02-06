const MenuBar = ({ navText, leftChild, rightChild, onCilck }) => {
  return (
    <div className="menubar" onClick={onCilck}>
      <div className="menu_btn_left">{leftChild}</div>
      <div className="menu_btn_center">{navText}</div>
      <div className="menu_btn_right">{rightChild}</div>
    </div>
  );
};
export default MenuBar;
