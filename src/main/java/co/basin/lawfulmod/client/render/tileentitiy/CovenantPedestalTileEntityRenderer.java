package co.basin.lawfulmod.client.render.tileentitiy;

import co.basin.lawfulmod.common.tileentities.CovenantPedestalTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
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



    //TODO make this code work or something
    @Override
    public void render(CovenantPedestalTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        if (!tileEntity.isRemoved()) {
            matrixStack.pushPose();
            //matrixStack.scale(0.75f, 0.75f, 0.75f);
            matrixStack.translate(0.5, 0.9, 0.5);

            if (!minecraft.isPaused()) {
                matrixStack.translate(0, 6 / 16f, 0);

                float totalTicks = tileEntity.getLevel().getGameTime()+ partialTicks;
                float angle = (totalTicks) % 360f;
                Quaternion rotation = Vector3f.YP.rotationDegrees(angle);

                matrixStack.mulPose(rotation);
            }

            ItemStack stack = tileEntity.getRitualItem(tileEntity);
            if (!stack.isEmpty()) {
                minecraft.getItemRenderer().renderStatic(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStack, buffer);
            }
            matrixStack.popPose();
        }
    }
}
