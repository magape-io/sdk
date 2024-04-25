将游戏物品导出并转换为 ERC-20 代币。根据我们的标准,游戏可以允许任何物品转换为代币。一旦转换过程启动,它是不可逆的,游戏内的物品将从玩家身上移除,并且将调用代币铸造功能。如果玩家因燃料费不足、连接问题或其他问题而未能完成铸造,我们将为他创建一个待处理状态,以便他可以在之后重新铸造。每个游戏物品导出都会扣除象征性的处理金额

# 1. 游戏资产列表Json结构
由于游戏物品模式的复杂性,我们动态地允许物品有广泛的分类。除了物品之外,如果游戏想要导出经验,游戏内的货币、等级或者任何有范围的物品都可以支持。游戏可以通过重新上传此文件到我们的接口来更新其资产列表。
## 信息
|  | 描述 |
| --- | --- |
| 主网 | https://api.magape.io/uploadAsset/v1/ |
| 测网 | https://testnet-api.magape.io/uploadAsset/v1/ |
| 200 | ok 和 json|
| 400 | 资源错误、不存在或请求错误。|
| 500 | 服务器错误或请求失败,与游戏服务器问题相关。|
## 例子
```json
{
  "attributes": [
    {
      "traitType": "width",
      "value": 100
    }
  ],
  "maxSell": 100,
  "maxBuy": 100,
  "cost": 100,
  "description": "game1's t-shirt",
  "id": "990e16cb17714985a6848829b8508a76",
  "image": "https://someurl.com/game/item001a",
  "name": "t-shirt",
  "type": "clothes"
}
```
## 属性说明
|  | 描述 |
| --- | --- |
| requestId (header) | 本次请求的的时间戳（以毫秒为单位）。这是出于版本控制的目的,其中时间戳将确定更新的游戏项目列表。|
| token (header) | 用于验证本次请求的合法性 |
| data (body) | 所有要导出的游戏物品列表,可以导入的物品数量没有限制。请参阅下文了解数据的格式。|
## data
|  | 描述 |
| --- | --- |
| id | 游戏物品的识别码,对于每个要识别的物品来说,该 id 必须是唯一的。该 id 将用于所有导入和导出。 |
| maxSell | 最多可以换多少个道具变成mac。 |
| maxBuy | 最多用mac购买多少个道具。 |
| cost | 该物品可能值的代币数量。如果是范围物品,则每件物品都价值代币数量。器具应该公平定价才能被我们的生态系统接受。 |
| quantity | 可供出售的物品数量,游戏可以导出此列表并相应地更新自己的库存。如果数量为-1,则无限制。 |
| image | 要导出的项目的图形。游戏开发者应该托管自己的映像以实现可更新性。我们的显示器建议尺寸为 300 x 300。|
| name | 名称应该是唯一的并且明确指定。如果找到 2 个名称,则后一个名称生效。一个例子就是等级化,等级越高越难达到,出口额就应该相应增加。 |
| description | 对物品的描述,这个描述可以描述物品的能力或者简单地讲述一个关于这个物品的故事。 |
| attributes [optional] | 这是可选的。这些是这个 NFT 应该包含的标准属性。属性的数量没有限制,但鼓励平衡。请参阅下文了解属性的格式。|
## attributes
|  | 描述 |
| --- | --- |
| traitType | 该物品的特性,换句话说,该装备可能具有的能力或效果。这是大多数 NFT 中存在的常见元数据。特征类型可以重复且可堆叠。 |
| value | 该值可以是数字或字符串形式。触发此能力可能有条件,因此应明确指定特质类型。|

