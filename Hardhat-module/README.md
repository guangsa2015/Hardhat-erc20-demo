### 安装依赖
`npm install`

### 编译
`npx hardhat compile`

### 启动本地测试网
- #### 内存链，使用node 每次启动新生成数据
`npx hardhat node`
- #### 持久化链，使用Ganache,指定保存路径与固定生成种子账户
```
ganache --db ./ganache-data --accounts 20 --defaultBalanceEther 10000 --host 127.0.0.1 --port 8545 --deterministic
```
`启动链后要看一下助记词Mnemonic，把hardhat.config.js中的助记词修改与之一致， 建议使用私钥，就不需要助记词了`
- `查看链ID, 启动后在终端中查看 1337就是链ID
- Chain
- ==================
- Hardfork: shanghai
- Id:       1337`


### 部署
`npx hardhat run scripts/deploy.js --network localhost`
- MyToken deployed to: 0xe78A0F7E598Cc8b0Bb87894B0F60dD2a88d6a8Ab

### 测试
`npx hardhat test --network localhost`

### 使用控制台命令进行合约调用##
`npx hardhat console --network localhost`  `进入交互模式`

  ### 1. 获取合约工厂（需与合约名称一致，如"MyToken"）
``` js 
const ContractFactory = await ethers.getContractFactory("contracts/MyToken.sol:MyToken"); 
```
  ### 2. 连接到已部署的合约
``` js 
const contract = await ContractFactory.attach("0xe78A0F7E598Cc8b0Bb87894B0F60dD2a88d6a8Ab");
```
  ### 3. 调用函数  因为Mytoken继承了ERC20.sol ，所以可以调用父类函数

``` js 
await contract.name();
```
 - MyToken

``` js
await contract.totalSupply();
``` 
   - 1000000000000000000000000n

### 使用Web3j 将solidity 转换成java类
```
1. 先在根目录编译 npx hardhat compile
2. 然后找abi生成的目录；
3. 使用web3j命令生成java类； web3j包放在tool里面；
4. web3j generate solidity --abiFile artifacts/abi/MyToken.json --outputDir java --package org.guang.web3module.service
```
### 把java类拷贝到目标包里面
- org.guang.web3module.service.MyToken.java


## 创世区块的内容 
```
Block {
  provider: HardhatEthersProvider {
    _hardhatProvider: LazyInitializationProviderAdapter {
      _providerFactory: [AsyncFunction (anonymous)],
      _emitter: [EventEmitter],
      _initializingPromise: [Promise],
      provider: [BackwardsCompatibilityProviderAdapter]
    },
    _networkName: 'localhost',
    _blockListeners: [],
    _transactionHashListeners: Map(0) {},
    _eventListeners: []
  },
  number: 0,
  hash: '0xed7c23ec2fde8d7ae264f3a3e77a5277054f97c03d09851009a83e7c066d63de',
  timestamp: 1759542773,
  parentHash: '0x0000000000000000000000000000000000000000000000000000000000000000',
  parentBeaconBlockRoot: undefined,
  nonce: '0x0000000000000000',
  difficulty: 0n,
  gasLimit: 30000000n,
  gasUsed: 0n,
  stateRoot: undefined,
  receiptsRoot: undefined,
  blobGasUsed: undefined,
  excessBlobGas: undefined,
  miner: '0x0000000000000000000000000000000000000000',
  prevRandao: null,
  extraData: '0x',
  baseFeePerGas: 1000000000n
}
```
## 第二个区块的内容
```
Block {
  provider: HardhatEthersProvider {
    _hardhatProvider: LazyInitializationProviderAdapter {
      _providerFactory: [AsyncFunction (anonymous)],
      _emitter: [EventEmitter],
      _initializingPromise: [Promise],
      provider: [BackwardsCompatibilityProviderAdapter]
    },
    _networkName: 'localhost',
    _blockListeners: [],
    _transactionHashListeners: Map(0) {},
    _eventListeners: []
  },
  number: 1,
  hash: '0xbbf7bbf4c3dce8770cc690626c6a34b27b98082d1da2f9a5616935c865348fe5',
  timestamp: 1759545266,
  parentHash: '0xed7c23ec2fde8d7ae264f3a3e77a5277054f97c03d09851009a83e7c066d63de',
  parentBeaconBlockRoot: undefined,
  nonce: '0x0000000000000000',
  difficulty: 0n,
  gasLimit: 30000000n,
  gasUsed: 975538n,
  stateRoot: undefined,
  receiptsRoot: undefined,
  blobGasUsed: undefined,
  excessBlobGas: undefined,
  miner: '0x0000000000000000000000000000000000000000',
  prevRandao: null,
  extraData: '0x',
  baseFeePerGas: 875000000n
}
```