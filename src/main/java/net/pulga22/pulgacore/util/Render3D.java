package net.pulga22.pulgacore.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.pulga22.pulgacore.blockentity.HorizontalFacingBlockEntity;
import net.pulga22.pulgacore.util.render3d.MatricesInfo;
import org.joml.Matrix4f;

public abstract class Render3D {

    /**
     * Render a quad facing in the direction which the block is facing.
     * @param entity The BlockEntity
     * @param matrices The MatrixStack of the render method.
     * @param identifier The identifier of the texture.
     * @param translate The translation of the quad.
     * @param scale The scale of the quad.
     * @param <T> Extends HorizontalFacingBlockEntity
     * @see net.pulga22.pulgacore.blockentity.HorizontalFacingBlockEntity
     */
    public static <T extends HorizontalFacingBlockEntity> void renderRotatedQuad(T entity, MatrixStack matrices, Identifier identifier, MatricesInfo translate, MatricesInfo scale){
        Direction facing = entity.getFacing();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.enableDepthTest();
        RenderSystem.disableCull();
        matrices.push();
        matrices.translate(0.5, 0, 0.5);
        if (facing != null){
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f - MathHelper.getAngleRotation(facing)));
        }
        matrices.translate(translate.x, translate.y, translate.z);
        matrices.scale(scale.x, scale.y, scale.z);
        Matrix4f positionMatrix = matrices.peek().getPositionMatrix();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        buffer.vertex(positionMatrix, 1, 0, 0).texture(0f, 1f).next();
        buffer.vertex(positionMatrix, 1, 1, 0).texture(0f, 0f).next();
        buffer.vertex(positionMatrix, 0, 1, 0).texture(1f, 0f).next();
        buffer.vertex(positionMatrix, 0, 0, 0).texture(1f, 1f).next();
        RenderSystem.setShaderTexture(0, identifier);
        tessellator.draw();
        matrices.pop();
    }

}
