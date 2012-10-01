<!DOCTYPE html>

<html ng-app="TestRunner">
<head>
    <meta charset="utf-8"/>

    <!-- Set the viewport width to device width for mobile -->
    <meta name="viewport" content="width=device-width"/>

    <title>Web Test Runner</title>

    <!-- Included CSS Files (Uncompressed) -->
    <!--
  <link rel="stylesheet" href="css/foundation.css">
  -->

    <!-- Included CSS Files (Compressed) -->
    <link rel="stylesheet" href="../css/foundation.min.css">
    <link rel="stylesheet" href="../css/app.css">

    <script src="../js/modernizr.foundation.js"></script>

    <!-- IE Fix for HTML5 Tags -->
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

</head>

<body>

<nav class="top-bar">
    <ul>
        <li class="name"><h1><a href="#">Web Spock Runner</a></h1></li>
        <li class="toggle-topbar"><a href="#"></a></li>
    </ul>
    <section>
        <ul class="left">
            <li><a href="#">Link</a></li>
        </ul>

        <ul class="right">
            <li><a href="#">Link</a></li>
        </ul>
    </section>
</nav>

<div class="row" ng-controller="ResultsCtrl">

    <div class="six columns">
        <div id="results">
            <h5>Result</h5>
            Total: {{totalTests}} Runs {{runsFinished}}/{{totalRuns}} Errors {{testErrors}} Failures
            {{testFailures}}
        </div>

        <div id="tests">
            <h5>Tests</h5>
            <ul>
                <li ng-repeat="suite in tests">
                    <input type="checkbox" ng-model="suite.checked">
                    {{suite.name}}
                    <ul>
                        <li ng-repeat="test in suite.tests">
                            <input type="checkbox" ng-model="test.checked">
                            {{test.name}}
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <button ng-click="runSelectedTests()" class="tiny round button">Run</button>
    </div>

    <div id="details" class="six columns">
        <dl class="tabs">
            <dd class="active"><a href="#failureTrace">Failure Trace</a></dd>
            <dd><a href="#log">Log</a></dd>
        </dl>

        <ul class="tabs-content">
            <li class="active" id="failureTraceTab">
                <h2>Todo</h2>
            </li>
            <li id="logTab">This is simple tab 2's content. Now you see it!</li>
        </ul>
    </div>

</div>

<script src="../js/angular.min.js"></script>
<script src="../js/angular-resource.min.js"></script>
<script src="../js/underscore-min.js"></script>
<script src="../js/jquery.js"></script>
<script src="../js/foundation.min.js"></script>

<!-- Initialize JS Plugins -->
<script src="../js/app.js"></script>

</body>
</html>
