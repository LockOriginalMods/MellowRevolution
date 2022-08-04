package com.LockOriginalMods.MellowRevolution.common.block.Kiln;

import com.LockOriginalMods.MellowRevolution.common.init.BlockInit;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class KilnContainer extends AbstractFurnaceMenu {


    private Object pcontainer;

    public KilnContainer(int pContainerId, Inventory pInventory) {
        super(BlockInit.KILN_CONTAINER.get(), BlockInit.FIRING_RECIPE, RecipeBookType.FURNACE, pContainerId, pInventory);
        
    }

    public KilnContainer(int pContainerId, Inventory pInventory, Container pcontainer, ContainerData PData) {
        super(BlockInit.KILN_CONTAINER.get(), BlockInit.FIRING_RECIPE, RecipeBookType.FURNACE, pContainerId, pInventory, pcontainer, PData);
        this.addSlot(new Slot(pcontainer, 0, 56, 17));
        this.addSlot(new Slot(pcontainer, 3, 46, 17));
        this.addSlot(new FurnaceFuelSlot(this, pcontainer, 1, 56, 53));
        this.addSlot(new FurnaceResultSlot(pInventory.player, pcontainer, 2, 116, 35));
    }

    public void fillCraftSlotsStackedContents(StackedContents p_38976_) {
        if (this.pcontainer instanceof StackedContentsCompatible) {
            ((StackedContentsCompatible) this.pcontainer).fillStackedContents(p_38976_);
        }

    }

    public ItemStack quickMoveStack(Player p_38986_, int p_38987_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_38987_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (p_38987_ == 2) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (p_38987_ != 1 && p_38987_ != 0) {
                if (this.canSmelt(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (p_38987_ >= 3 && p_38987_ < 30) {
                    if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (p_38987_ >= 30 && p_38987_ < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(p_38986_, itemstack1);
        }

        return itemstack;
    }
}
