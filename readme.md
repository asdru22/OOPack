# Example
```java
public static void main(String[] args) {

        Namespace main = new Namespace("proj");
        Namespace library = new Namespace("other");

        Datapack datapack = new Datapack(main, library);
        Resourcepack resourcepack = new Resourcepack(main);

        String mcmeta = """
                {
                    "pack": {
                        "pack_format":  52,
                        "description": "Cognition by Asdru"
                    }
                }
                """;

        datapack.setPackMCMeta(mcmeta);
        resourcepack.setPackMCMeta(mcmeta);

        Data mainData = main.getData();
        mainData.add(DataEntries.FUNCTION, "among", "say hi", "say lol");

        Assets mainAssets = main.getAssets();
        mainAssets.add(AssetEntries.TEXTURES, "hello", "img");

        Project p = new Project("My new project", "latest",
                "C:\\Users\\Ale\\Documents\\java projects\\OOPack\\out\\built","test");

        p.setDatapack(datapack);
        p.setResourcepack(resourcepack);

        p.build();
    }
```