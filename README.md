## Heya

_Heya (sumo) from the Japanese word for "room" (部屋)_

Heya is the first step in a long [roadmap](https://github.com/wasupu/wasupu/wiki/Roadmap) described in the main project [wiki](https://github.com/wasupu/wasupu/wiki). 

The agent controls the movement of a robot in a grid world. 

Some tiles of the grid are walkable, and others lead to the robot falling into the water, like the [frozen lake project]( https://github.com/wasupu/frozen-lake). Additionally, the movement direction of the agent is uncertain and only partially depends on the chosen direction. The robot is rewarded for finding a walkable path to a goal tile.

This project mix the result of the [frozen lake project](https://github.com/wasupu/frozen-lake) and the [walker project](https://github.com/wasupu/walker). 

* The [frozen lake project](https://github.com/wasupu/frozen-lake) train in a virtual environment an agent using reinforcement learning, the result is a policy implementing using a neural network that take decisitions using the envoronment observations.
* The [walker project](https://github.com/wasupu/walker) create a lego mindstorms robot that can move in an onmidirectional way, and provide a set of observations to the agent.

The goal of the robot is find the path and to transmit them to an external observer. 

The only information that the robot can use to achieve this mission is through its onboard sensors.

In this first stage, the robot doesn't have any energy restrictions.
