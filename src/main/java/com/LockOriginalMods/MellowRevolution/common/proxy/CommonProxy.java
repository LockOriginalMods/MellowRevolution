package com.LockOriginalMods.MellowRevolution.common.proxy;

import com.LockOriginalMods.MellowRevolution.common.enchant.AutoSmeltEnchants;
import com.LockOriginalMods.MellowRevolution.common.enchant.AutoSmeltLootModifiers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.LockOriginalMods.MellowRevolution.MellowRevolution.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    public void start() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        AutoSmeltEnchants.create(modBus);
        AutoSmeltLootModifiers.create(modBus);
    }
}