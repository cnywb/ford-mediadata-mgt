var DBApp = angular.module('DBApp');

DBApp.controller("projectEditorCtrl", ['$scope', '$modalInstance', 'dbUtils', 'project', ProjectEditorCtrl]);

function ProjectEditorCtrl($scope, $modalInstance, dbUtils, project) {

    $scope.dbForm = {
        settings: {showClose: true, transCode: "editProject", cols: 2},
        title: {icon: "luru", label: "项目活动信息"},
        sections: [{
            sectionTitle: {show: true, icon: "touxiang", label: "项目活动信息"},
            fields: [
                {"name": "code", "label": "项目活动代码", "type": "text", "labelCols": "4", "editable": false, "required": true, "placeholder": "项目活动代码", "cols": "6"},
                {"name": "name", "label": "项目活动名称", "type": "text", "labelCols": "4", "editable": true, "required": true, "placeholder": "项目活动名称", "cols": "6"},
                {"name": "beginTime", "label": "开始时间", "type": "date", "labelCols": "4", "editable": true, "required": true, "placeholder": "开始时间", "cols": "6"},
                {"name": "endTime", "label": "结束时间", "type": "date", "labelCols": "4", "editable": true, "required": true, "placeholder": "结束时间", "cols": "6"}]
        }],
        originData: project,
        events: {
            beforeSubmit: function (reqBody) {
                return true;
            },
            afterSubmit: function (data) {
                dbUtils.success("项目活动信息更新成功");
                $modalInstance.close();
            },
            modalClose: function () {
                $modalInstance.dismiss("cancel");
            }
        }
    };

}