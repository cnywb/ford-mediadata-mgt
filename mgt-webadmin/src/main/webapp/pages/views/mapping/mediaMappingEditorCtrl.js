var DBApp = angular.module('DBApp');

DBApp.controller("mediaMappingEditorCtrl", ['$scope', '$resource', '$modalInstance', 'dbUtils', 'mediaMapping', DealerEditorCtrl]);

function DealerEditorCtrl($scope, $resource, $modalInstance, dbUtils, mediaMapping) {

    $scope.dbForm = {
        settings: {showClose: true, transCode: "editMediaMapping", cols: 2},
        title: {icon: "luru", label: "渠道映射"},
        sections: [{
            sectionTitle: {show: true, icon: "touxiang", label: "渠道映射信息"},
            fields: [
                {"name": "mediaType", "label": "渠道标识", "type": "select", "labelCols": "4", "editable": true, "required": true, "placeholder": "", "cols": "6", "dropDownItemType": "json", "dropDownItem": "mediaType"},
                {"name": "mappingType", "label": "映射类型", "type": "select", "labelCols": "4", "editable": true, "required": true, "placeholder": "", "cols": "6", "dropDownItemType": "json", "dropDownItem": "mappingType"},
                {"name": "mediaValue", "label": "渠道值", "type": "text", "labelCols": "4", "editable": true, "required": true, "placeholder": "", "cols": "6"},
                {"name": "sysValue", "label": "系统值", "type": "text", "labelCols": "4", "editable": true, "required": true, "placeholder": "", "cols": "6"},
                {"name": "description", "label": "说明", "type": "text", "labelCols": "4", "editable": true, "required": false, "placeholder": "", "cols": "6"}]
        }],
        originData: mediaMapping,
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