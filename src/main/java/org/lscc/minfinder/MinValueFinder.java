package org.lscc.minfinder;

import org.lscc.parallelpatterns.divideandconquer.DivideAndConquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinValueFinder extends DivideAndConquer<MinValueFinderProblem, Integer>{


    private Function<Integer, Integer> f;

    public MinValueFinder(Function<Integer, Integer> function) {
        this.f = function;
    }

    public int findMinValueBetween(int a, int b) {
        return solve(new MinValueFinderProblem(a,b,this.f));
    }

    @Override
    protected Boolean isBaseCase(MinValueFinderProblem p) {
        return p.a == p.b;
    }

    @Override
    protected Integer baseSolve(MinValueFinderProblem p) {
        return p.f.apply(p.a);
    }

    @Override
    protected Integer merge(List<Integer> subSolutions) {
        return Collections.min(subSolutions);
    }

    @Override
    protected List<MinValueFinderProblem> split(MinValueFinderProblem p) {
        List<MinValueFinderProblem> result = new ArrayList<MinValueFinderProblem>();
        result.add(new MinValueFinderProblem(p.a, p.a+(p.b-p.a)/2, p.f));
        result.add(new MinValueFinderProblem(p.a+(p.b-p.a)/2+1, p.b, p.f));
        return result;
    }
}
