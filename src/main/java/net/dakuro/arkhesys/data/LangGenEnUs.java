package net.dakuro.arkhesys.data;

import net.dakuro.arkhesys.ARKHESYS;
import net.dakuro.arkhesys.world.item.ModItems;
import net.dakuro.arkhesys.world.level.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangGenEnUs extends LanguageProvider
{
    public LangGenEnUs(DataGenerator gen)
    {
        super(gen, ARKHESYS.MODID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        add("itemGroup.arkhesys_tab", "Arkhesys");

        add(ModItems.RAW_SILVER.get(), "Raw Silver");

        add(ModBlocks.SILVER_ORE.get(), "Silver Ore");
    }
}
