package net.distantdig.ezLib.enums;

import net.distantdig.ezLib.EzLib;
import net.distantdig.ezLib.item.EzItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum EzArmorMaterials implements ArmorMaterial {
    TEST_MATERIAL1("test1", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ARMOR_EQUIP_GENERIC, 2f, 0.1f,
            () -> Ingredient.of(EzItems.getItem("ingot_one"))),
    TEST_MATERIAL2("test1", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ARMOR_EQUIP_GENERIC, 2f, 0.1f,
            () -> Ingredient.of(EzItems.getItem("ingot_two"))),
    TEST_MATERIAL3("test1", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ARMOR_EQUIP_GENERIC, 2f, 0.1f,
            () -> Ingredient.of(EzItems.getItem("ingot_three")));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 11, 16, 15, 13 };

    EzArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability,
                     SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return this.BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return EzLib.getModId() + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
