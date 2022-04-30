package net.dakuro.arkhesys;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ARKHESYS.MODID)
public class ARKHESYS
{
    public static final String MODID = "arkhesys";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ARKHESYS()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event)
    {
        LOGGER.info("Hello from Arkhesys Commun Setup");
    }

    private void clientSetup(FMLClientSetupEvent event)
    {
        LOGGER.info("Hello from Arkhesys Client Setup");
    }

}
