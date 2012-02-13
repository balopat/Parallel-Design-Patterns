package org.lscc.minfinder;

import org.junit.Ignore;
import org.junit.Test;
import org.lscc.IntToIntFunction;
import org.lscc.PairOfIntsToIntFunction;
import org.lscc.fibonacci.Fibonacci;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FibonacciTest {

    @Test
    @Ignore
    public void shouldReturn0For0() {
        PairOfIntsToIntFunction add = new PairOfIntsToIntFunction() {
            @Override
            public int apply(int x, int y) {
                return x + y;
            }
        };
        Fibonacci fibo = new Fibonacci(add);
        assertThat(fibo.fibonacci(0), is(0));
        assertThat(fibo.fibonacci(1), is(1));
        assertThat(fibo.fibonacci(2), is(1));
        assertThat(fibo.fibonacci(23), is(28657));
    }

    @Test(timeout = 10050)
    public void trickyFibonacci() {
        PairOfIntsToIntFunction slowAdd = new PairOfIntsToIntFunction() {
            @Override
            public int apply(int x, int y) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return x + y;
            }
        };
        Fibonacci fibo = new Fibonacci(slowAdd);
        assertThat(fibo.fibonacci(10), is(3));
    }


}
