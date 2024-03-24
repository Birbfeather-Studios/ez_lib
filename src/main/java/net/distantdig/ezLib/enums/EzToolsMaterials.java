package net.distantdig.ezLib.enums;

import net.distantdig.ezLib.item.EzItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum EzToolsMaterials implements Tier {
    TEST_MATERIAL1(4, 1000, 4.5f, 2, 25, () -> Ingredient.of(EzItems.getItem("ingot_one"))),
    TEST_MATERIAL2(4, 1000, 4.5f, 2, 25, () -> Ingredient.of(EzItems.getItem("ingot_one"))),
    TEST_MATERIAL3(4, 1000, 4.5f, 2, 25, () -> Ingredient.of(EzItems.getItem("ingot_one")));

    final int miningLevel;
    final int itemDurability;
    final float miningSpeed;
    final float attackDamage;
    final int enchantability;
    final Supplier<Ingredient> repairIngredient;

    EzToolsMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses(){
        return this.itemDurability;
    }

    @Override
    public float getSpeed(){
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamageBonus(){
        return this.attackDamage;
    }

    @Override
    public int getLevel(){
        return this.miningLevel;
    }

    @Override
    public int getEnchantmentValue(){
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient(){
        return this.repairIngredient.get();
    }
}
