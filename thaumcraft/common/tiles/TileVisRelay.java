/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.visnet.TileVisNode;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;

public class TileVisRelay
extends TileVisNode
implements IWandable {
    public short orientation = 1;
    public byte color = (byte)-1;
    public static HashMap<Integer, WeakReference<TileVisRelay>> nearbyPlayers = new HashMap();
    protected Object beam1 = null;
    protected int pulse;
    public float pRed = 0.5f;
    public float pGreen = 0.5f;
    public float pBlue = 0.5f;
    public static final int[] colors = new int[]{0xFFFF7E, 16727041, 37119, 40960, 0xEECCFF, 0x555577};
    protected int px;
    protected int py;
    protected int pz;
    protected boolean parentLoaded = false;

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
    }

    @Override
    public byte getAttunement() {
        return this.color;
    }

    @Override
    public int getRange() {
        return 8;
    }

    @Override
    public boolean isSource() {
        return false;
    }

    @Override
    public void parentChanged() {
        if (this.field_145850_b != null && this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_147463_c(EnumSkyBlock.Block, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    @Override
    public void func_145843_s() {
        this.beam1 = null;
        super.func_145843_s();
    }

    @Override
    public void func_145845_h() {
        List var5;
        this.drawEffect();
        super.func_145845_h();
        if (!this.field_145850_b.field_72995_K && this.nodeCounter % 20 == 0 && (var5 = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(5.0, 5.0, 5.0))) != null && var5.size() > 0) {
            for (EntityPlayer player : var5) {
                if (nearbyPlayers.containsKey(player.func_145782_y()) && nearbyPlayers.get(player.func_145782_y()).get() != null && ((TileVisRelay)nearbyPlayers.get(player.func_145782_y()).get()).func_145835_a(player.field_70165_t, player.field_70163_u, player.field_70161_v) < this.func_145835_a(player.field_70165_t, player.field_70163_u, player.field_70161_v)) continue;
                nearbyPlayers.put(player.func_145782_y(), new WeakReference<TileVisRelay>(this));
            }
        }
    }

    protected void drawEffect() {
        if (this.field_145850_b.field_72995_K) {
            if (this.parentLoaded) {
                if (this.px != 0 || this.py != 0 || this.pz != 0) {
                    TileEntity tile = this.func_145831_w().func_147438_o(this.field_145851_c - this.px, this.field_145848_d - this.py, this.field_145849_e - this.pz);
                    if (tile != null && tile instanceof TileVisNode) {
                        this.setParent(new WeakReference<TileEntity>(tile));
                    }
                } else {
                    this.setParent(null);
                }
                this.parentLoaded = false;
                this.parentChanged();
            }
            if (VisNetHandler.isNodeValid(this.getParent())) {
                double xx = (double)((TileVisNode)((Object)this.getParent().get())).field_145851_c + 0.5;
                double yy = (double)((TileVisNode)((Object)this.getParent().get())).field_145848_d + 0.5;
                double zz = (double)((TileVisNode)((Object)this.getParent().get())).field_145849_e + 0.5;
                ForgeDirection d1 = ForgeDirection.UNKNOWN;
                if (this.getParent().get() instanceof TileVisRelay) {
                    d1 = ForgeDirection.getOrientation((int)((TileVisRelay)this.getParent().get()).orientation);
                }
                ForgeDirection d2 = ForgeDirection.getOrientation((int)this.orientation);
                this.beam1 = Thaumcraft.proxy.beamPower(this.field_145850_b, xx - (double)d1.offsetX * 0.05, yy - (double)d1.offsetY * 0.05, zz - (double)d1.offsetZ * 0.05, (double)this.field_145851_c + 0.5 - (double)d2.offsetX * 0.05, (double)this.field_145848_d + 0.5 - (double)d2.offsetY * 0.05, (double)this.field_145849_e + 0.5 - (double)d2.offsetZ * 0.05, this.pRed, this.pGreen, this.pBlue, this.pulse > 0, this.beam1);
            }
            if (this.pRed < 1.0f) {
                this.pRed += 0.025f;
            }
            if (this.pRed > 1.0f) {
                this.pRed = 1.0f;
            }
            if (this.pGreen < 1.0f) {
                this.pGreen += 0.025f;
            }
            if (this.pGreen > 1.0f) {
                this.pGreen = 1.0f;
            }
            if (this.pBlue < 1.0f) {
                this.pBlue += 0.025f;
            }
            if (this.pBlue > 1.0f) {
                this.pBlue = 1.0f;
            }
        }
        if (this.pulse > 0) {
            --this.pulse;
        }
    }

    @Override
    public void triggerConsumeEffect(Aspect aspect) {
        this.addPulse(aspect);
    }

    protected void addPulse(Aspect aspect) {
        int c = -1;
        if (aspect == Aspect.AIR) {
            c = 0;
        } else if (aspect == Aspect.FIRE) {
            c = 1;
        } else if (aspect == Aspect.WATER) {
            c = 2;
        } else if (aspect == Aspect.EARTH) {
            c = 3;
        } else if (aspect == Aspect.ORDER) {
            c = 4;
        } else if (aspect == Aspect.ENTROPY) {
            c = 5;
        }
        if (c >= 0 && this.pulse == 0) {
            this.pulse = 5;
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), 0, c);
        }
    }

    public boolean func_145842_c(int i, int j) {
        if (i == 0) {
            if (this.field_145850_b.field_72995_K) {
                Color c = new Color(colors[j]);
                this.pulse = 5;
                this.pRed = (float)c.getRed() / 255.0f;
                this.pGreen = (float)c.getGreen() / 255.0f;
                this.pBlue = (float)c.getBlue() / 255.0f;
                WeakReference<TileVisNode> vr = this.getParent();
                while (VisNetHandler.isNodeValid(vr) && vr.get() instanceof TileVisRelay && ((TileVisRelay)vr.get()).pulse == 0) {
                    ((TileVisRelay)vr.get()).pRed = this.pRed;
                    ((TileVisRelay)vr.get()).pGreen = this.pGreen;
                    ((TileVisRelay)vr.get()).pBlue = this.pBlue;
                    ((TileVisRelay)vr.get()).pulse = 5;
                    vr = ((TileVisNode)((Object)vr.get())).getParent();
                }
            }
            return true;
        }
        return super.func_145842_c(i, j);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.orientation = nbttagcompound.func_74765_d("orientation");
        this.color = nbttagcompound.func_74771_c("color");
        this.px = nbttagcompound.func_74771_c("px");
        this.py = nbttagcompound.func_74771_c("py");
        this.pz = nbttagcompound.func_74771_c("pz");
        this.parentLoaded = true;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74777_a("orientation", this.orientation);
        nbttagcompound.func_74774_a("color", this.color);
        if (VisNetHandler.isNodeValid(this.getParent())) {
            nbttagcompound.func_74774_a("px", (byte)(this.field_145851_c - ((TileVisNode)((Object)this.getParent().get())).field_145851_c));
            nbttagcompound.func_74774_a("py", (byte)(this.field_145848_d - ((TileVisNode)((Object)this.getParent().get())).field_145848_d));
            nbttagcompound.func_74774_a("pz", (byte)(this.field_145849_e - ((TileVisNode)((Object)this.getParent().get())).field_145849_e));
        } else {
            nbttagcompound.func_74774_a("px", (byte)0);
            nbttagcompound.func_74774_a("py", (byte)0);
            nbttagcompound.func_74774_a("pz", (byte)0);
        }
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        if (!this.field_145850_b.field_72995_K) {
            this.color = (byte)(this.color + 1);
            if (this.color > 5) {
                this.color = (byte)-1;
            }
            this.removeThisNode();
            this.nodeRefresh = true;
            this.func_70296_d();
            world.func_147471_g(x, y, z);
            world.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:crystal", 0.2f, 1.0f);
        }
        return 0;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return null;
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }
}

