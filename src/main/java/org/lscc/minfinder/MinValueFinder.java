package org.lscc.minfinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class MinValueFinder {
    private Function<Integer, Integer> f;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public MinValueFinder(Function<Integer, Integer> function) {
        this.f = function;
    }

    public int findMinValueBetween(int a, int b) {
        //isBaseCase
        if (a == b) {
            return f.apply(a);
        }
        //split
        List<Interval> subIntervals = new Interval(a, b).splitIntoTwo();
        //sub solvers
        List<Future<Integer>> localMinimumFinders = new ArrayList<Future<Integer>>();
        for (Interval subInterval : subIntervals) {
            localMinimumFinders.add(runMinFinderOnInterval(subInterval.a, subInterval.b));
        }
        //sub solutions
        List<Integer> localMinimums = waitForAndCollectLocalMinimums(localMinimumFinders);
        //merge
        return Collections.min(localMinimums);

    }

    private Future<Integer> runMinFinderOnInterval(final int a, final int b) {
        return executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return findMinValueBetween(a, b);
            }
        });
    }

    private List<Integer> waitForAndCollectLocalMinimums(List<Future<Integer>> minFinders) {
        try {
            List<Integer> localMinimums = new ArrayList<Integer>();
            for (Future<Integer> solver : minFinders) {
                localMinimums.add(solver.get());
            }
            return localMinimums;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
