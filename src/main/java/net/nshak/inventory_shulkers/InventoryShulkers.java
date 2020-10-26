package net.nshak.inventory_shulkers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.nshak.inventory_shulkers.entity.ShulkerBoxItemEntity;

public class InventoryShulkers implements ModInitializer {

	public static final Identifier OPEN_SHULKER_PACKET_ID = new Identifier("inventory_shulkers", "open_shulker");
	
	static {
		ServerSidePacketRegistry.INSTANCE.register(OPEN_SHULKER_PACKET_ID, (packetContext, attachedData) -> {
			int targetedSlotIndex = attachedData.readInt();

			packetContext.getTaskQueue().execute(() -> {
				ServerPlayerEntity player = (ServerPlayerEntity) packetContext.getPlayer();
				ItemStack targetedStack = player.currentScreenHandler.slots.get(targetedSlotIndex).getStack();
				if (targetedStack.getItem() instanceof BlockItem && ((BlockItem)targetedStack.getItem()).getBlock() instanceof ShulkerBoxBlock) {
					player.openHandledScreen(new ShulkerBoxItemEntity(targetedStack));
				}
			});
		});
	}
	
	public void onInitialize() {}
}
