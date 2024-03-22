
function updateTimer() {
    let targetTime = Date.parse("2024/03/09 23:59:59");
    let now = new Date();
    let remainTime = targetTime - now;

    let days = Math.floor(remainTime / (1000 * 60 * 60 * 24));
    let hours = Math.floor(remainTime / (1000 * 60 * 60));
    let mins = Math.floor(remainTime / (1000 * 60));
    let secs = Math.floor(remainTime / 1000);

    let d = days;
    let h = hours - days * 24;
    let m = mins - hours * 60;
    let s = secs - mins * 60;

    let timePlace = document.getElementById('timePlace');
    timePlace.innerHTML = `<div>${d}<span>일</span></div><div>${h}<span>시간</span></div><div>${m}<span>분</span></div><div>${s}<span>초</span></div>`
}
setInterval(updateTimer, 1000);

function rolling() {
    document.querySelector('.prev').classList.remove('prev');
    // ==========================================================
    let current = document.querySelector('.current');
    current.classList.remove('current');
    current.classList.add('prev');
    // ==========================================================

    // ==========================================================
    let next = document.querySelector('.next');
    if (next.nextElementSibling == null) {
        // nextElementSibling 이거 솔직히 무슨의미인지 이해가안돼
        document.querySelector('#keywordListDesign>div>ul>li:first-child').classList.add('next');
    } else {
        next.nextElementSibling.classList.add('next');
    }
    next.classList.remove('next');
    next.classList.add('current');
}
setInterval(rolling, 3000);


let current = document.querySelector('.current');
let keywordListDesign = document.querySelector('#keywordListDesign');
let keywordListDesign2 = document.querySelector('#keywordListDesign>div>ul>li');
let prevK = document.querySelector('.prev');
let nextK = document.querySelector('.next');
let currentK = document.querySelector('.current');

function overTest() {
    keywordListDesign.style.overflow = 'visible';
    keywordListDesign2.style.position = 'unset';

}
function leaveTest() {
    keywordListDesign.style.overflow = 'hidden';
    keywordListDesign2.style.position = 'absolute';
}

current.addEventListener('mouseover', overTest);
current.addEventListener('mouseleave', leaveTest);

// =================================================
let tab = document.querySelectorAll('#eventDetail1_3_Tab>div');
let itemList = document.querySelector('#eventDetail1_3_itemList');
let nextBt = document.querySelector('#nextBt');
let prevBt = document.querySelector('#prevBt');


let prevButton = document.getElementById('prevBt');
let nextButton = document.getElementById('nextBt');


prevButton.addEventListener('click', () => {
    let currentList = document.querySelector('.currentList');
    let prevList = document.querySelector('.prevList');
    let nextList = document.querySelector('.nextList');

    nextList.style.left = '-1220px';
    // ==================================
    currentList.style.position = 'absolute';
    currentList.style.right = '-1220px';
    // ==================================
    prevList.style.position = 'initial';

    nextList.className = 'brandItemList prevList';
    currentList.className = 'brandItemList nextList'
    prevList.className = 'brandItemList currentList';
});


nextBt.addEventListener('click', () => {
    let currentList = document.querySelector('.currentList');
    let prevList = document.querySelector('.prevList');
    let nextList = document.querySelector('.nextList');

    nextList.style.position = 'initial'
    // ==================================
    currentList.style.position = 'absolute';
    currentList.style.left = '-1220px';
    // ==================================
    prevList.style.position = 'apsolute';
    currentList.style.right = '-1220px';

    currentList.className = 'brandItemList prevList'
    prevList.className = 'brandItemList nextList';
    nextList.className = 'brandItemList currentList';
});