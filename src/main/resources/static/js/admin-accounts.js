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