package net.nshak.inventory_shulkers;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class InventoryShulkersClient implements ClientModInitializer {
	
	private static KeyBinding openShulkerBind;
	
	static {
		openShulkerBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.shulker-inventory.open_shulker_box",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_O,
			"key.category.test.first"
		));
	}
	
	@Override
	public void onInitializeClient() {
		
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			System.out.println(openShulkerBind.wasPressed());
		});
	}
}
