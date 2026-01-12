package com.wdiscute.starchaeology.registry.item;

import com.wdiscute.starchaeology.Starchaeology;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public interface ModItems
{
    DeferredRegister.Items ITEMS_REGISTRY = DeferredRegister.createItems(Starchaeology.MOD_ID);

    DeferredItem<Item> ARCHEOLOGY_TOOLS = ITEMS_REGISTRY.register("tools", () -> new Item(new Item.Properties()));


    DeferredItem<Item> MISSINGNO = ITEMS_REGISTRY.register("missingno", BasicItem::new);


    //antiqua
    DeferredItem<Item> VERY_COOL_TREASURE = ITEMS_REGISTRY.register("treasure", BasicItem::new);



}
