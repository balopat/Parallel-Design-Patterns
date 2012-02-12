package org.lscc.parallelpatterns.divideandconquer;

import java.util.List;

public abstract class DivideAndConquerStrategy<Problem, Solution> {

    protected abstract Boolean isBaseCase(Problem p);

    protected abstract Solution baseSolve(Problem p);

    protected abstract Solution merge(List<Solution> solutions);

    protected abstract List<Problem> split(Problem problem);

    protected abstract Solution solve(Problem p) ;

}
