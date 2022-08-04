package com.LockOriginalMods.MellowRevolution.common.block.Kiln;

import com.LockOriginalMods.MellowRevolution.common.init.BlockInit;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import net.minecraftforge.registries.ForgeRegistry;
import org.jetbrains.annotations.Nullable;

public class FiringRecipe extends AbstractCookingRecipe {
    public FiringRecipe(ResourceLocation pId, String pGroup, Ingredient pIngredient, ItemStack pResult, float pExperience, int pCookingTime) {
        super(BlockInit.FIRING_RECIPE, pId, pGroup, pIngredient, pResult, pExperience, pCookingTime);
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(BlockInit.KILN.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return BlockInit.FIRING_SERIALIZER.get();
    }

    public static class FiringRecipeType implements RecipeType<FiringRecipe> {
        @Override
        public String toString() {
            return "mellow_revolution:firing";
        }
    }
    public static class Serializer
            implements RecipeSerializer<FiringRecipe> {

        @Override
        public FiringRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
            String s = GsonHelper.getAsString(pJson, "group", "");
            JsonElement jsonelement = (JsonElement)(GsonHelper.isArrayNode(pJson, "ingredient") ? GsonHelper.getAsJsonArray(pJson, "ingredient") : GsonHelper.getAsJsonObject(pJson, "ingredient"));
            Ingredient ingredient = Ingredient.fromJson(jsonelement);
            //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
            if (!pJson.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            ItemStack itemstack;
            if (pJson.get("result").isJsonObject()) itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
            else {
                String s1 = GsonHelper.getAsString(pJson, "result");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                itemstack = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> {
                    return new IllegalStateException("Item: " + s1 + " does not exist");
                }));
            }
            float f = GsonHelper.getAsFloat(pJson, "experience", 0.0F);
            int i = GsonHelper.getAsInt(pJson, "cookingtime", 100);
            return new FiringRecipe(pRecipeId, s, ingredient, itemstack, f, i);
        }

        @Nullable
        @Override
        public FiringRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
            ItemStack itemstack = pBuffer.readItem();
            float f = pBuffer.readFloat();
            int i = pBuffer.readVarInt();
            return new FiringRecipe(pRecipeId, s, ingredient, itemstack, f, i);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, FiringRecipe pRecipe) {
            pBuffer.writeUtf(pRecipe.group);
            pRecipe.ingredient.toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.result);
            pBuffer.writeFloat(pRecipe.experience);
            pBuffer.writeVarInt(pRecipe.cookingTime);
        }
    }
}
