import "./App.css";
import { useReducer, useRef } from "react";
import { BrowserRouter, Routes, Route, useNavigate } from "react-router-dom";

//컴포넌트 import
import MyButton from "./components/MyButton";
import TopBar from "./components/TopBar";

//페이지 import
import Home from "./pages/home";

// reducer
// const reducer =

function App() {
  // reducer
  // const [data, dispatch] = useReducer(reducer, []);

  // const navigate = useNavigate();

  return (
    <BrowserRouter>
      <div className="App">
        <TopBar
          className="topbar"
          headText={"상단바"}
          leftChild={<MyButton text={"< 뒤로가기"} onClick={() => {}} />}
        />

        <Routes>
          <Route path="/" element={<Home />}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
