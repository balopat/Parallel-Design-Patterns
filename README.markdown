This is the Java version of the Parallel Design Patterns project to demonstrate some of the Patterns for Parallel Programming book's ideas. 

The Patterns for [Parallel Programming book](http://www.amazon.com/Patterns-Parallel-Programming-Timothy-Mattson/dp/0321228111)
 offers the power of design patterns to help tackling the complexity in the design process. It describes four “design spaces”:

* **Finding Concurrency**: identify the available concurrency and expose it for use in the algorithmic design
* **Algorithm Structure**: work with high level structures for organizing a parallel algorithm
* **Supporting Structures**: source code level considerations & data sharing techniques.
* **Implementation Mechanisms**: specific implementation constructs:  thread/process handling, synchronizations, etc.

From the four design spaces the current focus is on the Finding Concurrency and Algorithm Structure spaces. 

**Part I.** Make the [MinFinderTest](src/test/java/org/lscc/minfinder/MinValueFinderTest.java) green! 

1. Solve the functional part sequentially
2. Make the performance test green by porting your application to the Divide And Conquer pattern

**Part II.** Play with it! 

3. Refactor the code so MinimumFinder does not use the abstract class!
4. Introduce "a knob" to make the degree of parallelism configurable


