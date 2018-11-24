/**
 * Created by kui.yang on 16/01/19.
 * dbAreaTree 指令
 * 1.功能说明:
 *      dbAreaTree  用于弹出模态窗口显示组织机构棵树.可以对这棵树进行选择和点击树上的节点,并按关键字进行搜索(默认显示搜索功能)
 * 2.使用方式:
 *      1. 在dbForm的field当中定义type值为orgTree即可
 *      2. 在HTML当中直接使用<db-org-tree></db-org-tree>
 * 3. js 定义示例
 *          在dbForm当中使用的话,示例如下.注意其中的type:orgTree值
 *          $scope.dbForm = {
                settings: {transCode: "divisionPage", cols: 3, showClose: false},
                title: {label: "组织机构"},
                sections: [{
                    sectionTitle: {show: true, icon: "jigou", label: "机构"},
                    fields: [{
                        name: "parentDivision",
                        label: "上级机构",
                        type: "orgTree",
                        required: true,
                        placeholder: "请选择上级机构",
                        readonly:true
                    }]
                }]
            };
 //实现定义一个对象
 $scope.dbAreaTree = {settings:{noCache:true,showDepartment:false}};
 //实现机构选中之后的回调事件
 $scope.dbAreaTree.onAreaSelected = function(item){
                var orgNamePath = item.orgNamePath;
                var orgId = item.orgId;
         }
 4. 字段说明:
 4.1 返回的item字段说明
 {id:3,orgType:"DIVISION",orgName:"济南分公司",orgCode:"2001",orgId:3,orgNamePath:"总公司/山东公司/济南分公司",parentOrgId:2,parentOrgType:"DIVISION"},

 字段              说明
 id               数据库ID值
 orgType         机构类型:
 DIVISION:组织机构
 DEPARTMENT:部门
 orgName         机构名称
 orgCode         机构代码
 orgId           机构唯一键
 orgNamePath         机构路径
 parentOrgId     父级机构ID
 parentOrgType   父级机构类型

 4.2 settings 说明
 noCache             设置不使用缓存,默认树结构数据会本地缓存为false
 showSearch          是否显示搜索框,默认为显示
 showDivision        是否可以选择行政机构树,默认为可以
 showDepartment      是否可以选择部门树,默认为可以
 month               查询的月份,示例为:2016-01-


 5. 接口API 除了一下的接口之外,可以直接使用db-tree的API
 //选中某个机构值后触发的回调方法,需要调用方自己实现
 5.1 $scope.dbAreaTree.onAreaSelected(item);
 5.5 设置是否不使用缓存
 $scope.dbAreaTree.settings.noCache = true;


 */
'use strict';
var dbAreaTreeDirectives = angular.module('db.components.areaTree', ['dbUtils']);
dbAreaTreeDirectives.dbAreaTreeCaches = {};//机构树数据缓存对象
dbAreaTreeDirectives.directive('dbAreaTree', ['dbUtils',function(dbUtils){
    //dbAreaTree默认参数,针对settings值
    var options = {
        noCache:false,
        useCheckBox: false,//是否显示复选框
        showSearch:true,showDivision:true,showDepartment:true
    };

    return {
        restrict: 'E',
        templateUrl: Metronic.getResourcesPath() + "templates/dbAreaTree.html",
        replace: true,
        transclude: true,
        controller: ['$scope','$modal','$compile', function ($scope,$modal,$compile) {
            if (angular.isUndefined($scope.dbAreaTree)) {
                $scope.dbAreaTree = {settings:{}};
            }
            //替换默认值
            $scope.dbAreaTree.settings = angular.extend({}, options, $scope.dbAreaTree.settings);
            //弹出机构树查询界面
            $scope.dbAreaTree.selectArea = function(fieldName){
                openAreaTreeModal(fieldName)
            };
            var orgTreeSearchSettings = $scope.dbAreaTree.settings;
            function openAreaTreeModal(fieldName){
                var instance = $modal.open({
                    controller: ['$scope','$modalInstance','field',function ($scope,$modalInstance,field) {
                        //确定按钮事件
                        $scope.closeModal = function(){
                            var selectedItem=$scope.dbTree.getSelectedItem();
                            selectedItem.field = field;
                            //回调页面并关闭窗口
                            $modalInstance.close($scope.dbTree.getSelectedItem());
                        };
                        $scope.modalClose = function(){
                            $modalInstance.dismiss("cancel");
                        };

                    }],
                    templateUrl: 'dbAreaTreeModal_template.html',
                    size: "md",
                    backdrop: "static",
                    resolve:{
                        field:function(){
                            return {"name":fieldName};
                        }
                    }
                });

                instance.result.then(function (item) {
                    var fieldName = item.field.name;
                    //机构选择后回调调用方
                    if(!angular.isUndefined($scope.dbAreaTree.onAreaSelected)){
                        $scope.dbAreaTree.onAreaSelected(item,fieldName);
                    }
                });
                instance.rendered.then(function () {
                });
            }
        }],
        link: function (scope, element, attrs) {
            console.log("link dbOrgtree")
        }
    }
}]);
/**
 * 二  dbAreaTreeSearch 指令
 1.功能说明
 用于直接显示一个可以搜索的树结构,不是以模态窗口方式显示.
 2.使用方式
 在HTML当中直接使用<db-org-tree-search></db-org-tree-search>
 3.JS定义
 $scope.dbAreaTreeSearch = {settings:{showSearch:true}};
 noCache      设置不使用缓存,默认树结构数据会本地缓存 为false
 showSearch   是否显示搜索功能,默认为false
 showDivision 是否显示行政机构选项,默认为true
 showDepartment 是否显示部门选项,默认为false
 4. API
 //重新以当前条件加载树机构数据
 4.1 $scope.dbAreaTreeSearch.refreshTree()
 */
