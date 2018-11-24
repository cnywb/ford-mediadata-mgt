var DBApp = angular.module('DBApp');

DBApp.controller("dealerMgtCtrl", ['$scope', '$modal', 'dbUtils', '$window', DealerMgtCtrl]);

function DealerMgtCtrl($scope, $modal, dbUtils, $window) {

    //!!formGridOptions-START!!
    var formGridOptions = {
        form: {
            settings: {
                cols: 2,
				showClose : true
            },
            fields: [
                {name: "code", label: "经销商代码", type: "text", placeholder: "经销商代码"},
                {name: "name", label: "经销商名称", type: "text", placeholder: "经销商名称"}
            ]
        },
        grid: {
            settings: {
                transCode: "pageDealer",
                autoLoad: true,
                showCheckBox: true
            },
            header: [
                {"name": "省份", "width": "10%", "field": "province"},
                {"name": "城市", "width": "10%", "field": "city"},
                {"name": "经销商代码", "width": "10%", "field": "code"},
                {"name": "经销商名称", "width": "10%", "field": "name"}
            ],
            rowOperation: {show: true, width: "10%"}
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
                dbUtils.post("removeDealer", {"reqs": selectRows}, function () {
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
    function edit(dealer) {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'db/db-form.html',
            controller: 'dealerEditorCtrl',
            size: "md",
            backdrop: "static",
            resolve: {
            	dealer: function () {
                    return dealer;
                }
            }
        });
        instance.result.then(function () {
            $scope.dbFormGrid.reLoadData();
        });
    }

}