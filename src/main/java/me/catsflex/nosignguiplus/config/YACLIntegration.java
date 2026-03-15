package me.catsflex.nosignguiplus.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import net.minecraft.network.chat.Component;

public class YACLIntegration {
	public static Screen createScreen(Screen parent) {
		var config = NoSignGUIPlusConfig.getInstance();
		
		return YetAnotherConfigLib.createBuilder()
			.title(Component.translatable("config.nosignguiplus.title"))
			
			// 'General' category
			.category(ConfigCategory.createBuilder().name(Component.translatable("config.nosignguiplus.category.general"))
				
				// 'Disable sign GUI' option
				.option(Option.<Boolean>createBuilder()
					.name(Component.translatable("config.nosignguiplus.option.disable_sign_gui.name"))
					.description(OptionDescription.of(Component.translatable("config.nosignguiplus.option.disable_sign_gui.description")))
					.binding(NoSignGUIPlusConfig.DEF_IS_SIGN_GUI_DISABLED, () -> config.isSignGUIDisabled, v -> config.isSignGUIDisabled = v)
					.controller(TickBoxControllerBuilder::create)
					.build())
				
				// 'Controls' option
				.option(ButtonOption.createBuilder()
					.name(Component.translatable("config.nosignguiplus.option.go_to_key_binds.name"))
					.description(OptionDescription.of(Component.translatable("config.nosignguiplus.option.go_to_key_binds.description")))
					.text(Component.translatable("config.nosignguiplus.option.go_to_key_binds.text"))
					.action((yaclScreen, thisOption) -> {
						Minecraft.getInstance().setScreen(new KeyBindsScreen(parent, Minecraft.getInstance().options));
					})
					.build())
				
				.build())
			.save(config::save)
			.build()
			.generateScreen(parent);
	}
}
