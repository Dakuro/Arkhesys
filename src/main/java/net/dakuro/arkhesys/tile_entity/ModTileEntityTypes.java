package net.dakuro.arkhesys.tile_entity;

import net.dakuro.arkhesys.block.ModBlocks;
import net.dakuro.arkhesys.utils.Registration;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModTileEntityTypes {

    public static final RegistryObject<TileEntityType<InfuserTileEntity>> INFUSER = register(
            "infuser",
            InfuserTileEntity::new,
            ModBlocks.INFUSER
    );

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> factory, RegistryObject<? extends Block> block) {
        return Registration.TILE_ENTITIES.register(name, () -> {
            //noinspection ConstantConditions - null in build
            return TileEntityType.Builder.of(factory, block.get()).build(null);
        });
    }

    public static void init() { }
}
