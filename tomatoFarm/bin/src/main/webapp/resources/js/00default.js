'use strict'

let hides = document.getElementsByClassName("hide");

// hide
window.addEventListener("scroll", () => {
    for (let e of hides) {
        if (e.getBoundingClientRect().top < 100 + window.innerHeight) {
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