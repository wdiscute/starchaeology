package com.wdiscute.starchaeology.io;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Style;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

import java.util.List;

public record AntiquaProperties
                (
                Holder<Item> antiqua,
                int baseChance,
                Rarity rarity,
                WorldRestrictions wr,
                Difficulty dif
                )
{

    public static final Codec<AntiquaProperties> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BuiltInRegistries.ITEM.holderByNameCodec().fieldOf("antiqua").forGetter(AntiquaProperties::antiqua),
                    Codec.INT.fieldOf("base_chance").forGetter(AntiquaProperties::baseChance),
                    Rarity.CODEC.fieldOf("rarity").forGetter(AntiquaProperties::rarity),
                    WorldRestrictions.CODEC.fieldOf("world_restrictions").forGetter(AntiquaProperties::wr),
                    Difficulty.CODEC.fieldOf("difficulty").forGetter(AntiquaProperties::dif)
            ).apply(instance, AntiquaProperties::new)
    );


    public record Difficulty(
            int width,
            int height,
            int layers
    )
    {

        public static final Codec<Difficulty> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.fieldOf("base_chance").forGetter(Difficulty::width),
                        Codec.INT.fieldOf("base_chance").forGetter(Difficulty::height),
                        Codec.INT.fieldOf("base_chance").forGetter(Difficulty::layers)
                ).apply(instance, Difficulty::new)
        );

    }

    public record WorldRestrictions(
            List<ResourceLocation> dims,
            List<ResourceLocation> dimsBlacklist,
            List<ResourceLocation> biomes,
            List<ResourceLocation> biomesTags,
            List<ResourceLocation> biomesBlacklist,
            List<ResourceLocation> biomesBlacklistTags,
            int mustBeCaughtBelowY,
            int mustBeCaughtAboveY
    )
    {
        public static final WorldRestrictions DEFAULT = new WorldRestrictions(
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                Integer.MAX_VALUE,
                Integer.MIN_VALUE);

        public static final Codec<WorldRestrictions> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.list(ResourceLocation.CODEC).fieldOf("dimensions").forGetter(WorldRestrictions::dims),
                        Codec.list(ResourceLocation.CODEC).fieldOf("dimensions_blacklist").forGetter(WorldRestrictions::dimsBlacklist),
                        Codec.list(ResourceLocation.CODEC).fieldOf("biomes").forGetter(WorldRestrictions::biomes),
                        Codec.list(ResourceLocation.CODEC).fieldOf("biomes_tags").forGetter(WorldRestrictions::biomesTags),
                        Codec.list(ResourceLocation.CODEC).fieldOf("biomes_blacklist").forGetter(WorldRestrictions::biomesBlacklist),
                        Codec.list(ResourceLocation.CODEC).fieldOf("biomes_blacklist_tags").forGetter(WorldRestrictions::biomesBlacklistTags),
                        Codec.INT.fieldOf("below_y").forGetter(WorldRestrictions::mustBeCaughtBelowY),
                        Codec.INT.fieldOf("above_y").forGetter(WorldRestrictions::mustBeCaughtAboveY)
                ).apply(instance, WorldRestrictions::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, WorldRestrictions> STREAM_CODEC = ExtraComposites.composite(
                ByteBufCodecs.fromCodec(Codec.list(ResourceLocation.CODEC)), WorldRestrictions::dims,
                ByteBufCodecs.fromCodec(Codec.list(ResourceLocation.CODEC)), WorldRestrictions::dimsBlacklist,
                ByteBufCodecs.fromCodec(Codec.list(ResourceLocation.CODEC)), WorldRestrictions::biomes,
                ByteBufCodecs.fromCodec(Codec.list(ResourceLocation.CODEC)), WorldRestrictions::biomesTags,
                ByteBufCodecs.fromCodec(Codec.list(ResourceLocation.CODEC)), WorldRestrictions::biomesBlacklist,
                ByteBufCodecs.fromCodec(Codec.list(ResourceLocation.CODEC)), WorldRestrictions::biomesBlacklistTags,
                ByteBufCodecs.VAR_INT, WorldRestrictions::mustBeCaughtBelowY,
                ByteBufCodecs.VAR_INT, WorldRestrictions::mustBeCaughtAboveY,
                WorldRestrictions::new
        );



        public WorldRestrictions withDims(ResourceLocation... dims)
        {
            return new WorldRestrictions(List.of(dims), this.dimsBlacklist, this.biomes, this.biomesTags, this.biomesBlacklist, this.biomesBlacklistTags, this.mustBeCaughtBelowY, this.mustBeCaughtAboveY);
        }

        public WorldRestrictions withDimsBlacklist(ResourceLocation... dimsBlacklist)
        {
            return new WorldRestrictions(this.dims, List.of(dimsBlacklist), this.biomes, this.biomesTags, this.biomesBlacklist, this.biomesBlacklistTags, this.mustBeCaughtBelowY, this.mustBeCaughtAboveY);
        }

        public WorldRestrictions withBiomes(ResourceLocation... biome)
        {
            return new WorldRestrictions(this.dims, this.dimsBlacklist, List.of(biome), this.biomesTags, this.biomesBlacklist, this.biomesBlacklistTags, this.mustBeCaughtBelowY, this.mustBeCaughtAboveY);
        }

        public WorldRestrictions withBiomesTags(ResourceLocation... biomesTag)
        {
            return new WorldRestrictions(this.dims, this.dimsBlacklist, this.biomes, List.of(biomesTag), this.biomesBlacklist, this.biomesBlacklistTags, this.mustBeCaughtBelowY, this.mustBeCaughtAboveY);
        }

        public WorldRestrictions withBiomesBlacklist(ResourceLocation... biomesBlacklist)
        {
            return new WorldRestrictions(this.dims, this.dimsBlacklist, this.biomes, this.biomesTags, List.of(biomesBlacklist), this.biomesBlacklistTags, this.mustBeCaughtBelowY, this.mustBeCaughtAboveY);
        }

        public WorldRestrictions withBiomesBlacklistTags(ResourceLocation... biomesBlacklistTags)
        {
            return new WorldRestrictions(this.dims, this.dimsBlacklist, this.biomes, this.biomesTags, this.biomesBlacklist, List.of(biomesBlacklistTags), this.mustBeCaughtBelowY, this.mustBeCaughtAboveY);
        }

        public WorldRestrictions withMustBeCaughtBelowY(int mustBeCaughtBelowY)
        {
            return new WorldRestrictions(this.dims, this.dimsBlacklist, this.biomes, this.biomesTags, this.biomesBlacklist, this.biomesBlacklistTags, mustBeCaughtBelowY, this.mustBeCaughtAboveY);
        }

        public WorldRestrictions withMustBeCaughtAboveY(int mustBeCaughtAboveY)
        {
            return new WorldRestrictions(this.dims, this.dimsBlacklist, this.biomes, this.biomesTags, this.biomesBlacklist, this.biomesBlacklistTags, this.mustBeCaughtBelowY, mustBeCaughtAboveY);
        }

    }

    public enum Rarity implements StringRepresentable
    {
        COMMON("common", 4, "", "", Style.EMPTY.applyFormat(ChatFormatting.WHITE), 40),
        UNCOMMON("uncommon", 8, "<gradient-37>", "</gradient-43>", Style.EMPTY.applyFormat(ChatFormatting.GREEN), 40),
        RARE("rare", 12, "<gradient-57>", "</gradient-63>", Style.EMPTY.applyFormat(ChatFormatting.BLUE), 30),
        EPIC("epic", 20, "<gradient-80>", "</gradient-90>", Style.EMPTY.applyFormat(ChatFormatting.LIGHT_PURPLE), 10),
        LEGENDARY("legendary", 35, "<rgb>", "</rgb>", Style.EMPTY.applyFormat(ChatFormatting.GOLD), 10);

        public static final Codec<Rarity> CODEC = StringRepresentable.fromEnum(Rarity::values);
        public static final StreamCodec<FriendlyByteBuf, Rarity> STREAM_CODEC = NeoForgeStreamCodecs.enumCodec(Rarity.class);
        private final String key;
        private final int xp;
        private final String pre;
        private final String post;
        private final Style style;
        private final int stoneHookGraceTicks;

        Rarity(String key, int xp, String pre, String post, Style style, int stoneHookGraceTicks)
        {
            this.key = key;
            this.xp = xp;
            this.pre = pre;
            this.post = post;
            this.style = style;
            this.stoneHookGraceTicks = stoneHookGraceTicks;
        }

        public String getSerializedName()
        {
            return this.key;
        }

        public int getStoneHookGraceTicks()
        {
            return stoneHookGraceTicks;
        }

        public int getId()
        {
            return this.ordinal();
        }

        public int getXp()
        {
            return xp;
        }

        public String getPre()
        {
            return pre;
        }

        public String getPost()
        {
            return post;
        }

        public Style getStyle()
        {
            return style;
        }
    }
}
