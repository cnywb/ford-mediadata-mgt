var DBApp = angular.module('DBApp');

DBApp.controller("projectUserBatchCreatorCtrl", ['$scope', '$modalInstance', 'dbUtils', 'dbCityTreeOption', 'project', ProjectUserBatchCreatorCtrl]);

function ProjectUserBatchCreatorCtrl($scope, $modalInstance, dbUtils, dbCityTreeOption, project) {
	
	$scope.provinceOptions = dbCityTreeOption.get();
	$scope.cityOptions = [];
	
    $scope.dbForm = {
        settings: {showClose: true, transCode: "batchCreateProjectUser", cols: 2},
        title: {icon: "luru", label: "批量创建用户"},
        sections: [{
            sectionTitle: {show: true, icon: "touxiang", label: "用户信息"},
            fields: [
                {"name": "eventProvince", "label": "活动省份", "type": "select", "labelCols": "4", "editable": true, "required": false, "cols": "6", "dropDownItem": $scope.provinceOptions},
                {"name": "eventCity", "label": "活动城市", "type": "select", "labelCols": "4", "editable": true, "required": false, "cols": "6", "dropDownItem": $scope.cityOptions},
                {"name": "serial", "label": "活动场次", "type": "select", "labelCols": "4", "editable": true, "required": false, "cols": "6", "dropDownItemType": "json", "dropDownItem": "projectUserSerial"},
                {"name": "role", "label": "角色", "type": "select", "labelCols": "4", "editable": true, "required": true, "cols": "6", "dropDownItemType": "json", "dropDownItem": "projectUserRole"},
                {"name": "userNamePrefix", "label": "用户名前缀", "type": "text", "labelCols": "4", "editable": false, "required": true, "placeholder": "用户名前缀，如“AN”", "cols": "6"},
                {"name": "password", "label": "密码", "type": "text", "labelCols": "4", "editable": true, "required": true, "placeholder": "密码", "cols": "6"},
                {"name": "beginIndex", "label": "用户编号起始", "type": "text", "labelCols": "4", "editable": true, "required": true, "placeholder": "填写数字，如“1”", "cols": "6"},
                {"name": "endIndex", "label": "用户编号结束", "type": "text", "labelCols": "4", "editable": true, "required": true, "placeholder": "填写数字，如“20”", "cols": "6"},
                {"name": "dealerCodes", "label": "关联经销商代码", "type": "text", "labelCols": "2", "editable": true, "required": false, "placeholder": "关联经销商代码，多个经销商以“,”分割，如“A06152,A33805”", "cols": "12"}]
        }],
        originData: null,
        events: {
        	eventProvinceSelect: function(item){
        		if(angular.isUndefined(item) || angular.isUndefined(item.children)){
	        		$scope.dbForm.sections[0].fields[1].dropDownItem = [];
	        		$scope.dbForm.sections[0].fields[1].dropDownItemValue = [];
        		}else{
	        		$scope.dbForm.sections[0].fields[1].dropDownItem = item.children;
	        		$scope.dbForm.sections[0].fields[1].dropDownItemValue = item.children;
        		}
        		$scope.dbForm.formData["eventCity"] = "";
        	},
            beforeSubmit: function (reqBody) {
            	reqBody['projectCode'] = project.code;
                return true;
            },
            afterSubmit: function (data) {
                dbUtils.success("批量创建用户成功");
                $modalInstance.close();
            },
            modalClose: function () {
                $modalInstance.dismiss("cancel");
            }
        }
    };

}