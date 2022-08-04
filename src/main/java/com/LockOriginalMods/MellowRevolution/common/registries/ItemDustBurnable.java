package com.LockOriginalMods.MellowRevolution.common.registries;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import javax.annotation.Nullable;

public class ItemDustBurnable extends Item {

  public ItemDustBurnable(Properties properties) {
    super(properties);
  }

  @Override
  public int getBurnTime(ItemStack item, @Nullable RecipeType<?> recipeType) {
    return 60 * 20; // charcoal/coal is 80 . blaze rod is 120; oak log is 15
  }
}