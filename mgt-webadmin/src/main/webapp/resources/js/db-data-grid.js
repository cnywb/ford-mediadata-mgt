/**
 * Created by wanglijun on 2016-01-18.
 * dbFormGrid 指令
 * @version 1.1
 */

'use strict';
var dbDataGrid=angular.module('db.components.data.grid', ['dbUtils'])
dbDataGrid.directive('dbDataGrid', ['$timeout', '$window', 'dbUtils', function ($timeout, $window, dbUtils) {

    /**全局变量设置*/
    var settings={
        selection:{show:false,type:'radio',multiple:false},
        autoLoad: true,
        page: {pageSize: 10,pageNumber:1},
        transCode:'',
        checkedAll:function(){
            //多选实现方法
        }
    };
    //行的初始化参数
    var dataGridRow={

    };
    //行操作
    var rowOperation={show:false,width:'8%'};
    //分页初始化参数
    var dataGridPaging={
        pageNumber: 1,
        pageSize:10,
        prevPageDisabled: 'disabled',
        nextPageDisabled: 'disabled',
        totalRecords:0,
        totalPages:0
    };


    var params={
        keyWord:''
    };



    return {
        restrict: 'E',
        scope: {
            data:'='
        },
        replace: true,
        templateUrl: Metronic.getResourcesPath() + 'templates/dbDataGrid.html',
        controller: ['$scope', '$element', '$attrs', function ($scope, $element, $attrs) {



            if (angular.isUndefined($scope.data)) {
                return;
            }
            $scope.dataGrid=$scope.data;
            //定义默认查询条件,
            $scope.dataGrid.params=angular.extend({params:params},$scope.dataGrid.params);
            //定义默认表格配置
            $scope.dataGrid.settings=angular.extend({settings:settings},$scope.dataGrid.settings);
            //定义分页
            $scope.dataGrid.paging = angular.extend({paging: dataGridPaging}, $scope.dataGrid.settings.page);
            //定义行操作设置默认参数
            $scope.dataGrid.rowOperation=angular.extend({rowOperation:rowOperation},$scope.dataGrid.rowOperation);
            //定义行
            $scope.dataGrid.rows = [];

            //是否自动加载
            if ($scope.dataGrid.settings.autoLoad) {
                queryPaging();
            } else {
                $scope.dataGrid.loadingTip = "请手动点击查询按钮,进行查询操作!";
            }
            var selectFields = [];

            function setQueryParams(pageNumber) {
                var queryParams = angular.copy($scope.dataGrid.params);
                angular.forEach(selectFields, function (selectField) {
                    if (!angular.isUndefined($scope.dataGrid.params[selectField])) {
                        queryParams[selectField] = $scope.dataGrid.params[selectField].value;
                    } else {
                        queryParams[selectField] = null;
                    }
                });
                queryParams['page'] = {
                    keyWord: $scope.dataGrid.params.keyWord,
                    pageNumber: pageNumber || 1,
                    pageSize: $scope.dataGrid.paging.pageSize,
                    //month: $scope.dataGrid.params.month,
                    //orgPath: $scope.dataGrid.params.orgPath,
                    //channelRoleId: $scope.dataGrid.params.channelRoleId
                };
                return queryParams;
            }

            $scope.dataGrid.paramsBackup = angular.copy($scope.dataGrid.params);

            //分页查询
            function queryPaging(pageNumber) {
                $scope.dataGrid.loadingTip = '正在努力为您加载数据,请稍候...';
                var queryParams = setQueryParams(pageNumber);


                dbUtils.post($scope.dataGrid.settings.transCode, queryParams, function (data) {
                    //获取每行数据，并调用format方法进行处理，最后赋值给$scope.dbFormGrid.rows
                    var rows = data.content;
                    for (var i in rows) {
                        var row = rows[i];
                        for (var j in $scope.dataGrid.header) {
                            var header = $scope.dataGrid.header[j];
                            var value = (row[header.field]);


                            if (!angular.isUndefined($scope.dataGrid.events) && !angular.isUndefined($scope.dataGrid.events.fieldEvents)) {
                                var colorEvent = $scope.dataGrid.events.fieldEvents[header.field + 'Color'];
                                colorEvent ? header.colorEvent = colorEvent : null;

                                var clickEvent = $scope.dataGrid.events.fieldEvents[header.field + 'Click'];
                                clickEvent ? header.clickEvent = clickEvent : null;

                                var formatEvent = $scope.dataGrid.events.fieldEvents[header.field + 'Format'];
                                formatEvent ? header.formatEvent = formatEvent : null;
                            }
                            //格式化数据
                            if (header.formatEvent) {
                                value = header.formatEvent(value, row);
                            }
                            row[header.field] = value;
                        }
                        //添加一个是否选择的字段，默认值为false
                        row.checked = false;
                    }
                    $scope.dataGrid.rows = rows;
                    //分页数据处理
                    var pages = {};
                    pages.totalRecords = data.totalElements;
                    pages.pageNumber = data.pageNumber;
                    pages.pageSize = data.pageSize;
                    pages.totalPages = data.totalPages;
                    var totalPage = pages.totalPages;
                    //分页算法，页面只显示固定数量的分页按钮。
                    var pageNumbers = [];
                    var startPage = 1;
                    var endPage = totalPage;
                    var pageStep = 2;//以当前页为基准，前后各显示的页数量
                    if (totalPage >= 6) {
                        startPage = pages.pageNumber;
                        if (startPage >= pageStep) {
                            startPage -= pageStep;
                        }
                        if (startPage <= 1) {
                            startPage = 1;
                        }
                        endPage = (totalPage - pages.pageNumber) >= pageStep ? pages.pageNumber + pageStep : totalPage;
                        if (endPage > totalPage) {
                            endPage = totalPage;
                        }
                        if (startPage != 1) {
                            pageNumbers.push({number: '1'});
                            if (startPage - 1 != 1) {
                                pageNumbers.push({number: '...', disabled: 'disabled'});
                            }
                        }
                    }
                    for (var i = startPage; i <= endPage; i++) {
                        if (i == pages.pageNumber) {
                            pageNumbers.push({number: i, active: 'active'});
                        } else {
                            pageNumbers.push({number: i});
                        }
                    }
                    if (endPage != totalPage) {
                        if (endPage + 1 != totalPage) {
                            pageNumbers.push({number: '...', disabled: 'disabled'});
                        }
                        pageNumbers.push({number: totalPage});
                    }
                    pages.pageNumbers = pageNumbers;
                    if (pages.pageNumber == 1 || pages.totalPages == 0) {
                        pages.prevPageDisabled = 'disabled';
                    }
                    if (pages.pageNumber == totalPage || pages.totalPages == 0) {
                        pages.nextPageDisabled = 'disabled';
                    }
                    $scope.dataGrid.page = pages;
                    if (angular.isFunction($scope.dataGrid.gridLoaded)) {
                        $scope.dataGrid.gridLoaded($scope.dataGrid.rows);
                    }
                    // Metronic.stopPageLoading();
                    checkAllowSelect();
                    if ($scope.dataGrid.rows.length == 0) {
                        $scope.dataGrid.loadingTip = '已努力查询，但并无数据!';
                    }
                }, function () {
                    $scope.dataGrid.loadingTip = '服务器貌似生病啦，请稍等，再重试或者联系管理员吧!';
                });
            }

            //分页数量点击事件
            $scope.dataGrid.pageNumberClick = function (pageNumber) {
                var prevPage = $scope.dataGrid.page.prevPageDisabled;
                if (pageNumber === 'prev' && prevPage && prevPage != "") {
                    return false;
                }
                var nextPage = $scope.dataGrid.page.nextPageDisabled;
                if (pageNumber === 'next' && nextPage && nextPage != "") {
                    return false;
                }
                var pageNumber_ = $scope.dataGrid.page.pageNumber;
                if (pageNumber == pageNumber_) {
                    return false;
                }
                if (pageNumber === '...') {
                    return false;
                }
                if (pageNumber === 'prev') {
                    pageNumber_--;
                } else if (pageNumber === 'next') {
                    pageNumber_++;
                } else {
                    pageNumber_ = pageNumber;
                }
                queryPaging(pageNumber_);
            };
            //grid上方按钮点击事件
            $scope.dataGrid.operationButtonClick = function (clickFun) {
                //获取所有已经选择的行数据传递给回调方法。
                var rows = getAllSelectRows();
                clickFun(rows, $scope.dataGrid.rows);
                checkAllowSelect();
            };

            //每行点击事件
            $scope.dataGrid.rowClick = function (row) {
                row.checked = !row.checked;
                checkAllowSelect();
            };

            //点击全选复选框事件
            $scope.dataGrid.allRowClick = function () {
                $scope.dataGrid.settings.selection.multiple = !$scope.dataGrid.settings.selection.multiple;
                angular.forEach($scope.dataGrid.rows, function (row) {
                    row.checked = $scope.dataGrid.settings.selection.multiple;
                });
            };

            function checkAllowSelect() {
                if ($scope.dataGrid.settings.selection.type != 'checkbox' || $scope.dataGrid.settings.selection.multiple) {
                    return;
                }
                //如果所有行数据为非选中状态，则全选按钮为非选中状态，反之一样
                var flag = true;
                if ($scope.dataGrid.rows.length == 0) {
                    flag = false;
                }
                angular.forEach($scope.dataGrid.rows, function (row) {
                    if (!row.checked) {
                        flag = false;
                    }
                });
                $scope.dataGrid.settings.selection.multiple = flag;
            }

            //获取所有选中的行数据
            function getAllSelectRows() {
                var rows = [];
                angular.forEach($scope.dataGrid.rows, function (row) {
                    if (row.checked) {
                        rows.push(row);
                    }
                });
                return rows;
            }

            $scope.dataGrid.getAllSelectRows = function () {
                return getAllSelectRows();
            };



            $scope.dataGrid.reLoadData = function () {
                queryPaging();
            };

            /**
             * 重置查询条件数据
             */
            $scope.dataGrid.resetFormData = function () {
                $scope.dataGrid.params = angular.copy($scope.dataGrid.paramsBackup);
                queryPaging();
            };

            /**
             * 获取查询参数对象
             * @param pageNumber
             * @returns {*}
             */
            $scope.dataGrid.getQueryParams = function (pageNumber) {
                return queryPaging(pageNumber);
            };

            // console.dir($scope.dbDataGrid[dataGridName]);
        }],
        link: function (scope, element, attrs,controller) {
            console.log('linked db-data-grid');
        }
    }
}]);