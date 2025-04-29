    function initUI() {
        // 로그인 상태를 확인 UI 처리
        let name = sessionStorage.getItem("name");

        if( name != null ) {
        document.querySelector("#userName").innerHTML = name;
        document.querySelector("#userNameWrapper").style.display='inline';
        document.querySelector("#linkLogout").style.display='inline';

        document.querySelector("#linkLogin").style.display='none';
    } else {
        document.querySelector("#userName").innerHTML = '';
        document.querySelector("#userNameWrapper").style.display='none';
        document.querySelector("#linkLogout").style.display='none';

        document.querySelector("#linkLogin").style.display='inline';
    }
}

    async function logout(){
    // url
    let url = "/users/logout";

    let response = await fetch(url);
    let data = await response.json()

    console.log(data);
    if( data.result == 'success' ){

    // user 의 name, email 을 삭제 ( 개별 처리 )
    sessionStorage.removeItem("name");
    sessionStorage.removeItem("email");

    // 한꺼번에 전체 삭제
//          sessionStorage.clear();

    initUI();

    }
}