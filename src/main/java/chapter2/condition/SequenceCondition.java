package chapter2.condition;

import chapter2.Screening;

public class SequenceCondition implements DiscountCondition {
    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return false;
    }
}
