package com.LockOriginalMods.MellowRevolution.common.block.MellowCrops.CornCrop;

import com.LockOriginalMods.MellowRevolution.common.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;

import java.util.EnumMap;
import java.util.Random;
import java.util.function.Supplier;

public class CornBlock extends BushBlock implements BonemealableBlock
{
    public static final Supplier<Properties> PROPERTIES = () -> Properties.of(Material.PLANT)
            .sound(SoundType.CROP)
            .noCollission()
            .strength(0)
            .randomTicks();
    public final static EnumProperty<EnumCornGrowth> GROWTH = EnumProperty.create("growth", EnumCornGrowth.class);

    public CornBlock(Properties props)
    {
        super(props);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items)
    {
        //NOP
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(GROWTH);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        boolean b = super.canSurvive(state, world, pos);
        if(state.getValue(GROWTH)== EnumCornGrowth.TOP0)
        {
            BlockState stateBelow = world.getBlockState(pos.below());
            b = stateBelow.getBlock().equals(this)&&stateBelow.getValue(GROWTH)== EnumCornGrowth.BOTTOM0.getMax();
        }
        return b;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos)
    {
        //TODO improve once CropsBlock is improved
        return state.getBlock()==Blocks.FARMLAND;
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos)
    {
        return PlantType.CROP;
    }

    private static final EnumMap<EnumCornGrowth, VoxelShape> shapes = new EnumMap<>(EnumCornGrowth.class);

    static
    {
        shapes.put(EnumCornGrowth.BOTTOM0, Shapes.create(
                new AABB(0, 0, 0, 1, .375f, 1)));
        shapes.put(EnumCornGrowth.BOTTOM1, Shapes.create(
                new AABB(0, 0, 0, 1, .625f, 1)));
        shapes.put(EnumCornGrowth.BOTTOM2, Shapes.create(
                new AABB(0, 0, 0, 1, .875f, 1)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
    {
        return shapes.getOrDefault(state.getValue(GROWTH), Shapes.block());
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader world, BlockPos pos, BlockPos neighbor)
    {
        super.onNeighborChange(state, world, pos, neighbor);
        //TODO is this what this was intended to do?
        if(world.getBlockState(pos).getValue(GROWTH)!= EnumCornGrowth.TOP0)
            //FIXME: TEST THIS.
            if(world instanceof Level)
                ((Level)world).updateNeighborsAt(pos.offset(0, 1, 0), this);
    }


    public void tick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        int light = world.getMaxLocalRawBrightness(pos);
        if(light >= 12)
        {
            EnumCornGrowth growth = state.getValue(GROWTH);
            if(growth== EnumCornGrowth.TOP0)
                return;
            float speed = this.getGrowthSpeed(world, pos, state, light);
            if(random.nextInt((int)(50F/speed)+1)==0)
            {
                if(growth.getMax()!=growth)
                    world.setBlockAndUpdate(pos, state.setValue(GROWTH, growth.next()));
                else if(world.isEmptyBlock(pos.offset(0, 1, 0)))
                    world.setBlockAndUpdate(pos.offset(0, 1, 0), state.setValue(GROWTH, EnumCornGrowth.TOP0));
            }
        }
    }

    private float getGrowthSpeed(Level world, BlockPos pos, BlockState state, int light)
    {
        float growth = 0.125f*(light-11);
        if(world.canSeeSkyFromBelowWater(pos))
            growth += 2f;
        BlockState soil = world.getBlockState(pos.offset(0, -1, 0));
        if(soil.getBlock().isFertile(soil, world, pos.offset(0, -1, 0)))
            growth *= 1.5f;
        return 1f+growth;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean isClient)
    {
        EnumCornGrowth growth = state.getValue(GROWTH);
        if(growth!=growth.getMax())
            return true;
        else
            return growth== EnumCornGrowth.BOTTOM4&&world.getBlockState(pos.offset(0, 1, 0)).getBlock()!=this;
    }

    @Override
    public boolean isBonemealSuccess(Level p_220878_, RandomSource p_220879_, BlockPos p_220880_, BlockState p_220881_) {
        return isValidBonemealTarget(p_220878_, p_220880_, p_220878_.getBlockState(p_220880_), p_220878_.isClientSide);
    }

    @Override
    public void performBonemeal(ServerLevel p_220874_, RandomSource p_220875_, BlockPos p_220876_, BlockState p_220877_) {

        EnumCornGrowth growth = p_220877_.getValue(GROWTH);
        if(growth!=growth.getMax())
        {
            int span = growth.getMax().ordinal()-growth.ordinal();
            EnumCornGrowth newGrowth = growth;
            int growBy = p_220875_.nextInt(span)+1;
            for(int i = 0; i < growBy; ++i)
                newGrowth = newGrowth.next();
            p_220874_.setBlockAndUpdate(p_220876_, p_220877_.setValue(GROWTH, newGrowth));
            growth = newGrowth;
        }
        if(growth== EnumCornGrowth.BOTTOM4&&p_220874_.isEmptyBlock(p_220876_.offset(0, 1, 0)))
            p_220874_.setBlockAndUpdate(p_220876_.offset(0, 1, 0), p_220877_.setValue(GROWTH, EnumCornGrowth.TOP0));
    }





    @Override
    public Item asItem()
    {
        return ItemInit.CORN_SEEDS.get();
    }
}