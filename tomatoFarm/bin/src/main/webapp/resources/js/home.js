

const slideBox = document.getElementsByClassName("slideBox");
let secondSlideBtn;
const adImgBox = document.getElementById('adImgBox');
const canvas = adImgBox.querySelector('canvas');

const adImgList = ['fresheasy.jpg', 'mychef.jpg', 'signup.jpg', 'review.jpg']

window.onload(writeSlideContainer());

function changeAdImgBox(ele, event) {
    event.stopPropagation();
    let index = 0;
    for (let e of ele.closest('#adRightTab').children) {
        if (e == ele) break;
        index++;
    }
    ele.closest('#adImg').children[0].src = `/tomatoFarmA/resources/img/adimg/${adImgList[index]}`;
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

function writeSlideContainer() {
    let uri = "/item/eventitem";
    let result;

    axios.get(uri
    ).then(response => {
        const secondContainer = document.getElementById('secondContainer')
        result = `
            <h3><i class="fa-solid fa-gift"></i> &nbsp;&nbsp;특가 상품&nbsp;&nbsp; <i class="fa-solid fa-gift"></i></h3>
            <div id="secondContainerList">
            <div class="slideBox">
        `;

        for (const e of response.data) {
            result += `
             <div class="itemBox">
                    <div class="itemImg">
                        <i class="fa-solid fa-cart-shopping"></i>
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <img src="/resources/img/itemImg/${e.code}_1.jpg" alt="${e.name}">
                    <div>aasdfasdf</div>
                    </div>
                    <div class="itemName">${e.name}</div>
                    <div class="itemInfo">${e.brand}<br></div>
                    <p class="itemPrice">${e.price}원</p>
                    <div class="itemOption">${e.delivery}원</div>
                </div>
            `;
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
            <div id="secondContainerLeftBtn" onclick="secondContainerSlideLeftbth(event)"><i
                    class="fa-sharp fa-solid fa-arrow-left"></i></div>
            <div id="secondContainerRightBtn" onclick="secondContainerSlideRightbth(event)"><i
                    class="fa-sharp fa-solid fa-arrow-right"></i></div>
        ` ;
        secondContainer.innerHTML = result;
        secondSlideBtn = document.getElementById("secondSlideBtn");
    }).catch(err => {

    })

}