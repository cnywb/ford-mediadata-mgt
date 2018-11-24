/**
 * Created by ziv.hung on 16/1/11.
 */
var loginApp = angular.module('loginApp', ['toaster','ngAnimate']);
loginApp.controller("loginController", function($scope, $http, $window,toaster) {
    $scope.data = {
    		userName:'',
    		password:'',
    		validCode:''
    };




    $scope.submitDbForm = function (isValid) {

        $scope.submited = true;

        var userName = $scope.data.userName;
        var password = $scope.data.password;
        var validCode = $scope.data.validCode;
        if(!validCode){
            toasterTip('warning','登录提示','验证码不为空!');
            return;
        }
        if (!userName || !password ) {
            toasterTip('error','登录提示','用户名或密码不正确,请重新输入!');
            return;
        }
        if (isValid) {
            var reqBody = angular.copy($scope.data);
            $http.post("../login.do", reqBody).success(function (data) {
                var errorMsg = data["errorMsg"];
                if (errorMsg != "") {
                    $scope.resetVerifyCode();
                    toasterTip('warning','登录提示',errorMsg);
                } else {
                    $window.sessionStorage.setItem("user_loginName",data.loginName);
                    $window.sessionStorage.setItem("user_name", data.name);
                    $window.sessionStorage.setItem("user_willPassDays", data.willPassDays|| "");
                    $window.location.href = "index.html";
                }
            }).error(function () {
                toasterTip('error','登录提示','用户名或密码不正确,请重新输入!');
            });
        }
    };

    $scope.verifyCodeUrl = "../verfyCode.do";
    $scope.resetVerifyCode = function () {
        $scope.verifyCodeUrl = $scope.verifyCodeUrl + "?d" + new Date();
    }

    //登录提示框
    function toasterTip(type, title, content) {
        toaster.pop({
            type: type,
            title: title,
            body: content,
            timeout: 4000,
            showCloseButton: true,
            bodyOutputType: 'trustedHtml'
        });
    }
});