var DBApp = angular.module('DBApp');

DBApp.controller("projectUserMgtCtrl", ['$scope', '$modal', 'dbUtils', 'project', '$modalInstance', ProjectUserMgtCtrl]);

function ProjectUserMgtCtrl($scope, $modal, dbUtils, project, $modalInstance) {

    var formGridOptions = {
        form: {
            settings: {
                cols: 1,
                showClose: true
            },
            fields: [
                {"name": "usernameLike", "label": "用户名", "type": "text", "placeholder": "用户名模糊查询", "labelCols": "2", "cols": 6},
                {"name": "role", "label": "角色", "type": "select", "labelCols": "2", "cols": 4, "dropDownItemType": "json", "dropDownItem": "projectUserRole"}
            ],
            modalClose: function () {
                $modalInstance.dismiss("cancer");
            },
            hiddenParams: {projectCode: project.code}
        },
        grid: {
            settings: {
                transCode: "pageProjectUser",
                autoLoad: true,
                showCheckBox: true
            },
            header: [
                {"name": "用户名", "width": "10%", "field": "username"},
                {"name": "密码", "width": "10%", "field": "password"},
                {"name": "角色", "width": "10%", "field": "role_"},
                {"name": "活动省份", "width": "10%", "field": "eventProvince"},
                {"name": "活动城市", "width": "10%", "field": "eventCity"},
                {"name": "活动场次", "width": "10%", "field": "serial"}
            ],
            rowOperation: {show: true, width: "20%"}
        }
    };

    var formGridEvents = {
        grid: {
        	fieldEvents: {
                "role_Format": function (value, row) {
                    if (row.role == "ADMIN") {
                        return "管理员";
                    } else if (row.role == "OPERATOR") {
                        return "操作员";
                    }
                }
            },
        	rowEvents: [{
                "name": "修改", "class": "btn-warning", "click": function (row) {
                    edit(row);
                }
            },{
                "name": "关联经销商", "class": "btn-info", "click": function (row) {
                	dealer(row);
                }
            }],
            operationEvents: [{
                name: "删除所有操作员", class: "btn-danger", icon: "shanchu", click: function () {
                    removeAllOperator();
                }
            },{
                name: "删除所有用户", class: "btn-danger", icon: "shanchu", click: function () {
                    removeAll();
                }
            },{
                name: "删除选中用户", class: "btn-danger", icon: "shanchu", click: function () {
                    remove();
                }
            }, {
                name: "批量创建用户", class: "btn-primary", icon: "luru", click: function () {
                    batchCreate();
                }
            }, {
                name: "新增", class: "btn-primary", icon: "luru", click: function () {
                    edit();
                }
            }]
        },
        form: {
            modalClose: function () {
                $modalInstance.dismiss("cancel");
            }
        }
    };

    $scope.dbFormGrid = {options: formGridOptions, events: formGridEvents};

    //编辑
    function edit(projectUser) {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'db/db-form.html',
            controller: 'projectUserEditorCtrl',
            size: "md",
            backdrop: "static",
            resolve: {
            	projectUser: function () {
                    return projectUser;
                },
        		project: function () {
                    return project;
                }
            }
        });
        instance.result.then(function () {
            $scope.dbFormGrid.reLoadData();
        });
    }

    //批量创建用户
    function batchCreate() {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'db/db-form.html',
            controller: 'projectUserBatchCreatorCtrl',
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
     * 批量删除
     * @param rows
     */
    function remove() {
        $scope.dbFormGrid.operationButtonClick(function (selectRows) {
            if (selectRows.length === 0) {
                return;
            }
            dbUtils.confirm("确定要删除吗？", function () {
                dbUtils.post("removeProjectUser", {"reqs": selectRows}, function () {
                    dbUtils.success("用户删除成功!", "用户删除");
                    $scope.dbFormGrid.reLoadData();
                }, function (error) {
                    dbUtils.error(error, "用户删除");
                });
            });
        });
    }
    
    /**
     * 删除所有活动用户
     * @param rows
     */
    function removeAll() {
        dbUtils.confirm("确定要删除所有用户？", function () {
            dbUtils.post("removeProjectUserByCondition", {"projectCode": project.code}, function () {
                dbUtils.success("删除成功!", "用户删除");
                $scope.dbFormGrid.reLoadData();
            }, function (error) {
                dbUtils.error(error, "用户删除");
            });
        });
    }
    
    /**
     * 删除所有活动操作员用户
     * @param rows
     */
    function removeAllOperator() {
        dbUtils.confirm("确定要删除所有操作员用户？", function () {
            dbUtils.post("removeProjectUserByCondition", {"projectCode": project.code, "role": "OPERATOR"}, function () {
                dbUtils.success("删除成功!", "用户删除");
                $scope.dbFormGrid.reLoadData();
            }, function (error) {
                dbUtils.error(error, "用户删除");
            });
        });
    }
    
    /**
     * 关联经销商
     * @param row
     */
    function dealer(projectUser) {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'views/project/projectUserDealerEditor.html',
            controller: 'projectUserDealerEditorCtrl',
            size: "md",
            backdrop: "static",
            resolve: {
            	projectUser: function () {
                    return projectUser;
                }
            }
        });
        instance.result.then(function () {
            $scope.dbFormGrid.reLoadData();
        });
    }

    $scope.cancel = function () {
        $modalInstance.dismiss("cancel");
    }
}