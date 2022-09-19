
let index = {
    init: function () {
        $("#nickname-btn").on("click", () => {

            var newNickname = $("#nickname").val();

            if ($.trim($("#nickname").val()) == '') {
                alert("닉네임을 입력해주세요");
                location.href = "/mypage";

            } else {
                if(confirm("닉네임을 " + newNickname + "(으)로 변경하시겠습니까?") == true) {
                    this.changeNickname();
                }
                location.href = "/mypage";

            }

        });
    },

    changeNickname: function () {

        var data = {
            nickname: $("#nickname").val(),
            id: $("#member-id").val()
        };

        $.ajax({
            url: "/user/modify",
            type: "PUT",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success: function (result) {

                alert("닉네임이 \"" + data.nickname + "\"(으)로 변경되었습니다.");

                location.href = "/mypage";
            },
            error : function (error) {

                console.log(JSON.stringify(error));

                alert("실행이 정상적으로 완료되지 않았습니다. 다시 시도해주세요.")

            }
        })

    }
};

index.init();