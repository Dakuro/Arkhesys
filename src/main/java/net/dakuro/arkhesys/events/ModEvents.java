package net.dakuro.arkhesys.events;

import com.google.common.collect.Lists;
import net.dakuro.arkhesys.fluid.ModFluids;
import net.dakuro.arkhesys.item.ModItems;
import net.dakuro.arkhesys.potion.ModPotions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class ModEvents
{
    @SubscribeEvent
    public void onUnivWater(ProjectileImpactEvent.Throwable event) {
        if(event.getThrowable() instanceof PotionEntity) {
            if((((PotionEntity) event.getThrowable()).getItem().getItem()).equals(ModItems.UNIV_BOTTLE.get())) {
                BlockPos pos = event.getThrowable().blockPosition();
                List<BlockPos> waterPos = Lists.newArrayList();
                BlockPos tempPos;
                for (int z = -4; z <= 4; z++) {
                    for (int y = -49; y <= 49; y++) {
                        for (int x = -4; x <= 4; x++) {
                            tempPos = new BlockPos(pos.getX()+x, pos.getY()+y, pos.getZ()+z);
                            if(event.getThrowable().level.getFluidState(tempPos).getType() == Fluids.WATER) {
                                waterPos.add(tempPos);
                            }
                        }
                    }
                }
                if(!waterPos.isEmpty()) {
                    for (BlockPos blockPos : waterPos) {
                        if (!event.getThrowable().level.isClientSide) {
                            event.getThrowable().level.setBlock(blockPos, ModFluids.ASTRAAL_BLOCK.get().defaultBlockState(), 3);
                        }
                    }
                    waterPos.clear();
                    event.getThrowable().level.playSound(null, pos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundCategory.BLOCKS, 0.3F, 1.0F);
                }
            }
        }
    }

    @SubscribeEvent
    public void onAstraalClick(PlayerInteractEvent.RightClickItem event) {
        if(event.getPlayer().getMainHandItem().getItem() == (Items.GLASS_BOTTLE)){
            RayTraceResult traceResult = getPlayerPOVHitResult(event.getWorld(), event.getPlayer(), RayTraceContext.FluidMode.SOURCE_ONLY);
            if(traceResult.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos blockPos = ((BlockRayTraceResult)traceResult).getBlockPos();
                if(event.getWorld().getFluidState(blockPos).getType() == ModFluids.ASTRAAL_FLUID.get()) {
                    event.getPlayer().getMainHandItem().shrink(1);
                    event.getWorld().playSound(event.getPlayer(), event.getPlayer().getX(), event.getPlayer().getY(), event.getPlayer().getZ(),
                        SoundEvents.BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    event.getPlayer().inventory.add(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.ASTRAAL_POTION.get()));
                    event.setCanceled(true);
                }
            }
        } else if(event.getPlayer().getOffhandItem().getItem() == (Items.GLASS_BOTTLE)) {
            RayTraceResult traceResult = getPlayerPOVHitResult(event.getWorld(), event.getPlayer(), RayTraceContext.FluidMode.SOURCE_ONLY);
            if(traceResult.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos blockPos = ((BlockRayTraceResult)traceResult).getBlockPos();
                if(event.getWorld().getFluidState(blockPos).getType() == ModFluids.ASTRAAL_FLUID.get()) {
                    event.getPlayer().getOffhandItem().shrink(1);
                    event.getWorld().playSound(event.getPlayer(), event.getPlayer().getX(), event.getPlayer().getY(), event.getPlayer().getZ(),
                            SoundEvents.BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    event.getPlayer().inventory.add(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.ASTRAAL_POTION.get()));
                    event.setCanceled(true);
                }
            }
        }
    }

    public static BlockRayTraceResult getPlayerPOVHitResult(World world, PlayerEntity player, RayTraceContext.FluidMode fluidMode) {
        float f = player.xRot;
        float f1 = player.yRot;
        Vector3d vector3d = player.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();;
        Vector3d vector3d1 = vector3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return world.clip(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.OUTLINE, fluidMode, player));
    }
}
