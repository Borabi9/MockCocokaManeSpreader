package com.example.mock_cocoka_mane_spreader.service;

import com.example.mock_cocoka_mane_spreader.model.SpreadMoneyBundleInfoDetail;
import com.example.mock_cocoka_mane_spreader.repository.UserRepository;
import com.example.mock_cocoka_mane_spreader.service.cache.SpreadMoneyBundleInfoCache;
import com.example.mock_cocoka_mane_spreader.service.cache.SpreadMoneyBundleInfoDetailCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SpreadMoneyBundleService {

    @Autowired
    SpreadMoneyBundleInfoCache spreadMoneyBundleInfoCache;
    @Autowired
    SpreadMoneyBundleInfoDetailCache spreadMoneyBundleInfoDetailCache;
    @Autowired
    UserRepository userRepository;

    private String createMoneyToken() {
        var uuid = UUID.randomUUID().toString();
        var token = uuid.substring(0, 3);

        while (spreadMoneyBundleInfoCache.getOnCacheByToken(token).isPresent()) {
            uuid = UUID.randomUUID().toString();
            token = uuid.substring(0, 3);
        }

        return token;
    }

    private Integer calculateDistribution(String token) {
        var s = spreadMoneyBundleInfoCache.getOnCacheByToken(token).get();
        var currentReceiverNum = spreadMoneyBundleInfoDetailCache.getOnCacheByToken(token).size();

        var totalAmount = s.getTotalAmount();
        var receiverNum = s.getReceiverNum();

        if (!receiverNum.equals(currentReceiverNum)) {
            return totalAmount / receiverNum;
        } else {
            return totalAmount - (totalAmount / receiverNum * (receiverNum - 1));
        }
    }

    public String addSpreadMoneyBundleInfo(Integer userId, String roomId, Integer totalAmount, Integer receiverNum) {
        var token = createMoneyToken();
        var spreadAt = Timestamp.from(ZonedDateTime.now().toInstant());

        // 금액 차감 로직 생략
        spreadMoneyBundleInfoCache.putOnCacheByToken(token, userId, roomId, totalAmount, receiverNum, spreadAt);

        return token;
    }

    public Integer addSpreadMoneyBundleInfoDetail(String token, Integer userId, String roomId) {
        var sCache = spreadMoneyBundleInfoCache.getOnCacheByToken(token);
        var sdCache = spreadMoneyBundleInfoDetailCache.getOnCacheByToken(token);

        if (sCache.filter(s -> s.getSpreadAt().after(Timestamp.from(ZonedDateTime.now().minusMinutes(10).toInstant()))).isEmpty()) {
            return -1;
        }
        if (sCache.filter(s -> s.getSpreaderId().equals(userId)).isPresent()) {
            return -2;
        }
        if (sCache.filter(s -> s.getRoomId().equals(roomId)).isEmpty()) {
            return -3;
        }
        if (sdCache.stream().anyMatch(sd -> sd.getReceiverId().equals(userId))) {
            return -4;
        }
        if (sCache.get().getReceiverNum().equals(sdCache.size())) {
            return -5;
        }

        // 분배된 금액 추가 로직 생략
        var amount = calculateDistribution(token);
        spreadMoneyBundleInfoDetailCache.putOnCacheByToken(token, amount, userId);

        return amount;
    }

    public List<SpreadMoneyBundleInfoDetail> getSpreadMoneyBundleInfoDetailStatuses(String token, Integer userId) {
        if (spreadMoneyBundleInfoCache.getOnCacheByToken(token).filter(s -> s.getSpreaderId().equals(userId)).isEmpty())
            return null;
        if (spreadMoneyBundleInfoCache.getOnCacheByToken(token).filter(s -> s.getSpreadAt().after(Timestamp.from(ZonedDateTime.now().minusDays(7).toInstant()))).isEmpty())
            return null;

        return spreadMoneyBundleInfoDetailCache.getOnCacheByToken(token);
    }
}
