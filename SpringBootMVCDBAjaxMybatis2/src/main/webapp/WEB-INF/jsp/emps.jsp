<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 관리</title>
</head>
<body>
<h1>사원 관리</h1>

<table>
    <thead>
        <tr><th>employeeId</th><th>firstName</th><th>lastName</th><th>email</th><th>hireDate</th></tr>
    </thead>
    <tbody id="empTbody">
    </tbody>
</table>
<hr>
<form>
    <input type="text" name="employeeId" id="employeeId"></input><br>
    <input type="text" name="firstName" id="firstName"></input><br>
    <input type="text" name="lastName" id="lastName"></input><br>
    <input type="text" name="email" id="email"></input><br>
    <input type="text" name="hireDate" id="hireDate"></input><br>
</form>
<hr>
<button type="button" id="btnInsert">등록</button> <button type="button" id="btnUpdate">수정</button> <button type="button" id="btnDelete">삭제</button>

<script>
    window.onload = function(){
        listEmp();

        document.querySelector("#btnInsert").onclick = insertEmp;
        document.querySelector("#btnUpdate").onclick = updateEmp;
        document.querySelector("#btnDelete").onclick = deleteEmp;
    }

    async function listEmp(){

        let url = '/emps/list';
        try{
            let response = await fetch(url); // get 요청 json 포함 응답
            let data = await response.json(); // response 에서 json 꺼내서 javascript 객체로 변환

            makeListHtml(data); // javascript 객체를 이용해서 table 에 목록 구성

        }catch(error){
            console.log(error);
            alert('사원 목록 처리 중 오류 발생!');
        }
    }

    <%-- ${}  --%>
    // 백틱 (`) : 두 백틱 사이의 멀티라인의 문자열을 허락
    // \${el.bookId} : 문자열 사이에 자바스크립트 객체의 값을 전달 => 1, 2, 3,
    // 위 2개 모두 문자열 연결 + 처리하는 불편 해소
    // \${} ?? <= // jsp el 충동 방지
    async function makeListHtml(list){
        console.log(list);

        let listHTML = ``;
        list.forEach( el => {

            listHTML +=
                `<tr style="cursor:pointer" data-employeeId=\${el.employeeId}>
                        <td>\${el.employeeId}</td>
                        <td>\${el.firstName}</td>
                        <td>\${el.lastName}</td>
                        <td>\${el.email}</td>
                        <td>\${el.hireDate}</td>
                    </tr>`;

        } );

        document.querySelector("#empTbody").innerHTML = listHTML;

        document.querySelectorAll("#empTbody tr").forEach( el => {
            el.onclick = function(){
                let employeeId = this.getAttribute("data-employeeId");
                detailEmp(employeeId);
            }
        });
    }

    async function detailEmp(employeeId){
        let url = '/emps/detail/' + employeeId;  // path variable 에 대응
        try{
            let response = await fetch(url);
            let data = await response.json();
            console.log(data);

            document.querySelector("#employeeId").value = data.employeeId;
            document.querySelector("#firstName").value = data.firstName;
            document.querySelector("#lastName").value = data.lastName;
            document.querySelector("#email").value = data.email;
            document.querySelector("#hireDate").value = data.hireDate;

        } catch( error ){
            console.error( error );
            alert('사원 상세 처리 중 오류 발생!');
        }
    }

    async function insertEmp(){
        let urlParams = new URLSearchParams({  // post 전송 방식 중 x-www-url-encoded 방식
            employeeId: document.querySelector("#employeeId").value,
            firstName: document.querySelector("#firstName").value,
            lastName: document.querySelector("#lastName").value,
            email: document.querySelector("#email").value,
            hireDate: document.querySelector("#hireDate").value
        });

        let fetchOptions = {
            method: "POST",
            body: urlParams,
        }

        let url = '/emps/insert';

        try{
            let response = await fetch(url, fetchOptions );
            let data = await response.json();

            console.log(data);

            if( data.result == "success" ){
                alert('사원 등록!');
            }else {
                alert('사원 등록 실패!');
            }

            listEmp();

        }catch( error ){
            console.error( error );
            alert('사원 등록 처리 중 오류 발생!');
        }
    }

    async function updateEmp(){
        let urlParams = new URLSearchParams({
            employeeId: document.querySelector("#employeeId").value,
            firstName: document.querySelector("#firstName").value,
            lastName: document.querySelector("#lastName").value,
            email: document.querySelector("#email").value,
            hireDate: document.querySelector("#hireDate").value
        });

        let fetchOptions = {
            method: "POST",
            body: urlParams,
        }

        let url = '/emps/update';

        try{
            let response = await fetch(url, fetchOptions );
            let data = await response.json();

            console.log(data);

            if( data.result == "success" ){
                alert('사원 수정!');
            }else {
                alert('사원 수정 실패!');
            }

            listEmp();

        }catch( error ){
            console.error( error );
            alert('사원 수정 처리 중 오류 발생!');
        }
    }

    async function deleteEmp(){
        let employeeId = document.querySelector("#employeeId").value;
        let url = '/emps/delete/' + employeeId;
        try{
            let response = await fetch(url);
            let data = await response.json();
            console.log(data);

            if( data.result == "success" ){
                alert('사원 삭제!');
            }else {
                alert('사원 삭제 실패!');
            }

            listEmp();

        }catch( error ){
            console.error( error );
            alert('사원 삭제 처리 중 오류 발생!');
        }
    }
</script>
</body>
</html>