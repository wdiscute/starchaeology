package com.wdiscute.starchaeology.registry.blocks;

import com.wdiscute.starchaeology.Starchaeology;
import com.wdiscute.starchaeology.registry.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface ModBlocks
{
    DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Starchaeology.MOD_ID);

    DeferredBlock<Block> DIRT = registerBlock("dirt", DiggingSpotBlock::new);


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS_REGISTRY.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
