package com.wdiscute.starchaeology;

import com.wdiscute.starchaeology.datagen.TrustedHolder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Random;

public class U
{
    public static final Random r = new Random();
    public static ResourceLocation rl(String ns, String path)
    {
        return ResourceLocation.fromNamespaceAndPath(ns, path);
    }

    public static Holder<Item> holderItem(String ns, String path)
    {
        return TrustedHolder.createStandAlone(BuiltInRegistries.ITEM.holderOwner(), ResourceKey.create(Registries.ITEM, rl(ns, path)));
    }

    public static Holder<Item> holderItem(DeferredItem<Item> item)
    {
        return Holder.direct(item.get());
    }

    public static Holder<Item> holderItem(Item item)
    {
        return Holder.direct(item);
    }

    public static Holder<EntityType<?>> holderEntity(EntityType<?> entityType)
    {
        return Holder.direct(entityType);
    }

    public static Holder<EntityType<?>> holderEntity(String ns, String path)
    {
        return TrustedHolder.createStandAlone(BuiltInRegistries.ENTITY_TYPE.holderOwner(), ResourceKey.create(Registries.ENTITY_TYPE, rl(ns, path)));
    }
}
