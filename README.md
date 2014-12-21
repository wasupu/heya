## Heya

_Heya (sumo) from the Japanese word for "room" (部屋)_

Heya is the first step in a long [roadmap](https://github.com/wasupu/wasupu/wiki/Roadmap) described in the main project [wiki](https://github.com/wasupu/wasupu/wiki). This milestone consists in the creation of a simulation of an autonomous robot that lives in a walled environment with no obstacles.

The project is developed using [scala](http://www.scala-lang.org/). The main reason use [scala](http://www.scala-lang.org/) is mainly the actors system built in the language that permits the simulation of the physical robots.

The goal of the virtual robot is to visit all points of a square plain world and to transmit that information to an external observer. 

The only information that the robot can use to achieve this mission is through its onboard sensor.

In this first stage, the robot doesn't have any energy restrictions.

Another restriction of this project is that the resulting virtual robot, should be constructed using [lego mindstorm](http://www.lego.com/en-us/mindstorms/?domainredir=mindstorms.lego.com). Why? In order to easily create a prototype robot using this technology.

### Getting started

* Install [sbt](http://www.scala-sbt.org/) (Scala build tool)
* Clone the repository in your local machine
* sbt run
