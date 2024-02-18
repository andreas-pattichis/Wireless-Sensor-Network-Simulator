# Wireless Sensor Network Simulator

## Project Overview
This project simulates a Wireless Sensor Network (WSN) for the efficient exchange and management of information. Developed as a group project, it focuses on implementing six key functions that facilitate the simulation of various operations within a WSN environment. 
---
## Classes and Structures
The simulator is built around the concept of modular programming, with 11 classes each serving a distinct role:

1. `Coordinates1014682_1022927`: Manages [x, y] format coordinates and their basic functionalities.
2. `Node1014682_1022927`: Inherits from the `Vertex` class, representing a node within the network, and manages Vertex children.
3. `Vertex1014682_1022927`: Represents a network vertex, storing identity, coordinates, temperature, neighbor vertices, and more.
4. `Graph1014682_1022927`: Implements the graph structure of the network and provides essential graph functionalities.
5. `MST1014682_1022927`: Implements and manages the network's Minimum Spanning Tree (MST).
6. `Simulation1014682_1022927`: Facilitates the overall simulation of the network.
---
## Key Features
The simulator is designed to perform the following functions upon user request:

1. **Calculate Minimum Spanning Tree (MST):** Utilizes the `Graph.calculateMST()` method to compute and return the MST starting from a specified vertex.
2. **Print Minimum Spanning Tree:** Uses the `MST.display()` method to visually present the MST and its hierarchical structure.
3. **Insert New Node:** Adds a new node to the MST and graph, recalculating the MST to include this new node.
4. **Delete Node:** Removes a specified node from the MST and graph, followed by a recalculation of the MST.
5. **Inform Fire Station A of Highest Network Temperature:** Identifies and reports the maximum temperature within the network based on a given node ID.
6. **Exit Simulation:** Terminates the simulation process.
---
## User Interaction
The simulation prompts users to input the value of `d` and a filename containing node elements to initialize the graph and compute the hashtable.
---
## Example Usage

**Input:**
10 input.txt

**Sample Input File Content:**

30	[7, 6]	40<br>
05	[2, 5]	45<br>
10	[2, 0]	65<br>
21	[7, 0]	33<br>
31	[0, 9]	34<br>
02	[0, 0]	50<br>


## Example's Output

<br />![image](https://user-images.githubusercontent.com/63289392/152787211-60fe81c0-7590-48d1-b4fd-2de9e684c5fb.png)

![image](https://user-images.githubusercontent.com/63289392/152787236-c6b99d17-7b11-480d-bc91-fe23bc140c46.png)

![image](https://user-images.githubusercontent.com/63289392/152787256-5fbffe3f-1dc8-4073-bbfe-c3534809fafc.png)
