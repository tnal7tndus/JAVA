

let filterCheckedList = [];



function checkList() {
    console.log("--- checkC ---")
    for (let e of filterCheckedList) {
        console.log(e);
    }
}



function showList(event) {
    let target = event.target.closest('li');
    if (target.classList.contains('opened')) {
        target.classList.remove('opened');
    } else {
        target.classList.add('opened');
    }
}

function checkSort(event) {
    let target = event.target.closest('li');
    let check = false;

    if (target.classList.contains('selected')) {
        target.classList.remove('selected');

        for (let e of target.closest('.opened').getElementsByTagName('li')) {
            if (e.classList.contains('selected')) {
                check = true;
                break;
            }
        }
        if (!check) target.closest('.opened').classList.remove('selected');

        filterCheckedList = filterCheckedList.filter((e) => e !== target.innerText);
    } else {
        target.closest('.opened').classList.add('selected');
        target.classList.add('selected');
        if (!filterCheckedList.includes(target.innerText))
            filterCheckedList.push(target.innerText);
    }
    if (filterCheckedList.length > 0 && filterCheckedList[0] != '') {
        target.closest('.sortB').classList.add('selected');
    } else {
        target.closest('.sortB').classList.remove('selected');
    }
    event.stopPropagation();
    checkList();
}

function checkALL(event) {
    let target = event.target.closest('li');

    if (target.classList.contains('selected')) {
        target.classList.remove('selected');
        for (let e of target.getElementsByTagName('li')) {
            e.classList.remove('selected');
            filterCheckedList = filterCheckedList.filter((ele) =>
                ele !== e.innerText
            );
        }
    } else {
        target.classList.add('opened');
        target.classList.add('selected');
        target.closest('.sortB').classList.add('selected');
        for (let e of target.getElementsByTagName('li')) {
            e.classList.add('selected');
            if (!filterCheckedList.includes(e.innerText))
               ilterCheckedList.push(e.innerText);
        }
    }
    event.stopPropagation();
    checkList();
}

function  fselfCheck() {}