# 2. 咨询用户游戏资产Json结构
我们需要加载玩家的资产信息以供导出。每当我们需要更新玩家的资产列表或在交易确认期间,就会调用此API。由于我们已经有了游戏的资产列表,因此我们只需要项目 id 即可进行处理。
## 信息
|  | 描述 |
| --- | --- |
| 游戏提供 | https://game.com/api/userAsset/v1/ |
| 200 | ok 和 json|
| 400 | 资源错误、不存在或请求错误。|
| 500 | 服务器错误或请求失败,与游戏服务器问题相关。|
## 例子
```json
{
  "data":[
    {
      "id":554,
      "value":1
    },
    {
      "id":328,
      "value":1
    },
    {
      "id":7,
      "value":50
    },
    {
      "id":8,
      "value":7
    },
    {
      "id":3,
      "value":9987782
    }
  ],
  "requestId":1709267661354
}
```
## data
|  | 描述 |
| --- | --- |
| id | 游戏配备编号。|
| value | 属于玩家的物品数量,这还包括等级和资源。这种格式可以是通用的,游戏可以向我们发送范围的细分。例如,游戏将 1 至 50 级分解为每个级别 1 个代币,将 51 至 100 级分解为 2 个代币,可以创建 2 个单独的项目。如果玩家等级低于 51 级,游戏只需生成 1 个目录,如果游戏等级高于 50 级,则必须生成 n - 50 个目录,因为前 50 个目录已经生成。游戏必须相应地处理这些信息,以免重叠或过度导出。|

