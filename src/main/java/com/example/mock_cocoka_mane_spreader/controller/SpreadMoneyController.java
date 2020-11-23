package com.example.mock_cocoka_mane_spreader.controller;

import com.example.mock_cocoka_mane_spreader.model.SpreadForm;
import com.example.mock_cocoka_mane_spreader.model.SpreadMoneyBundleInfoDetail;
import com.example.mock_cocoka_mane_spreader.service.SpreadMoneyBundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/spreadMoney")
@RestController
public class SpreadMoneyController {

    @Autowired
    SpreadMoneyBundleService spreadMoneyBundleService;

    @PostMapping("/flex")
    public ResponseEntity<String> spreadMoney(@RequestHeader("X-USER-ID") Integer userId,
                                              @RequestHeader("X-ROOM-ID") String roomId,
                                              @RequestBody SpreadForm spreadForm) {
        String token = spreadMoneyBundleService.addSpreadMoneyBundleInfo(userId, roomId, spreadForm.getTotalAmount(), spreadForm.getReceiverNum());

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping(path = "/tap/{token}")
    public ResponseEntity<Integer> getMoney(@RequestHeader("X-USER-ID") Integer userId,
                                            @RequestHeader("X-ROOM-ID") String roomId,
                                            @PathVariable("token") String token) {
        Integer amount = spreadMoneyBundleService.addSpreadMoneyBundleInfoDetail(token, userId, roomId);

        if (amount < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(amount, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/status/{token}")
    public ResponseEntity<List<SpreadMoneyBundleInfoDetail>> getStatus(@RequestHeader("X-USER-ID") Integer userId,
                                                                       @PathVariable("token") String token) {
        List<SpreadMoneyBundleInfoDetail> status = spreadMoneyBundleService.getSpreadMoneyBundleInfoDetailStatuses(token, userId);

        if (status == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
    }
}
