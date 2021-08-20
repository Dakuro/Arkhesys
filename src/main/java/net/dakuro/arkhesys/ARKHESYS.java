package net.dakuro.arkhesys;

import net.dakuro.arkhesys.block.ModBlocks;
import net.dakuro.arkhesys.container.ModContainerTypes;
import net.dakuro.arkhesys.crafting.recipe.ModRecipes;
import net.dakuro.arkhesys.events.ModEvents;
import net.dakuro.arkhesys.fluid.ModFluids;
import net.dakuro.arkhesys.item.ModItems;
import net.dakuro.arkhesys.potion.ModPotions;
import net.dakuro.arkhesys.setup.ClientProxy;
import net.dakuro.arkhesys.setup.IProxy;
import net.dakuro.arkhesys.setup.ServerProxy;
import net.dakuro.arkhesys.sound.ModSoundEvents;
import net.dakuro.arkhesys.tile_entity.ModTileEntityTypes;
import net.dakuro.arkhesys.utils.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ARKHESYS.MODID)
public class ARKHESYS
{
    public static final String MODID = "arkhesys";

    public static IProxy proxy;

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public ARKHESYS()
    {
        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        registerModAdditions();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        proxy.init();

        ModPotions.addPotionRecipes();
    }

    private void registerModAdditions()
    {
        Registration.init();

        ModBlocks.init();
        ModContainerTypes.init();
        ModItems.init();
        ModFluids.init();
        ModSoundEvents.init();
        ModPotions.init();
        ModRecipes.init();
        ModTileEntityTypes.init();

        MinecraftForge.EVENT_BUS.register(new ModEvents());
    }

}