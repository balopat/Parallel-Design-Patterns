package org.lscc.parallelpatterns.divideandconquer;

import java.util.ArrayList;
import java.util.List;

public abstract class DivideAndConquerStrategyWithNoParallelism<Problem, Solution> extends DivideAndConquerStrategy<Problem,Solution>{

      protected Solution solve(Problem p) {
        if (isBaseCase(p)) {
            return baseSolve(p);
        }
        List<Problem> subProblems = split(p);
        List<Solution> subSolutions = new ArrayList<Solution>();

        for (Problem subProblem : subProblems) {
            subSolutions.add(solve(subProblem));
        }
        return merge(subSolutions);
    }
}
