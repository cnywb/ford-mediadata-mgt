/**
 * Created by wanglijun on 16/4/22.
 *
 *   var settings = {
                onSelected: function (row) {
                        console.log("-------------");
                        console.dir(row);
                },
                fields:{
                        p: '四川',
                        a: '荣县',
                        c: '自贡',
                        d: '兰城路108弄',
                }
        };

 //规则代码
 $scope.selectCity={settings:settings};
 */

'use strict';
(function () {
    var dbSelectCity = angular.module('db.components.selectCity', []);
    dbSelectCity.directive('dbSelectCity', function ($http, $q, $compile) {

            var dataUrl, delay, templateURL;

            delay = $q.defer()

            templateURL = Metronic.getResourcesPath() + 'templates/dbSelectCity.html';

            dataUrl = Metronic.getResourcesPath() + 'js/db-data-city.min.js';

            $http.get(dataUrl).success(function (data) {
                return delay.resolve(data);
            });

            var options = {
                fields: {
                    p: '',
                    a: '',
                    c: '',
                    d: '',
                    ngModel: ''
                },
                onSelected: function (row) {
                    console.dir(row);
                    console.log('default on selected change...');
                }
            };

            return {
                restrict: 'E',
                scope: false,
                replace: true,
                templateUrl: Metronic.getResourcesPath() + "templates/dbSelectCity.html",
                controller: function ($scope, $element) {

                    if (angular.isUndefined($scope.selectCity)) {
                        $scope.selectCity = {settings: {}};
                    }
                    //替换默认值
                    $scope.selectCity.settings = angular.extend({}, options, $scope.selectCity.settings);
                },
                link: function (scope, element, attrs) {
                    scope.p = scope.selectCity.settings.fields['p'];
                    scope.a = scope.selectCity.settings.fields.a;
                    scope.c = scope.selectCity.settings.fields.c;
                    scope.d = scope.selectCity.settings.fields.d;
                    var popup;
                    popup = {
                        element: null,
                        backdrop: null,
                        show: function () {
                            if (!this.element) {
                                return;
                            }
                            return this.element.addClass('active');
                        },
                        hide: function () {
                            if (!this.element) {
                                return;
                            }
                            this.element.removeClass('active');
                            return false;
                        },
                        resize: function () {
                            if (!this.element) {
                                return;
                            }
                            this.element.css({
                                top: -this.element.height() - 30,
                                'margin-left': -this.element.width() / 2
                            });
                            return false;
                        },
                        focus: function () {
                            $('[ng-model="d"]').focus();
                            return false;
                        },
                        init: function () {
                            element.on('click keydown', function () {
                                popup.show();
                                event.stopPropagation();
                                return false;
                            });
                            $(window).on('click', (function (_this) {
                                return function () {
                                    return _this.hide();
                                };
                            })(this));

                            if (this.element) {
                                this.element.on('click', function () {
                                    return event.stopPropagation();
                                });
                            }

                            return setTimeout((function (_this) {
                                return function () {
                                    if (_this.element) {
                                        _this.element.show();
                                    }
                                    return _this.resize();
                                };
                            })(this), 500);
                        }
                    };

                    return delay.promise.then(function (data) {
                        $http.get(templateURL).success(function (template) {
                            scope.provinces = data;
                            return popup.init();
                        });

                        scope.aSet = {
                            p: function (p) {
                                scope.p = p;
                                scope.c = '';
                                scope.a = '';
                                return scope.d = '';
                            },
                            c: function (c) {
                                scope.c = c;
                                scope.a = '';
                                return scope.d = '';
                            },
                            a: function (a) {
                                scope.a = a;
                                scope.d = '';
                                return popup.focus();
                            }
                        };
                        scope.clearCity = function () {
                            scope.p = '';
                            scope.c = '';
                            scope.a = '';
                            return scope.d = '';
                        };
                        scope.submitCity = function () {
                            var row = {"p": scope.p, "c": scope.c, "a": scope.a, "d": scope.d};
                            scope.selectCity.settings.onSelected(row);
                            return popup.hide();
                        };

                        scope.$watch('p', function (newV) {
                            var v, _i, _len, _results;
                            if (newV) {
                                _results = [];
                                for (_i = 0, _len = data.length; _i < _len; _i++) {
                                    v = data[_i];
                                    if (v.p === newV) {
                                        _results.push(scope.cities = v.c);
                                    }
                                }
                                return _results;
                            } else {
                                return scope.cities = [];
                            }
                        });
                        scope.$watch('c', function (newV) {
                            var v, _i, _len, _ref, _results;
                            if (newV) {
                                _ref = scope.cities;
                                _results = [];
                                for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                                    v = _ref[_i];
                                    if (v.n === newV) {
                                        _results.push(scope.dists = v.a);
                                    }
                                }
                                return _results;
                            } else {
                                return scope.dists = [];
                            }
                        });
                        return scope.$watch(function () {
                            scope.ngModel = '';
                            if (scope.p) {
                                scope.ngModel += scope.p;
                            }
                            if (scope.c) {
                                scope.ngModel += " " + scope.c;
                            }
                            if (scope.a) {
                                scope.ngModel += " " + scope.a;
                            }
                            if (scope.d) {
                                scope.ngModel += " " + scope.d;
                            }

                            return popup.resize();
                        });
                    });
                },

            }
        }
    );
}).call(this);

