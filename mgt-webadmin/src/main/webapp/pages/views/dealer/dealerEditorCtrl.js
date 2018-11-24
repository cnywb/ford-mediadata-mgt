var DBApp = angular.module('DBApp');

DBApp.controller("dealerEditorCtrl", ['$scope', '$resource', '$modalInstance', 'dbUtils', 'dbCityTreeOption', 'dealer', DealerEditorCtrl]);

function DealerEditorCtrl($scope, $resource, $modalInstance, dbUtils, dbCityTreeOption, dealer) {

	$scope.provinceOptions = dbCityTreeOption.get();
	$scope.cityOptions = [];
	if(!angular.isUndefined(dealer) && !angular.isUndefined(dealer.province)){
		angular.forEach($scope.provinceOptions, function (provinceOption) {
			if(provinceOption.value == dealer.province){
				$scope.cityOptions = provinceOption.children;
				return;
			}
		});
	}

    $scope.dbForm = {
        settings: {showClose: true, transCode: "editDealer", cols: 2},
        title: {icon: "luru", label: "经销商信息"},
        sections: [{
            sectionTitle: {show: true, icon: "touxiang", label: "经销商信息"},
            fields: [
                {"name": "province", "label": "省份", "type": "select", "labelCols": "4", "editable": true, "required": true, "cols": "6", "dropDownItem": $scope.provinceOptions},
                {"name": "city", "label": "城市", "type": "select", "labelCols": "4", "editable": true, "required": true, "cols": "6", "dropDownItem": $scope.cityOptions},
                {"name": "code", "label": "经销商代码", "type": "text", "labelCols": "4", "editable": false, "required": true, "placeholder": "经销商代码", "cols": "6"},
                {"name": "name", "label": "经销商名称", "type": "text", "labelCols": "4", "editable": true, "required": true, "placeholder": "经销商名称", "cols": "6"}]
        }],
        originData: dealer,
        events: {
        	provinceSelect: function(item){
        		if(angular.isUndefined(item) || angular.isUndefined(item.children)){
	        		$scope.dbForm.sections[0].fields[1].dropDownItem = [];
	        		$scope.dbForm.sections[0].fields[1].dropDownItemValue = [];
        		}else{
	        		$scope.dbForm.sections[0].fields[1].dropDownItem = item.children;
	        		$scope.dbForm.sections[0].fields[1].dropDownItemValue = item.children;
        		}
        		$scope.dbForm.formData["city"] = "";
        	},
            beforeSubmit: function (reqBody) {
                return true;
            },
            afterSubmit: function (data) {
                dbUtils.success("经销商信息更新成功");
                $modalInstance.close();
            },
            modalClose: function () {
                $modalInstance.dismiss("cancel");
            }
        }
    };

}