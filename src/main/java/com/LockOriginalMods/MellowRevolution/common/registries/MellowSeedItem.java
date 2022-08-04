package com.LockOriginalMods.MellowRevolution.common.registries;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import static com.LockOriginalMods.MellowRevolution.common.registries.CreativeTab.MYSTICISM_TAB;

public class MellowSeedItem extends BlockItem implements IPlantable
{
    public MellowSeedItem(Block cropBlock)
    {
        super(cropBlock, new Properties().tab(MYSTICISM_TAB));
    }



    @Override
    public String getDescriptionId()
    {
        return this.getOrCreateDescriptionId();
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos)
    {
        return ((IPlantable)this.getBlock()).getPlantType(world, pos);
    }

    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos)
    {
        return this.getBlock().defaultBlockState();
    }
}