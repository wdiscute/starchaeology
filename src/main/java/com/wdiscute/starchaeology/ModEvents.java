package com.wdiscute.starchaeology;

import com.wdiscute.starchaeology.io.AntiquaProperties;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@EventBusSubscriber(modid = Starchaeology.MOD_ID)
public class ModEvents
{
    @SubscribeEvent
    public static void addDatapackRegistry(DataPackRegistryEvent.NewRegistry event)
    {
        event.dataPackRegistry(
                Starchaeology.ANTIQUA_REGISTRY, AntiquaProperties.CODEC, AntiquaProperties.CODEC,
                builder -> builder.maxId(512));
    }

}
