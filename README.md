# Wireless Sensor Network Simulator
**•	Solution of the problem, explained:**
In this project, we were asked to present a simulation of a Wireless Sensor Network Simulator for the exchange and management of information within such a network. More specifically, we were asked to implement, in groups of two, six functions of this simulation, which are explained in detail below.

**Initially, regarding the classes of the program, I considered it necessary to create 11 classes:**

1. Coordinates1014682_1022927: A class that represents the coordinates in [x, y] format, and contains methods with all their basic functions.
2. Node1014682_1022927: A class that represents a Node, which is inherited from the Vertex class and stores all Vertex children, and contains methods with all of their basic functions.
3. Vertex1014682_1022927: a class that represents a Vertex and stores its identity, coordinates, temperature, a list of Vertex neighbors, and other information.
4. Graph1014682_1022927: a class that implements the form of a graph, as well as contains methods with all its basic functions.
5. MST1014682_1022927: class which implements the form of a Minimum Spanning Tree, as well as contains methods with all its basic functions.
6. Simulation1014682_1022927: the simulation


**As requested, the program asks the user for the value of d and the name of a file that will contain node elements. At the same time the graph is created and the hashtable is calculated.**

**The 6 requested functions are:**
1. Calculate Minimum Spanning Tree:
It calls the Graph calculateMST () method, which takes as a parameter the Vertex with which it will start, and implements the Minimum Spanning Tree which it returns.
2. Print Minimum Spanning Tree:
Calls the MST display method (), which prints the Minimum Spanning Tree and prints the tree per level.
3. Insert new node:
It reads from the user a new Node that will be placed in MST, you place it in Graph and its neighbors are located, and then the new MST is calculated.
4. Delete node:
It reads from the Node user to be deleted from the MST, is deleted from the Graph, and then the new MST is calculated.
5. Inform fire station A for the highest network temperature:
Reads the Node ID from the user, and calculates and prints the maximum temperature.
6. Exit the simulation:



**•	Example:**

10 input.txt
<br><br>
30	[7, 6]	40<br>
05	[2, 5]	45<br>
10	[2, 0]	65<br>
21	[7, 0]	33<br>
31	[0, 9]	34<br>
02	[0, 0]	50<br>


**•	Example's output:**

<br />![image](https://user-images.githubusercontent.com/63289392/152787211-60fe81c0-7590-48d1-b4fd-2de9e684c5fb.png)

![image](https://user-images.githubusercontent.com/63289392/152787236-c6b99d17-7b11-480d-bc91-fe23bc140c46.png)

![image](https://user-images.githubusercontent.com/63289392/152787256-5fbffe3f-1dc8-4073-bbfe-c3534809fafc.png)
