package net.dakuro.arkhesys.tile_entity;

import net.dakuro.arkhesys.ARKHESYS;
import net.dakuro.arkhesys.container.InfuserContainer;
import net.dakuro.arkhesys.crafting.recipe.ModRecipes;
import net.dakuro.arkhesys.crafting.recipe.TransmutationRecipe;
import net.dakuro.arkhesys.fluid.ModFluids;
import net.dakuro.arkhesys.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Predicate;

public class InfuserTileEntity extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {

    private final FluidStack CONTENT = new FluidStack(ModFluids.ASTRAAL_FLUID.get(), 1);
    private int tankCapacity = FluidAttributes.BUCKET_VOLUME * 20;
    private final Predicate<FluidStack> VALIDATOR = fluidStack -> fluidStack.isFluidEqual(CONTENT);
    protected FluidTank tank = new FluidTank(tankCapacity, VALIDATOR);
    private final LazyOptional<IFluidHandler> holder = LazyOptional.of(() -> tank);
    private static final int[] SLOTS_FOR_UP = new int[]{0, 1, 2, 3, 4, 5}; // Ingredients
    private static final int[] SLOTS_FOR_DOWN = new int[]{6, 7, 8}; // Result & Empty/Full Fluid Handlers
    private static final int[] SLOTS_FOR_WEST = new int[]{7}; // FluidIn
    private static final int[] SLOTS_FOR_EAST = new int[]{8}; // FluidOut
    private int totalExp;
    private NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);
    protected final IRecipeType<TransmutationRecipe> recipeType;
    private int mutationProgress;
    private int mutationTotalTime;
    private int fluidStock;
    private int fluidCost;
    protected final IIntArray dataAccess = new IIntArray() {
        public int get(int index) {
            switch(index) {
                case 0:
                    return InfuserTileEntity.this.mutationProgress;
                case 1:
                    return InfuserTileEntity.this.mutationTotalTime;
                case 2:
                    return InfuserTileEntity.this.fluidStock;
                case 3:
                    return InfuserTileEntity.this.fluidCost;
                case 4:
                    return InfuserTileEntity.this.tankCapacity;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch(index) {
                case 0:
                    InfuserTileEntity.this.mutationProgress = value;
                    break;
                case 1:
                    InfuserTileEntity.this.mutationTotalTime = value;
                    break;
                case 2:
                    InfuserTileEntity.this.fluidStock = value;
                    break;
                case 3:
                    InfuserTileEntity.this.fluidCost = value;
                    break;
                case 4:
                    InfuserTileEntity.this.tankCapacity = value;
            }

        }

        public int getCount() {
            return 5;
        }
    };

    public InfuserTileEntity() {
        super(ModTileEntityTypes.INFUSER.get());
        this.recipeType = ModRecipes.TRANSMUTING;
    }

    public void encodeExtraData(PacketBuffer buffer) {
        buffer.writeInt(dataAccess.getCount());
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tag, this.items);
        this.tank.readFromNBT(tag);
        this.mutationProgress = tag.getInt("TransmuteTime");
        this.mutationTotalTime = tag.getInt("TransmuteTotalTime");
        this.totalExp = tag.getInt("TotalExp");
        this.fluidStock = tag.getInt("FluidStock");
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag = super.save(tag);
        ItemStackHelper.saveAllItems(tag, this.items);
        this.tank.writeToNBT(tag);
        tag.putInt("TransmuteTime", this.mutationProgress);
        tag.putInt("TransmuteTotalTime", this.mutationTotalTime);
        tag.putInt("TotalExp", this.totalExp);
        tag.putInt("FluidStock", this.fluidStock);
        return tag;
    }

    @Override
    public void tick() {
        if (this.level != null && !this.level.isClientSide) {
            NonNullList<ItemStack> stacks = NonNullList.withSize(6, ItemStack.EMPTY);
            for (int i = 0; i < 6; i++) {
                stacks.set(i, this.items.get(i));
            }

            FluidStack stock = new FluidStack(CONTENT.getRawFluid(), this.getFluidStock());
            this.tank.setFluid(stock);
            this.drainFluidIn(this.items.get(7));
            this.fillFluidOut(this.items.get(8));

            if (!stacks.isEmpty()) {
                IRecipe<IInventory> recipe = this.level.getRecipeManager().getRecipeFor(this.recipeType, this, this.level).orElse(null);

                if (this.canTransmute(recipe)) {
                    ++this.mutationProgress;
                    this.mutationTotalTime = this.getTotalMutationTime();
                    if (this.mutationProgress == this.mutationTotalTime) {
                        this.mutationProgress = 0;
                        this.transmute(recipe);
                    }
                } else {
                    this.mutationProgress = 0;
                }
            }
        }
    }

    protected boolean canTransmute(@Nullable IRecipe<IInventory> recipe) {
        NonNullList<ItemStack> stacks = NonNullList.withSize(6, ItemStack.EMPTY);
        for (int i = 0; i < 6; i++) {
            stacks.set(i, this.items.get(i));
        }
        if ((!stacks.isEmpty() && recipe != null) && (this.getFluidCost() <= this.getFluidStock())) {
            ItemStack itemstack = recipe.assemble(this);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = this.items.get(6);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.sameItem(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= this.getMaxStackSize() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    protected void transmute(@Nullable IRecipe<IInventory> recipe) {
        if (recipe != null && this.canTransmute(recipe)) {
            NonNullList<ItemStack> stacks = NonNullList.withSize(6, ItemStack.EMPTY);
            for (int i = 0; i < 6; i++) {
                stacks.set(i, this.items.get(i));
            }
            ItemStack itemstack1 = recipe.assemble(this);
            ItemStack itemstack2 = this.items.get(6);
            if (itemstack2.isEmpty()) {
                this.items.set(6, itemstack1.copy());
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount());
            }
            for (ItemStack stack : stacks) {
                if(!stack.isEmpty()) {
                    stack.shrink(1);
                }
            }
            this.setFluidStock(this.getFluidCost(), true);
            this.stockExp(this.getExperience());
        }
    }

    private int getTotalMutationTime() {
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(this.recipeType, this, this.level).map(TransmutationRecipe::getMutationTime).orElse(100);
    }

    private int getFluidCost() {
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(this.recipeType, this, this.level).map(TransmutationRecipe::getFluidCost).orElse(500);
    }

    private float getExperience() {
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(this.recipeType, this, this.level).map(TransmutationRecipe::getExperience).orElse(0.0F);
    }

    private void drainFluidIn(ItemStack stack) {
        if (stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
            if (stack.getItem() instanceof BucketItem) {
                if (((BucketItem) stack.getItem()).getFluid().isSame(ModFluids.ASTRAAL_FLUID.get())) {
                    if (this.tank.getSpace() >= 1000) {
                        this.setFluidStock(1000, false);
                        this.setItem(7, stack.getContainerItem());
                    }
                }
            } else {
                FluidStack fluidIn = FluidStack.loadFluidStackFromNBT(stack.getTag());
                if (fluidIn.isFluidEqual(this.tank.getFluid())) {
                    int space = this.tank.getSpace();
                    if (space < fluidIn.getAmount()) {
                        this.setFluidStock(space, false);
                        fluidIn.shrink(space);
                    } else {
                        this.setFluidStock(fluidIn.getAmount(), false);
                        fluidIn.shrink(fluidIn.getAmount());
                    }
                }
            }
        }
        this.fluidStock = this.tank.getFluidAmount();
    }

    private void fillFluidOut(ItemStack stack) {
        if (stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
            if (stack.getItem() instanceof BucketItem) {
                if (((BucketItem) stack.getItem()).getFluid().isSame(Fluids.EMPTY)) {
                    if (this.tank.getFluidAmount() >= 1000) {
                        this.setFluidStock(1000, true);
                        this.setItem(8, new ItemStack(ModItems.ASTRAAL_BUCKET.get()));
                    }
                }
            } else {
                FluidStack fluidOut = FluidStack.loadFluidStackFromNBT(stack.getTag());
                if (fluidOut.isFluidEqual(FluidStack.EMPTY) || fluidOut.isFluidEqual(this.tank.getFluid())) {
                    this.tank.drain(fluidOut, IFluidHandler.FluidAction.EXECUTE);
                }
            }
        }
        this.fluidStock = this.tank.getFluidAmount();
    }

    protected int getFluidStock() {
        return this.tank.getFluidAmount();
    }

    protected void setFluidStock(int amount, boolean shrink) {
        if (shrink) {
            this.tank.getFluid().shrink(amount);
        } else this.tank.getFluid().grow(amount);
    }

    public void stockExp(float exp) {
        this.totalExp += MathHelper.floor(exp);
    }

    public int gettotalExp() {
        return this.totalExp;
    }

    public void createExperience(World world, PlayerEntity player, int exp) {
        world.addFreshEntity(new ExperienceOrbEntity(world, player.getX(), player.getY(), player.getZ(), exp));
        this.totalExp = 0;
    }

    @Override
    public int[] getSlotsForFace(Direction facing) {
        switch (facing) {
            case UP:
                return SLOTS_FOR_UP;
            case DOWN:
                return SLOTS_FOR_DOWN;
            case WEST:
                return SLOTS_FOR_WEST;
            case EAST:
                return SLOTS_FOR_EAST;
            default:
                return new int[]{ };
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction facing) {
        if (stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
            FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(stack.getTag());
            if (slot == 7 && this.items.get(7).isEmpty()) {
                if (stack.getItem() instanceof BucketItem) {
                    return ((BucketItem) stack.getItem()).getFluid().isSame(ModFluids.ASTRAAL_FLUID.get());
                } else {
                    return fluidStack.isFluidEqual(this.CONTENT) && !stack.getItem().equals(Items.MILK_BUCKET);
                }
            } else if (slot == 8 && this.items.get(8).isEmpty()){
                if (stack.getItem() instanceof BucketItem) {
                    return ((BucketItem) stack.getItem()).getFluid().isSame(Fluids.EMPTY);
                } else {
                    return fluidStack.isEmpty() && !stack.getItem().equals(Items.MILK_BUCKET);
                }
            }
        }
        return slot != 6;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction facing) {
        if (facing == Direction.DOWN) {
            if (stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
                FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(stack.getTag());
                if (slot == 7) {
                    if (stack.getItem() instanceof BucketItem) {
                        return ((BucketItem) stack.getItem()).getFluid().isSame(Fluids.EMPTY);
                    } else {
                        return fluidStack.isEmpty();
                    }
                } else if (slot == 8){
                    if (stack.getItem() instanceof BucketItem) {
                        return !((BucketItem) stack.getItem()).getFluid().isSame(Fluids.EMPTY);
                    } else {
                        return !fluidStack.isEmpty();
                    }
                } else return slot == 6;
            } else return slot == 6;
        }
        return false;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + ARKHESYS.MODID + ".infuser");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInv) {
        return new InfuserContainer(id, playerInv, this, this.dataAccess);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return slot >= 0 && slot < this.items.size() ? this.items.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ItemStackHelper.removeItem(this.items, slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ItemStackHelper.takeItem(this.items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (slot >= 0 && slot < this.items.size()) {
            this.items.set(slot, stack);
        }
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        assert this.level != null;
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) > 64.0D);
        }
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return save(new CompoundNBT());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(getBlockPos(), 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        load(getBlockState(), pkt.getTag());
    }

    LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.WEST, Direction.EAST);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {

        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return holder.cast();
        else if (!this.remove && facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
            else if (facing == Direction.WEST)
                return handlers[2].cast();
            else if (facing == Direction.EAST)
                return handlers[3].cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<? extends net.minecraftforge.items.IItemHandler> handler : handlers) handler.invalidate();
    }
}

