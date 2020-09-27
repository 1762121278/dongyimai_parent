app.controller('indexController' ,function($scope,$controller   ,loginService) {
    $scope.showLoginName  = function () {
        loginService.showLoginName().success(
            function(response){
                $scope.loginName=response.loginName;
            }
        );
    }
})
