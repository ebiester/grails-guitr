//TODO: attach onclick to allow tests/subtests to be called
//TODO: attach checkboxes to lists
//TODO: Restyle UL/LI for a plus
//TODO: attach run event to run
//TODO: get + expansion working
//TODO: Get failure trace pulled in
//TODO: Get log pulled in

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

    $scope.getTests = $resource('http://localhost:port/WebTestRunner/runner/testList',
        {port: ':8080', callback: 'JSON_CALLBACK'},
        {get: { method:'GET', isArray: true}});

    $scope.tests = $scope.getTests.get();
    //$scope.tests = [1, 3, 5, 7, 8]

    $scope.totalTests = 5;
    $scope.runsFinished = 1;
    $scope.totalRuns = 1;
    $scope.testErrors = 2;
    $scope.testFailures = 2;

}

function TodoCtrl($scope) {
    $scope.todos = [
        {text:'learn angular', done:true},
        {text:'build an angular app', done:false}];

    $scope.addTodo = function() {
        $scope.todos.push({text:$scope.todoText, done:false});
        $scope.todoText = '';
    };

    $scope.remaining = function() {
        var count = 0;
        angular.forEach($scope.todos, function(todo) {
            count += todo.done ? 0 : 1;
        });
        return count;
    };

    $scope.archive = function() {
        var oldTodos = $scope.todos;
        $scope.todos = [];
        angular.forEach(oldTodos, function(todo) {
            if (!todo.done) $scope.todos.push(todo);
        });
    };
}
