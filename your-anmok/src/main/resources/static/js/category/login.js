let index = {
    init: function () {
        $("#makeMyCategory").on("click", () => {

            event.preventDefault();


            if ($(".isLogin").val() == "false") {
                confirmAlertLogin("이 서비스는 로그인을 하셔야 사용 가능합니다. 로그인을 하시겠습니까?").then((result) => {
                    if (result == true) {
                        location.href = "https://kauth.kakao.com/oauth/authorize?client_id=84bb53ddc4e742b0c6aa6c06a6372dbc&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code";
                    } else {
                        swal.close();
                    }
                });
            } else {
                location.href = '/category/add';
            }


        });
        $("#addPlaceinCategory").on("click", () => {

            var categoryId = $("#searchMapButtonByCategoryId").val();

            if ($(".isLogin").val() == "false") {
                confirmAlertLogin("이 서비스는 로그인을 하셔야 사용 가능합니다. 로그인을 하시겠습니까?").then((result) => {
                    if (result == true) {
                        location.href = "https://kauth.kakao.com/oauth/authorize?client_id=84bb53ddc4e742b0c6aa6c06a6372dbc&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code";
                    } else {
                        swal.close();
                    }
                });
            } else {
                location.href = "/searchMap?categoryId=" + categoryId;
            }

        });
    },

};

const confirmAlertLogin = function (text) {

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