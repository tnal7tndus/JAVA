let errorBox = document.getElementById('errorBox');
let idInput = document.getElementById("id");
let pwInput = document.getElementById("password");

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

// =================================
function changePageToSignUp(event) {
    event.preventDefault();
    main.innerHTML = '';
    writeSignUp();
}

function writeSignUp() {
    main.style.height = '850px';
    main.innerHTML = `
    <a href="/tomatoFarm/"><img id="logo" src="../resources/img/logo.png"></img></a>
        <h3>회원가입</h3>
        <form id="signUpBox" action="signup" method="post">
            <p id="writeOption"><i class="fa-solid fa-check"></i>&nbsp;&nbsp;필수 입력 사항</p>
            <div id="idBox">
                <i class="fa-solid fa-user"></i>
                <input onkeydown="changeOpacityId(event)" onblur="checkId(event)" onfocus="focusInputBox(event)"
                    type="text" name="id" placeholder="아이디">
            </div>
            <div id="passwordBox">
                <i class="fa-solid fa-key"></i>
                <input onkeydown="changeOpacityPw(event)" onblur="checkPassword(event)" onfocus="focusInputBox(event)"
                    type="password" name="password" placeholder="비밀번호">
            </div>
            <div id="nameBox">
                <i class="fa-solid fa-circle-user"></i>
                <input onkeydown="changeOpacityName(event)" onblur="checkName(event)" onfocus="focusInputBox(event)"
                    type="text" name="name" placeholder="이름">
            </div>
            <div id="phonenumberBox">
                <i class="fa-solid fa-phone"></i>
                <input onkeydown="changeOpacityPn(event)" onblur="checkPhonenumber(event)"
                    onfocus="focusInputBox(event)" type="text" name="phonenumber" placeholder="전화번호">
            </div>
            <p id="errorBox">
                <span id="idError"></span>
                <span id="pwError"></span>
                <span id="nameError"></span>
                <span id="pnError"></span>
            </p>
            <p id="selectOption"><i class="fa-solid fa-check"></i>&nbsp;&nbsp;선택 입력 사항</p>
            <div id="addressBox">
                <i class="fa-solid fa-location-dot"></i>
                <input onkeydown="changeOpacityAddress(event)" type="text" name="address" placeholder="주소">
            </div>
            <div id="emailBox">
                <i class="fa-solid fa-envelope"></i>
                <input onkeydown="changeOpacityEmail(event)" type="text" name="email" placeholder="이메일"><i
                    class="fa-solid fa-at"></i>
                <input onkeydown="changeOpacityEmail(event)" type="text" name="emailback" id="emailWriteBox">
                <select onchange="changeSelectBox(event)" name="emailback" id="emailSelectBox">
                    <option>이메일 선택</option>
                    <option value="naver.com">naver.com</option>
                    <option value="daum.net">daum.net</option>
                    <option value="google.com">google.com</option>
                    <option value="nate.com">nate.com</option>
                    <option value=",">직접입력</option>
                </select>
            </div>
            <div id="genderBox">
                <i class="fa-solid fa-person-half-dress"></i>
                <span>성별</span>
                <ul id="genderUl">
                    <label>
                        <li>
                            <input onkeydown="changeOpacity(event)" onclick="selectGender(event)" type="radio"
                                name="gender" value="남성">남자
                        </li>
                    </label>
                    <label>
                        <li>
                            <input onkeydown="changeOpacity(event)" onclick="selectGender(event)" type="radio"
                                name="gender" value="여성">여자
                        </li>
                    </label>
                </ul>
            </div>
            <div id="birthdayBox">
                <i class="fa-solid fa-cake-candles"></i>
                <input onkeydown="changeOpacity2(event)" type="text" name="year" placeholder="yyyy" maxlength="4">
                <input onkeydown="changeOpacity2(event)" type="text" name="month" placeholder="mm" maxlength="2">
                <input onkeydown="changeOpacity2(event)" type="text" name="day" placeholder="dd" maxlength="2">
            </div>
            <button id="joinBox" disabled>가입하기</button>
        </form>
        <br>
        <p id="successOrNot">
        </p>
    `
}
