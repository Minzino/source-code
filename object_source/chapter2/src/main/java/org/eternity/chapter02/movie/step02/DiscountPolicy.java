package org.eternity.chapter02.movie.step02;

import org.eternity.chapter02.money.Money;

public interface DiscountPolicy {

    Money calculateDiscountAmount(Screening screening);
}
