package org.eternity.chapter02.movie.step02.pricing;

import java.time.DayOfWeek;
import java.time.LocalTime;
import org.eternity.chapter02.movie.step02.DiscountCondition;
import org.eternity.chapter02.movie.step02.Screening;

public class PeriodCondition implements DiscountCondition {

    private final DayOfWeek dayOfWeek;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return screening.getStartTime().getDayOfWeek().equals(dayOfWeek) &&
            startTime.compareTo(screening.getStartTime().toLocalTime()) <= 0 &&
            endTime.compareTo(screening.getStartTime().toLocalTime()) >= 0;
    }
}
