import { useContext } from "react";
import { StorageStateContext } from "../../pages/Storage";
import * as React from 'react';
import ImageList from '@mui/material/ImageList';
import ImageListItemBar from '@mui/material/ImageListItemBar';
import ImageListItem from '@mui/material/ImageListItem';
import { useNavigate } from "react-router-dom";

const StorageList = () => {
  const storageList = useContext(StorageStateContext);
  const navigate = useNavigate();
  return (
    <div style={{ width: "100%", height: "100%"}}>
      <ImageList >
        {storageList.map((it) => (
          <ImageListItem key={it.id} {...it}>
            <img
              src={`${it.photo_url}`}
              // srcSet={`${it.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
              alt={it.id}
              loading="lazy"
              onClick={() => navigate(`/storage/${it.id}`)}
            />
            <div style={{display: "flex", backgroundColor:"#C1BCBC", color:"white", height: "100%", padding: "3% 5% 3%", justifyContent: "space-between", fontFamily: "GangwonEduAll"}}>
              <div>
                {it.location}
              </div>
              <div>
                {it.date}
              </div>
            </div>
          
          </ImageListItem>
        ))}
      </ImageList>  
    </div>
  );
};

export default StorageList;
