package net.dakuro.arkhesys.data;

import net.dakuro.arkhesys.ARKHESYS;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
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
        ExistingFileHelper exFileHelp = event.getExistingFileHelper();

        if(event.includeClient())
        {
            gen.addProvider(new LangGenEnUs(gen));
            gen.addProvider(new LangGenFrFr(gen));
            gen.addProvider(new ItemModelGen(gen, exFileHelp));
            gen.addProvider(new BlockStateGen(gen, exFileHelp));
        }

        if(event.includeServer())
        {
            gen.addProvider(new LootTableGen(gen));
            BlockTagsGen blockTags = new BlockTagsGen(gen, exFileHelp);
            gen.addProvider(blockTags);
            gen.addProvider(new ItemTagsGen(gen, blockTags, exFileHelp));
        }
    }
}
