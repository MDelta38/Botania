/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.gui.GuiButtonMagicTile;
import witchinggadgets.common.blocks.tiles.TileEntityMagicalTileLock;
import witchinggadgets.common.util.network.message.MessageTileUpdate;

public class GuiMagicalTileLock
extends GuiScreen {
    List<Integer> stored = new ArrayList<Integer>();
    public static final HashMap<String, int[]> presets = new HashMap();
    ResourceLocation texture = new ResourceLocation("witchinggadgets:textures/gui/tileLock.png");
    boolean unlocked = false;
    int step = -1;
    int[] solving = new int[0];
    public static GuiButtonMagicTile currentTile;
    TileEntityMagicalTileLock tileentity;
    int xSize = 128;
    int ySize = 128;

    public GuiMagicalTileLock(TileEntityMagicalTileLock tileentity) {
        currentTile = null;
        this.tileentity = tileentity;
        this.unlocked = this.tileentity.unlocked;
    }

    public void func_73866_w_() {
        int guiLeft = (this.field_146294_l - this.xSize) / 2;
        int guiTop = (this.field_146295_m - this.xSize) / 2;
        this.field_146292_n.clear();
        int r = this.tileentity.lockPreset;
        for (int ii = 0; ii < 8; ++ii) {
            int id = this.unlocked ? ii : Integer.parseInt(presets.keySet().toArray(new String[0])[r].split(",")[ii]);
            this.field_146292_n.add(new GuiButtonMagicTile(id, guiLeft + 19 + ii % 3 * 30, guiTop + 19 + ii / 3 * 30));
        }
    }

    public void func_73863_a(int mX, int mY, float f) {
        int guiLeft = (this.field_146294_l - this.xSize) / 2;
        int guiTop = (this.field_146295_m - this.xSize) / 2;
        this.field_146297_k.func_110434_K().func_110577_a(this.texture);
        this.func_73729_b(guiLeft + 0, guiTop + 0, 0, 0, this.xSize, this.xSize);
        if (!this.unlocked) {
            GL11.glColor3f((float)0.1f, (float)0.0f, (float)0.2f);
        }
        this.func_73729_b(guiLeft + 19, guiTop + 19, 128, 30, 90, 90);
        super.func_73863_a(mX, mY, f);
        if (this.unlocked) {
            this.func_73732_a(this.field_146289_q, "UNLOCKED", guiLeft + 64, guiTop + 6, 0xFFFFFF);
        }
        if (!this.unlocked) {
            if (this.solving != null && this.step >= 0 && this.step < this.solving.length && currentTile == null) {
                for (int i = 0; i < 8; ++i) {
                    if (((GuiButton)this.field_146292_n.get((int)i)).field_146127_k != this.solving[31 - this.step]) continue;
                    this.func_146284_a((GuiButton)this.field_146292_n.get(i));
                }
                ++this.step;
            }
            if (currentTile == null) {
                boolean b = true;
                this.tileentity.tiles = new byte[9];
                for (int i = 0; i < 8; ++i) {
                    GuiButton button = (GuiButton)this.field_146292_n.get(i);
                    int bX = button.field_146128_h - guiLeft - 19;
                    int bY = button.field_146129_i - guiTop - 19;
                    if (bX != button.field_146127_k % 3 * 30) {
                        b = false;
                    }
                    if (bY != button.field_146127_k / 3 * 30) {
                        b = false;
                    }
                    if (bX == 0 && bY == 0) {
                        this.tileentity.tiles[0] = 1;
                    }
                    if (bX == 30 && bY == 0) {
                        this.tileentity.tiles[1] = 1;
                    }
                    if (bX == 60 && bY == 0) {
                        this.tileentity.tiles[2] = 1;
                    }
                    if (bX == 0 && bY == 30) {
                        this.tileentity.tiles[3] = 1;
                    }
                    if (bX == 30 && bY == 30) {
                        this.tileentity.tiles[4] = 1;
                    }
                    if (bX == 60 && bY == 30) {
                        this.tileentity.tiles[5] = 1;
                    }
                    if (bX == 0 && bY == 60) {
                        this.tileentity.tiles[6] = 1;
                    }
                    if (bX == 30 && bY == 60) {
                        this.tileentity.tiles[7] = 1;
                    }
                    if (bX != 60 || bY != 60) continue;
                    this.tileentity.tiles[8] = 1;
                }
                if (b) {
                    this.unlocked = true;
                    this.tileentity.unlocked = true;
                    this.tileentity.tick = 0;
                    WitchingGadgets.packetHandler.sendToServer((IMessage)new MessageTileUpdate(this.tileentity));
                }
            }
        }
    }

    public boolean func_73868_f() {
        return false;
    }

    protected void func_146284_a(GuiButton button) {
        if (currentTile == null && !this.unlocked) {
            GuiButtonMagicTile tile = (GuiButtonMagicTile)button;
            int guiLeft = (this.field_146294_l - this.xSize) / 2;
            int guiTop = (this.field_146295_m - this.xSize) / 2;
            int x = button.field_146128_h;
            int y = button.field_146129_i;
            boolean hasLeft = false;
            boolean hasRight = false;
            boolean hasTop = false;
            boolean hasBot = false;
            for (Object o : this.field_146292_n) {
                if (((GuiButton)o).field_146128_h - x == -30 && ((GuiButton)o).field_146129_i == y) {
                    hasLeft = true;
                }
                if (((GuiButton)o).field_146128_h - x == 30 && ((GuiButton)o).field_146129_i == y) {
                    hasRight = true;
                }
                if (((GuiButton)o).field_146128_h == x && ((GuiButton)o).field_146129_i - y == -30) {
                    hasTop = true;
                }
                if (((GuiButton)o).field_146128_h != x || ((GuiButton)o).field_146129_i - y != 30) continue;
                hasBot = true;
            }
            boolean moveLeft = (x -= guiLeft) > 19 && !hasLeft;
            boolean moveRight = x < 79 && !hasRight;
            boolean moveTop = (y -= guiTop) > 19 && !hasTop;
            boolean moveBot = y < 79 && !hasBot;
            tile.moveTop = moveTop;
            tile.moveBottom = moveBot;
            tile.moveLeft = moveLeft;
            tile.moveRight = moveRight;
            tile.moveProgress = 1;
            currentTile = tile;
            if (moveTop || moveBot || moveLeft || moveRight) {
                this.stored.add(button.field_146127_k);
            }
        }
    }

    static {
        presets.put("0,7,6,2,4,3,5,1", new int[]{7, 4, 5, 7, 4, 6, 3, 5, 6, 4, 7, 2, 1, 6, 2, 7, 4, 2, 7, 1, 6, 7, 2, 3, 5, 2, 3, 4, 1, 3, 4, 1});
        presets.put("5,2,3,7,0,1,6,4", new int[]{7, 4, 5, 2, 1, 5, 3, 0, 5, 3, 0, 6, 4, 0, 2, 1, 3, 2, 0, 7, 1, 0, 7, 4, 6, 7, 4, 1, 1, 4, 0, 1});
        presets.put("5,2,6,1,7,0,4,3", new int[]{5, 2, 1, 4, 7, 6, 3, 7, 4, 0, 7, 4, 0, 1, 2, 0, 6, 5, 0, 6, 5, 3, 4, 5, 1, 7, 5, 1, 7, 2, 6, 0});
        presets.put("0,5,6,1,2,3,4,7", new int[]{7, 4, 1, 0, 3, 1, 0, 3, 1, 6, 4, 7, 5, 2, 3, 0, 6, 1, 0, 6, 7, 5, 2, 7, 5, 2, 7, 3, 6, 5, 2, 7});
        presets.put("4,1,2,7,0,6,3,5", new int[]{7, 4, 5, 2, 1, 5, 3, 0, 5, 3, 4, 6, 0, 4, 3, 5, 4, 3, 5, 1, 2, 7, 6, 5, 7, 6, 5, 0, 3, 7, 0, 5});
        presets.put("4,0,2,6,3,1,7,5", new int[]{7, 6, 3, 4, 1, 0, 4, 3, 6, 1, 0, 2, 5, 0, 2, 5, 0, 2, 5, 0, 2, 7, 1, 5, 7, 1, 5, 7, 3, 6, 7, 5});
        presets.put("2,4,0,7,5,6,3,1", new int[]{5, 4, 7, 6, 3, 7, 1, 0, 7, 1, 0, 2, 4, 0, 2, 7, 1, 2, 7, 1, 2, 7, 1, 4, 0, 1, 6, 5, 1, 6, 5, 1});
        presets.put("6,2,4,0,7,5,1,3", new int[]{5, 4, 7, 6, 3, 7, 6, 3, 7, 0, 1, 6, 0, 1, 6, 2, 4, 0, 3, 5, 0, 3, 5, 0, 3, 5, 0, 7, 1, 0, 7, 3});
        presets.put("6,4,0,5,2,7,1,3", new int[]{5, 4, 3, 6, 7, 3, 1, 0, 6, 1, 0, 2, 4, 0, 2, 4, 0, 5, 3, 2, 5, 3, 2, 7, 1, 5, 7, 2, 3, 7, 2, 3});
        presets.put("4,3,5,7,6,2,1,0", new int[]{7, 6, 3, 4, 1, 0, 4, 1, 5, 2, 0, 5, 6, 7, 2, 6, 7, 3, 1, 7, 3, 2, 6, 0, 5, 3, 0, 6, 2, 0, 6, 2});
        presets.put("5,1,4,2,7,0,6,3", new int[]{7, 4, 5, 2, 1, 5, 3, 0, 5, 3, 4, 7, 2, 4, 0, 6, 7, 0, 3, 1, 4, 2, 0, 3, 2, 0, 3, 7, 6, 2, 7, 3});
        presets.put("4,7,0,6,3,5,1,2", new int[]{7, 6, 3, 4, 1, 0, 4, 1, 0, 2, 5, 7, 6, 0, 2, 5, 7, 2, 0, 6, 2, 0, 6, 3, 1, 6, 5, 7, 0, 5, 3, 2});
        presets.put("3,1,2,7,4,5,6,0", new int[]{5, 4, 3, 0, 1, 3, 7, 5, 4, 2, 3, 1, 0, 7, 1, 0, 7, 1, 0, 3, 2, 0, 1, 7, 3, 1, 5, 4, 0, 5, 4, 0});
        presets.put("7,4,0,5,2,3,1,6", new int[]{5, 4, 7, 6, 3, 7, 1, 0, 7, 1, 0, 2, 4, 0, 2, 4, 0, 5, 6, 2, 5, 6, 2, 3, 1, 5, 6, 2, 3, 6, 2, 3});
        presets.put("6,5,2,7,1,0,3,4", new int[]{5, 4, 1, 0, 3, 6, 7, 1, 0, 3, 6, 0, 3, 2, 4, 5, 1, 3, 5, 4, 2, 5, 0, 7, 3, 0, 4, 1, 0, 4, 1, 0});
        presets.put("3,7,0,6,4,1,5,2", new int[]{5, 4, 1, 0, 3, 1, 0, 2, 4, 0, 7, 5, 0, 4, 2, 7, 1, 6, 5, 1, 4, 2, 7, 4, 2, 0, 1, 2, 4, 7, 0, 1});
        currentTile = null;
    }
}

