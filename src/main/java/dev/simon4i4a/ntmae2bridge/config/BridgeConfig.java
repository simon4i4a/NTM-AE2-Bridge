package dev.simon4i4a.ntmae2bridge.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class BridgeConfig {
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.DoubleValue AE_PER_HE;
    public static final ModConfigSpec.IntValue MAX_TRANSFER;
    public static final ModConfigSpec.DoubleValue ENERGY_LOSS;
    public static final ModConfigSpec.IntValue BUFFER_CAPACITY;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.push("conversion");
        AE_PER_HE = builder
                .comment("Amount of AE energy produced by one unit of HBM HE.")
                .defineInRange("ae_per_he", 1.0, 0.000001, 1_000_000.0);
        MAX_TRANSFER = builder
                .comment("Maximum HBM HE accepted and converted per game tick.")
                .defineInRange("max_transfer", 256, 1, Integer.MAX_VALUE);
        ENERGY_LOSS = builder
                .comment("Fraction of input energy lost during conversion (0.0 to 1.0).")
                .defineInRange("energy_loss", 0.0, 0.0, 1.0);
        BUFFER_CAPACITY = builder
                .comment("Internal buffer capacity in HBM HE.")
                .defineInRange("buffer_capacity", 10000, 1, Integer.MAX_VALUE);
        builder.pop();

        SPEC = builder.build();
    }

    private BridgeConfig() {
    }
}
