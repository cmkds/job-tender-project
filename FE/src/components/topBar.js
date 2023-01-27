//상단바
const TopBar = ({ headText, leftChild, rightChild }) => {
  return (
    <header>
      <div className="head_btn_left">{leftChild}</div>
      <div className="head_btn_center">{headText}</div>
      <div className="head_btn_right">{rightChild}</div>
    </header>
  );
};
export default TopBar;
