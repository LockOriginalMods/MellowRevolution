package com.LockOriginalMods.MellowRevolution.common.init;

import com.LockOriginalMods.MellowRevolution.common.registries.HammerItem;
import com.LockOriginalMods.MellowRevolution.common.registries.MellowSeedItem;
import com.LockOriginalMods.MellowRevolution.common.registries.ToolItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static com.LockOriginalMods.MellowRevolution.MellowRevolution.MOD_ID;
import static com.LockOriginalMods.MellowRevolution.common.registries.CreativeTab.MYSTICISM_TAB;

public class ItemInit
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);


    //tools

    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer",
            ()-> new HammerItem(80));

    public static final RegistryObject<Item> CUTTER = ITEMS.register("cutter",
            ()-> new ToolItem(80));


    public static final RegistryObject<Item> MORTAR = ITEMS.register("mortar",
            ()-> new ToolItem(80));

    //crop
    public static final RegistryObject<Item> FLAX_CROP_SEEDS = ITEMS.register("flax_crop_seeds",
            ()-> new ItemNameBlockItem(BlockInit.FLAX_CROP.get(), new Item.Properties().tab(MYSTICISM_TAB)));

   // public static final RegistryObject<Item> CORN_SEEDS = ITEMS.register("corn_seeds",
          //  ()-> new ItemNameBlockItem(BlockInit.CORN_CROP.get(),new Item.Properties().tab(MYSTICISM_TAB)));




    public static final ItemRegObject<MellowSeedItem> CORN_SEEDS = register(
            "corn_seeds", () -> new MellowSeedItem(BlockInit.CORN_CROP.get()));



    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            ()-> new Item(new Item.Properties().tab(MYSTICISM_TAB).food(new FoodProperties.Builder().nutrition(2).saturationMod(2f).build())));

    public static final RegistryObject<Item> CORN_OIL = ITEMS.register("corn_oil",
            ()-> new Item(new Item.Properties().tab(MYSTICISM_TAB)));

    public static final RegistryObject<Item> FLAX_FIBER = ITEMS.register("flax_fiber",
            ()-> new Item(new Item.Properties().tab(MYSTICISM_TAB)));


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







    private static <T extends Item> ItemRegObject<T> register(String name, Supplier<? extends T> make)
    {
        return new ItemRegObject<>(ITEMS.register(name, make));
    }



    public static class ItemRegObject<T extends Item> implements Supplier<T>, ItemLike
    {
        private final RegistryObject<T> regObject;

        private ItemRegObject(RegistryObject<T> regObject)
        {
            this.regObject = regObject;
        }

        @Override
        @Nonnull
        public T get()
        {
            return regObject.get();
        }

        @Nonnull
        @Override
        public Item asItem()
        {
            return regObject.get();
        }

        public ResourceLocation getId()
        {
            return regObject.getId();
        }
    }

    public static void init()
    {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }
}
