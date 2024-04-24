# 1、SDK import (latest)
[https://magape-official.github.io/sdk/java/magape_connect_1.0.0.jar]
# 2、Configuration file
```yaml
basic-api:
  url: https://testnet-api.magape.io/
  sk: generate from admin platform
```
# 3、SDK functions (JAVA)
## 3.1、walletInfo 
### 3.1.1、Features
Get all asset information of the wallet (gas coin, erc20 coin, all NFT)
### 3.1.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | User token to view | Yes |
| networkId | The chain id to be queried (MagApe testnet: 141319, BNB Testnet: 97) | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |

### 3.1.3、Return data
```json
{
  "code": 200, //Status code, 200 success, 401 permission exception, other exceptions
  "data": {
    "address": "0x4fbf2de720f2301f89dd3442e57f9c3e12d59b93", // User address
    "id": 0,
    "mainCoin": {
      "balance": 9.899579443497757, // gas coin balance
      "symbol": "BNB" // gas coin symbol
    },
    "nftList": [ // NFT list
      {
        "attrList": [ // NFT attributes
          {
            "traitType": "Grade",
            "value": "SS"
          }
        ],
        "attrs": "[{\"traitType\":\"Grade\",\"value\":\"SS\"}]",
        "createTime": 1707195332000, // time stamp
        "erc721ContractAddress": "0x3461979a90d700a290338957bfb09fb297694a0e", // NFT contract address
        "fromAddress": "0x0000000000000000000000000000000000000000", // NFT source
        "id": 851,
        "imageUrl": "https://testnet-api.magape.io/ipfs/QmT5gqF7mMRy9484sG4XVQjUY1gHN2bLPoY81NC4vVwS3H", NFT image URL
        "imgCid": "ipfs://QmT5gqF7mMRy9484sG4XVQjUY1gHN2bLPoY81NC4vVwS3H", // NFT image CID
        "level": "SS", // NFT level
        "method": "mint", // NFT by mint、transfer
        "name": "X.APE #795", // NFT symbol
        "networkId": 141319, // network ID
        "nftCid": "k2k4r8okjkzfv76jo9y8ceeehw40q7jlxijf02ha9synne61q2mribop/795", // NFT CID
        "nftCidUrl": "https://testnet-api.magape.io/ipns/k2k4r8okjkzfv76jo9y8ceeehw40q7jlxijf02ha9synne61q2mribop/795", // Full CID URL
        "price": 0, // NFT current price
        "status": 2, // Status, 1:processing, 2:success
        "toAddress": "0x4fbf2de720f2301f89dd3442e57f9c3e12d59b93", // NFT owner
        "tokenId": 795, // NFT tokenId
        "transactionHash": "0x1566bdf3b0faab885c4ecf3885a9f0c455ec19d5b1d76b05d197d38dbdc2eb34" // blockchain transaction hash
      }
    ],
    "otherCoin": [ // ERC20 tokens owned by users in online wallets
      {
        "balance": 0.95, // token balance
        "erc20Address": "0x2bD4b92BE7a436DC043Dfd4710D5cBE52C94d39b", // token address
        "symbol": "GGC" // token symbol
      }
    ]
  },
  "message": "success"
}
```
### 3.1.4、Sample code
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
### 3.2.1、Features
Get all NFTs in the wallet
### 3.2.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | User token to view | Yes |
| networkId | The chain id to be queried (MagApe testnet: 141319, BNB Testnet: 97) | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |
| propPageReq  | Pagination query parameters, default 20 | Yes |

### 3.2.3、Return data
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
### 3.2.4、Sample code
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
### 3.3.1、Features
Get beans balance
### 3.3.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | User token to view | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |

### 3.3.3、Return data
```json
{
  "code": 200,
  "data": 2590, // balance
  "message": "success"
}
```
### 3.3.4、Sample code
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
### 3.4.1、Features
Get gas (native token) balance
### 3.4.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | User token to view | Yes |
| networkId | The chain id to be queried (MagApe Testnet: 141319, BNB Testnet: 97) | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |

### 3.4.3、Return data
```json
{
  "code": 200,
  "data": {
    "BNB": 49985.95833432954
  },
  "message": "success"
}
```
### 3.4.4、Sample code
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
### 3.5.1、Features
Get gold coins balance
### 3.5.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | User token to view | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |

### 3.5.3、Return data
```json
{
  "code": 200,
  "data": 577777780001,
  "message": "success"
}
```
### 3.5.4、Sample code
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
### 3.6.1、Features
Get user wallet address based on token
### 3.6.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | User token to view | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |

### 3.6.3、Return data
```json
{
  "code": 200,
  "data": "0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB",
  "message": "success"
}
```
### 3.6.4、Sample code
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
### 3.7.1、Features
Synchronize gold coins to User address
### 3.7.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | Receiver’s token | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |
| from | The sender’s wallet address, if Yes, the system is null | Yes |
| to | Receiver wallet address | Yes |
| balanceChange  | Send balance, positive number | Yes |

### 3.7.3、Return data
```json
{
  "code": 200,
  "data":"update user asset success",
  "message": "success"
}
```
### 3.7.4、Sample code
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
### 3.8.1、Features
User exits the game
### 3.8.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | Receiver’s token | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |

### 3.8.3、Return data
```json
{
  "code": 200,
  "data":"exit succes",
  "message": "success"
}
```
### 3.8.4、Sample code
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
### 3.9.1、Features
Upload or update game props
### 3.9.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | Receiver’s token | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |

### 3.9.3、Return data
```json
{
  "code": 200,
  "data":"succes",
  "message": "success"
}
```
### 3.9.4、Sample code
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
### 4.1.1、Features
Get all asset information of the wallet (gas coin, ERC20 coin, all nft)
### 4.1.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | User token to view | Yes |
| networkId | The chain id to be queried (MagApe Testnet: 141319, BNB Testnet: 97) | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |

### 4.1.3、Return data
```json
{
  "code": 200, // Status code, 200 success, 401 permission exception, other exceptions
  "data": {
    "address": "0x4fbf2de720f2301f89dd3442e57f9c3e12d59b93", // User address
    "id": 0,
    "mainCoin": {
      "balance": 9.899579443497757, // Gas coin balance
      "symbol": "BNB" // Gas coin symbol
    },
    "nftList": [ // NFT list
      {
        "attrList": [ // NFT attributes
          {
            "traitType": "Grade",
            "value": "SS"
          }
        ],
        "attrs": "[{\"traitType\":\"Grade\",\"value\":\"SS\"}]",
        "createTime": 1707195332000, // Time stamp
        "erc721ContractAddress": "0x3461979a90d700a290338957bfb09fb297694a0e", // NFT contract address
        "fromAddress": "0x0000000000000000000000000000000000000000", // NFT source
        "id": 851,
        "imageUrl": "https://testnet-api.magape.io/ipfs/QmT5gqF7mMRy9484sG4XVQjUY1gHN2bLPoY81NC4vVwS3H", NFT image URL
        "imgCid": "ipfs://QmT5gqF7mMRy9484sG4XVQjUY1gHN2bLPoY81NC4vVwS3H", // NFT image CID
        "level": "SS", // NFT level
        "method": "mint", // NFT by mint、transfer
        "name": "X.APE #795", // NFT symbol
        "networkId": 141319, // network ID
        "nftCid": "k2k4r8okjkzfv76jo9y8ceeehw40q7jlxijf02ha9synne61q2mribop/795", //NFT CID
        "nftCidUrl": "https://testnet-api.magape.io/ipns/k2k4r8okjkzfv76jo9y8ceeehw40q7jlxijf02ha9synne61q2mribop/795", // Full CID URL
        "price": 0, // NFT current price
        "status": 2, // Status, 1:processing, 2:success
        "toAddress": "0x4fbf2de720f2301f89dd3442e57f9c3e12d59b93", // NFT owner
        "tokenId": 795, // NFT tokenId
        "transactionHash": "0x1566bdf3b0faab885c4ecf3885a9f0c455ec19d5b1d76b05d197d38dbdc2eb34" // blockchain transaction hash
      }
    ],
    "otherCoin": [ // ERC20 tokens owned by users in online wallets
      {
        "balance": 0.95, // Token balance
        "erc20Address": "0x2bD4b92BE7a436DC043Dfd4710D5cBE52C94d39b", // Token address
        "symbol": "GGC" // Token symbol
      }
    ]
  },
  "message": "success"
}
```
### 4.1.4、Sample code
```http
curl --location 'https://testnet-api.magape.io/api/blockchain/allAssets' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'networkId: 141319' \
--header 'requestId: 123123123' \
--header 'X-Secret-Key: xxxx'
```
## 4.2、getPopList  
### 4.2.1、Features
Get all NFTs in the wallet
### 4.2.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | User token to view | Yes |
| networkId | The chain id to be queried (MagApe Testnet: 141319, BNB Testnet: 97) | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |
| X-Secret-Key | The secret key applied for on the platform | Yes |
| propPageReq  | Pagination query parameters, default 20 | Yes |

### 4.2.3、Return data
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
### 4.2.4、Sample code
```http
curl --location 'https://testnet-api.magape.io/api/NFT/NFTList' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'networkId: 141319' \
--header 'requestId: 123123127' \
--header 'X-Secret-Key: xxx' \
--header 'Content-Type: application/json' \
--data '{}'
```

## 4.3、diamondBalance  
### 4.3.1、Features
Get beans balance
### 4.3.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | User token to view | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |
| X-Secret-Key | The secret key applied for on the platform | Yes |

### 4.3.3、Return data
```json
{
  "code": 200,
  "data": 2590, //  balance
  "message": "success"
}
```
### 4.3.4、Sample code
```java
curl --location --request GET 'https://testnet-api.magape.io/api/blockchain/diamondBalance' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123134' \
--header 'X-Secret-Key: xxx' \
--header 'Content-Type: application/json' \
--data '{}'
```
## 4.4、gasCoinBalance  
### 4.4.1、Features
Get gas （native token) balance
### 4.4.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | User token to view | Yes |
| networkId | The chain id to be queried (MagApe Testnet: 141319, BNB Testnet: 97) | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |
| X-Secret-Key | The secret key applied for on the platform | Yes |

