|         | url                           |
|---------|-------------------------------| 
| MainNet | https://api.magape.io         |
| TestNet | https://testnet-api.magape.io |

# 1、/api/v1/nft/nftList

## 1.1、Features

Get all NFTs in the wallet

## 1.2、Input parameters

|              | Descriptions | Postiton                                                                               | Required |
|--------------|--------------|----------------------------------------------------------------------------------------|----------|
| requestId    | header       | Unique traceId, cannot be repeated                                                     | Yes      |
| signature    | header       | Signature information                                                                  | Yes      |
| X-Access-Key | header       | Game merchants' access keys on the magape platform                                     | Yes      |
| propPageReq  | body         | Query all NFTs of players on the magape platform, including a series of query criteria | Yes      |

## propPageReq

|             | Descriptions    | type                                           | Required |
|-------------|-----------------|------------------------------------------------|----------|
| address     | string          | Gamer's address                                | Yes      |
| networkId   | int             | networkId(97 bsc testnet,56 mainnet)           | Yes      |
| name        | string          | NFT Name，Support fuzzy queries                 | No       |
| level       | array           | NFT Level（Common、Uncommon、Rare、Epic、Legendary） | No       |
| category    | array           | NFT Category（City、Jungle、Ocean、Sky）            | No       |
| attributes  | map<string,obj> | NFT Attributes                                 | No       |
| tokenIdAsc  | bool            | order by tokenId asc                           | No       |
| levelAsc    | bool            | order by level asc                             | No       |
| categoryAsc | bool            | order by category asc                          | No       |
| pageNo      | int             | Start Page（Default First Page）                 | No       |
| pageSize    | int             | Page size（Default 20 data points per page）     | No       |

## 1.3、Return data

```json
{
  "code": 200,
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
      "category": "City",
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
  "message": "success"
}
```

## 1.4、Sample code

```http
curl --location 'https://url/api/v1/nft/nftList' \
        --header 'signature: xxxx' \
        --header 'requestId: 123123127' \
        --header 'X-Access-Key: xxx' \
        --header 'Content-Type: application/json' \
        --data '{
            "address":xxxx,
            "level":["Common"],
            "category":["City"]
        }' 
```

# 2、/api/v1/game/uploadOrUpdateProp

## 2.1、Features

Upload or update game props，After uploading, the administrator will review the price and quantity of the props to see if
they are reasonable. Only after the review is approved can MAC redemption be carried out

## 2.2、Input parameters

|              | Descriptions | Postiton                                                                                                                                                           | Required |
|--------------|--------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| requestId    | header       | Unique traceId, cannot be repeated                                                                                                                                 | Yes      |
| signature    | header       | Signature information                                                                                                                                              | Yes      |
| X-Access-Key | header       | Game merchants' access keys on the magape platform                                                                                                                 | Yes      |
| data         | body         | The list of all game items to be exported has no limit on the number of items that can be imported. Please refer to the following text for the format of the data. | Yes      |

## 2.2、Data

|             | Descriptions | type                                                                                                                                                                                                                                                              | Required |
|-------------|--------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| id          | string       | The identification code for game items must be unique for each item to be identified. This ID will be used for all imports and exports.                                                                                                                           | yes      |
| maxSell     | int          | How many items can the entire game sell at most                                                                                                                                                                                                                   | yes      |
| maxBuy      | int          | How many items can I buy at most through Mac                                                                                                                                                                                                                      | yes      |
| cost        | double       | The number of tokens that this item may be worth. If it is a scope item, then each item is worth a token quantity. Appliances should be priced fairly in order to be accepted by our ecosystem.                                                                   | yes      |
| type        | int          | 1:Items that can be chained、2: arean items                                                                                                                                                                                                                        | yes      |
| image       | string       | The graphics of the project to be exported. Game developers should host their own images to achieve renewability. Our recommended monitor size is 300 x 300.                                                                                                      | yes      |
| name        | string       | The name should be unique and clearly specified. If two names are found, the latter one takes effect. An example is hierarchical, where the higher the level, the more difficult it is to achieve, and the corresponding increase in export value should be made. | yes      |
| description | string       | A description of an item, which can describe its abilities or simply tell a story about the item.                                                                                                                                                                 | NO       |

## 2.3、Return data

```json
{
  "code": 200,
  "data": true,
  "message": "success"
}
```

## 2.4、Sample code

```http
curl --location 'https://url/api/v1/game/uploadOrUpdateProp' \
        --header 'signature: xxxx' \
        --header 'requestId: 123123127' \
        --header 'X-Access-Key: xxx' \
        --header 'Content-Type: application/json' \
        --data '[
            {
                "maxSell":1000,
                "maxBuy":1000,
                "cost":0.1,
                "id":"6",
                "type":1,
                "image":"https://media.istockphoto.com/id/182462356/photo/speedometer-and-tachometer.jpg?s=1024x1024&w=is&k=20&c=Kb9uDKKfcP1Wklnx08G_TIp5xum0rcPDK7GlnqBRdD0=",
                "name":"gogoCar"
            }
        ]'
```

# 3、/api/v1/nft/overallPerformance

## 3.1、Features

obtain the current user's NFT has an impact on the corresponding game

## 3.2、Input parameters

|                          | Descriptions | Postiton                                           | Required |
|--------------------------|--------------|----------------------------------------------------|----------|
| requestId                | header       | Unique traceId, cannot be repeated                 | Yes      |
| signature                | header       | Signature information                              | Yes      |
| X-Access-Key             | header       | Game merchants' access keys on the magape platform | Yes      |
| NFTOverallPerformanceReq | body         | request   param                                    | Yes      |

### NFTOverallPerformanceReq

|           | Descriptions | Postiton                             | Required |
|-----------|--------------|--------------------------------------|----------|
| networkId | int          | networkId(97 bsc testnet,56 mainnet) | yes      |
| address   | string       | Gamer's address                      | yes      |

### 3.2.4、Return data

```json
{
  "code": 200,
  "data": {
    "enhancedAttrs": [
      {
        "enhancedPercentage": "80",
        // nft enhanced percentage 80%
        "enhancedVal": 16.84,
        // the value that calculated according to configuretion in magape platform
        "id": 6,
        "level": "Common",
        // nft level
        "name": "A6",
        // game define attribute
        "nfName": "Mappy #106"
        // the NFT that provide this capability
      }
    ]
  },
  "message": "success"
}
```

### 3.2.5、Sample code

```http request
curl --location 'https://url/api/v1/nft/overallPerformance' \
        --header 'signature: xxxx' \
        --header 'requestId: 123123127' \
        --header 'X-Access-Key: xxx' \
        --header 'Content-Type: application/json' \
        --data '
            {
                "address":"xxxx",
                "networkId":56
            }
'
```