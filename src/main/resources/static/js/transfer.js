function getSelectedCardValues(cards, select) {
    const cardNum = select.value;
    for (let card of cards) {
        if (cardNum == card['cardNumber']) {
            return card;
        }
    }
}

function changeBlock(removeId, showId) {
    const container = document.getElementById(showId);
    container.style.display = "block";
    const spinner = document.getElementById(removeId);
    spinner.style.display = "none";
}

function addOptions(cards, select) {
    for (let card of cards) {
        let opt = document.createElement('option');
        opt.value = card['cardNumber'];
        let innerHtml = `${card['cardNumber']}`;
        if (select.id === "transferMethod") {
            innerHtml += `, <b>Balance:</b> <span>${card['balance']}</span>$`
        }
        opt.innerHTML = innerHtml;
        select.appendChild(opt);
    }
}

function displayDangerCard(dangerCard, response) {
    dangerCard.style.display = "block";
    dangerCard.innerText = response.responseText;
}

function checkAmountInput(ajaxThis, cards, select) {
    let validAmountMsg = document.getElementById("validAmount");
    let submitBtn = document.querySelector("#submitBtn");
    let option = getSelectedCardValues(cards, select);
    if (ajaxThis.val() > option['balance'] || ajaxThis.val() === "") {
        validAmountMsg.style.display = "block";
        ajaxThis[0].style.borderColor = "#dc3545";
        submitBtn.disabled = true;
    } else {
        validAmountMsg.style.display = "none";
        ajaxThis[0].style.borderColor = "#198754";
        submitBtn.disabled = false;
    }
}

function hasErrorsCard(amount, senderAcc, recipientCard, securityCode) {
    return amount > senderAcc['balance']
        || amount === ""
        || amount === 0
        || recipientCard.length !== 16
        || securityCode.length !== 3
}

function hasErrorsPhone(amount, senderAcc, recipientPhoneNumber, securityCode) {
    return amount > senderAcc['balance']
        || amount === ""
        || amount === 0
        || recipientPhoneNumber.length < 9
        || recipientPhoneNumber.length > 12
        || securityCode.length !== 3
}

function sendAjaxRequest(data, url) {
    $.ajax({
        type: "PATCH",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 600000,
        headers: {'X-CSRF-TOKEN': csrfToken},
        statusCode: {
            200: function (response) {
                console.log(response)
                let successCard = document.getElementById('successCard');
                let row = document.getElementById('row');
                successCard.style.display = "block";
                row.style.display = "none";
            },
            400: function (response) {
                displayDangerCard(dangerCard, response);
            },
            403: function (response) {
                displayDangerCard(dangerCard, response);
            },
            404: function (response) {
                displayDangerCard(dangerCard, response);
            },
            409: function (response) {
                displayDangerCard(dangerCard, response);
            },
            412: function (response) {
                displayDangerCard(dangerCard, response);
            },
        },
    });
}