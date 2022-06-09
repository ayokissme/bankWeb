const collection = document.getElementsByClassName("card-body");

for (let e of collection) {
    let cardNumb = e.getElementsByClassName("card-number")[0]
    let cardFormatStr = "";
    for (let i = 0; i < 16; i++) {
        if (i % 4 === 0) {
            cardFormatStr += " " + cardNumb.textContent[i];
        } else {
            cardFormatStr += cardNumb.textContent[i];
        }
    }
    cardNumb.textContent = cardFormatStr;

    let phoneNumb = e.getElementsByClassName("card-telephone")[0]
    let textContent = phoneNumb.textContent
    phoneNumb.textContent = `${textContent.substring(0, 2)} (${textContent.substring(2, 5)})-${textContent.substring(5, 8)}-${textContent.substring(8, textContent.length)}`
}

const csrfToken = $("meta[name='_csrf']").attr("content");

$(".accept_form").click(function() {
    let cardId = $(this).parent().serializeArray()[0].value;
    $.ajax({
        type: 'PATCH',
        url: "/api/private/account/accept",
        headers: {'X-CSRF-TOKEN': csrfToken},
        data: $(this).parent().serialize(),
        statusCode: {
            200: function(response) {
                $( "#" + cardId ).remove();
                alert(response);
            },
        },
    });
    return false;
});

$(".reject_form").click(function() {
    let cardId = $(this).parent().serializeArray()[0].value;
    $.ajax({
        type: 'DELETE',
        url: "/api/private/account/reject",
        headers: {'X-CSRF-TOKEN': csrfToken},
        data: $(this).parent().serialize(),
        statusCode: {
            200: function(response) {
                $( "#" + cardId ).remove();
                alert(response);
            },
        },
    });
    return false;
});