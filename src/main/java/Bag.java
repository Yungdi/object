public class Bag {
    private Long amount;
    private Invitation invitation;
    private Ticket ticket;

    // 초대장 O
    public Bag(Long amount, Invitation invitation) {
        this.amount = amount;
        this.invitation = invitation;
    }

    // 초대장 X
    public Bag(Long amount) {
        this(amount, null);
    }

    public boolean hasInvitation() {
        return invitation != null;
    }

    public boolean hasTicket() {
        return ticket != null;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }


    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}
