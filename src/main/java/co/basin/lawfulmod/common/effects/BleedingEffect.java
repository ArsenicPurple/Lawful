package co.basin.lawfulmod.common.effects;

import co.basin.lawfulmod.common.damagesources.BleedingDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class BleedingEffect extends Effect {
    public BleedingEffect(EffectType type, int color) {
        super(type, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof SkeletonEntity) { return; }
        entity.hurt(new BleedingDamageSource(), 1);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int ticksToWait = 50 >> amplifier;
        if (ticksToWait > 0) { return duration % ticksToWait == 0; }
        return true;
    }

    @Override
    public boolean isInstantenous() {
        return false;
    }
}
