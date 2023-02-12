const UserProfile = () => {
  window.location.href.includes('access_token') && GetUser();
  function GetUser() {
    const location = window.location.href.split('=')[1];
    const form_data = new FormData();

    const item:any = {
      token: location.split('&')[0],
    };
    for (const key in item) {
      form_data.append(key, item[key]);
    }
    fetch(${유저 토큰으로 유저 정보를 가져오는 backend api url}, {
      method: 'POST',
      body: form_data,
    }).then((res) => res.json())
      .then((resjson) => {
        if (resjson.responseCode == '403') {
          // 사용자가 없으니 회원가입 창으로
        } else if (resjson.responseCode == '200') {
          // 로그인이 되었으니 이 후 process 로 이동
        }
      }).catch((err) => console.log(err));
  }
};