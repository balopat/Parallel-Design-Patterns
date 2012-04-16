package org.lscc.minfinder;

import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class MinValueFinder {
    private Function<Integer, Integer> f;
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private static final int MAX_NUM_OF_THREADS = 3;

    public MinValueFinder(Function<Integer, Integer> function) {
        this.f = function;
    }

    public int findMinValueBetween(int a, int b) {
        return findMinValueBetweenIfEnoughThreads(a, b, 0);
    }

    private int findMinValueBetweenIfEnoughThreads(int a, int b, int numThreads) {
        //isBaseCase is extended now with the number of threads
        if (a == b) {
            return f.apply(a);
        }
        if (numThreads == MAX_NUM_OF_THREADS) {
            return findMinValueSequentially(a, b);
        }
        //split
        List<Interval> subIntervals = new Interval(a, b).splitIntoTwo();
        //sub solvers
        List<Future<Integer>> localMinimumFinders = new ArrayList<Future<Integer>>();
        for (Interval subInterval : subIntervals) {
            localMinimumFinders.add(runMinFinderOnInterval(subInterval.a, subInterval.b, numThreads + subIntervals.size()));
        }
        //sub solutions
        List<Integer> localMinimums = waitForAndCollectLocalMinimums(localMinimumFinders);
        //merge
        return Collections.min(localMinimums);
    }

    private int findMinValueSequentially(int a, int b) {
        Integer[] values = new Integer[b - a + 1];
        for (int place = a; place <= b; place++) {
            values[place - a] = f.apply(place);
        }
        return Collections.min(Arrays.asList(values));
    }
    private Future<Integer> runMinFinderOnInterval(final int a, final int b, final int numThreads) {
        return executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return findMinValueBetweenIfEnoughThreads(a, b, numThreads);
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
