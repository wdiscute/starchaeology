package com.wdiscute.starchaeology.registry.blocks;

import com.wdiscute.starchaeology.excavation.ExcavationScreen;
import com.wdiscute.starchaeology.registry.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class DiggingSpotBlock extends Block
{
    public DiggingSpotBlock()
    {
        super(BlockBehaviour.Properties.of());
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if(stack.is(ModItems.ARCHEOLOGY_TOOLS))
        {
            if(level.isClientSide) openScreen();
            return ItemInteractionResult.SUCCESS;
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @OnlyIn(Dist.CLIENT)
    private void openScreen()
    {
        Minecraft.getInstance().setScreen(new ExcavationScreen());
    }
}
