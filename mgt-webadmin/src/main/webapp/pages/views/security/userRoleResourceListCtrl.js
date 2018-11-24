/**
 * Created by ziv.hung on 16/8/25.
 */

angular.module('DBApp').controller("userRoleResourceListCtrl", ['$scope', '$modal', 'dbUtils', '$window', function  ($scope, $modal, dbUtils, $window) {

    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 3
            },
            fields: [
                {"name": "userName", "label": "用户名", "type": "text", "placeholder": "用户名"},
                {"name": "roleName", "label": "角色名称", "type": "text", "placeholder": "角色名称"},
                {"name": "resourceName", "label": "资源名称", "type": "text", "placeholder": "资源名称"},
            ]
        },
        grid: {
            settings: {
                transCode: "userRoleResourcePage",
                autoLoad: true,
                showCheckBox: true
            },
            header: [
                {"name": "登录账户", "width": "14%", "field": "userLoginName"},
                {"name": "登陆用名", "width": "13%", "field": "userName"},
                {"name": "角色名称", "width": "14%", "field": "roleName"},
                {"name": "资源名称", "width": "14%", "field": "resourceName"},
                {"name": "资源类型", "width": "14%", "field": "resourceType"},
                {"name": "是否为菜单", "width": "13%", "field": "resourceIsMenu"},
                {"name": "资源URL", "width": "14%", "field": "resourcePermission"}
            ],
            rowOperation: {show: false}
        }
    }
    //!!formGridOptions-END!!
    var formGridEvents = {
        grid: {
            fieldEvents: {
                "resourceTypeFormat": function (value) {
                    if ("business" === value) {
                        return "业务资源";
                    }
                    return "全局资源";
                }
            },
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
            var userName = params['userName'] || "";
            var roleName = params['roleName'] || "";
            var resourceName = params['resourceName'] || "";
            var url = '../userRoleResourceExport.do?userName=' + userName + '&roleName=' + roleName + '&resourceName=' + resourceName;
            $window.open(url);
        });
    }
}]);