let index = {
    init: function () {
        $("#makeMyCategory").on("click", () => {

            if ($(".isLogin").val() == "false") {
                if (confirm("이 서비스는 로그인을 하셔야 사용 가능합니다. 로그인을 하시겠습니까?")) {
                    location.href = "https://kauth.kakao.com/oauth/authorize?client_id=84bb53ddc4e742b0c6aa6c06a6372dbc&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code";
                }
            } else {
                location.href = '/category/add';
            }


        });
        $("#addPlaceinCategory").on("click", () => {

            var categoryId = $("#searchMapButtonByCategoryId").val();

            if ($(".isLogin").val() == "false") {
                if (confirm("이 서비스는 로그인을 하셔야 사용 가능합니다. 로그인을 하시겠습니까?")) {
                    location.href = "https://kauth.kakao.com/oauth/authorize?client_id=84bb53ddc4e742b0c6aa6c06a6372dbc&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code";
                }
            } else {
                location.href = "/searchMap?categoryId=" + categoryId;
            }

        });
    },

};

index.init();