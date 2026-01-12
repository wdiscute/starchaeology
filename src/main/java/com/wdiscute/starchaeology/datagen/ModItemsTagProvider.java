package com.wdiscute.starchaeology.datagen;

import com.wdiscute.starchaeology.Starchaeology;
import com.wdiscute.starchaeology.registry.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemsTagProvider extends ItemTagsProvider
{

    public ModItemsTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, blockTags, Starchaeology.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        //fishes, cat_food, foods/raw_fish
        for (var item : ModItems.ITEMS_REGISTRY.getEntries())
        {
            //tag(StarchaeologyTags.STARCAUGHT_FISHES).add(item.get());
        }

        //optional tags
        //tag(StarchaeologyTags.BAITS).addOptional(rl("tfc", "food/bluegill"));


    }

    public static ResourceLocation rl(String ns, String path)
    {
        return ResourceLocation.fromNamespaceAndPath(ns, path);
    }
}
