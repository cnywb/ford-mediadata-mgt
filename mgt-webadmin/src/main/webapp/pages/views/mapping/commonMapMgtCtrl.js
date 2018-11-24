var DBApp = angular.module('DBApp');

DBApp.controller("commonMapMgtCtrl", ['$scope', '$modal', 'dbUtils', '$window', CommonMapMgtCtrl]);

function CommonMapMgtCtrl($scope, $modal, dbUtils, $window) {

    var formGridOptions = {
        form: {
            settings: {
                cols: 2,
				showClose : true
            },
            fields: [
                {name: "mapKeyLike", label: "KEY", type: "text", placeholder: ""}
            ]
        },
        grid: {
            settings: {
                transCode: "pageCommonMap",
                autoLoad: true,
                showCheckBox: true
            },
            header: [
                {"name": "KEY", "width": "10%", "field": "mapKey"},
                {"name": "VALUE", "width": "10%", "field": "mapValue"}
            ],
            rowOperation: {show: true, width: "10%"}
        }
    };
    //!!formGridOptions-END!!
    var formGridEvents = {
        grid: {
            fieldEvents: {
                "mapKeyClick": function (row) {
                	edit(row);
                }
            },
            rowEvents: [{
                "name": "修改", "class": "btn-warning", "click": function (row) {
                    edit(row);
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
                dbUtils.post("removeCommonMap", {"reqs": selectRows}, function () {
                    dbUtils.success("删除成功!", "批量删除");
                    $scope.dbFormGrid.reLoadData();
                }, function (error) {
                    dbUtils.error(error, "批量删除");
                });
            });
        });
    }

    /**
     * 新增/修改
     * @param row
     */
    function edit(row) {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'db/db-form.html',
            controller: 'commonMapEditorCtrl',
            size: "md",
            backdrop: "static",
            resolve: {
            	commonMap: function () {
                    return row;
                }
            }
        });
        instance.result.then(function () {
            $scope.dbFormGrid.reLoadData();
        });
    }

}