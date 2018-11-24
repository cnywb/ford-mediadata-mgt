/**
 * Created by yangkui on 16/4/8.
 * 机构查询 含月份\人员查询条件封装,用于grid查询条件显示
 *
 */
(function(){
    'use strict';
    var uis = angular.module('db.components.orgQuery', []);
    uis.directive('dbOrgQuery', ['dbUtils', function (dbUtils) {
        return {
            restrict: 'E',
            templateUrl: Metronic.getResourcesPath() + "templates/dbOrgQuery.html",
            replace: true,
            scope:false,
            transclude: true,
            controller: ['$scope', '$modal', 'dbUtils',function ($scope, $modal,dbUtils) {
                var now = new Date();
                var currentYear = now.getFullYear();
                var month = dbUtils.dateFormat(new Date(currentYear, now.getMonth()-1,1));
                //机构树选择后的回调事件
                $scope.dbOrgTree = {settings: {noCache:true,showDivision: true, showDepartment: true,month: month}};
                $scope.dbOrgTree.onOrgSelected = function(item){
                    $scope.dbFormGrid.setFormDataField("orgNamePath", item['orgNamePath']);
                    $scope.dbFormGrid.setFormDataField("orgPath", item['orgPath']);
                };
                //选择年月份回调事件
                $scope.dbMonthSelect = {};
                $scope.dbMonthSelect.onMonthSelected = function(select){
                    var date = new Date(select.year,select.month,1);
                    var month = dbUtils.dateFormat(date);
                    $scope.dbFormGrid.setFormDataField("yearMonth", select.path);
                    $scope.dbFormGrid.setFormDataField("month",month);
                    //设置机构查询条件的月份值
                    $scope.dbOrgTree.settings.month = month;
                    //清空机构
                    $scope.dbFormGrid.setFormDataField("orgNamePath",null);
                    $scope.dbFormGrid.setFormDataField("orgPath",null);
                };
                $scope.dbFormGrid.queryType = [{'name':'按机构','value':'org'},{'name':'按人员','value':'channelRole'}];
                //监听下拉框
                $scope.$watch("dbFormGrid.queryParams.queryType",function(newValue,oldValue){
                    if(newValue!=oldValue&&!angular.isUndefined(newValue)&&!angular.isUndefined(oldValue)){
                        if(newValue["value"]=="org"){
                            $scope.dbFormGrid.reSetFormField([{"name": "orgNamePath","show":true},{"name": "channelRole","show":false}]);
                            $scope.dbFormGrid.setFormDataField("channelRoleId", null);
                            $scope.dbFormGrid.setFormDataField("channelRole", null);
                        }else{
                            $scope.dbFormGrid.reSetFormField([{"name": "orgNamePath","show":false},{"name": "channelRole","show":true}]);
                            $scope.dbFormGrid.setFormDataField("orgPath", null);
                            $scope.dbFormGrid.setFormDataField("orgNamePath",null);
                        }
                    }
                });
            }]
        }
    }]);
})();
