package net.dakuro.arkhesys.data;

import com.google.common.collect.Sets;
import net.dakuro.arkhesys.ARKHESYS;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.Collections;

@Mod.EventBusSubscriber(modid = ARKHESYS.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen
{
    public static final ExistingFileHelper IGNORING_FILES_EFH = new ExistingFileHelper(Collections.emptyList(),
            Sets.newConcurrentHashSet(), false, null, null);

    @SubscribeEvent
    public static void dataGen(final GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();

        if(event.includeClient())
        {
            gen.addProvider(new LangGenEnUs(gen));
            gen.addProvider(new LangGenFrFr(gen));
            gen.addProvider(new ItemModelGen(gen, event.getExistingFileHelper()));
        }
    }
}
