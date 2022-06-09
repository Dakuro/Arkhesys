package com.github.dakuro.arkhesys.data;

import com.github.dakuro.arkhesys.world.level.block.ModBlocks;
import com.github.dakuro.arkhesys.ARKHESYS;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsGen extends BlockTagsProvider {
    public BlockTagsGen(DataGenerator gen, ExistingFileHelper exFileHelp) {
        super(gen, ARKHESYS.MODID, exFileHelp);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SILVER_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SILVER_ORE.get());

        tag(Tags.Blocks.ORES)
                .add(ModBlocks.SILVER_ORE.get());
    }

    @Override
    public String getName() {
        return "Arkhesys Tags";
    }
}
