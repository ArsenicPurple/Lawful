package co.basin.lawfulmod.client.render.model;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.entities.MeowlzebubEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MeowlzebubModel extends AnimatedGeoModel<MeowlzebubEntity>
{
    @Override
    public ResourceLocation getModelLocation(MeowlzebubEntity object)
    {
        return new ResourceLocation(LawfulMod.MOD_ID, "geo/meowlzebub.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MeowlzebubEntity object)
    {
        return new ResourceLocation(LawfulMod.MOD_ID, "textures/entities/meowlzebub.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MeowlzebubEntity object)
    {
        return new ResourceLocation(LawfulMod.MOD_ID, "animations/animation.meowlzebub.json");
    }
}