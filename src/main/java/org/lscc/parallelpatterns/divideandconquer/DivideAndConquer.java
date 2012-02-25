package org.lscc.parallelpatterns.divideandconquer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class DivideAndConquer<Problem, Solution> {
    private ExecutorService executorService = Executors.newCachedThreadPool();

    protected abstract Boolean isBaseCase(Problem p);
    protected abstract Solution baseSolve(Problem p);
    protected abstract Solution merge(List<Solution> solutions);
    protected abstract List<Problem> split(Problem problem);

    protected Solution solve(Problem problem) {
        try {
            return solveWithExecutorService(problem);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Solution solveWithExecutorService(Problem problem) throws InterruptedException, ExecutionException {
        if (isBaseCase(problem)) {
            return baseSolve(problem);
        }
        List<Problem> subProblems = split(problem);
        List<Future<Solution>> subSolvers = new ArrayList<Future<Solution>>();
        for (Problem subProblem : subProblems) {
            subSolvers.add(runSolverTask(subProblem));
        }
        return merge(waitForAndCollectSolutions(subSolvers));
    }

    private Future<Solution> runSolverTask(final Problem problem) {
        return executorService.submit(new Callable<Solution>() {
            @Override
            public Solution call() throws Exception {
                return solve(problem);
            }
        });
    }

    private List<Solution> waitForAndCollectSolutions(List<Future<Solution>> solvers)
            throws ExecutionException, InterruptedException {
        List<Solution> solutions = new ArrayList<Solution>();
        for (Future<Solution> solver : solvers) {
            solutions.add(solver.get());
        }
        return solutions;
    }
}
