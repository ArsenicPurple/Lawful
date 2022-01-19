package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.entities.LukkiEntity;
import co.basin.lawfulmod.common.entities.MagicProjectileEntity;
import co.basin.lawfulmod.common.entities.MeowlzebubEntity;
import co.basin.lawfulmod.common.entities.TranscendenceProjectileEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, LawfulMod.MOD_ID);

    public static final RegistryObject<EntityType<MeowlzebubEntity>> MEOWLZEBUB = ENTITY_TYPES.register("meowlzebub",
            () -> EntityType.Builder.of(MeowlzebubEntity::new, EntityClassification.MONSTER)
                    .sized(0.5F, 1.6875F)
                    .clientTrackingRange(10)
                    .build("meowlzebub")
    );

    public static final RegistryObject<EntityType<LukkiEntity>> LUKKI = ENTITY_TYPES.register("lukki",
            () -> EntityType.Builder.of(LukkiEntity::new, EntityClassification.MONSTER)
                    .sized(1,1)
                    .clientTrackingRange(20)
                    .build("lukki")
    );

    public static final RegistryObject<EntityType<MagicProjectileEntity>> MAGIC_PROJECTILE = ENTITY_TYPES.register("magic_projectile",
            () -> EntityType.Builder.<MagicProjectileEntity>of(MagicProjectileEntity::new, EntityClassification.MISC)
                    .sized(0.25F, 0.25F)
                    .build("magic_projectile")
    );

    public static final RegistryObject<EntityType<TranscendenceProjectileEntity>> TRANSCENDENCE_ENTITY = ENTITY_TYPES.register("transcendence_projectile",
            () -> EntityType.Builder.<TranscendenceProjectileEntity>of(TranscendenceProjectileEntity::new, EntityClassification.MISC)
                    .sized(1, 1)
                    .build("transcendence_projectile")
    );
}
