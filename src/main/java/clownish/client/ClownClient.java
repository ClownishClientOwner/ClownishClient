package clownish.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class ClownClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("ClownClient loaded successfully!");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // ClownClient client-side features go here.
        });
    }
}
