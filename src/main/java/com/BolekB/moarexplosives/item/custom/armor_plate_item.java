package com.BolekB.moarexplosives.item.custom;

import com.BolekB.moarexplosives.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class armor_plate_item extends Item {

    public armor_plate_item(Properties properties) {
        super(properties);
    }

    @Mod.EventBusSubscriber
    public static class OnRightClick
    {
        @SubscribeEvent
        public static void onPlayerUse(PlayerInteractEvent.RightClickItem event)
        {
            PlayerEntity player =event.getPlayer();
            ItemStack itemStack = event.getItemStack();
            ItemStack armorItem = player.getItemBySlot(EquipmentSlotType.CHEST);
            if (player.getItemBySlot(EquipmentSlotType.CHEST).sameItem(ModItems.PLATE_CARRIER.get().getDefaultInstance())) {
                if(player.getMainHandItem().sameItem(ModItems.ARMOR_PLATE.get().getDefaultInstance())) {
                    if(armorItem.getDamageValue() >= 5) {
                        itemStack.shrink(1);
                        armorItem.setDamageValue(armorItem.getDamageValue() - 5);
                    }
                }
            }
        }
    }
}
