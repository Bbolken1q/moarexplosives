package com.BolekB.moarexplosives.item;

import com.BolekB.moarexplosives.item.custom.remote_activator_item;
import com.BolekB.moarexplosives.item.custom.remote_explosive_item;
import com.BolekB.moarexplosives.moarexplosives;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, moarexplosives.MOD_ID);

    public static final RegistryObject<Item> REMOTE_EXPLOSIVE = ITEMS.register("remote_explosive",
            () -> new remote_explosive_item(new Item.Properties().tab(ItemGroup.TAB_TOOLS).stacksTo(1)));

    public static final RegistryObject<Item> PLATE_CARRIER = ITEMS.register("plate_carrier",
            () -> new ArmorItem(ModArmorMaterial.PLATE, EquipmentSlotType.CHEST, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));

    public static final RegistryObject<Item> REMOTE_TRIGGER = ITEMS.register("remote_trigger",
            () -> new remote_activator_item(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}

