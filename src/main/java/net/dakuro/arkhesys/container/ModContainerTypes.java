package net.dakuro.arkhesys.container;

import net.dakuro.arkhesys.utils.Registration;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainerTypes {

    public static final RegistryObject<ContainerType<InfuserContainer>> INFUSER = Registration.CONTAINERS.register("infuser",
            () -> IForgeContainerType.create(InfuserContainer::new));

    public static void init() { }
}
