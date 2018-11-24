/**
 * Created by ziv.hung on 16/8/25.
 */

angular.module('DBApp').controller("operationLogListCtrl", ['$scope', 'dbUtils', '$window', function ($scope, dbUtils, $window) {

    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 3
            },
            fields: [
                {label: "操作日期", type: "dateRange"},
                {name: "operationType", label: "操作类型", type: "select", dropDownItemType: "json", dropDownItem: "operationType"}
            ]
        },
        grid: {
            settings: {
                transCode: "operationLogPage",
                autoLoad: true,
                showCheckBox: true
            },
            header: [
                {"name": "操作人", "width": "9%", "field": "userName"},
                {"name": "操作类型", "width": "10%", "field": "operationTypeName"},
                {"name": "操作内容", "width": "35%", "field": "operationContent"},
                {"name": "操作时间", "width": "12%", "field": "operationDate"}
            ],
            rowOperation: {show: false}
        }
    };
    //!!formGridOptions-END!!
    var formGridEvents = {
        grid: {
            operationEvents: [
                {
                    name: "导出查询结果", icon: 'daochuuexport', class: "btn-primary", click: function () {
                    exportExcel();
                }
                }]
        }
    };

    $scope.dbFormGrid = {options: formGridOptions, events: formGridEvents};

    function exportExcel() {
        var totalRecords = $scope.dbFormGrid.page.totalRecords;
        if (!totalRecords) {
            dbUtils.warning("无查询数据记录不导出！", "温馨提示");
            return;
        }
        dbUtils.confirm("确定要以当前查询条件下的结果数据执行导出操作?", function () {
            var params = $scope.dbFormGrid.getQueryParams(1);

            var operationType = params['operationType'] || "";
            var startDate = params['startDate'] || "";
            var endDate = params['endDate'] ? dbUtils.getNextDay(params['endDate'], 1) : "";
            var url = '../logExport.do?operationType=' + operationType + '&startDate=' + startDate + '&endDate=' + endDate;
            $window.open(url);
        });
    }
}]);