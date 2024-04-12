package com.BolekB.moarexplosives.item.custom;

import com.BolekB.moarexplosives.entity.ModEntityTypes;
import com.BolekB.moarexplosives.entity.custom.remote_explosive;
import com.BolekB.moarexplosives.item.ModItems;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class remote_explosive_item extends Item {

    public remote_explosive_item(Properties properties) {
        super(properties);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player)
    {
        World world = player.level;
        if ((world instanceof ServerWorld) && (player.getMainHandItem().getItem() == ModItems.REMOTE_EXPLOSIVE.get()))
        {
//            player.sendMessage(new StringTextComponent(player.getMainHandItem().toString()), player.getUUID())
            player.setItemInHand(player.getUsedItemHand(), ItemStack.EMPTY); //Game doesn't set main hand ItemStack to empty for some reason, so I set it manually

            remote_explosive remote_explosive = new remote_explosive(ModEntityTypes.REMOTE_EXPLOSIVE.get(), world);
            remote_explosive.moveTo(player.position().add(player.getLookAngle().x,1D,player.getLookAngle().z));

            remote_explosive.setDeltaMovement(new Vector3d(player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z));

            remote_explosive.finalizeSpawn(((ServerWorld) world).getWorldServer(), world.getCurrentDifficultyAt(remote_explosive.blockPosition()), SpawnReason.MOB_SUMMONED, null, null);
            world.addFreshEntity(remote_explosive);
            remote_explosive.getPersistentData().putString("UserID", player.getUUID().toString());
        }

        return false;
    }
}
