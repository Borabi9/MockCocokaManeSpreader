package com.example.mock_cocoka_mane_spreader.service.cache;

import com.example.mock_cocoka_mane_spreader.model.SpreadMoneyBundleInfo;
import com.example.mock_cocoka_mane_spreader.repository.SpreadMoneyBundleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Optional;

@Component
@CacheConfig(cacheNames = {"spreadMoneyBundleInfo"})
public class SpreadMoneyBundleInfoCache {

    @Autowired
    SpreadMoneyBundleInfoRepository spreadMoneyBundleInfoRepository;

    @Cacheable(key = "#token")
    public Optional<SpreadMoneyBundleInfo> getOnCacheByToken(String token) {
        return spreadMoneyBundleInfoRepository.findOneByToken(token);
    }

    @CachePut(key = "#token")
    public SpreadMoneyBundleInfo putOnCacheByToken(String token, Integer spreaderId, String roomId, Integer totalAmount,
                                                   Integer receiverNum, Timestamp spreadAt) {
        SpreadMoneyBundleInfo s = new SpreadMoneyBundleInfo(token, spreaderId, roomId, totalAmount, receiverNum, spreadAt);

        return spreadMoneyBundleInfoRepository.save(s);
    }
}
