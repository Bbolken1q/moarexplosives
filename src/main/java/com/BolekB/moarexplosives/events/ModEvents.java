package com.BolekB.moarexplosives.events;

import com.BolekB.moarexplosives.entity.custom.remote_explosive;
import com.BolekB.moarexplosives.item.ModItems;
import com.BolekB.moarexplosives.moarexplosives;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = moarexplosives.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCall(PlayerInteractEvent.RightClickItem event)
    {
        PlayerEntity player = event.getPlayer();
        World world = player.level;
        if(player.level instanceof ServerWorld){
            if(player.getMainHandItem().getItem() == ModItems.REMOTE_TRIGGER.get()){
                List<Entity> remote_explosives = world.getLoadedEntitiesOfClass(remote_explosive.class, player.getBoundingBox().inflate(128.0D));
                for (Entity entity: remote_explosives) {
                    if (entity.getPersistentData().getString("UserID").matches(player.getStringUUID())) {
                        world.explode(null, entity.blockPosition().getX(), entity.blockPosition().getY(), entity .blockPosition().getZ(), 4f, Explosion.Mode.NONE);
                    }
                }
            }
        }
    }

}
