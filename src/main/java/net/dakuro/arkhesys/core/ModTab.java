package net.dakuro.arkhesys.core;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class ModTab {

    public static final CreativeModeTab ARKHESYS_TAB = new CreativeModeTab("arkhesys_tab") {
        @Override
        @OnlyIn(Dist.CLIENT)
        @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.INFUSION_BASE.get());
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> items) {
            items.add(new ItemStack(ModBlocks.SILVER_ORE.get()));
            items.add(new ItemStack(ModBlocks.DEEPSLATE_SILVER_ORE.get()));
            items.add(new ItemStack(ModItems.RAW_SILVER.get()));
            items.add(new ItemStack(ModBlocks.RAW_SILVER_BLOCK.get()));
            items.add(new ItemStack(ModItems.SILVER_INGOT.get()));
            items.add(new ItemStack(ModBlocks.SILVER_BLOCK.get()));
            items.add(new ItemStack(ModItems.SILVER_SWORD.get()));
            items.add(new ItemStack(ModItems.SILVER_PICKAXE.get()));
            items.add(new ItemStack(ModItems.SILVER_AXE.get()));
            items.add(new ItemStack(ModItems.SILVER_SHOVEL.get()));
            items.add(new ItemStack(ModItems.SILVER_HOE.get()));
            items.add(new ItemStack(ModItems.SILVER_HELMET.get()));
            items.add(new ItemStack(ModItems.SILVER_CHESTPLATE.get()));
            items.add(new ItemStack(ModItems.SILVER_LEGGINGS.get()));
            items.add(new ItemStack(ModItems.SILVER_BOOTS.get()));
            items.add(new ItemStack(ModBlocks.CHAOS_BLOCK.get()));
            items.add(new ItemStack(ModItems.CHAOS_SHARD.get()));
            items.add(new ItemStack(ModItems.CHAOS_GEM.get()));
            items.add(new ItemStack(ModBlocks.PRIMORDIAL_GLASS.get()));
            items.add(new ItemStack(ModItems.MUSIC_DISC_RTC.get()));
            items.add(new ItemStack(ModBlocks.INFUSION_BASE.get()));
            items.add(new ItemStack(ModItems.EMPTY_BOTTLE.get()));
        }
    };

}
