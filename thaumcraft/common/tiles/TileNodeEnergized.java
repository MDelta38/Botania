/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.MathHelper
 */
package thaumcraft.common.tiles;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.visnet.TileVisNode;
import thaumcraft.common.lib.research.ResearchManager;

public class TileNodeEnergized
extends TileVisNode
implements IAspectContainer {
    private AspectList auraBase = new AspectList().add(Aspect.AIR, 20).add(Aspect.FIRE, 20).add(Aspect.EARTH, 20).add(Aspect.WATER, 20).add(Aspect.ORDER, 20).add(Aspect.ENTROPY, 20);
    AspectList visBase = new AspectList();
    AspectList vis = new AspectList();
    private NodeType nodeType = NodeType.NORMAL;
    private NodeModifier nodeModifier = null;
    String id = "blank";

    @Override
    public void func_145845_h() {
        super.func_145845_h();
        if (!this.field_145850_b.field_72995_K) {
            if (this.getNodeType() == NodeType.UNSTABLE && this.field_145850_b.field_73012_v.nextInt(500) == 1) {
                this.visBase = new AspectList();
            }
            if (this.visBase.size() == 0 && this.getAuraBase().size() > 0) {
                this.setupNode();
            }
            this.vis = this.visBase.copy();
        }
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public void setupNode() {
        this.visBase = new AspectList();
        AspectList temp = ResearchManager.reduceToPrimals(this.getAuraBase(), true);
        for (Aspect aspect : temp.getAspects()) {
            int amt = temp.getAmount(aspect);
            if (this.getNodeModifier() == NodeModifier.BRIGHT) {
                amt = (int)((float)amt * 1.2f);
            }
            if (this.getNodeModifier() == NodeModifier.PALE) {
                amt = (int)((float)amt * 0.8f);
            }
            if (this.getNodeModifier() == NodeModifier.FADING) {
                amt = (int)((float)amt * 0.5f);
            }
            amt = MathHelper.func_76128_c((double)MathHelper.func_76133_a((double)amt));
            if (this.getNodeType() == NodeType.UNSTABLE) {
                amt += this.field_145850_b.field_73012_v.nextInt(5) - 2;
            }
            if (amt < 1) continue;
            this.visBase.merge(aspect, amt);
        }
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
    }

    @Override
    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.id = nbttagcompound.func_74779_i("nodeId");
        this.setNodeType(NodeType.values()[nbttagcompound.func_74771_c("type")]);
        byte mod = nbttagcompound.func_74771_c("modifier");
        if (mod >= 0) {
            this.setNodeModifier(NodeModifier.values()[mod]);
        } else {
            this.setNodeModifier(null);
        }
        this.visBase.aspects.clear();
        NBTTagList tlist = nbttagcompound.func_150295_c("AEB", 10);
        for (int j = 0; j < tlist.func_74745_c(); ++j) {
            NBTTagCompound rs = tlist.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            this.visBase.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        this.getAuraBase().readFromNBT(nbttagcompound);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74778_a("nodeId", this.id);
        nbttagcompound.func_74774_a("type", (byte)this.getNodeType().ordinal());
        nbttagcompound.func_74774_a("modifier", this.getNodeModifier() == null ? (byte)-1 : (byte)this.getNodeModifier().ordinal());
        NBTTagList tlist = new NBTTagList();
        nbttagcompound.func_74782_a("AEB", (NBTBase)tlist);
        for (Aspect aspect : this.visBase.getAspects()) {
            if (aspect == null) continue;
            NBTTagCompound f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.visBase.getAmount(aspect));
            tlist.func_74742_a((NBTBase)f);
        }
        this.getAuraBase().writeToNBT(nbttagcompound);
    }

    public boolean func_145842_c(int i, int j) {
        return super.func_145842_c(i, j);
    }

    public NodeType getNodeType() {
        return this.nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public void setNodeModifier(NodeModifier nodeModifier) {
        this.nodeModifier = nodeModifier;
    }

    public NodeModifier getNodeModifier() {
        return this.nodeModifier;
    }

    @Override
    public int getRange() {
        return 8;
    }

    @Override
    public boolean isSource() {
        return true;
    }

    @Override
    public int consumeVis(Aspect aspect, int amount) {
        int drain = Math.min(this.vis.getAmount(aspect), amount);
        if (drain > 0) {
            this.vis.reduce(aspect, drain);
        }
        return drain;
    }

    public AspectList getAuraBase() {
        return this.auraBase;
    }

    @Override
    public AspectList getAspects() {
        return this.visBase;
    }

    @Override
    public void setAspects(AspectList aspects) {
        this.auraBase = aspects;
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
    public byte getAttunement() {
        return -1;
    }
}

