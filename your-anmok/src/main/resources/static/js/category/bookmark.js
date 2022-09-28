
let index = {
    init: function () {
        $(".add-category-btn").on("click", () => {

            if ($.trim($(".tag-name").val()) == '') {
                alert("카테고리 명을 입력해주세요");
            } else {
                if (confirm("\"" + $(".tag-name").val() + "\"를 추가하시겠습니까?")) {
                    this.add();
                }
            }
        });
    },

    add: function () {

        var data = {
            tagName: $(".tag-name").val()
        };

        let numberOfElements = $(".number-of-elements").val() % 9;
        let lastPage;

        if (numberOfElements == 0) {
            lastPage = $(".last-page").val();
        } else {
            lastPage = $(".last-page").val() - 1;
        }

        $.ajax({
            url: "/category/add",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success: function (result) {

                alert("\" " + data.tagName + " \" 카테고리가 추가되었습니다.");

                location.href = "/category/home?page=" + lastPage + "&keyword=";
            },
            error : function (error) {

                // console.log(JSON.stringify(error));

                if(error.status == 409) {
                    alert("동일한 이름의 카테고리명이 존재합니다.");
                } else {
                    alert("카테고리가 정상적으로 추가되지 않았습니다. 다시 시도해주세요.");
                }

            }
        })

    }
};

index.init();