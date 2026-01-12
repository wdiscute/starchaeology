package com.wdiscute.starchaeology.datagen.Antiqua;

import com.mojang.datafixers.util.Pair;
import com.wdiscute.starchaeology.Starchaeology;
import com.wdiscute.starchaeology.io.AntiquaProperties;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class AntiquaPropertiesRegistry
{

    public static void register()
    {
        DGMinecraftAntiqua.bootstrap();
        DGStarchaeologyAntiqua.bootstrap();
        //add compat here
    }

    //region builders
    protected static AntiquaProperties overworldAntiqua(Holder<Item> antiqua)
    {
        return AntiquaProperties.DEFAULT.withAntiqua(antiqua)
                .withWorldRestrictions(AntiquaProperties.WorldRestrictions.OVERWORLD);
    }

    //endregion

    private static final List<Pair<ResourceKey<AntiquaProperties>, AntiquaProperties>> PROPERTIES = new ArrayList<>();
    private static final List<ResourceKey<AntiquaProperties>> COMPAT_KEYS = new ArrayList<>();

    static ResourceKey<AntiquaProperties> createKey(AntiquaProperties ap)
    {
        return ResourceKey.create(
                Starchaeology.ANTIQUA_REGISTRY, ResourceLocation.parse(ap.antiqua()
                        .getRegisteredName()));
    }

    protected static void register(AntiquaProperties ap)
    {
        ResourceKey<AntiquaProperties> key = AntiquaPropertiesRegistry.createKey(ap);
        PROPERTIES.add(Pair.of(key, ap));
        String namespace = key.location().getNamespace();
        if (!namespace.equals("minecraft") && !namespace.equals("starchaeology"))
            COMPAT_KEYS.add(key);
    }

    public static void registerConditions(BiConsumer<ResourceKey<?>, ICondition> consumer)
    {
        for (ResourceKey<AntiquaProperties> compatKey : COMPAT_KEYS)
        {
            consumer.accept(compatKey, new ModLoadedCondition(compatKey.location().getNamespace()));
        }
    }

    public static void bootstrap(BootstrapContext<AntiquaProperties> context)
    {
        PROPERTIES.forEach(p -> context.register(p.getFirst(), p.getSecond()));
    }
}
