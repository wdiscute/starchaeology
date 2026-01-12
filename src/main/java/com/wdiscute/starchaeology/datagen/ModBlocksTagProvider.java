package com.wdiscute.starchaeology.datagen;

import com.wdiscute.starchaeology.Starchaeology;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlocksTagProvider extends BlockTagsProvider
{

    public ModBlocksTagProvider(PackOutput output,
                                CompletableFuture<HolderLookup.Provider> lookupProvider,
                                 @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, Starchaeology.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {

    }
}
