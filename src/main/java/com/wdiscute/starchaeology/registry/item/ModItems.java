package com.wdiscute.starchaeology.registry.item;

import com.wdiscute.starchaeology.Starchaeology;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModItems
{
    DeferredRegister.Items ITEMS_REGISTRY = DeferredRegister.createItems(Starchaeology.MOD_ID);

    DeferredItem<Item> ARCHEOLOGY_TOOLS = ITEMS_REGISTRY.register("tools", () -> new Item(new Item.Properties()));


}
