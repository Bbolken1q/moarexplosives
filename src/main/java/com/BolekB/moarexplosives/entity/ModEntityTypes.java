package com.BolekB.moarexplosives.entity;

import com.BolekB.moarexplosives.entity.custom.remote_explosive;
import com.BolekB.moarexplosives.moarexplosives;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, moarexplosives.MOD_ID);

    public static final RegistryObject<EntityType<remote_explosive>> REMOTE_EXPLOSIVE =
            ENTITY_TYPES.register("remote_explosive", () ->EntityType.Builder.
            of(remote_explosive::new, EntityClassification.MISC).sized(0.6f, 0.3f).
            build(new ResourceLocation(moarexplosives.MOD_ID, "remote_explosive").toString()));

    public static void register(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }
}
