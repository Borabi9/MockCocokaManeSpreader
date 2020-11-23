package com.example.mock_cocoka_mane_spreader.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class SpreadMoneyBundleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String token;

    private Integer spreaderId;

    private String roomId;

    private Integer totalAmount;

    private Integer receiverNum;

    private Timestamp spreadAt;

    public SpreadMoneyBundleInfo() {
    }

    public SpreadMoneyBundleInfo(String token, Integer spreaderId, String roomId, Integer totalAmount, Integer receiverNum, Timestamp spreadAt) {
        this.token = token;
        this.spreaderId = spreaderId;
        this.roomId = roomId;
        this.totalAmount = totalAmount;
        this.receiverNum = receiverNum;
        this.spreadAt = spreadAt;
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

    public Integer getSpreaderId() {
        return spreaderId;
    }

    public void setSpreaderId(Integer spreaderId) {
        this.spreaderId = spreaderId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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

    public Timestamp getSpreadAt() {
        return spreadAt;
    }

    public void setSpreadAt(Timestamp spreadAt) {
        this.spreadAt = spreadAt;
    }
}
