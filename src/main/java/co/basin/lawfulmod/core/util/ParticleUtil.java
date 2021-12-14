package co.basin.lawfulmod.core.util;

import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.server.ServerWorld;

public class ParticleUtil {
    public static void spawnParticles(ServerWorld world, BasicParticleType particle, double x, double y, double z, int count) {
        world.sendParticles(particle, x, y, z, count,0,0,0, 0.1);
    }

    public static void spawnParticles(ServerWorld world, BasicParticleType particle, double x, double y, double z, int count, double speed) {
        world.sendParticles(particle, x, y, z, count,0,0,0, speed);
    }

    public static void spawnStillParticles(ClientWorld world, BasicParticleType particle, double x, double y, double z) {
        world.addParticle(particle, x, y, z, 0.0D, 0.0D, 0.0D);
    }
}
