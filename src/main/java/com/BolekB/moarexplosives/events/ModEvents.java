package com.BolekB.moarexplosives.events;

import com.BolekB.moarexplosives.entity.ModEntityTypes;
import com.BolekB.moarexplosives.entity.custom.remote_explosive;
import com.BolekB.moarexplosives.item.ModItems;
import com.BolekB.moarexplosives.moarexplosives;
import com.mrcrayfish.obfuscate.client.event.RenderItemEvent;
import com.tac.guns.entity.ProjectileEntity;
import com.tac.guns.init.ModEffects;
import com.tac.guns.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;

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
                        world.explode(null, entity.blockPosition().getX(), entity.blockPosition().getY(), entity .blockPosition().getZ(), 1f, Explosion.Mode.DESTROY);
                        entity.remove();
                        List<LivingEntity> entitiesInRadius = world.getEntitiesOfClass(PlayerEntity.class, entity.getBoundingBox().inflate(10D));
                        for (Entity players: entitiesInRadius) {
                            PlayerEntity playerentity = (PlayerEntity) players;
                            playerentity.addEffect(new EffectInstance(ModEffects.DEAFENED.get(), 100));
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) { //TODO: timer
        Entity entity = event.getEntity();
        World world = entity.level;
        if (!world.isClientSide()) {
            if (event.getSource().isProjectile()) {
                if (event.getEntity() instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) entity;
                    ItemStack itemStack = player.getItemBySlot(EquipmentSlotType.CHEST);
                    if (player.getItemBySlot(EquipmentSlotType.CHEST).sameItem(ModItems.PLATE_CARRIER.get().getDefaultInstance())) {
                        if (!player.getPersistentData().getBoolean("hasFired")){
                            if (itemStack.getDamageValue()==16)
                            {
                                player.setItemSlot(EquipmentSlotType.CHEST, ItemStack.EMPTY);
                            }
                            else {

                                itemStack.setDamageValue(itemStack.getDamageValue() + 1);
                            }
                        }
                        event.setCanceled(true);
                        player.getPersistentData().putBoolean("hasFired", true);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event)
    {
        if(!event.player.level.isClientSide())
        {
            PlayerEntity player = event.player;
            player.getPersistentData().putBoolean("hasFired", false);
        }
    }
}
