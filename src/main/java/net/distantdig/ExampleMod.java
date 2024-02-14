package net.distantdig;

import net.distantdig.block.EzBlocks;
import net.distantdig.effect.EzEffect;
import net.distantdig.effect.EzEffects;
import net.distantdig.enums.EzArmorMaterials;
import net.distantdig.enums.EzToolsMaterials;
import net.distantdig.item.EzItemGroups;
import net.distantdig.item.EzItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

public class ExampleMod extends EzLib {

    public ExampleMod() {
        super("other_mod");
    }

    @Override
    public void registerModItems() {
        // Simple Items
        Item INGOT_ONE = EzItems.registerItem("ingot_one", Item::new, new FabricItemSettings());
        Item INGOT_TWO = EzItems.registerItem("ingot_two", Item::new, new FabricItemSettings());
        Item INGOT_THREE = EzItems.registerItem("ingot_three", Item::new, new FabricItemSettings());
        Item INGOT_FOUR = EzItems.registerItem("ingot_four", Item::new, new FabricItemSettings());
        Item INGOT_FIVE = EzItems.registerItem("ingot_five", Item::new, new FabricItemSettings());

        // Tool Sets
        EzItems.ToolSet IRON_TOOL_SET_2 = EzItems.registerToolSet("iron2", EzToolsMaterials.TEST_MATERIAL1, 3, 1.6f, new FabricItemSettings());
        EzItems.ToolSet IRON_TOOL_SET_3 = EzItems.registerToolSet("iron3", EzToolsMaterials.TEST_MATERIAL2, 3, 1.6f, new FabricItemSettings(), IRON_TOOL_SET_2);
        EzItems.ToolSet IRON_TOOL_SET_4 = EzItems.registerToolSet("iron4", EzToolsMaterials.TEST_MATERIAL3, 3, 1.6f, new FabricItemSettings(), IRON_TOOL_SET_3);

        // Armor Sets
        EzItems.ArmorSet IRON_ARMOR_SET_2 = EzItems.registerArmorSet("iron2", EzArmorMaterials.TEST_MATERIAL1, new FabricItemSettings());
        EzItems.ArmorSet IRON_ARMOR_SET_3 = EzItems.registerArmorSet("iron3", EzArmorMaterials.TEST_MATERIAL1, new FabricItemSettings(), IRON_ARMOR_SET_2);
        EzItems.ArmorSet IRON_ARMOR_SET_4 = EzItems.registerArmorSet("iron4", EzArmorMaterials.TEST_MATERIAL1, new FabricItemSettings(), IRON_ARMOR_SET_3);

        // Food Items
        Item NOT_STEAK1 = EzItems.registerFoodItem("not_steak1", new FoodProperties.Builder().nutrition(8).build(), new FabricItemSettings());
        Item NOT_STEAK2 = EzItems.registerFoodItem("not_steak2", 8, 2, new FabricItemSettings());

        // Fuel Items
        Item NOT_COAL = EzItems.registerFuelItem("not_coal", 200, new FabricItemSettings());
    }

    @Override
    public void registerModBlocks() {
        //woodset
        EzBlocks.registerWoodSet("mahogany", Blocks.OAK_PLANKS, false);

        //rockset
        EzBlocks.registerStoneSet("rocky", Blocks.STONE, true, true, true);

        //metalset
        EzBlocks.registerButton("tritanium", Blocks.NETHERITE_BLOCK, 3);
        EzBlocks.registerPressurePlate("tritanium", Blocks.NETHERITE_BLOCK, 3);

        //woolset
        EzBlocks.registerBlock("turqoise_wool", Blocks.WHITE_WOOL);
        EzBlocks.registerCarpet("turqoise", Blocks.WHITE_WOOL);
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
