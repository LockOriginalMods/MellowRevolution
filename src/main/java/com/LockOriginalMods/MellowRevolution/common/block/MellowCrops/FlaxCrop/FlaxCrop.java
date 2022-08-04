package com.LockOriginalMods.MellowRevolution.common.block.MellowCrops.FlaxCrop;

import com.LockOriginalMods.MellowRevolution.common.init.ItemInit;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class FlaxCrop extends CropBlock {
    public FlaxCrop(Properties p_52247_) {
        super(p_52247_);
    }
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);

    @Override
    protected ItemLike getBaseSeedId() {
        return ItemInit.FLAX_CROP_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_52286_) {
        p_52286_.add(AGE);
    }
}
