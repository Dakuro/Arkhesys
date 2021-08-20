package net.dakuro.arkhesys.fluid;

import net.dakuro.arkhesys.ARKHESYS;
import net.dakuro.arkhesys.item.ModItems;
import net.dakuro.arkhesys.utils.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;

public class ModFluids
{
    public static final ResourceLocation ASTRAAL_STILL_RL = new ResourceLocation(ARKHESYS.MODID, "block/astraal_still");
    public static final ResourceLocation ASTRAAL_FLOWING_RL = new ResourceLocation(ARKHESYS.MODID, "block/astraal_flowing");
    public static final ResourceLocation ASTRAAL_OVERLAY_RL = new ResourceLocation(ARKHESYS.MODID, "block/astraal_overlay");
    public static final ResourceLocation ASTRAAL_TAG_RL = new ResourceLocation(ARKHESYS.MODID, "fluids/astraal");

    public static final ITag.INamedTag<Fluid> ASTRAAL = FluidTags.createOptional(ASTRAAL_TAG_RL);

    public static final RegistryObject<FlowingFluid> ASTRAAL_FLUID = Registration.FLUIDS.register("astraal_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.ASTRAAL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> ASTRAAL_FLOWING = Registration.FLUIDS.register("astraal_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.ASTRAAL_PROPERTIES));

    public static final ForgeFlowingFluid.Properties ASTRAAL_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> ModFluids.ASTRAAL_FLUID.get(), () -> ModFluids.ASTRAAL_FLOWING.get(),
            FluidAttributes.builder(ASTRAAL_STILL_RL, ASTRAAL_FLOWING_RL).overlay(ASTRAAL_OVERLAY_RL).rarity(Rarity.RARE)
                    .luminosity(6).density(1).viscosity(1).temperature(300).color(0xFF2AC8F2).sound(SoundEvents.WATER_AMBIENT)
                    .sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY)).slopeFindDistance(8).levelDecreasePerBlock(2)
            .canMultiply().explosionResistance(100.0F).tickRate(5).block(() -> ModFluids.ASTRAAL_BLOCK.get())
            .bucket(() -> ModItems.ASTRAAL_BUCKET.get());

    public static final RegistryObject<FlowingFluidBlock> ASTRAAL_BLOCK = Registration.BLOCKS.register("astraal",
            () -> new ModFluidBlock(() -> ModFluids.ASTRAAL_FLUID.get(), AbstractBlock.Properties.of(Material.WATER)
            .strength(100.0F).noDrops().speedFactor(1.07F).lightLevel((state) -> {return 6;}), ModFluids.ASTRAAL));

    public static void init() { }

}
