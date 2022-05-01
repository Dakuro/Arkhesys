package net.dakuro.arkhesys.data;

import net.dakuro.arkhesys.ARKHESYS;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGen extends ItemModelProvider {
    public ItemModelGen(DataGenerator gen, ExistingFileHelper exFileHelper)
    {
        super(gen, ARKHESYS.MODID, exFileHelper);
    }

    @Override
    protected void registerModels()
    {
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        getBuilder("raw_silver")
                .parent(itemGenerated)
                .texture("layer0", "item/raw_silver");
    }
}
