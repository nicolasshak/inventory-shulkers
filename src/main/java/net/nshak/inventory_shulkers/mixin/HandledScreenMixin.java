package net.nshak.inventory_shulkers.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> extends HandledScreen<T> {

	public HandledScreenMixin(T handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}
	
	@Shadow
	private Slot getSlotAt(double xPosition, double yPosition) { return null; }
	
	public Slot getSlotAtWrapper(double xPosition, double yPosition) {
		return this.getSlotAt(xPosition, yPosition);
	}
}
