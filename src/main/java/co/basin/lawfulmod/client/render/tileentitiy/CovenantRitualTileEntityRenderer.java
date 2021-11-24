package co.basin.lawfulmod.client.render.tileentitiy;

import co.basin.lawfulmod.common.tileentities.CovenantRitualTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CovenantRitualTileEntityRenderer extends TileEntityRenderer<CovenantRitualTileEntity> {
    private final Minecraft minecraft = Minecraft.getInstance();
    private final ItemRenderer itemRenderer;
    private final EntityRendererManager entityRenderer;
    private final FontRenderer font;

    public CovenantRitualTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
        this.itemRenderer = minecraft.getItemRenderer();
        this.entityRenderer = minecraft.getEntityRenderDispatcher();
        this.font = minecraft.font;
    }


    @Override
    public void render(CovenantRitualTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        if (!tileEntity.isRemoved()) {
            matrixStack.pushPose();
            matrixStack.translate(0.5, 1.125, 0.5);
            matrixStack.scale(0.5f, 0.5f, 0.5f);
            matrixStack.translate(0, 0.25, 0);

            if (!minecraft.isPaused()) {
                matrixStack.translate(0, 6 / 16f, 0);
                matrixStack.scale(1.5f, 1.5f, 1.5f);

                //BlockPos blockpos = tileEntity.getPos();
                //long blockoffset = (long) (blockpos.getX() * 7 + blockpos.getY() * 9 + blockpos.getZ() * 13);

                //long time = System.currentTimeMillis();
                //float tt = tileEntity.getWorld().getGameTime()+ partialTicks;
                float tt = tileEntity.counter + partialTicks;


                //long t = blockoffset + time;
                float angle = (tt * (float) ClientConfigs.cached.PEDESTAL_SPEED) % 360f;
                Quaternion rotation = Vector3f.YP.rotationDegrees(angle);

                matrixStack.mulPose(rotation);
            }

            //TODO: make FIXED
            ItemStack stack = tileEntity.getDisplayedItem();
            if (CommonUtil.FESTIVITY.isAprilsFool()) stack = new ItemStack(Items.DIRT);
            itemRenderer.renderStatic(stack, transform, combinedLightIn, combinedOverlayIn, matrixStack, bufferIn,0);

            matrixStack.popPose();
        }
    }
}
