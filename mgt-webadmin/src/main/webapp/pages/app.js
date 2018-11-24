/***
 Metronic AngularJS App Main Script
 ***/

/* Metronic App */
var DBApp = angular.module('DBApp', [
    'ui.router',
    'ui.bootstrap',
    'oc.lazyLoad',
    'db.components.grid',
    'db.components.form.grid',
    'db.components.report.grid',
    'db.components.data.table',
    'db.components.data.grid',
    'db.components.form',
    'db.components.form.fields',
    'db.components.orgTree',
    'db.components.resourceTree',
    'db.components.areaTree',
    'db.components.tree',
    'db.components.monthselect',
    'db.components.selectCity',
    'ui.select',
    'ngSanitize',
    'dbImService',
    'dbCityTreeOption',
    'dbUtils',
    'ngAnimate',
    'toaster',
    'angularFileUpload'
]);

/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
DBApp.config(['$ocLazyLoadProvider', '$httpProvider', function ($ocLazyLoadProvider, $httpProvider) {
    $ocLazyLoadProvider.config({
        debug: true
    });
    //添加请求头，防止后台无法判断是ajax请求
    $httpProvider.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest'
}]);

/* Setup Rounting For All Pages */
DBApp.config(['$stateProvider', '$urlRouterProvider', StateConfigController]);

/* Setup global settings */
DBApp.factory('settings', ['$rootScope', function ($rootScope) {
    // supported languages
    var settings = {
        layout: {
            pageSidebarClosed: false, // sidebar state
            pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
        },
        layoutImgPath: Metronic.getAssetsPath() + 'admin/layout/img/',
        layoutCssPath: Metronic.getAssetsPath() + 'admin/layout/css/'
    };
    $rootScope.settings = settings;

    return settings;
}]);
/*UI-SELECT多选框使用到的过滤器*/
DBApp.filter('selectPropsFilter', function () {
    return function (items, props) {
        var out = [];
        if (angular.isArray(items)) {
            items.forEach(function (item) {
                var itemMatches = false;

                var keys = Object.keys(props);
                for (var i = 0; i < keys.length; i++) {
                    var prop = keys[i];
                    var text = props[prop].toLowerCase();
                    if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                        itemMatches = true;
                        break;
                    }
                }

                if (itemMatches) {
                    out.push(item);
                }
            });
        } else {
            out = items;
        }
        return out;
    };
});

/* Setup App Main Controller */
DBApp.controller('AppController', ['$scope', '$rootScope', AppController]);

function AppController($scope) {
    $scope.$on('$viewContentLoaded', function () {
        Metronic.initComponents(); // init core components
        //Layout.init(); //  Init entire layout(header, footer, sidebar, etc) on page load if the partials included in server side instead of loading with ng-include directive 
    });
}

/***
 Layout Partials.
 By default the partials are loaded through AngularJS ng-include directive. In case they loaded in server side(e.g: PHP include function) then below partial
 initialization can be disabled and Layout.init() should be called on page load complete as explained above.
 ***/

