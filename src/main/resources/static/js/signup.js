$(function() {
    // 회원가입 실행
    $("#signupForm").on("submit", function(e) {
        const username = $("#username").val();
        const password = $("#password").val();
        const name = $("#name").val();
        const email = $("#email").val();

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

        if (name === "") {
            alert("이름을 입력해주세요");
            e.preventDefault();
            return;
        }

        if (email === "") {
            alert("이메일을 입력해주세요");
            e.preventDefault();
            return;
        }
    })
});