# 3. 游戏资产/NFT导入/出Json结构
一旦导入或导出资产列表有任何减少或增加,我们的服务器将向相关游戏接口（RPC）发送信息。收到消息后,游戏应检查库存是否存在并立即从玩家身上移除或增加该物品。这也适用于 NFT。请注意这部分,这是所有部分中最复杂的部分,因为此导入/导出操作具有不同的含义和功能。
## 信息
|  | 描述 |
| --- | --- |
| 游戏提供 | https://game.com/api/userAsset/v1/ |
| 200 | ok 和 json|
| 400 | 资源错误、不存在或请求错误。|
| 500 | 服务器错误或请求失败,与游戏服务器问题相关。|
## 例子（导出游戏配备）
```json
{
  "data":[
    {
      "id":3,
      "quantity":-6684
    }
  ],
  "operation":"game export",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x2Db213D8D90eD1af3F4DEBfFAF66b5efe28932A6",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## 例子（导入游戏配备）
```json
{
  "data":[
    {
      "id":554,
      "quantity":3
    }
  ],
  "operation":"game import",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x2Db213D8D90eD1af3F4DEBfFAF66b5efe28932A6",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## 例子（游戏配备买卖）
```json
{
  "data":[
    {
      "id":328,
      "quantity":1
    },
    {
      "id":328,
      "quantity":-1,
      "value":"0xA34357486224151dDfDB291E13194995c22Df505"
    },
    {
      "id":3,
      "quantity":467
    },
    {
      "id":3,
      "quantity":-467
      "value":"0x40f0de13ff4454f2dc786e38504cf4efc2dd12ca"
    }
  ],
  "operation":"game purchase",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x2Db213D8D90eD1af3F4DEBfFAF66b5efe28932A6",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## 例子（铸3个NFT）
```json
{
  "data":[
    {
      "id":8896672
    },
    {
      "id":8896673
    },
    {
      "id":8896674
    }
  ],
  "operation":"nft mint",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## 例子（转移NFT）
```json
{
  "data":[
    {
      "id":33671
    }
  ],
  "operation":"nft transfer",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0xA34357486224151dDfDB291E13194995c22Df505",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## 例子（从不同的2个持有者购买NFT）
```json
{
  "data":[
    {
      "id":67580,
      "value":"0xA34357486224151dDfDB291E13194995c22Df505"
    },
    {
      "id":3377,
      "value":"0x40f0de13ff4454f2dc786e38504cf4efc2dd12ca"
    }
  ],
  "operation":"nft purchase",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## 例子（物物交换NFT）
```json
{
  "data":[
    {
      "id":5288,
      "value":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
    },
    {
      "id":330002,
      "value":"0xA34357486224151dDfDB291E13194995c22Df505"
    }
  ],
  "operation":"nft barter",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0xA34357486224151dDfDB291E13194995c22Df505"
}
```
## 例子（NFT合并）
```json
{
  "data":[
    {
      "id":13289,
      "quantity":-1
    },
    {
      "id":654,
      "quantity":-1
    },
    {
      "id":3230,
      "quantity":-1
    },
    {
      "id":4451,
      "quantity":-1
    },
    {
      "id":5288,
      "quantity":1
    },
  ],
  "operation":"nft merge",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## 例子（NFT升级/重滚 - 刷新元数据）
```json
{
  "data":[
    {
      "id":61
    }
  ],
  "operation":"nft refresh",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## 属性说明
|  | 描述 |
| --- | --- |
| operation | "game export" - id 是正在导出的游戏资产 id,quantity 是将从游戏中扣除的质量。|
| operation | "game import" - id 是正在导入的游戏资产 id,quantity 是将从游戏中增加的质量。|
| operation | “game purchase" - 游戏中正在轮换的物品,to 将收到正数量的物品,而所有负数量的物品将反映到 value 中的地址。|
| operation | "nft mint" - id 是新铸造的 NFT 属于 to 地址。|
| operation | "nft transfer" - 这是直接转账,没有任何平台或代币参与,NFT id 会被扣除并添加到 to 中。|
| operation | "nft purchase" - 这是从市场上购买的,卖家可能是不同的人,NFT 都转移到 to 地址并从 value 字段中扣除。|
| operation | "nft barter" - to 地址是此易货交易的发起者,而 value 字段是 id 中指定的 NFT id 的新所有者。|
| operation | "nft merge" - 合并的始终是同一个人的NFT,并且根据id相应地添加或删除数量。|
| operation | "nft refresh" - 这不需要更新数量,而只需调用刷新元数据,因为其中的属性已更改。|
| networkId | 处理该交易的区块链网络。|
| txHash | 从区块链生成的交易哈希。|
| from | 如果创建了东西,它将是 0x0,否则它将是发件人的地址。|
| to | 除非操作指定,否则通常是收件人地址。|
| timeStamp | 生成此文件的时间戳（以毫秒为单位）。这是出于版本控制的目的,其中时间戳将确定更新的游戏项目列表。|
| data | 所有要导出的游戏物品列表,可以导入的物品数量没有限制。请参阅下文了解数据的格式。|
## data
|  | 描述 |
| --- | --- |
| id | 游戏配备编号。|
| value | 该属性有 2 种情况 - 钱包地址和空。这已在操作属性中进行了解释。|
| quantity | 该属性有 2 种情况 - 加法和减法。这已在操作属性中进行了解释。|

# 3. 查询NFT Json结构
这将返回玩家拥有的 NFT 列表。基本上它的大部分属性都遵循 OpenSea 标准,这将与我们元数据中存储的属性相同。
## 信息
|  | 描述 |
| --- | --- |
| 主网 | https://api.magape.io/userAsset/v1/ |
| 测网 | https://testnet-api.magape.io/userAsset/v1/ |
| 200 | ok 和 json|
| 400 | 资源错误、不存在或请求错误。|
| 500 | 服务器错误或请求失败,与游戏服务器问题相关。|
## 例子（导出游戏配备）
```json
{
  "data":[
    {
      "id":3,
      "name": "X.APE #3",
      "attributes": [
        {
          "trait_type": "Rank", 
          "value": "SS"
        }
      ]
    },
    {
      "id":6,
      "name": "X.APE #6",
      "attributes": [
        {
          "trait_type": "Rank", 
          "value": "SS"
        },
        {
          "trait_type": "Type", 
          "value": "Ocean"
        }
      ]
    }
  ],
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD"
}
```
## 属性说明
|  | 描述 |
| --- | --- | 
| id | NFT 编号。|
| name | NFT 名称。|

# 4. 将钱包与游戏账户关联  
如果游戏不想集成我们的SDK进行账号联动,我们仍然可以向后调用一个函数来验证该用户。一旦一次性密码通过电子邮件或其他方式发送给用户,用户将输入该代码,我们将确认链接。
## 信息
|  | 描述 |
| --- | --- |
| 主网绑定请求调用 | https://game.com/api/link/v1/ |
| 主网解绑请求调用 | https://game.com/api/unlink/v1/ |
| 主网认证调用 | https://game.com/api/auth/v1/ |
| 主网用户名请求调用 | https://game.com/api/username/v1/ |
| 200 | ok 和 json|
| 400 | 资源错误、不存在或请求错误。|
| 401 | 认证失败 |
| 500 | 服务器错误或请求失败,与游戏服务器问题相关。|
## 例子（传 - 请求一次性密码来连接）
```json
{
  "address":"0xA34357486224151dDfDB291E13194995c22Df505",
  "username":"Capta1nJ@ck",
  "timeStamp":1709267661354
}
```
## 例子（传 - 请求一次性密码来解除）
```json
{
  "address":"0xA34357486224151dDfDB291E13194995c22Df505",
  "username":"Capta1nJ@ck",
  "timeStamp":1709267661354
}
```
## 例子（传 - 请求验证一次性密码）
```json
{
  "address":"0xA34357486224151dDfDB291E13194995c22Df505",
  "username":"Capta1nJ@ck",
  "otp":"336579",
  "timeStamp":1709267661354
}
```
## 例子（传 - 请求用户名）
```json
{
  "address":"0xA34357486224151dDfDB291E13194995c22Df505",
  "timeStamp":1709267661354
}
```
## 例子（收 - 请求用户名返回,200）
```json
{
  "username":"Capta1nJ@ck"
}
```
## 属性说明
|  | 描述 |
| --- | --- | 
| address | 钱包地址。|
| username | 玩家游戏用户名。|
| otp | 一次性密码。|
| timeStamp | 时间戳。|

# 5. 游戏效应连接NFT属性
我们根据 4 种猿中的每一种都有固定的属性集,游戏可以选择猿类型和要链接的属性。游戏如何解释该属性完全由游戏自行决定,但链接信息将显示在我们的网站上供玩家了解。游戏只能选择单个猿类及其相邻属性,如果在猿类中找不到某个属性,则会返回错误。每次新的更新都需要重新提交完整列表,如果列表不包含旧属性,则会从数据库中删除。
## 信息
|  | 描述 |
| --- | --- |
| 主网 | https://api.magape.io/attributeLink/v1/ |
| 测网 | https://testnet-api.magape.io/attributeLink/v1/ |
| 200 | ok 和 json|
| 400 | 资源错误、不存在或请求错误。|
| 500 | 服务器错误或请求失败,与游戏服务器问题相关。|
## 例子
```json
{
  "data":[
    {
      "equip":2,
      "gameAttr":"Extra Questions Limit per Day",
      "1":20,
      "2":50,
      "3":120,
      "4":250,
      "5":500
    },
    {
      "equip":5,
      "gameAttr":"Extra Hint per Day",
      "1":5,
      "2":9,
      "3":17,
      "4":31,
      "5":50
    }
  ],
  "apeType":"Ocean",
  "timeStamp":1709267661354
}
```
## 属性说明
|  | 描述 |
| --- | --- | 
| equip | 器具,以下列表定义 |
| gameAttr | 游戏玩法解说 |
| 1 | Common 等级值 |
| 2 | Uncommon 等级值 |
| 3 | Rare 等级值 |
| 4 | Epic 等级值 |
| 5 | Legendary 等级值 |
| apeType | 选择 “City", “Jungle", “Ocean", “Sky" |
## 属性表
| 器具号 | City | Jungle | Ocean | Sky |
| --- | --- | --- | --- | --- | 
| 1 | Handgun | Spear | Trident | Lightning rod |
| 2 | Briefcase | Rucksack | Ziplock bag | Jetpack |
| 3 | Electric car | Lianas | Surfboard | Airship |
| 4 | Condo |  Treehouse | Boathouse | Aetheropolis |
| 5 | Mobile phone | Horn | Blue whale | Satellite |
| 6 | Laptop | Firepit | Fishing net | Bird trap |
| 7 | Water bottle | Morning dew | Oxygen tank | Space suit |
| 8 | Headphone | Wood spirit | Swan lake | Telescope |
| 9 | Sunglasses | Tribal mask | Goggles | Flying helmet |
| 10 | Banana split | MangoSea | coconut | Starfruit |
