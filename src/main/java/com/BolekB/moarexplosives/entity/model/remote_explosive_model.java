package com.BolekB.moarexplosives.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class remote_explosive_model<R extends Entity> extends EntityModel<R> {
    private final ModelRenderer bone;
    private final ModelRenderer cube_r1;

    public remote_explosive_model() {
        texWidth = 64;
        texHeight = 64;

        bone = new ModelRenderer(this);
        bone.setPos(8.0F, 24.0F, -8.0F);
        bone.texOffs(0, 0).addBox(-11.5F, -2.0F, 3.0F, 7.0F, 2.0F, 10.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(-8.0F, 0.0F, 8.0F);
        bone.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.3927F, 0.0F);
        cube_r1.texOffs(0, 0).addBox(1.0F, -3.0F, 3.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r1.texOffs(1, 12).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 1.0F, 7.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
