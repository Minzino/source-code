package org.eternity.chapter02.movie.step01.pricing;

import org.eternity.chapter02.money.Money;
import org.eternity.chapter02.movie.step01.DiscountPolicy;
import org.eternity.chapter02.movie.step01.Screening;

public class NoneDiscountPolicy extends DiscountPolicy {

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
