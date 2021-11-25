package co.basin.lawfulmod.client.render.tileentitiy;

import co.basin.lawfulmod.common.tileentities.CovenantPedestalTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class CovenantPedestalTileEntityRenderer extends TileEntityRenderer<CovenantPedestalTileEntity> {
    private final Minecraft minecraft = Minecraft.getInstance();
    private final ItemRenderer itemRenderer;
    private final EntityRendererManager entityRenderer;
    private final FontRenderer font;

    public CovenantPedestalTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
        this.itemRenderer = minecraft.getItemRenderer();
        this.entityRenderer = minecraft.getEntityRenderDispatcher();
        this.font = minecraft.font;
    }


    @Override
    public void render(CovenantPedestalTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        if (!tileEntity.isRemoved()) {
            matrixStack.pushPose();
            matrixStack.translate(0.5, 1.125, 0.5);
            matrixStack.scale(0.5f, 0.5f, 0.5f);
            matrixStack.translate(0, 0.25, 0);

            if (!minecraft.isPaused()) {
                matrixStack.translate(0, 6 / 16f, 0);
                matrixStack.scale(1.5f, 1.5f, 1.5f);

                float totalTicks = tileEntity.getLevel().getGameTime()+ partialTicks;
                float angle = (totalTicks) % 360f;
                Quaternion rotation = Vector3f.YP.rotationDegrees(angle);

                matrixStack.mulPose(rotation);
            }

            //TODO: make FIXED
            ItemStack stack = tileEntity.getRitualItem(tileEntity);
            itemRenderer.renderStatic(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStack, buffer);
            matrixStack.popPose();
        }
    }
}
