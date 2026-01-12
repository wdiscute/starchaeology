package com.wdiscute.starchaeology.excavation;

import com.wdiscute.starchaeology.Starchaeology;
import com.wdiscute.starchaeology.U;
import com.wdiscute.starchaeology.io.AntiquaProperties;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class ExcavationScreen extends Screen
{
    public static final ResourceLocation BACKGROUND_TILE = Starchaeology.rl("textures/gui/excavation/tiles/background.png");

    AntiquaProperties ap;
    List<ResourceLocation> layers;
    List<List<ResourceLocation>> tiles = new ArrayList<>();
    int rowsCount;
    int columnCount;
    ItemStack antiqua;
    int antiquaRow;
    int antiquaColumn;

    int movesRemaining;

    Tool tool = Tool.BRUSH;

    int uiX;
    int uiY;

    public ExcavationScreen(AntiquaProperties ap)
    {
        super(Component.empty());
        this.ap = ap;

        antiqua = new ItemStack(ap.antiqua());
        antiquaRow = 2;
        antiquaColumn = 2;
        movesRemaining = ap.dif().moves();

        layers = ap.dif().layers();

        rowsCount = ap.dif().rows();
        columnCount = ap.dif().columns();

        for (int i = 0; i < ap.dif().rows(); i++)
        {
            for (int j = 0; j < ap.dif().columns(); j++)
            {
                List<ResourceLocation> layersOfThisTile = new ArrayList<>(layers.stream().skip(U.r.nextInt(2)).toList());
                tiles.add(layersOfThisTile);
            }
        }
    }

    @Override
    protected void init()
    {
        super.init();
        uiX = (width - columnCount * 20) / 2;
        uiY = (height - rowsCount * 20) / 2;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        Component compTool = Component.translatable("gui.stararchaelogy.excavation.tool").append(Component.translatable(tool.getTranslation()));
        Component compMoves = Component.translatable("gui.stararchaelogy.excavation.moves").append(movesRemaining + "");

        guiGraphics.drawString(this.font, compTool, uiX, uiY - rowsCount / 2 - 20, 0xff000000, false);
        guiGraphics.drawString(this.font, compMoves, uiX, uiY - rowsCount / 2 - 10, 0xff000000, false);

        int columnHover = Mth.floor((float) (mouseX - uiX) / 20);
        int rowHover = Mth.floor((float) (mouseY - uiY) / 20);

        int indexHover = columnHover + rowHover * columnCount;

        //compare rlhover for shovel stuff
        // if(indexHover >= 0 && indexHover < tiles.size())
        //ResourceLocation rlHover = tiles.get(rowHover * columnCount + columnHover).getFirst();

        for (int r = 0; r < rowsCount; r++)
        {
            for (int c = 0; c < columnCount; c++)
            {
                int tileIndex = c + r * columnCount;
                int tileTopLeftX = uiX + c * 20;
                int tileTopLeftY = uiY + r * 20;

                //render background
                guiGraphics.blit(BACKGROUND_TILE, tileTopLeftX, tileTopLeftY, 0, 0, 20, 20, 20, 20);

                //render tile's top layer unless empty
                List<ResourceLocation> layersInThisTile = tiles.get(r * columnCount + c);
                if (!layersInThisTile.isEmpty())
                    guiGraphics.blit(layersInThisTile.getFirst(), tileTopLeftX, tileTopLeftY, 0, 0, 20, 20, 20, 20);

                //brush highlight
                if (tool.equals(Tool.BRUSH) && tileIndex == indexHover)
                    guiGraphics.fill(tileTopLeftX, tileTopLeftY, tileTopLeftX + 20, tileTopLeftY + 20, 0x33ffffff);

                //todo fix highlight for shovel
                if (tool.equals(Tool.SHOVEL))
                {
                    if (tileIndex - 1 == indexHover)
                        guiGraphics.fill(tileTopLeftX, tileTopLeftY, tileTopLeftX + 20, tileTopLeftY + 20, 0x33ffffff);
                    if (tileIndex == indexHover)
                        guiGraphics.fill(tileTopLeftX, tileTopLeftY, tileTopLeftX + 20, tileTopLeftY + 20, 0x33ffffff);
                    if (tileIndex + 1 == indexHover)
                        guiGraphics.fill(tileTopLeftX, tileTopLeftY, tileTopLeftX + 20, tileTopLeftY + 20, 0x33ffffff);

                }
            }
        }

        //render antiqua
        int antiquaIndex = antiquaColumn + antiquaRow * columnCount;

        if (tiles.get(antiquaIndex).isEmpty())
            guiGraphics.renderItem(antiqua, uiX + antiquaColumn * 20 + 2, uiY + antiquaRow * 20 + 2);

    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        if (keyCode == GLFW.GLFW_KEY_TAB) tool = tool.next();


        if (keyCode == GLFW.GLFW_KEY_D) movesRemaining = 20;

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        int column = Mth.floor((mouseX - uiX) / 20);
        int row = Mth.floor((mouseY - uiY) / 20);


        if (row < rowsCount && column < columnCount && row >= 0 && column >= 0 && movesRemaining > 0)
        {
            int index = column + row * columnCount;

            movesRemaining--;

            System.out.println("clicked on tile " + row + " - " + column + ". Index " + index);


            switch (tool)
            {
                case BRUSH ->
                {
                    if (!tiles.get(index).isEmpty())
                    {
                        tiles.get(index).removeFirst();
                    }
                }

                case PICKAXE ->
                {
                    for (int i = 0; i < 3; i++)
                    {
                        if (!tiles.get(index).isEmpty())
                        {
                            tiles.get(index).removeFirst();
                        }
                    }
                }
            }

        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public enum Tool
    {
        BRUSH("gui.stararchaelogy.excavation.tool.brush"),
        SHOVEL("gui.stararchaelogy.excavation.tool.shovel"),
        PICKAXE("gui.stararchaelogy.excavation.tool.pickaxe");

        Tool(String name)
        {
            this.translation = name;
        }

        public String toString()
        {
            return this.translation;
        }

        //public static final Codec<Tool> CODEC = StringRepresentable.fromEnum(Tool::values);
        //public static final StreamCodec<RegistryFriendlyByteBuf, Tool> STREAM_CODEC = NeoForgeStreamCodecs.enumCodec(Tool.class);
        private final String translation;

        public String getTranslation()
        {
            return this.translation;
        }

        private static final Tool[] vals = values();

        public Tool previous()
        {
            if (this.ordinal() == 0) return vals[vals.length - 1];
            return vals[(this.ordinal() - 1) % vals.length];
        }

        public Tool next()
        {
            return vals[(this.ordinal() + 1) % vals.length];
        }
    }
}
