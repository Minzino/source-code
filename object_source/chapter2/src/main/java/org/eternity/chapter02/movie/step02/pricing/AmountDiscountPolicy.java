package org.eternity.chapter02.movie.step02.pricing;

import org.eternity.chapter02.money.Money;
import org.eternity.chapter02.movie.step02.DefaultDiscountPolicy;
import org.eternity.chapter02.movie.step02.DiscountCondition;
import org.eternity.chapter02.movie.step02.Screening;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {

    private final Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
