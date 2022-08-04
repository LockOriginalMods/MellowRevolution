package com.LockOriginalMods.MellowRevolution.common.registries;

import net.minecraft.commands.arguments.ColorArgument;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;

import static com.LockOriginalMods.MellowRevolution.MellowRevolution.MOD_ID;

public abstract class BaseItem extends Item {
    public static final HashMap<BaseItem, int[]> COLORED_ITEMS = new HashMap<>();

    public BaseItem(Properties properties) {
        super(properties.tab(CreativeModeTab.TAB_MATERIALS));
    }

    public abstract boolean hasContainerItem(ItemStack stack);

    public abstract ItemStack getContainerItem(ItemStack itemStack);

    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    private static class ColorRegisterHandler {

    }
}

