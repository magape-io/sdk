# 1、SDK import (latest)
[https://magape-official.github.io/sdk/java/magape_connect_1.0.0.jar]
# 2、Configuration file
```yaml
basic-api:
  url: https://testnet-api.magape.io/
  sk: generate from admin platform
```
# 3、SDK functions (JAVA)
## 3.1、getPopList  
### 3.1.1、Features
Get all NFTs in the wallet
### 3.1.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| networkId | The chain id to be queried (MagApe testnet: 141319, BNB Testnet: 97) | Yes |
| requestId | Unique traceId, cannot be repeated | Yes |
| propPageReq  | Pagination query parameters, default 20 | Yes |

### 3.1.3、Return data
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
        "category":"City",
        "level": "Common",
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
### 3.1.4、Sample code
```java
PropPageReq propPageReq = new PropPageReq();
  try {
      String requestID = UUID.randomUUID().toString();
      propPageReq.setAddress("0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB");
      Response<com.wd.api.model.Response<Page<PropLog>>> execute = apiService.getPopList(97, requestID, propPageReq).execute();
      if (execute.isSuccessful()) {
          System.out.println(JSON.toJSONString(execute.body()));
      } else {
          System.out.println(execute.errorBody());
      }
  } catch (Exception e) {
      throw new RuntimeException(e);
  }
```


## 3.2、uploadOrUpdateProp
### 3.2.1、Features
Upload or update game props
### 3.9.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| requestId | Unique traceId, cannot be repeated | Yes |

### 3.2.3、Return data
```json
{
  "code": 200,
  "data":"succes",
  "message": "success"
}
```
### 3.2.4、Sample code
```java
String requestID = UUID.randomUUID().toString();
GamePropReq gamePropReq = new GamePropReq();
GameProp gameProp = new GameProp();
gameProp.setName("test");
gameProp.setType("cap");
gameProp.setCost(0.1);
gameProp.setImage("https://testnet-api.magape.io/ipfs/QmWJEQchSo7HNUzctzTtCPnefFwqzy2ZJAsZcBunvjY8SE");
gameProp.setId(String.valueOf(402003351));
gameProp.setMaxBuy(10);
gameProp.setMaxSell(100);
gamePropReq.setGameProps(List.of(gameProp));
Response<com.wd.api.model.Response<String>> execute = apiService.uploadOrUpdateProp(requestID,gamePropReq).execute();
if (execute.isSuccessful()) {
    System.out.println(JSON.toJSONString(execute.body()));
} else {
    System.out.println(execute.errorBody());
}
```
