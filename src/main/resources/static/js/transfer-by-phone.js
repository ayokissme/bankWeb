const csrfToken = $("meta[name='_csrf']").attr("content");
let select = document.getElementById('transferMethod');
const dangerCard = document.getElementById('dangerCard');
url = "/api/public/account/get"

fetch(url)
    .then(res => res.json())
    .then(cards => {
        removeSpinner();
        addOptions(cards, select);

        $('.valid-input').keypress(function (e) {
            var charCode = (e.which) ? e.which : event.keyCode
            if (String.fromCharCode(charCode).match(/[^0-9]/g)) {
                return false;
            }
        });

        $('#amountInput').on('input', function () {
            checkAmountInput($(this), cards);
        });

        $('#submitBtn').on('click', function () {
            let recipientPhoneNumber = document.getElementById('recipientPhoneNumber').value;
            let amount = document.getElementById("amountInput").value;
            let securityCode = document.getElementById("securityCode").value;
            const senderAcc = getSelectedCardValues(cards, select);
            let phoneCode = document.getElementById('phoneCode').value;
            if (hasErrorsPhone(amount, senderAcc, recipientPhoneNumber, securityCode)) {
                dangerCard.style.display = "block";
                dangerCard.innerText = "Data entry error";
                return false;
            }
            let data = {}
            let phone = phoneCode + recipientPhoneNumber;
            data['transferAmount'] = parseFloat(amount);
            data['message'] = document.getElementById("message").value;
            data['recipientPhoneNumber'] = phone;
            data['senderAccount'] = Number(senderAcc['cardNumber']);
            data['securityCode'] = Number(securityCode);
            sendAjaxRequest(data, "/api/public/account/transfer/phone");
            return false;
        });

    });