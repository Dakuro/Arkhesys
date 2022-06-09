package com.github.dakuro.arkhesys.world.item;

import com.github.dakuro.arkhesys.ARKHESYS;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ARKHESYS.MODID);

    public static final Rarity CHAOTIC = Rarity.create("chaotic", ChatFormatting.DARK_RED);

    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties().tab(ModTab.ARKHESYS_TAB))
    );
}
