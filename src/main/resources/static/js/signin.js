$(function() {
    // 로그인 실행 시 유효성 검사
    $("#signinForm").on("submit", function(e) {
        const username = $("#username").val();
        const password = $("#password").val();

        if (username === "") {
           alert("아이디를 입력해주세요");
            e.preventDefault();
           return;
        }

        if (password === "") {
            alert("비밀번호를 입력해주세요");
            e.preventDefault();
            return;
        }
    })
});