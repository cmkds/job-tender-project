import * as React from "react";
import Fab from "@mui/material/Fab";
import { useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
export default function FloatingActionButtons() {
  const [category, setCategory] = useState("hot");
  const navigate = useNavigate();
  const params = useParams();
  console.log();

  const clickButton = () => {
    if (category === "new") {
      setCategory("hot");
      navigate("main/new");
    } else {
      setCategory("new");
      navigate("main/hot");
    }
  };

  return (
    <div className="switch-position">
      {category === "hot" ? (
        <Fab
          style={{
            backgroundColor: "white",
          }}
          onClick={() => {
            clickButton();
          }}
        >
          <div
            style={{
              fontFamily: "GangwonEduAll",
              fontSize: "30px",
              fontWeight: "600",
              marginBottom: "10%",
              color: "#201F1F",
            }}
          >
            뉴
          </div>
        </Fab>
      ) : (
        <Fab
          style={{
            backgroundColor: "white",
          }}
          onClick={() => {
            clickButton();
          }}
        >
          <div
            style={{
              fontFamily: "GangwonEduAll",
              fontSize: "30px",
              fontWeight: "600",
              marginBottom: "10%",
              color: "#201F1F",
            }}
          >
            핫
          </div>
        </Fab>
      )}
    </div>
  );
}
