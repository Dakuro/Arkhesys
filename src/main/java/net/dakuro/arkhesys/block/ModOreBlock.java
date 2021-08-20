package net.dakuro.arkhesys.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ModOreBlock extends OreBlock
{
    public ModOreBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    protected int getExperience(Random rand) {
        if (this == ModBlocks.DEEPSLATE_CHAOS_ORE.get()) {
            return MathHelper.nextInt(rand, 4, 8);
        } else if (this == ModBlocks.END_CHAOS_ORE.get()) {
            return MathHelper.nextInt(rand, 4, 8);
        } else {
            return this == ModBlocks.SILVER_ORE.get() ? MathHelper.nextInt(rand, 0, 1) : 0;
        }
    }

    public void spawnAfterBreak(BlockState state, ServerWorld worldIn, BlockPos pos, ItemStack stack) {
        super.spawnAfterBreak(state, worldIn, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }
}
