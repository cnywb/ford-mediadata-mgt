var DBApp = angular.module('DBApp');

DBApp.controller("batchMgtCtrl", ['$scope', '$modal', 'dbUtils', '$window', BatchMgtCtrl]);

function BatchMgtCtrl($scope, $modal, dbUtils, $window) {
	
	$scope.jobNameOption = [
		{
			name : "易湃订单数据转换",
			value : "commonOrderToObJob"
		},
		{
			name : "易湃话单数据转换",
			value : "callOrderToObJob"
		},
		{
			name : "车商汇订单数据转换",
			value : "cshOrderToObJob"
		},
		{
			name : "车商汇话单数据转换",
			value : "cshCallToObJob"
		}
	];

    var formGridOptions = {
        form: {
            settings: {
                cols: 2,
				showClose : true
            },
            fields: [
                {name: "jobName", label: "批处理", type: "select", dropDownItem: $scope.jobNameOption},
                {name: "createTime", label: "触发时间", type: "dateRange"}
            ]
        },
        grid: {
            settings: {
                transCode: "pageBatch",
                autoLoad: true,
                showCheckBox: true
            },
            header: [
                {"name": "批处理", "width": "10%", "field": "jobName"},
                {"name": "触发时间", "width": "10%", "field": "createTime"},
                {"name": "开始时间", "width": "10%", "field": "startTime"},
                {"name": "结束时间", "width": "10%", "field": "endTime"},
                {"name": "参数", "width": "10%", "field": "jobParams"},
                {"name": "状态", "width": "10%", "field": "status"},
                {"name": "结果", "width": "10%", "field": "exitCode"},
                {"name": "消息", "width": "10%", "field": "exitMessage"}
            ],
            rowOperation: {show: false, width: "10%"}
        }
    };
    //!!formGridOptions-END!!
    var formGridEvents = {
        grid: {
            fieldEvents: {
            	"jobNameFormat":function(val,row){
                    return row.jobInstance.jobName;
                },

            	"jobParamsFormat":function(val,row){
                    return JSON.stringify(row.jobExecutionParams);
                }
            },
            rowEvents: [
            ],
            operationEvents: [
            	{
	                name: "手动触发", class: "btn-primary", icon: "luru", click: function () {
	                    add();
                }
            }]
        }
    };

    $scope.dbFormGrid = {options: formGridOptions, events: formGridEvents};

    /**
     * 新增
     * @param row
     */
    function add() {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'db/db-form.html',
            controller: 'batchActivatorCtrl',
            size: "md",
            backdrop: "static",
            resolve: {}
        });
        instance.result.then(function () {
            $scope.dbFormGrid.reLoadData();
        });
    }

}