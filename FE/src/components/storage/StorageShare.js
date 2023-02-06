// /storage/:card-no/share
// 엽서 게시물 작성해서 피드에 공유하는 페이지.

const StorageShare = () => {
  return (
    <div>
      스토리지 쉐어
      <textarea
        name=""
        id=""
        cols="30"
        rows="10"
        placeholder="할 말을 입력하세요"
      ></textarea>
      <button>올리기</button>
    </div>
  );
};

export default StorageShare;
