package co.basin.lawfulmod.client.render.model;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.tileentities.CovenantPedestalTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CovenantPedestalModel extends AnimatedGeoModel<CovenantPedestalTileEntity> {
    @Override
    public ResourceLocation getModelLocation(CovenantPedestalTileEntity object) {
        return new ResourceLocation(LawfulMod.MOD_ID, "geo/covenant_pedestal.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CovenantPedestalTileEntity object) {
        return new ResourceLocation(LawfulMod.MOD_ID,"textures/blocks/covenant_pedestal.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CovenantPedestalTileEntity animatable) {
        return new ResourceLocation(LawfulMod.MOD_ID, "animations/animation.covenant_pedestal.json");
    }
}
