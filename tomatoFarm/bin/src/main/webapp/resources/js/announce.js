'use strict'

function a(str) {
    console.log(str)
}

function changePage(ele) {
    let chess = document.getElementById('tomatoChess');
    a(ele.getAttribute('value'));
    switch (ele.getAttribute('value')) {
        case '1': {
            chess.style.transform = 'translateX(0)';
            chess.style.transitionDuration = '1s';
        } break;
        case '2': {
            chess.style.transform = 'translateX(55px)';
            chess.style.transitionDuration = '1s';
        } break;
        case '3': {
            chess.style.transform = 'translateX(110px)';
            chess.style.transitionDuration = '1s';
        } break;
        case '4': {
            chess.style.transform = 'translateX(165px)';
            chess.style.transitionDuration = '1s';
        } break;
        case '5': {
            chess.style.transform = 'translateX(220px)';
            chess.style.transitionDuration = '1s';
        } break;
        default: {
            for (let i = 1; i < ele.parentNode.childElementCount - 1; i++) {
                if (ele.parentNode.children[i].getAttribute('value') != ele.getAttribute('value')) {
                    ele.parentNode.children[i].innerText = ele.ele.parentNode.children[i].getAttribute('value');
                }
            }
        }
    }
}

