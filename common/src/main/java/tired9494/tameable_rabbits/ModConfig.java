package tired9494.tameable_rabbits;

import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings({"DefaultAnnotationParam"})
public class ModConfig extends MidnightConfig {
    public static final String RABBIT = "rabbit";

    @Entry(category = RABBIT) public static ResourceLocation tameItem = ResourceLocation.withDefaultNamespace("hay_block");
}