var DBApp = angular.module('DBApp');

DBApp.controller("orderTransMgtCtrl", ['$scope', '$modal', 'dbUtils', '$http', '$window', function($scope, $modal, dbUtils, $http, $window){

    var formGridOptions = {
        form: {
            settings: {
                cols: 2
            },
            fields: [
                {name: "orderId", label: "线索ID", type: "text", placeholder: "请输入线索ID",  labelCols: "4"},
                {name: "campaignChannelId", label: "活动渠道", type: "select",  labelCols: "4", dropDownItemType: "json", dropDownItem: "campaignChannelId"},
                {name: "localToDealerId", label: "LMCID", type: "text", placeholder: "请输入LMCID",  labelCols: "4"},
                {name: "importDate",type: "dateRange",label: "LMC处理时间", labelCols: "4"},
                {name: "refId", label: "关联ID", type: "text", placeholder: "请输入关联ID",  labelCols: "4"},
                {name: "orderBusinessOpportunityCreateTime",type: "dateRange",label: "线索创建时间", labelCols: "4"}
            ]
        },
        grid: {
            settings: {
                transCode: "pageOrderTrans",
                autoLoad: true,
                page: {pageSize: 10},
                showCheckBox: false
            },
            header: [
                {name: "创建日期", width: "10%", field: "dataCreateDate"},
                {name: "更新日期", width: "10%", field: "dataUpdateDate"},
                {name: "线索ID", width: "10%", field: "orderId"},
                {name: "订单类型", width: "10%", field: "orderTypeName"},
                {name: "姓名", width: "10%", field: "userName"},
                {name: "手机号", width: "10%", field: "userMobile"},
                {name: "性别", width: "10%", field: "_userGender"},
                {name: "邮件", width: "10%", field: "userMail"},
                {name: "颜色", width: "10%", field: "carColor"},
                {name: "车型", width: "10%", field: "csName"},
                {name: "车名称", width: "10%", field: "carName"},
                {name: "销售顾问", width: "10%", field: "accountName"},
                {name: "线索处理状态", width: "10%", field: "status"},
                {name: "首次处理时间", width: "10%", field: "fistFollowTime"},
                {name: "省份", width: "10%", field: "provinceName"},
                {name: "城市", width: "10%", field: "cityName"},
                {name: "线索创建时间", width: "10%", field: "orderBusinessOpportunityCreateTime"},
                {name: "承接经销商名称", width: "10%", field: "dealerName"},
                {name: "承接经销商代码", width: "10%", field: "dealerId"},
                {name: "备注", width: "10%", field: "orderRemark"},
                {name: "处理经销商ID", width: "10%", field: "proceedDealerId"},
                {name: "处理经销商名称", width: "10%", field: "proceedDealerName"},
                {name: "处理时间", width: "10%", field: "proceedDateTime"},
                {name: "是否试驾", width: "10%", field: "isTestDrive"},
                {name: "被叫电话号码", width: "10%", field: "calleeRealNumber"},
                {name: "通话开始时间", width: "10%", field: "callBeginTime"},
                {name: "通话结束时间", width: "10%", field: "callFinishTime"},
                {name: "通话时长(秒)", width: "10%", field: "callerDuration"},
                {name: "座席", width: "10%", field: "agentName"},
                {name: "跟近状态", width: "10%", field: "_dealerFollowStatus"},
                {name: "等待时长(秒)", width: "10%", field: "waitDuration"},
                {name: "经营厂商", width: "10%", field: "operationBrand"},
                {name: "被叫400", width: "10%", field: "fooPhone"},
                {name: "是否成功", width: "10%", field: "isSuccess"},
                {name: "主叫省份", width: "10%", field: "callerProvince"},
                {name: "主叫城市", width: "10%", field: "callerCity"},
                {name: "400状态", width: "10%", field: "fooStatus"},
                {name: "谁先挂断", width: "10%", field: "cutoff"},
                {name: "价格", width: "10%", field: "referPrice"},
                {name: "年份", width: "10%", field: "yearType"},
                {name: "平台", width: "10%", field: "mediaSource"},
                {name: "活动渠道", width: "10%", field: "campaignChannelId"},
                {name: "LMCID", width: "10%", field: "localToDealerId"},
                {name: "LMC处理时间", width: "10%", field: "importDate"},
                {name: "SAP文件状态", width: "10%", field: "_sapFileStatus"},
                {name: "SAP文件名", width: "10%", field: "sapFileName"},
                {name: "SAP文件生成时间", width: "10%", field: "sapFileProcTime"},
                {name: "关联ID", width: "10%", field: "refId"},
            ],
            rowOperation: {show: false}
        }
    };
    var formGridEvents = {
        grid: {
			operationEvents: [
				{
					name: "导出查询结果", icon: 'daochuuexport', class: "btn-primary", click: function () {
					exportExcel();
				}
			}],
            fieldEvents: {
            	'_userGenderFormat':function(val,row){
                    var value = row['userGender'];
                    if(value==0){
                        return '女';
                    }else if(value==1){
                        return '男';
                    }else if(value==-1){
                        return '未知';
                    }else {
                        return value;
                    }
                },
                '_dealerFollowStatusFormat':function(val,row){
                    var value = row['dealerFollowStatus'];
                    if(value==0){
                        return '未跟进';
                    }else if(value==1){
                        return '已跟进';
                    }else if(value==2){
                        return '无效';
                    }else {
                        return value;
                    }
                },
                '_sapFileStatusFormat':function(val,row){
                    var value = row['sapFileStatus'];
                    if(value==0){
                        return '未生成';
                    }else if(value==1){
                        return '已生成';
                    }else {
                        return value;
                    }
                }
            }
        }
    };

    $scope.dbFormGrid = {options: formGridOptions, events: formGridEvents};
    
	function exportExcel() {
		var totalRecords = $scope.dbFormGrid.page.totalRecords;
		if (!totalRecords) {
			dbUtils.warning("无查询数据记录不导出！", "温馨提示");
			return;
		}
		dbUtils.confirm("确定要以当前查询条件下的结果数据执行导出操作?", function () {
			var params = $scope.dbFormGrid.getQueryParams(1);
			var orderId = params['orderId'] || "";
			var campaignChannelId = params['campaignChannelId'] || "";
			var importDateStartDate = params['importDateStartDate'] || "";
			var importDateEndDate = params['importDateEndDate'] || "";
			var localToDealerId = params['localToDealerId'] || "";
			var refId = params['refId'] || "";
			var orderBusinessOpportunityCreateTimeStartDate = params['orderBusinessOpportunityCreateTimeStartDate'] || "";
			var orderBusinessOpportunityCreateTimeEndDate = params['orderBusinessOpportunityCreateTimeEndDate'] || "";
			var url = '../export/order/orderTrans.do';
			url += '?orderId=' + orderId;
			url += '&campaignChannelId=' + campaignChannelId;
			url += '&importDateStartDate=' + importDateStartDate;
			url += '&importDateEndDate=' + importDateEndDate;
			url += '&refId=' + refId;
			url += '&orderBusinessOpportunityCreateTimeStartDate=' + orderBusinessOpportunityCreateTimeStartDate;
			url += '&orderBusinessOpportunityCreateTimeEndDate=' + orderBusinessOpportunityCreateTimeEndDate;
			console.log(url);
			$window.open(url);
		});
	}
    
}]);
