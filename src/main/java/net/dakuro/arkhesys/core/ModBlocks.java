package net.dakuro.arkhesys.core;

import net.dakuro.arkhesys.ARKHESYS;
import net.dakuro.arkhesys.world.level.block.ChaosBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ARKHESYS.MODID);

    public static final RegistryObject<Block> SILVER_ORE = createBlock("silver_ore",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0F, 3.0F).sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = createBlock("deepslate_silver_ore",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE)
                    .strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)
                    .requiresCorrectToolForDrops())
    );

    public static final RegistryObject<Block> RAW_SILVER_BLOCK = createBlock("raw_silver_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.METAL)
                    .strength(5.0F, 6.0F).requiresCorrectToolForDrops())
    );

    public static final RegistryObject<Block> SILVER_BLOCK = createBlock("silver_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                    .strength(5.0F, 6.0F).sound(SoundType.METAL)
                    .requiresCorrectToolForDrops())
    );

    public static final RegistryObject<Block> CHAOS_BLOCK = createBlock("chaos_block",
            () -> new ChaosBlock(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.COLOR_RED)
                    .strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()),
            new Item.Properties().rarity(ModItems.CHAOTIC)
    );

    public static final RegistryObject<Block> PRIMORDIAL_GLASS = createBlock("primordial_glass",
            () -> new TintedGlassBlock(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.COLOR_RED)
                    .strength(0.6F, 666666.6F).sound(SoundType.GLASS)
                    .noOcclusion().isValidSpawn(ModBlocks::never).isRedstoneConductor(ModBlocks::never)
                    .isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never)),
            new Item.Properties().rarity(ModItems.CHAOTIC)
    );

    public static final RegistryObject<Block> INFUSION_BASE = createBlock("infusion_base",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE)
                    .strength(6.0F, 6.0F).sound(SoundType.DEEPSLATE_TILES)
                    .requiresCorrectToolForDrops().lightLevel((state) -> 6)),
            new Item.Properties().rarity(Rarity.RARE)
    );

    public static RegistryObject<Block> createBlock(String name, Supplier<? extends Block> supplier, Item.Properties properties) {
        RegistryObject<Block> block = BLOCKS.register(name, supplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
        return block;
    }

    public static RegistryObject<Block> createBlock(String name, Supplier<? extends Block> supplier) {
        RegistryObject<Block> block = BLOCKS.register(name, supplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static Boolean never(BlockState state, BlockGetter level, BlockPos pos, EntityType<?> entity) {
        return (boolean)false;
    }

    private static Boolean always(BlockState state, BlockGetter level, BlockPos pos, EntityType<?> entity) {
        return (boolean)true;
    }

    private static boolean never(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    private static boolean always(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

}
