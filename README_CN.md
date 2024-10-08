# 1、 游戏接入

![image.png](https://cdn.nlark.com/yuque/0/2024/png/901377/1716266050349-6290d18d-4403-4491-9529-34c563d92a26.png#averageHue=%23f2f2f2&clientId=u8d8f315f-fec8-4&from=paste&height=238&id=u5a183cb5&originHeight=476&originWidth=1316&originalType=binary&ratio=2&rotation=0&showTitle=false&size=98990&status=done&style=none&taskId=u6ddfb854-519e-429e-a44b-bc0d5eb41b7&title=&width=658)

# 2、使用SDK和magape平台进行交互

[[https://github.com/magape-official/sdk/blob/main/java/JAVA_SDK_EN.md](https://github.com/magape-official/sdk/blob/main/JAVA_SDK_EN.md)]

# 3、游戏道具与ERC20代币转换

## 3.1、erc20代币 -> 游戏道具（导入）

```mermaid
sequenceDiagram
    participant 游戏厂商
    participant magape
    participant 玩家
    		游戏厂商 ->> magape: 1、通过sdk上传支持导出的道具列表(接口1)
		magape -> magape: 2、审核
		玩家 ->> magape: 3、执行导入
		magape ->> 游戏厂商: 4、查询可以导出的道具列表以及玩家个人导入限制，可选(接口2)
		magape ->> 游戏厂商: 5、检查玩家是否还有余额可以导入(case:特殊道具每人只能购买一次)(接口3)
		游戏厂商 ->> magape: 5、返回结果，库存足够，可以执行导入
		magape ->> blockChain: 6、调用合约
		blockChain ->> magape: 7、监听合约事件
		magape ->> 游戏厂商: 8、调用游戏厂商接口给玩家新增道具(接口4)
```

接口1: - [上传可导出道具元数据http（uploadOrUpdateProp）](HTTP_EN.md) ｜ - [上传可导出道具元数据SDK（uploadOrUpdateProp）](JAVA_SDK_EN.md)

接口2: - [查询可以导出的道具列表及个人限制](#3311查询玩家可以导入导出的配置可选)

接口3: - [检查游戏对应道具库存](#3314检查是否还有库存支持导入)

接口4: - [在游戏中新增玩家道具](#3315新增玩家道具)

## 3.2、游戏道具 -> erc20代币（导出）

```mermaid
sequenceDiagram
    participant 游戏厂商
    participant magape
    participant 玩家
    		游戏厂商 ->> magape: 1、通过sdk上传支持导出的道具列表(接口1)
		magape -> magape: 2、审核
		玩家 ->> magape: 3、在magape市场可以看到自己在不同游戏支持导入导出的道具以及限制(接口2)
		玩家 ->> magape: 4、执行导出
		magape ->> 游戏厂商: 5、调用游戏厂商接口删除玩家道具(接口3)
		magape ->> blockChain: 6、调用合约
		blockChain ->> magape: 7、监听合约事件
```

接口1: - [上传可导出道具元数据http（uploadOrUpdateProp）](HTTP_EN.md) ｜ - [上传可导出道具元数据SDK（uploadOrUpdateProp）](JAVA_SDK_EN.md)

接口2: - [查询玩家可导出数量](#3313查询玩家游戏道具库存)

接口3: - [删除玩家道具](#3312删除玩家道具)

## 3.3、需要提供的接口

### 3.3.1、游戏方

#### 3.3.1.1、查询玩家可以导入导出的配置(可选)

如果没有提供，则默认在magape页面不展示导入导出限制数量

```http
# 请求
POST https://game.com/assetConfig
--header 'Content-Type: application/json' \
--header 'signature:xxxx' 
--data '{
"reqId":'xxxx',
"operate":'buy|sell'
}'

# 返回
{
"code":200,
"data":[ 
    {
      "propId":"游戏道具id",
      "quantity":10
    }
  ],
  "err":"" 
}
```

**request**

|              | 类型        | 位置     | 描述                                    | 是否必填 |
|--------------|-----------|--------|---------------------------------------|------|
| header       | signature | header | 请求签名，游戏平台使用私钥解签                       | 是    |
| Content-Type | string    | header | 请求类型application/json                  | 是    |
| reqId        | string    | body   | 本次请求的唯一id                             | 是    |
| operate      | string    | body   | 操作描述（buy or sell），buy表示查询玩家在平台可以购买的游戏道具库存数量，查询纬度是游戏厂商(例如有的道具是每个玩家只能购买一次)，sell表示查询玩家可以卖出的游戏道具库存数量，查询纬度是玩家 | 是    |

**response**

|               | 类型     | 描述                                | 是否必填   |
|---------------|--------|-----------------------------------|--------|
| code          | int    | 相应码,200 成功，401 未授权，500 错误         | 是      |
| err           | string | 错误信息，有则不用填                        |
| data          | array  | 玩家可导出的道具合集                        | 是      |
| data[0].propId    | string | 游戏道具id                            | 是      |
| data[0].quantity | int    | 玩家最多可以买入或卖出多少个道具(-1表示道具可以无限购买/卖出) | 是    |

#### 3.3.1.2、删除玩家道具

```http
# 请求
POST https://game.com/deleteAsset
--header 'signature:xxxx'
--header 'Content-Type: application/json' \
--data '{
    "reqId":"xxx",
    "address": xxxx,
    "assets":[
      {
      "propId": "游戏道具id", 
      "quantity": 10
      }
    ]
}'

# 返回
{
"code":200｜400｜401｜500 // 返回状态码
"data":"success" | "fail",
"err":"" 
}
```

**request**

|                   | 类型     | 位置     | 描述                   | 是否必填 |
|-------------------|--------|--------|----------------------|------|
| signature         | string | header | 请求签名，游戏平台使用私钥解签      | 是    |
| Content-Type      | string | header | 请求类型application/json | 是    |
| reqId             | string | body   | 本次请求的唯一id            | 是    |
| address           | string | body   | 玩家地址                 | 是    |
| assets            | array  | body   | 受影响的资产               | 是    |
| assets[].propId   | string | body   | 游戏道具id               | 是    |
| assets[].quantity | int    | body   | 要删除的数量               | 是    |

**response**

|      | 类型     | 描述                        | 是否必填 |
|------|--------|---------------------------|------|
| code | int    | 相应码,200 成功，401 未授权，500 错误 | 是    |
| err  | string | 错误信息，有则不用填                | 否    |
| data | string | "success" &#124; "fail    | 是    |

#### 3.3.1.3、查询玩家游戏道具库存

```http
# 请求
POST https://game.com/queryStorage
--header 'signature:xxxx' 
--header 'Content-Type: application/json' \
--data '{
    "reqId":"xxx",
    "address": xxxx,
    "operate": "buy | sell",
    "queryIds": [1, 2, 3]
}'
# 返回
{
"code":200,
"data":[
    {
        "propId": 1,
        "quantity": 100
    },
    {
        "propId": 2,
        "quantity": 200
    }
],
"msg":""
}
```

**request**

|              | 类型       | 位置     | 描述                                                                                                       | 是否必填 |
|--------------|----------|--------|----------------------------------------------------------------------------------------------------------|------|
| signature    | string   | header | 请求签名，游戏平台使用私钥解签                                                                                          | 是    |
| Content-Type | string   | header | 请求类型application/json                                                                                     | 是    |
| reqId        | string   | body   | 本次请求的唯一id                                                                                                | 是    |
| address      | string   | body   | 玩家地址                                                                                                     | 是    |
| operate      | string   | body   | 操作描述（buy or sell），buy表示查询玩家在平台可以购买的游戏道具库存数量，查询纬度是游戏厂商(例如有的道具是每个玩家只能购买一次)，sell表示查询玩家可以卖出的游戏道具库存数量，查询纬度是玩家 | 是    |
| queryIds     | string[] | body   | 游戏道具id                                                                                                   | 否    | 要查询的道具ID数组,如果不传则查询所有道具

**response**

|              | 类型       | 描述                                       | 是否必填 |
|--------------|----------|------------------------------------------|------|
| code         | int      | 相应码,200 成功，401 未授权，500 错误                | 是    |
| err          | string   | 错误信息，有则不用填                               | 否    |
| data         | object[] | 返回对象                                     | 是    |
| data[].propId    | string   | 道具id                                     | 是    |
| data[].quantity | string   | 剩余库存数量(operate为buy)或玩家拥有数量(operate为sell) | 是    |

#### 3.3.1.4、检查是否还有库存支持导入

```http
# 请求
POST https://game.com/checkEnough
--header 'signature:xxxx' 
--header 'Content-Type: application/json' \
--data '{
    "reqId":"xxx",
    "address": xxxx,
    "operate": "buy | sell",
    "assets":[
      {
      "propId": "游戏道具id", 
      "quantity": 10
      }
    ]
}'

# 返回
{
"code":200,
"data":{
    "enough":true ｜ false
 },
"err":""
}
```

**request**

|                   | 类型     | 位置     | 描述                                                    | 是否必填 |
|-------------------|--------|--------|-------------------------------------------------------|------|
| signature         | string | header | 请求签名，游戏平台使用私钥解签                                       | 是    |
| Content-Type      | string | header | 请求类型application/json                                  | 是    |
| reqId             | string | body   | 本次请求的唯一id                                             | 是    |
| address           | string | body   | 玩家地址                                                  | 是    |
| operate           | string | body   | 操作描述（buy or sell），buy表示查询玩家在平台可以购买的游戏道具库存数量，查询纬度是游戏厂商(例如有的道具是每个玩家只能购买一次)，sell表示查询玩家可以卖出的游戏道具库存数量，查询纬度是玩家 | 是    |
| assets[].propId   | string | body   | 游戏道具id                                                | 是    |
| assets[].quantity | int    | body   | 要购买的数量                                                | 是    |

**response**

|             | 类型     | 描述                        | 是否必填 |
|-------------|--------|---------------------------|------|
| code        | int    | 相应码,200 成功，401 未授权，500 错误 | 是    |
| err         | string | 错误信息，有则不用填                | 否    |
| data        | object | 返回对象                      | 是    |
| data.enough | bool   | 库存是否足够去买或者卖               | 是    | 

#### 3.3.1.5、新增玩家道具

```http
# 请求
POST https://game.com/increaseAsset
--header 'signature:xxxx'
--header 'Content-Type: application/json' \
--data '{
    "reqId":"xxx", 
    "address": xxxx,
    "assets":[
      {
      "propId": "游戏道具id", 
      "quantity": 10
      }
    ]
}'

# 返回
{
"code":200,
"data":"success" ,
"err":""
}
```

**request**

|                   | 类型     | 位置     | 描述                   | 是否必填 |
|-------------------|--------|--------|----------------------|------|
| signature         | string | header | 请求签名，游戏平台使用私钥解签      | 是    |
| Content-Type      | string | header | 请求类型application/json | 是    |
| reqId             | string | body   | 本次请求的唯一id            | 是    |
| address           | string | body   | 玩家地址                 | 是    |
| assets            | array  | body   | 受影响的资产               | 是    |
| assets[].propId   | string | body   | 游戏道具id               | 是    |
| assets[].quantity | int    | body   | 要删除的数量               | 是    |

**response**

|      | 类型     | 描述                        | 是否必填 |
|------|--------|---------------------------|------|
| code | int    | 相应码,200 成功，401 未授权，500 错误 | 是    |
| err  | string | 错误信息，有则不用填                | 否    |
| data | string | "success" &#124; "fail    | 是    |


#### 3.3.1.6、购买游戏内商品(实体纪念品，如果游戏没有周边商品需要贩卖可以不用提供这个接口，这个接口用于用magape平台beans购买游戏周边之后通知游戏厂商)
```http
# 请求
POST https://game.com/callback/buyMerchandise
--header 'signature:xxxx'
--header 'Content-Type: application/json' \
--data '{
  "reqId": "1720160554482100200",
  "address": "0xA34357486224151dDfDB291E13194995c22Df505",
  "items": [
    {
      "username": "magape",
      "id": 43,
      "telephone": "+61 88888888",
      "remark": "Hello!",
      "quantity": 3,
      "deliveryAddress": "moon"
    }
  ]
}'

# 返回
{
  "code": 200,
  "err": "",
  "data": "success"
}
```

**request**
|                   | 类型     | 位置     | 描述                                         | 是否必填 |
|-------------------|--------|--------|---------------------------------------------|------|
| reqId             | int    | body   | 请求的唯一ID                                    | 是    |
| address           | string | body   | 用户钱包地址                                     | 是    |
| items             | array  | body   | 购买的商品列表                                    | 是    |
| items[].id        | int    | body   | 商品ID                                       | 是    |
| items[].quantity  | int    | body   | 购买数量                                       | 是    |
| items[].username  | string    | body   | 收件人名称                                       | 是    |
| items[].telephone  | string    | body   | 收件人号码                                       | 是    |
| items[].deliveryAddress  | string    | body   | 收件地址                                       | 是    |
| items[].remark  | string    | body   | 备注                                       | 否    |

**response**
|      | 类型     | 描述                           | 是否必填 |
|------|--------|------------------------------|------|
| code | int    | 响应码,200 成功,401 未授权,500 错误    | 是    |
| err  | string | 错误信息,若无错误则为空字符串              | 是    |
| data | string | 操作结果,"success"表示成功| 是    |




####          

### 3.3.2、magape平台

#### 3.3.2.1、上传游戏可导出道具元数据

#### [https://github.com/magape-official/sdk/blob/main/java/HTTP_EN.md](https://github.com/magape-official/sdk/blob/main/HTTP_EN.md)（参考uploadOrUpdateProp方法）

# 4、使用erc20代币进行玩家之间游戏道具买卖

## 4.1、玩家A上架道具 -> 玩家B购买流程

```mermaid
sequenceDiagram
    participant 游戏厂商
    participant magape
    participant 玩家A
    participant 玩家B
    	游戏厂商 ->> magape: 1、通过sdk上传支持导出的道具列表(接口1)
		magape -> magape: 2、审核
		玩家A ->> magape: 3、上架要卖的道具，货币为mac
		magape ->> 游戏厂商: 4、调用game厂商接口删除游戏道具(接口2)
		magape -> magape: 5、生成一条道具上架记录
		玩家B ->> magape: 6、购买玩家A的道具
		magape ->> blockChain: 7、调用合约扣除玩家B mac数量
		blockChain ->> magape: 8、监听合约事件
		magape ->> 游戏厂商: 9、调用游戏厂商接口给玩家新增道具(接口3)
```

## 4.2、玩家A上架道具 -> 下架道具

```mermaid
sequenceDiagram
    participant 游戏厂商
    participant magape
    participant 玩家A
    	游戏厂商 ->> magape: 1、通过sdk上传支持导出的道具列表(接口1)
		magape -> magape: 2、审核
		玩家A ->> magape: 3、上架要卖的道具，货币为mac
		magape ->> 游戏厂商: 4、调用game厂商接口删除游戏道具(接口2)
		magape -> magape: 5、生成一条道具上架记录
		玩家A ->> magape: 6、下架要卖的道具
		magape ->> 游戏厂商: 7、调用游戏厂商接口给玩家恢复道具(接口3)
```

接口1: - [上传可导出道具元数据http（uploadOrUpdateProp）](HTTP_EN.md) ｜ - [上传可导出道具元数据SDK（uploadOrUpdateProp）](JAVA_SDK_EN.md)

接口2: - [在游戏中删除玩家道具](#3312删除玩家道具)

接口3: - [在游戏中新增玩家道具](#3315新增玩家道具)

# 5、NFT变动

```mermaid
sequenceDiagram
    participant 游戏厂商
    participant magape
    participant 玩家
    		玩家 ->> magape: mint、transfer、merge、dust、upgrade、buy
		magape -->> magape: 查询这个用户在那些游戏上面有账号
		magape ->> 游戏厂商: 通过回调地址通知游戏玩家nft资产发生变化(接口1)
		游戏厂商 ->> magape: 使用http或者sdk拉取玩家最新的nft详情(接口2)
```

接口1: - [提醒游戏用户nft发生变化](#4111接受magape-nft变更的回调函数用户在nft变更minttransfer会通知游戏方游戏方调用magape接口拉取最新的nft列表)

接口2: - [获取用户所有nft](java/HTTP_EN.md#1getpoplist)

## 5.1、需要提供的接口

### 5.1.1、游戏方

#### 5.1.1.1、接受magape nft变更的回调函数。用户在nft变更(mint、transfer...)会通知游戏方。游戏方调用magape接口拉取最新的nft列表

```http
# 请求
POST https://game.com/nftNotify
--header 'Content-Type: application/json' \
--header 'signature:xxxx' 
--data '{
"reqId":'xxxx',
"address':'xxxx'
}'
# 返回
{
"code":200,
"data":"success",
"err":""
}
```

**request**

|              | 类型     | 位置     | 描述              | 是否必填 |
|--------------|--------|--------|-----------------|------|
| signature    | string | header | 请求签名，游戏平台使用私钥解签 | 是    |
| Content-Type | string | header | 请求类型            | 是    |
| reqId        | string | body   | 本次请求唯一id        | 是    |
| address      | string | body   | 玩家地址            | 是    |

**response**

|      | 类型     | 描述                        | 是否必填 |
|------|--------|---------------------------|------|
| code | int    | 相应码,200 成功，401 未授权，500 错误 | 是    |
| err  | string | 错误信息，有则不用填                | 否    |
| data | string | "success" &#124; "fail    | 是    |

####          

### 5.1.2、magape

#### 5.1.2.1、根据玩家地址查询所有nft列表，sdk中getPopList函数，返回的NFT属性为A(1-10)、B(1-10)、C(1-10)、D(1-10),属性在5、NFT在游戏中的影响中有详细描述

```json
{
  "code": 200,
  "data": {
    "data": [
      {
        "attrList": [
          {
            "displayType": "boost_percentage",
            // 不需要处理，属于nft market市场展示
            "traitType": "D2",
            // 属性名
            "value": 60
            // 属性值，百分比
          },
          {
            "displayType": "boost_percentage",
            "traitType": "D1",
            "value": 20
          },
          {
            "displayType": "boost_percentage",
            "traitType": "D9",
            "value": 30
          },
          {
            "displayType": "boost_percentage",
            "traitType": "D5",
            "value": 40
          },
          {
            "traitType": "Rank",
            "value": "Epic"
          },
          {
            "traitType": "Ape Type",
            "value": "Sky"
          }
        ],
        "attrs": "[{\"display_type\":\"boost_percentage\",\"trait_type\":\"D2\",\"value\":60},{\"display_type\":\"boost_percentage\",\"trait_type\":\"D1\",\"value\":20},{\"display_type\":\"boost_percentage\",\"trait_type\":\"D9\",\"value\":30},{\"display_type\":\"boost_percentage\",\"trait_type\":\"D5\",\"value\":40},{\"trait_type\":\"Rank\",\"value\":\"Epic\"},{\"trait_type\":\"Ape Type\",\"value\":\"Sky\"}]",
        "category": "Sky",
        "createTime": 1714109254000,
        "erc721ContractAddress": "0x3FB236F17054c24DB20FDf6135Ce334DE7451928",
        "fromAddress": "0x0000000000000000000000000000000000000000",
        "imageUrl": "https://testnet-api.magape.io/ipfs/QmeSRVY7786EZTEQivowaPakjjSPg3BRyMdWk8dHWroDFL",
        "imgCid": "ipfs://QmeSRVY7786EZTEQivowaPakjjSPg3BRyMdWk8dHWroDFL",
        "level": "Epic",
        "name": "Mappy #306",
        "nftCidUrl": "https://testnet-api.magape.io/ipns/null",
        "status": 2,
        "toAddress": "0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb",
        "tokenId": 306
      }
    ],
    "hasNext": true,
    "pageNo": 1,
    "pageSize": 20,
    "totalPage": 2,
    "totalRecord": 38
  },
  "message": "success"
}

```

## 5.2、安全问题

5.2.1、游戏厂商在生成sk的时候，magape平台会同步生成一个公钥，在magape给游戏厂商发送数据时会对数据进行加密，游戏方拿到数据之后可以通过sk进行解密。避免接口暴露导致恶意攻击

```java
InputStream inputStream = request.getInputStream();
ByteArrayOutputStream output = new ByteArrayOutputStream();
byte[] buffer = new byte[1024];
int n;
while(-1!=(n =inputStream.

read(buffer))){
        output.

write(buffer, 0,n);
}
String payload = output.toString(StandardCharsets.UTF_8);
String sigHeader = request.getHeader("signature");
String accessKeyStr = "magape平台公钥";

byte[] accessKeyBytes = Base64.decodeBase64(accessKeyStr);
X509EncodedKeySpec keySpec = new X509EncodedKeySpec(accessKeyBytes);
KeyFactory keyFactory = KeyFactory.getInstance("Ed25519");
PublicKey accessKey = keyFactory.generatePublic(keySpec);

Signature signDecode = Signature.getInstance("Ed25519");
signDecode.

initVerify(accessKey);
signDecode.

update(source.getBytes(StandardCharsets.UTF_8));
boolean verifyResult = signDecode.verify(Base64.decodeBase64(rsaData));

System.out.

println("Verify Result: "+verifyResult);
```

# 6、NFT在游戏中的影响

在magape页面设置游戏支持的nft类型，目前分为4类"City", "Jungle", “Ocean", "Sky",每一种类型下面有不同的属性。
游戏厂商可以选择任意一种类型的nft进行游戏人物增强，每一个nft的属性只会属于其中一种类型，比如NFT不会同时拥有City下面的属性和Jungle下面的属性
![image.png](https://cdn.nlark.com/yuque/0/2024/png/901377/1716774123682-0695f2f2-bfdb-4962-8bc6-97bafeb3af66.png#averageHue=%23eeeff0&clientId=uca42cdc8-16fd-4&from=paste&height=472&id=u0db8d80c&originHeight=944&originWidth=1454&originalType=binary&ratio=2&rotation=0&showTitle=false&size=198141&status=done&style=none&taskId=uad80840f-faa8-4103-8927-32f94b9a789&title=&width=727)
游戏方可以自行设定游戏功能使用哪一种属性（例如：攻击力 -> City的A1），并设置在游戏中影响的最大值和最小值

nft等级决定了具体影响数值。目前magape的nft有5个等级（Common、Uncommon、Rare、Epic、Legendary）
![image.png](https://cdn.nlark.com/yuque/0/2024/png/901377/1716269294463-0137c412-1537-426f-904b-c2bb37c0fbca.png#averageHue=%23f3f4f5&clientId=uc39d0872-6693-4&from=paste&height=532&id=u48a3162b&originHeight=1064&originWidth=1500&originalType=binary&ratio=2&rotation=0&showTitle=false&size=160330&status=done&style=none&taskId=u30b8823f-5261-4e7c-b89e-d850c6dac99&title=&width=750)
magape平台可以提供接口计算这些NFT最终加成，游戏平台也可以获取所有的NFT自行计算这些属性加成

## 6.1、需要提供的接口

### 6.1.1、游戏方

6.1.1.1、获取游戏支持的属性，方便玩家可以准确的知道自己拥有的nft能对游戏的加成

```http
# 请求
POST https://game.com/attrDefinition
--header 'Content-Type: application/json' \
--header 'signature:xxxx' \
--data '{
"reqId":"xxxxx"
}'

# 返回
{
  "code":200
  "data": {
    "category": "City",
    "attrs": [
      {
        "attr":"A1",
        "min":10,
        "max":100,
        "description":"Power Enhancement"
      }
    ]
  },
  "err":"" 
}
```

**request**

|              | 类型     | 位置     | 描述              | 是否必填 |
|--------------|--------|--------|-----------------|------|
| signature    | string | header | 请求签名，游戏平台使用私钥解签 | 是    |
| Content-Type | string | header | 请求类型            |
|
| reqId        | string | body   | 本次请求唯一id        |
|

**response**

|                      | 类型     | 描述                                   | 是否必填 |
|----------------------|--------|--------------------------------------|------|
| code                 | int    | 相应码,200 成功，401 未授权，500 错误            | 是    |
| err                  | string | 错误信息，有则不用填                           | 否    |
| category             | string | 游戏支持的nft类型（City, Jungle, Ocean, Sky） | 是    |
| attrs                | array  | 游戏支持的属性                              | 是    |
| attrs[0].attr        | string | 属性名称                                 | 是    |
| attrs[0].min         | int    | 属性最小值，百分比                            | 是    |
| attrs[0].max         | int    | 属性最大值，百分比                            | 是    |
| attrs[0].description | string | 属性描述                                 | 是    |

### 6.1.2、magape

6.1.1.2.1、游戏获取用户所有nft总的加成，算法参考上述表格

```http
# 请求
curl --location 'https://{url}/api/v1/nft/overallPerformance' \
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

# 返回
{
  "code": 200,
  "data": {
    "enhancedAttrs": [
      {
        "enhancedPercentage": "80",// nft enhanced percentage 80%
        "enhancedVal": 16.84,// the value that calculated according to configuretion in magape platform
        "id": 6,
        "level": "Common", // nft level   
        "name": "A6",// game define attribute
        "nfName": "Mappy #106" // the NFT that provide this capability
      }
    ]
  },
  "message": "success"
}
```

**request**

|                          | 位置     | 描述                 | 是否必填 |
|--------------------------|--------|--------------------|------|
| requestId                | header | 唯一的traceId，不能重复    | Yes  |
| signature                | header | 签名信息               | Yes  |
| X-Access-Key             | header | magape平台上游戏厂商的访问密钥 | Yes  |
| NFTOverallPerformanceReq | body   | 请求参数               | Yes  |

|           | 类型     | 描述                             | Required |
|-----------|--------|--------------------------------|----------|
| networkId | int    | 链id(97 bsc testnet,56 mainnet) | yes      |
| address   | string | 玩家地址                           | yes      |

**response**

|                                     | 类型     | 描述                        | 是否必填 |
|-------------------------------------|--------|---------------------------|------|
| code                                | int    | 相应码,200 成功，401 未授权，500 错误 | 是    |
| err                                 | string | 错误信息，有则不用填                | 否    |
| address                             | string | 玩家地址                      | 是    |
| enhancedAttrs                       | array  | 增益                        | 是    |
| enhancedAttrs[0].enhancedPercentage | string | 属性增幅比例                    | 是    |
| enhancedAttrs[0].enhancedVal        | int    | 属性加成值                     | 是    |
| enhancedAttrs[0].id                 | int    | 属性id                      | 是    |
| enhancedAttrs[0].level              | int    | nft 等级                    | 是    |
| enhancedAttrs[0].name               | int    | 游戏定义属性                    | 是    |
| enhancedAttrs[0].nfName             | int    | nft 名称                    | 是    |

# 7、登入&退出游戏

## 7.1、web游戏集成在magape平台，并通过magape game hall登入

玩家先登入magape平台进行签名，在进入游戏的时候带上magape平台生成的token信息。游戏厂商可以直接使用token操作sdk方法，也可以直接传入玩家地址，区别是token可以让玩家退出游戏，address不存在退出

```mermaid
sequenceDiagram
    participant A as 玩家
    participant B as magape平台
    participant C as 游戏厂商
    A ->> B: 登入magape平台
    Note left of A: 对象A的描述(提示)
    B -->> A: Fine, Thank you.
    Note right of B: 对象B的描述
    A -x B: 我走了


```


## 7.2、通过游戏自身的账户体系登入

游戏需要提供入口让玩家和钱包地址进行绑定，绑定之后在调用magape平台接口时会自动在magape平台中保存玩家和游戏的关联关系
![image.png](https://cdn.nlark.com/yuque/0/2024/png/901377/1716523939764-5bca1861-9721-49e3-a7cc-32e6425f78ba.png#averageHue=%23fafafa&clientId=u19a5e47a-eb1b-4&from=paste&height=542&id=u96838970&originHeight=1084&originWidth=1330&originalType=binary&ratio=2&rotation=0&showTitle=false&size=83358&status=done&style=none&taskId=ue54c796d-353f-4998-877e-27f4924a38d&title=&width=665)

# 8、悬浮图标功能

## 8.1、登入注册

### 8.1.1、登入

通过本地钱包执行登入验证签名，或者输入设置好的密码执行登入，调用签名访问进行认证授权。返回认证结果和用户钱包地址

### 8.1.2、注册

通过开源钱包或者magape随机生成一个钱包公私钥，并让用户设定好密码。返回认证结果和用户钱包地址

## 8.2、退出
```http
# 请求
curl --location 'https://{url}/api/v1/game/exit' \
        --header 'signature: xxxx' \
        --header 'requestId: 123123127' \
        --header 'X-Access-Key: xxx' \
        --header 'Content-Type: application/json' \
        --data '
            {
                "token":"xxxx"
            }
'

# 返回
{
  "code": 200,
  "data": {"succcess"},
  "message": "success"
}
```

**request**

|              | 位置     | 描述                 | 是否必填 |
|--------------|--------|--------------------|------|
| requestId    | header | 唯一的traceId，不能重复    | Yes  |
| signature    | header | 签名信息               | Yes  |
| X-Access-Key | header | magape平台上游戏厂商的访问密钥 | Yes  |
| GameExitReq  | body   | 请求参数               | Yes  |

|         | 类型     | 描述         | Required |
|---------|--------|------------|----------|
| token      | string | 登入游戏的token | yes      |

**response**

|                                     | 类型     | 描述                        | 是否必填 |
|-------------------------------------|--------|---------------------------|------|
| code                                | int    | 相应码,200 成功，401 未授权，500 错误 | 是    |
| data                                 | string | 正常返回success，错误返回对应错误信息    | 否    |

## 8.3、资产查询

可以查询用户所有的nft，以及代币余额

## 8.4、支付
