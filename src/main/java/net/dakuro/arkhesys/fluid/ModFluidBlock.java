package net.dakuro.arkhesys.fluid;

import net.dakuro.arkhesys.potion.ModPotions;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.merchant.IReputationType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.entity.passive.horse.ZombieHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.ITag;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.function.Supplier;

public class ModFluidBlock extends FlowingFluidBlock
{
    public ITag.INamedTag<Fluid> fluidTag;
    public ModFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties, ITag.INamedTag<Fluid> fluidTag) {
        super(supplier, properties);
        this.fluidTag = fluidTag;
    }

    public BlockState state;
    public World world;
    public BlockPos blockPos;
    public LivingEntity liventity;

    @Override
    @SuppressWarnings("deprecation")
    public void entityInside(BlockState state, World world, BlockPos blockPos, Entity entity) {
        if(entity instanceof LivingEntity) {
            this.state = state;
            this.world = world;
            this.blockPos = blockPos;
            this.liventity = (LivingEntity)entity;

            if(liventity.isInWater() && (world.getFluidState(blockPos).getType() == ModFluids.ASTRAAL_FLUID.get())) {
                liventity.addEffect(new EffectInstance(ModPotions.ASTRAAL_EFFECT.get(), 60, 0, false, false, false));
                if(liventity instanceof ZombieVillagerEntity) {
                    world.playSound((PlayerEntity)null, blockPos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundCategory.NEUTRAL, 0.3F, 1.0F);
                    if(!world.isClientSide) {
                        ZombieVillagerEntity zombieVillagerEntity = (ZombieVillagerEntity) liventity;
                        VillagerEntity villagerentity = zombieVillagerEntity.convertTo(EntityType.VILLAGER, false);
                        for(EquipmentSlotType equipmentslottype : EquipmentSlotType.values()) {
                            ItemStack itemstack = zombieVillagerEntity.getItemBySlot(equipmentslottype);
                            if (!itemstack.isEmpty()) {
                                if (EnchantmentHelper.hasBindingCurse(itemstack)) {
                                    villagerentity.setSlot(equipmentslottype.getIndex() + 300, itemstack);
                                } else {
                                    zombieVillagerEntity.spawnAtLocation(itemstack);
                                }
                            }
                        }
                        villagerentity.setVillagerData(zombieVillagerEntity.getVillagerData());
                        villagerentity.setVillagerXp(256);
                        villagerentity.finalizeSpawn((ServerWorld)world, world.getCurrentDifficultyAt(villagerentity.blockPosition()), SpawnReason.CONVERSION, (ILivingEntityData)null, (CompoundNBT)null);
                        PlayerEntity playerentity = world.getPlayerByUUID(world.getNearestPlayer(zombieVillagerEntity, 5.0D).getUUID());
                        if (playerentity instanceof ServerPlayerEntity) {
                            CriteriaTriggers.CURED_ZOMBIE_VILLAGER.trigger((ServerPlayerEntity)playerentity, zombieVillagerEntity, villagerentity);
                            ((ServerWorld) world).onReputationEvent(IReputationType.ZOMBIE_VILLAGER_CURED, playerentity, villagerentity);
                        }
                        villagerentity.addEffect(new EffectInstance(Effects.CONFUSION, 200, 0));
                        if (!zombieVillagerEntity.isSilent()) {
                            world.levelEvent((PlayerEntity)null, 1027, zombieVillagerEntity.blockPosition(), 0);
                        }
                        net.minecraftforge.event.ForgeEventFactory.onLivingConvert(zombieVillagerEntity, villagerentity);
                    }
                }
                if(liventity instanceof ZombieHorseEntity) {
                    world.playSound((PlayerEntity)null, blockPos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundCategory.NEUTRAL, 0.3F, 1.0F);
                    ((ZombieHorseEntity) liventity).convertTo(EntityType.HORSE, false);
                }
            }
        }
        super.entityInside(state, world, blockPos, entity);
    }
}
