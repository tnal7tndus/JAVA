let idInput = document.getElementById("id");
let pwInput = document.getElementById("password");
let errorBox = document.getElementById('errorBox');
let loginBt = document.getElementById('loginInBox').children[0];

let id = "test";
let pw = "123123!";



function selectLoginType(ele) {
    idInput.value = "";
    pwInput.value = "";
    idBox.style.border = "1px solid #564f45";
    idBox.style.borderBottom = "0.5px solid #564f45";
    idBox.children[0].style.color = "black";
    idBox.children[0].style.opacity = "0.3";
    passwordBox.style.border = "1px solid #564f45";
    passwordBox.style.borderTop = "0.5px solid #564f45";
    passwordBox.children[0].style.color = "black";
    passwordBox.children[0].style.opacity = "0.3";
    for (let e of errorBox.children) {
        e.innerText = '';
    }
    if (ele.innerText == "일반 로그인") {
        ele.style.backgroundColor = "#9B1B30 ";
        ele.style.borderBottom = "none";
        ele.style.color = "white";
        ele.nextElementSibling.style.backgroundColor = "white";
        ele.nextElementSibling.style.color = "black";
        ele.nextElementSibling.style.borderBottom = "1px solid #564f45";
    } else {
        ele.style.backgroundColor = "#9B1B30 ";
        ele.style.borderBottom = "none";
        ele.style.color = "white";
        ele.previousElementSibling.style.backgroundColor = "white";
        ele.previousElementSibling.style.color = "black";
        ele.previousElementSibling.style.borderBottom = "1px solid #564f45";
    }
}

function focusInputBox(event) {
    let box = event.target.closest('div');
    for (e of box.children) {
        e.style.opacity = "1";
    }
    box.style.border = "2px solid #9B1B30";
}

function changeOpacityId(event) {
    let box = event.target.closest('div');
    for (e of box.children) {
        e.style.opacity = "1";
    }
    if (event.which == 13) {
        event.preventDefault();
        pwInput.focus();
    }
}

function changeOpacityPw(event) {
    let box = event.target.closest('div');
    for (e of box.children) {
        e.style.opacity = "1";
    }
    return true;
}


function checkId(event) {
    let value = event.target.value;
    let key = /[a-z.0-9.-._]/gi;

    if (value.length < 4 || value.length > 15) {
        idBox.style.border = "2px solid #FF3F3F";
        idBox.style.borderBottom = "1px solid #FF3F3F";
        idBox.children[0].style.color = "#FF3F3F";
        document.getElementById('idError').innerHTML = `<i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp;아이디 : 4 ~ 15 글자 이하만 가능합니다.<br>`;
    } else if (value.replace(key, '').length > 0) {
        idBox.style.border = "2px solid #FF3F3F";
        idBox.style.borderBottom = "1px solid #FF3F3F";
        idBox.children[0].style.color = "#FF3F3F";
        document.getElementById('idError').innerHTML = `<i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp;아이디 : 영문, 숫자, 특수문자(-, _)만 가능합니다.<br>`;
    } else {
        idBox.style.border = "2px solid #03C75A";
        idBox.style.borderBottom = "1px solid #03C75A";
        idBox.children[0].style.color = "#03C75A";
        document.getElementById('idError').innerHTML = '';
    }
}

function checkPassword(event) {
    let value = event.target.value;
    let key = /[a-z.0-9.!-*.@]/gi;

    if (value.length < 4 || value.length > 15) {
        passwordBox.style.border = "2px solid #FF3F3F";
        passwordBox.style.borderTop = "1px solid #FF3F3F";
        passwordBox.children[0].style.color = "#FF3F3F";
        document.getElementById('pwError').innerHTML = `<i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp;비밀번호 : 4 ~ 15 글자 이하만 입력해주세요.<br>`;
    } else {
        passwordBox.style.border = "2px solid #03C75A";
        passwordBox.style.borderTop = "1px solid #03C75A";
        passwordBox.children[0].style.color = "#03C75A";
        document.getElementById('pwError').innerHTML = '';
    }
}
