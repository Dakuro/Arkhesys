package com.github.dakuro.arkhesys.data;

import com.github.dakuro.arkhesys.ARKHESYS;
import com.github.dakuro.arkhesys.world.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ItemModelGen extends ItemModelProvider {
    public ItemModelGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ARKHESYS.MODID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        createItemModel(ModItems.RAW_SILVER.get(), "generated");
    }

    public void createItemModel(Item item, String parentType) {
        String itemPath = Objects.requireNonNull(item.getRegistryName()).getPath();
        getBuilder(itemPath)
                .parent(getExistingFile(mcLoc("item/" + parentType)))
                .texture("layer0", "item/" + itemPath);
    }
}
