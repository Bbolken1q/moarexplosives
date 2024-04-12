package com.BolekB.moarexplosives.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class plate_carrier_item extends ArmorItem {
    public plate_carrier_item(IArmorMaterial iArmorMaterial, EquipmentSlotType slotType, Properties properties) {
        super(iArmorMaterial, slotType, properties);
    }

    @Override
    public void onCraftedBy(ItemStack itemStack, World p_77622_2_, PlayerEntity p_77622_3_) {
        this.setDamage(itemStack, 15);
    }
}
