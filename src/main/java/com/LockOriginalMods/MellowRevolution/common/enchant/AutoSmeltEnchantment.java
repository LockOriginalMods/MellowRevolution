package com.LockOriginalMods.MellowRevolution.common.enchant;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class AutoSmeltEnchantment extends Enchantment {
    protected AutoSmeltEnchantment() {
        super(Rarity.VERY_RARE, AutoSmeltEnchantmentCategory.SMELTERS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int p_45264_) {
        return 15;
    }

    public int getMaxCost(int p_45268_) {
        return super.getMinCost(p_45268_) + 50;
    }

    public int getMaxLevel() {
        return 1;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return super.checkCompatibility(enchantment) && enchantment != Enchantments.SILK_TOUCH;
    }
}