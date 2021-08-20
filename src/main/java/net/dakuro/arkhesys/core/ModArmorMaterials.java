package net.dakuro.arkhesys.core;

import net.dakuro.arkhesys.ARKHESYS;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    SILVER(ARKHESYS.MODID + ":silver", 26, new int[]{3, 6, 6, 2}, 30, SoundEvents.ARMOR_EQUIP_DIAMOND, 1.0F, 0.1F, () -> {
        return Ingredient.of(ModItems.SILVER_INGOT.get());
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairIngredient;

    ModArmorMaterials(String name, int durability, int[] protection, int enchant, SoundEvent sound, float toughness, float knockback, Supplier<Ingredient> repair) {
        this.name = name;
        this.durabilityMultiplier = durability;
        this.slotProtections = protection;
        this.enchantmentValue = enchant;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockback;
        this.repairIngredient = Lazy.concurrentOf(repair);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.slotProtections[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    @Nonnull
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    @Nonnull
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
