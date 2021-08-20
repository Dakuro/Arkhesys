package net.dakuro.arkhesys.core;

import net.dakuro.arkhesys.ARKHESYS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModTiers {

    public static final Tag.Named<Block> SILVER_TAG = BlockTags.createOptional(
            new ResourceLocation("needs_silver_tool")
    );

    public static final Tier SILVER = TierSortingRegistry.registerTier(
            new ForgeTier(2, 1024, 7.0F, 2.5F, 26,
                    SILVER_TAG, () -> Ingredient.of(ModItems.SILVER_INGOT.get())),
            new ResourceLocation("silver"),
            List.of(Tiers.IRON), List.of(Tiers.DIAMOND)
    );

}
