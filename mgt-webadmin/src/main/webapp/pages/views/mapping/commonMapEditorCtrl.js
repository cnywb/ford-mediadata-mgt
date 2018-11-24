var DBApp = angular.module('DBApp');

DBApp.controller("commonMapEditorCtrl", ['$scope', '$resource', '$modalInstance', 'dbUtils', 'commonMap', DealerEditorCtrl]);

function DealerEditorCtrl($scope, $resource, $modalInstance, dbUtils, commonMap) {

    $scope.dbForm = {
        settings: {showClose: true, transCode: "editCommonMap", cols: 2},
        title: {icon: "luru", label: "参数"},
        sections: [{
            sectionTitle: {show: true, icon: "touxiang", label: "参数信息"},
            fields: [
                {"name": "mapKey", "label": "KEY", "type": "text", "labelCols": "4", "editable": false, "required": true, "placeholder": "", "cols": "6"},
                {"name": "mapValue", "label": "VALUE", "type": "text", "labelCols": "4", "editable": true, "required": true, "placeholder": "", "cols": "6"}]
        }],
        originData: commonMap,
        events: {
            beforeSubmit: function (reqBody) {
                return true;
            },
            afterSubmit: function (data) {
                dbUtils.success("更新成功");
                $modalInstance.close();
            },
            modalClose: function () {
                $modalInstance.dismiss("cancel");
            }
        }
    };

}