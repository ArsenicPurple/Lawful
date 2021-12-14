package co.basin.lawfulmod.client.render.entity;

import co.basin.lawfulmod.client.render.model.LukkiModel;
import co.basin.lawfulmod.common.entities.LukkiEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LukkiRenderer extends GeoEntityRenderer<LukkiEntity> {
    public LukkiRenderer(EntityRendererManager renderManager) { super(renderManager, new LukkiModel()); }
}
