package com.LockOriginalMods.MellowRevolution.common.registries;

import com.LockOriginalMods.MellowRevolution.common.entity.Villagers.MellowVillagers;
import com.LockOriginalMods.MellowRevolution.common.init.ItemInit;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static com.LockOriginalMods.MellowRevolution.MellowRevolution.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class Events {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == MellowVillagers.MECHANIC.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ItemInit.HAMMER.get(), 1);

            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    stack,10,8,0.02F));
        }

        if(event.getType() == MellowVillagers.MECHANIC.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ItemInit.CUTTER.get(), 1);

            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    stack,10,8,0.02F));
        }
    }
}
