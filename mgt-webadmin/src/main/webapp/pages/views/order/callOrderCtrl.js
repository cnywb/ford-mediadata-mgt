/**
 * Created by Administrator on 2016/10/3.
 */

angular.module('DBApp').controller("callOrderCtrl", ['$scope', 'dbUtils', '$window', function ($scope, dbUtils, $window) {
    var formGridOptions = {
        form: {
            settings: {
                cols: 3,
                showClose:true
            },
            fields: [
				{"name": "id","type": "text","label": "ID", "labelCols": "4"},
                {"name": "createTime","type": "dateRange","label": "商机创建时间", "labelCols": "4"},
                {"name": "createdDate","type": "dateRange","label": "数据创建时间", "labelCols": "4"},
                {"name": "updatedDate","type": "dateRange","label": "数据更新时间", "labelCols": "4"},
                {"name": "callBusinessOpportunityId", "label" : "通话类商机Id", "type" : "text", "placeholder" : "通话类商机Id"},
                {"name": "agentName", "label" : "座席", "type" : "text", "placeholder" : "座席"},
                {"name": "cityName", "label" : "城市名称", "type" : "text", "placeholder" : "城市名称"},
                {"name": "provinceName", "label" : "省份名称", "type" : "text", "placeholder" : "省份名称"},
                {"name": "dealerFollowStatus", "label" : "状态", "type" : "select","dropDownItemType":"json","dropDownItem":"callorderstatusSelect", "placeholder" : "状态","labelCols":"4"}
            ]
        },
        grid: {
            settings: {
                transCode: 'callOrderPage',
                autoLoad: true,
                page: {pageSize: 10},
                showCheckBox: false
            },
            header: [
            	{"name" : "ID", "width" : "10%", "field" : "id",lineFeed: true},
                {"name" : "通话类商机ID", "width" : "10%", "field" : "callBusinessOpportunityId",lineFeed: true},
                {"name" : "数据创建时间", "width" : "10%", "field" : "createdDate",lineFeed: true},
                {"name" : "数据更新时间", "width" : "10%", "field" : "updatedDate",lineFeed: true},
                {"name" : "经销商代码", "width" : "10%", "field" : "dealerId",lineFeed: true},
                {"name" : "座席", "width" : "10%", "field" : "agentName",lineFeed: true},
                {"name" : "主叫电话号码", "width" : "10%", "field" : "callerPhoneNumber",lineFeed: true},
                {"name" : "被叫电话号码", "width" : "10%", "field" : "calleeRealNumber",lineFeed: true},
                {"name" : "等待时长", "width" : "10%", "field" : "waitDuration",lineFeed: true},
                {"name" : "通话开始时间", "width" : "10%", "field" : "callBeginTime",lineFeed: true},
                {"name" : "通话结束时间", "width" : "10%", "field" : "callFinishTime",lineFeed: true},
                {"name" : "通话时长", "width" : "10%", "field" : "callerDuration",lineFeed: true},
                {"name" : "跟近时间", "width" : "10%", "field" : "fistFollowTime",lineFeed: true},
                {"name" : "跟近状态", "width" : "10%", "field" : "dealerFollowStatus",lineFeed: true},
                {"name" : "城市名称", "width" : "10%", "field" : "cityName",lineFeed: true},
                {"name" : "省份名称", "width" : "10%", "field" : "provinceName",lineFeed: true},
                {"name" : "通话类商机创建时间", "width" : "10%", "field" : "callBusinessOpportunityCreateTime",lineFeed: true},
                {"name" : "订购价格", "width" : "10%", "field" : "referPrice",lineFeed: true},
                {"name" : "上市年份", "width" : "10%", "field" : "yearType",lineFeed: true}
            ],
            rowOperation: {show:false}
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
                'dealerFollowStatusFormat':function(val,row){
                    var value =row['dealerFollowStatus'];
                    if(value==0){
                        return '未跟进';
                    }else if(value==1){
                        return '已跟进';
                    }else if(value==2){
                        return '无效';
                    }else {
                        return "";
                    }
                },
            }
        }
    };
    $scope.dbFormGrid = {options: formGridOptions, events: formGridEvents};
    //打开modal
    function openModal(source) {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'db/db-form.html',
            controller: 'batchTaskEditorCtrl',
            size: "md",
            backdrop: "static",
            resolve: {
                source: function () {
                    return source;

                }
            }
        });
        instance.result.then(function () {
            $scope.dbFormGrid.reLoadData();
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
            
            var agentName = params['agentName'] || "";
            var cityName = params['cityName'] || "";
            var provinceName = params['provinceName'] || "";
            var dealerFollowStatus = params['dealerFollowStatus'] || "";
            var callBusinessOpportunityId = params['callBusinessOpportunityId'] || "";
            var createTime = params['callBusinessOpportunityCreateTime'] || "";
            var createdDate = params['createdDate'] || "";
            var updatedDate = params['updatedDate'] || "";
            var createTimeStartDate = params['createTimeStartDate'] || "";
            var createTimeEndDate = params['createTimeEndDate'] || "";

            var createdDateStartDate = params['createdDateStartDate'] || "";
            var createdDateEndDate = params['createdDateEndDate'] || "";
            var updatedDateStartDate = params['updatedDateStartDate'] || "";
            var updatedDateEndDate = params['updatedDateEndDate'] || "";

            var url = '../export/order/callOrder.do?agentName=' + agentName + '&cityName=' + cityName
                    + '&provinceName='+provinceName + '&pdealerFollowStatus='+dealerFollowStatus + '&callBusinessOpportunityId='+callBusinessOpportunityId + '&createTime='+createTime + '&createdDate='+createdDate + '&updatedDate='+updatedDate
                + '&createTimeStartDate='+createTimeStartDate + '&createTimeEndDate='+createTimeEndDate
                + '&createdDateStartDate='+createdDateStartDate + '&createdDateEndDate='+createdDateEndDate
                + '&updatedDateStartDate='+updatedDateStartDate + '&updatedDateEndDate='+updatedDateEndDate
                + '&id='+id;
            $window.open(url);
        });
    }
}]);