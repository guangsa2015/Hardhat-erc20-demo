require("@nomicfoundation/hardhat-toolbox");
require("hardhat-abi-exporter");

// 导入 Ganache 账户私钥（复制第一个账户的私钥）
const GANACHE_PRIVATE_KEY = "0x4f3edf983ac636a65a842ce7c78d9aa706d3b113bce9c46f30d7d21715b23b1d";

module.exports = {
    solidity: "0.8.20",
    networks: {
        localhost: {
            url: "http://127.0.0.1:8545",
            // 导入 Ganache 账户私钥（复制第一个账户的私钥）
            accounts: [GANACHE_PRIVATE_KEY], // 关键：指定使用 Ganache 的账户私钥，则不需要mnemonic
            // accounts: {
            //     mnemonic: "myth like bonus scare over problem client lizard pioneer submit female collect",
            //     count: 20
            // },
            blockGasLimit: 30000000,
            gasPrice: 20000000000, // 20 Gwei
            gas: 6721975, // 默认 Gas 限制
            mining: {
                auto: true,
                interval: 0
            }
        }
    },
    abiExporter: {
        path: "./artifacts/abi/",
        runOnCompile: true,
        clear: true,
        flat: true,
        only: ["contracts/MyToken.sol:MyToken"]
    }
};