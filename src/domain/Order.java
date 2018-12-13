package domain;

import java.util.Date;

public class Order extends Entity {
    private User user;
    private Car car;
    private Date date;
    private int amountOfDays;
    private long totalCost;
    private boolean paid;
    private boolean accepted;

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    private boolean reviewed;
    private String comments;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmountOfDays() {
        return amountOfDays;
    }

    public void setAmountOfDays(int amountOfDays) {
        this.amountOfDays = amountOfDays;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(long totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", car=" + car +
                ", date=" + date +
                ", amountOfDays=" + amountOfDays +
                ", totalCost=" + totalCost +
                ", paid=" + paid +
                ", accepted=" + accepted +
                ", comments='" + comments + '\'' +
                '}';
    }
}
