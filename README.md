# Prerequisites for Game Integration with MagApe Platform
As a web3 platform, several essential requirements must be met before integrating your game with the MagApe platform. These prerequisites ensure the proper functioning of the games and seamless interaction with our platform.
1. **BIP44 Standard Wallet Address:** It is imperative to possess a BIP44 standard wallet address. Ensure that this address is not a contract address and is readily accessible. This wallet serves as a gateway to all future configurations and facilitates payment reception.
2. **Game Item Import and Export:** Prepare all game items for import and export, adhering to the specified JSON format outlined below. Prior to submission, ensure that your JSON file is validated for accuracy.
3. **Image URLs for Game Items:** Each game item must be accompanied by an Image URL. Ensure that the images are formatted with a resolution of 100px by 100px in any image format. Additionally, confirm that hotlinking is permitted for seamless integration.
4. **Acquisition of Secret Key (SK):** Contact our team at partnership@magape.io to obtain the Secret Key (SK) required for communication with our platform. Two Secret Keys will be provided—one for the mainnet and the other for the testnet for testing purposes.

Once these prerequisites are fulfilled, you can proceed to download the SDK or utilize cURL to initiate the process of uploading your game items for approval.

Export and convert game items to ERC-20 tokens. According to our standards, games can allow any item to be converted into tokens. Once the conversion process is initiated, it is irreversible, in-game items will be removed from the player, and the token minting function will be called. If a player fails to complete a cast due to low fuel, connection issues, or other issues, we will create a pending status for him so that he can recast later. A nominal processing amount will be deducted for each game item export.

# 1. Game asset list Json structure
Due to the complexity of the game's item model, we dynamically allow items to have a broad classification. In addition to items, if the game wants to export experience, in-game currencies, levels, or any scoped items can support this. Games can update their asset list by re-uploading this file to our interface.
## Message
|  | Description |
| --- | --- |
| Mainnet | https://api.magape.io/api/game/uploadOrUpdateProp |
| Testnet | https://testnet-api.magape.io/api/game/uploadOrUpdateProp |
| 200 | ok with json|
| 400 | The resource is wrong, does not exist, or the request is incorrect.|
| 500 | Server error or request failure, related to game server issues.|
## Example
```json
{
  "maxSell": 10,
  "maxBuy": 10,
  "cost": 0.1,
  "id": "402003351",
  "image": "https://testnet-api.magape.io/ipfs/QmWJEQchSo7HNUzctzTtCPnefFwqzy2ZJAsZcBunvjY8SE",
  "name": "clothes",
  "description":"this is clothes"
}
```
## Attributes explanation
|  | Description |
| --- | --- |
| requestId (header) | The timestamp of this request (in milliseconds). This is for versioning purposes, where the timestamp will determine the updated list of game items. |
| token (header) | used to verify the legitimacy of this request |
| data (body) | List of all game items to be exported, there is no limit to the number of items that can be imported. See below for the format of the data. |
## data
|  | Description |
| --- | --- |
| id | The identification code of the game item. The id must be unique for each item to be identified. This id will be used for all imports and exports. |
| maxSell | How many items can be changed to Mac at most。 |
| maxBuy | How many items can be purchased using Mac at most。 |
| cost | The number of tokens this item may be worth. In the case of range items, each item is worth the number of tokens. Appliances should be priced fairly to be accepted by our ecosystem. |
| image | The graphic of the project to export. Game developers should host their own images for updateability. Our recommended monitor size is 300 x 300. |
| name | The name should be unique and clearly specified. If 2 names are found, the latter one takes effect. An example is grading. The higher the grade, the harder it is to achieve, and the export volume should increase accordingly. |
| description | Description of the item. This Description can describe the ability of the item or simply tell a story about the item. |
| attributes [optional] | This is optional. These are the standard properties that this NFT should contain. There is no limit to the number of attributes, but balance is encouraged. See below for the format of the attribute. |

# 2.Binding player characters and wallet addresses
The game requires binding the player's character and wallet address to facilitate the Magape platform's asset inquiry, NFT change, and asset change operations through the wallet address in the later stage

