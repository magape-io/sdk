# 1、SDK import (latest)

```xml

<dependency>
    <groupId>io.github.magape-official</groupId>
    <artifactId>magape-java-sdk</artifactId>
    <version>1.0.3</version>
</dependency>
```

# 2、Configuration

```java

@Configuration
public class MagApeConfiguration {

    // from magape platfrom game administrator page
    @Value("${magape.publicKey}")
    String publicKey;

    // from magape platfrom game administrator page
    @Value("${magape.privateKey}")
    String privateKey;

    // mode：true mainnet，false testnet
    @Value("${magape.live}")
    boolean live;

    @PostConstruct
    public void init() {
        MagApe.live = live;
        MagApe.publicKey = publicKey;
        MagApe.privateKey = privateKey;
    }
}
```

# 3、SDK functions (JAVA)

## 3.1、nft

### 3.1.1、Features

Get all NFTs in the wallet

### 3.1.2、Input parameters

|          | type  |  Descriptions                                                                                               | Required |
|------------|------------|--------------------------------------------------------------------------------------------------------|----------|
| requestId  |string |  Unique traceId, cannot be repeated                                                                     | Yes      |
| NFTListReq |object |  Request body, with multiple built-in request parameters, refer to the explanation of propPageReq below | Yes      |

### NFTListReq

|             | Descriptions                                   | Required |
|-------------|------------------------------------------------|----------|
| address     | Gamer's address                                | Yes      |
| networkId   | networkId(97 bsc testnet,56 mainnet)           | Yes      |
| name        | NFT Name，Support fuzzy queries                 | No       |
| level       | NFT Level（Common、Uncommon、Rare、Epic、Legendary） | No       |
| category    | NFT Category（City、Jungle、Ocean、Sky）            | No       |
| attributes  | NFT Attributes                                 | No       |
| tokenIdAsc  | order by tokenId asc                           | No       |
| levelAsc    | order by level asc                             | No       |
| categoryAsc | order by category asc                          | No       |
| pageNo      | Start Page（Default First Page）                 | No       |
| pageSize    | Page size（Default 20 data points per page）     | No       |

### 3.1.3、Return data

```json
[
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
]
```

### 3.1.4、Sample code

```java

@PostMapping("nftNotify")
public Response nftNotify(HttpServletRequest request) throws IOException {
    String payload = payload(request);
    String sigHeader = request.getHeader("signature");

    boolean verify = DigitalSignECDSA.signVerify(payload, sigHeader, publicKey);
    log.info("Verify Result:{}", verify);

    try {
        PropPageReq propPageReq = PropPageReq.builder().address("").networkId(97).level(List.of("Common"));
        String requestID = UUID.randomUUID().toString();
        try {
            List<NFT> nfts = Wallet.nft(requestID, propPageReq);
            Map<String, List<NFT>> map = nfts.stream().collect(Collectors.groupingBy(NFT::getLevel));
            nft.putAll(map);
            return com.example.starttest.Response.success("succcess");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } catch (Exception e) {
        log.error("", e);
        return Response.success("false");
    }
}


private static String payload(HttpServletRequest request) throws IOException {
    InputStream inputStream = request.getInputStream();
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int n;
    while (-1 != (n = inputStream.read(buffer))) {
        output.write(buffer, 0, n);
    }
    String payload = output.toString(StandardCharsets.UTF_8);
    return payload;
}
```

## 3.2、uploadOrUpdateProp

### 3.2.1、Features

Upload or update game props

### 3.2.2、Input parameters

|            | type   | Descriptions                                                                                                                                                       | Required |
|-----------|--------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| requestId    | string | Unique traceId, cannot be repeated                                                                                                                                 | Yes      |
| gameProps    | array  | The list of all game items to be exported has no limit on the number of items that can be imported. Please refer to the following text for the format of the data. | yes      |

## 3.2.3、gameProp

|             | type   | Descriptions                                                                                                                                                                                                                                                      | Required |
|-------------|--------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| id          | string | The identification code for game items must be unique for each item to be identified. This ID will be used for all imports and exports.                                                                                                                           | yes      |
| maxSell     | int    | How many items can the entire game sell at most                                                                                                                                                                                                                   | yes      |
| maxBuy      | int    | How many items can I buy at most through Mac                                                                                                                                                                                                                      | yes      |
| cost        | double | The number of tokens that this item may be worth. If it is a scope item, then each item is worth a token quantity. Appliances should be priced fairly in order to be accepted by our ecosystem.                                                                   | yes      |
| type        | int    | 1:Items that can be chained、2: arean items                                                                                                                                                                                                                        | yes      |
| image       | string | The graphics of the project to be exported. Game developers should host their own images to achieve renewability. Our recommended monitor size is 300 x 300.                                                                                                      | yes      |
| name        | string | The name should be unique and clearly specified. If two names are found, the latter one takes effect. An example is hierarchical, where the higher the level, the more difficult it is to achieve, and the corresponding increase in export value should be made. | yes      |
| description | string | A description of an item, which can describe its abilities or simply tell a story about the item.                                                                                                                                                                 | NO       |

### 3.2.4、Return data

```
true | false
```

### 3.2.5、Sample code

```java

@Test
public void test() throws Exception {
    MagApe.live = false;
    MagApe.publicKey = "apply from magape";
    MagApe.privateKey = "apply from magape";
    boolean result = Game.uploadOrUpdateProp(String.valueOf(System.currentTimeMillis()), List.of(GameProp.builder().id("9").name("hehe").cost(10d).type(1).maxBuy(100).maxSell(10).image("http://hehe")));
    System.out.println(result);
}
```

## 3.3、performance

### 3.3.1、Features

obtain the current user's NFT has an impact on the corresponding game

### 3.2.2、Input parameters

|                        | type   | Descriptions                       | Required |
|--------------------------| --------|------------------------------------|----------|
| requestId                |  string | Unique traceId, cannot be repeated | Yes      |
| NFTOverallPerformanceReq |  object | query condition                    | yes      |

## 3.2.3、NFTOverallPerformanceReq

|           | type   | Descriptions                         | Required |
|-----------|--------|--------------------------------------|----------|
| networkId | int    | networkId(97 bsc testnet,56 mainnet) | yes      |
| address   | string | Gamer's address                      | yes      |

### 3.2.4、Return data

```json
{
  "enhancedAttrs": [
    {
      "enhancedPercentage": "80", // nft enhanced percentage 80%
      "enhancedVal": 16.84, // the value that calculated according to configuretion in magape platform
      "id": 6,
      "level": "Common", // nft level
      "name": "A6", // game define attribute
      "nfName": "Mappy #106" // the NFT that provide this capability
    }
  ]
}
```

### 3.2.5、Sample code

```java

@Test
public void test() throws Exception {
    MagApe.live = false;
    MagApe.publicKey = "apply from magape";
    MagApe.privateKey = "apply from magape";
    NFTOverallPerformance performance = Wallet.performance(String.valueOf(System.currentTimeMillis()), NFTOverallPerformanceReq.build().address("0x4D11dF920E0E48c7E132e5a9754C7e754Cd6EBFB").networkId(97));
    System.out.println(performance);
}
```

]()
