const { expect } = require("chai");

describe("MyToken", function () {
    let token, owner, addr1;

    beforeEach(async function () {
        [owner, addr1] = await ethers.getSigners();
        const MyToken = await ethers.getContractFactory("MyToken");
        token = await MyToken.deploy(1000000);
        await token.waitForDeployment();
    });

    it("Should deploy with correct initial supply", async function () {
        expect(await token.totalSupply()).to.equal(1000000n * 10n ** 18n);
    });

    it("Should transfer tokens", async function () {
        await token.transfer(addr1.address, 1000n * 10n ** 18n);
        expect(await token.balanceOf(addr1.address)).to.equal(1000n * 10n ** 18n);
    });
});