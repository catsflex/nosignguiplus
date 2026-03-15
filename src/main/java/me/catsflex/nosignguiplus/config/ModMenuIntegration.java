package me.catsflex.nosignguiplus.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.catsflex.nosignguiplus.util.Helper;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuIntegration implements ModMenuApi {
	
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return FabricLoader.getInstance().isModLoaded(Helper.YACL_MOD_ID) ? YACLIntegration::createScreen : parent -> null;
	}
}
