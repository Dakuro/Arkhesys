package net.dakuro.arkhesys.world.item;

import net.dakuro.arkhesys.world.level.block.ModBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTab {
    public static final CreativeModeTab ARKHESYS_TAB = new CreativeModeTab("arkhesys_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RAW_SILVER.get());
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> pItems) {
            pItems.add(new ItemStack(ModBlocks.SILVER_ORE.get()));
            pItems.add(new ItemStack(ModItems.RAW_SILVER.get()));
        }
    };
}
