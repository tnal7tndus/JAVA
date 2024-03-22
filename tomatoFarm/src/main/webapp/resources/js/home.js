'use strict';

/* 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 모듈예정 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 */
// 쉼표 찍기
function makeComa(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

// itemBox 작성하기
function writeItemBox(data) {
    let result = `
        <div class="itemBox" onclick="writeItemDetailBox(${data.code})">
            <div class="itemImg">
                <i class="fa-solid fa-cart-shopping"></i>
                <i class="fa-solid fa-magnifying-glass"></i>
                <img src="/resources/img/itemImg/${data.code < 10000 ? 'default' : data.code}_1.jpg" alt="${data.name}">
            <div></div>
        </div>
        <div class="itemName">${data.name}</div>
        <div class="itemInfo">${data.brand}<br></div>
        `;
    if (data.discount) {
        result += `
                <p class="itemPriceB">${makeComa(data.price)}원</p>
                <p class="itemPrice">${makeComa(Math.round(data.price * (100 - data.discount) / 100))}원</p>
            `;
    } else {
        result += `
                <p class="itemPrice">${makeComa(data.price)}원</p>
            `;
    }
    if (data.delivery > 0) {
        result += `
            <div class="itemDelivery"><span>배송비&nbsp;&nbsp;</span>${makeComa(data.delivery)}원</div>
            `;
    } else {
        result += `
            <div class="itemDelivery">무료배송</div>
        `;
    }
    result += `
        <div class="itemOption">
        `;
    if (data.delivery == 0) {
        result += `
            <div class="itemOptionFreeDelivery">무료배송</div>
            `;
    }
    if (data.event_code != null) {
        result += `
            <div class="itemOptionEvent">${data.event_name}</div>
            `;
    }
    result += `
            </div>
        </div>
        `;

    return result;
}

/* 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 모듈예정 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 */

const body = document.getElementsByTagName("body")[0];
const slideBox = document.getElementsByClassName("slideBox");
const secondContainer = document.getElementById('secondContainer');
let secondSlideBtn;
const adImgBox = document.getElementById('adImgBox');
const main = document.getElementById('main');
const header = document.getElementsByTagName('header')[0];

/* list */
let listfilter;;

const adImgList = ['fresheasy.jpg', 'mychef.jpg', 'signup.jpg', 'review.jpg']

let idx = 0;




/* 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 Home View 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 */



function changeAdImgBox(ele, event) {
    event.stopPropagation();
    let index = 0;
    for (let e of ele.closest('#adRightTab').children) {
        if (e == ele) break;
        index++;
    }
    ele.closest('#adImg').children[0].src = `../resources/img/adimg/${adImgList[index]}`;
}

function secondContainerSlideLeftbth(event) {
    document.getElementById('secondSlideBtnSelected').removeAttribute("id");
    let margin = event.target.closest('#secondContainerList').children[0].style.marginLeft.replace('px', '');
    if (margin < 2200) {
        margin = +margin + 440;
    }
    secondSlideBtn.children[`${5 - margin / 440}`].setAttribute("id", "secondSlideBtnSelected")
    slideBox[0].style.marginLeft = `${margin}px`;
}

function secondContainerSlideRightbth(event) {
    document.getElementById('secondSlideBtnSelected').removeAttribute("id");
    let margin = event.target.closest('#secondContainerList').children[0].style.marginLeft.replace('px', '');
    if (margin > -2200) {
        margin -= 440;
    }
    secondSlideBtn.children[`${5 - margin / 440}`].setAttribute("id", "secondSlideBtnSelected");
    slideBox[0].style.marginLeft = `${margin}px`;
}

function secondContainerSlideBtn(event) {
    let target = event.target;
    if (event.target.id != 'secondSlideBtn') {
        document.getElementById('secondSlideBtnSelected').removeAttribute("id");
        event.target.setAttribute("id", "secondSlideBtnSelected");
        let index = 0;
        for (let a of secondSlideBtn.children) {
            if (target == a) {
                break;
            }
            ++index;
        }
        slideBox[0].style.marginLeft = `${2200 - (440 * index)}px`;
    }
}

function thirdContainerSlideRightBth(event) {
    let box = event.target.closest('.typeBoxList').children;
    let margin = box[0].style.marginLeft.replace('px', '');
    let maxMargin = -220 * (box[0].children.length - 2);
    if (margin > maxMargin) {
        margin -= 220;
        box[0].style.marginLeft = `${margin}px`;
    }
    if (margin == maxMargin) {
        box[2].style.display = "none";
    }
    if (margin != 0) {
        box[1].style.display = "block";
    }
}

function thirdContainerSlideLeftBth(event) {
    let box = event.target.closest('.typeBoxList').children;
    let margin = box[0].style.marginLeft.replace('px', '');
    let maxMargin = -220 * (box[0].children.length - 2);
    if (margin >= maxMargin) {
        margin = +margin + 220;
        box[0].style.marginLeft = `${margin}px`;
    }
    if (margin == 0) {
        box[1].style.display = "none";
    }
    if (margin != 0) {
        box[2].style.display = "block";
    }
}

/* 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 Header 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 */

/* 📗📗📗📗 TAG 📗📗📗📗 */
let firstCategory; // 카테고리 리스트
let searchBoxInput; // 헤더 메인 검색창 input 테그


writeHeader()

/* 📖📖📖📖 view 📖📖📖📖*/

function resetInputBox(ele) {
    searchBoxInput.value = '';
    searchBoxInput.focus();
    ele.closest("form").children[1].style.visibility = "hidden"
}

function appearinputBoxResetButton(ele) {
    ele.closest("form").children[1].style.visibility = "visible"
}

function sperateKorWord(str) {
    const kor_starts = ["ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"];
    const kor_middles = ["ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"];
    const kor_ends = ["", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"];

    const unicode_kor_start_num = 44032;
    const unicode_kor_end_num = 55203;

    const unicodeNum = str.charCodeAt(0);

    if (unicodeNum < unicode_kor_start_num || unicodeNum > unicode_kor_end_num) {
        return str;
    }

    // const kor_starts_index
}

function seachCategory(ele) {
    let key = ele.value;
    let liBox = ele.closest('ul').children;
    for (let i = 2; i < liBox.length; i++) {
        if (!liBox[i].innerText.includes(key)) {
            liBox[i].style.display = "none";
        } else {
            liBox[i].style.display = "block";
        }
    }
}

function searchBox(event) {
    event.preventDefault();
    let keyword = event.target.closest('form').children[0].value;
    writeItemList(keyword);
}

function searchBoxEnterKey(event) {
    if (event.which == 13) {
        searchBox(event)
    }
}

async function writeHeader() {
    let content = `
        <div id="loginBar">
            <div class="container">
                <a>고객센터</a>
                &nbsp;&nbsp;|&nbsp;&nbsp;
                <a onclick='writeLoginPage()'>로그인</a>
                &nbsp;&nbsp;|&nbsp;&nbsp;
                <a href="">회원가입</a>
            </div>
        </div>

        <div id="searchBar">
            <div class="container">
                <div id="logoBox">
                    <a href="/">
                        <img src="../resources/img/logo.png" alt="로고">
                        <h1>토마토팜 tomatoFarm</h1>
                    </a>
                </div>
                <form id="searchBox">
                    <input onkeyup="searchBoxEnterKey(event)" oninput="appearinputBoxResetButton(this)"
                        id="searchBoxInput" type="text" placeholder="검색어를 입력해주세요.">
                    <i onclick="resetInputBox(this)" class="fa-solid fa-circle-xmark"></i>
                    <button onclick="searchBox(event)"><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
                <div id="searchRightBox">
                    <div id="myPage">
                        <a href="/"><i class="fa-solid fa-user"></i></a>
                    </div>
                    <div id="myCart">
                        <a href="/"><i class="fa-solid fa-cart-shopping"></i></a>
                    </div>
                    <div id="myItem">
                        <a href="/"><i class="fa-solid fa-box-archive"></i></a>
                    </div>
                </div>
            </div>
        </div>

        <nav>
            <div class="container">
                <div id="categoryBox">
                    <div id="categoryTag"><i class="fa-solid fa-bars"></i>&nbsp;&nbsp;&nbsp;카테고리</div>
                    <ul id="firstCategory">
                        <li></li>
                        <li id="firstCategorySearch">
                            <div>
                                <input onkeyup="seachCategory(this)" oninput="appearinputBoxResetButton2(this)"
                                    type="text"><i onclick="resetInputBox2(this)" class="fa-solid fa-circle-xmark"></i>
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </div>
                        </li>
                    </ul>
                </div>
                <ul id="navBar">
                    <li><a href="">밀키트 주문</a></li>
                    <li><a href="">식단 주문</a></li>
                    <li><a href="">재료 주문</a></li>
                    <li><a href="">이벤트</a></li>
                </ul>
            </div>
        </nav>
    `;
    header.innerHTML = content;

    firstCategory = document.getElementById("firstCategory");
    searchBoxInput = document.getElementById("searchBoxInput");

    firstCategory.innerHTML += await makeHeader();

}

/* 💻💻💻💻 view model 💻💻💻💻*/
async function makeHeader() {
    let result = '';
    const data = await getSortList();
    for (let e of data) {
        result += `
                <li><img src="../resources/img/${e.sort2}.png" alt="${e.sort2} 이미지">${e.sort2}</li>
                `;
    }
    return result;
}

/* 📦📦📦📦 model 📦📦📦📦*/
async function getSortList() {
    let uri = '/item/sort';
    let response = await axios.get(uri);
    // console.log("🚨🚨🚨🚨 getSortList 에러 🚨🚨🚨🚨");
    return response.data;
}


/* 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 Home 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 */

writeHome();

// 로그인페이지 -> 홈
async function pageToHome() {
    await writeHeader();
    await writeHome();
}

/* 📖📖📖📖 view 📖📖📖📖*/

async function writeHome() {
    let content = await makeAdImgBox();
    content += `
        <div id="firstContainer" class="container">
            <h3><i class="fa-solid fa-star"></i>&nbsp;&nbsp; 토마토팜 바로가기 &nbsp;&nbsp;<i class="fa-solid fa-star"></i></h3>
            <div id="firstContainerButton">
                <div><a><img src="../resources/img/index_bestSeller.png" alt="베스트 상품" class="categoryImg"><br>베스트 상품</a>
                </div>
                <div onclick="changePageToList('밀키트')"><a><img src="../resources/img/index_mealkit.png" alt="밀키트"
                            class="categoryImg"><br>밀키트</a></div>
                <div><a><img src="../resources/img/index_food.png" alt="신선 재료" class="categoryImg"><br>신선 재료</a></div>
                <div><a><img src="../resources/img/index_menu.png" alt="메뉴 주문" class="categoryImg"><br>메뉴 주문</a></div>
                <div><a><img src="../resources/img/index_cooking.png" alt="조리 도구" class="categoryImg"><br>조리 도구</a>
                </div>
                <div><a><img src="../resources/img/index_gift.png" alt="이벤트" class="categoryImg"><br>이벤트</a></div>
            </div>
        </div>
        <hr>
    `;
    content += `
        <div id="secondContainer" class="container">
            <h3> <i class="fa-solid fa-gift"></i>   특가 상품   <i class="fa-solid fa-gift"></i></h3>
        `;
    content += await makeSlideContainer();
    content += `
        </div>
        <hr>
        `;
    content += `
        <div id="thirdContainer" class="container hide">
            <h3 class="hide"><i class="fa-solid fa-bag-shopping"></i> &nbsp;&nbsp; 상품 보기 &nbsp;&nbsp; <i class="fa-solid fa-bag-shopping"></i></h3>
        </div>
    `;

    main.innerHTML = content;
    secondSlideBtn = document.getElementById('secondSlideBtn');

    let writeTarget = ['프레시지', '김구원선생', '마이셰프', '하림', '하루한킷'];
    let content2 = [];
    const thirdContainer = document.getElementById('thirdContainer');
    for (let i in writeTarget) {
        content2[i] = await makePresentBox(writeTarget[i]);
    }

    window.addEventListener("scroll", function () {
        if (document.documentElement.scrollHeight - innerHeight - scrollY < 600) {
            if (content2.length > idx) {
                thirdContainer.innerHTML += content2[idx++];
            }
        }
    });
}

/* 💻💻💻💻 view model 💻💻💻💻*/
async function makeAdImgBox() {
    let result = `
        <div id = "adImgBox">
            <div id="adImg" class="container">
                <img src="../resources/img/adimg/fresheasy.jpg" alt="">
                <div id="adRightTab">
                    <div onmouseover="changeAdImgBox(this,event)">프레시지<img src="../resources/img/brand/프레시지.png" alt="프레시지로고"></div>
                    <div onmouseover="changeAdImgBox(this,event)">MyChef<img src="../resources/img/brand/마이셰프.png" alt="마이셰프로고"></div>
                    <div onmouseover="changeAdImgBox(this,event)">회원가입쿠폰<img src="../resources/img/adimg/coupon.jpg" alt="마이셰프로고"></div>
                    <div onmouseover="changeAdImgBox(this,event)">후기이벤트<img src="../resources/img/adimg/review.png" alt="마이셰프로고"></div>
                </div>
            </div>
        </div >
    `;
    return result;
}

async function makeSlideContainer() {
    let data = await getEventItem();
    let result = `
            <div id="secondContainerList">
                <div class="slideBox">
        `;

    for (const e of data) {
        result += writeItemBox(e);
    }

    result += `
                </div>    
                <div id="secondSlideBtn" onclick="secondContainerSlideBtn(event)">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div id="secondSlideBtnSelected"></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
                <div id="secondContainerLeftBtn" onclick="secondContainerSlideLeftbth(event)"><i class="fa-sharp fa-solid fa-arrow-left"></i></div>
                <div id="secondContainerRightBtn" onclick="secondContainerSlideRightbth(event)"><i class="fa-sharp fa-solid fa-arrow-right"></i></div>
            </div>
        ` ;
    return result;

}

async function makePresentBox(brand) {
    const data = await getBrandItem(brand);
    let result = `
        <div class="typeBox hide">
            <div class="typeBoxTag">
                <div class="typeBoxTagTitle"><img src="../resources/img/brand/${brand}.png"
                        alt="category_vitamin">${brand}
                </div>
                <ul class="typeBoxTagList">
                    <li><a href="">스테이크</a></li>
                    <li><a href="">파스타</a></li>
                    <li><a href="">감바스</a></li>
                </ul>
            </div>
            <a onclick="writeItemDetailBox(${data[0].code})" class="typeBoxImg">
                <img src="../resources/img/itemImg/${data[0].code}_1.jpg" alt="${data[0].name}">
                <div class="typeBoxImgTitle">
                    <div class="typeBoxImgTitleName">
                        ${data[0].name}
                    </div>
                    <p class="typeBoxImgTitlePrice">${makeComa(data[0].price)}원</p>
                </div>
                <div class="typeBoxImgTitleBest">Best 상품</div>
            </a>
        <div class="typeBoxList">
            <div class="slideBox">
    `;
    for (let i = 1; i < 6; i++) {
        result += writeItemBox(data[i]);
    }

    result += `
                    <div onclick="writeItemList('${data[0].brand}')" class="linkBox">
                        <p>" ${data[0].brand} "</p>
                        <i class="fa-regular fa-circle-play"></i> 상품 더 보러가기
                    </div>
                </div>
                <div onclick="thirdContainerSlideLeftBth(event)" class="thirdContainerLeftBtn">
                    <i class="fa-sharp fa-solid fa-arrow-left"></i>
                </div>
                <div onclick="thirdContainerSlideRightBth(event)" class="thirdContainerRightBtn">
                    <i class="fa-sharp fa-solid fa-arrow-right"></i>
                </div>
            </div>
        `;
    return result;
}

/* 📦📦📦📦 model 📦📦📦📦*/
async function getEventItem() {
    let uri = "/item/eventitem";
    const response = await axios.get(uri);
    return response.data;
}

async function getBrandItem(brand) {
    let uri = "/item/branditem/" + brand;
    let response = await axios.get(uri);
    return response.data;
}

/* 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 List 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 */
/* 📖📖📖📖 view 📖📖📖📖*/


// 아이템리스트 작성 (keyword=검색어 , sortType=정렬기준)
async function writeItemList(keyword, sortType) {
    let content = `
        <div id="searchTitle" class="container">"<b>${keyword}</b>"<span>에 대한 검색 결과</span></div>
        <div class="container">
    `;
    content += await makeListFilter();
    content += await makeItemList(keyword, sortType);

    content += `
        </div>
    `;

    main.innerHTML = content;

    window.addEventListener('scroll', function () {
        listfilter = document.getElementById("listfilter");
        listfilter.style.height = `calc(100vh - 320px - 30px + ${window.scrollY}px)`;
        if (window.scrollY <= 300) {
            listfilter.style.top = `calc(325px - ${window.scrollY}px)`;
        } else {
            listfilter.style.top = `30px`;
        }
    });
}

/* 💻💻💻💻 view model 💻💻💻💻*/

// 아이템리스트 HTML코드 작성 (keyword=검색어 , sortType=정렬기준)
async function makeItemList(keyword, sortType) {
    const data = await getItemList(keyword, sortType);
    let result = `
        <div id="listContainer">
            <div id="containerOption">
                <div id="total">총 <span>${data.length}</span> 개</div>
                <div id="listOption">
                <div onclick="writeItemList('${keyword}','salesD')">인기상품순</div>
                    <div onclick="writeItemList('${keyword}','')">최신상품순</div>
                    <div onclick="writeItemList('${keyword}','priceA')">가격낮은순</div>
                    <div onclick="writeItemList('${keyword}','priceD')">가격높은순</div>
                </div>
            </div>
            `;

    for (let e of data) {
        result += writeItemBox(e);
    }

    result += `</div>`;

    return result;
}

async function makeListFilter() {
    let data = await getSortList();
    console.log(data);
    let result = `
        <div id="listfilter">
            <ul>
                <li onclick="showList(event)" class="sortB">
                    <i onclick="checkALL(event)" class="fa-regular fa-circle-check"></i>
                    <span>밀키트</span>
    `;
    result += `
                </li>
                <li onclick="showList(event)" class="sortB">
                    <i onclick="checkALL(event)" class="fa-regular fa-circle-check"></i>식재료
                    <ul>
        `;
    for (let e of data) {
        if (e.sort1 == '식재료')
            result += `
                        <li><i onclick="checkALL(event)" class="fa-regular fa-circle-check"></i><span>${e.sort2}</span></li>
            `;
    }
    result += `
                    </ul>
                </li>
                <li>
                    <i class="fa-regular fa-circle-check"></i>행사
                    <ul>
                        <li><i class="fa-regular fa-circle-check"></i>채선당</li>
                        <li><i class="fa-regular fa-circle-check"></i>도리깨침</li>
                    </ul>
                </li>
                <li id="filterPrice"><i class="fa-regular fa-circle-check"></i>가격
                    <form>
                        <input type="text" placeholder="0">
                        &nbsp;&nbsp;~&nbsp;&nbsp;
                        <input type="text" placeholder="1000000">
                    </form>
                </li>
            </ul>
        </div>
                        `;
    return result;
}

/* 📦📦📦📦 model 📦📦📦📦*/

// 아이템리스트 데이터 (keyword=검색어 , sortType=정렬기준)
async function getItemList(keyword, sortType) {
    let uri = "/item/search?keyword=" + keyword + "&sorttype=" + sortType;
    console.log(uri);
    const response = await axios.get(uri);
    return response.data;
}

/* 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 Detail 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 */
/* 📗📗📗📗 TAG 📗📗📗📗 */
let itemAskForm;
let reviewWriteForm;
let reviewDetailForm;
/* 📖📖📖📖 view 📖📖📖📖*/

function changeMainImg(event) {
    event.preventDefault();
    let ele = event.target.closest('div');
    ele.style.opacity = 1;
    ele.parentNode.previousElementSibling.children[0].src = ele.children[0].src;
    for (let i = 0; i < ele.parentNode.childElementCount; i++) {
        if (ele.parentNode.children[i] != ele) {
            ele.parentNode.children[i].style.opacity = '0.5';
        }
    }
    return false;
}

function count(event, type) {
    let target = event.target.closest("#countBox");
    let value = target.children[1].value
    if ("-" === type) {
        if (value > 0)
            value--;
    }
    else
        value++;

    target.children[1].value = value;
    sumTotal(value);
}

function sumTotal(value) {
    const title6Value = document.getElementById('title6').innerText.replace('원', '');
    const priceBox = document.getElementById('price');
    priceBox.children[0].innerText = `${value * title6Value} 원`;
}

function inputCount(event) {
    const inputBox = document.getElementById('inputCount');
    const countBox = document.getElementById('price');
    countBox.children[1].innerText = inputBox;
}

function showItemDetail(ele) {
    let itemIntro = ele.previousElementSibling;
    if (itemIntro.classList.contains('heightAuto')) {
        itemIntro.classList.remove('heightAuto');
        ele.innerHTML = `상품정보 더보기<i class="fa-solid fa-chevron-down"></i>`;
    } else {
        itemIntro.classList.add('heightAuto');
        introItemBtn.innerHTML = `상품정보 접기<i class="fa-solid fa-chevron-up"></i>`;
        ele.innerHTML = `상품정보 접기<i class="fa-solid fa-chevron-up"></i>`;
    }
    return null;
}

function reviewDetailClick(code) {
    writeReviewDetailForm(code);
}

function reivewDetailImgChange(ele) {
    ele.parentNode.previousElementSibling.children[0].setAttribute('src', ele.src);
}

function reviewDetailClose(ele) {
    ele.closest('#reviewDetailForm').style.display = 'none';
}

let imgList = document.getElementById('reviewDetailImgBottom');
let imgLength = imgList.length;

function returnImg(event) {
    let returnImg = (imgList + imgLength - 1) % imgLength;

}

function nextImg(event) {
    let nextImg = (imgList + 1) % imgLength;

}

async function itemAskClick() {
    writeItemAskForm();
}

async function itemReviewClick() {
    writeItemReviewForm();
}

async function writeItemDetailBox(code) {
    window.scrollTo(0, 0);
    main.innerHTML = await makeItemDetailBox(code);
    main.innerHTML += await makeItemReviewBoardBox(code);
    main.innerHTML += await makeAskBoardBox(code);
}

async function writeReviewDetailForm() {
    main.innerHTML += await makeReviewDetailForm();
    reviewDetailForm = document.getElementById('reviewDetailForm');

}
async function writeItemAskForm() {
    main.innerHTML += await makeItemAskForm();
    itemAskForm = document.getElementById('itemAskForm');
}
async function writeItemReviewForm() {
    main.innerHTML += await makeItemReviewForm();
    reviewWriteForm = document.getElementById('reviewWriteForm');
}

/* 💻💻💻💻 view model 💻💻💻💻*/
async function makeItemDetailBox(code) {
    const data = await getItem(code);
    let result = `
        <div id="itemDetailBox" class="container">
            <div id="imgBox">
                <div id="imgBoxImg">
                    <img src="resources/img/itemImg/${data.code}_1.jpg" alt="${data.name} 사진">
                </div>
                <div id="imgBoxImgList">
                    <div onclick="changeMainImg(event)"><img src="resources/img/itemImg/${data.code}_1.jpg" alt="${data.name} 조리"></div>
                    <div onclick="changeMainImg(event)"><img src="resources/img/itemImg/${data.code}_2.jpg" alt="${data.name} 제품"></div>
                    <div onclick="changeMainImg(event)"><img src="resources/img/itemImg/${data.code}_3.jpg" alt="${data.name} 구성품"></div>
                    <div onclick="changeMainImg(event)"><img src="resources/img/itemImg/${data.code}_4.jpg" alt="${data.name} 상세표기"></div>
                </div>
            </div>

            <div id="itemDetail">
                <div id="itemDetailTitle">
                    <div id="itemDelivery">${data.event}</div>
                    <div id="itemName">${data.name}</div>
                    <div id="itemAccount">소고기 찹스테이크 신선하고 맛있어요</div>
                    <span id="itemSale">10<span>%</span></span>
                    <div id="itemPrice">${makeComa(data.price)}원</div>
                    <div id="itemSalePrice">${makeComa(data.price)}원</div>
                </div>
                <div>배송</div>
                <div>${data.delivery}원<br>(23시 전 주문 시 내일 아침 7시 전 도착)</div>
                <div>제조사</div>
                <div>${data.brand}</div>
                <div>포장타입</div>
                <div>${data.storage}</div>
                <div>판매단위</div>
                <div>${data.packing}</div>
                <div>중량/용량</div>
                <div>${data.weight}g</div>
                <div>유통기한</div>
                <div>수령일 포함 180일 이상 남은 제품을 보내드립니다.</div>
                <div id="itemSelect">
                    <div>수량 선택</div>
                    <div id="countBox">
                        <button onclick='count(event,"-")'><i class="fa-solid fa-minus"></i></button>
                        <input onkeydown="inputCount(event)" id="inputCount" type="text" value="1">
                        <button onclick='count(event,"+")'><i class="fa-solid fa-plus"></i></button>
                    </div>
                    <div id="priceBox">
                        <div id="price">총 상품금액&nbsp; : &nbsp;<span>30000원</span></div>
                        <a href="" id="cart">장바구니 담기</a>
                        <a href="" id="buy">구매하기</a>
                    </div>
                </div>
            </div>
        
            <ul id="detailClick" class="container">
                <li id="detailButton"><a onclick="return showItemDetail(this)" href="#introItem1">상품설명</a></li>
                <li><a onclick="return confirm('aa')" href="#introItem2">상세정보</a></li>
                <li><a href="#reviewBoardBox">상품후기</a></li>
                <li><a href="#askBoardBox">상품문의</a></li>
            </ul>
            <div id="introItem" class="container">
            <div id="introItem1" class="subTitle">
                <hr>
                <h4>상품 조리 사진
                    <img src="resources/img/logo3.png" alt="제품 조리 사진">
                </h4>
                <hr>
            </div>
            <img src="resources/img/itemImg/${data.code}_2.jpg" alt="${data.name} 제품">
            <div id="introItem2" class="subTitle">
                <hr>
                <h4>상품 구성
                    <img src="resources/img/logo4.png" alt="상품 구성">
                </h4>
                <hr>
            </div>
            <img src="resources/img/itemImg/${data.code}_3.jpg" alt="${data.name} 구성품">
            <div class="subTitle">
                <hr>
                <h4>상품 표시사항
                    <img src="resources/img/logo2.png" alt="제품 조리 사진">
                </h4>
                <hr>
            </div>
            <img src="resources/img/itemImg/${data.code}_4.jpg" alt="${data.name} 상세표기">
        </div>
        <div onclick="showItemDetail(this)" id="introItemBtn" class="container">
            상품정보 더보기<i class="fa-solid fa-chevron-down"></i>
        </div>
    </div>
    <hr>
    `;
    return result;
}

async function makeItemReviewBoardBox(itemcode) {
    let data = await getReview(itemcode);
    let result = `
        <div id="reviewBoardBox" class="container appearContainer">
            <h5>상품후기</h5>
            <span>한줄리뷰 - 제목을 클릭하시면 상세내용을 보실 수 있습니다.</span>
            <div onclick="reviewWriteClick(this)" id="reviewWrite"> 후기작성 </div>
            <div id="reviewBoard">
                <div class="reviewBoardRow">
                    <div>별점</div>
                    <div>제목</div>
                    <div>작성자</div>
                    <div>등록일</div>
                </div>
    `;
    for (let e of data) {
        result += `
            <div onclick="reviewDetailClick(${e.seq})" class="reviewContent">
                <div class="reviewDetail">
                    <div id="reivewImg">
                        <img src="/resources/img/itemImg/${e.image1}.jpg" alt="">
                        <img src="/resources/img/itemImg/${e.image2}.jpg" alt="">
                    </div>
                    <b>${e.title}</b>
                    <p>${e.contents}</p>
                </div>
                <div>
        `;
        for (let i = 1; i <= e.score / 2; i++){
            result += `
            <i class="fa-solid fa-star"></i>
            `;
        }
        if (e.score % 2) 
            result += `
            <i class="fa-solid fa-star-half"></i>
            `;
        
        result +=`
                </div>
                <div>${e.title}</div>
                <div>${e.writer}</div>
                <div>${e.regdate}</div>
            </div>
        `;
    }

    result += `
            </div>
            <div id="reviewBoardBtn">
                <i class="fa-solid fa-angles-left"></i>
                <i class="fa-solid fa-angle-left"></i>
                <span> 1 </span>
                <span> 2 </span>
                <span> 3 </span>
                <i class="fa-solid fa-angle-right"></i>
                <i class="fa-solid fa-angles-right"></i>
            </div>
        </div>
        <hr>
    `;

    return result;
}
async function makeAskBoardBox(code) {
	let data = await getReview(itemcode);
    let result = `
        <div id="askBoardBox" class="container appearContainer">
            <h5>상품문의</h5>
            <span>상품문의 - 상품에 궁금하신점을 남겨주세요.</span>
            <div onclick="itemAskClick(event)" id="itemAskWrite">문의하기</div>
            <div id="askBoard">
                <div class="boardRow">
                    <div></div>
                    <div>답변</div>
                    <div>제목</div>
                    <div>작성자</div>
                    <div>작성일자</div>
                </div>
        `;

    for (let e of data) {
        result += `
            <div onclick="showContent(this)" class="boardRow">
                    <div class="askContents">
                        ${e.contents}
                        <a>답변</a>
                        <a>삭제</a>
                    </div>
                    <div>미답변</div>
                    <div>${e.title}</div>
                    <div>${e.writer}</div>
                    <div>${e.regdate}</div>
                </div>
            `;
    }

    result += `
            </div>
            <div id="askBoardBtn">
                <i class="fa-solid fa-angles-left"></i>
                <i class="fa-solid fa-angle-left"></i>
                <span> 1 </span>
                <span> 2 </span>
                <span> 3 </span>
                <i class="fa-solid fa-angle-right"></i>
                <i class="fa-solid fa-angles-right"></i>
            </div>
        </div>
     `;
    return result;
}

async function makeReviewDetailForm() {
    let data = await getReview(itemcode);
    let result = `
        <div onclick="reviewDetailClick(code)" id="reviewDetailForm">
        <div id="reviewDetailBox">
            <div id="reviewDetailImg">
                <div id="reviewDetailImgTop">
                    <img src="../resources/img/itemImg/${e.image2}" alt="">
                    <i class="fa-solid fa-arrow-left"></i>
                    <i class="fa-solid fa-arrow-right"></i>
                </div>
                <div id="reviewDetailImgBottom">
                    <img onclick="reivewDetailImgChange(this)" src="../resources/img/${e.image1}" alt="">
                    <img onclick="reivewDetailImgChange(this)" src="../resources/img/${e.image2}" alt="">
                </div>
            </div>
            <div id="reviewDetail_Write">
                <p>구매상품 이름</p>
                <p>${e.writer}</p>
                <p>${e.regdate}</p>
                <p id="reviewDetail_Final">${e.contents}</p>
            </div>
            <div onclick="reviewDetailClose(this)" id="reviewDetailBoxClose"><i class="fa-solid fa-xmark"></i></div>
        </div>
    </div>
    `;
    return result;
}

async function makeItemAskForm() {
    let result = `
    <div id="itemAskForm">
        <div id="itemAskBox">
            <div id="itemAskTop">
                <div>상품 문의하기</div>
                <img src="../resources/img/itemImg/5000100_1.jpg" alt="">
                <div>[밀키트] 밀키트다 밀키트다</div>
            </div>
            <div id="itemAskBottom">
                <div>제목 <input type="text" placeholder="제목을 입력해주세요"></input></div>
                <div>내용 
                    <input type="text" placeholder="상품 문의 작성 전 확인해주세요.
                        1. 답변은 영업일 기준 2~3일 소요됩니다.
                        2. 해당 게시판은 성격과 다른 글은 사전 동의 없이 담당 게시판으로 이동될 수 있습니다.
                        3. 배송관련, 주문(취소/교환/반품)관련 문의 요청사항은 마켓컬리 1:1 문의에 남겨주세요">
                    </input>
                </div>
            </div>
            <div id="itemAskPrivacy">
                <input type="checkbox" name="privacyBox">
                <div>비밀글로 문의하기</div>
            </div>
            <div id="itemAskButton">
                <button id="itemAskBoxCancle">취소</button>
                <button id="itemAskBoxEnter">등록</button>
            </div>
            <div onclick="itemAskBoxClose(this)" id="itemAskBoxClose"><i class="fa-solid fa-xmark"></i></div>
        </div>
    </div>
    `;
    return result;
}

async function makeItemReviewForm(){
	let result=`
	<div id="reviewWriteForm">
        <div id="reviewWriteBox">
            <div id="reviewWriteTop">
                <div>상품 후기 작성하기</div>
                <img src="../resources/img/itemImg/5000100_1.jpg" alt="">
                <div id="itemTitle">작성할 상품의 제목 넣으셈</div>
                <div>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star-half"></i>
                </div>
            </div>
            <div id="reviewWriteBottom">
                <div id="reviewWriteTitle">
                    <div>리뷰</div>
                    <input type="radio">맛있어요</input>
                    <input type="radio">보통이에요</input>
                    <input type="radio">생각보다 별로에요</input>
                </div>
                <div id="reviewWriteContent">
                    <div>상세리뷰</div>
                    <input type="text" placeholder="
                        다른 고객님에게 도움이 되도록 상품에 대한 솔직한 평가를 300자 이내로 남겨주세요.
                        (상품 품질과 관계 없는 배송, 포장, 질문 응대, 상품 가격 등은 판매자 서비스 평가에 남겨주세요.)">
                    </input>
                </div>
                <div id="reviewWriteContentBottom">
                    <div>* 상품 품질과 관계 없는 내용은 비공개 처리 될 수 있습니다</div>
                    <div>* 작성된 리뷰는 '마이페이지> 상품 후기 관리' 에서 수정 및 삭제 가능합니다</div>
                </div>
                <div id="reviewWritePhoto">
                    <div id="reviewPhoto">사진첨부</div>
                    <div>
                        <label>사진 첨부하기</label>
                    </div>
                </div>
            </div>
            <div id="reviewWriteButton">
                <button id="reviewWriteCancle">취소</button>
                <button id="reviewWriteEnter">등록</button>
            </div>
            <div onclick="reviewWriteBoxClose(this)" id="reviewWriteBoxClose"><i class="fa-solid fa-xmark"></i></div>
        </div>
    </div>
	`;
	return result;
}



/* 📦📦📦📦 model 📦📦📦📦*/
async function getItem(code) {
    const uri = "item/detail?code=" + code;
    const response = await axios.get(uri).catch(err => {

    });
    return response.data;
}

async function getReview(itemcode) {
    const uri = `itemreview/select/${itemcode}`;
    const response = await axios.get(uri).catch(err => {

    });
    return response.data;
}

async function getAsk(itemcode) {
    const uri = `itemask/select/${itemcode}`;
    const response = await axios.get(uri).catch(err => {

    });
    return response.data;
}


/* 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 Login 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 */
/* 📗📗📗📗 TAG 📗📗📗📗 */
let errorBox;
let signUpBox;
let idBox;
let passwordBox;
let nameBox;
let phonenumberBox;
let addressBox;
let emailBox;
let genderBox;
let birthdayBox;
let emailSelectBox;
let emailWriteBox;
let genderUl;
let genderName;
let emailback;


let idCheck = false;
let pwCheck = false;
let nameCheck = false;
let phoneCheck = false;
// ===============================
let idInput;
let pwInput;

/* 📖📖📖📖 view 📖📖📖📖*/
// 로그인 페이지 작성
function writeLoginPage() {
    body.innerHTML = makeLoginPage();
    idBox = document.getElementById('idBox');
    errorBox = document.getElementById('errorBox');
    signUpBox = document.getElementById('signUpBox');
    passwordBox = document.getElementById('passwordBox');
    nameBox = document.getElementById('nameBox');
    phonenumberBox = document.getElementById('phonenumberBox');
    addressBox = document.getElementById('addressBox');
    emailBox = document.getElementById('emailBox');
    genderBox = document.getElementById('genderBox');
    birthdayBox = document.getElementById('birthdayBox');
    emailSelectBox = document.getElementById("emailSelectBox");
    emailWriteBox = document.getElementById("emailWriteBox");
    genderUl = document.getElementById('genderUl');
    genderName = document.getElementsByName('gender');
    emailback = document.getElementById('emailWriteBox');

    idCheck = false;
    pwCheck = false;
    nameCheck = false;
    phoneCheck = false;
}
// 로그인 -> 회원가입 애니메이션


function clickLogin() {
    document.getElementById('loginBG').style.transform = "translate(0,0)";
    document.getElementById('signBG').style.transform = "translate(0,0)";
}

/* 💻💻💻💻 view model 💻💻💻💻*/
// 로그인 HTML코드 작성
function makeLoginPage() {
    let result = `
    <div id="bodyBG"></div>
    <div id="contentBox">
        <div id="signBG">
        <div onclick="clickLogin()" id="historyback"><i class="fa-solid fa-arrow-left"></i></div>
            <div>
                <a href="/tomatoFarm/"><img id="logo" src="/resources/img/logo.png"></img></a>
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
                            autocomplete="off" type="password" name="password" placeholder="비밀번호">
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
                        <select onchange="changeSelectBox(event)" id="emailSelectBox">
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
                                        name="gender" value="0">남자
                                </li>
                            </label>
                            <label>
                                <li>
                                    <input onkeydown="changeOpacity(event)" onclick="selectGender(event)" type="radio"
                                        name="gender" value="1">여자
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
                    <button type="button" onclick="requestSign()" id="joinBox" disabled>가입하기</button>
                </form>
                <br>
                <p id="successOrNot">
                </p>
            </div>
        </div>
        <div id="loginBG">
            <div>
                <a href="/tomatoFarm/"><img id="logo" src="/resources/img/logo.png"></a>
                <form id="loginBox" action="/tomatoFarm/member/login" method="post">
                    <div id="loginButton">
                        <div onclick="selectLoginType(this)">일반 로그인</div>
                        <div onclick="selectLoginType(this)">사업자 로그인</div>
                    </div>

                    <div id="idBox">
                        <i class="fa-solid fa-user"></i>
                        <input onkeydown="changeOpacityId(event)" onblur="checkId(event)" onfocus="focusInputBox(event)" id="id"
                            type="text" name="id" placeholder="아이디">
                    </div>
                    <div id="passwordBox">
                        <i class="fa-solid fa-key"></i>
                        <input onkeydown="changeOpacityPw(event)" onblur="checkPassword(event)" onfocus="focusInputBox(event)"
                            autocomplete="off" id="password" type="password" name="password" placeholder="비밀번호">
                    </div>
                    <p id="errorBox">
                        <span id="idError"></span>
                        <span id="pwError"></span>
                    </p>

                    <button type="button" onclick="requestLogin()" id="loginInBox">로그인</button>
                </form>
                <p id="successOrNot">
                </p>
                <ul id="search">
                    <li>아이디 찾기</li>
                    <li>비밀번호 찾기</li>
                    <li><a onclick="writeSign()">회원가입</a></li>
                </ul>
            </div>
        </div>
    </div>
    `;
    return result;
}

async function requestLogin() {
    let id = document.getElementById('id').value;
    let password = document.getElementById('password').value;
    let uri = `user/login`;
    let data = {
        id: id,
        password: password
    }
    let response = await axios.post(uri, null, {
        params: {
            id: id,
            password: password
        }
    });
    return response.data;
}


// 사용X (makeLoginPage 에 더해서 작성 중)
function makeSign() {
    let result = `
    <div id=signForm>
        <a href="/tomatoFarm/"><img id="logo" src="/tomatoFarm/resources/img/logo.png"></img></a>
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
                <input autocomplete="off" onkeydown="changeOpacityPw(event)" onblur="checkPassword(event)" onfocus="focusInputBox(event)"
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
            <button type="button" onclick="requestSign()" id="joinBox" disabled>가입하기</button>
        </form>
        <br>
        <p id="successOrNot">
        </p>
    </div>
    `;
    return result;
}

/* 💻💻💻💻 view 💻💻💻💻*/
function writeSign() {
    document.getElementById('loginBG').style.transform = "translate(-100%,0)";
    document.getElementById('signBG').style.transform = "translate(-100%,0)";
    document.getElementById('signBG').style.zIndex = '2';
}


/* 📦📦📦📦 model 📦📦📦📦*/



async function requestLogin() {
    idInput = document.getElementById("id");
    pwInput = document.getElementById("password");
    errorBox = document.getElementById('errorBox');
    let id = document.getElementById('id').value;
    let password = document.getElementById('password').value;
    let uri = `user/login`;
    let data = {
        id: id,
        password: password
    }
    let response = await axios.post(uri, null, {
        params: {
            id: id,
            password: password
        }
    });
}

async function requestSign() {
    let idValue = idBox.children[1].value;
    let pwdValue = passwordBox.children[1].value;
    let nameValue = nameBox.children[1].value;
    let phonenumberValue = phonenumberBox.children[1].value;
    let addressValue = addressBox.children[1].value;
    let emailFrontValue = emailBox.children[1].value;
    let emailBackValue = emailback.value;
    let genderValue;
    for (let t of genderName) {
        if (t.checked == true) {
            genderValue = t.value;
        }
    }
    let birthdateValue
        = birthdayBox.children[1].value
        + birthdayBox.children[2].value
        + birthdayBox.children[3].value;



    let uri = "user/signup";

    let response = await axios.post(uri, null, {
        params: {
            id: idValue,
            password: pwdValue,
            name: nameValue,
            phonenumber: phonenumberValue,
            address2: addressValue,
            email: emailFrontValue,
            email2: emailBackValue,
            gender: genderValue,
            birthdate: birthdateValue
        }
    });

}

// ===============================================

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


function focusInputBox(event) {
    let box = event.target.closest('div');
    box.style.border = "2px solid #9B1B30";
}

function changeOpacity(event) {
    let box = event.target.closest('div');
    for (let e of box.children) {
        e.style.opacity = "1";
    }
}
function changeOpacityId(event) {
    let box = event.target.closest('div');
    for (let e of box.children) {
        e.style.opacity = "1";
    }
    if (event.which == 13) {
        event.preventDefault();
        passwordBox.children[1].focus();
    }
}
function changeOpacityPw(event) {
    let box = event.target.closest('div');
    for (let e of box.children) {
        e.style.opacity = "1";
    }
    if (event.which == 13) {
        event.preventDefault();
        nameBox.children[1].focus();
    }
    console.log(`리턴없는놈이다`);
}
function changeOpacityName(event) {
    let box = event.target.closest('div');
    for (let e of box.children) {
        e.style.opacity = "1";
    }
    if (event.which == 13) {
        event.preventDefault();
        phonenumberBox.children[1].focus();
    }
}
function changeOpacityPn(event) {
    let box = event.target.closest('div');
    for (let e of box.children) {
        e.style.opacity = "1";
    }
    if (event.which == 13) {
        event.preventDefault();
        addressBox.children[1].focus();
    }
}
function changeOpacityAddress(event) {
    let box = event.target.closest('div');
    for (let e of box.children) {
        e.style.opacity = "1";
    }
    if (event.which == 13) {
        event.preventDefault();
        emailBox.children[1].focus();
    }
}
function changeOpacityEmail(event) {
    let box = event.target.closest('div');
    for (let e of box.children) {
        e.style.opacity = "1";
    }
    if (event.which == 13) {
        event.preventDefault();
        emailSelectBox.focus();
    }
}

function changeOpacity2(event) {
    event.target.closest('div').children[0].style.opacity = "1";
    event.target.style.opacity = "1";
}

function checkId(event) {
    console.log(idBox);
    let value = event.target.value;
    let key = /[a-z.0-9.-._]/gi;

    if (value.length < 4 || value.length > 15) {
        idCheck = false;
        idBox.style.border = "2px solid #FF3F3F";
        idBox.style.borderBottom = "1px solid #FF3F3F";
        idBox.children[0].style.color = "#FF3F3F";
        document.getElementById('idError').innerHTML = `<i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp;아이디 : 4 ~ 15 글자 이하만 가능합니다.<br>`;
    } else if (value.replace(key, '').length > 0) {
        idCheck = false;
        idBox.style.border = "2px solid #FF3F3F";
        idBox.style.borderBottom = "1px solid #FF3F3F";
        idBox.children[0].style.color = "#FF3F3F";
        document.getElementById('idError').innerHTML = `<i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp;아이디 : 영문, 숫자, 특수문자(-, _)만 가능합니다.<br>`;
    } else {
        idCheck = true;
        idBox.style.border = "2px solid #03C75A";
        idBox.style.borderBottom = "1px solid #03C75A";
        idBox.children[0].style.color = "#03C75A";
        document.getElementById('idError').innerHTML = '';
    }
    checkAll();
}//checkId

function checkPassword(event) {
    let value = event.target.value;
    let key = /[a-z.0-9.!-*.@]/gi;

    if (value.length < 4 || value.length > 14) {
        pwCheck = false;
        passwordBox.style.border = "2px solid #FF3F3F";
        passwordBox.style.borderTop = "1px solid #FF3F3F";
        passwordBox.style.borderBottom = "1px solid #FF3F3F";
        passwordBox.children[0].style.color = "#FF3F3F";
        document.getElementById('pwError').innerHTML = `<i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp;비밀번호 : 4 ~ 15 글자 이하만 입력해주세요.<br>`;
    } else if (value.replace(key, '').length > 0) {
        pwCheck = false;
        passwordBox.style.border = "2px solid #FF3F3F";
        passwordBox.style.borderTop = "1px solid #FF3F3F";
        passwordBox.style.borderBottom = "1px solid #FF3F3F";
        passwordBox.children[0].style.color = "#FF3F3F";
        document.getElementById('pwError').innerHTML = `<i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp;비밀번호 : 영문, 숫자, 특수문자(!,@,#,$,%,^,&,*)만 가능합니다.<br>`;
    } else if (value.replace(/[!-*.@]/gi, '').length >= value.length) {
        pwCheck = false;
        passwordBox.style.border = "2px solid #FF3F3F";
        passwordBox.style.borderTop = "1px solid #FF3F3F";
        passwordBox.style.borderBottom = "1px solid #FF3F3F";
        passwordBox.children[0].style.color = "#FF3F3F";
        document.getElementById('pwError').innerHTML = `<i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp;비밀번호 : 특수문자(!,@,#,$,%,^,&,*)를 반드시 포함해주세요.<br>`;
    } else {
        pwCheck = true;
        passwordBox.style.border = "2px solid #03C75A";
        passwordBox.style.borderTop = "1px solid #03C75A";
        passwordBox.style.borderBottom = "1px solid #03C75A";
        passwordBox.children[0].style.color = "#03C75A";
        document.getElementById('pwError').innerHTML = '';

    }
    checkAll();
}//checkPassword

function checkName(event) {
    let value = event.target.value;
    if (value.length < 2 || value.length > 10) {
        nameCheck = false;
        nameBox.style.border = "2px solid #FF3F3F";
        nameBox.style.borderTop = "1px solid #FF3F3F";
        nameBox.style.borderBottom = "1px solid #FF3F3F";
        nameBox.children[0].style.color = "#FF3F3F";
        document.getElementById('nameError').innerHTML = `<i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp;이름 : 2글자 이상 10글자 이하로 입력하세요.<br>`;
    } else if (value.replace(/[a-z.가-힣]/gi, '').length > 0) {
        nameCheck = false;
        nameBox.style.border = "2px solid #FF3F3F";
        nameBox.style.borderTop = "1px solid #FF3F3F";
        nameBox.style.borderBottom = "1px solid #FF3F3F";
        nameBox.children[0].style.color = "#FF3F3F";
        document.getElementById('nameError').innerHTML = `<i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp;이름은 한글, 영문만 입력하세요.<br>`;
    } else {
        nameCheck = true;
        nameBox.style.border = "2px solid #03C75A";
        nameBox.style.borderBottom = "1px solid #03C75A";
        nameBox.style.borderTop = "1px solid #03C75A";
        nameBox.children[0].style.color = "#03C75A";
        document.getElementById('nameError').innerHTML = '';
    }
    checkAll();
}//checkName

function checkPhonenumber(event) {
    let value = event.target.value;
    if (value.length < 10 || value.length > 11) {
        phoneCheck = false;
        phonenumberBox.style.border = "2px solid #FF3F3F";
        phonenumberBox.style.borderTop = "1px solid #FF3F3F";
        phonenumberBox.children[0].style.color = "#FF3F3F";
        document.getElementById('pnError').innerHTML = `<i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp;전화번호는 9자리 ~ 12자리 숫자로 입력해주세요.<br>`;
    } else if (value.replace(/[0-9]/gi, '').length > 0) {
        phoneCheck = false;
        phonenumberBox.style.border = "2px solid #FF3F3F";
        phonenumberBox.style.borderTop = "1px solid #FF3F3F";
        phonenumberBox.children[0].style.color = "#FF3F3F";
        document.getElementById('pnError').innerHTML = `<i class="fa-solid fa-circle-exclamation"></i>&nbsp;&nbsp;전화번호는 숫자만 입력하세요.<br>`;
    } else {
        phoneCheck = true;
        phonenumberBox.style.border = "2px solid #03C75A";
        phonenumberBox.style.borderTop = "1px solid #03C75A";
        phonenumberBox.children[0].style.color = "#03C75A";
        document.getElementById('pnError').innerHTML = '';
    }
    checkAll();
}//checkPhonenumber

function changeSelectBox(event) {
    if (event.target.value == "write") {
        emailSelectBox.style.display = "none";
        emailWriteBox.style.display = "inline-block";
        emailWriteBox.focus();
        return;
    }
    console.log(emailback.value)
    emailback.value = event.target.value;
    console.log(emailback.value)
}//changeSelectBox

function selectGender(event) {
    let value = event.target.value;
    for (let e of event.target.closest('div').children) {
        e.style.opacity = "1";
        e.style.color = "black";
    }
    if (document.getElementById('genderChecked') != null) {
        document.getElementById('genderChecked').removeAttribute("id");
    }
    event.target.closest('li').setAttribute("id", "genderChecked");
    birthdayBox.focus();
}//selectGender

function a(str) {
    console.log(str);
}

function checkAll() {
    if (idCheck == true && pwCheck == true && nameCheck == true && phoneCheck == true) {
        document.getElementById('joinBox').style.opacity = "1";
        document.getElementById('joinBox').disabled = false;
    } else {
        document.getElementById('joinBox').style.opacity = "0.5";
        document.getElementById('joinBox').disabled = true;
    }
}
// ============================================

function selectAll() {
    const checkboxes = document.getElementsByClassName(".chk");
    // console.log(checkAll);
    if (checkAll.checked) {
        const checkboxes = document.querySelectorAll('.chk');

        for (const a of checkboxes) {
            a.checked = true;
        }
    } else {
        const checkboxes = document.querySelectorAll('.chk');

        for (const a of checkboxes) {
            a.checked = false;
        }
    }
}

function uncheckedAllBox() {
    checkAll.checked = false;
}

function insertData() {
    const makeDiv = document.createElement("div");

    for (let index = 0; index < 12; index++) {
        if (index == 0) {
            orderList.appendChild(makeDiv);
            const makeInput = makeDiv.createElement("input");
            makeInput.className = "chk";
        } else {
            orderList.appendChild(makeDiv);
        }
        orderList.appendChild(makeDiv);
    }
    orderList.appendChild(makeDiv);
}

// ======================================================================
/* 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 CommandPage 🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅🍅 */
/* 📗📗📗📗 TAG 📗📗📗📗 */
let row;
let excelBox;



/* 📖📖📖📖 view 📖📖📖📖*/
async function writeCommand() {
    body.innerHTML = await makeCommand();
    row = document.getElementsByClassName('excelColumn');
    excelBox = document.getElementById('excelBox');
}

/* 📖📖📖📖 view model 📖📖📖📖*/
async function makeCommand() {
    let data = await getAllItem();
    console.log(data);
    let result = `
        <div id="topBar">
        <i class="fa-solid fa-house"></i>
    </div>
    <div id="sideBar">
        <div>토마토팜</div>
        <ul>
            <li onclick="openDetailFirst()">
                <div><i class="fa-solid fa-bars"></i><br>전체메뉴</div>
            </li>
            <li>
                <div><i class="fa-solid fa-circle-user"></i><br>즐겨찾기</div>
            </li>
            <li>
                <div><i class="fa-solid fa-list-check"></i><br>최근메뉴</div>
            </li>
            <li>
                <div><i class="fa-solid fa-bullhorn"></i><br>공지사항</div>
            </li>
            <li>
                <div><i class="fa-solid fa-bell"></i><br>알림</div>
            </li>
            <li>
                <div><i class="fa-solid fa-comment-dots"></i><br>고객센터</div>
            </li>
            <li>
                <div><i class="fa-solid fa-gear"></i><br>설정</div>
            </li>
        </ul>
        <div onclick="openSideBar()" id="sideBarButton"><i class="fa-solid fa-chevron-right"></i></div>
        <div id="sideBarDetail">
            <div id="sideBarDetailFirst">
                <ul>
                    <li>상품주문</li>
                    <li>상품조회/검색</li>
                    <li>주문조회</li>
                    <li>클레임 처리현황</li>
                    <li>출력물</li>
                </ul>
            </div>
            <div id="sideBarDetailSecond">
                <div id="sideBarDetailSecondExit" onclick="closeDetail()"><i class="fa-solid fa-xmark"></i></div>
                <div id="sideBarDetailSecondSearch">
                    <input type="text">
                    <i class="fa-solid fa-circle-xmark"></i>
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>
                <ul>
                    <li>식재주문등록</li>
                    <li>식단주문등록</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container">
        <div id="topBoxYH">
            <!-- 최상단 -->
            <div id="topBoxLeftYH">
                <h3>★ 식재주문등록</h3>&nbsp;&nbsp;
                <div>
                    <button onclick="plusColumn()">행추가<i class="fa-duotone fa-square-plus"></i></button>
                    <button onclick="sendExcelData()">추가완료<i class="fa-duotone fa-square-plus"></i></button>
                </div>
            </div>


            <div id=excelBox>
                <div id="excelHead">
                    <input type="text" value="상품코드" readonly>
                    <input type="text" value="대분류" readonly>
                    <input type="text" value="중분류" readonly>
                    <input type="text" value="소분류" readonly>
                    <input type="text" value="브랜드" readonly>
                    <input type="text" value="제품명" readonly>
                    <input type="text" value="중량" readonly>
                    <input type="text" value="보관방식" readonly>
                    <input type="text" value="포장단위" readonly>
                    <input type="text" value="배송비" readonly>
                    <input type="text" value="제품가격" readonly>
                    <input type="text" value="VAT" readonly>
                    <input type="text" value="원산지" readonly>
                    <input type="text" value="재고량" readonly>
                    <input type="text" value="제품등록자" readonly>
                </div>
                <div class="excelColumn">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                    <input type="text">
                </div>
            </div>
        </div>
    `;

    result += `
        <!-- 주문현황 -->
        <div id="orderReport">
            <div class="headtitle">
                <div class="subtitle"> &nbsp;주문현황</div>
                <div id="sortBt">
                    <div>가로</div>
                    <div>세로</div>
                </div>
            </div>



            <div id=excelBox>
                <div id="excelHead">
                    <input type="text" value="상품코드" readonly>
                    <input type="text" value="대분류" readonly>
                    <input type="text" value="중분류" readonly>
                    <input type="text" value="소분류" readonly>
                    <input type="text" value="브랜드" readonly>
                    <input type="text" value="제품명" readonly>
                    <input type="text" value="중량" readonly>
                    <input type="text" value="보관방식" readonly>
                    <input type="text" value="포장단위" readonly>
                    <input type="text" value="배송비" readonly>
                    <input type="text" value="제품가격" readonly>
                    <input type="text" value="VAT" readonly>
                    <input type="text" value="원산지" readonly>
                    <input type="text" value="재고량" readonly>
                    <input type="text" value="제품등록자" readonly>
                </div>
                `;

    for (let d of data) {
        result += `
            <div class="dataColumn">
                <input type="text" value=${d.code}>
                <input type="text" value=${d.sort1}>
                <input type="text" value=${d.sort2}>
                <input type="text" value=${d.sort3}>
                <input type="text" value=${d.brand}>
                <input type="text" value=${d.name}>
                <input type="text" value=${d.weight}>
                <input type="text" value=${d.storage}>
                <input type="text" value=${d.packing}>
                <input type="text" value=${d.delivery}>
                <input type="text" value=${d.price}>
                <input type="text" value=${d.vat}>
                <input type="text" value=${d.origin}>
                <input type="text" value=${d.stock}>
                <input type="text" value=${d.admin}>
            </div>
            `;
    }

    result += `
            <div id="pageNation">
                <div onclick="movePage(event)">1</div>
                <div onclick="movePage(event)">2</div>
                <div onclick="movePage(event)">3</div>
                <div onclick="movePage(event)">4</div>
                <div onclick="movePage(event)">5</div>
            </div>
            `;
    result += `
            </div>
        </div>

        <div id="orderListBox">
            <div>


                <div class="headtitle">
                    <div class="subtitle">&nbsp;주문내역</div>
                    <div id="orderListButtonBox">
                        <button><i class="fa-solid fa-magnifying-glass"></i></button>
                        <button><i class="fa-solid fa-file-excel"></i></button>
                        <button><i class="fa-solid fa-plus"></i></button>
                        <button><i class="fa-solid fa-minus"></i></button>
                    </div>
                </div>
                <div id="orderedListYH">
                    <!-- 주문내역 -->
                    <div id="insertRowYH">
                        <div><input type="checkbox" name="test1" id="checkAll" onclick="selectAll()"></div>
                        <div>순번</div>
                        <div>제품코드</div>
                        <div>제품명</div>
                        <div>규격</div>
                        <div>단위</div>
                        <div>주문량</div>
                        <div>단가</div>
                        <div>공급액</div>
                        <div>부가세</div>
                        <div>합계</div>
                        <div>원산지</div>
                        <div><input onclick="uncheckedAllBox()" type="checkbox" name="test1" class="chk"></div>
                        <div>1</div>
                        <div>1</div>
                        <div>1</div>
                        <div>1</div>
                        <div>1</div>
                        <div>1</div>
                        <div>1</div>
                        <div>1</div>
                        <div>1</div>
                        <div>1</div>
                        <div>1</div>
                        <div><input onclick="uncheckedAllBox()" type="checkbox" name="test1" class="chk"></div>
                        <div>2</div>
                        <div>2</div>
                        <div>2</div>
                        <div>2</div>
                        <div>2</div>
                        <div>2</div>
                        <div>2</div>
                        <div>2</div>
                        <div>2</div>
                        <div>2</div>
                        <div>2</div>
                        <div><input onclick="uncheckedAllBox()" type="checkbox" name="test1" class="chk"></div>
                        <div>3</div>
                        <div>3</div>
                        <div>3</div>
                        <div>3</div>
                        <div>3</div>
                        <div>3</div>
                        <div>3</div>
                        <div>3</div>
                        <div>3</div>
                        <div>3</div>
                        <div>3</div>

                    </div>
                </div>
            </div>
        </div>
    </div>
  `;

    return result;
}

/* 📦📦📦📦 model 📦📦📦📦*/
async function getAllItem() {
    let uri = 'item/allitem';
    let response = await axios.get(uri);
    return response.data;
}

function selectAll() {
    const checkboxes = document.getElementsByClassName(".chk");
    // console.log(checkAll);
    if (checkAll.checked) {
        const checkboxes = document.querySelectorAll('.chk');

        for (const a of checkboxes) {
            a.checked = true;
        }
    } else {
        const checkboxes = document.querySelectorAll('.chk');

        for (const a of checkboxes) {
            a.checked = false;
        }
    }
}

function uncheckedAllBox() {
    checkAll.checked = false;
}

function insertData() {
    const makeDiv = document.createElement("div");

    for (let index = 0; index < 12; index++) {
        if (index == 0) {
            orderList.appendChild(makeDiv);
            const makeInput = makeDiv.createElement("input");
            makeInput.className = "chk";
        } else {
            orderList.appendChild(makeDiv);
        }
        orderList.appendChild(makeDiv);
    }
    orderList.appendChild(makeDiv);
}


// =============================================
// =============================================


function plusColumn() {
    excelBox.innerHTML += `
        <div class="excelColumn">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
        </div>
    `
}


async function sendExcelData() {
    let uri = 'http://localhost:8090/item/batchinsert';
    // row = document.getElementsByClassName('excelColumn');
    let itemdata = {};

    let data = new Array(row.length);
    for (let i = 0; i < row.length; i++) {
        itemdata.code = row[i].children[0].value;
        itemdata.sort1 = row[i].children[1].value;
        itemdata.sort2 = row[i].children[2].value;
        itemdata.sort3 = row[i].children[3].value;
        itemdata.brand = row[i].children[4].value;
        itemdata.name = row[i].children[5].value;
        itemdata.weight = row[i].children[6].value;
        itemdata.storage = row[i].children[7].value;
        itemdata.packing = row[i].children[8].value;
        itemdata.delivery = row[i].children[9].value;
        itemdata.price = row[i].children[10].value;
        itemdata.vat = row[i].children[11].value;
        itemdata.origin = row[i].children[12].value;
        itemdata.stock = row[i].children[13].value;
        itemdata.admin = row[i].children[14].value;
        data[i] = itemdata;
    }

    let response = await axios.post(uri, null, data);
}




async function movePage(event) {
    let keyword = "";
    let sorttype = "salesD";
    let currPage = event.target.value;
    let uri = `item/search?keyword=${keyword}&sorttype=${sorttype}&currPage=${currPage}`;

    let data = await axios.get(uri);

}
async function movePage2() {
    let keyword = "프레시지";
    let sorttype = "salesD";
    let currPage = 1;
    let uri = `item/search?keyword=${keyword}&sortType=${sorttype}&currPage=${currPage}`;
    console.log(uri);
    let data = await axios.get(uri);

}