### 4.4.3、Return data
```json
{
  "code": 200,
  "data": {
    "BNB": 49985.95833432954
  },
  "message": "success"
}
```
### 4.4.4、Sample code
```java
curl --location --request GET 'https://testnet-api.magape.io/api/blockchain/mainCoinBalance' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123135' \
--header 'X-Secret-Key: xxx' \
--header 'Content-Type: application/json' \
--data '{}'
```
## 4.5、goldCoinBalance   
### 4.5.1、Features
Get gold coins balance
### 4.5.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | User token to view | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |
| X-Secret-Key | The secret key applied for on the platform | Yes |

### 4.5.3、Return data
```json
{
  "code": 200,
  "data": 577777780001,
  "message": "success"
}
```
### 4.5.4、Sample code
```java
curl --location --request GET 'https://testnet-api.magape.io/api/blockchain/goldBalance' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123137' \
--header 'X-Secret-Key: hnk0d4c7iq9wj3uejsk3ubft' \
```
## 4.6、address    
### 4.6.1、Features
Get user wallet address based on token
### 4.6.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | User token to view | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |

### 4.6.3、Return data
```json
{
  "code": 200,
  "data": "0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB",
  "message": "success"
}
```
### 4.6.4、Sample code
```java
curl --location --request GET 'https://testnet-api.magape.io/api/blockchain/addressByToken' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123138' \
--header 'X-Secret-Key: hnk0d4c7iq9wj3uejsk3ubft' \
--header 'Content-Type: application/json' \
--data '{}'
```
## 4.7、syncAsset    
### 4.7.1、Features
Synchronize gold coins to User address
### 4.7.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | Receiver’s token | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |
| X-Secret-Key | The secret key applied for on the platform | Yes |
| from | The sender’s wallet address, if Yes, the system is null | Yes |
| to | Receiver wallet address | Yes |
| balanceChange  | Send balance, positive number | Yes |

