
function a(str) {
	console.log(str);
}



const firstCategory = document.getElementById("firstCategory");
const searchBoxInput = document.getElementById("searchBoxInput");

function firstCategoryVisible() {
	firstCategory.style.display = "block";
}

function firstCategoryHidden() {
	firstCategory.style.display = "none";
}

function resetInputBox(ele) {
	searchBoxInput.value = '';
	searchBoxInput.focus();
	ele.closest("form").children[1].style.visibility = "hidden"
}

function appearinputBoxResetButton(ele) {
	ele.closest("form").children[1].style.visibility = "visible"
}

function resetInputBox2(ele) {
	ele.closest("div").children[0].value = '';
	ele.closest("div").children[0].focus();
	ele.closest("div").children[1].style.visibility = "hidden";
	seachCategory(ele.closest("div").children[0])
}

function appearinputBoxResetButton2(ele) {
	ele.closest("div").children[1].style.visibility = "visible"
}

function sperateKorWord(str) {
	const kor_starts = ["ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"];
	const kor_middles = ["ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"];
	const kor_ends = ["", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"];

	const unicode_kor_start_num = 44032;
	const unicode_kor_end_num = 55203;

	const unicodeNum = str.charCodeAt(0);

	if (unicodeNum < unicode_kor_start_num || unicodeNum > unicode_kor_end_num) {
		return str;
	}

	// const kor_starts_index
}

function seachCategory(ele) {
	let key = ele.value;
	let liBox = ele.closest('ul').children;
	for (let i = 2; i < liBox.length; i++) {
		if (!liBox[i].innerText.includes(key)) {
			liBox[i].style.display = "none";
		} else {
			liBox[i].style.display = "block";
		}
	}
}
