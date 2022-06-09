const phoneCodes = {
    "RUSSIA": "+7",
    "USA": "+1",
    "GERMANY": "+49",
    "BELARUS": "+375",
    "FRANCE": "+33",
}

function setPhoneNumber() {
    let e = document.getElementById("country");
    let country = e.value;
    document.getElementById("phoneNumber").value = phoneCodes[country];
}

// $('#phoneNumber').inputmask('+9 (999)-999-9999',{placeholder:'+_ (___)-___-____'})
