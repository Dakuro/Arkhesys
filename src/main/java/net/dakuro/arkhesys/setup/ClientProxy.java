package net.dakuro.arkhesys.setup;

import net.dakuro.arkhesys.ARKHESYS;
import net.dakuro.arkhesys.container.ModContainerTypes;
import net.dakuro.arkhesys.fluid.ModFluids;
import net.dakuro.arkhesys.screen.InfuserScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ARKHESYS.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy implements IProxy
{
    @Override
    public void init()
    {
        RenderTypeLookup.setRenderLayer(ModFluids.ASTRAAL_FLUID.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(ModFluids.ASTRAAL_FLOWING.get(), RenderType.translucent());
        ScreenManager.register(ModContainerTypes.INFUSER.get(), InfuserScreen::new);
    }

    @Override
    public World getClientWorld()
    {
        return Minecraft.getInstance().level;
    }
}
