var DBApp = angular.module('DBApp');

DBApp.controller("projectUserDealerEditorCtrl", ['$scope', '$resource', 'dbUtils', 'projectUser', '$modalInstance', ProjectUserDealerEditorCtrl]);

function ProjectUserDealerEditorCtrl($scope, $resource, dbUtils, projectUser, $modalInstance) {
	
	var provinceCityTreeResource = $resource(Metronic.getResourcesPath() + "js/db-province-city-tree.json");

    $scope.dbTree = {settings: {useCheckBox: true, treeScrollHeight: "300px", noCache: true}};

    initTree();

    function initTree() {
        dbUtils.post("getProjectUserDealerTree", {projectUserId: projectUser.id}, function (projectUserDealerTree) {
        	console.log(projectUserDealerTree);
            if ($scope.dbTree) {
                $scope.dbTree.setData([projectUserDealerTree]);
            } else {
                $scope.dbTree = {
                    data: [projectUserDealerTree]
                }
            }
        });
    }

    $scope.closeModal = function () {
        var selectedDealerList = $scope.dbTree.getAllSelectedData();
        dbUtils.confirm("<span style='color: red' >确定要保存关联经销商?</span>", function () {
        	console.log(selectedDealerList);
            dbUtils.post("editProjectUserDealer", {projectUserId: projectUser.id, selectedDealerList: selectedDealerList}, function () {
                dbUtils.success("保存成功");
                initTree();
            }, function (error) {
                dbUtils.error(error);
            });
        })
    }

    $scope.modalClose = function () {
        $modalInstance.dismiss("cancer");
    }
}