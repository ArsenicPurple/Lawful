package co.basin.lawfulmod.client.render.entity;

import co.basin.lawfulmod.client.render.model.MeowlzebubModel;
import co.basin.lawfulmod.common.entities.MeowlzebubEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MeowlzebubRenderer extends GeoEntityRenderer<MeowlzebubEntity> {
    public MeowlzebubRenderer(EntityRendererManager renderManager) {
        super(renderManager, new MeowlzebubModel());
    }
}
