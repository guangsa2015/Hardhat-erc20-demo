package org.guang.web3module.service;

import org.guang.web3module.contract.WLDToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigDecimal;
import java.math.BigInteger;

public class WorldcoinService {

    @Value("${world.chain.address}")
    private String address;
    private final WLDToken wldContract;

    public WorldcoinService(Web3j web3j, String privateKey, String contractAddress, long gasPrice, long gasLimit){
        Credentials credentials = Credentials.create(privateKey);
        ContractGasProvider  gasProvider = new StaticGasProvider(BigInteger.valueOf(gasPrice), BigInteger.valueOf(gasLimit));
        this.wldContract = WLDToken.load(contractAddress, web3j, credentials, gasProvider);
    }
    /**
     *  查询worldcoin余额
     *
     * @throws Exception
     */
    public String getBalanceWLD() throws Exception {

        BigInteger balance = wldContract.balanceOf(address).send();
        BigInteger decimals = wldContract.decimals().send();
        BigDecimal value = new BigDecimal(balance).divide(new BigDecimal(BigInteger.TEN.pow(decimals.intValue())));
        return value.toPlainString();
    }
}
