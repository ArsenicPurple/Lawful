package co.basin.lawfulmod.client.render.tileentitiy;

import co.basin.lawfulmod.common.tileentities.CovenantPedestalTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CovenantPedestalTileEntityRenderer extends TileEntityRenderer<CovenantPedestalTileEntity> {
    private final Minecraft minecraft = Minecraft.getInstance();

    public CovenantPedestalTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public boolean shouldRenderOffScreen(CovenantPedestalTileEntity tileEntity) {
        return tileEntity.isRitualStarted();
    }

    /**
     * Renders the ItemStack contained within the TileEntity.
     */
    @Override
    public void render(CovenantPedestalTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        if (!tileEntity.isRemoved()) {
            matrixStack.pushPose();

            // Shrinks the item and raises it to the top of the block.
            matrixStack.scale(0.5f, 0.5f, 0.5f);
            matrixStack.translate(1, 1.9, 1);

            if (!minecraft.isPaused()) {
                matrixStack.translate(0, 6 / 16f, 0);

                // Spins the item
                float totalTicks = tileEntity.getLevel().getGameTime() + partialTicks;
                float angle = (totalTicks) % 360f;
                Quaternion rotation = Vector3f.YP.rotationDegrees(angle);

                // Bobs the item up and down
                float height = 0;
                if (tileEntity.isRitualStarted()) {
                    height = tileEntity.getRitualStage() - 0.05f;
                } else {
                    height = MathHelper.sin(totalTicks / 10) / 10;
                }
                matrixStack.translate(0, height,0);

                matrixStack.mulPose(rotation);
            }

            ItemStack stack = tileEntity.getRitualItem();
            if (stack != null) {
                minecraft.getItemRenderer().renderStatic(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStack, buffer);
            }
            matrixStack.popPose();
        }
    }
}
