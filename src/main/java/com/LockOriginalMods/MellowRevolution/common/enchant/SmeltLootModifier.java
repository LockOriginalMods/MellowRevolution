package com.LockOriginalMods.MellowRevolution.common.enchant;

import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class SmeltLootModifier extends LootModifier {
    public SmeltLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        return null;
    }

    @Nonnull

    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        return generatedLoot.stream().map(stack ->
                context.getLevel().getRecipeManager().getRecipeFor(
                                RecipeType.SMELTING, new SimpleContainer(stack), context.getLevel()
                        ).map(SmeltingRecipe::getResultItem)
                        .filter(itemStack -> !itemStack.isEmpty())
                        .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                        .orElse(stack)
        ).collect(Collectors.toList());
    }

    public static class Serializer extends GlobalLootModifierSerializer<SmeltLootModifier> {

        @Override
        public SmeltLootModifier read(ResourceLocation name, JsonObject json, LootItemCondition[] conditions) {
            return new SmeltLootModifier(conditions);
        }

        @Override
        public JsonObject write(SmeltLootModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}