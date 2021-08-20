package net.dakuro.arkhesys.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ModEffect extends Effect {

    public ModEffect(EffectType effectType, int color) {
        super(effectType, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if(this == ModPotions.ASTRAAL_EFFECT.get()) {
            if (entity.getHealth() < entity.getMaxHealth()) {
                entity.heal((amplifier+1)*1.0F);
            }
            entity.setAirSupply(entity.getMaxAirSupply());
        } else {
            entity.heal((float) Math.max(4 << amplifier, 0));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 60 == 0;
    }
}
