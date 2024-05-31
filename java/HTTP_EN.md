# 1、getPopList

## 1.1、Features
Get all NFTs in the wallet

## 1.2、Input parameters
|  | Descriptions| Postiton | Required |
| --- | --- | --- | --- |
| networkId | header| The chain id to be queried (MagApe testnet: 141319, BNB Testnet: 97) | Yes |
| requestId | header| Unique traceId, cannot be repeated | Yes |
| X-Secret-Key | header|Game merchants' access keys on the magape platform  | Yes |
| propPageReq | body| Query all NFTs of players on the magape platform, including a series of query criteria| Yes |

## propPageReq
|  | Descriptions|type | Required |
| --- |---|  --- | --- |
| address |string| Gamer's address| Yes |
| name  | string|NFT Name，Support fuzzy queries| No |
| level | array|NFT Level（Common、Uncommon、Rare、Epic、Legendary）| No |
| category |array|NFT Category（City、Jungle、Ocean、Sky） | No |
| attributes |map<string,obj>|NFT Attributes | No |
| tokenIdAsc |bool| order by tokenId asc| No |
| levelAsc |bool|order by level asc | No |
| categoryAsc|bool|order by category asc | No |
| pageNo  |int| Start Page（Default First Page）| No |
| pageSize |int | Page size（Default 20 data points per page）| No |


## 1.3、Return data
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

## 1.4、Sample code
```
curl --location 'https://testnet-api.magape.io/api/nft/nftList' \ 
--header 'networkId: 141319' \ 
--header 'requestId: 123123127' \ 
--header 'X-Secret-Key: xxx' \ 
--header 'Content-Type: application/json' \ 
--data '{
"address":xxxx
}' 
```

# 2、uploadOrUpdateProp

## 2.1、Features
Upload or update game props，After uploading, the administrator will review the price and quantity of the props to see if they are reasonable. Only after the review is approved can MAC redemption be carried out


## 2.2、Input parameters
|  | Descriptions| Postiton | Required |
| --- | --- | --- | --- |
| requestId | header |Unique traceId, cannot be repeated | Yes |
| data |body|The list of all game items to be exported has no limit on the number of items that can be imported. Please refer to the following text for the format of the data. |yes|

## 2.2、Data
|  | Descriptions| type | Required |
| --- | --- | --- | --- |
| id |string | The identification code for game items must be unique for each item to be identified. This ID will be used for all imports and exports.|yes|
| maxSell| int |How many items can the entire game sell at most |yes|
| maxBuy |int | How many items can I buy at most through Mac|yes|
| cost |double|  The number of tokens that this item may be worth. If it is a scope item, then each item is worth a token quantity. Appliances should be priced fairly in order to be accepted by our ecosystem.|yes|
| image |string |The graphics of the project to be exported. Game developers should host their own images to achieve renewability. Our recommended monitor size is 300 x 300. |yes|
| name |string|The name should be unique and clearly specified. If two names are found, the latter one takes effect. An example is hierarchical, where the higher the level, the more difficult it is to achieve, and the corresponding increase in export value should be made.|yes|
| description |string | A description of an item, which can describe its abilities or simply tell a story about the item.|NO|


## 2.3、Return data
```json
{
  "code": 200,
  "data":"succes",
  "message": "success"
}
```

## 2.4、Sample code
```
curl --location 'https://testnet-api.magape.io/api/game/uploadOrUpdateProp' \
--header 'requestId: 123123136458' \ 
--header 'X-Secret-Key: xxx' \ 
--header 'Content-Type: application/json' \ 
--data '{"maxSell": 10, "maxBuy": 10, "cost": 0.1, "id": "402003351", "image": "https://testnet-api.magape.io/ipfs/QmWJEQchSo7HNUzctzTtCPnefFwqzy2ZJAsZcBunvjY8SE", "name": "test"}'
```
