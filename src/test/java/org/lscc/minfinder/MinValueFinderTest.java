package org.lscc.minfinder;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MinValueFinderTest {
    private Function<Integer, Integer> square = new Function<Integer, Integer>() {
        @Override public Integer apply(Integer x) {
            return x * x;
        }
    };

    private Function<Integer, Integer> neg = new Function<Integer, Integer>() {
        @Override public Integer apply(Integer x) {
            return -x;
        }
    };

    private Function<Integer, Integer> slowNeg = new Function<Integer, Integer>() {
        @Override public Integer apply(Integer x) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return -x;
        }
    };

    @Test
    public void givenSqrFunctionAndIntervalFrom2To5ShouldResultIn4() {
        MinValueFinder minValueFinderSqr = new MinValueFinder(square);
        int minValue = minValueFinderSqr.findMinValueBetween(2, 5);
        assertThat(minValue, is(4));
    }

    @Test
    public void givenSqrFunctionAndIntervalFromMinus4To10ShouldResultIn0() {
        MinValueFinder minValueFinderForSquare = new MinValueFinder(square);
        int minValue = minValueFinderForSquare.findMinValueBetween(-4, 10);
        assertThat(minValue, is(0));
    }

    @Test
    public void givenNegFunctionAndIntervalFromMinus4To10ShouldResultInMinus10() {
        MinValueFinder minValueFinderForNeg = new MinValueFinder(neg);
        int minValue = minValueFinderForNeg.findMinValueBetween(-4, 10);
        assertThat(minValue, is(-10));
    }

    @Test
    public void shouldReturnTheFunctionValueForOneElement() {
        MinValueFinder minValueFinderSqr = new MinValueFinder(square);
        int minValue = minValueFinderSqr.findMinValueBetween(-2, -2);
        assertThat(minValue, is(4));
    }

    @Test(timeout = 1000)
    public void shouldFinishInTime() {
        MinValueFinder minValueFinderForSlowNeg = new MinValueFinder(slowNeg);
        int minValue = minValueFinderForSlowNeg.findMinValueBetween(0, 10);
        assertThat(minValue, is(-10));
    }
}
