package com.BolekB.moarexplosives.item.custom;

import com.BolekB.moarexplosives.entity.ModEntityTypes;
import com.BolekB.moarexplosives.entity.custom.remote_explosive;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class remote_activator_item extends Item {

    public remote_activator_item(Properties properties) {
        super(properties);
    }

    @Mod.EventBusSubscriber
    public static class OnRightClick
    {
        @SubscribeEvent
        public static void onPlayerUse(PlayerInteractEvent.RightClickItem event)
        {
//            System.out.println("***** TEST2 *****");

        }
    }
}
