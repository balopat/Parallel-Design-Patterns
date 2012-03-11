package org.lscc.minfinder;

public class MinValueFinderProblem {
    int a;
    int b;
    Function<Integer,Integer> f;

    public MinValueFinderProblem(int a, int b, Function<Integer, Integer> f) {
        this.a = a;
        this.b = b;
        this.f = f;
    }
}
