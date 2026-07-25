/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 */
package thaumcraft.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileJar;
import thaumcraft.common.tiles.TileNode;

public class TileJarNode
extends TileJar
implements IAspectContainer,
INode,
IWandable {
    private AspectList aspects = new AspectList();
    private AspectList aspectsBase = new AspectList();
    private NodeType nodeType = NodeType.NORMAL;
    private NodeModifier nodeModifier = null;
    private String id = "";
    public long animate = 0L;
    public boolean drop = true;

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.aspects.readFromNBT(nbttagcompound);
        this.id = nbttagcompound.func_74779_i("nodeId");
        AspectList al = new AspectList();
        NBTTagList tlist = nbttagcompound.func_150295_c("AspectsBase", 10);
        for (int j = 0; j < tlist.func_74745_c(); ++j) {
            NBTTagCompound rs = tlist.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            al.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        Short oldBase = nbttagcompound.func_74765_d("nodeVisBase");
        this.aspectsBase = new AspectList();
        if (oldBase > 0 && al.size() == 0) {
            for (Aspect a : this.aspects.getAspects()) {
                this.aspectsBase.merge(a, oldBase.shortValue());
            }
        } else {
            this.aspectsBase = al.copy();
        }
        this.setNodeType(NodeType.values()[nbttagcompound.func_74771_c("type")]);
        byte mod = nbttagcompound.func_74771_c("modifier");
        if (mod >= 0) {
            this.setNodeModifier(NodeModifier.values()[mod]);
        } else {
            this.setNodeModifier(null);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        this.aspects.writeToNBT(nbttagcompound);
        nbttagcompound.func_74778_a("nodeId", this.id);
        NBTTagList tlist = new NBTTagList();
        nbttagcompound.func_74782_a("AspectsBase", (NBTBase)tlist);
        for (Aspect aspect : this.aspectsBase.getAspects()) {
            if (aspect == null) continue;
            NBTTagCompound f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.aspectsBase.getAmount(aspect));
            tlist.func_74742_a((NBTBase)f);
        }
        nbttagcompound.func_74774_a("type", (byte)this.getNodeType().ordinal());
        nbttagcompound.func_74774_a("modifier", this.getNodeModifier() == null ? (byte)-1 : (byte)this.getNodeModifier().ordinal());
    }

    @Override
    public AspectList getAspects() {
        return this.aspects;
    }

    @Override
    public AspectList getAspectsBase() {
        return this.aspectsBase;
    }

    @Override
    public void setAspects(AspectList aspects) {
        this.aspects = aspects.copy();
        this.aspectsBase = aspects.copy();
    }

    @Override
    public int addToContainer(Aspect tt, int am) {
        int out = 0;
        if (this.aspects.getAmount(tt) + am > this.aspectsBase.getAmount(tt)) {
            out = this.aspects.getAmount(tt) + am - this.aspectsBase.getAmount(tt);
        }
        this.aspects.add(tt, am - out);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
        return out;
    }

    @Override
    public boolean takeFromContainer(Aspect tt, int am) {
        if (this.aspects.getAmount(tt) >= am) {
            this.aspects.remove(tt, am);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
            return true;
        }
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amt) {
        return this.aspects.getAmount(tag) >= amt;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        for (Aspect tt : ot.getAspects()) {
            if (this.aspects.getAmount(tt) >= ot.getAmount(tt)) continue;
            return false;
        }
        return true;
    }

    @Override
    public int containerContains(Aspect tag) {
        return this.aspects.getAmount(tag);
    }

    @Override
    public NodeType getNodeType() {
        return this.nodeType;
    }

    @Override
    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public void setNodeModifier(NodeModifier nodeModifier) {
        this.nodeModifier = nodeModifier;
    }

    @Override
    public NodeModifier getNodeModifier() {
        return this.nodeModifier;
    }

    @Override
    public int getNodeVisBase(Aspect aspect) {
        return this.aspectsBase.getAmount(aspect);
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setNodeVisBase(Aspect aspect, short nodeVisBase) {
        if (this.aspectsBase.getAmount(aspect) < nodeVisBase) {
            this.aspectsBase.merge(aspect, nodeVisBase);
        } else {
            this.aspectsBase.reduce(aspect, this.aspectsBase.getAmount(aspect) - nodeVisBase);
        }
    }

    public boolean func_145842_c(int i, int j) {
        if (i == 9) {
            if (this.field_145850_b.field_72995_K) {
                for (int yy = -1; yy < 3; ++yy) {
                    for (int xx = -1; xx < 2; ++xx) {
                        for (int zz = -1; zz < 2; ++zz) {
                            Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c + xx, this.field_145848_d + yy, this.field_145849_e + zz, -9999, 5);
                        }
                    }
                }
                this.animate = System.currentTimeMillis() + 1000L;
            }
            return true;
        }
        return super.func_145842_c(i, j);
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        if (!world.field_72995_K) {
            this.drop = false;
            world.func_147465_d(x, y, z, ConfigBlocks.blockAiry, 0, 3);
            TileNode tn = (TileNode)world.func_147438_o(x, y, z);
            if (tn != null) {
                tn.setAspects(this.getAspects());
                tn.setNodeModifier(this.getNodeModifier());
                tn.setNodeType(this.getNodeType());
                tn.id = this.getId();
                world.func_147471_g(x, y, z);
                tn.func_70296_d();
            }
        }
        world.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)ConfigBlocks.blockJar) + 61440);
        player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.glass", 1.0f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
        player.func_71038_i();
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

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }
}

