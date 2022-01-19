package co.basin.lawfulmod.client.render.entity;

import co.basin.lawfulmod.common.entities.TranscendenceProjectileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class TranscendenceProjectileRenderer extends EntityRenderer<TranscendenceProjectileEntity> {
    private final Minecraft minecraft = Minecraft.getInstance();

    public TranscendenceProjectileRenderer(EntityRendererManager rendererManager) {
        super(rendererManager);
    }

    @Override
    public ResourceLocation getTextureLocation(TranscendenceProjectileEntity entity) {
        return null;
    }

    @Override
    public void render(TranscendenceProjectileEntity entity, float p_225623_2_, float partialTicksMaybe, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entity, p_225623_2_, partialTicksMaybe, stack, bufferIn, packedLightIn);
        if (!entity.isAlive()) {
            stack.pushPose();

            if(!entity.isReturning()) {
                float totalTicks = entity.level.getGameTime() + partialTicksMaybe;
                float angle = (totalTicks) % 360f;
                Quaternion rotation = Vector3f.YP.rotationDegrees(angle);

                stack.mulPose(rotation);
            } else {
                PlayerEntity owner = entity.getOwner();
                Vector3d pos = owner.position();
                Vector3f angle = new Vector3f((float)(pos.x - entity.position().x), (float)(pos.y - entity.position().y), (float)(pos.z - entity.position().z));

                Quaternion rotation = angle.rotation(1f);
                stack.mulPose(rotation);
            }

            ItemStack sword = entity.getSword();
            if (sword != null) {
                minecraft.getItemRenderer().renderStatic(sword, ItemCameraTransforms.TransformType.FIXED, packedLightIn, 0, stack, bufferIn);
            }
            stack.popPose();
        }
    }
}
