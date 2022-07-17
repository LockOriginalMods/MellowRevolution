package com.LockOriginalMods.MellowRevolution.common.init;

import com.LockOriginalMods.MellowRevolution.common.registries.ToolItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.LockOriginalMods.MellowRevolution.MellowRevolution.MOD_ID;
import static com.LockOriginalMods.MellowRevolution.common.registries.CreativeTab.MYSTICISM_TAB;

public class ItemInit
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    //tools

    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer",
            ()-> new ToolItem(80));

    public static final RegistryObject<Item> CUTTER = ITEMS.register("cutter",
            ()-> new ToolItem(80));


    public static final RegistryObject<Item> MORTAR = ITEMS.register("mortar",
            ()-> new ToolItem(80));


    //ingot

    public static final RegistryObject<Item> BRASS_INGOT = ITEMS.register("brass_ingot",
            ()-> new Item(new Item.Properties().tab(MYSTICISM_TAB)));

    public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot",
            ()-> new Item(new Item.Properties().tab(MYSTICISM_TAB)));

    //raw

    public static final RegistryObject<Item> RAW_ZINC = ITEMS.register("raw_zinc",
            ()-> new Item(new Item.Properties().tab(MYSTICISM_TAB)));

    //dust

    public static final RegistryObject<Item> ZINC_DUST = ITEMS.register("zinc_dust",
            ()-> new Item(new Item.Properties().tab(MYSTICISM_TAB)));

    public static final RegistryObject<Item> IRON_DUST = ITEMS.register("iron_dust",
            ()-> new Item(new Item.Properties().tab(MYSTICISM_TAB)));

    public static final RegistryObject<Item> BRASS_DUST = ITEMS.register("brass_dust",
            ()-> new Item(new Item.Properties().tab(MYSTICISM_TAB)));

    public static final RegistryObject<Item> COPPER_DUST = ITEMS.register("copper_dust",
            ()-> new Item(new Item.Properties().tab(MYSTICISM_TAB)));

    //plate

    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
            ()-> new Item(new Item.Properties().tab(MYSTICISM_TAB)));

    public static final RegistryObject<Item> BRASS_PLATE = ITEMS.register("brass_plate",
            ()-> new Item(new Item.Properties().tab(MYSTICISM_TAB)));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
