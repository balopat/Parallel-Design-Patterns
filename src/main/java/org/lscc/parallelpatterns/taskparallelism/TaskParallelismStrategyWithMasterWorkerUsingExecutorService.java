package org.lscc.parallelpatterns.taskparallelism;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public abstract class TaskParallelismStrategyWithMasterWorkerUsingExecutorService<Problem, Task, Solution> extends TaskParallelismStrategy<Problem, Task, Solution> {
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    protected Solution solve(Problem problem) {
        try {
            return solveWithExecutorService(problem);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Solution solveWithExecutorService(Problem problem) throws ExecutionException, InterruptedException {
        List<Future<Solution>> futures = new ArrayList<Future<Solution>>();
        for (final Task task : map(problem)) {
            futures.add(runSolver(task));
        }
        return reduce(waitForAndCollectSolutions(futures));
    }

    private Future<Solution> runSolver(final Task task) {
        return executorService.submit(new Callable<Solution>() {
            @Override
            public Solution call() throws Exception {
                return solveTask(task);
            }
        });
    }

    private List<Solution> waitForAndCollectSolutions(Iterable<Future<Solution>> futures) throws ExecutionException, InterruptedException {
        List<Solution> result = new ArrayList<Solution>();
        for (Future<Solution> task : futures) {
            result.add(task.get());
        }
        return result;
    }
}
