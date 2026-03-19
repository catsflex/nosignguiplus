package me.catsflex.nosignguiplus.mixin;

import me.catsflex.nosignguiplus.config.ModConfiguration;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class SignGUIDisablerMixin {
	
	@Inject(method = "openTextEdit", at = @At("HEAD"), cancellable = true)
	private void onOpenEditSignScreen(SignBlockEntity sign, boolean front, CallbackInfo info) {
		var config = ModConfiguration.getInstance();
		
		if (config.isSignGUIDisabled) {
			info.cancel();
		}
	}
}
