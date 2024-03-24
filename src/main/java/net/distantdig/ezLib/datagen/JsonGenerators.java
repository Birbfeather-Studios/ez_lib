package net.distantdig.ezLib.datagen;

import net.distantdig.ezLib.EzLib;
import net.distantdig.ezLib.block.EzBlocksBuilder.Strings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonGenerators {

    static Path resourcePath = Paths.get(System.getProperty("user.dir")).getParent().getParent()
            .resolve(Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "assets" + File.separator));


    //full generators -> use these outside this class!!
    public static void createEzBlock(Strings strings) {
        EzBlockStateGenerator(strings.blockname);
        EzBlockModelGenerator(strings.blockname);
        EzItemModelGenerator(strings.blockname, "");
    }

    public static void createEzStair(Strings strings) {
        EzStairStateGenerator(strings.blockname);
        EzStairModelGenerator(strings.blockname, strings.fullblockname);
        EzItemModelGenerator(strings.blockname, "");
    }

    public static void createEzSlab(Strings strings) {

        EzSlabStateGenerator(strings.blockname, strings.fullblockname);
        EzSlabModelGenerator(strings.blockname, strings.fullblockname);
        EzItemModelGenerator(strings.blockname, "");
    }

    public static void createEzVerticalSlab(Strings strings) {
        EzVerticalSlabStateGenerator(strings.blockname, strings.fullblockname);
        EzVerticalSlabModelGenerator(strings.blockname, strings.fullblockname);
        EzItemModelGenerator(strings.blockname, "_west");
    }

    public static void createEzColumn(Strings strings) {
        EzColumnStateGenerator(strings.blockname);
        EzColumnModelGenerator(strings.blockname, strings.blockname, true);
        EzItemModelGenerator(strings.blockname, "");
    }

    public static void createEzWood(Strings strings) {
        EzColumnStateGenerator(strings.blockname);
        EzColumnModelGenerator(strings.blockname, strings.fullblockname, false);
        EzItemModelGenerator(strings.blockname, "");
    }

    public static void createEzDoor(Strings strings) {
        EzDoorStateGenerator(strings.blockname);
        EzDoorModelGenerator(strings.blockname);
        EzFlatItemModelGenerator(strings.blockname);
    }

    public static void createEzTrapDoor(Strings strings) {
        EzTrapDoorStateGenerator(strings.blockname);
        EzTrapDoorModelGenerator(strings.blockname);
        EzItemModelGenerator(strings.blockname, "_bottom");
    }

    public static void createEzButton(Strings strings) {
        EzButtonStateGenerator(strings.blockname);
        EzButtonModelGenerator(strings.blockname, strings.fullblockname);
        EzItemModelGenerator(strings.blockname, "_inventory");
    }

    public static void createEzFence(Strings strings) {
        EzFenceStateGenerator(strings.blockname);
        EzFenceModelGenerator(strings.blockname, strings.fullblockname);
        EzItemModelGenerator(strings.blockname, "_inventory");
    }

    public static void createEzFenceGate(Strings strings) {
        EzFenceGateStateGenerator(strings.blockname);
        EzFenceGateModelGenerator(strings.blockname, strings.fullblockname);
        EzItemModelGenerator(strings.blockname, "");
    }

    public static void createEzWall(Strings strings) {
        EzWallStateGenerator(strings.blockname);
        EzWallModelGenerator(strings.blockname, strings.fullblockname);
        EzItemModelGenerator(strings.blockname, "_inventory");
    }

    public static void createEzPressurePlate(Strings strings) {
        EzPressurePlateStateGenerator(strings.blockname);
        EzPressurePlateModelGenerator(strings.blockname, strings.fullblockname);
        EzItemModelGenerator(strings.blockname, "");
    }

    public static void createEzCarpet(Strings strings) {
        EzCarpetStateGenerator(strings.blockname);
        EzCarpetModelGenerator(strings.blockname, strings.fullblockname);
        EzItemModelGenerator(strings.blockname, "");
    }

    //Blockstates
    public static void EzBlockStateGenerator(String key) {
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);
        String blockStateContent = """
                {
                  "variants": {
                    "": {
                      "model": "$modid:block/$name"
                    }
                  }
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzStairStateGenerator(String key) {
        String stairgeneratorString = """
                {        
                    "variants": {
                    "facing=east,half=bottom,shape=inner_left": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "y": 270
                    },
                    "facing=east,half=bottom,shape=inner_right": {
                        "model": "$modid:block/$name_inner"
                    },
                    "facing=east,half=bottom,shape=outer_left": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "y": 270
                    },
                    "facing=east,half=bottom,shape=outer_right": {
                        "model": "$modid:block/$name_outer"
                    },
                    "facing=east,half=bottom,shape=straight": {
                        "model": "$modid:block/$name"
                    },
                    "facing=east,half=top,shape=inner_left": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "x": 180
                    },
                    "facing=east,half=top,shape=inner_right": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "x": 180,
                                "y": 90
                    },
                    "facing=east,half=top,shape=outer_left": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "x": 180
                    },
                    "facing=east,half=top,shape=outer_right": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "x": 180,
                                "y": 90
                    },
                    "facing=east,half=top,shape=straight": {
                        "model": "$modid:block/$name",
                                "uvlock": true,
                                "x": 180
                    },
                    "facing=north,half=bottom,shape=inner_left": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "y": 180
                    },
                    "facing=north,half=bottom,shape=inner_right": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "y": 270
                    },
                    "facing=north,half=bottom,shape=outer_left": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "y": 180
                    },
                    "facing=north,half=bottom,shape=outer_right": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "y": 270
                    },
                    "facing=north,half=bottom,shape=straight": {
                        "model": "$modid:block/$name",
                                "uvlock": true,
                                "y": 270
                    },
                    "facing=north,half=top,shape=inner_left": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "x": 180,
                                "y": 270
                    },
                    "facing=north,half=top,shape=inner_right": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "x": 180
                    },
                    "facing=north,half=top,shape=outer_left": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "x": 180,
                                "y": 270
                    },
                    "facing=north,half=top,shape=outer_right": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "x": 180
                    },
                    "facing=north,half=top,shape=straight": {
                        "model": "$modid:block/$name",
                                "uvlock": true,
                                "x": 180,
                                "y": 270
                    },
                    "facing=south,half=bottom,shape=inner_left": {
                        "model": "$modid:block/$name_inner"
                    },
                    "facing=south,half=bottom,shape=inner_right": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "y": 90
                    },
                    "facing=south,half=bottom,shape=outer_left": {
                        "model": "$modid:block/$name_outer"
                    },
                    "facing=south,half=bottom,shape=outer_right": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "y": 90
                    },
                    "facing=south,half=bottom,shape=straight": {
                        "model": "$modid:block/$name",
                                "uvlock": true,
                                "y": 90
                    },
                    "facing=south,half=top,shape=inner_left": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "x": 180,
                                "y": 90
                    },
                    "facing=south,half=top,shape=inner_right": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "x": 180,
                                "y": 180
                    },
                    "facing=south,half=top,shape=outer_left": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "x": 180,
                                "y": 90
                    },
                    "facing=south,half=top,shape=outer_right": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "x": 180,
                                "y": 180
                    },
                    "facing=south,half=top,shape=straight": {
                        "model": "$modid:block/$name",
                                "uvlock": true,
                                "x": 180,
                                "y": 90
                    },
                    "facing=west,half=bottom,shape=inner_left": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "y": 90
                    },
                    "facing=west,half=bottom,shape=inner_right": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "y": 180
                    },
                    "facing=west,half=bottom,shape=outer_left": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "y": 90
                    },
                    "facing=west,half=bottom,shape=outer_right": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "y": 180
                    },
                    "facing=west,half=bottom,shape=straight": {
                        "model": "$modid:block/$name",
                                "uvlock": true,
                                "y": 180
                    },
                    "facing=west,half=top,shape=inner_left": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "x": 180,
                                "y": 180
                    },
                    "facing=west,half=top,shape=inner_right": {
                        "model": "$modid:block/$name_inner",
                                "uvlock": true,
                                "x": 180,
                                "y": 270
                    },
                    "facing=west,half=top,shape=outer_left": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "x": 180,
                                "y": 180
                    },
                    "facing=west,half=top,shape=outer_right": {
                        "model": "$modid:block/$name_outer",
                                "uvlock": true,
                                "x": 180,
                                "y": 270
                    },
                    "facing=west,half=top,shape=straight": {
                        "model": "$modid:block/$name",
                                "uvlock": true,
                                "x": 180,
                                "y": 180
                    }
                    }
                }""".replace("$modid", EzLib.getModId()).replace("$name", key);
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);

        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(stairgeneratorString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzSlabStateGenerator(String key, String fullBlock) {
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);
        String blockStateContent = """
                {
                  "variants": {
                    "type=bottom": {
                      "model": "$modid:block/$name"
                    },
                    "type=double": {
                      "model": "$modid:block/$full_name"
                    },
                    "type=top": {
                      "model": "$modid:block/$name_top"
                    }
                  }
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key).replace("$full_name", fullBlock);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzVerticalSlabStateGenerator(String key, String fullBlock) {
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);
        String blockStateContent = """
                {
                  "variants": {
                    "facing=north,single=true": {
                      "model": "$modid:block/$name_south"
                    },
                    "facing=west,single=true": {
                      "model": "$modid:block/$name_east"
                    },
                    "facing=south,single=true": {
                      "model": "$modid:block/$name_north"
                    },
                    "facing=east,single=true": {
                      "model": "$modid:block/$name_west"
                    },
                    "facing=north,single=false": {
                      "model": "$modid:block/$full_name"
                    },
                    "facing=west,single=false": {
                      "model": "$modid:block/$full_name"
                    },
                    "facing=east,single=false": {
                      "model": "$modid:block/$full_name"
                    },
                    "facing=south,single=false": {
                      "model": "$modid:block/$full_name"
                    }
                  }
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key).replace("$full_name", fullBlock);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzColumnStateGenerator(String key) {
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);
        String blockStateContent = """
                {
                  "variants": {
                    "axis=x": {
                      "model": "$modid:block/$name_horizontal",
                      "x": 90,
                      "y": 90
                    },
                    "axis=y": {
                      "model": "$modid:block/$name"
                    },
                    "axis=z": {
                      "model": "$modid:block/$name_horizontal",
                      "x": 90
                    }
                  }
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzDoorStateGenerator(String key) {
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);
        String blockStateContent = """
                {
                  "variants": {
                    "facing=east,half=lower,hinge=left,open=false": {
                      "model": "$modid:block/$name_bottom_left"
                    },
                    "facing=east,half=lower,hinge=left,open=true": {
                      "model": "$modid:block/$name_bottom_left_open",
                      "y": 90
                    },
                    "facing=east,half=lower,hinge=right,open=false": {
                      "model": "$modid:block/$name_bottom_right"
                    },
                    "facing=east,half=lower,hinge=right,open=true": {
                      "model": "$modid:block/$name_bottom_right_open",
                      "y": 270
                    },
                    "facing=east,half=upper,hinge=left,open=false": {
                      "model": "$modid:block/$name_top_left"
                    },
                    "facing=east,half=upper,hinge=left,open=true": {
                      "model": "$modid:block/$name_top_left_open",
                      "y": 90
                    },
                    "facing=east,half=upper,hinge=right,open=false": {
                      "model": "$modid:block/$name_top_right"
                    },
                    "facing=east,half=upper,hinge=right,open=true": {
                      "model": "$modid:block/$name_top_right_open",
                      "y": 270
                    },
                    "facing=north,half=lower,hinge=left,open=false": {
                      "model": "$modid:block/$name_bottom_left",
                      "y": 270
                    },
                    "facing=north,half=lower,hinge=left,open=true": {
                      "model": "$modid:block/$name_bottom_left_open"
                    },
                    "facing=north,half=lower,hinge=right,open=false": {
                      "model": "$modid:block/$name_bottom_right",
                      "y": 270
                    },
                    "facing=north,half=lower,hinge=right,open=true": {
                      "model": "$modid:block/$name_bottom_right_open",
                      "y": 180
                    },
                    "facing=north,half=upper,hinge=left,open=false": {
                      "model": "$modid:block/$name_top_left",
                      "y": 270
                    },
                    "facing=north,half=upper,hinge=left,open=true": {
                      "model": "$modid:block/$name_top_left_open"
                    },
                    "facing=north,half=upper,hinge=right,open=false": {
                      "model": "$modid:block/$name_top_right",
                      "y": 270
                    },
                    "facing=north,half=upper,hinge=right,open=true": {
                      "model": "$modid:block/$name_top_right_open",
                      "y": 180
                    },
                    "facing=south,half=lower,hinge=left,open=false": {
                      "model": "$modid:block/$name_bottom_left",
                      "y": 90
                    },
                    "facing=south,half=lower,hinge=left,open=true": {
                      "model": "$modid:block/$name_bottom_left_open",
                      "y": 180
                    },
                    "facing=south,half=lower,hinge=right,open=false": {
                      "model": "$modid:block/$name_bottom_right",
                      "y": 90
                    },
                    "facing=south,half=lower,hinge=right,open=true": {
                      "model": "$modid:block/$name_bottom_right_open"
                    },
                    "facing=south,half=upper,hinge=left,open=false": {
                      "model": "$modid:block/$name_top_left",
                      "y": 90
                    },
                    "facing=south,half=upper,hinge=left,open=true": {
                      "model": "$modid:block/$name_top_left_open",
                      "y": 180
                    },
                    "facing=south,half=upper,hinge=right,open=false": {
                      "model": "$modid:block/$name_top_right",
                      "y": 90
                    },
                    "facing=south,half=upper,hinge=right,open=true": {
                      "model": "$modid:block/$name_top_right_open"
                    },
                    "facing=west,half=lower,hinge=left,open=false": {
                      "model": "$modid:block/$name_bottom_left",
                      "y": 180
                    },
                    "facing=west,half=lower,hinge=left,open=true": {
                      "model": "$modid:block/$name_bottom_left_open",
                      "y": 270
                    },
                    "facing=west,half=lower,hinge=right,open=false": {
                      "model": "$modid:block/$name_bottom_right",
                      "y": 180
                    },
                    "facing=west,half=lower,hinge=right,open=true": {
                      "model": "$modid:block/$name_bottom_right_open",
                      "y": 90
                    },
                    "facing=west,half=upper,hinge=left,open=false": {
                      "model": "$modid:block/$name_top_left",
                      "y": 180
                    },
                    "facing=west,half=upper,hinge=left,open=true": {
                      "model": "$modid:block/$name_top_left_open",
                      "y": 270
                    },
                    "facing=west,half=upper,hinge=right,open=false": {
                      "model": "$modid:block/$name_top_right",
                      "y": 180
                    },
                    "facing=west,half=upper,hinge=right,open=true": {
                      "model": "$modid:block/$name_top_right_open",
                      "y": 90
                    }
                  }
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzTrapDoorStateGenerator(String key) {
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);
        String blockStateContent = """
                {
                  "variants": {
                    "facing=east,half=bottom,open=false": {
                      "model": "$modid:block/$name_bottom"
                    },
                    "facing=east,half=bottom,open=true": {
                      "model": "$modid:block/$name_open",
                      "y": 90
                    },
                    "facing=east,half=top,open=false": {
                      "model": "$modid:block/$name_top"
                    },
                    "facing=east,half=top,open=true": {
                      "model": "$modid:block/$name_open",
                      "y": 90
                    },
                    "facing=north,half=bottom,open=false": {
                      "model": "$modid:block/$name_bottom"
                    },
                    "facing=north,half=bottom,open=true": {
                      "model": "$modid:block/$name_open"
                    },
                    "facing=north,half=top,open=false": {
                      "model": "$modid:block/$name_top"
                    },
                    "facing=north,half=top,open=true": {
                      "model": "$modid:block/$name_open"
                    },
                    "facing=south,half=bottom,open=false": {
                      "model": "$modid:block/$name_bottom"
                    },
                    "facing=south,half=bottom,open=true": {
                      "model": "$modid:block/$name_open",
                      "y": 180
                    },
                    "facing=south,half=top,open=false": {
                      "model": "$modid:block/$name_top"
                    },
                    "facing=south,half=top,open=true": {
                      "model": "$modid:block/$name_open",
                      "y": 180
                    },
                    "facing=west,half=bottom,open=false": {
                      "model": "$modid:block/$name_bottom"
                    },
                    "facing=west,half=bottom,open=true": {
                      "model": "$modid:block/$name_open",
                      "y": 270
                    },
                    "facing=west,half=top,open=false": {
                      "model": "$modid:block/$name_top"
                    },
                    "facing=west,half=top,open=true": {
                      "model": "$modid:block/$name_open",
                      "y": 270
                    }
                  }
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzButtonStateGenerator(String key) {
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);
        String blockStateContent = """
                {
                  "variants": {
                    "face=ceiling,facing=east,powered=false": {
                      "model": "$modid:block/$name",
                      "x": 180,
                      "y": 270
                    },
                    "face=ceiling,facing=east,powered=true": {
                      "model": "$modid:block/$name_pressed",
                      "x": 180,
                      "y": 270
                    },
                    "face=ceiling,facing=north,powered=false": {
                      "model": "$modid:block/$name",
                      "x": 180,
                      "y": 180
                    },
                    "face=ceiling,facing=north,powered=true": {
                      "model": "$modid:block/$name_pressed",
                      "x": 180,
                      "y": 180
                    },
                    "face=ceiling,facing=south,powered=false": {
                      "model": "$modid:block/$name",
                      "x": 180
                    },
                    "face=ceiling,facing=south,powered=true": {
                      "model": "$modid:block/$name_pressed",
                      "x": 180
                    },
                    "face=ceiling,facing=west,powered=false": {
                      "model": "$modid:block/$name",
                      "x": 180,
                      "y": 90
                    },
                    "face=ceiling,facing=west,powered=true": {
                      "model": "$modid:block/$name_pressed",
                      "x": 180,
                      "y": 90
                    },
                    "face=floor,facing=east,powered=false": {
                      "model": "$modid:block/$name",
                      "y": 90
                    },
                    "face=floor,facing=east,powered=true": {
                      "model": "$modid:block/$name_pressed",
                      "y": 90
                    },
                    "face=floor,facing=north,powered=false": {
                      "model": "$modid:block/$name"
                    },
                    "face=floor,facing=north,powered=true": {
                      "model": "$modid:block/$name_pressed"
                    },
                    "face=floor,facing=south,powered=false": {
                      "model": "$modid:block/$name",
                      "y": 180
                    },
                    "face=floor,facing=south,powered=true": {
                      "model": "$modid:block/$name_pressed",
                      "y": 180
                    },
                    "face=floor,facing=west,powered=false": {
                      "model": "$modid:block/$name",
                      "y": 270
                    },
                    "face=floor,facing=west,powered=true": {
                      "model": "$modid:block/$name_pressed",
                      "y": 270
                    },
                    "face=wall,facing=east,powered=false": {
                      "model": "$modid:block/$name",
                      "uvlock": true,
                      "x": 90,
                      "y": 90
                    },
                    "face=wall,facing=east,powered=true": {
                      "model": "$modid:block/$name_pressed",
                      "uvlock": true,
                      "x": 90,
                      "y": 90
                    },
                    "face=wall,facing=north,powered=false": {
                      "model": "$modid:block/$name",
                      "uvlock": true,
                      "x": 90
                    },
                    "face=wall,facing=north,powered=true": {
                      "model": "$modid:block/$name_pressed",
                      "uvlock": true,
                      "x": 90
                    },
                    "face=wall,facing=south,powered=false": {
                      "model": "$modid:block/$name",
                      "uvlock": true,
                      "x": 90,
                      "y": 180
                    },
                    "face=wall,facing=south,powered=true": {
                      "model": "$modid:block/$name_pressed",
                      "uvlock": true,
                      "x": 90,
                      "y": 180
                    },
                    "face=wall,facing=west,powered=false": {
                      "model": "$modid:block/$name",
                      "uvlock": true,
                      "x": 90,
                      "y": 270
                    },
                    "face=wall,facing=west,powered=true": {
                      "model": "$modid:block/$name_pressed",
                      "uvlock": true,
                      "x": 90,
                      "y": 270
                    }
                  }
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzFenceStateGenerator(String key) {
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);
        String blockStateContent = """
                {
                  "multipart": [
                    {
                      "apply": {
                        "model": "$modid:block/$name_post"
                      }
                    },
                    {
                      "apply": {
                        "model": "$modid:block/$name_side",
                        "uvlock": true
                      },
                      "when": {
                        "north": "true"
                      }
                    },
                    {
                      "apply": {
                        "model": "$modid:block/$name_side",
                        "uvlock": true,
                        "y": 90
                      },
                      "when": {
                        "east": "true"
                      }
                    },
                    {
                      "apply": {
                        "model": "$modid:block/$name_side",
                        "uvlock": true,
                        "y": 180
                      },
                      "when": {
                        "south": "true"
                      }
                    },
                    {
                      "apply": {
                        "model": "$modid:block/$name_side",
                        "uvlock": true,
                        "y": 270
                      },
                      "when": {
                        "west": "true"
                      }
                    }
                  ]
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzFenceGateStateGenerator(String key) {
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);
        String FenceGate = """
                {
                  "variants": {
                    "facing=east,in_wall=false,open=false": {
                      "model": "$modid:block/$name",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,in_wall=false,open=true": {
                      "model": "$modid:block/$name_open",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,in_wall=true,open=false": {
                      "model": "$modid:block/$name",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=east,in_wall=true,open=true": {
                      "model": "$modid:block/$name_open",
                      "uvlock": true,
                      "y": 270
                    },
                    "facing=north,in_wall=false,open=false": {
                      "model": "$modid:block/$name",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,in_wall=false,open=true": {
                      "model": "$modid:block/$name_open",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,in_wall=true,open=false": {
                      "model": "$modid:block/$name",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=north,in_wall=true,open=true": {
                      "model": "$modid:block/$name_open",
                      "uvlock": true,
                      "y": 180
                    },
                    "facing=south,in_wall=false,open=false": {
                      "model": "$modid:block/$name",
                      "uvlock": true
                    },
                    "facing=south,in_wall=false,open=true": {
                      "model": "$modid:block/$name_open",
                      "uvlock": true
                    },
                    "facing=south,in_wall=true,open=false": {
                      "model": "$modid:block/$name",
                      "uvlock": true
                    },
                    "facing=south,in_wall=true,open=true": {
                      "model": "$modid:block/$name_open",
                      "uvlock": true
                    },
                    "facing=west,in_wall=false,open=false": {
                      "model": "$modid:block/$name",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,in_wall=false,open=true": {
                      "model": "$modid:block/$name_open",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,in_wall=true,open=false": {
                      "model": "$modid:block/$name",
                      "uvlock": true,
                      "y": 90
                    },
                    "facing=west,in_wall=true,open=true": {
                      "model": "$modid:block/$name_open",
                      "uvlock": true,
                      "y": 90
                    }
                  }
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(FenceGate);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzWallStateGenerator(String key) {
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);
        String blockStateContent = """
                {
                  "multipart": [
                    {
                      "apply": {
                        "model": "$modid:block/$name_post"
                      },
                      "when": {
                        "up": "true"
                      }
                    },
                    {
                      "apply": {
                        "model": "$modid:block/$name_side",
                        "uvlock": true
                      },
                      "when": {
                        "north": "low"
                      }
                    },
                    {
                      "apply": {
                        "model": "$modid:block/$name_side",
                        "uvlock": true,
                        "y": 90
                      },
                      "when": {
                        "east": "low"
                      }
                    },
                    {
                      "apply": {
                        "model": "$modid:block/$name_side",
                        "uvlock": true,
                        "y": 180
                      },
                      "when": {
                        "south": "low"
                      }
                    },
                    {
                      "apply": {
                        "model": "$modid:block/$name_side",
                        "uvlock": true,
                        "y": 270
                      },
                      "when": {
                        "west": "low"
                      }
                    },
                    {
                      "apply": {
                        "model": "$modid:block/$name_side_tall",
                        "uvlock": true
                      },
                      "when": {
                        "north": "tall"
                      }
                    },
                    {
                      "apply": {
                        "model": "$modid:block/$name_side_tall",
                        "uvlock": true,
                        "y": 90
                      },
                      "when": {
                        "east": "tall"
                      }
                    },
                    {
                      "apply": {
                        "model": "$modid:block/$name_side_tall",
                        "uvlock": true,
                        "y": 180
                      },
                      "when": {
                        "south": "tall"
                      }
                    },
                    {
                      "apply": {
                        "model": "$modid:block/$name_side_tall",
                        "uvlock": true,
                        "y": 270
                      },
                      "when": {
                        "west": "tall"
                      }
                    }
                  ]
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzPressurePlateStateGenerator(String key) {
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);
        String blockStateContent = """
                {
                  "variants": {
                    "powered=false": {
                      "model": "$modid:block/$name"
                    },
                    "powered=true": {
                      "model": "$modid:block/$name_down"
                    }
                  }
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzCarpetStateGenerator(String key) {
        Path blockStatePath = Paths.get(EzLib.getModId() + File.separator + "blockstates" + File.separator + key + ".json");
        Path path = resourcePath.resolve(blockStatePath);
        String blockStateContent = """
                {
                  "variants": {
                    "": {
                      "model": "$modid:block/$name"
                    }
                  }
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //BlockModels
    public static void EzBlockModelGenerator(String key) {
        String block = """
                {
                  "parent": "minecraft:block/cube_all",
                  "textures": {
                    "all": "$modid:block/$name"
                  }
                }
                """;
        baseModelJsonGenWithCustomTexture(key, key, block);
    }

    public static void EzStairModelGenerator(String key, String fullName) {
        String straight_stair = """
                {
                  "parent": "minecraft:block/stairs",
                  "textures": {
                    "bottom": "$modid:block/$full_name",
                    "side": "$modid:block/$full_name",
                    "top": "$modid:block/$full_name"
                  }
                }
                """;

        String stair_inner = """
                {
                  "parent": "minecraft:block/inner_stairs",
                  "textures": {
                    "bottom": "$modid:block/$full_name",
                    "side": "$modid:block/$full_name",
                    "top": "$modid:block/$full_name"
                  }
                }
                """;

        String stair_outer = """
                {
                  "parent": "minecraft:block/outer_stairs",
                  "textures": {
                    "bottom": "$modid:block/$full_name",
                    "side": "$modid:block/$full_name",
                    "top": "$modid:block/$full_name"
                  }
                }
                """;
        baseModelJsonGenWithFullTexture(key, fullName, straight_stair);
        baseModelJsonGenWithFullTexture(key + "_inner", fullName, stair_inner);
        baseModelJsonGenWithFullTexture(key + "_outer", fullName, stair_outer);
    }

    public static void EzSlabModelGenerator(String key, String fullName) {
        String slab = """
                {
                  "parent": "minecraft:block/slab",
                  "textures": {
                    "bottom": "$modid:block/$full_name",
                    "side": "$modid:block/$full_name",
                    "top": "$modid:block/$full_name"
                  }
                }
                """;

        String slab_top = """
                {
                  "parent": "minecraft:block/slab_top",
                  "textures": {
                    "bottom": "$modid:block/$full_name",
                    "side": "$modid:block/$full_name",
                    "top": "$modid:block/$full_name"
                  }
                }
                """;
        baseModelJsonGenWithFullTexture(key, fullName, slab);
        baseModelJsonGenWithFullTexture(key + "_top", fullName, slab_top);
    }

    public static void EzVerticalSlabModelGenerator(String key, String fullName) {
        String facingNorth = """
                {
                	"textures": {
                		"0": "$modid:block/$full_name",
                		"particle": "$modid:block/$full_name"
                	},
                	"elements": [
                		{
                			"from": [0, 0, 0],
                			"to": [16, 16, 8],
                			"faces": {
                				"north": {"uv": [0, 0, 16, 16], "texture": "#0"},
                				"east": {"uv": [0, 0, 8, 16], "texture": "#0"},
                				"south": {"uv": [0, 0, 16, 16], "texture": "#0"},
                				"west": {"uv": [0, 0, 8, 16], "texture": "#0"},
                				"up": {"uv": [0, 0, 8, 16], "rotation": 270, "texture": "#0"},
                				"down": {"uv": [0, 0, 8, 16], "rotation": 90, "texture": "#0"}
                			}
                		}
                	],
                	"display": {
                 		"thirdperson_righthand": {
                 			"rotation": [75, 45, 0],
                 			"translation": [0, 2.5, 0],
                 			"scale": [0.375, 0.375, 0.375]
                 		},
                 		"thirdperson_lefthand": {
                 			"rotation": [75, 45, 0],
                 			"translation": [0, 2.5, 0],
                 			"scale": [0.375, 0.375, 0.375]
                 		},
                 		"firstperson_righthand": {
                 			"rotation": [0, 45, 0],
                 			"scale": [0.4, 0.4, 0.4]
                 		},
                 		"firstperson_lefthand": {
                 			"rotation": [0, 225, 0],
                 			"scale": [0.4, 0.4, 0.4]
                 		},
                 		"ground": {
                 			"translation": [0, 3, 0],
                 			"scale": [0.25, 0.25, 0.25]
                 		},
                 		"gui": {
                 			"rotation": [30, 225, 0],
                 			"scale": [0.625, 0.625, 0.625]
                 		},
                 		"fixed": {
                 			"scale": [0.5, 0.5, 0.5]
                 		}
                 	}
                }
                """;

        String facingWest = """
                {                	
                	"textures": {
                		"0": "$modid:block/$full_name",
                		"particle": "$modid:block/$full_name"
                	},
                	"elements": [
                		{
                			"from": [0, 0, 0],
                			"to": [8, 16, 16],
                			"faces": {
                				"north": {"uv": [0, 0, 8, 16], "texture": "#0"},
                				"east": {"uv": [0, 0, 16, 16], "texture": "#0"},
                				"south": {"uv": [0, 0, 8, 16], "texture": "#0"},
                				"west": {"uv": [0, 0, 16, 16], "texture": "#0"},
                				"up": {"uv": [0, 0, 16, 8], "rotation": 270, "texture": "#0"},
                				"down": {"uv": [0, 0, 16, 8], "rotation": 90, "texture": "#0"}
                			}
                		}
                	],
                	"display": {
                 		"thirdperson_righthand": {
                 			"rotation": [75, 45, 0],
                 			"translation": [0, 2.5, 0],
                 			"scale": [0.375, 0.375, 0.375]
                 		},
                 		"thirdperson_lefthand": {
                 			"rotation": [75, 45, 0],
                 			"translation": [0, 2.5, 0],
                 			"scale": [0.375, 0.375, 0.375]
                 		},
                 		"firstperson_righthand": {
                 			"rotation": [0, 45, 0],
                 			"scale": [0.4, 0.4, 0.4]
                 		},
                 		"firstperson_lefthand": {
                 			"rotation": [0, 225, 0],
                 			"scale": [0.4, 0.4, 0.4]
                 		},
                 		"ground": {
                 			"translation": [0, 3, 0],
                 			"scale": [0.25, 0.25, 0.25]
                 		},
                 		"gui": {
                 			"rotation": [30, 225, 0],
                 			"scale": [0.625, 0.625, 0.625]
                 		},
                 		"fixed": {
                 			"scale": [0.5, 0.5, 0.5]
                 		}
                 	}
                }
                """;

        String facingEast = """
                {                	
                	"textures": {
                		"0": "$modid:block/$full_name",
                		"particle": "$modid:block/$full_name"
                	},
                	"elements": [
                		{
                			"from": [8, 0, 0],
                			"to": [16, 16, 16],
                			"faces": {
                				"north": {"uv": [0, 0, 8, 16], "texture": "#0"},
                				"east": {"uv": [0, 0, 16, 16], "texture": "#0"},
                				"south": {"uv": [0, 0, 8, 16], "texture": "#0"},
                				"west": {"uv": [0, 0, 16, 16], "texture": "#0"},
                				"up": {"uv": [0, 0, 16, 8], "rotation": 270, "texture": "#0"},
                				"down": {"uv": [0, 0, 16, 8], "rotation": 90, "texture": "#0"}
                			}
                		}
                	],
                	"display": {
                 		"thirdperson_righthand": {
                 			"rotation": [75, 45, 0],
                 			"translation": [0, 2.5, 0],
                 			"scale": [0.375, 0.375, 0.375]
                 		},
                 		"thirdperson_lefthand": {
                 			"rotation": [75, 45, 0],
                 			"translation": [0, 2.5, 0],
                 			"scale": [0.375, 0.375, 0.375]
                 		},
                 		"firstperson_righthand": {
                 			"rotation": [0, 45, 0],
                 			"scale": [0.4, 0.4, 0.4]
                 		},
                 		"firstperson_lefthand": {
                 			"rotation": [0, 225, 0],
                 			"scale": [0.4, 0.4, 0.4]
                 		},
                 		"ground": {
                 			"translation": [0, 3, 0],
                 			"scale": [0.25, 0.25, 0.25]
                 		},
                 		"gui": {
                 			"rotation": [30, 225, 0],
                 			"scale": [0.625, 0.625, 0.625]
                 		},
                 		"fixed": {
                 			"scale": [0.5, 0.5, 0.5]
                 		}
                 	}
                }
                """;

        String facingSouth = """
                {
                	"textures": {
                		"0": "$modid:block/$full_name",
                		"particle": "$modid:block/$full_name"
                	},
                	"elements": [
                		{
                			"from": [0, 0, 8],
                			"to": [16, 16, 16],
                			"faces": {
                				"north": {"uv": [0, 0, 16, 16], "texture": "#0"},
                				"east": {"uv": [0, 0, 8, 16], "texture": "#0"},
                				"south": {"uv": [0, 0, 16, 16], "texture": "#0"},
                				"west": {"uv": [0, 0, 8, 16], "texture": "#0"},
                				"up": {"uv": [0, 0, 8, 16], "rotation": 270, "texture": "#0"},
                				"down": {"uv": [0, 0, 8, 16], "rotation": 90, "texture": "#0"}
                			}
                		}
                	],
                	"display": {
                 		"thirdperson_righthand": {
                 			"rotation": [75, 45, 0],
                 			"translation": [0, 2.5, 0],
                 			"scale": [0.375, 0.375, 0.375]
                 		},
                 		"thirdperson_lefthand": {
                 			"rotation": [75, 45, 0],
                 			"translation": [0, 2.5, 0],
                 			"scale": [0.375, 0.375, 0.375]
                 		},
                 		"firstperson_righthand": {
                 			"rotation": [0, 45, 0],
                 			"scale": [0.4, 0.4, 0.4]
                 		},
                 		"firstperson_lefthand": {
                 			"rotation": [0, 225, 0],
                 			"scale": [0.4, 0.4, 0.4]
                 		},
                 		"ground": {
                 			"translation": [0, 3, 0],
                 			"scale": [0.25, 0.25, 0.25]
                 		},
                 		"gui": {
                 			"rotation": [30, 225, 0],
                 			"scale": [0.625, 0.625, 0.625]
                 		},
                 		"fixed": {
                 			"scale": [0.5, 0.5, 0.5]
                 		}
                 	}
                }
                """;
        baseModelJsonGenWithFullTexture(key + "_east", fullName, facingEast);
        baseModelJsonGenWithFullTexture(key + "_north", fullName, facingNorth);
        baseModelJsonGenWithFullTexture(key + "_south", fullName, facingSouth);
        baseModelJsonGenWithFullTexture(key + "_west", fullName, facingWest);
    }

    public static void EzColumnModelGenerator(String key, String key2, Boolean toptexture) {
        String column = """
                {
                  "parent": "minecraft:block/cube_column",
                  "textures": {
                    "end": "$modid:block/$name_top",
                    "side": "$modid:block/$name"
                  }
                }
                """;

        String horizontalColumn = """
                {
                  "parent": "minecraft:block/cube_column_horizontal",
                  "textures": {
                    "end": "$modid:block/$name_top",
                    "side": "$modid:block/$name"
                  }
                }
                """;
        if (!toptexture) {
            baseModelJsonGenWithCustomTexture(key, key2, column.replace("_top", ""));
            baseModelJsonGenWithCustomTexture(key + "_horizontal", key2, horizontalColumn.replace("_top", ""));
        } else {
            baseModelJsonGenWithCustomTexture(key, key2, column);
            baseModelJsonGenWithCustomTexture(key + "_horizontal", key2, horizontalColumn);
        }
    }

    public static void EzDoorModelGenerator(String key) {
        String door_bottom_left = """                
                {
                  "parent": "minecraft:block/door_bottom_left",
                  "textures": {
                    "bottom": "$modid:block/$name_bottom",
                    "top": "$modid:block/$name_top"
                  }
                }
                """;
        String door_bottom_right = """
                {
                  "parent": "minecraft:block/door_bottom_right",
                  "textures": {
                    "bottom": "$modid:block/$name_bottom",
                    "top": "$modid:block/$name_top"
                  }
                }
                """;
        String door_top_left = """
                {
                  "parent": "minecraft:block/door_top_left",
                  "textures": {
                    "bottom": "$modid:block/$name_bottom",
                    "top": "$modid:block/$name_top"
                  }
                }
                """;
        String door_top_right = """
                {
                  "parent": "minecraft:block/door_top_right",
                  "textures": {
                    "bottom": "$modid:block/$name_bottom",
                    "top": "$modid:block/$name_top"
                  }
                }
                """;
        String door_top_left_open = """
                {
                  "parent": "minecraft:block/door_top_left_open",
                  "textures": {
                    "bottom": "$modid:block/$name_bottom",
                    "top": "$modid:block/$name_top"
                  }
                }
                """;
        String door_top_right_open = """
                {
                  "parent": "minecraft:block/door_top_right_open",
                  "textures": {
                    "bottom": "$modid:block/$name_bottom",
                    "top": "$modid:block/$name_top"
                  }
                }
                """;
        String door_bottom_left_open = """
                {
                  "parent": "minecraft:block/door_bottom_left_open",
                  "textures": {
                    "bottom": "$modid:block/$name_bottom",
                    "top": "$modid:block/$name_top"
                  }
                }
                """;
        String door_bottom_right_open = """
                {
                  "parent": "minecraft:block/door_bottom_right_open",
                  "textures": {
                    "bottom": "$modid:block/$name_bottom",
                    "top": "$modid:block/$name_top"
                  }
                }
                """;
        baseModelJsonGenWithCustomTexture(key + "_bottom_left_open", key, door_bottom_left_open);
        baseModelJsonGenWithCustomTexture(key + "_top_left_open", key, door_top_left_open);
        baseModelJsonGenWithCustomTexture(key + "_top_right_open", key, door_top_right_open);
        baseModelJsonGenWithCustomTexture(key + "_bottom_right_open", key, door_bottom_right_open);
        baseModelJsonGenWithCustomTexture(key + "_bottom_left", key, door_bottom_left);
        baseModelJsonGenWithCustomTexture(key + "_bottom_right", key, door_bottom_right);
        baseModelJsonGenWithCustomTexture(key + "_top_left", key, door_top_left);
        baseModelJsonGenWithCustomTexture(key + "_top_right", key, door_top_right);

    }

    public static void EzTrapDoorModelGenerator(String key) {
        String trapdoor_bottom = """
                {
                  "parent": "minecraft:block/template_trapdoor_bottom",
                  "textures": {
                    "texture": "$modid:block/$name"
                  }
                }
                """;
        String trapdoor_open = """
                {
                  "parent": "minecraft:block/template_trapdoor_open",
                  "textures": {
                    "texture": "$modid:block/$name"
                  }
                }
                """;
        String trapdoor_top = """
                {
                  "parent": "minecraft:block/template_trapdoor_top",
                  "textures": {
                    "texture": "$modid:block/$name"
                  }
                }
                """;
        baseModelJsonGenWithCustomTexture(key + "_bottom", key, trapdoor_bottom);
        baseModelJsonGenWithCustomTexture(key + "_open", key, trapdoor_open);
        baseModelJsonGenWithCustomTexture(key + "_top", key, trapdoor_top);

    }

    public static void EzButtonModelGenerator(String key, String fullName) {
        String button = """
                {
                    "parent": "minecraft:block/button",
                        "textures": {
                    "texture": "$modid:block/$full_name"
                    }
                }
                """;
        String button_inventory = """
                {
                    "parent": "minecraft:block/button_inventory",
                        "textures": {
                    "texture": "$modid:block/$full_name"
                    }
                }
                """;
        String button_pressed = """
                {
                    "parent": "minecraft:block/button_pressed",
                        "textures": {
                    "texture": "$modid:block/$full_name"
                    }
                }
                """;
        baseModelJsonGenWithFullTexture(key, fullName, button);
        baseModelJsonGenWithFullTexture(key + "_inventory", fullName, button_inventory);
        baseModelJsonGenWithFullTexture(key + "_pressed", fullName, button_pressed);
    }

    public static void EzFenceModelGenerator(String key, String fullName) {
        String fenceInventory = """
                {
                  "parent": "minecraft:block/fence_inventory",
                  "textures": {
                    "texture": "$modid:block/$full_name"
                  }
                }
                """;
        String fencePost = """
                {
                  "parent": "minecraft:block/fence_post",
                  "textures": {
                    "texture": "$modid:block/$full_name"
                  }
                }
                """;
        String fenceSide = """
                {
                  "parent": "minecraft:block/fence_side",
                  "textures": {
                    "texture": "$modid:block/$full_name"
                  }
                }
                """;
        baseModelJsonGenWithFullTexture(key + "_inventory", fullName, fenceInventory);
        baseModelJsonGenWithFullTexture(key + "_post", fullName, fencePost);
        baseModelJsonGenWithFullTexture(key + "_side", fullName, fenceSide);
    }

    public static void EzFenceGateModelGenerator(String key, String fullName) {
        String fenceGate = """
                {
                  "parent": "minecraft:block/template_fence_gate",
                  "textures": {
                    "texture": "$modid:block/$full_name"
                  }
                }
                """;
        String fenceGateOpen = """
                {
                  "parent": "minecraft:block/template_fence_gate_open",
                  "textures": {
                    "texture": "$modid:block/$full_name"
                  }
                }
                """;
        String fenceGateWall = """
                {
                  "parent": "minecraft:block/template_fence_gate_wall",
                  "textures": {
                    "texture": "$modid:block/$full_name"
                  }
                }
                """;
        String fenceGateWallOpen = """
                {
                  "parent": "minecraft:block/template_fence_gate_wall_open",
                  "textures": {
                    "texture": "$modid:block/$full_name"
                  }
                }
                """;
        baseModelJsonGenWithFullTexture(key, fullName, fenceGate);
        baseModelJsonGenWithFullTexture(key + "_open", fullName, fenceGateOpen);
        baseModelJsonGenWithFullTexture(key + "_wall", fullName, fenceGateWall);
        baseModelJsonGenWithFullTexture(key + "_wall_open", fullName, fenceGateWallOpen);
    }

    public static void EzWallModelGenerator(String key, String fullName) {
        String wall_inventory = """
                {
                  "parent": "minecraft:block/wall_inventory",
                  "textures": {
                    "wall": "$modid:block/$full_name"
                  }
                }
                """;
        String wall_post = """
                {
                  "parent": "minecraft:block/template_wall_post",
                  "textures": {
                    "wall": "$modid:block/$full_name"
                  }
                }
                """;
        String wall_side = """
                {
                  "parent": "minecraft:block/template_wall_side",
                  "textures": {
                    "wall": "$modid:block/$full_name"
                  }
                }
                """;
        String wall_side_tall = """
                {
                  "parent": "minecraft:block/template_wall_side_tall",
                  "textures": {
                    "wall": "$modid:block/$full_name"
                  }
                }
                """;
        baseModelJsonGenWithFullTexture(key + "_inventory", fullName, wall_inventory);
        baseModelJsonGenWithFullTexture(key + "_post", fullName, wall_post);
        baseModelJsonGenWithFullTexture(key + "_side", fullName, wall_side);
        baseModelJsonGenWithFullTexture(key + "_side_tall", fullName, wall_side_tall);
    }

    public static void EzPressurePlateModelGenerator(String key, String fullName) {
        String notPressed = """
                {
                  "parent": "minecraft:block/pressure_plate_up",
                  "textures": {
                    "texture": "$modid:block/$full_name"
                  }
                }
                """;
        String pressed = """
                {
                  "parent": "minecraft:block/pressure_plate_down",
                  "textures": {
                    "texture": "$modid:block/$full_name"
                  }
                }
                """;
        baseModelJsonGenWithFullTexture(key, fullName, notPressed);
        baseModelJsonGenWithFullTexture(key + "_down", fullName, pressed);
    }

    public static void EzCarpetModelGenerator(String key, String fullName) {
        String blockContentString = """
                {
                  "parent": "minecraft:block/carpet",
                  "textures": {
                    "wool": "$modid:block/$full_name"
                  }
                }
                """;
        baseModelJsonGenWithFullTexture(key, fullName, blockContentString);
    }

    //ItemModels
    public static void EzItemModelGenerator(String key, String suffix) {
        Path itemModelPath = Paths.get(EzLib.getModId() + File.separator + "models" + File.separator + "item" + File.separator + key + ".json");
        Path path = resourcePath.resolve(itemModelPath);
        String blockStateContent = """
                {
                  "parent": "$modid:block/$name"
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key + suffix);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void EzFlatItemModelGenerator(String key) {
        Path itemModelPath = Paths.get(EzLib.getModId() + File.separator + "models" + File.separator + "item" + File.separator + key + ".json");
        Path path = resourcePath.resolve(itemModelPath);
        String blockStateContent = """
                {
                    "parent": "minecraft:item/generated",
                        "textures": {
                    "layer0": "$modid:item/$name"
                  }
                }
                """.replace("$modid", EzLib.getModId()).replace("$name", key);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockStateContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //BaseJsonGenerator -> this makes it way easyer to create the other methods...
    public static void baseModelJsonGenWithFullTexture(String key, String fullName, String blockModelContentString) {
        Path modelPath = Paths.get(EzLib.getModId() + File.separator + "models" + File.separator + "block" + File.separator + key + ".json");
        Path path = resourcePath.resolve(modelPath);
        String blockModelContent = blockModelContentString.replace("$modid", EzLib.getModId()).replace("$name", key).replace("$full_name", fullName);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockModelContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void baseModelJsonGenWithCustomTexture(String key, String key2, String blockModelContentString) {
        Path modelPath = Paths.get(EzLib.getModId() + File.separator + "models" + File.separator + "block" + File.separator + key + ".json");
        Path path = resourcePath.resolve(modelPath);
        String blockModelContent = blockModelContentString.replace("$modid", EzLib.getModId()).replace("$name", key2);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(blockModelContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
