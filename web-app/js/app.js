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

angular.module('TestRunner', ['ngResource']);

function ResultsCtrl($scope, $resource) {

    $scope.getTestsMethod = $resource('http://:host:port/WebTestRunner/runner/testList',
        {host: 'localhost', port: ':8080', callback: 'JSON_CALLBACK'},
        {get: { method:'GET', isArray: true}});

    //$scope.tests = $scope.getTestsMethod.get();

    $scope.tests = [{name: "spec.a.ErrorTestSpec", checked: true}, {name: "spec.a.ABTestSpec", checked: true}]
    $scope.runTestsMethod = $resource('http://:host:port/WebTestRunner/runner/run',
        {host: 'localhost', port: ':8080', callback: 'JSON_CALLBACK', tests: $scope.tests},
            {save: { method:'POST', isArray: true}});

    $scope.totalTests = 0;
    $scope.runsFinished = 0;
    $scope.totalRuns = 0;
    $scope.testErrors = 0;
    $scope.testFailures = 0;

    $scope.runSelectedTests = function() {
        var fun = $scope.runTestsMethod.save()
        console.log(fun);
    }

}