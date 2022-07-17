package com.LockOriginalMods.MellowRevolution.common.enchant;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.ForgeRegistries;

public class AutoSmeltEnchantmentCategory {
    public static final EnchantmentCategory SMELTERS = EnchantmentCategory.create("smelters", item ->
            item instanceof PickaxeItem ||
                    item instanceof ShovelItem ||
                    item instanceof AxeItem ||
                    item instanceof SwordItem ||
                    ForgeRegistries.ITEMS.tags().getTag(ItemTags.CLUSTER_MAX_HARVESTABLES).contains(item) ||
                    ForgeRegistries.ITEMS.tags().getTag(ItemTags.create(new ResourceLocation("forge", "tools/paxels"))).contains(item)
    );
}