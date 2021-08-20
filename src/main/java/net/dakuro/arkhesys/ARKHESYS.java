package net.dakuro.arkhesys;

import net.dakuro.arkhesys.core.ModBlocks;
import net.dakuro.arkhesys.core.ModItems;
import net.dakuro.arkhesys.core.ModSoundEvents;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ARKHESYS.MODID)
public class ARKHESYS
{
    public static final String MODID = "arkhesys";

    public ARKHESYS()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);
        ModItems.ITEMS.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
        ModSoundEvents.SOUND_EVENTS.register(eventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void clientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PRIMORDIAL_GLASS.get(), RenderType.translucent());
    }
}