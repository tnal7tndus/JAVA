let row = document.getElementsByClassName('excelColumn');
let excelBox = document.getElementById('excelBox');




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