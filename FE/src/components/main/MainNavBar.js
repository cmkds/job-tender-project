import * as React from "react";
import { useNavigate, useParams } from "react-router-dom";

import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Box from "@mui/material/Box";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  root: {
    padding: theme.spacing(3),
  },
  header: {
    padding: theme.spacing(3),
  },
  tabCont: {
    padding: theme.spacing(3),
  },
  divider: {
    background: "#e3e3e3",
  },
  customStyleOnTab: {
    fontSize: "20px",
    color: "#bac3d5",
    fontFamily: "GangwonEduAll",
    zIndex: "tooltip !important",
  },
  activeTab: {
    fontSize: "30px",
    fontWeight: "600",
    fontFamily: "GangwonEduAll",
    color: "#d75b51",
  },
  textIndent: {
    textIndent: "15px",
  },
}));

const MainNavBar = () => {
  const [value, setValue] = React.useState(0);
  const classes = useStyles();
  const navigate = useNavigate();
  const params = useParams();
  const handleChange = (event, newValue) => {
    setValue(newValue);
    // console.log(newValue);
    // console.log(params.value);
  };

  return (
    <div style={{ position: "fixed", zIndex: "1" }}>
      <Box
        sx={{
          maxWidth: "100vw",
          bgcolor: "white",
          height: "70px",
        }}
      >
        <Tabs
          TabIndicatorProps={{
            style: { background: "#d75b51" },
          }} // indicator color
          classes={{ indicator: classes.customStyleOnTab }}
          value={value}
          onChange={handleChange}
          variant="scrollable"
        >
          <Tab
            label={
              <span
                className={
                  value === 0 ? classes.activeTab : classes.customStyleOnTab
                }
              >
                전국
              </span>
            }
          />
          <Tab
            label={
              <span
                className={
                  value === 1 ? classes.activeTab : classes.customStyleOnTab
                }
              >
                서울
              </span>
            }
          />
          <Tab
            label={
              <span
                className={
                  value === 2 ? classes.activeTab : classes.customStyleOnTab
                }
              >
                강릉
              </span>
            }
          />
          <Tab
            label={
              <span
                className={
                  value === 3 ? classes.activeTab : classes.customStyleOnTab
                }
              >
                경주
              </span>
            }
          />
          <Tab
            label={
              <span
                className={
                  value === 4 ? classes.activeTab : classes.customStyleOnTab
                }
              >
                전주
              </span>
            }
          />
          <Tab
            label={
              <span
                className={
                  value === 5 ? classes.activeTab : classes.customStyleOnTab
                }
              >
                부산
              </span>
            }
          />
        </Tabs>
      </Box>
    </div>
  );
};

export default MainNavBar;
