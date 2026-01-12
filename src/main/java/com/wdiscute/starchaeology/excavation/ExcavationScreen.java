package com.wdiscute.starchaeology.excavation;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ExcavationScreen extends Screen
{
    public ExcavationScreen()
    {
        super(Component.empty());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        super.render(guiGraphics, mouseX, mouseY, partialTick);




        guiGraphics.fill(0, 0, 100, 100, 0xff000000);
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}