/* Setup Layout Part - Header */
DBApp.controller('HeaderController', ['$scope', '$window', '$http', '$state', 'dbUtils','$modal',  function ($scope, $window, $http, $state, dbUtils,$modal) {
    $scope.$on('$includeContentLoaded', function () {
        Layout.initHeader(); // init header
    });
    $scope.userInfo = dbUtils.getUserInfo();
    $scope.logout = function () {
        dbUtils.confirm('确定退出系统吗?', function () {
            $http.post('../logout.do', {}).success(function (data, status, headers, config) {
                $window.sessionStorage.setItem('loginName', "");
                $window.location.href = '../';//退出后直接返回主页
            });
        });
    };
    $scope.reBackIndex = function () {
        $state.go('dashboard');
    };
    $scope.changePwd = function () {
        var instance = $modal.open({
            animation: true,
            templateUrl: 'views/security/changePwd.html',
            controller: ['$scope', 'dbUtils', '$modalInstance', 'loginName', function ($scope, dbUtils, $modalInstance, loginName) {
                $scope.data = {
                    loginName: loginName,
                    password: null,
                    checkPassword: null
                };
                //监听 密码 和确认密码是否一致。
                angular.forEach(['data.password', 'data.checkPassword'], function (item) {
                    $scope.$watch(item, function (newVal, oldVal) {
                        if (newVal !== oldVal) {
                            var password = $scope.data.password;
                            var checkPassword = $scope.data.checkPassword;
                            $scope.passwordMsg = "";
                            if (password != checkPassword) {
                                $scope.passwordMsg = "两次密码不一致,请检查。";
                            }
                        }
                    });
                });


                $scope.submitDialogForm = function (isValid) {
                    $scope.submited = true;
                    if (isValid) {
                        var password = $scope.data.password;
                        var checkPassword = $scope.data.checkPassword;
                        if (password != checkPassword) {
                            return;
                        }
                        var reqBody = angular.copy($scope.data);
                        dbUtils.post("modifyPwd", reqBody, function (data) {
                            if (data) {
                                dbUtils.warning(data);
                            } else {
                                dbUtils.success("密码修改更新成功!");
                                $modalInstance.close();
                            }
                        }, function (error) {
                            dbUtils.error(error);
                        });
                    }
                };
                $scope.cancel = function () {
                    $modalInstance.dismiss('cancel');
                };
            }],
            size: "md",
            backdrop: "static",
            resolve: {
                loginName: function () {
                    return $scope.userInfo.loginName;
                }
            }
        });
        instance.result.then(function () {
            $http.post("../logout.do", {}).success(function (data, status, headers, config) {
                $window.sessionStorage.setItem("loginName", "");
                $window.location.href = "../";//退出后直接返回主页
            });
        });
    };
    $scope.reBackIndex = function () {
        $state.go("dashboard");
    }


}]);

/* Setup Layout Part - Sidebar */
DBApp.controller('SidebarController', ['$scope', '$http', '$modal', '$window', '$state', function ($scope, $http, $modal, $window, $state) {
    $scope.$on('$includeContentLoaded', function () {
        Layout.initSidebar($scope, $http, $modal, $window, $state); // init sidebar
    });
}]);

/* Setup Layout Part - Sidebar */
DBApp.controller('PageHeadController', ['$scope', function ($scope) {
    $scope.$on('$includeContentLoaded', function () {
        Demo.init(); // init theme panel
    });
}]);

/* Setup Layout Part - Footer */
DBApp.controller('FooterController', ['$scope', function ($scope) {
    $scope.$on('$includeContentLoaded', function () {
        Layout.initFooter(); // init footer
    });
}]);


