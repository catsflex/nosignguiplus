package me.catsflex.nosignguiplus.util;

import com.mojang.logging.LogUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.slf4j.Logger;

public class Helper {
	
	private static final ModContainer MOD = FabricLoader
		.getInstance()
		.getModContainer("nosignguiplus")
		.orElseThrow();
	
	public static final String MOD_ID = MOD.getMetadata().getId();
	public static final String MOD_NAME = MOD.getMetadata().getName();
	public static final String MOD_PREFIX = String.format("[%s]", MOD_NAME);
	
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final String CONFIG_NAME = "nosignguiplus.json";
	public static final String YACL_MOD_ID = "yet_another_config_lib_v3";
}
