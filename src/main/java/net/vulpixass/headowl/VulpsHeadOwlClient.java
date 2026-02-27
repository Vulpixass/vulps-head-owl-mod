package net.vulpixass.headowl;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.EntityType;
import net.vulpixass.headowl.client.render.OwlHatFeatureRenderer;
import net.vulpixass.headowl.client.HeadOwlModelLayers;
import net.vulpixass.headowl.client.model.owl;
import net.vulpixass.headowl.command.client.ClientCommands;

public class VulpsHeadOwlClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess) -> {
            ClientCommands.register(commandDispatcher);
        });
        EntityModelLayerRegistry.registerModelLayer(HeadOwlModelLayers.OWL_LAYER, owl::getTexturedModelData);
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, renderer, registrationHelper, ctx) -> {
            if (entityType == EntityType.PLAYER && renderer instanceof PlayerEntityRenderer playerRenderer) {
                registrationHelper.register(new OwlHatFeatureRenderer(playerRenderer));
            }
        });
        System.out.println("Owl: I have to add this print line so I can upload the 1.21.10 version of the mod to modrinth");
    }
}
