const collection = document.getElementsByClassName("card-body");

for (let e of collection) {
    let cardNums = e.getElementsByClassName("card-number")
    for (let cardNum of cardNums) {
        let cardFormatStr = "";
        for (let i = 0; i < 16; i++) {
            if (i % 4 === 0) {
                cardFormatStr += " " + cardNum.textContent[i];
            } else {
                cardFormatStr += cardNum.textContent[i];
            }
        }
        cardNum.textContent = cardFormatStr;
    }
}