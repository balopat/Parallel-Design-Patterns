package org.lscc.fibonacci;

import org.lscc.PairOfIntsToIntFunction;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fibonacci {

    private PairOfIntsToIntFunction add;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public Fibonacci(PairOfIntsToIntFunction add) {
        this.add = add;
    }

    public Integer fibonacci(final int i) {

        if (i == 0) return 0;
        if (i == 1) return 1;

        try {
            return executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    System.out.println((i-1) +","+ ( i-2));
                    return add.apply(fibonacci(i-1),fibonacci(i-2));  //To change body of implemented methods use File | Settings | File Templates.
                }
            }).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
