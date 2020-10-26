package net.nshak.inventory_shulkers;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import net.nshak.inventory_shulkers.screen.HandledScreenEvents;
import net.nshak.inventory_shulkers.screen.TargetedItemStackProvider;
import org.lwjgl.glfw.GLFW;

public class InventoryShulkersClient implements ClientModInitializer {
	
	private final static KeyBinding openShulkerBind;
	
	static {
		openShulkerBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"Open Shulker Box",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_R,
			"Inventory Shulkers"
		));
	}
	
	@Override
	public void onInitializeClient() {
		
		HandledScreenEvents.KEY_PRESS.register((client, keyCode, scanCode, modifiers) -> {

			if (openShulkerBind.matchesKey(keyCode, scanCode)) {

				if (client.currentScreen instanceof HandledScreen) {
					TargetedItemStackProvider screen = (TargetedItemStackProvider) client.currentScreen;
					int targetedSlotIndex = screen.getSlotIndex(client.mouse.getX(), client.mouse.getY());

					if (targetedSlotIndex != -1) {
						PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
						data.writeInt(targetedSlotIndex);
						ClientSidePacketRegistry.INSTANCE.sendToServer(InventoryShulkers.OPEN_SHULKER_PACKET_ID, data);
					}
				}
			}
		});
	}
}
