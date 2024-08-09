document.querySelector("#test").style.display ="none";

function clickBtn1() {
	const p1 = document.querySelector("#test");

	if (p1.style.display=="block") {
		// noneで非表示
		p1.style.display ="none";
	} else {
		// blockで表示
		p1.style.display ="block";
	}
}