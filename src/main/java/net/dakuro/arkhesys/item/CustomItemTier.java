package net.dakuro.arkhesys.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum CustomItemTier implements IItemTier
{
    SILVER(2, 1024, 7.0F, 2.5F, 30, new LazyValue<>(() -> {return Ingredient.of(ModItems.SILVER_INGOT.get());}));

    public final int harvestLevel;
    public final int maxUses;
    public final float efficiency;
    public final float attackDamage;
    public final int enchantability;
    public final LazyValue<Ingredient> repairMaterial;

    CustomItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, LazyValue<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }
}
