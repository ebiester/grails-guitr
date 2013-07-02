grails-guitr
============

Grails (web) UI Test Runner for spock.

This was an attempt to create an integration test runner plugin that could be used in development to run integrated tests during the normal run-app lifecycle. While it worked (and a more complete version is deployed at a private organization), it was eventually abandoned because it required too many compromises -- mostly, that we had to place the tests within src/groovy without forking grails itself, or making other similar compromises. 

I may eventually return to it and finish the UI (and implement the log functionality) but it's not currently being used. If you are interested, send me an email and I can write up some install instructions. 
