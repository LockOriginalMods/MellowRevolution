package com.LockOriginalMods.MellowRevolution;

import com.LockOriginalMods.MellowRevolution.common.block.Kiln.KilnScreen;
import com.LockOriginalMods.MellowRevolution.common.entity.Villagers.MellowVillagers;
import com.LockOriginalMods.MellowRevolution.common.init.BlockInit;
import com.LockOriginalMods.MellowRevolution.common.init.ItemInit;
import com.LockOriginalMods.MellowRevolution.common.world.MellowConfiguredFeatures;
import com.LockOriginalMods.MellowRevolution.common.world.MellowPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.LockOriginalMods.MellowRevolution.MellowRevolution.MOD_ID;

@Mod(MOD_ID)
public class MellowRevolution
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mellow_revolution";
    public static MellowRevolution instance;

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


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

        event.enqueueWork(() -> {
            MellowVillagers.registerPOIs();
        });

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientMellowEvents{
        @SubscribeEvent

        public static void onClientSetup(FMLClientSetupEvent event) {

            ItemBlockRenderTypes.setRenderLayer(BlockInit.FLAX_CROP.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BlockInit.CORN_CROP.get(), RenderType.cutout());
            MenuScreens.register(BlockInit.KILN_CONTAINER.get(), KilnScreen::new);
        }
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
