package com.github.dakuro.arkhesys.data;

import com.github.dakuro.arkhesys.world.item.ModItems;
import com.github.dakuro.arkhesys.world.level.block.ModBlocks;
import net.minecraft.data.DataGenerator;

public class LootTableGen extends ModLootTableProvider {
    public LootTableGen(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(ModBlocks.SILVER_ORE.get(), createOreTable("silver_ore",
                ModBlocks.SILVER_ORE.get(), ModItems.RAW_SILVER.get()));
    }
}
