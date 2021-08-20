package net.dakuro.arkhesys.block;

import net.dakuro.arkhesys.utils.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ModBlocks
{

    public static final RegistryObject<Block> DEEPSLATE_CHAOS_ORE = createBlock("deepslate_chaos_ore",
            () -> new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops()
            .harvestTool(ToolType.PICKAXE).harvestLevel(2).strength(3.0F, 3.0F).sound(SoundType.STONE)),
            new Item.Properties());

    public static final RegistryObject<Block> END_CHAOS_ORE = createBlock("end_chaos_ore",
            () -> new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops()
            .harvestTool(ToolType.PICKAXE).harvestLevel(2).strength(3.0F, 3.0F).sound(SoundType.STONE)),
            new Item.Properties());

    public static final RegistryObject<Block> SILVER_ORE = createBlock("silver_ore",
            () -> new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops()
            .harvestTool(ToolType.PICKAXE).harvestLevel(2).strength(3.0F, 3.0F).sound(SoundType.STONE)),
            new Item.Properties());

    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = createBlock("deepslate_silver_ore",
            () -> new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops()
            .harvestTool(ToolType.PICKAXE).harvestLevel(2).strength(5.0F, 5.0F).sound(SoundType.STONE)),
            new Item.Properties());

    public static final RegistryObject<Block> SILVER_BLOCK = createBlock("silver_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops()
            .harvestTool(ToolType.PICKAXE).harvestLevel(2).strength(8.0F, 10.0F).sound(SoundType.METAL)),
            new Item.Properties());

    public static final RegistryObject<Block> INFUSION_BASE = createBlock("infusion_base",
            () -> new Block(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                    .harvestTool(ToolType.PICKAXE).harvestLevel(1).strength(6.0F, 6.0F).sound(SoundType.ANCIENT_DEBRIS)
                    .lightLevel((state) -> {return 6;})), new Item.Properties().rarity(Rarity.RARE));

    public static final RegistryObject<Block> INFUSER = createBlock("infuser",
            () -> new InfuserBlock(AbstractBlock.Properties.of(Material.HEAVY_METAL).harvestTool(ToolType.PICKAXE).harvestLevel(1)
                    .strength(20.0F, 20.0F).sound(SoundType.ANCIENT_DEBRIS).lightLevel((state) -> {return 8;})),
            new Item.Properties().rarity(Rarity.RARE));

    public static void init() { }

    public static RegistryObject<Block> createBlock(String name, Supplier<? extends Block> supplier, Item.Properties properties)
    {
        RegistryObject<Block> block = Registration.BLOCKS.register(name, supplier);
        Registration.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
        return block;
    }

}
