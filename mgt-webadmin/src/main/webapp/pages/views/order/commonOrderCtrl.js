/**
 * Created by Administrator on 2016/10/3.
 */
angular.module('DBApp').controller("commonOrderCtrl", ['$scope', 'dbUtils', '$window', function ($scope, dbUtils, $window) {

	var formGridOptions = {
		form: {
			settings: {
				cols: 3,
				//showClose:true
			},
			fields: [
				{"name": "id","type": "text","label": "ID", "labelCols": "4"},
				{"name": "createTime","type": "dateRange","label": "商机创建时间", "labelCols": "4"},
				{"name": "createdDate","type": "dateRange","label": "数据创建时间", "labelCols": "4"},
				{"name": "updatedDate","type": "dateRange","label": "数据更新时间", "labelCols": "4"},
				{"name": "orderId", "label" : "订单类商机ID", "type" : "text", "placeholder" : "订单类商机ID"},
				{"name": "orderTypeName", "label" : "订单类型名称", "type" : "text", "placeholder" : "订单类型名称"},
				{"name": "userName", "label" : "订购者名称", "type" : "text", "placeholder" : "订购者名称"},
				{"name": "accountName", "label" : "销售顾问", "type" : "text", "placeholder" : "销售顾问"},
				{"name": "cityName", "label" : "市名称", "type" : "text", "placeholder" : "市名称"},
				{"name": "provinceName", "label" : "省名称", "type" : "text", "placeholder" : "省名称"},
				{"name": "status", "label" : "状态", "type" : "select","dropDownItemType":"json","dropDownItem":"commonorderstatusSelect", "placeholder" : "状态","labelCols":"4"}
			]
		},
		grid: {
			settings: {
				transCode: 'commonOrderPage',
				autoLoad: true,
				page: {pageSize: 10},
				showCheckBox: false
			},
			header: [
            	{"name" : "ID", "width" : "10%", "field" : "id",lineFeed: true},
				{"name" : "订单类商机ID", "width" : "10%", "field" : "orderId", lineFeed: true},
				{"name" : "数据创建时间", "width" : "10%", "field" : "createdDate",lineFeed: true},
				{"name" : "数据更新时间", "width" : "10%", "field" : "updatedDate",lineFeed: true},
				{"name" : "订单类型", "width" : "10%", "field" : "orderTypeName",lineFeed: true},
				{"name" : "销售顾问", "width" : "10%", "field" : "accountName",lineFeed: true},
				{"name" : "订购者名称", "width" : "10%", "field" : "userName",lineFeed: true},
				{"name" : "订购者电话", "width" : "10%", "field" : "userMobile",lineFeed: true},
				{"name" : "订购者性别", "width" : "10%", "field" : "userGender",lineFeed: true},
				{"name" : "订购者邮件", "width" : "10%", "field" : "userMail",lineFeed: true},
				{"name" : "订购车名", "width" : "10%", "field" : "carName",lineFeed: true},
				{"name" : "订购价格", "width" : "10%", "field" : "yeferPrice",lineFeed: true},
				{"name" : "车型颜色", "width" : "10%", "field" : "carColor",lineFeed: true},
				{"name" : "上市年份", "width" : "10%", "field" : "yearType",lineFeed: true},
				{"name" : "经销商名称", "width" : "10%", "field" : "dealerName",lineFeed: true},
				{"name" : "订单备注信息", "width" : "10%", "field" : "orderRemark",lineFeed: true},
				{"name" : "城市名称", "width" : "10%", "field" : "cityName",lineFeed: true},
				{"name" : "省份名称", "width" : "10%", "field" : "provinceName",lineFeed: true},
				{"name" : "状态", "width" : "10%", "field" : "status",lineFeed: true},
				{"name" : "订单类商机创建时间", "width" : "10%", "field" : "orderBusinessOpportunityCreateTime",lineFeed: true},
				{"name" : "订购车品牌", "width" : "10%", "field" : "brandName",lineFeed: true},
				{"name" : "订购车型", "width" : "10%", "field" : "csName",lineFeed: true},
				{"name" : "车款", "width" : "10%", "field" : "carTypeName",lineFeed: true}
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
			rowEvents: [{
				name: '再次执行', class: 'btn-primary', icon: 'tianjia',isDisabled:function(row){
					var status=row['status'];
					if(status==3){
						return false;
					}
					return true;
				}, click: function (row) {
					retryModal(row);
				}
			}],
			fieldEvents: {
				'userGenderFormat':function(val,row){
					var value = row['userGender'];
					if(value == 0){
						return '女';
					}else if(value == 1){
						return '男';
					}else if(value == -1){
						return '未知';
					}else {
						return "";
					}
				},
				'statusFormat':function(val,row){
					var value = row['status'];
					if(value==0){
						return '未处理';
					}else if(value==1){
						return '已处理';
					}else {
						return "";
					}
				},
			}

		}
	};

	$scope.dbFormGrid = {options: formGridOptions, events: formGridEvents};

	//再次执行
	function retryModal(source){
		dbUtils.confirm('确认是否再次执行?', function () {
			dbUtils.post('batchTaskHandle', {id: source.id,status:'1'}, function () {
				dbUtils.success('已修改为待处理状态');
				$scope.dbFormGrid.reLoadData();
			});
		});
	}
	function exportExcel() {
		var totalRecords = $scope.dbFormGrid.page.totalRecords;
		if (!totalRecords) {
			dbUtils.warning("无查询数据记录不导出！", "温馨提示");
			return;
		}
		dbUtils.confirm("确定要以当前查询条件下的结果数据执行导出操作?", function () {
			var params = $scope.dbFormGrid.getQueryParams(1);

            var id = params['id'] || "";
            
			var orderTypeName = params['orderTypeName'] || "";
			var userName = params['userName'] || "";
			var accountName = params['accountName'] || "";
			var cityName = params['cityName'] || "";
			var provinceName = params['provinceName'] || "";
			var status = params['status'] || "";
			var orderId = params['orderId'] || "";
			var createTimeStartDate = params['createTimeStartDate'] || "";
			var createTimeEndDate = params['createTimeEndDate'] || "";

			var createdDateStartDate = params['createdDateStartDate'] || "";
			var createdDateEndDate = params['createdDateEndDate'] || "";
			var updatedDateStartDate = params['updatedDateStartDate'] || "";
			var updatedDateEndDate = params['updatedDateEndDate'] || "";

			var url = '../export/order/commomOrder.do?orderTypeName=' + orderTypeName + '&userName=' + userName + '&accountName=' + accountName+ '&cityName=' + cityName+ '&provinceName=' + provinceName + '&status=' + status+ '&orderId=' + orderId + '&createTimeStartDate='+createTimeStartDate + '&createTimeEndDate='+createTimeEndDate
				+ '&createdDateStartDate='+createdDateStartDate + '&createdDateEndDate='+createdDateEndDate
				+ '&updatedDateStartDate='+updatedDateStartDate + '&updatedDateEndDate='+updatedDateEndDate
				+ '&id='+id;
			$window.open(url);
		});
	}
}]);