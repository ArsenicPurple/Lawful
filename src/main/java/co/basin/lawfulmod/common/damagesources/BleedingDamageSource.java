package co.basin.lawfulmod.common.damagesources;

import net.minecraft.util.DamageSource;

public class BleedingDamageSource extends DamageSource {
    public BleedingDamageSource() {
        super("lawful.bleeding");
    }

    @Override
    public boolean isBypassArmor() {
        return true;
    }
}
