package com.example.mock_cocoka_mane_spreader.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SpreadMoneyBundleInfoDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String token;

    private Integer amount;

    private Integer receiverId;

    public SpreadMoneyBundleInfoDetail() {
    }

    public SpreadMoneyBundleInfoDetail(String token, Integer amount, Integer receiverId) {
        this.token = token;
        this.amount = amount;
        this.receiverId = receiverId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }
}
