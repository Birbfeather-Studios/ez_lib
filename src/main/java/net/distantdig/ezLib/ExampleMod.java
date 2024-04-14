package net.distantdig.ezLib;

import net.distantdig.ezLib.block.EzBlockSets;
import net.distantdig.ezLib.block.EzBlocksBuilder;
import net.distantdig.ezLib.effect.EzEffect;
import net.distantdig.ezLib.effect.EzEffects;
import net.distantdig.ezLib.enums.EzArmorMaterials;
import net.distantdig.ezLib.enums.EzToolsMaterials;
import net.distantdig.ezLib.item.EzItemGroups;
import net.distantdig.ezLib.item.EzItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;

public class ExampleMod extends EzLib {

    public ExampleMod() {
        super("other_mod");
    }

    // Item Pointers
    Item INGOT_ONE = EzItems.registerItem("ingot_one", Item::new, new FabricItemSettings());
    Item INGOT_TWO;
    Item INGOT_THREE;
    Item INGOT_FOUR;
    Item INGOT_FIVE;

    Item NOT_COAL;
    Item NOT_STEAK1;
    Item NOT_STEAK2;

    @Override
    public void registerModItems() {
        // Simple Items
        INGOT_TWO = EzItems.registerItem("ingot_two", Item::new, new FabricItemSettings());
        INGOT_THREE = EzItems.registerItem("ingot_three", Item::new, new FabricItemSettings());
        INGOT_FOUR = EzItems.registerItem("ingot_four", Item::new, new FabricItemSettings());
        INGOT_FIVE = EzItems.registerItem("ingot_five", Item::new, new FabricItemSettings());

        // Tool Sets
        EzItems.ToolSet IRON_TOOL_SET_2 = EzItems.registerToolSet("iron2", EzToolsMaterials.TEST_MATERIAL1, 3, 1.6f, new FabricItemSettings());
        EzItems.ToolSet IRON_TOOL_SET_3 = EzItems.registerToolSet("iron3", EzToolsMaterials.TEST_MATERIAL2, 3, 1.6f, new FabricItemSettings(), IRON_TOOL_SET_2);
        EzItems.ToolSet IRON_TOOL_SET_4 = EzItems.registerToolSet("iron4", EzToolsMaterials.TEST_MATERIAL3, 3, 1.6f, new FabricItemSettings(), IRON_TOOL_SET_3);

        // Armor Sets
        EzItems.ArmorSet IRON_ARMOR_SET_2 = EzItems.registerArmorSet("iron2", EzArmorMaterials.TEST_MATERIAL1, new FabricItemSettings());
        EzItems.ArmorSet IRON_ARMOR_SET_3 = EzItems.registerArmorSet("iron3", EzArmorMaterials.TEST_MATERIAL1, new FabricItemSettings(), IRON_ARMOR_SET_2);
        EzItems.ArmorSet IRON_ARMOR_SET_4 = EzItems.registerArmorSet("iron4", EzArmorMaterials.TEST_MATERIAL1, new FabricItemSettings(), IRON_ARMOR_SET_3);

        // Food Items
        NOT_STEAK1 = EzItems.registerFoodItem("not_steak1", new FoodProperties.Builder().nutrition(8).build(), new FabricItemSettings());
        NOT_STEAK2 = EzItems.registerFoodItem("not_steak2", 8, 2, new FabricItemSettings());

        // Fuel Items
        NOT_COAL = EzItems.registerFuelItem("not_coal", 200, new FabricItemSettings());
    }

