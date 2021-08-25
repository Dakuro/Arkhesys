package net.dakuro.arkhesys.world.item;

import net.dakuro.arkhesys.core.ModArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PotionArmorItem extends ArmorItem {
    public PotionArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        ArmorMaterial material = ((PotionArmorItem) stack.getItem()).getMaterial();
        if (material == ModArmorMaterials.SILVER) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0), null);
        }
    }
}
