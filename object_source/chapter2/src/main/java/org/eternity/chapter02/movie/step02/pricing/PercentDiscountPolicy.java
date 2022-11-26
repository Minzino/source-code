package org.eternity.chapter02.movie.step02.pricing;

import org.eternity.chapter02.money.Money;
import org.eternity.chapter02.movie.step02.DefaultDiscountPolicy;
import org.eternity.chapter02.movie.step02.DiscountCondition;
import org.eternity.chapter02.movie.step02.Screening;

public class PercentDiscountPolicy extends DefaultDiscountPolicy {

    private final double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
