package com.LockOriginalMods.MellowRevolution.common.block.Kiln;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.LockOriginalMods.MellowRevolution.MellowRevolution.MOD_ID;

@OnlyIn(Dist.CLIENT)
public class KilnScreen extends AbstractFurnaceScreen<KilnContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/container/kiln.png");

    public KilnScreen(KilnContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, new FiringRecipeBookComponent(), pPlayerInventory, pTitle, TEXTURE);
    }
}
