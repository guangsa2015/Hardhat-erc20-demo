# Hardhat ERC20 Demo

这是一个基于Hardhat框架开发的ERC20代币示例项目，包含Solidity智能合约和Java后端服务。该项目演示了如何创建、部署ERC20代币，并通过Java Web服务与其进行交互。

## 项目结构

```
.
├── Hardhat-module          # Hardhat项目模块
│   ├── contracts           # Solidity智能合约
│   ├── scripts             # 部署脚本
│   ├── test                # 合约测试
│   └── README.md           # Hardhat模块详细说明
├── Web3-module             # Java Web服务模块
│   ├── src                 # Java源码
│   │   ├── controller      # REST控制器
│   │   ├── service         # 业务逻辑层
│   │   └── config          # 配置文件
│   └── pom.xml             # Maven配置
└── pom.xml                 # 根项目Maven配置
```

## 功能特性

1. 基于OpenZeppelin的ERC20标准实现自定义代币(MyToken)
2. 使用Hardhat进行合约编译、部署和测试
3. 提供完整的Java Web服务与智能合约交互
4. 支持查询代币名称、总供应量、账户余额
5. 支持代币转账功能

## 技术栈

- Solidity ^0.8.20
- Hardhat 开发框架
- OpenZeppelin 合约库
- Java 17
- Spring Boot 3.3.4
- Web3j 4.14.0
- Maven 构建工具

## 快速开始

### 1. 启动本地以太坊网络

```bash
cd Hardhat-module
npx hardhat node
```

或者使用持久化链：

```bash
ganache --db ./ganache-data --accounts 20 --defaultBalanceEther 10000 --host 127.0.0.1 --port 8545 --deterministic
```

### 2. 部署智能合约

```bash
npx hardhat run scripts/deploy.js --network localhost
```

记录输出的合约地址，用于后续配置。

### 3. 配置Java服务

编辑 [Web3-module/src/main/resources/application.properties](file:///E:/IntelliJ_space2024/Hardhat-erc20-demo/Web3-module/src/main/resources/application.properties) 文件，更新以下配置：

```properties
ethereum.rpc.url=http://127.0.0.1:8545
ethereum.admin.private-key=YOUR_PRIVATE_KEY
ethereum.contract.address=DEPLOYED_CONTRACT_ADDRESS
```

### 4. 启动Java Web服务

```bash
cd Web3-module
./mvnw spring-boot:run
```

服务将在 `http://localhost:8080` 启动。

### 5. API接口

- `GET /api/token-name` - 获取代币名称
- `GET /api/total-supply` - 获取代币总供应量
- `GET /api/balance?address={address}` - 查询账户余额
- `POST /api/transfer?to={to}&amount={amount}` - 转账代币
- `GET /api/account-balance?address={address}` - 查询账户ETH余额

## 开发指南

### 编译合约

```bash
npx hardhat compile
```

### 生成Java包装类

```bash
web3j generate solidity --abiFile artifacts/abi/MyToken.json --outputDir java --package org.guang.web3module.service
```

### 运行测试

```bash
npx hardhat test --network localhost
```

## 许可证

本项目仅供学习和参考使用。