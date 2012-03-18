package org.lscc.minfinder;

import java.util.ArrayList;
import java.util.List;

public class Interval {
    int a;
    int b;

    public Interval(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public List<Interval> splitIntoTwo() {
        List<Interval> result = new ArrayList<Interval>();
        result.add(new Interval(a, a + (b - a) / 2));
        result.add(new Interval(a + (b - a) / 2 + 1, b));
        return result;
    }
}
