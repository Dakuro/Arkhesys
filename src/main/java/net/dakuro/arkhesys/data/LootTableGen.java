package net.dakuro.arkhesys.data;

import net.dakuro.arkhesys.world.item.ModItems;
import net.dakuro.arkhesys.world.level.block.ModBlocks;
import net.minecraft.data.DataGenerator;

public class LootTableGen extends ModLootTableProvider
{
    public LootTableGen(DataGenerator dataGeneratorIn)
    {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables()
    {
        lootTables.put(ModBlocks.SILVER_ORE.get(), createSilkTouchTable("silver_ore",
                ModBlocks.SILVER_ORE.get(), ModItems.RAW_SILVER.get(), 1, 3));
    }
}
