package org.guang.web3module.service;

import org.guang.web3module.contract.MyToken;
import org.guang.web3module.contract.WLDToken;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import java.math.BigInteger;
import java.math.BigDecimal;

public class EthereumService {
    private final Web3j web3j;
    private final Credentials credentials;
    private final MyToken contract;
    private final StaticGasProvider gasProvider;
    private final WLDToken wldContract;

    public EthereumService(Web3j web3j, String privateKey, String contractAddress, long gasPrice, long gasLimit) {
        this.web3j = web3j;
        this.credentials = Credentials.create(privateKey);
        this.gasProvider = new StaticGasProvider(BigInteger.valueOf(gasPrice), BigInteger.valueOf(gasLimit));
        this.contract = MyToken.load(contractAddress, web3j, credentials, gasProvider);
        this.wldContract = WLDToken.load(contractAddress, web3j, credentials, gasProvider);
    }

    public String getTokenName() throws Exception {
        return contract.name().send();
    }

    public BigInteger getTotalSupply() throws Exception {
        return contract.totalSupply().send();
    }

    public String getTotalSupplyFormatted() throws Exception {
        BigInteger totalSupply = contract.totalSupply().send();
        BigInteger decimals = contract.decimals().send();
        BigDecimal value = new BigDecimal(totalSupply).divide(new BigDecimal(BigInteger.TEN.pow(decimals.intValue())));
        return value.toPlainString();
    }

    public String getBalance(String address) throws Exception {
        BigInteger balance = contract.balanceOf(address).send();
        BigInteger decimals = contract.decimals().send();
        BigDecimal value = new BigDecimal(balance).divide(new BigDecimal(BigInteger.TEN.pow(decimals.intValue())));
        return value.toPlainString();
    }



    public String transfer(String toAddress, BigInteger amount) throws Exception {
        TransactionReceipt receipt = contract.transfer(toAddress, amount).send();
        return receipt.getTransactionHash();
    }

    public BigInteger getAccountBalance(String address) throws Exception {
        return web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance();
    }
}