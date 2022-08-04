package com.LockOriginalMods.MellowRevolution.common.block.Kiln;

import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Set;


@OnlyIn(Dist.CLIENT)
public class FiringRecipeBookComponent extends AbstractFurnaceRecipeBookComponent {


    private static final Component FILTER_NAME = Component.translatable("gui.recipebook.toggleRecipes.fireable");


    protected Component getRecipeFilterName() {
        return FILTER_NAME;
    }

    @Override
    protected Set<Item> getFuelItems() {
        return AbstractFurnaceBlockEntity.getFuel().keySet(); // What else then??
    }
}
