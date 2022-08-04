package com.LockOriginalMods.MellowRevolution.common.block.MellowCrops.CornCrop;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;

import static com.LockOriginalMods.MellowRevolution.MellowRevolution.MOD_ID;

public enum EnumCornGrowth implements StringRepresentable
{
    BOTTOM0,
    BOTTOM1,
    BOTTOM2,
    BOTTOM3,
    BOTTOM4,
    TOP0;

    @Override
    public String getSerializedName()
    {
        return name().toLowerCase(Locale.ENGLISH);
    }

    public ResourceLocation getTextureName()
    {
        return new ResourceLocation(MOD_ID, "block/corn/"+getSerializedName());
    }

    @Override
    public String toString()
    {
        return getSerializedName();
    }

    public EnumCornGrowth next()
    {
        return switch(this)
                {
                    case BOTTOM0 -> BOTTOM1;
                    case BOTTOM1 -> BOTTOM2;
                    case BOTTOM2 -> BOTTOM3;
                    case BOTTOM3 -> BOTTOM4;
                    case BOTTOM4, TOP0 -> this;
                };
    }

    public EnumCornGrowth getMin()
    {
        return TOP0==this?TOP0: BOTTOM0;
    }

    public EnumCornGrowth getMax()
    {
        return TOP0==this?TOP0: BOTTOM4;
    }
}