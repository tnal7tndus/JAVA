'use strict'



function count(event, type) {
    let target = event.target.closest("#itemCount");
    let value = target.children[1].value;
    

    if ("-" === type) {
        if (value > 1)
            value--;

    }
    else
        value++;

    target.children[1].value = value;
    sumTotal(value);
}

function sumTotal(value) {
    const price = document.getElementsByClassName('price').innerText.replace('원', '');
    const priceBox = document.getElementsByClassName('sumPrice');
    sumPrice.innerText = `${value * price} 원`;
}

function inputCount(event) {
    const inputBox = document.getElementById('inputCount');
    const countBox = document.getElementsByClassName('sumPrice');
    countBox.innerText = inputBox;
}

function delCheckItem() {
    document.querySelectorAll("input[name=buy]:checked").forEach(function (item) {
        
    })
}


// function sumTotal(value) {
//     const price = document.getElementById('price');
//     const putCnt = document.getElementById('inputCount');
//     const sumPrice = document.getElementById('sumPrice');
//     const total = price * putCnt;

//     total = 0;
//     price.forEach(function (value) {
//         total += value;
//         total.innerText = `${total}원`
//     })
// }





// function finalPrice() {
//     let sumPrice = 0;
//     c = document.getElementsByClassName("check");
//     for (i = 0; i < c.length; i++){
//         if (c[i].checked == true) {
//             sum += parseInt(c[i].value);
//         }
//     }
//     document.getElementById("price").value = sumPrice;
//     sumPrice = 0;

// }