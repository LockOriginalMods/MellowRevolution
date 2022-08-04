package com.LockOriginalMods.MellowRevolution.common.init;

import com.LockOriginalMods.MellowRevolution.common.block.Kiln.FiringRecipe;
import com.LockOriginalMods.MellowRevolution.common.block.Kiln.KilnBlock;
import com.LockOriginalMods.MellowRevolution.common.block.Kiln.KilnBlockEntity;
import com.LockOriginalMods.MellowRevolution.common.block.Kiln.KilnContainer;
import com.LockOriginalMods.MellowRevolution.common.block.MellowCrops.CornCrop.CornBlock;
import com.LockOriginalMods.MellowRevolution.common.block.MellowCrops.FlaxCrop.FlaxCrop;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static com.LockOriginalMods.MellowRevolution.MellowRevolution.MOD_ID;
import static com.LockOriginalMods.MellowRevolution.common.registries.CreativeTab.MYSTICISM_TAB;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);
    private static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);



    public static final RegistryObject<Block> ZINC_BLOCK = registerBlock("zinc_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f).requiresCorrectToolForDrops()), MYSTICISM_TAB);

    public static final RegistryObject<Block> ZINC_ORE = registerBlock("zinc_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5f).requiresCorrectToolForDrops()), MYSTICISM_TAB);

    public static final RegistryObject<Block> DEEPSLATE_ZINC_ORE = registerBlock("deepslate_zinc_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(7f).requiresCorrectToolForDrops()), MYSTICISM_TAB);

    public static final RegistryObject<Block> FLAX_CROP = BLOCKS.register("flax_crop",
            () -> new FlaxCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop",
            () -> new CornBlock(BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH)));


    public static final RegistryObject<Block> KILN = registerBlock("kiln",
            () -> new KilnBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F).lightLevel(litBlockEmission(13))));
    private static <T extends Block> RegistryObject<T> registerBlock(String pName, Supplier<T> pBlock) {
        RegistryObject<T> block = BLOCKS.register(pName, pBlock);
        BlockInit.ITEMS.register(pName, () -> new BlockItem(block.get(), new Item.Properties().tab(MYSTICISM_TAB)));
        return block;
    }
    public static final RegistryObject<BlockEntityType<KilnBlockEntity>> KILN_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register("kiln",
            () -> BlockEntityType.Builder.of(KilnBlockEntity::new, BlockInit.KILN.get()).build(null));

    public static final RegistryObject<MenuType<KilnContainer>> KILN_CONTAINER = MENU_TYPES.register("kiln",
            () -> new MenuType<>(KilnContainer::new));


    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);
    public static final RegistryObject<RecipeSerializer<FiringRecipe>> FIRING_SERIALIZER = RECIPE_SERIALIZERS.register("firing",
//            () -> new SimpleCookingSerializer<>(FiringRecipe::new, 100));
            FiringRecipe.Serializer::new);
    public static RecipeType<FiringRecipe> FIRING_RECIPE = new FiringRecipe.FiringRecipeType();


    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
    }

    private static ResourceLocation makeStat(String pKey, StatFormatter pFormatter) {
        ResourceLocation resourcelocation = new ResourceLocation(pKey);
        Registry.register(Registry.CUSTOM_STAT, pKey, resourcelocation);
        Stats.CUSTOM.get(resourcelocation, pFormatter);
        return resourcelocation;
    }


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
                                                                     CreativeModeTab tab, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, tooltipKey);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab, String tooltipKey) {
        return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)) {
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(Component.translatable(tooltipKey));
            }
        });
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    static int id=1;
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public int nextID(){
        id++;
        return id;
    }




    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        BLOCK_ENTITY_TYPES.register(eventBus);
        MENU_TYPES.register(eventBus);
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
