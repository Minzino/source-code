package org.eternity.chapter02.movie.step01.pricing;

import org.eternity.chapter02.money.Money;
import org.eternity.chapter02.movie.step01.DiscountCondition;
import org.eternity.chapter02.movie.step01.DiscountPolicy;
import org.eternity.chapter02.movie.step01.Screening;

public class PercentDiscountPolicy extends DiscountPolicy {

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