    @Override
    public void registerModBlocks() {
        new EzBlocksBuilder("not_andesite", Blocks.COAL_ORE, null, EzBlocksBuilder.EzMaterial.stone)
                .makeOre(EzBlocksBuilder.stoneReplacables, 64, 1,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)), NOT_COAL)
                .pickaxe()
                .stoneTool();

        new EzBlocksBuilder("not_coal_ore", Blocks.COAL_ORE, null, EzBlocksBuilder.EzMaterial.stone)
                .makeOre(EzBlocksBuilder.stoneReplacables, 12, 12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)), NOT_COAL)
                .pickaxe()
                .stoneTool();

        new EzBlocksBuilder("not_iron_ore", Blocks.IRON_ORE, null, EzBlocksBuilder.EzMaterial.stone)
                .makeOre(EzBlocksBuilder.stoneReplacables, 12, 12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)), INGOT_ONE)
                .pickaxe()
                .stoneTool();

        new EzBlocksBuilder("unobtainable", Blocks.STONE, null, EzBlocksBuilder.EzMaterial.stone)
                .stair()
                .slab()
                .verticalSlab()
                .extraBlock("cut_","",null)
                .extraBlock("chiseled_","",null)
                .pillar("ornate_", "_pillar", null)
                .pillar("broad_","_pillar", null)
                .button(BlockSetType.OAK, 30, true)
                .pressurePlate(BlockSetType.OAK,PressurePlateBlock.Sensitivity.MOBS)
                .wall()
                .trapdoor(BlockSetType.OAK, Blocks.OAK_TRAPDOOR)
                .door(BlockSetType.OAK, Blocks.OAK_DOOR)
                .fence()
                .fenceGate()
                .pickaxe();
        //slab,stair, door, trapdoor, button, pressureplate, fence, fencegate, wall, pillar, logsAndWoods
        //needs to be able to make additional blocks, like leaves
        //needs to be able to have different minables, such as axes for wood, pickaxes for stone, and shears for wool

        //need seperate builder for plants, such as two block plants, plants like sugarcane or bamboo and growable samplings or just simple grass/mushrooms etc.

        //woodset
        new EzBlockSets().woodSet("mahogany", true, Blocks.OAK_SAPLING);
        //you will need to register the sapling block seperately because this requires you to create a custom tree.
        //EzBlocks.registerWoodSet("mahogany", Blocks.OAK_PLANKS, false);

        //rockset
        new EzBlockSets().advancedStoneSet("chalk");
        //EzBlocks.registerStoneSet("chalk", Blocks.STONE, true, true, true);

        //metalset
        new EzBlocksBuilder("ketal", Blocks.IRON_BLOCK, "", EzBlocksBuilder.EzMaterial.metal).pickaxe().stoneTool();

        //woolset
        new EzBlocksBuilder("turqoise", Blocks.WHITE_WOOL, "_wool", EzBlocksBuilder.EzMaterial.wool).carpet(Blocks.WHITE_CARPET);

        //iceset
        new EzBlocksBuilder("mice", Blocks.ICE, "", EzBlocksBuilder.EzMaterial.ice).pickaxe();

        //sandset
        new EzBlocksBuilder("zand", Blocks.SAND, "", EzBlocksBuilder.EzMaterial.sand).shovel();

        //dirtset
        new EzBlocksBuilder("birt", Blocks.DIRT, "", EzBlocksBuilder.EzMaterial.dirt).shovel();
    }

    @Override
    public void registerModEffects() {
        MobEffect NOT_SLOWNESS = EzEffects.registerEffect("not_slowness",
                new EzEffect.NormalEffect(MobEffectCategory.HARMFUL, 9154528)
                        .addAttributeModifier(Attributes.MOVEMENT_SPEED,"7107DE5E-7CE8-4030-940E-514C1F160891",-0.15000000596046448, AttributeModifier.Operation.MULTIPLY_TOTAL));
        MobEffect BETTER_STRENGTH = EzEffects.registerEffect("better_strength",
                new EzEffect.AttackDamageEffect(MobEffectCategory.BENEFICIAL, 16762624, 1.1)
                        .addAttributeModifier(Attributes.ATTACK_DAMAGE,"648D7064-6A60-4F59-8ABE-C2C23A6DD7A9",0.0, AttributeModifier.Operation.MULTIPLY_TOTAL));
        MobEffect WORSE_HEAL = EzEffects.registerEffect("worse_heal",
                new EzEffect.InstantEffect(MobEffectCategory.BENEFICIAL, 16262179)
                        .heal(1));
        MobEffect WORSE_HARM = EzEffects.registerEffect("worse_harm",
                new EzEffect.InstantEffect(MobEffectCategory.HARMFUL, 11101546)
                        .harm(1));
        MobEffect BETTER_POISON = EzEffects.registerEffect("better_poison",
                new EzEffect.NormalEffect(MobEffectCategory.HARMFUL, 8889187)
                        .poison(2));
        MobEffect BETTER_WITHER = EzEffects.registerEffect("better_wither",
                new EzEffect.NormalEffect(MobEffectCategory.HARMFUL, 7561558)
                        .wither(2));
        MobEffect BETTER_REGENERATION = EzEffects.registerEffect("better_regeneration",
                new EzEffect.NormalEffect(MobEffectCategory.BENEFICIAL, 13458603)
                        .regeneration(2));
    }

    @Override
    public void registerModGroup() {
        EzItemGroups.registerItemGroup(EzItems.getItem("ingot_one"));
    }
}
