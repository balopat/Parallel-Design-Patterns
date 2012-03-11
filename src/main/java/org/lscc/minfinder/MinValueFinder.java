package org.lscc.minfinder;

import org.lscc.parallelpatterns.divideandconquer.DivideAndConquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinValueFinder {

    private Function<Integer, Integer> f;

    public MinValueFinder(Function<Integer, Integer> function) {
        this.f = function;
    }

    public int findMinValueBetween(int a, int b) {
        int candidate = f.apply(a);
        for (int i = a+1; i<=b; i++){
            int nextValue = f.apply(i);
            candidate = Math.min(candidate, nextValue);
        }
        return candidate;
    }
}
