package net.distantdig.ezLib.effect;

import net.minecraft.world.effect.AttackDamageMobEffect;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.slf4j.LoggerFactory;

public class EzEffect {

    public static class NormalEffect extends MobEffect {
        private Boolean regen = false;
        private int regenAmount;
        private Boolean poison = false;
        private int poisonAmount;
        private Boolean wither = false;
        private int witherAmount;
        private Boolean hunger = false;
        private int hungerAmount;

        public NormalEffect(MobEffectCategory mobEffectCategory, int i) {
            super(mobEffectCategory, i);
        }

        @Override
        public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
            LoggerFactory.getLogger("ez-lib").info(this.regen.toString());
            if (this.regen) {
                if (livingEntity.getHealth() < livingEntity.getMaxHealth()) {
                    livingEntity.heal((float) this.regenAmount);
                }
            }
            LoggerFactory.getLogger("ez-lib").info(this.poison.toString());
            if (this.poison) {
                if (livingEntity.getHealth() > 1.0F) {
                    livingEntity.hurt(livingEntity.damageSources().magic(),(float) this.poisonAmount);
                }
            }
            LoggerFactory.getLogger("ez-lib").info(this.wither.toString());
            if (this.wither) {
                livingEntity.hurt(livingEntity.damageSources().wither(),(float) this.witherAmount);
            }
            if (this.hunger && livingEntity instanceof Player) {
                ((Player)livingEntity).causeFoodExhaustion(0.005F * (float)(amplifier + hungerAmount));
            }
            super.applyEffectTick(livingEntity, amplifier);
        }

        public MobEffect regeneration(int amount) {
            this.regen = true;
            this.regenAmount = amount;
            return this;
        }
        public MobEffect poison(int amount) {
            this.poison = true;
            this.poisonAmount = amount;
            return this;
        }
        public MobEffect wither(int amount) {
            this.wither = true;
            this.witherAmount = amount;
            return this;
        }
        public MobEffect hunger(int amount) {
            this.hunger = true;
            this.hungerAmount = amount;
            return this;
        }
    }

    public static class AttackDamageEffect extends AttackDamageMobEffect {
        public AttackDamageEffect(MobEffectCategory mobEffectCategory, int i, double d) {
            super(mobEffectCategory, i, d);
        }
    }

    public static class InstantEffect extends InstantenousMobEffect {
        private Boolean heal = false;
        private int healAmount;
        private Boolean harm = false;
        private int harmAmount;
        private Boolean saturation = false;
        private int saturationAmount;

        public InstantEffect(MobEffectCategory mobEffectCategory, int i) {
            super(mobEffectCategory, i);
        }

        @Override
        public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
            if (this.saturation && livingEntity instanceof Player) {
                if (!livingEntity.level().isClientSide) {
                    ((Player)livingEntity).getFoodData().eat(amplifier + 1,(float) this.saturationAmount);
                }
            }
            if ((this.harm && !livingEntity.isInvertedHealAndHarm()) || (this.heal && livingEntity.isInvertedHealAndHarm())) {
                livingEntity.hurt(livingEntity.damageSources().magic(), (float) (this.harmAmount << amplifier));
            }
            if ((this.heal && !livingEntity.isInvertedHealAndHarm()) || (this.harm && livingEntity.isInvertedHealAndHarm())) {
                livingEntity.heal((float) Math.max(this.healAmount << amplifier, 0));
            }
            super.applyEffectTick(livingEntity, amplifier);
        }

        public MobEffect heal(int amount) {
            if(!this.harm) {
                this.heal = true;
                this.healAmount = amount;
            }
            return this;
        }
        public MobEffect harm(int amount) {
            if(!this.heal) {
                this.harm = true;
                this.harmAmount = amount;
            }
            return this;
        }
        public MobEffect saturation(int amount) {
            this.saturation = true;
            this.saturationAmount = amount;
            return this;
        }
    }
}
