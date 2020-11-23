package com.example.mock_cocoka_mane_spreader.model;

public class SpreadForm {

    private Integer totalAmount;

    private Integer receiverNum;

    public SpreadForm(Integer totalAmount, Integer receiverNum) {
        this.totalAmount = totalAmount;
        this.receiverNum = receiverNum;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getReceiverNum() {
        return receiverNum;
    }

    public void setReceiverNum(Integer receiverNum) {
        this.receiverNum = receiverNum;
    }
}
