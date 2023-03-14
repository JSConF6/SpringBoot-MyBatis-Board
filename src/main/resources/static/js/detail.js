$(function() {
    // 글 삭제 시
    $("#delBtn").on("click", function(e) {
        const boardId = $("#boardId").val();

        if(confirm("정말 삭제하시겠습니까?")) {
            location.href = `/board/delete?id=${boardId}`;
        }
    });
});