function StateConfigController($stateProvider, $urlRouterProvider) {

    // Redirect any unmatched url
    $urlRouterProvider.otherwise('/dashboard');

    $stateProvider.state('dashboard', {
        url: '/dashboard',
        views: {
            'mainContentContainer': { //mainContentContainer 对应页面上的ui-view值，用于指定view显示在哪个位置
                controller: 'DashboardController', // 新加载的页面对应controller，需要确保值唯一
                templateUrl: 'welcome.html'//具体需要显示的页面URL路径
            }
        },
        data: {pageTitle: '长安福特垂直电商系统', pageSubTitle: '控制面板 | 欢迎页面'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                //在切换到这个view的时候需要先加载对应的js或其他文件，load当中可以放入数组加载多个文件
                return $ocLazyLoad.load(['DashboardController.js']);
            }]
        }
    }).state('userRoleResource', {
        url: "/userRoleResource",
        views: {
            "mainContentContainer": {
                controller: "userRoleResourceListCtrl",
                templateUrl: "db/db-form-grid.html"
            }
        },
        data: {pageTitle: '权限管理', pageSubTitle: '用户角色权限查询导出'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/security/userRoleResourceListCtrl.js']
                }]);
            }]
        }
    }).state('operationLogEntry', {
        url: "/operationLogEntry",
        views: {
            "mainContentContainer": {
                controller: "operationLogListCtrl",
                templateUrl: "db/db-form-grid.html"
            }
        },
        data: {pageTitle: '权限管理', pageSubTitle: '权限操作日志查询'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/security/operationLogListCtrl.js',
                        Metronic.getAssetsPath() + 'global/plugins/bootstrap-datepicker/css/datepicker3.css',
                        Metronic.getAssetsPath() + 'global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js',
                        Metronic.getAssetsPath() + 'global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js']
                }]);
            }]
        }
    }).state('securityUserList', {
        url: '/securityUserList',
        views: {
            'mainContentContainer': {
                controller: 'userListCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '用户管理', pageSubTitle: '权限管理|用户管理——用户信息维护预览'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: [
                        'views/security/userListCtrl.js',
                        'views/security/userEditorCtrl.js',
                        'views/security/changePwdCtrl.js'
                    ]
                }]);
            }]
        }
    }).state('securityUserImportList', {
        url: '/securityUserImportList',
        views: {
            'mainContentContainer': {
                controller: 'userImportCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '用户导入', pageSubTitle: '权限管理|用户导入——用户导入信息维护预览'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: [
                        '../resources/lib/global/plugins/angularjs/plugins/angular-file-upload.min.js',
                        'views/security/userimport/userImportQueryCtrl.js',
                        'views/security/userimport/userImportCtrl.js',
                        'views/security/userimport/userListCtrl.js',
                        'views/security/userimport/userImportDetails.js',
                        Metronic.getAssetsPath() + 'global/plugins/bootstrap-datepicker/css/datepicker3.css',
                        Metronic.getAssetsPath() + 'global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js',
                        Metronic.getAssetsPath() + 'global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js'
                    ]
                }]);
            }]
        }
    }).state('securityRoleList', {
        url: '/securityRoleList',
        views: {
            'mainContentContainer': {
                controller: 'roleListCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '角色管理', pageSubTitle: '权限管理|角色管理——系统角色信息维护预览'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true, files: [
                        'views/security/roleListCtrl.js',
                        'views/security/roleEditorCtrl.js',
                        'views/security/authorizeUserCtrl.js',
                        'views/security/authorizeResourceCtrl.js',
                        'views/security/selectUserCtrl.js',
                        'views/security/selectResourceCtrl.js'
                    ]
                }]);
            }]
        }
    }).state('security-resource-list', {
        url: '/security-resource-list',
        views: {
            'mainContentContainer': {
                controller: 'resourceListCtrl',
                templateUrl: 'views/security/resourceList.html'
            }
        },
        data: {pageTitle: '资源管理', pageSubTitle: '权限管理|资源管理——系统资源信息维护预览'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true, files: ['views/security/resourceListCtrl.js', 'views/security/resourceEditorCtrl.js']
                }]);
            }]
        }
    }).state('resourceEntry', {
        url: '/resourceEntry',
        views: {
            'mainContentContainer': {
                controller: 'resourceEntryCtrl',
                templateUrl: 'db/db-form.html'
            }
        },
        data: {pageTitle: '资源录入', pageSubTitle: '权限管理|资源录入——资源信息维护录入'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load({
                    serie: true,
                    files: ['views/security/resourceEntryCtrl.js']
                });
            }]
        }
    }).state('resourceModify', {
        url: '/resourceModify',
        views: {
            'mainContentContainer': {
                controller: 'resourceModifyCtrl',
                templateUrl: 'views/security/resourceModifyView.html'
            }
        },
        data: {pageTitle: '资源修改', pageSubTitle: '权限管理|资源修改——系统资源信息维护预览'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true, files: ['views/security/resourceModifyCtrl.js']
                }]);
            }]
        }
    }).state('securityImList', {
        url: '/securityImList',
        views: {
            'mainContentContainer': {
                controller: 'imCodeCtrl',
                templateUrl: 'views/im/imCodeTypeList.html'
            }
        },
        data: {pageTitle: '字典管理', pageSubTitle: '权限管理|字典管理——系统枚举分类以及字典项维护预览'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: [
                        'views/im/imCodeTypeListCtrl.js',
                        'views/im/imCodeTypeEditorCtrl.js',
                        'views/im/imCodeListEditorCtrl.js']
                }]);
            }]
        }
    }).state('commonOrder', {
        url: '/commonOrder',
        views: {
            'mainContentContainer': {
                controller: 'commonOrderCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '订单查询', pageSubTitle: '订单查询|订单查询——用于查询订单信息'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                        'views/order/commonOrderCtrl.js']
                }]);
            }]
        }
    }).state('callOrder', {
        url: '/callOrder',
        views: {
            'mainContentContainer': {
                controller: 'callOrderCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '话单查询', pageSubTitle: '话单查询|话单查询——用于查询话单信息'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                        'views/order/callOrderCtrl.js']
                }]);
            }]
        }
    }).state('projectMgt', {
        url: '/projectMgt',
        views: {
            'mainContentContainer': {
                controller: 'projectMgtCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '项目活动管理', pageSubTitle: '项目活动管理——用于管理及配置活动信息'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                        'views/project/projectMgtCtrl.js',
                        'views/project/projectEditorCtrl.js',
                        'views/project/projectCityEditorCtrl.js',
                        'views/project/projectUserMgtCtrl.js',
                        'views/project/projectUserEditorCtrl.js',
                        'views/project/projectUserDealerEditorCtrl.js',
                        'views/project/projectUserBatchCreatorCtrl.js']
                }]);
            }]
        }
    }).state('dealerMgt', {
        url: '/dealerMgt',
        views: {
            'mainContentContainer': {
                controller: 'dealerMgtCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '经销商管理', pageSubTitle: '经销商管理——用于管理及配置经销商信息'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                        'views/dealer/dealerMgtCtrl.js',
                        'views/dealer/dealerEditorCtrl.js']
                }]);
            }]
        }
    }).state('cshOrderMgt', {
        url: '/cshOrderMgt',
        views: {
            'mainContentContainer': {
                controller: 'cshOrderMgtCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '车商汇订单导入', pageSubTitle: '导入车商汇订单'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                        'views/order/cshOrderMgtCtrl.js',
                        'views/share/fileUploadModalCtrl.js']
                }]);
            }]
        }
    }).state('cshCallMgt', {
        url: '/cshCallMgt',
        views: {
            'mainContentContainer': {
                controller: 'cshCallMgtCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '车商汇话单导入', pageSubTitle: '导入车商汇话单'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                        'views/order/cshCallMgtCtrl.js',
                        'views/share/fileUploadModalCtrl.js']
                }]);
            }]
        }
    }).state('orderTransMgt', {
        url: '/orderTransMgt',
        views: {
            'mainContentContainer': {
                controller: 'orderTransMgtCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '订单数据转换', pageSubTitle: '查询订单数据'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                        'views/order/orderTransMgtCtrl.js']
                }]);
            }]
        }
    }).state('commonMapMgt', {
        url: '/commonMapMgt',
        views: {
            'mainContentContainer': {
                controller: 'commonMapMgtCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '系统参数', pageSubTitle: '系统参数'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                        'views/mapping/commonMapMgtCtrl.js',
                        'views/mapping/commonMapEditorCtrl.js']
                }]);
            }]
        }
    }).state('mediaMappingMgt', {
        url: '/mediaMappingMgt',
        views: {
            'mainContentContainer': {
                controller: 'mediaMappingMgtCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '渠道映射', pageSubTitle: '渠道映射'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    	'views/mapping/mediaMappingMgtCtrl.js',
                    	'views/mapping/mediaMappingEditorCtrl.js']
                }]);
            }]
        }
    }).state('batchMgt', {
        url: '/batchMgt',
        views: {
            'mainContentContainer': {
                controller: 'batchMgtCtrl',
                templateUrl: 'db/db-form-grid.html'
            }
        },
        data: {pageTitle: '批处理', pageSubTitle: '批处理'},
        resolve: {
            loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    files: [
                    	'views/batch/batchMgtCtrl.js',
                    	'views/batch/batchActivatorCtrl.js']
                }]);
            }]
        }
    });
}

/* Init global settings and run the app */
DBApp.run(['$rootScope', 'settings', '$state', '$templateCache', function ($rootScope, settings, $state, $templateCache) {
    $rootScope.$state = $state; // state to be accessed from view
    $templateCache.put('db/db-form.html', '<div class="row"><db-form></db-form></div>');
    $templateCache.put('db/db-form-grid.html', '<div class="row"><db-form-grid options="dbFormGrid" db-area-tree="dbAreaTree" db-month-select="dbMonthSelect"></db-form-grid></div>');
    $templateCache.put('db/db-report-grid.html', '<div class="row"><db-report-grid></db-report-grid></div>');
}]);
