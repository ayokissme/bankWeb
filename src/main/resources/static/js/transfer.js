$('.valid-input').keypress(function (e) {
    var charCode = (e.which) ? e.which : event.keyCode
    if (String.fromCharCode(charCode).match(/[^0-9]/g)) {
        return false;
    }
});

$('#amount').on('input', function () {
    let validAmountMsg = document.getElementById("validAmount");
    let submitBtn = document.querySelector("#submitBtn");
    if ($(this).val() > balance || $(this).val() === "") {
        validAmountMsg.style.display = "block";
        $(this)[0].style.borderColor = "#dc3545";
        submitBtn.disabled = true;
    } else {
        validAmountMsg.style.display = "none";
        $(this)[0].style.borderColor = "#198754";
        submitBtn.disabled = false;
    }
});