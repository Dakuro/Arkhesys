package net.dakuro.arkhesys.core;

import net.dakuro.arkhesys.ARKHESYS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ARKHESYS.MODID);

    public static final RegistryObject<SoundEvent> MUSIC_DISC_RTC = SOUND_EVENTS.register("record.music_disc.rtc",
            () -> new SoundEvent(new ResourceLocation(ARKHESYS.MODID,"record.music_disc.rtc")));

}
