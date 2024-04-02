package com.BolekB.moarexplosives.events;

import com.BolekB.moarexplosives.entity.ModEntityTypes;
import com.BolekB.moarexplosives.entity.custom.remote_explosive;
import com.BolekB.moarexplosives.moarexplosives;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = moarexplosives.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void AddEntityAttributes(EntityAttributeCreationEvent event)
    {
        event.put(ModEntityTypes.REMOTE_EXPLOSIVE.get(), remote_explosive.setCustomAttributes().build());
    }

}
