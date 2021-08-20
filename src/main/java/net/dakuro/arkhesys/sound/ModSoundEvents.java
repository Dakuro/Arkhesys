package net.dakuro.arkhesys.sound;

import net.dakuro.arkhesys.ARKHESYS;
import net.dakuro.arkhesys.utils.Registration;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

public class ModSoundEvents
{
    public static final RegistryObject<SoundEvent> MUSIC_DISC_RTC = Registration.SOUND_EVENTS.register("record.music_disc.rtc",
            () -> new SoundEvent(new ResourceLocation(ARKHESYS.MODID,"record.music_disc.rtc")));

    public static void init() { }

}
