package net.dakuro.arkhesys.utils;

import net.dakuro.arkhesys.ARKHESYS;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration
{
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, ARKHESYS.MODID);

    public static final DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, ARKHESYS.MODID);

    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, ARKHESYS.MODID);

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, ARKHESYS.MODID);

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS
            = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ARKHESYS.MODID);

    public static final DeferredRegister<Effect> EFFECTS
            = DeferredRegister.create(ForgeRegistries.POTIONS, ARKHESYS.MODID);

    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTION_TYPES, ARKHESYS.MODID);

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS
            = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ARKHESYS.MODID);

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES
            = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ARKHESYS.MODID);

    public static void init() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(eventBus);
        CONTAINERS.register(eventBus);
        ITEMS.register(eventBus);
        FLUIDS.register(eventBus);
        SOUND_EVENTS.register(eventBus);
        EFFECTS.register(eventBus);
        POTIONS.register(eventBus);
        RECIPE_SERIALIZERS.register(eventBus);
        TILE_ENTITIES.register(eventBus);
    }

}
