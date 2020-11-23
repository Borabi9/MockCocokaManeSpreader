package com.example.mock_cocoka_mane_spreader.repository;

import com.example.mock_cocoka_mane_spreader.model.SpreadMoneyBundleInfoDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpreadMoneyBundleInfoDetailRepository extends CrudRepository<SpreadMoneyBundleInfoDetail, Integer> {

    List<SpreadMoneyBundleInfoDetail> findByToken(String token);
}
