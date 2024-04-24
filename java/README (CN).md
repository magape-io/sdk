# 1、SDK导入 (最新）
[https://testnet-api.magape.io/ipfs/QmXMaevcXPdJJTZyNP8wJ5AswoKdmKcHHXwxkpmYyamQWb/digital-wallet-api-starter-1.0.0-SNAPSHOT-jar-with-dependencies.jar](https://whyindian.ddns.net/ipfs/QmXMaevcXPdJJTZyNP8wJ5AswoKdmKcHHXwxkpmYyamQWb/digital-wallet-api-starter-1.0.0-SNAPSHOT-jar-with-dependencies.jar)
# 2、配置文件
```yaml
basic-api:
  url: https://testnet-api.magape.io/
  sk: generate from admin platform
```
# 3、SDK方法(JAVA)
## 3.1、walletInfo 
### 3.1.1、功能介绍
获取钱包所有资产信息(gas coin、erc20 coin、所有的nft)
### 3.1.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 要查看的用户token | 是 |
| networkId | 要查询的链id(WD链:141319 、BNB Testnet:97 ) | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |

### 3.1.3、返回数据
```json
{
  "code": 200, //状态码,200成功,401权限异常,其他异常
  "data": {
    "address": "0x4fbf2de720f2301f89dd3442e57f9c3e12d59b93", // 用户地址
    "id": 0,
    "mainCoin": {
      "balance": 9.899579443497757, // gas coin余额
      "symbol": "BNB" // gas coin 名称
    },
    "nftList": [ // nft列表
      {
        "attrList": [ // nft属性
          {
            "traitType": "Grade",
            "value": "SS"
          }
        ],
        "attrs": "[{\"traitType\":\"Grade\",\"value\":\"SS\"}]",
        "createTime": 1707195332000, // 创建时间
        "erc721ContractAddress": "0x3461979a90d700a290338957bfb09fb297694a0e", // 所属nft合约地址
        "fromAddress": "0x0000000000000000000000000000000000000000", // nft源头
        "id": 851,
        "imageUrl": "https://testnet-api.magape.io/ipfs/QmT5gqF7mMRy9484sG4XVQjUY1gHN2bLPoY81NC4vVwS3H", nft图片地址
        "imgCid": "ipfs://QmT5gqF7mMRy9484sG4XVQjUY1gHN2bLPoY81NC4vVwS3H", // nft图片cid
        "level": "SS", // nft等级
        "method": "mint", // nft获得方式 mint、transfer
        "name": "X.APE #795", // nft名称
        "networkId": 141319, // 所属链id
        "nftCid": "k2k4r8okjkzfv76jo9y8ceeehw40q7jlxijf02ha9synne61q2mribop/795", //nft原数据cid
        "nftCidUrl": "https://testnet-api.magape.io/ipns/k2k4r8okjkzfv76jo9y8ceeehw40q7jlxijf02ha9synne61q2mribop/795", // 原数据地址
        "price": 0, //当前nft在wd市场的售价
        "status": 2, // 状态,1:处理中,2:成功
        "toAddress": "0x4fbf2de720f2301f89dd3442e57f9c3e12d59b93", // nft所属人
        "tokenId": 795, // nft tokenId
        "transactionHash": "0x1566bdf3b0faab885c4ecf3885a9f0c455ec19d5b1d76b05d197d38dbdc2eb34" // 链上唯一交易码
      }
    ],
    "otherCoin": [ // 用户在线上钱包拥有的erc20代币
      {
        "balance": 0.95, // 代币余额
        "erc20Address": "0x2bD4b92BE7a436DC043Dfd4710D5cBE52C94d39b", // 代币地址
        "symbol": "GGC" //代币名称
      }
    ]
  },
  "message": "success"
}
```
### 3.1.4、示例代码
```java
String requestID = UUID.randomUUID().toString();
Response<com.wd.api.model.Response<WalletVO>> execute;
try {
    execute = apiService.walletInfo(token,141319, requestID).execute();
} catch (Exception e) {
    throw new RuntimeException(e);
}
if (execute.isSuccessful()) {
    System.out.println(JSON.toJSONString(execute.body()));
} else {
    System.out.println(execute.errorBody());
}
```

## 3.2、getPopList  
### 3.2.1、功能介绍
获取钱包所有的nft
### 3.2.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 要查看的用户token | 是 |
| networkId | 要查询的链id(WD链:141319 、BNB Testnet:97 ) | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |
| propPageReq  | 分页查询参数,默认20条 | 是 |

### 3.2.3、返回数据
```json
{
  "code": 200,
  "data": {
    "data": [
      {
        "attrList": [
          {
            "traitType": "Grade",
            "value": "S"
          }
        ],
        "attrs": "[{\"traitType\":\"Grade\",\"value\":\"S\"}]",
        "createTime": 1707123617000,
        "erc721ContractAddress": "0x3461979a90d700a290338957bfb09fb297694a0e",
        "fromAddress": "0x0000000000000000000000000000000000000000",
        "id": 765,
        "imageUrl": "https://testnet-api.magape.io/ipfs/QmY225Bo3dpMk46KEFYe9RB4TG9bgtZJXw5c7xGwrKu5D5",
        "imgCid": "ipfs://QmY225Bo3dpMk46KEFYe9RB4TG9bgtZJXw5c7xGwrKu5D5",
        "level": "S",
        "method": "mint",
        "name": "X.APE #712",
        "networkId": 141319,
        "nftCid": "k2k4r8okjkzfv76jo9y8ceeehw40q7jlxijf02ha9synne61q2mribop/712",
        "nftCidUrl": "https://testnet-api.magape.io/ipns/k2k4r8okjkzfv76jo9y8ceeehw40q7jlxijf02ha9synne61q2mribop/712",
        "price": 0,
        "status": 2,
        "toAddress": "0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb",
        "tokenId": 712,
        "transactionHash": "0xabb7ba4b8b0e59f55b3102fa061201c3779ec850e112db52b1a472b1bdd154e4"
      }
    ],
    "hasNext": true,
    "pageNo": 1,
    "pageSize": 1,
    "totalPage": 171,
    "totalRecord": 171
  },
  "message": "success"
}
```
### 3.2.4、示例代码
```java
String requestID = UUID.randomUUID().toString();
Response<com.wd.api.model.Response<WalletVO>> execute;
try {
    execute = apiService.walletInfo(token, 141319, requestID).execute();
} catch (Exception e) {
    throw new RuntimeException(e);
}
if (execute.isSuccessful()) {
    System.out.println(JSON.toJSONString(execute.body()));
} else {
    System.out.println(execute.errorBody());
}
```

## 3.3、diamondBalance  
### 3.3.1、功能介绍
获取钻石余额
### 3.3.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 要查看的用户token | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |

### 3.3.3、返回数据
```json
{
  "code": 200,
  "data": 2590, // 余额
  "message": "success"
}
```
### 3.3.4、示例代码
```java
String requestID = UUID.randomUUID().toString();
Response<com.wd.api.model.Response<BigDecimal>> execute = apiService.diamondBalance(token, requestID).execute();
if (execute.isSuccessful()) {
    assert execute.body() != null;
    System.out.println(JSON.toJSONString(execute.body()));
} else {
    System.out.println(execute.errorBody());
}
```
## 3.4、gasCoinBalance  
### 3.4.1、功能介绍
获取gas币余额
### 3.4.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 要查看的用户token | 是 |
| networkId | 要查询的链id(WD链:141319 、BNB Testnet:97 ) | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |

### 3.4.3、返回数据
```json
{
  "code": 200,
  "data": {
    "BNB": 49985.95833432954
  },
  "message": "success"
}
```
### 3.4.4、示例代码
```java
String requestID = UUID.randomUUID().toString();
Response<com.wd.api.model.Response<Map<String, BigDecimal>>> execute = apiService.gasCoinBalance(token, 4, requestID).execute();
if (execute.isSuccessful()) {
    assert execute.body() != null;
    System.out.println(JSON.toJSONString(execute.body()));
} else {
    System.out.println(execute.errorBody());
}
```
## 3.5、goldCoinBalance   
### 3.5.1、功能介绍
获取金币余额
### 3.5.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 要查看的用户token | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |

### 3.5.3、返回数据
```json
{
  "code": 200,
  "data": 577777780001,
  "message": "success"
}
```
### 3.5.4、示例代码
```java
String requestID = UUID.randomUUID().toString();
Response<com.wd.api.model.Response<BigDecimal>> execute = apiService.goldCoinBalance(token, requestID).execute();
if (execute.isSuccessful()) {
    System.out.println(JSON.toJSONString(execute.body()));
} else {
    System.out.println(execute.errorBody());
}
```
## 3.6、address    
### 3.6.1、功能介绍
根据token获取用户钱包地址
### 3.6.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 要查看的用户token | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |

### 3.6.3、返回数据
```json
{
  "code": 200,
  "data": "0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB",
  "message": "success"
}
```
### 3.6.4、示例代码
```java
String requestID = UUID.randomUUID().toString();
Response<com.wd.api.model.Response<String>> execute = apiService.address(token, requestID).execute();
if (execute.isSuccessful()) {
    System.out.println(JSON.toJSONString(execute.body()));
} else {
    System.out.println(execute.errorBody());
}
```
## 3.7、syncAsset    
### 3.7.1、功能介绍
同步金币到用户地址
### 3.7.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 接收方的token | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |
| from | 发送方的钱包地址,如果是系统则为null | 是 |
| to | 接收方钱包地址 | 是 |
| balanceChange  | 发送余额,正数 | 是 |

### 3.7.3、返回数据
```json
{
  "code": 200,
  "data":"update user asset success",
  "message": "success"
}
```
### 3.7.4、示例代码
```java
String requestID = UUID.randomUUID().toString();
Response<com.wd.api.model.Response<String>> execute = apiService.syncUserAsset(token, requestID, null, "0xa2c441aa2cbbc016e1e9a551e2e6283d264006c3", BigDecimal.valueOf(100)).execute();
if (execute.isSuccessful()) {
    System.out.println(execute.body());
} else {
    System.out.println(execute.errorBody());
}
```
## 3.8、exit    
### 3.8.1、功能介绍
用户退出游戏
### 3.8.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 接收方的token | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |

### 3.8.3、返回数据
```json
{
  "code": 200,
  "data":"exit succes",
  "message": "success"
}
```
### 3.8.4、示例代码
```java
String requestID = UUID.randomUUID().toString();
Response<com.wd.api.model.Response<String>> execute = apiService.exit(token, requestID).execute();
if (execute.isSuccessful()) {
    System.out.println(JSON.toJSONString(execute.body()));
} else {
    System.out.println(execute.errorBody());
}
```

## 3.9、uploadOrUpdateProp
### 3.9.1、功能介绍
上传或更新游戏道具
### 3.9.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 接收方的token | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |

### 3.9.3、返回数据
```json
{
  "code": 200,
  "data":"succes",
  "message": "success"
}
```
### 3.9.4、示例代码
```java
String requestID = UUID.randomUUID().toString();
 GamePropReq gamePropReq = new GamePropReq();
 GameProp gameProp = new GameProp();
 gameProp.setName("test");
 gameProp.setType("cap");
 gameProp.setCost(0.1);
 gameProp.setImage("https://testnet-api.magape.io/ipfs/QmWJEQchSo7HNUzctzTtCPnefFwqzy2ZJAsZcBunvjY8SE");
 gameProp.setId(String.valueOf(402003351));
 GameProp.Attr attr = new GameProp.Attr();
 attr.setTraitType("health");
 attr.setValue(100);
 gameProp.setAttributes(List.of(attr));
 gamePropReq.setGameProps(List.of(gameProp));
 Response<com.wd.api.model.Response<String>> execute = apiService.uploadOrUpdateProp(token, requestID,gamePropReq).execute();
 if (execute.isSuccessful()) {
     System.out.println(JSON.toJSONString(execute.body()));
 } else {
     System.out.println(execute.errorBody());
 }
```

# 4、HTTP报文
## 4.1、walletInfo 
### 4.1.1、功能介绍
获取钱包所有资产信息(gas coin、erc20 coin、所有的nft)
### 4.1.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 要查看的用户token | 是 |
| networkId | 要查询的链id(WD链:141319 、BNB Testnet:97 ) | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |

### 4.1.3、返回数据
```json
{
  "code": 200, //状态码,200成功,401权限异常,其他异常
  "data": {
    "address": "0x4fbf2de720f2301f89dd3442e57f9c3e12d59b93", // 用户地址
    "id": 0,
    "mainCoin": {
      "balance": 9.899579443497757, // gas coin余额
      "symbol": "BNB" // gas coin 名称
    },
    "nftList": [ // nft列表
      {
        "attrList": [ // nft属性
          {
            "traitType": "Grade",
            "value": "SS"
          }
        ],
        "attrs": "[{\"traitType\":\"Grade\",\"value\":\"SS\"}]",
        "createTime": 1707195332000, // 创建时间
        "erc721ContractAddress": "0x3461979a90d700a290338957bfb09fb297694a0e", // 所属nft合约地址
        "fromAddress": "0x0000000000000000000000000000000000000000", // nft源头
        "id": 851,
        "imageUrl": "https://testnet-api.magape.io/ipfs/QmT5gqF7mMRy9484sG4XVQjUY1gHN2bLPoY81NC4vVwS3H", nft图片地址
        "imgCid": "ipfs://QmT5gqF7mMRy9484sG4XVQjUY1gHN2bLPoY81NC4vVwS3H", // nft图片cid
        "level": "SS", // nft等级
        "method": "mint", // nft获得方式 mint、transfer
        "name": "X.APE #795", // nft名称
        "networkId": 141319, // 所属链id
        "nftCid": "k2k4r8okjkzfv76jo9y8ceeehw40q7jlxijf02ha9synne61q2mribop/795", //nft原数据cid
        "nftCidUrl": "https://testnet-api.magape.io/ipns/k2k4r8okjkzfv76jo9y8ceeehw40q7jlxijf02ha9synne61q2mribop/795", // 原数据地址
        "price": 0, //当前nft在wd市场的售价
        "status": 2, // 状态,1:处理中,2:成功
        "toAddress": "0x4fbf2de720f2301f89dd3442e57f9c3e12d59b93", // nft所属人
        "tokenId": 795, // nft tokenId
        "transactionHash": "0x1566bdf3b0faab885c4ecf3885a9f0c455ec19d5b1d76b05d197d38dbdc2eb34" // 链上唯一交易码
      }
    ],
    "otherCoin": [ // 用户在线上钱包拥有的erc20代币
      {
        "balance": 0.95, // 代币余额
        "erc20Address": "0x2bD4b92BE7a436DC043Dfd4710D5cBE52C94d39b", // 代币地址
        "symbol": "GGC" //代币名称
      }
    ]
  },
  "message": "success"
}
```
### 4.1.4、示例代码
```http
curl --location 'https://testnet-api.magape.io/api/blockchain/allAssets' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'networkId: 141319' \
--header 'requestId: 123123123' \
--header 'X-Secret-Key: xxxx'
```
## 4.2、getPopList  
### 4.2.1、功能介绍
获取钱包所有的nft
### 4.2.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 要查看的用户token | 是 |
| networkId | 要查询的链id(WD链:141319 、BNB Testnet:97 ) | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |
| X-Secret-Key | 在平台申请的secret key | 是 |
| propPageReq  | 分页查询参数,默认20条 | 是 |

### 4.2.3、返回数据
```json
{
  "code": 200,
  "data": {
    "data": [
      {
        "attrList": [
          {
            "traitType": "Grade",
            "value": "S"
          }
        ],
        "attrs": "[{\"traitType\":\"Grade\",\"value\":\"S\"}]",
        "createTime": 1707123617000,
        "erc721ContractAddress": "0x3461979a90d700a290338957bfb09fb297694a0e",
        "fromAddress": "0x0000000000000000000000000000000000000000",
        "id": 765,
        "imageUrl": "https://testnet-api.magape.io/ipfs/QmY225Bo3dpMk46KEFYe9RB4TG9bgtZJXw5c7xGwrKu5D5",
        "imgCid": "ipfs://QmY225Bo3dpMk46KEFYe9RB4TG9bgtZJXw5c7xGwrKu5D5",
        "level": "S",
        "method": "mint",
        "name": "X.APE #712",
        "networkId": 141319,
        "nftCid": "k2k4r8okjkzfv76jo9y8ceeehw40q7jlxijf02ha9synne61q2mribop/712",
        "nftCidUrl": "https://testnet-api.magape.io/ipns/k2k4r8okjkzfv76jo9y8ceeehw40q7jlxijf02ha9synne61q2mribop/712",
        "price": 0,
        "status": 2,
        "toAddress": "0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb",
        "tokenId": 712,
        "transactionHash": "0xabb7ba4b8b0e59f55b3102fa061201c3779ec850e112db52b1a472b1bdd154e4"
      }
    ],
    "hasNext": true,
    "pageNo": 1,
    "pageSize": 1,
    "totalPage": 171,
    "totalRecord": 171
  },
  "message": "success"
}
```
### 4.2.4、示例代码
```http
curl --location 'https://testnet-api.magape.io/api/nft/NFTList' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'networkId: 141319' \
--header 'requestId: 123123127' \
--header 'X-Secret-Key: xxx' \
--header 'Content-Type: application/json' \
--data '{}'
```

## 4.3、diamondBalance  
### 4.3.1、功能介绍
获取钻石余额
### 4.3.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 要查看的用户token | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |
| X-Secret-Key | 在平台申请的secret key | 是 |

### 4.3.3、返回数据
```json
{
  "code": 200,
  "data": 2590, // 余额
  "message": "success"
}
```
### 4.3.4、示例代码
```java
curl --location --request GET 'https://testnet-api.magape.io/api/blockchain/diamondBalance' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123134' \
--header 'X-Secret-Key: xxx' \
--header 'Content-Type: application/json' \
--data '{}'
```
## 4.4、gasCoinBalance  
### 4.4.1、功能介绍
获取gas币余额
### 4.4.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 要查看的用户token | 是 |
| networkId | 要查询的链id(WD链:141319 、BNB Testnet:97 ) | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |
| X-Secret-Key | 在平台申请的secret key | 是 |

### 4.4.3、返回数据
```json
{
  "code": 200,
  "data": {
    "BNB": 49985.95833432954
  },
  "message": "success"
}
```
### 4.4.4、示例代码
```java
curl --location --request GET 'https://testnet-api.magape.io/api/blockchain/mainCoinBalance' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123135' \
--header 'X-Secret-Key: xxx' \
--header 'Content-Type: application/json' \
--data '{}'
```
## 4.5、goldCoinBalance   
### 4.5.1、功能介绍
获取金币余额
### 4.5.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 要查看的用户token | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |
| X-Secret-Key | 在平台申请的secret key | 是 |

### 4.5.3、返回数据
```json
{
  "code": 200,
  "data": 577777780001,
  "message": "success"
}
```
### 4.5.4、示例代码
```java
curl --location --request GET 'https://testnet-api.magape.io/api/blockchain/goldBalance' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123137' \
--header 'X-Secret-Key: hnk0d4c7iq9wj3uejsk3ubft' \
```
## 4.6、address    
### 4.6.1、功能介绍
根据token获取用户钱包地址
### 4.6.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 要查看的用户token | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |

### 4.6.3、返回数据
```json
{
  "code": 200,
  "data": "0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB",
  "message": "success"
}
```
### 4.6.4、示例代码
```java
curl --location --request GET 'https://testnet-api.magape.io/api/blockchain/addressByToken' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123138' \
--header 'X-Secret-Key: hnk0d4c7iq9wj3uejsk3ubft' \
--header 'Content-Type: application/json' \
--data '{}'
```
## 4.7、syncAsset    
### 4.7.1、功能介绍
同步金币到用户地址
### 4.7.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 接收方的token | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |
| X-Secret-Key | 在平台申请的secret key | 是 |
| from | 发送方的钱包地址,如果是系统则为null | 是 |
| to | 接收方钱包地址 | 是 |
| balanceChange  | 发送余额,正数 | 是 |

### 4.7.3、返回数据
```json
{
  "code": 200,
  "data":"update user asset success",
  "message": "success"
}
```
### 4.7.4、示例代码
```java
curl --location 'https://testnet-api.magape.io/api/userAsset/syncUserAsset' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123139' \
--header 'X-Secret-Key: xxx' \
--header 'Content-Type: application/json' \
--data '{
    "from":null,
    "to":"0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB",
    "changeBalance":10
}'
```
## 4.8、exit    
### 4.8.1、功能介绍
用户退出游戏
### 4.8.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 接收方的token | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |
| X-Secret-Key | 在平台申请的secret key | 是 |

### 4.8.3、返回数据
```json
{
  "code": 200,
  "data":"exit succes",
  "message": "success"
}
```
### 4.8.4、示例代码
```http
curl --location --request GET 'https://testnet-api.magape.io/api/blockchain/exit' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123135' \
--header 'X-Secret-Key: xxx' \
--header 'Content-Type: application/json' \
--data '{}'
```

## 4.9、exit    
### 4.9.1、功能介绍
用户退出游戏
### 4.9.2、入参数
|  | 描述 | 必填 |
| --- | --- | --- |
| token | 接收方的token | 是 |
| requestId | 唯一traceId,不可以重复 | 是 |
| X-Secret-Key | 在平台申请的secret key | 是 |
| data | 需要上传或着修改的道具 | 
 |

### 4.9.3、返回数据
```json
{
  "code": 200,
  "data":"succes",
  "message": "success"
}
```
### 4.9.4、示例代码
```http
curl --location --request GET 'https://testnet-api.magape.io//api/game/uploadOrUpdateProp' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123135' \
--header 'X-Secret-Key: xxx' \
--header 'Content-Type: application/json' \
--data '{"gameProps":[{"attributes":[{"traitType":"health","value":100}],"cost":0.1,"id":"402003351","image":"https://testnet-api.magape.io/ipfs/QmWJEQchSo7HNUzctzTtCPnefFwqzy2ZJAsZcBunvjY8SE","name":"test","type":"cap"}]}'
```

# 5、MQ报文
## 5.1、介绍
MQ是为了及时将用户gold、nft变更通知到游戏方
gold变更场景

- withdraw:销毁gold -> GGC
- deposit:转出GGC -> 转入gold

nft变更场景

- mint:玩家购买nft
- transfer/buy:nft转移
- upgrade:升级nft
- merge:合并nft
## 5.2、enum
### 5.2.1、operateType
**MINT_NFT(2,"铸造NFT"),**
**TRANSFER_NFT(3,"转移NFT"
**BUY_NFT(4,"购买NFT"),**
**WITHDRAW(9,"提现"),// 销毁gold换取GGC**
**DEPOSIT(10,"GGC换Gold")// 销毁GGC换取gold**
### 5.2.2、validStatus
**PENDING(1,"处理中"),**
**SUCCESS(2,"成功"),**
**FAIL(-1,"失败");**
## 5.3、报文
### 5.3.1、mint nft
```json
{
  "amount": 0,
  "contractAddress": "0x1dAFAf1b2afCe7CD8B985Ed7e98f491043efB542",// 本次NFT所属item合约地址
  "extra": {
    "requestId": "1702698972069yBox67102",// 本次请求id
    "ak": ""
  },
  "fromAddress": "0x1dAFAf1b2afCe7CD8B985Ed7e98f491043efB542",// item合约地址
  "networkId": 2,// 网络id,1:bsctestnet,2:开发链
  "operateType": 2,// operateType 2表示mint nft
  "symbol": "nft #41",// nft名称
  "toAddress": "0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb",// 接受nft的钱包地址
  "txHash": "0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",// 本次链操作的txCode
  "validStatus": 2 // 操作状态2表示成功
}

```
### 5.3.2 transfer nft
```json
{
  "amount": 0,
  "contractAddress": "0x1dAFAf1b2afCe7CD8B985Ed7e98f491043efB542",// 本次NFT所属item合约地址
  "extra": {
    "requestId": "2133",// 本次请求id
    "ak": ""
  },
  "fromAddress": "0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb",// nft发送方
  "networkId": 2, // 网络id,1:bsctestnet,2:开发链
  "operateType": 3, // operateType 3转移NFT
  "symbol": "nft #41",
  "toAddress": "0xA34357486224151dDfDB291E13194995c22Df505", // nft 接收方
  "txHash": "0x6c0b95e5993c71499cf7b692333831522e69fd7068b172c9163b5d6535b12ec4",
  "validStatus": 2 // 操作状态2表示成功
}
```
### 5.3.3、buy nft
```json
{
  "amount": 0,
  "contractAddress": "0x352A40cc430d8d87fCbA44516a583e424567F9Dd",// NFT上架的市场合约地址
  "extra": {
    // 目前在opensea卖,没有requestId
  },
  "fromAddress": "0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb",// nft 卖家
  "networkId": 2, // 网络id,1:bsctestnet,2:开发链
  "operateType": 3, // operateType 3转移NFT
  "symbol": "nft #41",
  "toAddress": "0xA34357486224151dDfDB291E13194995c22Df505", // nft 买家
  "txHash": "0x6c0b95e5993c71499cf7b692333831522e69fd7068b172c9163b5d6535b12ec4",
  "validStatus": 2 // 操作状态2表示成功
}
```
### 5.3.4、withdraw (销毁gold换取GGC)
```json
{
  // 变动金额,目前GOLD和GGC比例1:1
  "amount": 1,
  "contractAddress": "0x0AD61A3312aB48d24B714DB368d2F9AE03A39f31", // node合约地址
  "extra": {
    "requestId": "16967468280",
    "appId": 2 // 所属应用id
  },
  "fromAddress": "0xa34357486224151ddfdb291e13194995c22df505",// 销毁gold的钱包地址
  "networkId": 2,
  "operateType": 9, // operateType 9表示withdraw
  "symbol": "GGC",
  "toAddress": "official",
  "txHash": "0xad60ebf227296b160cadfe1b20e88fa131f2bda9c2994ad150527adc5a25db97",
  "validStatus": 2
}
```
### 5.3.5、deposite (销毁GGC换取GOLD)
```json
{
  "amount": 1,
  "contractAddress": "0x0AD61A3312aB48d24B714DB368d2F9AE03A39f31", // node合约地址
  "extra": {
    "requestId": "123w1",
    "appId": 2 // 所属应用id
  },
  "fromAddress": "official",
  "networkId": 2,
  "operateType": 10,
  "symbol": "GGC",
  "toAddress": "0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb", 接受gold的合约地址
  "txHash": "0x06d37a140fe23e92cb68baefc36578d2fca00aad12b0e497c13eaec8f0ed506f",
  "validStatus": 1
}
```
