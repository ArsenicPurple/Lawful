package co.basin.lawfulmod.core.util;

import net.minecraft.util.math.vector.Vector3i;

public class UtilArrays {
    public static final Vector3i[] ANCHOR_OFFSETS = {
            new Vector3i(2, 2, 2),
            new Vector3i(2, 2, -2),
            new Vector3i(-2, 2, -2),
            new Vector3i(-2, 2, 2),
    };

    public static final Vector3i[] CRYING_OFFSETS = {
            new Vector3i(2, 0, 2),
            new Vector3i(2, 1, 2),
            new Vector3i(2, 0, -2),
            new Vector3i(2, 1, -2),
            new Vector3i(-2, 0, -2),
            new Vector3i(-2, 1, -2),
            new Vector3i(-2, 0, 2),
            new Vector3i(-2, 1, 2),
    };
}
