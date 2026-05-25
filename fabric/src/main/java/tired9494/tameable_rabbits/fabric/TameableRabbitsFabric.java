package tired9494.tameable_rabbits.fabric;

import tired9494.tameable_rabbits.TameableRabbits;
import net.fabricmc.api.ModInitializer;

public final class TameableRabbitsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        TameableRabbits.init();
    }
}
