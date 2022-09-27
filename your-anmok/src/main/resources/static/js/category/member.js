let index = {
    init: function () {
        $("#nickname-btn").on("click", () => {

            event.preventDefault();

            var newNickname = $("#nickname").val();

            if ($.trim($("#nickname").val()) == '') {

                alertCustom('닉네임을 입력해주세요');

            } else {

                confirmAlertCustom("닉네임을 " + newNickname + "(으)로 변경하시겠습니까?").then((result) => {
                    if (result == true) {
                        this.changeNickname();
                    } else {
                        swal.close();
                    }
                });
            }

        });
    },

    changeNickname: function () {

        event.preventDefault();

        var data = {
            nickname: $("#nickname").val(),
            id: $("#member-id").val()
        };

        $.ajax({
            url: "/user/modify",
            type: "PUT",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            async: false,
            success: function (result) {

                alertCustomTimer("닉네임이 \"" + data.nickname + "\"(으)로 변경되었습니다.");

                setTimeout(function(){location.href = "/mypage";}, 2000);

            },
            error : function (error) {

                console.log(JSON.stringify(error));

                alertCustom("실행이 정상적으로 완료되지 않았습니다. 다시 시도해주세요.");

                setTimeout(function(){location.href = "/mypage";}, 2000);

            }
        })

    }


};

const alertCustom = function (text) {

    swal({
        text: text,
    }).then(value => {
        if (value) {
            swal.close();
        }
    });

};

const alertCustomTimer = function (text) {

    swal({
        text: text,
        timer: 30000,
    }).then(value => {
        if (value) {
            swal.close();
        }
    });

};

const confirmAlertCustom = function (text) {

    const result = swal({
        text: text,
        buttons: {
            cancel: true,
            confirm: {
                text: 'OK',
                value: 'confirm'
            },
        },
    }).then(value => {
        if (value == 'confirm') {
            return true;
        }
        else {
            return false;
        }
    });

    return result;

};


index.init();