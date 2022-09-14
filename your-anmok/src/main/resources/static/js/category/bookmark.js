
let index = {
    init: function () {
        $(".add-category-btn").on("click", () => {
            this.add();
        });
    },

    add: function () {

        var data = {
            tagName: $(".tag-name").val()
        };

        $.ajax({
            url: "/category/add",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                alert("\" " + data.tagName + " \" 카테고리가 추가되었습니다.");

                location.href = "/category/home";
            },
            error : function (error) {

                alert(JSON.stringify(error));
                console.log(JSON.stringify(error));
                alert("카테고리가 정상적으로 추가되지 않았습니다. 다시 시도해주세요.");
            }
        })

    }
};

index.init();