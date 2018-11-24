var DBApp = angular.module('DBApp');

DBApp.controller("projectCityEditorCtrl", ['$scope', '$resource', 'dbUtils', 'project', '$modalInstance', ProjectCityEditorCtrl]);

function ProjectCityEditorCtrl($scope, $resource, dbUtils, project, $modalInstance) {
	
	var provinceCityTreeResource = $resource(Metronic.getResourcesPath() + "js/db-province-city-tree.json");

    $scope.dbTree = {settings: {useCheckBox: true, treeScrollHeight: "300px", noCache: true}};

    doGetCityData();

    function doGetCityData() {
        dbUtils.post("listProjectCity", {projectCode: project['code']}, function (selectedProjectCityList) {
        	provinceCityTreeResource.query().$promise.then(function (provinceCityTree) {
                initCityTree(provinceCityTree, selectedProjectCityList);
            });
        });
    }

    //初始化树形结构的数据
    function initCityTree(provinceCityTree, selectedProjectCityList) {
    	if(selectedProjectCityList.length > 0){
    		provinceCityTree[0].selected = true;
    	}
    	//设置选中
    	angular.forEach(selectedProjectCityList, function (selectedProjectCity) {
    		angular.forEach(provinceCityTree[0].children, function (province) {
    			if(!angular.isUndefined(province.attr)
    					&&province.attr.province == selectedProjectCity.province){
    				province.selected = true;
    	    		angular.forEach(province.children, function (city) {
		    			if(!angular.isUndefined(city.attr)
		    					&&city.attr.province == selectedProjectCity.province
		    					&&city.attr.city == selectedProjectCity.city){
		    				city.selected = true;
		    			}
    	            });
    			}
            });
        });
        //渲染树结构
        if ($scope.dbTree) {
            $scope.dbTree.setData(provinceCityTree);
        } else {
            $scope.dbTree = {
                data: provinceCityTree
            }
        }
    }

    $scope.closeModal = function () {
        var selectedProjectCityList = $scope.dbTree.getAllSelectedData();
        dbUtils.confirm("<span style='color: red' >确定要保存项目活动城市?</span>", function () {
            dbUtils.post("editProjectCity", {projectCode: project['code'], selectedProjectCityList: selectedProjectCityList}, function () {
                dbUtils.success("保存成功");
                doGetCityData();
            }, function (error) {
                dbUtils.error(error);
            });
        })
    }

    $scope.modalClose = function () {
        $modalInstance.dismiss("cancer");
    }
}