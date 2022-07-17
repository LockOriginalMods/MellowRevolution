package com.LockOriginalMods.MellowRevolution;

import com.LockOriginalMods.MellowRevolution.common.entity.Villagers.MellowVillagers;
import com.LockOriginalMods.MellowRevolution.common.init.BlockInit;
import com.LockOriginalMods.MellowRevolution.common.init.ItemInit;
import com.LockOriginalMods.MellowRevolution.common.proxy.ClientProxy;
import com.LockOriginalMods.MellowRevolution.common.proxy.CommonProxy;
import com.LockOriginalMods.MellowRevolution.common.world.MellowConfiguredFeatures;
import com.LockOriginalMods.MellowRevolution.common.world.MellowPlacedFeatures;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static com.LockOriginalMods.MellowRevolution.MellowRevolution.MOD_ID;

@Mod(MOD_ID)
public class MellowRevolution
{

    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "mellow_revolution";
    public static MellowRevolution instance;
    public static CommonProxy proxy;

    public MellowRevolution()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemInit.register(eventBus);
        BlockInit.register(eventBus);
        MellowVillagers.register(eventBus);
        eventBus.addListener(this::setup);
        MellowConfiguredFeatures.register(eventBus);
        MellowPlacedFeatures.register(eventBus);
        instance = this;
        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        proxy.start();


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            MellowVillagers.registerPOIs();
        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
    }

    private void processIMC(final InterModProcessEvent event)
    {
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }


}
