package chapter2.condition;

import chapter2.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}