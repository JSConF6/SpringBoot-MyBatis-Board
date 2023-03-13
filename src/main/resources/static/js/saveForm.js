$(function() {
    $("#fileUploadBtn").on("click", function() {
       $("#file").click();
    });

    // 글 작성 submit
    $("#saveForm").on("submit", function(e) {
        const title = $("#title").val();
        const content = $("#content").val();

        if (title === "") {
           alert("제목을 입력해주세요");
           e.preventDefault();
           return;
        }

        if (content === "") {
            alert("내용을 입력해주세요");
            e.preventDefault();
            return;
        }
    });

    // 제목 입력시 validation class 제거
    $("#title").on("change", function() {
       $(this).removeClass("is-invalid");
    });

    // 내용 입력시 validation class 제거
    $("#content").on("change", function() {
        $(this).removeClass("is-invalid");
    });

    // 파일 input change 될때
    $("#file").on("change", function() {
        const fileName = $(this)[0].files[0].name;
        $("#fileName").text(fileName);
        $("#fileRemoveBtn").removeClass("hide");
        $("#fileRemoveBtn").addClass("show");
    });

    // 파일 삭제시
    $("#fileRemoveBtn").on("click", function() {
       $("#file").val("");
       $("#fileName").text("");
       $("#fileRemoveBtn").removeClass("show");
       $("#fileRemoveBtn").addClass("hide");
    });
});