Parallel Design Patterns is a project to demonstrate the Patterns for Parallel Programming book's patterns. From the four design spaces the focus is on the Finding Concurrency and Algorithm Structure spaces. 

We defined a few Algorithm Structure templates with some examplary default Implementation Mechanism such as DivideAndConquerStrategyWithExecutionService. 

Sample problem testcases are defined for small problems which can be parallelized. 


An example: 

MinimumFinder is a function which should return the minimum value of a function on a given interval. 

The functional requirements are defined by MinimumFinderTest. 
This test also contains a timing requirement and a "slow" function for simulation. The intention is to enforce the parallel implementation. 

How to solve? 

When implementing the problem you should first give some though to the Finding Concurrency phase, so you will end up having: 

A task decomposition that identifies tasks that can execute concurrently 
A data decomposition that identifies data local to each task 
A way of grouping tasks and ordering the groups to satisfy temporal constraints
An analysis of dependencies among tasks

The examples in this project will have some quite basic designs, still, the goal is to bring the thinking process and the decisions to a conscious level.  

Secondly decide which algorithm fits the best to your design: 

What is the major organizing principle in your problem? Tasks, Data Decomposition or th Flow of Data? 

If Tasks, choose between Task Parallelism (for linear solutions) or Divide And Conquer (for recursive decomposition).

If Data Decomposition, choose between Geometric Decomposition (linear) or Recursive data (recursive decomposition). 

If is Organized by Flow of Data, choose between Pipeline (regular feeds) or Event-Based coordination (irregular feeds). 

Happy experimenting! 

