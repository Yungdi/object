package chapter3;

import java.time.Duration;
import java.util.List;

class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Movie movie = screening.getMovie();

        boolean discountable = false;
        List<DiscountCondition> discountConditions = movie.getDiscountConditions();
        for (DiscountCondition discountCondition : discountConditions) {
            if (discountCondition.getDiscountConditionType() == DiscountConditionType.PERIOD) {
                discountable = screening.getWhenScreened().getDayOfWeek().equals(discountCondition.getDayOfWeek()) &&
                        discountCondition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                        discountCondition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) > 0;
            } else {
                discountable = discountCondition.getSequence() == screening.getSequence();
            }


            if (discountable) {
                break;
            }
        }
        Money fee;
        if (discountable) {
            Money discountAmount;
            MovieType movieType = movie.getMovieType();

            discountAmount = switch (movieType) {
                case AMOUNT_DISCOUNT -> movie.getDiscountAmount();
                case PERCENT_DISCOUNT -> movie.getFee().times(movie.getDiscountPercent());
                case NONE_DISCOUNT -> Money.ZERO;
            };

            fee = movie.getFee().minus(discountAmount).times(audienceCount);
        } else {
            fee = movie.getFee();
        }
        return new Reservation(customer, screening, fee, audienceCount);

    }
}
