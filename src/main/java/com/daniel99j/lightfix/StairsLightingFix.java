package com.daniel99j.lightfix;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StairsLightingFix implements ModInitializer {
	public static final String MOD_ID = "stairs-lighting-fix";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static boolean ENABLED = true;

	@Override
	public void onInitialize() {
		if(FabricLoader.getInstance().isDevelopmentEnvironment()) {
			CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
				dispatcher.register(Commands.literal("toggle_stairs_lighting_fix").executes(context -> {
					context.getSource().sendSuccess(() -> Component.literal("Toggled."), false);
					ENABLED = !ENABLED;
					return 1;
				}));
			});
		}
	}
}
