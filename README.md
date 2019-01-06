## Heya

_Heya (sumo) from the Japanese word for "room" (部屋)_

Heya is the first step in a long [roadmap](https://github.com/wasupu/wasupu/wiki/Roadmap) described in the main project [wiki](https://github.com/wasupu/wasupu/wiki). 

The agent controls the movement of a character in a grid world. Some tiles of the grid are walkable, and others lead to the agent falling into the water. Additionally, the movement direction of the agent is uncertain and only partially depends on the chosen direction. The agent is rewarded for finding a walkable path to a goal tile.

The goal of the virtual robot is to visit all points of a square plain world and to transmit that information to an external observer. 

The only information that the robot can use to achieve this mission is through its onboard sensors.

In this first stage, the robot doesn't have any energy restrictions.

### Getting started

* Install [sbt](http://www.scala-sbt.org/) (Scala build tool)
* Clone the repository in your local machine
* sbt clean package
* service/target/universal/stage/bin/heya-service
* sbt acceptance:test

#### Simulate API

* GET http://hostname:port/api/heartbeat if respond 200 (OK) the server is ready
* POST http://hostname:port/api/simulation start new simulation
* DELETE http://hostname:port/api/simulation stop the current simulation
* GET  http://hostname:port/api/simulation return a json object with the data of the current simulation

