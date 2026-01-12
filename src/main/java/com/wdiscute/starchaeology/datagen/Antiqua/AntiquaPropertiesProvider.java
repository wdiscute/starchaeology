package com.wdiscute.starchaeology.datagen.Antiqua;

import com.wdiscute.starchaeology.Starchaeology;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class AntiquaPropertiesProvider extends DatapackBuiltinEntriesProvider {

    static {
        AntiquaPropertiesRegistry.register(); //register all entries before anything else
    }

    public static final RegistrySetBuilder REGISTRY = new RegistrySetBuilder()
            //fishes
            .add(Starchaeology.ANTIQUA_REGISTRY, AntiquaPropertiesRegistry::bootstrap);

    public AntiquaPropertiesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, REGISTRY, AntiquaPropertiesProvider::addConditions, Set.of(
                Starchaeology.MOD_ID,
                "minecraft"
                //add more compat here
        ));
    }

    private static void addConditions(final BiConsumer<ResourceKey<?>, ICondition> consumer) {
        AntiquaPropertiesRegistry.registerConditions(consumer);
    }

    @Override
    public String getName() {
        return "AntiquaProperties";
    }
}
