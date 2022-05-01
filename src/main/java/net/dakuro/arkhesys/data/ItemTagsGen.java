package net.dakuro.arkhesys.data;

import net.dakuro.arkhesys.ARKHESYS;
import net.dakuro.arkhesys.world.item.ModItems;
import net.dakuro.arkhesys.world.level.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsGen extends ItemTagsProvider {
    public ItemTagsGen(DataGenerator gen, BlockTagsProvider blockTags, ExistingFileHelper exFileHelp) {
        super(gen, blockTags, ARKHESYS.MODID, exFileHelp);
    }

    @Override
    protected void addTags() {
        tag(Tags.Items.ORES)
                .add(ModBlocks.SILVER_ORE.get().asItem());

        tag(Tags.Items.RAW_MATERIALS)
                .add(ModItems.RAW_SILVER.get());
    }

    @Override
    public String getName() {
        return "Arkhesys Tags";
    }
}