### 4.7.3、Return data
```json
{
  "code": 200,
  "data":"update user asset success",
  "message": "success"
}
```
### 4.7.4、Sample code
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
### 4.8.1、Features
User exits the game
### 4.8.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | Receiver’s token | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |
| X-Secret-Key | The secret key applied for on the platform | Yes |

### 4.8.3、Return data
```json
{
  "code": 200,
  "data":"exit succes",
  "message": "success"
}
```
### 4.8.4、Sample code
```http
curl --location --request GET 'https://testnet-api.magape.io/api/blockchain/exit' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123135' \
--header 'X-Secret-Key: xxx' \
--header 'Content-Type: application/json' \
--data '{}'
```

## 4.9、exit    
### 4.9.1、Features
User exits the game
### 4.9.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| token | Receiver’s token | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |
| X-Secret-Key | The secret key applied for on the platform | Yes |
| data | Props that need to be uploaded or modified | 
 |

### 4.9.3、Return data
```json
{
  "code": 200,
  "data":"succes",
  "message": "success"
}
```
### 4.9.4、Sample code
```http
curl --location --request GET 'https://testnet-api.magape.io//api/game/uploadOrUpdateProp' \
--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIweDREMTFkRjkyMEUwRTQ4YzdFMTMyZTVhOTc1NEM3ZTc1NENkNkVCRkIiLCJkYXRlIjoxNzA3MjAwNDczNjk0fQ.PSFmagKkqBHOfefpqab4kRibozGk2uowaWwfPiUotik' \
--header 'requestId: 123123135' \
--header 'X-Secret-Key: xxx' \
--header 'Content-Type: application/json' \
--data '{"gameProps":[{"attributes":[{"traitType":"health","value":100}],"cost":0.1,"id":"402003351","image":"https://testnet-api.magape.io/ipfs/QmWJEQchSo7HNUzctzTtCPnefFwqzy2ZJAsZcBunvjY8SE","name":"test","type":"cap"}]}'
```

