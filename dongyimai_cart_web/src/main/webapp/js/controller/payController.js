app.controller('payController',function($scope, payService,$location){

    //本地生成二维码
    $scope.createNative=function(){

        payService.createNative().success(
            function(response){
                $scope.money=  (response.total_fee/1).toFixed(2) ;	//金额
                $scope.out_trade_no= response.out_trade_no;//订单号

                //二维码
                var qr = new QRious({
                    element:document.getElementById('qrious'),
                    size:250,
                    level:'H',
                    value:response.qrcode
                });
                alert($scope.out_trade_no);
                queryPayStatus($scope.out_trade_no);

            }
        );
    }

    //查询支付状态
    queryPayStatus=function(){
        payService.queryPayStatus($scope.out_trade_no).success(
            function(response){
                if(response.success){
                    location.href="paysuccess.html#?money="+$scope.money;
                }else{
                    if(reaponse.message=='二维码超时'){
                        alert("二维码超时，刷新页面重新获取二维码");
                        createNative();//重新生成二维码
                    }else{
                        location.href="payfail.hmtl";
                    }

                }
            }
        )
    }

    $scope.getMoney=function(){
        $scope.totalFee= $location.search()['money'];
    }
})