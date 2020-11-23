package com.example.mock_cocoka_mane_spreader.repository;

import com.example.mock_cocoka_mane_spreader.model.SpreadMoneyBundleInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SpreadMoneyBundleInfoRepository extends CrudRepository<SpreadMoneyBundleInfo, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM spread_money_bundle_info s WHERE s.token = :token LIMIT 1")
    Optional<SpreadMoneyBundleInfo> findOneByToken(@Param("token") String token);
}
