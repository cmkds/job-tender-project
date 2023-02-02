import { useContext } from "react";
import { StorageStateContext } from "../Storage";
import StorageItem from "./StorageItem";

const StorageList = () => {
  const storageList = useContext(StorageStateContext);

  return (
    <div>
      {storageList.map((it) => (
        <StorageItem key={it.id} {...it} />
      ))}
    </div>
  );
};

export default StorageList;
