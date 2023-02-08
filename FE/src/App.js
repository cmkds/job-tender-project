import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";

//컴포넌트 import
// import MyButton from "./components/MyButton";
import TopBar from "./components/TopBar";
import RouteTest from "./components/RouteTest";

//페이지 import
import Home from "./pages/Home";
import Menu from "./pages/Menu";
import Map from "./pages/Map";
import Menual from "./pages/Menual";
import Main from "./pages/Main";
import LogBox from "./pages/LogBox";
import Feed from "./pages/Feed";
import Storage from "./pages/Storage";
import User from "./pages/User";
import Comment from "./pages/Comment";
import SignUp from "./pages/SignUp";
import BottomBar from "./components/BottomBar";
import Test from "./api/Teest";
import Search from "./pages/Search";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles({
  root: {
    fontFamily: "GangwonEduAll",
    fontWeight: "bold",
  },
});

function App() {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <BrowserRouter>
        <div className="App">
          {/* <TopBar
            // className="topbar"
            head_btn_center={"중단 홈"}
            head_btn_right={"우측 로그인 버튼"}
          /> */}
          <Routes>
            <Route path="/" element={<Home />}></Route>
            <Route path="/menu" element={<Menu />}></Route>
            <Route path="/map" element={<Map />}></Route>
            <Route path="/menual" element={<Menual />}></Route>
            <Route path="/main/*" element={<Main />}></Route>
            <Route path="/logbox" element={<LogBox />}></Route>
            <Route path="/feed/:id" element={<Feed />}></Route>
            <Route path="/storage/*" element={<Storage />}></Route>
            <Route path="/user/*" element={<User />}></Route>
            <Route path="/comment/:feedId" element={<Comment />}></Route>
            <Route path="/sign-up" element={<SignUp />}></Route>
            <Route path="/api-test" element={<Test />}></Route>
            <Route path="/search" element={<Search />}></Route>
          </Routes>
          <BottomBar />
        </div>
        <RouteTest />
      </BrowserRouter>
    </div>
  );
}

export default App;
