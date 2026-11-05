package com.daniel99j.lightfix.mixin;

import com.daniel99j.lightfix.StairsLightingFix;
import com.llamalad7.mixinextras.sugar.Local;
import net.caffeinemc.mods.sodium.client.model.light.LightMode;
import net.caffeinemc.mods.sodium.client.render.model.AbstractBlockRenderContext;
import net.caffeinemc.mods.sodium.client.render.model.MutableQuadViewImpl;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlockRenderContext.class)
public class ExampleMixin {
	@Shadow
	protected BlockState state;

	@Shadow
	protected LightMode defaultLightMode;

	@Inject(method = "prepareAoInfo", at = @At(value = "TAIL"))
	private void modifyLightMode(boolean modelAo, CallbackInfo ci) {
		if(this.state.is(BlockTags.STAIRS) && StairsLightingFix.ENABLED) {
			this.defaultLightMode = LightMode.FLAT;
		}
	}
}