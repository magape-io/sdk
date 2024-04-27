# 1、getPopList

## 1.1、Features
Get all NFTs in the wallet

## 1.2、Input parameters
|  | Descriptions| Postiton | Required |
| --- | --- | --- | --- |
| networkId | header| The chain id to be queried (MagApe testnet: 141319, BNB Testnet: 97) | Yes |
| requestId | header| Unique traceId, cannot be repeated | Yes |
| X-Secret-Key | header|Game merchants' access keys on the magape platform  | Yes |
| propPageReq.address | body| Gamer's address| Yes |
| propPageReq.name  | body| NFT Name| No |
| propPageReq.level |body| NFT Level| No |
| propPageReq.category |body|NFT Category | No |
| propPageReq.attributes |body|NFT Attributes | No |
| propPageReq.tokenIdAsc |body| order by tokenId asc| No |
| propPageReq.levelAsc |body|order by level asc | No |
| propPageReq.categoryAsc| body|order by category asc | No |
| propPageReq.pageNo  | body| Start Page| No |
| propPageReq.pageSize  | body| Page size| No |


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
curl --location 'https://testnet-api.magape.io/api/nft/NFTList' \ 
--header 'networkId: 141319' \ 
--header 'requestId: 123123127' \ 
--header 'X-Secret-Key: xxx' \ 
--header 'Content-Type: application/json' \ 
--data '{}' 
```

# 2、uploadOrUpdateProp

## 2.1、Features
Upload or update game props


## 2.2、Input parameters
|  | Descriptions | Required |
| --- | --- | --- |
| requestId | Unique traceId, cannot be repeated | Yes |


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
