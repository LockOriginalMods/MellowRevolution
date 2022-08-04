package com.LockOriginalMods.MellowRevolution.common.registries;

import com.LockOriginalMods.MellowRevolution.common.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTab {
    public static final CreativeModeTab MYSTICISM_TAB = new CreativeModeTab("mysticismtab") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemInit.HAMMER.get());
        }
    };


    protected boolean allowdedIn(CreativeModeTab group)
    {
        return false;
    }
}
