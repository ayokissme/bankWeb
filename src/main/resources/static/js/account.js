const csrfToken = $("meta[name='_csrf']").attr("content");

$('#sendRequest').submit(function () {
    $.ajax({
        type: 'POST',
        url: "/api/public/account/create",
        headers: {'X-CSRF-TOKEN': csrfToken},
        statusCode: {
            200: function(response) {
                const tagSuccess = '<div class="alert alert-success" role="alert">' + response + '</div>';
                $("form#sendRequest").replaceWith(tagSuccess);
            },
            208: function(response) {
                let tagDanger = '<div class="alert alert-danger" role="alert">' + response + '</div>'
                $(tagDanger).insertBefore("#submitBtn");
            },
        },
    });
    return false;
});