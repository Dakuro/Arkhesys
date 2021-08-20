package net.dakuro.arkhesys.crafting.recipe;

import net.dakuro.arkhesys.ARKHESYS;
import net.dakuro.arkhesys.utils.Registration;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;

public class ModRecipes {

    public static final RegistryObject<TransmutationRecipe.Serializer> TRANSMUTATION =
            Registration.RECIPE_SERIALIZERS.register("transmuting", TransmutationRecipe.Serializer::new);

    public static final IRecipeType<TransmutationRecipe> TRANSMUTING = IRecipeType.register(ARKHESYS.MODID + "transmuting");

    public static void init() { }
}
