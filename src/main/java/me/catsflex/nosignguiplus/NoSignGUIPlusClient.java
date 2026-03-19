package me.catsflex.nosignguiplus;

import com.mojang.blaze3d.platform.InputConstants;
import me.catsflex.nosignguiplus.config.ModConfiguration;
import me.catsflex.nosignguiplus.util.Helper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class NoSignGUIPlusClient implements ClientModInitializer {
	
	private static final KeyMapping.Category DISABLE_SIGN_GUI_CATEGORY =
		KeyMapping.Category.register(Identifier.parse(Helper.MOD_ID));
	public static KeyMapping toggleKey;
	
	@Override
	public void onInitializeClient() {
		
		// Registering the key
		toggleKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
			"key.nosignguiplus.toggle",
			InputConstants.Type.KEYSYM,
			GLFW.GLFW_KEY_RIGHT_CONTROL,
			DISABLE_SIGN_GUI_CATEGORY
		));
		
		// Key listener
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (toggleKey.consumeClick()) {
				if (client.player == null) return;
				var config = ModConfiguration.getInstance();
				
				// Invert state & save
				config.isSignGUIDisabled = !config.isSignGUIDisabled;
				config.save();
				
				// Actionbar message
				var message = config.isSignGUIDisabled ?
					Component.translatable("message.nosignguiplus.sign_gui_disabled").withStyle(ChatFormatting.RED) :
					Component.translatable("message.nosignguiplus.sign_gui_enabled").withStyle(ChatFormatting.GREEN);
				client.player.displayClientMessage(message, true);
			}
		});
	}
}
