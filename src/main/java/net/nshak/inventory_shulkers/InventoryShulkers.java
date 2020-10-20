package net.nshak.inventory_shulkers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class InventoryShulkers implements ModInitializer {

	public static final Identifier OPEN_SHULKER_PACKET_ID = new Identifier("inventory_shulkers", "open_shulker");
	
	static {
		ServerSidePacketRegistry.INSTANCE.register(OPEN_SHULKER_PACKET_ID, (packetContext, attachedData) -> {
			packetContext.getTaskQueue().execute(() -> {
				PlayerEntity player = packetContext.getPlayer();
				//player.openHandledScreen();
			});
		});
	}
	
	public void onInitialize() {
		
	}
}
