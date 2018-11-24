var DBApp = angular.module('DBApp');

DBApp.controller("batchActivatorCtrl", ['$scope', '$modalInstance', 'dbUtils', BatchActivatorCtrl]);

function BatchActivatorCtrl($scope, $modalInstance, dbUtils) {
	

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

    $scope.dbForm = {
        settings: {showClose: true, transCode: "activateBatch", cols: 2},
        title: {icon: "luru", label: "批处理参数"},
        sections: [{
            sectionTitle: {show: true, icon: "touxiang", label: "参数设置"},
            fields: [
                {"name": "jobName", "label": "批处理", "type": "select", "labelCols": "4", "editable": true, "required": true, "cols": "6", "dropDownItem": $scope.jobNameOption},
                {"name": "dateParam", "label": "日期参数", "type": "dateRange", "labelCols": "4", "editable": true, "required": true, "cols": "6"}
            ]
        }],
        events: {
            beforeSubmit: function (reqBody) {
                return true;
            },
            afterSubmit: function (data) {
                dbUtils.success("批处理执行中，请勿重复执行！");
                $modalInstance.close();
            },
            modalClose: function () {
                $modalInstance.dismiss("cancel");
            }
        }
    };

}