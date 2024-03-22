'use strict'

function a(str) {
    console.log(str)
}

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
    } else {
        itemIntro.classList.add('heightAuto');
        introItemBtn.innerHTML = `상품정보 접기<i class="fa-solid fa-chevron-up"></i>`;
        //ele.innerHTML = `상품정보 접기<i class="fa-solid fa-chevron-up"></i>`;
    }
    return null;
}


function reviewDetailClick(event) {
    event.stopPropagation();
    const reviewDetailForm = document.getElementById('reviewDetailForm');
    reviewDetailForm.style.display = 'flex';
}

function reivewDetailImgChange(ele) {
    ele.parentNode.previousElementSibling.children[0].setAttribute('src', ele.src);
}

let imgList = document.getElementById('reviewDetailImgBottom');
let imgLength = imgList.length;

// function returnImg(event) {
    //     let returnImg = (imgList + imgLength - 1) % imgLength;
    //     event.
    // }
    
    
    // function nextImg(event) {
        //     let nextImg = (imgList + 1) % imgLength;
        
// }

function reviewDetailClose(ele) {
    ele.closest('#reviewDetailForm').style.display = 'none';
}


function itemAskClick(event) {
    event.stopPropagation();
    const reviewDetailForm = document.getElementById('itemAskForm');
    reviewDetailForm.style.display = 'flex';
}

function itemAskBoxClose(ele) {
    ele.closest('#itemAskForm').style.display = 'none';
}

function reviewWriteClick(event) {
    const reviewDetailForm = document.getElementById('reviewWriteForm');
    reviewDetailForm.style.display = 'flex';
}

function reviewWriteBoxClose(ele) {
    ele.closest('#reviewWriteForm').style.display = 'none';
}