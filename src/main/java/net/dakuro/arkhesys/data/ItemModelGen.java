package net.dakuro.arkhesys.data;

import net.dakuro.arkhesys.ARKHESYS;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGen extends ItemModelProvider {
    public ItemModelGen(DataGenerator gen, ExistingFileHelper existingFileHelper)
    {
        super(gen, ARKHESYS.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {

    }
}
