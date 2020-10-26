package net.nshak.inventory_shulkers.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.nshak.inventory_shulkers.screen.ImplementedInventory;
import org.jetbrains.annotations.Nullable;

public class ShulkerBoxItemEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory;
    private final ItemStack itemStack;

    public ShulkerBoxItemEntity(ItemStack stack) {
        this.itemStack = stack;
        this.inventory = DefaultedList.ofSize(27, ItemStack.EMPTY);
        CompoundTag tag = stack.getOrCreateTag();
        CompoundTag blockEntityTag = tag.getCompound("BlockEntityTag");

        if (!blockEntityTag.isEmpty()) {
            Inventories.fromTag(blockEntityTag, this.inventory);
        } else {
           tag.put("BlockEntityTag", new CompoundTag());
        }
    }

    @Override
    public Text getDisplayName() {
        if (this.itemStack.hasCustomName()) {
            CompoundTag tag = this.itemStack.getTag();

            if (tag != null) {
                return new TranslatableText(tag.getString("Name"));
            }
        }

        return new TranslatableText("container.shulkerBox");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new ShulkerBoxScreenHandler(syncId, inv, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void markDirty() {
        CompoundTag tag = this.itemStack.getTag().getCompound("BlockEntityTag");

        if (tag != null) {
            Inventories.toTag(tag, this.inventory, false);
        }
    }
}