# 3. Consult the user’s game asset Json structure
We need to load the player's asset Message for export. This API is called whenever we need to update a player's asset list or during transaction confirmation. Since we already have the game's asset list, we only need the project id to work with.
## Message
|  | Description |
| --- | --- |
| Game to provide | https://game.com/api/userAsset/ |
| 200 | ok with json|
| 400 | The resource is wrong, does not exist, or the request is incorrect.|
| 500 | Server error or request failure, related to game server issues.|


## 请求参数

||Description|
|---|---|
|address|Player's wallet address|
## Example
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
    }
  ]
}
```
## data
|  | Description |
| --- | --- |
| id | Game equipment number. |
| value | The number of items belonging to the player, this also includes level and resources. This format can be generic and the game can send us a breakdown of the range. For example, the game breaks down levels 1 to 50 into 1 token per level, and levels 51 through 100 into 2 tokens, allowing for the creation of 2 separate items. If the player level is lower than level 51, the game only needs to generate 1 directory. If the game level is higher than level 50, n - 50 directories must be generated because the first 50 directories have already been generated. The game must handle these messages accordingly to avoid overlapping or excessive export. |

# 3. Game assets/NFT import/export Json structure
Once there is any decrease or increase in the imported or exported asset list, our server will send a Message to the relevant game interface (RPC). Upon receiving the message, the game should check the inventory to see if it exists and immediately remove or add the item from the player. This also applies to NFTs. Please pay attention to this part, it is the most complex part of all because this import/export operation has different meanings and functions.
## Message
|  | Description |
| --- | --- |
| Game to provide | https://game.com/api/userAsset/v1/ |
| 200 | ok with json|
| 400 | The resource is wrong, does not exist, or the request is incorrect.|
| 500 | Server error or request failure, related to game server issues.|
## Example (Export game equipment)
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
## Example (Import game equipment)
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
## Example (Transact of game items)
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
## Example (Minted 3 tokens)
```json
{
  "operation":"nft mint",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## Example (Transferred of NFT)
```json
{
  "operation":"nft transfer",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0xA34357486224151dDfDB291E13194995c22Df505",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## Example (Bought 2 NFT from different users)
```json
{
  "operation":"nft purchase",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## Example (Bartered NFT)
```json
{
  "operation":"nft barter",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0xA34357486224151dDfDB291E13194995c22Df505"
}
```
## Example（NFT Merged）
```json
{
  "operation":"nft merge",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## Example（Update of NFT Metadata）
```json
{
  "operation":"nft refresh",
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD",
  "txHash":"0x4fc7b30e0a2388adcb064d6472cbf440280e7aeacea65c0cbe3925d1714b8488",
  "from":"0x0000000000000000000000000000000000000000",
  "to":"0x4d11df920e0e48c7e132e5a9754c7e754cd6ebfb"
}
```
## Attributes explanation
|  | Description |
| --- | --- |
| operation | "game export" - id is the id of the game asset being exported, quantity is the quality that will be deducted from the game. |
| operation | "game import" - id is the game asset id being imported, quantity is the quality that will be added from the game. |
| operation | "game purchase" - an item that is being rotated in the game, to will receive positive quantities of items, while all negative quantities of items will be reflected to the address in value. |
| operation | "nft mint" - id is the newly minted NFT belonging to the to address. |
| operation | "nft transfer" - This is a direct transfer, no platform or token is involved, the NFT id is deducted and added to the to. |
| operation | "nft purchase" - This is purchased from the market, the seller may be a different person, the NFT is transferred to the to address and deducted from the value field. |
| operation | "nft barter" - The to address is the initiator of this barter transaction, and the value field is the new owner of the NFT id specified in id . |
| operation | "nft merge" - It is always the same person's NFT that is merged, and the amount is added or removed accordingly based on the id. |
| operation | "nft refresh" - This does not require updating the quantities, but simply calling refresh metadata because the properties in it have changed. |
| networkId | The blockchain network that processed this transaction. |
| txHash | Transaction hash generated from the blockchain. |
| from | If something was created, it will be 0x0, otherwise it will be the sender's address. |
| to | Usually the recipient address unless specified by the action. |
| timeStamp | The timestamp (in milliseconds) when this file was generated. This is for versioning purposes, where the timestamp will determine the updated list of game items. |
| data | List of all game items to be exported, there is no limit to the number of items that can be imported. See below for the format of the data. |
## data
|  | Description |
| --- | --- |
| id | Game equipment number. |
| value | This attribute has 2 cases - wallet address and empty. This is explained in Action Properties. |
| quantity | This property has 2 cases - addition and subtraction. This is explained in Action Properties. |

# 3. Query NFT Json structure
This will return a list of NFTs owned by the player. Basically most of its properties follow the OpenSea standard, which will be the same properties stored in our metadata.
## Message
|  | Description |
| --- | --- |
| Mainnet | https://api.magape.io/api/nft/nftList |
| Testnet | https://testnet-api.magape.io/api/nft/nftList |
| 200 | ok with json|
| 400 | The resource is wrong, does not exist, or the request is incorrect.|
| 500 | Server error or request failure, related to game server issues.|
## Example
```json
{
  "data":[
    {
      "id":3,
      "name": "Mappy #3",
      "attributes": [
        {
          "trait_type": "Rank", 
          "value": "Common"
        },
        {
          "trait_type": "Type", 
          "value": "City"
        },
        {
          "display_type": "boost_percentage",
          "trait_type": "Horn",
          "value": 20
        }
      ]
    }
  ],
  "timeStamp":1709267661354,
  "networkId":141319,
  "contract":"0x93EbAFF33eC2Fcc82087DE058ec07a433e6982AD"
}
```
## Attributes explanation
|  | Description |
| --- | --- | 
| id | nft number. |
| name | nft name. |
| attributes | nft attributes. |
| timeStamp | The last time magape received nft. |
| networkId | chainId （BNB mainnet 56） |
| contract | Contract address to which NFT belongs |

## attributes
|  | Description |
| --- | --- | 
| trait_type | attribute name|
| value | attribute value|


# 4. The magape platform queries basic information of game users based on wallet addresses
In the gamehall of magape, players can directly obtain the corresponding game's user character name through the wallet address

## Message
|  | Description |
| --- | --- |
| 主网 | https://game.com/api/userInfo |
| 200 | ok with json|
| 400 | The resource is wrong, does not exist, or the request is incorrect.|
| 500 | Server error or request failure, related to game server issues.|



## Attributes explanation

||Description|
|---|---|
|address|Player's wallet address|

## Example
```json
{
  "address":"0xA34357486224151dDfDB291E13194995c22Df505",
  "username":"Capta1nJ@ck",
  "timeStamp":1709267661354
}
```

## Attributes explanation
|  | Description |
| --- | --- | 
| address | Player's wallet address。|
| username | Player game username。|
| timeStamp | time stamp。|

# 5. Obtain the ability of Ape in the game
We have a fixed set of attributes based on each of the 4 apes, and the game can choose the ape type and attributes to link. How the game interprets this attribute is entirely at the game's discretion, but the linked Message will be displayed on our website for players to understand. The game can only select a single ape and its adjacent properties, and will return an error if a property is not found in the ape. Each new update requires the complete list to be resubmitted, and if the list does not contain the old attributes, it is deleted from the database.
## Message
|  | Description |
| --- | --- |
| Mainnet | https://api.magape.io/attributeLink/v1/ |
| Testnet | https://testnet-api.magape.io/attributeLink/v1/ |
| 200 | ok with json|
| 400 | The resource is wrong, does not exist, or the request is incorrect.|
| 500 | Server error or request failure, related to game server issues.|
## Example
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
## Attributes explanation
|  | Description |
| --- | --- | 
| equip | equipment, defined in the following list |
| gameAttr | Gameplay explanation |
| 1 | Common level value |
| 2 | Uncommon level value |
| 3 | Rare level value |
| 4 | Epic Level Value |
| 5 | Legendary level value |
| apeType | Select "City", "Jungle", "Ocean", "Sky" |
## Attribute table
| Instrument number | City | Jungle | Ocean | Sky |
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
