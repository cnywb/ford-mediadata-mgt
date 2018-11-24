DBApp.controller('fileUploadModalCtrl', ['$scope', '$modalInstance', 'FileUploader', 'dbUtils', 'uploadConfig', function ($scope, $modalInstance, FileUploader, dbUtils, uploadConfig) {
    var uploader = $scope.uploader = new FileUploader({
        url: uploadConfig.url
    });

    uploader.filters.push({
        name: 'excelFilter',
        fn: function (item, options) {
        	//TODO
            return true;
        }
    });

    // CALLBACKS

    uploader.onWhenAddingFileFailed = function(item, filter, options) {
        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
        console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function(progress) {
        console.info('onProgressAll', progress);
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function (fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
        if (status == 200) {
            fileItem.message = response.message;
            fileItem.dataSize = response.dataSize;
        	if(response.success){
        		dbUtils.success(response.message);
        		fileItem.isSuccess = true;
        		fileItem.isError = false;
        		return;
        	}else{
        		dbUtils.error(response.message);
        		fileItem.isSuccess = false;
        		fileItem.isError = true;
        		fileItem.progress = 0;
        		return;
        	}
        } else {
            dbUtils.error("后台服务异常，请稍后再试");
            fileItem.message = "后台服务异常，请稍后再试";
            fileItem.dataSize = 0;
    		fileItem.isSuccess = false;
    		fileItem.isError = true;
    		fileItem.progress = 0;
        }
    };
    uploader.onCompleteAll = function () {
        console.info('onCompleteAll');
    };

    /* 页面关闭按钮事件 */
    $scope.modalClose = function () {
        $modalInstance.dismiss('cancel');
    };
    
}]);