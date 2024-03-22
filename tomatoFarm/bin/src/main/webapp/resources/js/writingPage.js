'use strict'

const submitBt = document.getElementById('writeBtn');

function otherCategory(ele) {
    ele.style.boxShadow = '0px 0px 3px 3px #9B1B20';
    ele.style.fontWeight = 'bold';
    for (let i = 0; i < ele.parentNode.childElementCount; i++) {
        if (ele.parentNode.children[i] != ele) {
            ele.parentNode.children[i].style.boxShadow = '0px 0px 0px 0px white';
            ele.parentNode.children[i].style.fontWeight = 'initial';
        }
    }
}

function selectCategory(ele) {
    if (ele.value == 'announce' || ele.value == 'choice') {
        submitBt.disabled = true;
        submitBt.style.backgroundColor = 'grey';
    } else {
        submitBt.disabled = false;
        submitBt.style.backgroundColor = '#9B1B20';
    }

    for (let i = 1; i < ele.childElementCount; i++) {
        if (ele.value == ele.parentNode.children[i].name) {
            ele.parentNode.children[i].style.display = 'initial';
        } else {
            ele.parentNode.children[i].style.display = 'none';
        }
    }
}