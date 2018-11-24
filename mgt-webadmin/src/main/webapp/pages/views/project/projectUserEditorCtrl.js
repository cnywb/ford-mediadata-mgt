var DBApp = angular.module('DBApp');

DBApp.controller("projectUserEditorCtrl", ['$scope', '$modalInstance', 'dbUtils', 'dbCityTreeOption', 'projectUser', 'project', ProjectUserEditorCtrl]);

function ProjectUserEditorCtrl($scope, $modalInstance, dbUtils, dbCityTreeOption, projectUser, project) {

	$scope.provinceOptions = dbCityTreeOption.get();
	$scope.cityOptions = [];
	if(!angular.isUndefined(projectUser) && !angular.isUndefined(projectUser.eventProvince)){
		angular.forEach($scope.provinceOptions, function (provinceOption) {
			if(provinceOption.value == projectUser.eventProvince){
				$scope.cityOptions = provinceOption.children;
				return;
			}
		});
	}
	
    $scope.dbForm = {
        settings: {showClose: true, transCode: "editProjectUser", cols: 2},
        title: {icon: "luru", label: "用户信息"},
        sections: [{
            sectionTitle: {show: true, icon: "touxiang", label: "用户信息"},
            fields: [
                {"name": "username", "label": "用户名", "type": "text", "labelCols": "4", "editable": false, "required": true, "placeholder": "用户名", "cols": "6"},
                {"name": "password", "label": "密码", "type": "text", "labelCols": "4", "editable": true, "required": true, "placeholder": "密码", "cols": "6"},
                {"name": "eventProvince", "label": "活动省份", "type": "select", "labelCols": "4", "editable": true, "required": false, "cols": "6", "dropDownItem": $scope.provinceOptions},
                {"name": "eventCity", "label": "活动城市", "type": "select", "labelCols": "4", "editable": true, "required": false, "cols": "6", "dropDownItem": $scope.cityOptions},
                {"name": "serial", "label": "活动场次", "type": "select", "labelCols": "4", "editable": true, "required": false, "cols": "6", "dropDownItemType": "json", "dropDownItem": "projectUserSerial"},
                {"name": "role", "label": "角色", "type": "select", "labelCols": "4", "editable": true, "required": true, "cols": "6", "dropDownItemType": "json", "dropDownItem": "projectUserRole"}]
        }],
        originData: projectUser,
        events: {
        	eventProvinceSelect: function(item){
        		if(angular.isUndefined(item) || angular.isUndefined(item.children)){
	        		$scope.dbForm.sections[0].fields[3].dropDownItem = [];
	        		$scope.dbForm.sections[0].fields[3].dropDownItemValue = [];
        		}else{
	        		$scope.dbForm.sections[0].fields[3].dropDownItem = item.children;
	        		$scope.dbForm.sections[0].fields[3].dropDownItemValue = item.children;
        		}
        		$scope.dbForm.formData["eventCity"] = "";
        	},
            beforeSubmit: function (reqBody) {
            	reqBody['projectCode'] = project.code;
                return true;
            },
            afterSubmit: function (data) {
                dbUtils.success("用户信息更新成功");
                $modalInstance.close();
            },
            modalClose: function () {
                $modalInstance.dismiss("cancel");
            }
        }
    };

}