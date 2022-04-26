package com.payment.model;

public class paymentmodel {
    private int paymentId;
    private String userId;
    private String date;
    private int monthlyUnit;
    private int amount;

    public paymentmodel() {
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMonthlyUnit() {
        return monthlyUnit;
    }

    public void setMonthlyUnit(int monthlyUnit) {
        this.monthlyUnit = monthlyUnit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
