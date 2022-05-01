package net.dakuro.arkhesys.data;

import net.dakuro.arkhesys.ARKHESYS;
import net.dakuro.arkhesys.world.level.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class BlockStateGen extends BlockStateProvider {
    public BlockStateGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ARKHESYS.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        createSimpleBlockModel(ModBlocks.SILVER_ORE.get());
    }

    public void createSimpleBlockModel(Block block) {
        simpleBlock(block);
        simpleBlockItem(block, models().getExistingFile(modLoc("block/"
                + Objects.requireNonNull(block.getRegistryName()).getPath())));
    }
}
