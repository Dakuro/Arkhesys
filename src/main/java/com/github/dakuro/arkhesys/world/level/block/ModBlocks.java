package com.github.dakuro.arkhesys.world.level.block;

import com.github.dakuro.arkhesys.world.item.ModItems;
import com.github.dakuro.arkhesys.ARKHESYS;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ARKHESYS.MODID);

    public static final RegistryObject<Block> SILVER_ORE = createBlock("silver_ore",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0F, 3.0F).sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
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
}
