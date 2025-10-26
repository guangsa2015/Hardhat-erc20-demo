package org.guang.web3module.config;

import org.guang.web3module.service.EthereumService;
import org.guang.web3module.service.WorldcoinService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {

    @Value("${ethereum.rpc.url}")
    private String rpcUrl;

    @Value("${ethereum.admin.private-key}")
    private String privateKey;

    @Value("${ethereum.contract.address}")
    private String contractAddress;

    @Value("${ethereum.gas.price}")
    private long gasPrice;

    @Value("${ethereum.gas.limit}")
    private long gasLimit;

    @Value("${world.chain.rpc}")
    private String wldRpcUrl;

    @Value("${world.chain.contract.address}")
    private String wldContractAddress;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(rpcUrl));
    }

    @Bean
    public EthereumService ethereumService(Web3j web3j) {
        return new EthereumService(web3j, privateKey, contractAddress, gasPrice, gasLimit);
    }

    @Bean
    public WorldcoinService worldcoinService(Web3j web3j) {
        Web3j wld_web3j =Web3j.build(new HttpService(wldRpcUrl));
        return new WorldcoinService(wld_web3j, privateKey, wldContractAddress, gasPrice, gasLimit);
    }
}