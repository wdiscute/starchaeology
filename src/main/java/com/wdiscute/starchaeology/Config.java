package com.wdiscute.starchaeology;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

//    public static final ModConfigSpec.DoubleValue HIT_DELAY = BUILDER
//            .defineInRange("hit_delay", 0.0d, -20, 20);

    static final ModConfigSpec SPEC = BUILDER.build();


    private static final ModConfigSpec.Builder BUILDER_SERVER = new ModConfigSpec.Builder();

//    public static final ModConfigSpec.BooleanValue ENABLE_SEASONS = BUILDER_SERVER
//            .comment("Enables/disables fishes being restricted by seasons.")
//            .comment("Useful if you want to play with a seasons mod but don't like the built-in restrictions.")
//            .define("enable_seasons", true);

    static final ModConfigSpec SPEC_SERVER = BUILDER_SERVER.build();


}
