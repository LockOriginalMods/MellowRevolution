package com.LockOriginalMods.MellowRevolution.common.block.MellowCrops.CornCrop;

import com.LockOriginalMods.MellowRevolution.common.init.BlockInit;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public class PottedCornBlock extends FlowerPotBlock
{
    public static final Supplier<Properties> PROPERTIES = () -> Properties.of(Material.DECORATION)
            .strength(0.0F)
            .noOcclusion();

    public PottedCornBlock(Properties props)
    {
        super(() -> (FlowerPotBlock)Blocks.FLOWER_POT, BlockInit.CORN_CROP, props);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items)
    {
        //NOP
    }
}