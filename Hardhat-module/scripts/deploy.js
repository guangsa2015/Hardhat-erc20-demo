const hre = require("hardhat");

async function main() {
    const [deployer] = await hre.ethers.getSigners();
    console.log("当前部署账户地址：", deployer.address);
    //onst balanceBigInt = await deployer.getBalance(); // 加 await，返回 BigInt
    //const balanceEth = hre.ethers.formatEther(balanceBigInt); // 转换为 ETH 字符串
    //console.log("该账户余额：", balanceEth, "ETH");


    const initialSupply = 1000000; // 100万代币
    const MyToken = await hre.ethers.getContractFactory("contracts/MyToken.sol:MyToken");
    const token = await MyToken.deploy(initialSupply, { gasLimit: 6721975, gasPrice: ethers.parseUnits("20", "gwei") });
    await token.waitForDeployment();
    console.log("MyToken deployed to:", await token.getAddress());
}

main().catch((error) => {
    console.error(error);
    process.exitCode = 1;
});