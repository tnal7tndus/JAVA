'use strict'

const hides = document.getElementsByClassName("hide");



// hide
window.addEventListener("scroll", () => {
    for (let e of hides) {
        if (e.getBoundingClientRect().top < 0 + window.innerHeight) {
            e.style.transition = "0.8s";
            e.classList.remove('hide');
            e.style.visibility = "visible";
            e.style.marginTop = "30px";
        }
    }
});

// appear
function showContent(ele) {
    if (ele.classList.contains('appear')) {
        ele.classList.remove('appear');
    } else {
        if (ele.closest('.appearContainer').getElementsByClassName('appear')[0]) {
            ele.closest('.appearContainer').getElementsByClassName('appear')[0].classList.remove('appear');
        }
        ele.classList.add('appear');
    }
}

// 쉼표 찍기
function makeComa() {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}