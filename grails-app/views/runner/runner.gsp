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
</nav>

<div class="row" ng-controller="ResultsCtrl">

    <div class="six columns">

        <div id="getDirectory">
            <h5>Test Directory</h5>
            <input type="text" id="baseDirectory"
                   placeholder="c:/Users/me/proj/src/groovy/spec"
                   ng-model="baseDirectory"/>
            <!-- TODO: On return, run getTests() -->
            <input type="text" id="basePackage"
                   placeholder="spec"
                   ng-model="packageName" />
            <button ng-click="getTests()" class="tiny round button">Get Tests</button>
        </div>

        <div id="results">
            <h5>Result</h5>
            Passed: {{statistics.runsFinished}}/{{statistics.totalTests}}
            Errors: {{statistics.testErrors}}/{{statistics.totalTests}}
            Failures: {{statistics.testFailures}}/{{statistics.totalTests}}
            Runtime: {{statistics.runtime}} ms
        </div>

        <div id="tests">
            <h5>Tests</h5>

            <ul>
                <li ng-repeat="suite in testTree">
                    <!-- TODO: make this work to check all specs of suite
                    <input type="checkbox" ng-model="suite.isChecked">
                    -->
                    {{suite.className}}
                    <ul>
                        <li ng-repeat="spec in suite.specs">
                            <input type="checkbox" ng-model="spec.isChecked">
                            {{spec.spec}}
                        </li>
                    </ul>
                </li>
            </ul>

            <button ng-click="runSelectedTests()" class="tiny round button">Run</button>
        </div>

    </div>


    <div id="details" class="six columns">
        <dl class="tabs">
            <dd class="active"><a href="#failureTrace">Failure Trace</a></dd>
            <dd><a href="#log">Log</a></dd>
        </dl>

        <ul class="tabs-content">
            <li class="active" id="failureTraceTab">
                {{failures}}
            </li>
            <li id="logTab">This is simple tab 2's content. Now you see it!</li>
        </ul>
    </div>

</div>

<script src="../js/underscore-min.js"></script>
<script src="../js/jquery.js"></script>
<script src="../js/angular.js"></script>
<script src="../js/angular-resource.js"></script>
<script src="../js/foundation.min.js"></script>

<!-- Initialize JS Plugins -->
<script src="../js/app.js"></script>

</body>
</html>
