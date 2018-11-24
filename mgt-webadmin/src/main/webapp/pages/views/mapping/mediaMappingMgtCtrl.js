var DBApp = angular.module('DBApp');

DBApp.controller("mediaMappingMgtCtrl", ['$scope', '$modal', 'dbUtils', '$window', MediaMappingMgtCtrl]);

function MediaMappingMgtCtrl($scope, $modal, dbUtils, $window) {

    var formGridOptions = {
        form: {
            settings: {
                cols: 2,
				showClose : true
            },
            fields: [
                {name: "mediaType", label: "渠道标识", type: "select", placeholder: "", dropDownItemType: "json", dropDownItem: "mediaType"},
                {name: "mappingType", label: "映射类型", type: "select", placeholder: "", dropDownItemType: "json", dropDownItem: "mappingType"},
                {name: "mediaValue", label: "渠道值", type: "text", placeholder: ""},
                {name: "sysValue", label: "系统值", type: "text", placeholder: ""}
            ]
        },
        grid: {
            settings: {
                transCode: "pageMediaMapping",
                autoLoad: true,
                showCheckBox: true
            },
            header: [
                {"name": "渠道标识", "width": "10%", "field": "mediaType"},
                {"name": "映射类型", "width": "10%", "field": "mappingType"},
                {"name": "渠道值", "width": "10%", "field": "mediaValue"},
                {"name": "系统值", "width": "10%", "field": "sysValue"},
                {"name": "说明", "width": "10%", "field": "description"}
            ],
            rowOperation: {show: true, width: "10%"}
        }
    };
    //!!formGridOptions-END!!
    var formGridEvents = {
        grid: {
            fieldEvents: {
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
                dbUtils.post("removeMediaMapping", {"reqs": selectRows}, function () {
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
            controller: 'mediaMappingEditorCtrl',
            size: "md",
            backdrop: "static",
            resolve: {
            	mediaMapping: function () {
                    return row;
                }
            }
        });
        instance.result.then(function () {
            $scope.dbFormGrid.reLoadData();
        });
    }

}