var DBApp = angular.module('DBApp');

DBApp.controller("cshOrderMgtCtrl", ['$scope', '$modal', 'dbUtils','$http', function($scope, $modal, dbUtils,$http){
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
                transCode: "pageCshOrder",
                autoLoad: true,
                page: {pageSize: 10},
                showCheckBox: true
            },
            header: [
            	{name: "ID", width: "10%", field: "id"},
                {name: "线索ID", width: "10%", field: "orderId"},
                {name: "省", width: "10%", field: "province"},
                {name: "市", width: "10%", field: "city"},
                {name: "区/县", width: "10%", field: "distriction"},
                {name: "姓名", width: "10%", field: "name"},
                {name: "联系方式", width: "10%", field: "mobile"},
                {name: "品牌", width: "10%", field: "brand"},
                {name: "预定车系", width: "10%", field: "carModel"},
                {name: "预定车型", width: "10%", field: "carModelDetail"},
                {name: "其他说明", width: "10%", field: "remark"},
                {name: "承接经销商ID", width: "10%", field: "assignedDealerId"},
                {name: "承接经销商", width: "10%", field: "assignedDealerName"},
                {name: "处理经销商ID", width: "10%", field: "proceedDealerId"},
                {name: "处理经销商", width: "10%", field: "proceedDealerName"},
                {name: "下单时间", width: "10%", field: "orderDateTime"},
                {name: "处理时间", width: "10%", field: "proceedDateTime"},
                {name: "是否试驾", width: "10%", field: "isTestDrive"},
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
                dbUtils.post("removeCshOrder", {"reqs": selectRows}, function () {
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
                    	url: '../import/csh/order.do'
                    };
                }
            }
        });
        instance.result.then(function () {
            $scope.dbFormGrid.reLoadData();
        });
    }
}]);
