package com.example.mock_cocoka_mane_spreader.service.cache;

import com.example.mock_cocoka_mane_spreader.model.SpreadMoneyBundleInfoDetail;
import com.example.mock_cocoka_mane_spreader.repository.SpreadMoneyBundleInfoDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@CacheConfig(cacheNames = {"spreadMoneyBundleInfoDetail"})
public class SpreadMoneyBundleInfoDetailCache {

    @Autowired
    SpreadMoneyBundleInfoDetailRepository spreadMoneyBundleInfoDetailRepository;

    @Cacheable(key = "#token")
    public List<SpreadMoneyBundleInfoDetail> getOnCacheByToken(String token) {
        return spreadMoneyBundleInfoDetailRepository.findByToken(token);
    }

    @CachePut(key = "#token")
    public List<SpreadMoneyBundleInfoDetail> putOnCacheByToken(String token, Integer amount, Integer receiverId) {
        List<SpreadMoneyBundleInfoDetail> slist = spreadMoneyBundleInfoDetailRepository.findByToken(token);

        SpreadMoneyBundleInfoDetail sd = spreadMoneyBundleInfoDetailRepository.save(new SpreadMoneyBundleInfoDetail(token, amount, receiverId));
        slist.add(sd);

        return slist;
    }
}
