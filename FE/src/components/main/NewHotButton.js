import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import Fab from "@mui/material/Fab";
export default function FloatingActionButtons() {
  const navigate = useNavigate();
  const params = useParams();

  const [category, setCategory] = useState("hot");

  const clickButton = () => {
    if (category === "new") {
      setCategory("hot");
      navigate(`/main/${category}/${params.locationId}`);
    } else {
      setCategory("new");
      navigate(`/main/${category}/${params.locationId}`);
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
