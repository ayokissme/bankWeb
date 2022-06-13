const csrfToken = $("meta[name='_csrf']").attr("content");
let select = document.getElementById('transferMethod');
let recipientCard = document.getElementById('recipientCard');
const dangerCard = document.getElementById('dangerCard');
url = "/api/public/account/get"
urlPhone = "/api/public/account/get/phone/"

$('#nextBtn').on('click', function () {
    let phoneInput = document.getElementById('phoneInput').value;
    const dangerCardPhone = document.getElementById('dangerCardPhone');
    if (9 < phoneInput.length < 11) {
        let phoneCode = document.getElementById('phoneCode').value;
        let phone = phoneCode + phoneInput;
        fetch(urlPhone + phone)
            .then(res => res.json())
            .then(accounts => {
                if (typeof accounts[0] === "string") {
                    dangerCardPhone.style.display = "block";
                    dangerCardPhone.innerText = accounts[0];
                } else {
                    changeBlock("phoneContainer", "spinner")
                    fetchCard(accounts);
                }
            })
    } else {
        dangerCardPhone.innerText = "Incorrect phone number";
        dangerCardPhone.style.display = "block";
    }
})

function fetchCard(recipientAccounts) {
    fetch(url)
        .then(res => res.json())
        .then(cards => {
            changeBlock("spinner", "mainContainer");
            addOptions(cards, select);
            addOptions(recipientAccounts, recipientCard);

            $('.valid-input').keypress(function (e) {
                var charCode = (e.which) ? e.which : event.keyCode
                if (String.fromCharCode(charCode).match(/[^0-9]/g)) {
                    return false;
                }
            });

            $('#amountInput').on('input', function () {
                checkAmountInput($(this), cards, select);
            });

            $('#submitBtn').on('click', function () {
                let recipientAccount = document.getElementById('recipientCard').value;
                let amount = document.getElementById("amountInput").value;
                let securityCode = document.getElementById("securityCode").value;
                const senderAcc = getSelectedCardValues(cards, select);
                if (hasErrorsCard(amount, senderAcc, recipientAccount, securityCode)) {
                    dangerCard.style.display = "block";
                    dangerCard.innerText = "Data entry error";
                    return false;
                }
                let data = {}
                data['transferAmount'] = parseFloat(amount);
                data['message'] = document.getElementById("message").value;
                data['recipientAccount'] = Number(recipientAccount);
                data['senderAccount'] = Number(senderAcc['cardNumber']);
                data['securityCode'] = Number(securityCode);
                sendAjaxRequest(data, "/api/public/account/transfer/phone");
                return false;
            });

        });
}