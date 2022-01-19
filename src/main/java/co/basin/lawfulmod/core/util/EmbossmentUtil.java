package co.basin.lawfulmod.core.util;

import co.basin.lawfulmod.common.enchantments.Embossment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmbossmentUtil {
    public static EmbossmentUtil instance = new EmbossmentUtil();

    private final Map<Enchantment, Embossment> mappings = new HashMap<>();

    public EmbossmentUtil() {
        for (Enchantment enchantment : ForgeRegistries.ENCHANTMENTS.getValues()) {
            if (enchantment instanceof Embossment) {
                mappings.put(((Embossment) enchantment).getParent(), ((Embossment) enchantment));
            }
        }
    }

    public Embossment getChild(Enchantment enchantment) {
        return mappings.get(enchantment);
    }

    public static <T extends Entity> T getClosestEntity(List<? extends T> entities, Vector3d v) {
        double d0 = -1.0D;
        T t = null;

        for(T t1 : entities) {
            double d1 = t1.distanceToSqr(v.x, v.y, v.z);
            if (d0 == -1.0D || d1 < d0) {
                d0 = d1;
                t = t1;
            }
        }
        return t;
    }

    public static AxisAlignedBB getBoundingBox(Entity entity, int xRange, int yRange) {
        return new AxisAlignedBB(entity.position().add(-0.5 - xRange, -0.5 - yRange, -0.5 - xRange), entity.position().add(0.5 + xRange, 0.5 + yRange, 0.5 + xRange));
    }
}
