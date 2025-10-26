package org.guang.web3module.controller;

import org.guang.web3module.service.EthereumService;
import org.guang.web3module.service.WorldcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EthereumController {

    @Autowired
    private EthereumService ethereumService;

    @Autowired
    private WorldcoinService worldcoinService;
    @GetMapping("/token-name")
    public ResponseEntity<String> getTokenName() {
        try {
            return ResponseEntity.ok(ethereumService.getTokenName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/total-supply")
    public ResponseEntity<String> getTotalSupply() {
        try {
            return ResponseEntity.ok(ethereumService.getTotalSupplyFormatted());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<String> getBalance(@RequestParam String address) {
        try {
            return ResponseEntity.ok(ethereumService.getBalance(address));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String to, @RequestParam BigInteger amount) {
        try {
            return ResponseEntity.ok(ethereumService.transfer(to, amount));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/account-balance")
    public ResponseEntity<String> getAccountBalance(@RequestParam String address) {
        try {
            BigInteger balance = ethereumService.getAccountBalance(address);
            return ResponseEntity.ok(new BigDecimal(balance).divide(new BigDecimal("1000000000000000000")).toPlainString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/batch-transfer")
    public ResponseEntity<String> batchTransfer(@RequestParam List<String> toAddresses, @RequestParam BigInteger amount) {
        try {
            StringBuilder result = new StringBuilder();
            for (String to : toAddresses) {
                result.append(ethereumService.transfer(to, amount)).append("\n");
            }
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/wld")
    public ResponseEntity<String> getBalanceWLD() {
        try {
            return ResponseEntity.ok(worldcoinService.getBalanceWLD());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}