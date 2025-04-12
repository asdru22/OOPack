# Example
```java
public class _MyProject {
    public static void main(String[] args) {

        String author = "Asdru";

        Namespace main = new Namespace("proj");
        Namespace library = new Namespace("other");

        Datapack datapack = new Datapack(main, library);
        Resourcepack resourcepack = new Resourcepack(main);

        String mcmeta = String.format("""
                {
                    "pack": {
                        "pack_format":  52,
                        "description": "by %s"
                    }
                }
                """, author);

        datapack.setPackMCMeta(mcmeta);
        resourcepack.setPackMCMeta(mcmeta);

        Data mainData = main.getData();
        mainData.add(DataEntries.FUNCTION, "among", "say hi", "say lol");

        Assets mainAssets = main.getAssets();
        mainAssets.add(AssetEntries.TEXTURES, "hello", "img");

        Lang lang = mainAssets.getLang();
        lang.add("item.test", "Test");

        Sounds sounds = mainAssets.getSounds();
        sounds.add("item.double_edged_sword.throw", "basadlsadlsa",
                new SoundItem("test/balls", "sound"));

        Project p = new Project("My new project", "latest",
                "C:\\Users\\Ale\\Documents\\java projects\\OOPack\\out\\built", "test");

        p.setDatapack(datapack);
        p.setResourcepack(resourcepack);

        p.build();
    }
}
```

# TODO:
