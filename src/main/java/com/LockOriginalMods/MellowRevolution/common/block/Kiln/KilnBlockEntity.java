package com.LockOriginalMods.MellowRevolution.common.block.Kiln;

import com.LockOriginalMods.MellowRevolution.common.init.BlockInit;
import net.minecraft.commands.BrigadierExceptions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.gui.StatsComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class KilnBlockEntity extends AbstractFurnaceBlockEntity {


    public KilnBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockInit.KILN_ENTITY_TYPE.get(), pWorldPosition, pBlockState, BlockInit.FIRING_RECIPE);
    }

    /**
     * @return
     */
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mellow_revolution.kiln");
    }

    protected int getBurnDuration(ItemStack pFuel) {
        return super.getBurnDuration(pFuel) / 2;
    }

    protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
        return new KilnContainer(pId, pPlayer, this, this.dataAccess);
    }

}
