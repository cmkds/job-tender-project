// 시작 페이지 로그인 화면 뜸

const Home = () => {
  return (
    <div>
      <h2>홈페이지</h2>
      <div>
        <img src={process.env.PUBLIC_URL + `assets/logo.png`}></img>
      </div>
      <button onClick={() => {}}>네이버로 로그인</button>
      <div>
        <img src={process.env.PUBLIC_URL + `assets/post.png`}></img>
      </div>
    </div>
  );
};

export default Home;
