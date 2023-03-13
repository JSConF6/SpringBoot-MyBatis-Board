$(function() {
    // 회원가입 실행
    $("#signupForm").on("submit", function(event) {
        const username = $("#username").val();
        const password = $("#password").val();
        const name = $("#name").val();
        const email = $("#email").val();

        inputValidation("username", username, event);
        inputValidation("password", password, event);
        inputValidation("name", name, event);
        inputValidation("email", email, event);
    })

    function inputValidation(name, value, e) {
        if (value === "") {
            $(`#${name}`).addClass("is-invalid");
            e.preventDefault();
            return;
        } else {
            $(`#${name}`).removeClass("is-invalid");
            $(`#${name}`).addClass("is-valid");
        }
    }
});