;(function ($, window, undefined) {
  'use strict';

  var $doc = $(document),
      Modernizr = window.Modernizr;


  $.fn.foundationAlerts           ? $doc.foundationAlerts() : null;
  $.fn.foundationAccordion        ? $doc.foundationAccordion() : null;
  $.fn.foundationTooltips         ? $doc.foundationTooltips() : null;
  $('input, textarea').placeholder();
  $.fn.foundationButtons          ? $doc.foundationButtons() : null;
  $.fn.foundationNavigation       ? $doc.foundationNavigation() : null;
  $.fn.foundationTopBar           ? $doc.foundationTopBar() : null;
  $.fn.foundationCustomForms      ? $doc.foundationCustomForms() : null;
  $.fn.foundationMediaQueryViewer ? $doc.foundationMediaQueryViewer() : null;
  $.fn.foundationTabs             ? $doc.foundationTabs() : null;


  // UNCOMMENT THE LINE YOU WANT BELOW IF YOU WANT IE8 SUPPORT AND ARE USING .block-grids
  // $('.block-grid.two-up>li:nth-child(2n+1)').css({clear: 'both'});
  // $('.block-grid.three-up>li:nth-child(3n+1)').css({clear: 'both'});
  // $('.block-grid.four-up>li:nth-child(4n+1)').css({clear: 'both'});
  // $('.block-grid.five-up>li:nth-child(5n+1)').css({clear: 'both'});

})(jQuery, this);

//Clean up as we go

var testApp = angular.module('TestRunner', ['ngResource']);

testApp.directive('helloWorld', function(){
   return {
       restrict: 'E',
       scope: {
           name:'@'
       },
       template: '<span>Hello {{name}}</span>'
   }
});


testApp.directive('testSpec', function(){
    return {
        restrict: 'E',
        scope: {
            spec:'@'
        },
        template: '<input type="checkbox" ng-model="spec.isChecked" />' +
                  '{{spec.spec}}'
    }
});

function ResultsCtrl($scope, $resource) {

    $scope.getTestsMethod = $resource('/WebTestRunner/runner/testList',
        {baseDirectory:'@baseDirectory',
         packageName: '@packageName'});

    $scope.testTree = {};
    $scope.testMap = {};

    var runTestsResource = $resource('/WebTestRunner/runner/run', {
        tests: '@tests'
    });

    //TODO: Hook up to local storage
    $scope.baseDirectory = "";
    $scope.packageName = "";

    $scope.statistics = {
        totalTests: 0,
        testErrors: 0,
        testFailures: 0,
        runtime: 0
    }

    $scope.getTests = function() {
        $scope.getTestsMethod.query(
            {baseDirectory: $scope.baseDirectory,
             packageName: $scope.packageName},
            function(tests) {
            $scope.testTree = tests;
        });
    }

    $scope.runSelectedTests = function() {
        var filteredTests = [];
        for (i in $scope.testTree) {
            var suite = $scope.testTree[i];
            for (j in suite.specs) {
                var spec = suite.specs[j];
                if (spec.isChecked == true) {
                    filteredTests.push({"className": suite.className, "spec":spec})
                }
            }
        }

        var res = new runTestsResource({tests: angular.toJson(filteredTests)});
        //TODO: Handle failure
        res.$get(function(result) {
            $scope.statistics.testFailures = result.failureCount;
            $scope.statistics.totalTests = result.runCount;
            $scope.statistics.runtime = result.runtime;
            //$scope.statistics.failures = result.failures;
        });
    }
}
