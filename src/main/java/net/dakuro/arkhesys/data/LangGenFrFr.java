package net.dakuro.arkhesys.data;

import net.dakuro.arkhesys.ARKHESYS;
import net.dakuro.arkhesys.world.item.ModItems;
import net.dakuro.arkhesys.world.level.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangGenFrFr extends LanguageProvider
{
    public LangGenFrFr(DataGenerator gen)
    {
        super(gen, ARKHESYS.MODID, "fr_fr");
    }

    @Override
    protected void addTranslations()
    {
        add("itemGroup.arkhesys_tab", "Arkhesys");

        add(ModItems.RAW_SILVER.get(), "Argent Brut");

        add(ModBlocks.SILVER_ORE.get(), "Minerai d'Argent");
    }
}
