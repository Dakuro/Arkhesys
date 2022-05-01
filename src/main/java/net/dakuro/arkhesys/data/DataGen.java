package net.dakuro.arkhesys.data;

import net.dakuro.arkhesys.ARKHESYS;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = ARKHESYS.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen
{
    @SubscribeEvent
    public static void dataGen(final GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();

        if(event.includeClient())
        {
            gen.addProvider(new LangGenEnUs(gen));
            gen.addProvider(new LangGenFrFr(gen));
            gen.addProvider(new ItemModelGen(gen, event.getExistingFileHelper()));
            gen.addProvider(new BlockStateGen(gen, event.getExistingFileHelper()));
        }

        if(event.includeServer())
        {
            gen.addProvider(new LootTableGen(gen));
        }
    }
}
