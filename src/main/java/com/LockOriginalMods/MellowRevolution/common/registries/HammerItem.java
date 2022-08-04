package com.LockOriginalMods.MellowRevolution.common.registries;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Scanner;

public class HammerItem extends BaseItem{
    public HammerItem(int maxDamage) {
        super(new Properties().stacksTo(1).durability(maxDamage).setNoRepair());
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == CreativeTab.MYSTICISM_TAB) pItems.add(new ItemStack(this));
        super.fillItemCategory(pCategory, pItems);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack result = itemStack.copy();
        result.setDamageValue(itemStack.getDamageValue() + 1);
        return result.getDamageValue() >= result.getMaxDamage() ? ItemStack.EMPTY : result;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {

        if (Screen.hasShiftDown()) {
            components.add(Component.literal("You need this item to grind raw ore or to obtain plates from ingots").withStyle(ChatFormatting.AQUA));
        }
            else{
                    components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.BLUE));
            }





        super.appendHoverText(stack, level, components, flag);
    }
}
