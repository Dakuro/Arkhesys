package net.dakuro.arkhesys.item;

import net.dakuro.arkhesys.block.ModBlocks;
import net.dakuro.arkhesys.potion.ModPotions;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModItemGroup {

    public static final ItemGroup ARKHESYS_TAB = new ItemGroup("arkhesys_tab") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ARKHESYS_ICON.get());
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> items) {
            items.add(new ItemStack(ModItems.CHAOS_SHARD.get()));
            items.add(new ItemStack(ModItems.CHAOS_GEM.get()));
            items.add(new ItemStack(ModBlocks.DEEPSLATE_CHAOS_ORE.get()));
            items.add(new ItemStack(ModBlocks.END_CHAOS_ORE.get()));
            items.add(new ItemStack(ModItems.MUSIC_DISC_RTC.get()));
            items.add(new ItemStack(ModItems.SILVER_INGOT.get()));
            items.add(new ItemStack(ModBlocks.SILVER_ORE.get()));
            items.add(new ItemStack(ModBlocks.DEEPSLATE_SILVER_ORE.get()));
            items.add(new ItemStack(ModBlocks.SILVER_BLOCK.get()));
            items.add(new ItemStack(ModItems.SILVER_SWORD.get()));
            items.add(new ItemStack(ModItems.SILVER_PICKAXE.get()));
            items.add(new ItemStack(ModItems.SILVER_SHOVEL.get()));
            items.add(new ItemStack(ModItems.SILVER_AXE.get()));
            items.add(new ItemStack(ModItems.SILVER_HOE.get()));
            items.add(new ItemStack(ModItems.SILVER_HELMET.get()));
            items.add(new ItemStack(ModItems.SILVER_CHESTPLATE.get()));
            items.add(new ItemStack(ModItems.SILVER_LEGGINGS.get()));
            items.add(new ItemStack(ModItems.SILVER_BOOTS.get()));
            items.add(new ItemStack(ModItems.ASTRAAL_BUCKET.get()));
            items.add(PotionUtils.setPotion(new ItemStack(ModItems.UNIV_BOTTLE.get()), ModPotions.ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(ModItems.UNIV_BOTTLE.get()), ModPotions.LONG_ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(ModItems.UNIV_BOTTLE.get()), ModPotions.STRONG_ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.LONG_ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.STRONG_ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.LONG_ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.STRONG_ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.LONG_ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.STRONG_ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), ModPotions.ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), ModPotions.LONG_ASTRAAL_POTION.get()));
            items.add(PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), ModPotions.STRONG_ASTRAAL_POTION.get()));
            items.add(new ItemStack(ModItems.EMPTY_BOTTLE.get()));
            items.add(new ItemStack(ModBlocks.INFUSION_BASE.get()));
            items.add(new ItemStack(ModBlocks.INFUSER.get()));
        }
    };

}
