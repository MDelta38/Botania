/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.nbt.NBTTagCompound
 */
package witchinggadgets.common.blocks.tiles;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import witchinggadgets.client.gui.GuiMagicalTileLock;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;

public class TileEntityMagicalTileLock
extends TileEntityWGBase {
    public int tick = -1;
    public int lockPreset = -1;
    public boolean unlocked = false;
    public byte[] tiles = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 0};

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (this.unlocked && this.tick >= 0) {
            if (this.field_145850_b.field_72995_K && Minecraft.func_71410_x().field_71462_r instanceof GuiMagicalTileLock) {
                Minecraft.func_71410_x().field_71462_r = null;
                Minecraft.func_71410_x().func_71381_h();
            }
            if (this.tick % 5 == 0) {
                int t = this.tick / 5;
                switch (t) {
                    case 0: {
                        this.removeBlock(-1, -1, -1);
                        this.removeBlock(1, 1, 1);
                        break;
                    }
                    case 1: {
                        this.removeBlock(1, -1, -1);
                        this.removeBlock(-1, 1, 1);
                        break;
                    }
                    case 2: {
                        this.removeBlock(-1, 1, -1);
                        this.removeBlock(1, -1, 1);
                        break;
                    }
                    case 3: {
                        this.removeBlock(1, 1, -1);
                        this.removeBlock(-1, -1, 1);
                        break;
                    }
                    case 4: {
                        this.removeBlock(-1, 0, -1);
                        this.removeBlock(1, 0, 1);
                        break;
                    }
                    case 5: {
                        this.removeBlock(1, 0, -1);
                        this.removeBlock(-1, 0, 1);
                        break;
                    }
                    case 6: {
                        this.removeBlock(0, -1, -1);
                        this.removeBlock(0, 1, 1);
                        break;
                    }
                    case 7: {
                        this.removeBlock(-1, -1, 0);
                        this.removeBlock(1, 1, 0);
                        break;
                    }
                    case 8: {
                        this.removeBlock(0, -1, 1);
                        this.removeBlock(0, 1, -1);
                        break;
                    }
                    case 9: {
                        this.removeBlock(-1, 1, 0);
                        this.removeBlock(1, -1, 0);
                        break;
                    }
                    case 10: {
                        this.removeBlock(0, 0, -1);
                        this.removeBlock(-1, 0, 0);
                        break;
                    }
                    case 11: {
                        this.removeBlock(0, 0, 1);
                        this.removeBlock(1, 0, 0);
                        break;
                    }
                    case 12: {
                        this.removeBlock(0, -1, 0);
                        break;
                    }
                    case 13: {
                        this.removeBlock(0, 1, 0);
                    }
                }
                if (this.tick >= 80) {
                    this.removeBlock(0, 0, 0);
                }
            }
            ++this.tick;
        }
    }

    void removeBlock(int x, int y, int z) {
        if (this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z).equals(WGContent.BlockStoneDevice) && (this.field_145850_b.func_72805_g(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == 2 || this.field_145850_b.func_72805_g(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == 1)) {
            this.field_145850_b.func_147468_f(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);
            this.field_145850_b.func_72908_a((double)(this.field_145851_c + x), (double)(this.field_145848_d + y), (double)(this.field_145849_e + z), "mob.endermen.portal", 1.0f, 1.0f);
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound tags) {
        this.tick = tags.func_74762_e("tick");
        this.lockPreset = tags.func_74762_e("lockPreset");
        this.unlocked = tags.func_74767_n("unlocked");
        this.tiles = tags.func_74770_j("tiles");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tags) {
        tags.func_74768_a("tick", this.tick);
        tags.func_74768_a("lockPreset", this.lockPreset);
        tags.func_74757_a("unlocked", this.unlocked);
        tags.func_74773_a("tiles", this.tiles);
    }
}

