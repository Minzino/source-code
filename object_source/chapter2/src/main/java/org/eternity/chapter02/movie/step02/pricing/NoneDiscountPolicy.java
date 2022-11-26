package org.eternity.chapter02.movie.step02.pricing;

import org.eternity.chapter02.money.Money;
import org.eternity.chapter02.movie.step02.DiscountPolicy;
import org.eternity.chapter02.movie.step02.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
