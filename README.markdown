# Introduction 

This is the Java version of a Test Driven Practice Field to demonstrate some of the ideas in the Patterns for Parallel Programming book. 

The [Patterns for Parallel Programming book](http://www.amazon.com/Patterns-Parallel-Programming-Timothy-Mattson/dp/0321228111)
 offers the power of design patterns to help tackling the complexity in the design process. It describes four “design spaces”:

* **Finding Concurrency**: identify the available concurrency and expose it for use in the algorithmic design
* **Algorithm Structure**: work with high level structures for organizing a parallel algorithm
* **Supporting Structures**: source code level considerations & data sharing techniques.
* **Implementation Mechanisms**: specific implementation constructs:  thread/process handling, synchronizations, etc.

From the four design spaces the current focus is on the Finding Concurrency and Algorithm Structure spaces. 

# The First Excercise

**Part I.** Make the [MinFinderTest](src/test/java/org/lscc/minfinder/MinValueFinderTest.java) green! 

1. *Solve the functional tests sequentially*! The last test is using ```@Test(timeout=x)```! Don't bother with it yet! 
1. Now is the time: Make the timing test PASS by *porting your application to parallel* 
    * Identify/Create classes which you will use for the Problem and Solution generics. (this refers to Finding Concurrency, how will you decompose your tasks and data?)
    * use the DivideAndConquer pattern (make your MinValueFinder class extend the [DivideAndConquer abstract class](src/main/java/org/lscc/parallelpatterns/divideandconquer/DivideAndConquer.java))

**Part II.** Play with it! 

3. Refactor the code so MinimumFinder does not use the abstract class! We don't want these patterns to explicitly dwell in our codebase!
4. Introduce "a knob" to make the degree of parallelism configurable, and reach the lowest possible resource occupation (i.e. number of threads) within the performance requirements!




