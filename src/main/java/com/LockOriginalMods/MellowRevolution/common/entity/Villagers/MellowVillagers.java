package com.LockOriginalMods.MellowRevolution.common.entity.Villagers;

import com.LockOriginalMods.MellowRevolution.common.init.BlockInit;
import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

import static com.LockOriginalMods.MellowRevolution.MellowRevolution.MOD_ID;

public class MellowVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MOD_ID);

    public static final RegistryObject<PoiType> BLOCK_POI = POI_TYPES.register("block_poi", ()->
            new PoiType(ImmutableSet.copyOf(Blocks.ACACIA_LOG.getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<PoiType> BLOCK_POIS = POI_TYPES.register("block_pois", ()->
            new PoiType(ImmutableSet.copyOf(BlockInit.DEEPSLATE_ZINC_ORE.get().getStateDefinition().getPossibleStates()),
                    1, 1));


    public static final RegistryObject<VillagerProfession> MECHANIC = VILLAGER_PROFESSIONS.register("mechanic",
            ()-> new VillagerProfession("mechanic", x -> x.get() == BLOCK_POI.get(),
                    x -> x.get() == BLOCK_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));

    public static final RegistryObject<VillagerProfession> AGRONOMIST = VILLAGER_PROFESSIONS.register("agronomist",
            ()-> new VillagerProfession("agronomist", x -> x.get() == BLOCK_POIS.get(),
                    x -> x.get() == BLOCK_POIS.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));






    public static void registerPOIs(){
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, BLOCK_POI.get());
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, BLOCK_POIS.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
