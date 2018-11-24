var DBApp = angular.module('DBApp');

DBApp.controller("projectMgtCtrl", ['$scope', '$modal', 'dbUtils', '$window', ProjectMgtCtrl]);

function ProjectMgtCtrl($scope, $modal, dbUtils, $window) {

    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2,
				showClose : true
            },
            fields: [
                {name: "code", label: "项目活动代码", type: "text", placeholder: "项目活动代码"},
                {name: "name", label: "项目活动名称", type: "text", placeholder: "项目活动名称"}
            ]
        },
        grid: {
            settings: {
                transCode: "pageProject",
                autoLoad: true,
                showCheckBox: true
            },
            header: [
                {"name": "项目活动代码", "width": "10%", "field": "code"},
                {"name": "项目活动名称", "width": "10%", "field": "name"},
                {"name": "开始时间", "width": "10%", "field": "beginTime"},
                {"name": "结束时间", "width": "10%", "field": "endTime"}
            ],
            rowOperation: {show: true, width: "20%"}
        }
    };
    //!!formGridOptions-END!!
    var formGridEvents = {
        grid: {
            fieldEvents: {
                "codeClick": function (row) {
                	edit(row);
                }
            },
            rowEvents: [{
                "name": "修改", "class": "btn-warning", "click": function (row) {
                    edit(row);
                }
            },{
                "name": "关联城市", "class": "btn-info", "click": function (row) {
                    city(row);
                }
            },{
                "name": "用户管理", "class": "btn-info", "click": function (row) {
                    user(row);
                }
            }],
            operationEvents: [{
                name: "删除", class: "btn-danger", icon: "shanchu", click: function () {
                    remove();
                }
            }, {
                name: "新增", class: "btn-primary", icon: "luru", click: function () {
                    edit();
                }
            }]
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
                dbUtils.post("removeProject", {"reqs": selectRows}, function () {
                    dbUtils.success("经销商删除成功!", "经销商删除");
                    $scope.dbFormGrid.reLoadData();
                }, function (error) {
                    dbUtils.error(error, "经销商删除");
                });
            });
        });
    }

    /**
     * 新增/修改
     * @param row
     */
    function edit(project) {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'db/db-form.html',
            controller: 'projectEditorCtrl',
            size: "md",
            backdrop: "static",
            resolve: {
            	project: function () {
                    return project;
                }
            }
        });
        instance.result.then(function () {
            $scope.dbFormGrid.reLoadData();
        });
    }

    /**
     * 城市
     * @param row
     */
    function city(project) {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'views/project/projectCityEditor.html',
            controller: 'projectCityEditorCtrl',
            size: "md",
            backdrop: "static",
            resolve: {
            	project: function () {
                    return project;
                }
            }
        });
        instance.result.then(function () {
            $scope.dbFormGrid.reLoadData();
        });
    }

    /**
     * 账号
     * @param row
     */
    function user(project) {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'db/db-form-grid.html',
            controller: 'projectUserMgtCtrl',
            size: "lg",
            backdrop: "static",
            resolve: {
            	project: function () {
                    return project;
                }
            }
        });
        instance.result.then(function () {
            $scope.dbFormGrid.reLoadData();
        });
    }

}