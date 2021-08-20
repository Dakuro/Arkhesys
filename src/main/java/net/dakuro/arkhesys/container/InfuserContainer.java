package net.dakuro.arkhesys.container;

import net.dakuro.arkhesys.crafting.recipe.ModRecipes;
import net.dakuro.arkhesys.fluid.ModFluids;
import net.dakuro.arkhesys.tile_entity.InfuserTileEntity;
import net.dakuro.arkhesys.tile_entity.ModTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class InfuserContainer extends Container {
    private final IInventory inventory;
    private final IIntArray dataAccess;

    public InfuserContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
        this(id, playerInventory, new InfuserTileEntity(), new IntArray(buffer.readInt()));
    }

    public InfuserContainer(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray dataAccess) {
        super(ModContainerTypes.INFUSER.get(), id);
        this.inventory = inventory;
        this.dataAccess = dataAccess;
        inventory.startOpen(playerInventory.player);

        this.addSlot(new Slot(this.inventory, 0, 40, 41));
        this.addSlot(new Slot(this.inventory, 1, 26, 81));
        this.addSlot(new Slot(this.inventory, 2, 40, 120));
        this.addSlot(new Slot(this.inventory, 3, 118, 41));
        this.addSlot(new Slot(this.inventory, 4, 132, 81));
        this.addSlot(new Slot(this.inventory, 5, 118, 120));
        this.addSlot(new Slot(this.inventory, 6, 79, 81) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public ItemStack onTake(PlayerEntity player, ItemStack result) {
                if (!player.level.isClientSide && this.container instanceof InfuserTileEntity) {
                    ((InfuserTileEntity)this.container).createExperience(player.level, player, ((InfuserTileEntity)this.container).gettotalExp());
                }
                return super.onTake(player, result);
            }
        });
        this.addSlot(new Slot(this.inventory, 7, 152, 11) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                if (stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
                    if (stack.getItem() instanceof BucketItem) {
                        return ((BucketItem) stack.getItem()).getFluid().isSame(ModFluids.ASTRAAL_FLUID.get());
                    } else {
                        FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(stack.getTag());
                        return fluidStack.isFluidEqual(new FluidStack(ModFluids.ASTRAAL_FLUID.get(), 0)) && !stack.getItem().equals(Items.MILK_BUCKET);
                    }
                }
                return false;
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.inventory, 8, 152, 149) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                if (stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
                    if (stack.getItem() instanceof BucketItem) {
                        return ((BucketItem) stack.getItem()).getFluid().isSame(Fluids.EMPTY);
                    } else {
                        FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(stack.getTag());
                        return fluidStack.isFluidEqual(FluidStack.EMPTY) && !stack.getItem().equals(Items.MILK_BUCKET);
                    }
                }
                return false;
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addDataSlots(dataAccess);

        // Main Player Inventory
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                int index = x + y * 9;
                int posX = 8 + x * 18;
                int posY = 174 + y * 18;
                this.addSlot(new Slot(playerInventory, index + 9, posX, posY));
            }
        }

        // Player Hotbar
        for (int x = 0; x < 9; x++) {
            int posX = 8 + x * 18;
            int posY = 232;
            this.addSlot(new Slot(playerInventory, x, posX, posY));
        }
    }

    public int getProgressScale() {
        int progress = dataAccess.get(0);
        int totalTime = dataAccess.get(1);
        if (progress > 0 && totalTime > 0) {
            return (int)((progress * 55) / totalTime);
        }
        return 0;
    }

    public int getStockScale() {
        int fluidStock = dataAccess.get(2);
        int tankCapacity = dataAccess.get(4);
        if (fluidStock > 0 && tankCapacity > 0) {
            return (int)((fluidStock * 112) / tankCapacity);
        }
        return 0;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 9) {
                if (!this.moveItemStackTo(itemstack1, 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.inventory.stillValid(player);
    }

}
