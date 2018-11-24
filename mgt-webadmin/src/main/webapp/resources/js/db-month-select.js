/**
 * Created by yangkui on 16/4/5.
 * 年月份选择指令
 * 在formGrid里面定义type为monthSelect,然后定义一个回调接收方法
 *
 * $scope.dbMonthSelect = {};
 * $scope.dbMonthSelect.onMonthSelected = function(select){
 *      select = {year:"2016",month:"12",name:"",path:"2016年12月"};
 *      filedName = 指定的名称
 * }
 */

(function () {
    'use strict';
    var uis = angular.module('db.components.monthselect', []);
    uis.directive('dbMonthSelect', ['dbUtils', function (dbUtils) {
        return {
            restrict: 'E',
            templateUrl: Metronic.getResourcesPath() + "templates/dbMonthSelect.html",
            replace: true,
            transclude: true,
            controller: ['$scope', '$modal', function ($scope, $modal) {
                if (angular.isUndefined($scope.dbMonthSelect)) {
                    $scope.dbMonthSelect = {};
                }
                $scope.dbMonthSelect.selectMonth = function (fieldName,month) {

                    var instance = $modal.open({
                        controller: ['$scope', '$modalInstance', 'field', function ($scope, $modalInstance, field) {
                            $scope.dbMonthSelect = {};//防止无法直接读取$scope上的一级变量,先定义一个已经存在的变量

                            //确定按钮事件
                            $scope.closeModal = function () {
                                var year = $scope.dbMonthSelect.year.value;
                                var month = $scope.dbMonthSelect.month.value;
                                //此处的月份需要减1,保证与js当中的月份一致
                                var select = {year: year, month: month-1, name: field.name, path: year + "年" + month + "月"};
                                //回调页面并关闭窗口
                                $modalInstance.close(select);
                            };
                            $scope.modalClose = function () {
                                $modalInstance.dismiss("cancel");
                            };

                            var years = [];
                            var months = [];
                            var now = new Date();
                            var currentYear = now.getFullYear();
                            var currentMonth = now.getMonth();
                            var monthValue = field.month||"";
                            var yearValue = currentYear;
                            if(monthValue!=""&&monthValue.indexOf("年")>-1&&monthValue.indexOf("月")>-1){
                                var a = monthValue.split("年");
                                yearValue = a[0];
                                var b = a[1].split("月");
                                currentMonth = b[0];
                            }

                            var defaultYear = {"name": yearValue + "年", "value": currentYear};
                            var defaultMonth = {"name": currentMonth + "月", "value": currentMonth};
                            for (var i = 0; i < 3; i++) {
                                years.push({"name": currentYear - i + "年", "value": currentYear - i});
                            }
                            for (var i = 0; i < 12; i++) {
                                months.push({"name": i + 1 + "月", "value": i + 1});
                            }
                            $scope.dbMonthSelect.year = defaultYear;
                            $scope.dbMonthSelect.month = defaultMonth;
                            $scope.years = years;
                            $scope.months = months;
                        }],
                        templateUrl: 'dbMonthSelectModal_template.html',
                        size: "sm",
                        backdrop: "static",
                        resolve: {
                            field: function () {
                                return {"name": fieldName,month:month};
                            }
                        }
                    });

                    instance.result.then(function (item) {
                        //机构选择后回调调用方
                        if (!angular.isUndefined($scope.dbMonthSelect.onMonthSelected)) {
                            $scope.dbMonthSelect.onMonthSelected(item, item.name);
                        }
                    });

                }
            }],
            link: function (scope, element, attrs) {
            }
        }
    }]);
})();

