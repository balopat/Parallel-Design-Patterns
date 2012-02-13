package org.lscc.minfinder;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MinimumFinderTest {

    private Function sqr = new Function() {
        public int apply(int x) {
            return x * x;
        }
    };

    private Function neg = new Function() {
        public int apply(int x) {
            return -x;
        }
    };

    private Function slowNeg = new Function() {
        public int apply(int x) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return -x;
        }
    };

    @Test
    public void givenSqrFunctionAndIntervalFrom2To5ShouldResultIn4(){
        assertThat(new MinimumFinder(sqr).findMinimum(2,5), is(sqr.apply(2)));
    }

    @Test
    public void givenSqrFunctionAndIntervalFromMinus4To10ShouldResultIn0(){
        MinimumFinder minimumFinderForNeg = new MinimumFinder(sqr);
        int actualMin = minimumFinderForNeg.findMinimum(-4,10);
        assertThat(actualMin, is(0));
    }

    @Test
    public void givenNegFunctionAndIntervalFromMinus4To10ShouldResultInMinus10(){
        MinimumFinder minimumFinderForNeg = new MinimumFinder(neg);
        int actualMin = minimumFinderForNeg.findMinimum(-4,10);
        assertThat(actualMin, is(-10));
    }

    @Test
    public void shouldReturnTheFunctionValueForOneElement(){
        assertThat(new MinimumFinder(sqr).findMinimum(-1,-1), is(sqr.apply(-1)));
    }

    @Test(timeout = 1000)
    public void shouldFinishInTime(){
        MinimumFinder minimumFinderForSlowNeg = new MinimumFinder(slowNeg);
        int actualMin = minimumFinderForSlowNeg.findMinimum(0,10);
        assertThat(actualMin, is(-10));
    }


}
