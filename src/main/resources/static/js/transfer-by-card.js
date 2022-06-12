const csrfToken = $("meta[name='_csrf']").attr("content");
let select = document.getElementById('transferMethod');
url = "/api/public/account/get"

fetch(url)
    .then(res => res.json())
    .then(cards => {

        function getSelectedCardValues() {
            const cardNum = select.value;
            for (let card of cards) {
                if (cardNum == card['cardNumber']) {
                    return card;
                }
            }
        }

        let container = document.getElementById("mainContainer");
        container.style.display = "block";

        for (let card of cards) {
            let opt = document.createElement('option');
            opt.value = card['cardNumber'];
            opt.innerHTML = `${card['cardNumber']}, <b>Balance:</b> <span>${card['balance']}</span>$`;
            select.appendChild(opt);
        }

        $('.valid-input').keypress(function (e) {
            var charCode = (e.which) ? e.which : event.keyCode
            if (String.fromCharCode(charCode).match(/[^0-9]/g)) {
                return false;
            }
        });

        $('#amountInput').on('input', function () {
            let recipientCard = document.getElementById('recipientCardNumber').value;
            let validAmountMsg = document.getElementById("validAmount");
            let submitBtn = document.querySelector("#submitBtn");
            let option = getSelectedCardValues();
            if ($(this).val() > option['balance'] || $(this).val() === "" || recipientCard.length !== 16) {
                validAmountMsg.style.display = "block";
                $(this)[0].style.borderColor = "#dc3545";
                submitBtn.disabled = true;
            } else {
                validAmountMsg.style.display = "none";
                $(this)[0].style.borderColor = "#198754";
                submitBtn.disabled = false;
            }
        });

        $('#submitBtn').on('click', function () {
            let recipientCard = document.getElementById('recipientCardNumber').value;
            let amount = document.getElementById("amountInput").value;
            const senderAcc = getSelectedCardValues();
            if (amount > senderAcc['balance']
                && amount !== ""
                && amount > 0
                && recipientCard.length === 16) {
                alert("The data is specified incorrectly");
                return false;
            }
            let data = {}
            data['transferAmount'] = parseFloat(amount);
            data['message'] = '';
            data['recipientAccount'] = Number(recipientCard);
            data['senderAccount'] = Number(senderAcc['cardNumber']);

            $.ajax({
                type: "PATCH",
                contentType: "application/json",
                url: "/api/public/account/transfer/card-to-card",
                data: JSON.stringify(data),
                dataType: 'json',
                timeout: 600000,
                headers: {'X-CSRF-TOKEN': csrfToken},
                statusCode: {
                    200: function (response) {
                        const tagSuccess = '<div class="alert alert-success" role="alert">' + response + '</div>';
                        $("form#sendRequest").replaceWith(tagSuccess);
                        alert(response)
                    },
                    400: function (response) {
                        alert(response.responseText);
                    },
                    403: function (response) {
                        alert(response.responseText);
                    },
                    404: function (response) {
                        alert(response.responseText);
                    },
                    412: function (response) {
                        alert(response.responseText);
                    },
                },
            });

            // $.ajax({
            //     type: 'PATCH',
            //     url: '/api/public/account/transfer/card-to-card',
            //     headers: {'X-CSRF-TOKEN': csrfToken},
            //     dataType: "json",
            //     contentType: "application/json",
            //     data: JSON.stringify(cardToCardResponseBody),
            //     statusCode: {
            //         200: function (response) {
            //             const tagSuccess = '<div class="alert alert-success" role="alert">' + response + '</div>';
            //             $("form#sendRequest").replaceWith(tagSuccess);
            //             alert(response)
            //         },
            //         208: function (response) {
            //             let tagDanger = '<div class="alert alert-danger" role="alert">' + response + '</div>'
            //             $(tagDanger).insertBefore("#submitBtn");
            //         },
            //     },
            // });
            return false;
        });

    });