blackjackcardcount
==================

A blackjack console game written in Scala that incorporates card counting models

##Installation & Configuration
The game can be compiled, tested and run using the Scala Simple Build Tool (sbt). See http://www.scala-sbt.org for more instructions.

1. _Compile the source:_
 
        $ sbt package
	

2. _Run tests:_

        $ sbt test
	

3. _Launch the game:_

        $ sbt run
	

##Game Rules
You start with 100 chips and must bet at least one each round.

The dealer will hit any hand below 17 and stay when the hand is 17 or above. 

Card counting is supported and encouraged! 
See http://en.wikipedia.org/wiki/Card_counting for a description of the HiLo and Zen models.

CONSOLE ENTRIES ARE NOT ECHOed!
