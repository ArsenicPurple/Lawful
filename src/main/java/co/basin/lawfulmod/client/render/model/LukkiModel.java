package co.basin.lawfulmod.client.render.model;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.entities.LukkiEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LukkiModel extends AnimatedGeoModel<LukkiEntity>
{
	@Override
	public ResourceLocation getModelLocation(LukkiEntity object)
	{
		return new ResourceLocation(LawfulMod.MOD_ID, "geo/lukki.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(LukkiEntity object)
	{
		return new ResourceLocation(LawfulMod.MOD_ID, "textures/entities/lukki.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(LukkiEntity object)
	{
		return new ResourceLocation(LawfulMod.MOD_ID, "animations/animation.lukki.json");
	}
}