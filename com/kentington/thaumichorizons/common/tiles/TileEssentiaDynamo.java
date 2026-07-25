/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.common.lib.research.ResearchManager
 */
package com.kentington.thaumichorizons.common.tiles;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.visnet.TileVisNode;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.lib.research.ResearchManager;

public class TileEssentiaDynamo
extends TileVisNode
implements IAspectContainer,
IEssentiaTransport {
    AspectList primalsActuallyProvided = new AspectList();
    AspectList primalsProvided = new AspectList();
    public Aspect essentia = null;
    public int ticksProvided = 0;
    public float rise = 0.0f;
    public float rotation = 0.0f;
    public float rotation2 = 0.0f;

    @Override
    public AspectList getAspects() {
        if (this.primalsProvided.getAspects().length > 0 && this.primalsProvided.getAspects()[0] != null) {
            return this.primalsProvided;
        }
        return null;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return false;
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    @Override
    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return 0;
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public boolean isSource() {
        return this.ticksProvided > 0;
    }

    @Override
    public int consumeVis(Aspect aspect, int amount) {
        int drain = Math.min(this.primalsActuallyProvided.getAmount(aspect), amount);
        if (drain > 0) {
            this.primalsActuallyProvided.reduce(aspect, drain);
        }
        return drain;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face == ForgeDirection.DOWN;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return face == ForgeDirection.DOWN;
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return false;
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
    }

    @Override
    public Aspect getSuctionType(ForgeDirection face) {
        return null;
    }

    @Override
    public int getSuctionAmount(ForgeDirection face) {
        if (this.ticksProvided <= 20) {
            return 128;
        }
        return 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        this.ticksProvided += 21;
        this.essentia = aspect;
        if (VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g) == null) {
            VisNetHandler.sources.put(this.field_145850_b.field_73011_w.field_76574_g, new HashMap());
        }
        if (VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).get(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g)) == null) {
            VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).put(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g), new WeakReference<TileEssentiaDynamo>(this));
        } else if (VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).get(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g)).get() == null) {
            VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).remove(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g));
            VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).put(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g), new WeakReference<TileEssentiaDynamo>(this));
        }
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return 1;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection face) {
        return this.essentia;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection face) {
        return 0;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }

    @Override
    public void func_145845_h() {
        super.func_145845_h();
        if (this.ticksProvided >= 0) {
            if (this.rise < 0.3f) {
                this.rise += 0.02f;
            } else {
                this.rotation2 += 2.0f;
                if (this.rotation2 >= 360.0f) {
                    this.rotation2 -= 360.0f;
                }
            }
            this.rotation += 2.0f;
            if (this.rotation >= 360.0f) {
                this.rotation -= 360.0f;
            }
        } else if (this.ticksProvided < 0 && (this.rise > 0.0f || this.rotation2 != 0.0f)) {
            if (this.rotation2 > 0.0f) {
                this.rotation2 -= 8.0f;
                if (this.rotation2 < 0.0f) {
                    this.rotation2 = 0.0f;
                }
            } else if (this.rise > 0.0f) {
                this.rise -= 0.02f;
            }
        }
        if (this.ticksProvided > 0) {
            this.primalsProvided = ResearchManager.reduceToPrimals((AspectList)new AspectList().add(this.essentia, 1));
            int numEach = 12 / this.primalsProvided.size();
            for (Aspect asp : this.primalsProvided.getAspects()) {
                int num = this.primalsProvided.getAmount(asp);
                if (num > numEach) {
                    this.primalsProvided.reduce(asp, num - numEach);
                    continue;
                }
                if (num >= numEach) continue;
                this.primalsProvided.add(asp, numEach - num);
            }
            this.primalsActuallyProvided = this.primalsProvided.copy();
            --this.ticksProvided;
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            }
        } else if (this.ticksProvided == 0) {
            if (!this.field_145850_b.field_72995_K && !this.drawEssentia() && VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g) != null) {
                --this.ticksProvided;
                this.killMe();
                this.primalsProvided = new AspectList();
                this.primalsActuallyProvided = new AspectList();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            }
        } else if (!this.field_145850_b.field_72995_K && this.ticksProvided < 0) {
            this.drawEssentia();
        }
    }

    public void killMe() {
        if (VisNetHandler.sources != null && this.field_145850_b != null && this.field_145850_b.field_73011_w != null && VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g) != null) {
            VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).remove(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g));
            this.removeThisNode();
        }
    }

    boolean drawEssentia() {
        TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, ForgeDirection.DOWN);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport)te;
            if (!ic.canOutputTo(ForgeDirection.UP)) {
                return false;
            }
            Aspect ta = null;
            if (ic.getEssentiaAmount(ForgeDirection.UP) > 0 && ic.getSuctionAmount(ForgeDirection.UP) < this.getSuctionAmount(ForgeDirection.DOWN) && this.getSuctionAmount(ForgeDirection.DOWN) >= ic.getMinimumSuction()) {
                ta = ic.getEssentiaType(ForgeDirection.UP);
            }
            if (ta != null && ic.takeEssentia(ta, 1, ForgeDirection.UP) == 1) {
                this.addEssentia(ta, 1, ForgeDirection.DOWN);
                return true;
            }
        }
        return false;
    }

    public void debug() {
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        if (this.essentia != null) {
            nbttagcompound.func_74778_a("key", this.essentia.getTag());
        }
        nbttagcompound.func_74768_a("ticks", this.ticksProvided);
        NBTTagList tlist = new NBTTagList();
        nbttagcompound.func_74782_a("AspectsProvided", (NBTBase)tlist);
        for (Aspect aspect : this.primalsProvided.getAspects()) {
            if (aspect == null) continue;
            NBTTagCompound f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.primalsProvided.getAmount(aspect));
            tlist.func_74742_a((NBTBase)f);
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.essentia = Aspect.getAspect(nbttagcompound.func_74779_i("key"));
        this.ticksProvided = nbttagcompound.func_74762_e("ticks");
        AspectList al = new AspectList();
        NBTTagList tlist = nbttagcompound.func_150295_c("AspectsProvided", 10);
        for (int j = 0; j < tlist.func_74745_c(); ++j) {
            NBTTagCompound rs = tlist.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            al.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        this.primalsProvided = al.copy();
        this.primalsActuallyProvided = this.primalsProvided.copy();
        if (this.ticksProvided < 0) {
            this.killMe();
        }
    }
}

