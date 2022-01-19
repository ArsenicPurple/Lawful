package co.basin.lawfulmod.client.render.tileentitiy;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.client.render.model.CovenantPedestalModel;
import co.basin.lawfulmod.common.tileentities.CovenantPedestalTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class CovenantPedestalTileEntityRenderer extends GeoBlockRenderer<CovenantPedestalTileEntity> {
    private final Minecraft minecraft = Minecraft.getInstance();

    public CovenantPedestalTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new CovenantPedestalModel());
    }

    @Override
    public RenderType getRenderType(CovenantPedestalTileEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(new ResourceLocation(LawfulMod.MOD_ID,"textures/blocks/covenant_pedestal.png"));
    }

    @Override
    public void render(CovenantPedestalTileEntity tile, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(tile, partialTicks, stack, bufferIn, packedLightIn);
        if (!tile.isRemoved()) {
            stack.pushPose();

            // Shrinks the item and raises it to the top of the block.
            stack.scale(0.5f, 0.5f, 0.5f);
            stack.translate(1, 1.9, 1);

            if (!minecraft.isPaused()) {
                stack.translate(0, 6 / 16f, 0);

                // Spins the item
                float totalTicks = tile.getLevel().getGameTime() + partialTicks;
                float angle = (totalTicks) % 360f;
                Quaternion rotation = Vector3f.YP.rotationDegrees(angle);

                // Bobs the item up and down
                float height = 0;
                if (tile.isRitualStarted()) {
                    height = tile.getRitualStage() - 0.05f;
                } else {
                    height = MathHelper.sin(totalTicks / 10) / 10;
                }
                stack.translate(0, height, 0);

                stack.mulPose(rotation);
            }

            ItemStack itemStack = tile.getRitualItem();
            if (itemStack != null) {
                minecraft.getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.FIXED, packedLightIn, 0, stack, bufferIn);
            }
            stack.popPose();
        }
    }
}
