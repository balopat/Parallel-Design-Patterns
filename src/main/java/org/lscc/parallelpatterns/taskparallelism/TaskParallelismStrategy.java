package org.lscc.parallelpatterns.taskparallelism;

import java.util.List;

public abstract class TaskParallelismStrategy<Problem,Task,Solution> {
    protected abstract List<Task> map(Problem problem);
    protected abstract Solution solveTask(Task task);
    protected abstract Solution reduce(List<Solution> solutions);
    protected abstract Solution solve(Problem task);
}
