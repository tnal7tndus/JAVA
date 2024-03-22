'use strict'

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
    }).catch(err => {

    })

}