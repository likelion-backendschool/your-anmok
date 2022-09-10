
let index = {
    init: function () {
        $(".remove-btn").on("click", () => {
            this.remove();
        });
    },

    remove: function () {

        let categoryId = $(".category_id").val()

        $.ajax({
            url: "/bookmark/removeByMypage",
            type: "POST",
            data: categoryId,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {
                alert("해당 카테고리가 즐겨찾기 해제되었습니다.");

                location.href = "/mypage";
            },
            error: function (error) {
                console.log(error);
                alert("해당 요청이 바르게 수행되지 않았습니다.");
            }
        })

    }
};

index.init();