# 5、MQ message
## 5.1、Introduction
In order to promptly notify the game party of changes in user gold and NFT, MQYes
gold change scene

- withdraw: destroy gold -> GGC
- deposit: transfer out of GGC -> transfer into gold

nft change scenario

- mint: players buy nft
- transfer/buy:nft transfer
- upgrade: upgrade nft
- merge: merge nft
## 5.2、enum
### 5.2.1、operateType
**MINT_NFT(2,"mint NFT"),**
**TRANSFER_NFT(3,"transfer NFT"
**BUY_NFT(4,"purchase NFT"),**
**WITHDRAW(9,"withdraw"),// Burn gold to get MAC**
**DEPOSIT(10,"GGC become Gold")// Burn MAC to get gold**
### 5.2.2、validStatus
**PENDING(1,"pending"),**
**SUCCESS(2,"success"),**
**FAIL(-1,"fail");**
## 5.3、Message
### 5.3.1、mint NFT
```json
{
  "amount": 0,
  "contractAddress": "0x1dAFAf1b2afCe7CD8B985Ed7e98f491043efB542",// The item contract address to which this NFT belongs
  "extra": {
    "requestId": "1702698972069yBox67102",// This request ID
    "ak": ""
  },
  "fromAddress": "0x1dAFAf1b2afCe7CD8B985Ed7e98f491043efB542",// item contract address
  "networkId": 2,// 1: 97, 2: 141319
  "operateType": 2,// operateType 2 means mint NFT
  "symbol": "NFT #41",// NFT symbol
  "toAddress": "0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb",// Wallet address that accepts NFT
  "txHash": "0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",// The txCode of this chain operation
  "validStatus": 2 // Operation status 2 indicates success
}

```
### 5.3.2 Transfer NFT
```json
{
  "amount": 0,
  "contractAddress": "0x1dAFAf1b2afCe7CD8B985Ed7e98f491043efB542",// The item contract address to which this NFT belongs
  "extra": {
    "requestId": "2133",// This request ID
    "ak": ""
  },
  "fromAddress": "0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb",// NFT sender
  "networkId": 2, // 1: 97, 2: 141319
  "operateType": 3, // operateType 3 transfer NFT
  "symbol": "NFT #41",
  "toAddress": "0xA34357486224151dDfDB291E13194995c22Df505", // NFT receiver
  "txHash": "0x6c0b95e5993c71499cf7b692333831522e69fd7068b172c9163b5d6535b12ec4",
  "validStatus": 2 // Operation status 2 indicates success
}
```
### 5.3.3、Buy NFT
```json
{
  "amount": 0,
  "contractAddress": "0x352A40cc430d8d87fCbA44516a583e424567F9Dd",// NFT listed market contract address
  "extra": {
    // Currently sold on OpenSea, no requestId
  },
  "fromAddress": "0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb",// NFT seller
  "networkId": 2, // 1: 97, 2: 141319
  "operateType": 3, // operateType 3 transfer NFT
  "symbol": "NFT #41",
  "toAddress": "0xA34357486224151dDfDB291E13194995c22Df505", // NFT buyer
  "txHash": "0x6c0b95e5993c71499cf7b692333831522e69fd7068b172c9163b5d6535b12ec4",
  "validStatus": 2 // Operation status 2 indicates success
}
```
### 5.3.4、Withdraw (Destroy gold in exchange for MAC)
```json
{
  // Change amount, currently the ratio of GOLD to MAC is 1:1
  "amount": 1,
  "contractAddress": "0x0AD61A3312aB48d24B714DB368d2F9AE03A39f31", // Node contract address
  "extra": {
    "requestId": "16967468280",
    "appId": 2 // Belonging application id
  },
  "fromAddress": "0xa34357486224151ddfdb291e13194995c22df505",// Destroy gold's wallet address
  "networkId": 2,
  "operateType": 9, // operateType 9 means withdraw
  "symbol": "MAC",
  "toAddress": "official",
  "txHash": "0xad60ebf227296b160cadfe1b20e88fa131f2bda9c2994ad150527adc5a25db97",
  "validStatus": 2
}
```
### 5.3.5、Deposit (Destroy MAC in exchange for GOLD)
```json
{
  "amount": 1,
  "contractAddress": "0x0AD61A3312aB48d24B714DB368d2F9AE03A39f31", // Node contract address
  "extra": {
    "requestId": "123w1",
    "appId": 2 // Belonging application id
  },
  "fromAddress": "official",
  "networkId": 2,
  "operateType": 10,
  "symbol": "GGC",
  "toAddress": "0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb", // Receiver's address
  "txHash": "0x06d37a140fe23e92cb68baefc36578d2fca00aad12b0e497c13eaec8f0ed506f",
  "validStatus": 1
}
```
