package com.BolekB.moarexplosives.entity.render;

import com.BolekB.moarexplosives.entity.custom.remote_explosive;
import com.BolekB.moarexplosives.entity.model.remote_explosive_model;
import com.BolekB.moarexplosives.moarexplosives;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class remote_explosive_renderer extends MobRenderer<remote_explosive, remote_explosive_model<remote_explosive>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(moarexplosives.MOD_ID, "textures/item/remote_explosive.png");

    public remote_explosive_renderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new remote_explosive_model<>(),0.2f);

    }

    @Override
    public ResourceLocation getTextureLocation(remote_explosive p_110775_1_) {
        return TEXTURE;
    }


}
