package com.wdiscute.starchaeology;

import com.mojang.logging.LogUtils;
import com.wdiscute.starchaeology.registry.blocks.ModBlocks;
import com.wdiscute.starchaeology.io.AntiquaProperties;
import com.wdiscute.starchaeology.registry.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Starchaeology.MOD_ID)
public class Starchaeology
{
    public static final String MOD_ID = "starchaeology";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final ResourceKey<Registry<AntiquaProperties>> ANTIQUA_REGISTRY =
            ResourceKey.createRegistryKey(Starchaeology.rl("fish"));

    public static ResourceLocation rl(String s)
    {
        return ResourceLocation.fromNamespaceAndPath(Starchaeology.MOD_ID, s);
    }


    public Starchaeology(IEventBus modEventBus, ModContainer modContainer)
    {
        //registers
//        ModCreativeModeTabs.register(modEventBus);
        ModItems.ITEMS_REGISTRY.register(modEventBus);
        ModBlocks.register(modEventBus);
//        ModBlockEntities.register(modEventBus);
//        ModDataComponents.register(modEventBus);
//        ModSounds.register(modEventBus);
//        ModEntities.register(modEventBus);
//        ModParticles.register(modEventBus);
//        ModRecipes.register(modEventBus);
//        ModMenuTypes.register(modEventBus);
//        ModDataAttachments.register(modEventBus);
//        ModSweetSpotsBehaviour.register(modEventBus);
//        ModMinigameModifiers.register(modEventBus);
//        ModCatchModifiers.register(modEventBus);
//        ModTackleSkins.register(modEventBus);
//        ModCriterionTriggers.register(modEventBus);

        //modContainer.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
        //modContainer.registerConfig(ModConfig.Type.SERVER, Config.SPEC_SERVER);
    }



}
