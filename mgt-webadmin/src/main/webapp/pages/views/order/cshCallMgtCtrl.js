var DBApp = angular.module('DBApp');

DBApp.controller("cshCallMgtCtrl", ['$scope', '$modal', 'dbUtils','$http', function($scope, $modal, dbUtils,$http){
    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2
            },
            fields: [
                {name: "id", label: "ID", type: "text", placeholder: "请输入ID",  labelCols: "4"},
                {name: "orderId", label: "线索ID", type: "text", placeholder: "请输入线索ID",  labelCols: "4"},
                {name: "uploadTime",type: "dateRange",label: "导入时间", labelCols: "4"}
            ]
        },
        grid: {
            settings: {
                transCode: "pageCshCall",
                autoLoad: true,
                page: {pageSize: 10},
                showCheckBox: true
            },
            header: [
            	{name: "ID", width: "10%", field: "id"},
                {name: "线索ID", width: "10%", field: "orderId"},
                {name: "经销商ID", width: "10%", field: "dealerId"},
                {name: "经销商名称", width: "10%", field: "dealerName"},
                {name: "省份", width: "10%", field: "province"},
                {name: "城市", width: "10%", field: "city"},
                {name: "经营厂商", width: "10%", field: "operationBrand"},
                {name: "品牌", width: "10%", field: "brand"},
                {name: "通话时间", width: "10%", field: "callDateTime"},
                {name: "主叫号码", width: "10%", field: "callerPhoneNumber"},
                {name: "被叫号码", width: "10%", field: "calleeRealNumber"},
                {name: "被叫400", width: "10%", field: "fooPhone"},
                {name: "通话时长", width: "10%", field: "callerDuration"},
                {name: "等待时长", width: "10%", field: "waitDuration"},
                {name: "是否成功", width: "10%", field: "isSuccess"},
                {name: "主叫省份", width: "10%", field: "callerProvince"},
                {name: "主叫城市", width: "10%", field: "callerCity"},
                {name: "400状态", width: "10%", field: "fooStatus"},
                {name: "谁先挂断", width: "10%", field: "cutoff"},
                {name: "导入时间", width: "10%", field: "uploadTime"}
            ],
            rowOperation: {show: false}
        }
    };
    var formGridEvents = {
        grid: {
            operationEvents: [
            	{
                    name: "删除", class: "btn-danger", icon: "shanchu", click: function () {
                        remove();
                    }
                },
                {
                    name: "导入", class: "btn-primary", icon: "x-xlsx", click: function () {
	                    openImportModal();
	                }
                }
            ]
        }
    };

    $scope.dbFormGrid = {options: formGridOptions, events: formGridEvents};
    
    /**
     * 批量删除
     * @param rows
     */
    function remove() {
        $scope.dbFormGrid.operationButtonClick(function (selectRows) {
            if (selectRows.length === 0) {
                return;
            }
            dbUtils.confirm("确定要删除吗？", function () {
                dbUtils.post("removeCshCall", {"reqs": selectRows}, function () {
                    dbUtils.success("删除成功!", "删除");
                    $scope.dbFormGrid.reLoadData();
                }, function (error) {
                    dbUtils.error(error, "删除");
                });
            });
        });
    }

    //导入excel功能
    function openImportModal() {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'views/share/fileUploadModal.html',
            controller: 'fileUploadModalCtrl',
            size: "lg",
            backdrop: "static",
            resolve: {
                uploadConfig: function () {
                    return {
                    	url: '../import/csh/call.do'
                    };
                }
            }
        });
        instance.result.then(function () {
            $scope.dbFormGrid.reLoadData();
        });
    }
}]);
