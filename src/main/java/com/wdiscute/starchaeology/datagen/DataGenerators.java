package com.wdiscute.starchaeology.datagen;

import com.wdiscute.starchaeology.Starchaeology;
import com.wdiscute.starchaeology.datagen.Antiqua.AntiquaPropertiesProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Starchaeology.MOD_ID)
public class DataGenerators
{

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
        PackOutput output = gen.getPackOutput();

        //antiqua properties
        gen.addProvider(
                event.includeServer(),
                new AntiquaPropertiesProvider(output, registries)
        );

        //item models
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        gen.addProvider(event.includeServer(), new ModItemModelProvider(output, existingFileHelper));

        //block tags
        BlockTagsProvider btp = new ModBlocksTagProvider(output, registries, existingFileHelper);
        gen.addProvider(event.includeServer(), btp);

        //item tags
        ItemTagsProvider itp = new ModItemsTagProvider(output, registries, btp.contentsGetter(), existingFileHelper);
        gen.addProvider(event.includeServer(), itp);
    }
}
