import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { BrowserView, MobileView } from "react-device-detect";

//컴포넌트 import
// import MyButton from "./components/MyButton";
// import TopBar from "./components/TopBar";
// import RouteTest from "./components/RouteTest";
import ScrollToTop from "./components/ScrollToTop";

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
import Search from "./pages/Search";
import NaverLogin from "./pages/NaverLogin";
import Edit from "./pages/Edit";
import QR from "./pages/QR";
import NotFoundPage from "./pages/NotFound";
import IsWeb from "./pages/IsWeb";

import { makeStyles } from "@material-ui/core/styles";
import { createTheme } from "@material-ui/core/styles";
import { ThemeProvider } from "@material-ui/styles";

const useStyles = makeStyles({
  root: {
    fontFamily: "GangwonEduAll",
    fontWeight: "bold",
    // backgroundColor: "rgba(255, 250, 230, 0.5)",
  },
});

const theme = createTheme({
  palette: {
    primary: {
      main: "#FFB9B9",
    },
  },
});

function App() {
  const classes = useStyles();

  return (
    <div>
      <BrowserView>
        <IsWeb></IsWeb>
      </BrowserView>

      <MobileView>
        <ThemeProvider theme={theme}>
          <div className={classes.root}>
            <BrowserRouter>
              <ScrollToTop />
              <div className="App">
                <Routes>
                  <Route path="/" element={<Home />}></Route>
                  <Route path="/menu" element={<Menu />}></Route>
                  <Route path="/map" element={<Map />}></Route>
                  <Route path="/menual" element={<Menual />}></Route>
                  <Route path="/main/*" element={<Main />}></Route>
                  <Route
                    path="/logbox"
                    element={<LogBox className="stop-scrolling" />}
                  ></Route>
                  <Route path="/feed/:id" element={<Feed />}></Route>
                  <Route path="/storage/*" element={<Storage />}></Route>
                  <Route path="/user/*" element={<User />}></Route>
                  <Route path="/comment/:feedId" element={<Comment />}></Route>
                  <Route path="/sign-up" element={<SignUp />}></Route>
                  <Route path="/search" element={<Search />}></Route>
                  {/* <Route path="/download/*" element={<Download />}></Route> */}
                  <Route path="/naver" element={<NaverLogin />}></Route>
                  <Route path="/edit" element={<Edit />}></Route>
                  <Route
                    path="/download/:machineDataSeq"
                    element={<QR />}
                  ></Route>
                  {/* <Route path="/search" element={<Search />}></Route> */}
                  <Route path="/*" element={<NotFoundPage />} />
                </Routes>
              </div>
              {/* <RouteTest /> */}
            </BrowserRouter>
          </div>
        </ThemeProvider>
      </MobileView>
    </div>
  );
}

export default App;