dbAreaTreeDirectives.directive('dbAreaTreeSearch', ['dbUtils',function(dbUtils){
    var settings = {
        noCache:false, showSearch:false,showDivision:true,showDepartment:false
    };
    return {
        restrict: 'E',
        templateUrl: Metronic.getResourcesPath() + "templates/dbAreaTreeSearch.html",
        replace: true,
        transclude: true,
        scope:false,
        controller: ['$scope', function ($scope) {
            if (angular.isUndefined($scope.dbAreaTreeSearch)) {
                $scope.dbAreaTreeSearch = {settings:{}};
            }
            $scope.dbAreaTreeSearch.orgNamePaths = [];
            $scope.dbAreaTreeSearch.settings = angular.extend({},settings,$scope.dbAreaTreeSearch.settings);
            //获取树结构数据
            function doGetAreaTreeData(){
                dbUtils.post("report.area.list",{},function(retval){
                    $scope.dbAreaTreeSearch.orgNamePaths = retval;
                    dbAreaTreeDirectives.dbAreaTreeCaches=retval;
                    $scope.dbAreaTreeSearch.queryEnd = true;
                    initDbAreaTree();
                });
            }
            function initDbAreaTree(){
                //构造树结构
                //1.查找root
                var root =null;
                angular.forEach($scope.dbAreaTreeSearch.orgNamePaths,function(item){
                    if (angular.isUndefined(item['parentCode']) || !item['parentCode']) {
                        root = {text: item['name'], code: item['code'], attr: item, opened: true, iconClass: "icon-state-warning", treeId: item['code'], isMenu: item['isMenu'], canSelect: true};
                        return false;
                    }
                });
                if(!root){
                    console.log("db-org-tree root is null");
                    return;
                }
                //2.递归循环所有节点,将节点加入到父节点当中
                function getChildren(parentCode){
                    var child = [];
                    var type = $scope.dbAreaTreeSearch.queryForm.type.value;

                    angular.forEach($scope.dbAreaTreeSearch.orgNamePaths, function (item) {
                        if (item['parentCode'] == parentCode) {
                            var iconClass = item['isMenu'] == "是" ? 'icon-state-warning' : 'icon-state-success';
                            var o = {text: item['name'], code: item['code'], attr: item, children: [], iconClass: iconClass, treeId: item['code'], isMenu: item['isMenu'], canSelect: true};
                            //当树是部门时,只有菜单数据数据可以选择
                            o.canSelect = item['isMenu'] === "是";
                            child.push(o);
                        }
                    });
                    angular.forEach(child, function (item) {
                        item.children = getChildren(item['code']);
                    });
                    return child;
                }

                //生成树结构数据
                root.children = getChildren(root['code']);
                //渲染树结构
                if($scope.dbTree){
                    $scope.dbTree.setData([root]);
                }else{
                    $scope.dbTree = {
                        data:[root]
                    }
                }

            }

            //
            $scope.dbAreaTreeSearch.queryForm = {
                type:null,
                keyWord:null
            };
            $scope.dbAreaTreeSearch.typeSelects = [];
            if($scope.dbAreaTreeSearch.settings.showDivision){
                $scope.dbAreaTreeSearch.queryForm.type = {"name":"行政机构","value":"DIVISION"};
                $scope.dbAreaTreeSearch.typeSelects.push({"name":"行政机构","value":"DIVISION"});
            }

            if($scope.dbAreaTreeSearch.settings.showDepartment){
                if( $scope.dbAreaTreeSearch.queryForm.type==null){
                    $scope.dbAreaTreeSearch.queryForm.type = {"name":"部门科室","value":"DEPARTMENT"};
                }
                $scope.dbAreaTreeSearch.typeSelects.push({"name":"部门科室","value":"DEPARTMENT"});
            }

            doGetAreaTreeData();

            //搜索的数据来源
            $scope.dbAreaTreeSearch.searchDataSource = [];
            //输入关键字时进行匹配
            $scope.dbAreaTreeSearch.refreshDataSource = function(search){
                if(search==""){
                    $scope.dbAreaTreeSearch.searchDataSource=[];
                    return ;
                }
                var retval = [];
                angular.forEach($scope.dbAreaTreeSearch.orgNamePaths,function(item){
                    var path = item.orgNamePath;
                    if(path.indexOf(search)>-1){
                        retval.push(item);
                    }
                });
                $scope.dbAreaTreeSearch.searchDataSource=retval;
            };
            //当前选择某个搜索结果下拉框的值时触发的事件
            $scope.dbAreaTreeSearch.queryOnSelect=function(item,model){
                $scope.dbTree.setItemSeleted({text: item.orgName,orgId:item.orgId,treeId:item.orgId,attr:item,children:[]});
            };
            //重新以当前条件加载树机构数据
            $scope.dbAreaTreeSearch.refreshTree = function(){
                doGetAreaTreeData();
            };

        }],

        link: function (scope, element, attrs) {
            console.log("link dbAreaTreeSearch")
        }
    }
}]);
