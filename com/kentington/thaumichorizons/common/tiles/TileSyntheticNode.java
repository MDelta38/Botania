/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.ItemWispEssence
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.research.ResearchManager
 *  thaumcraft.common.lib.utils.EntityUtils
 */
package com.kentington.thaumichorizons.common.tiles;

import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.visnet.TileVisNode;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemWispEssence;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.EntityUtils;

public class TileSyntheticNode
extends TileVisNode
implements INode,
IWandable {
    AspectList aspects = new AspectList();
    AspectList fractionalAspects = new AspectList();
    AspectList aspectsMax = new AspectList();
    private NodeType nodeType = NodeType.NORMAL;
    private NodeModifier nodeModifier = null;
    public float rotation = 0.0f;
    float increment = 1.0f;
    public Entity drainEntity = null;
    public MovingObjectPosition drainCollision = null;
    public int drainColor = 0xFFFFFF;
    public Color targetColor = new Color(0xFFFFFF);
    public Color color = new Color(0xFFFFFF);

    public void debug() {
        if (this.field_145850_b.field_72995_K) {
            // empty if block
        }
    }

    @Override
    public AspectList getAspects() {
        if (this.aspects.getAspects().length > 0 && this.aspects.getAspects()[0] != null) {
            return this.aspects;
        }
        return new AspectList();
    }

    public AspectList getMaxAspects() {
        return this.aspectsMax;
    }

    @Override
    public void setAspects(AspectList aspects) {
        this.aspectsMax = aspects;
        this.aspects = new AspectList();
        this.fractionalAspects = new AspectList();
        for (Aspect asp : aspects.getAspectsSortedAmount()) {
            this.aspects.add(asp, 0);
            this.fractionalAspects.add(asp, 0);
        }
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        this.aspects.add(tag, amount);
        int toReturn = amount;
        if (this.aspectsMax.getAmount(tag) < this.aspects.getAmount(tag)) {
            toReturn -= this.aspectsMax.getAmount(tag) - this.aspects.getAmount(tag);
            this.aspects.reduce(tag, this.aspectsMax.getAmount(tag) - this.aspects.getAmount(tag));
        }
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
        return toReturn;
    }

    public boolean addFractionalToContainer(Aspect tag, int amount) {
        if (this.aspects.getAmount(tag) < this.aspectsMax.getAmount(tag)) {
            this.fractionalAspects.add(tag, amount);
            while (this.fractionalAspects.getAmount(tag) > 100) {
                this.addToContainer(tag, 1);
                this.fractionalAspects.remove(tag, 100);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean takeFromContainer(Aspect tag, int amount) {
        return this.aspects.reduce(tag, amount);
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        Aspect[] toRemove = ot.getAspects();
        for (int i = 0; i < toRemove.length; ++i) {
            if (this.aspects.reduce(toRemove[i], ot.getAmount(toRemove[i]))) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return this.aspects.getAmount(tag) >= amount;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        for (Aspect asp : ot.getAspectsSortedAmount()) {
            if (this.aspects.getAmount(asp) > 0) continue;
            return false;
        }
        return true;
    }

    @Override
    public int containerContains(Aspect tag) {
        return this.aspects.getAmount(tag);
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        return -1;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        player.func_71008_a(wandstack, Integer.MAX_VALUE);
        ItemWandCasting wand = (ItemWandCasting)wandstack.func_77973_b();
        wand.setObjectInUse(wandstack, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return wandstack;
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
        boolean mfu = false;
        ItemWandCasting wand = (ItemWandCasting)wandstack.func_77973_b();
        MovingObjectPosition movingobjectposition = EntityUtils.getMovingObjectPositionFromPlayer((World)this.field_145850_b, (EntityPlayer)player, (boolean)true);
        if (movingobjectposition == null || movingobjectposition.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK) {
            player.func_71034_by();
        } else {
            int i = movingobjectposition.field_72311_b;
            int j = movingobjectposition.field_72312_c;
            int k = movingobjectposition.field_72309_d;
            if (i != this.field_145851_c || j != this.field_145848_d || k != this.field_145849_e) {
                player.func_71034_by();
            }
        }
        if (count % 5 == 0) {
            int tap = 1;
            if (ResearchManager.isResearchComplete((String)player.func_70005_c_(), (String)"NODETAPPER1")) {
                ++tap;
            }
            if (ResearchManager.isResearchComplete((String)player.func_70005_c_(), (String)"NODETAPPER2")) {
                ++tap;
            }
            boolean preserve = !player.func_70093_af() && ResearchManager.isResearchComplete((String)player.func_70005_c_(), (String)"NODEPRESERVE") && !wand.getRod(wandstack).getTag().equals("wood") && !wand.getCap(wandstack).getTag().equals("iron");
            boolean success = false;
            Aspect aspect = null;
            aspect = this.chooseRandomFilteredFromSource(wand.getAspectsWithRoom(wandstack), preserve);
            if (aspect != null) {
                int rem;
                int amt = this.getAspects().getAmount(aspect);
                if (tap > amt) {
                    tap = amt;
                }
                if (preserve && tap == amt) {
                    --tap;
                }
                if (tap > 0 && (rem = wand.addVis(wandstack, aspect, tap, !this.field_145850_b.field_72995_K)) < tap) {
                    this.drainColor = aspect.getColor();
                    if (!this.field_145850_b.field_72995_K) {
                        this.takeFromContainer(aspect, tap - rem);
                        mfu = true;
                    }
                    success = true;
                }
            }
            if (success) {
                this.drainEntity = player;
                this.drainCollision = movingobjectposition;
                this.targetColor = new Color(this.drainColor);
            } else {
                this.drainEntity = null;
                this.drainCollision = null;
            }
            if (mfu) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            }
        }
        if (player.field_70170_p.field_72995_K) {
            int r = this.targetColor.getRed();
            int g = this.targetColor.getGreen();
            int b = this.targetColor.getBlue();
            int r2 = this.color.getRed() * 4;
            int g2 = this.color.getGreen() * 4;
            int b2 = this.color.getBlue() * 4;
            this.color = new Color((r + r2) / 5, (g + g2) / 5, (b + b2) / 5);
        }
    }

    public Aspect chooseRandomFilteredFromSource(AspectList filter, boolean preserve) {
        int min = preserve ? 1 : 0;
        ArrayList<Aspect> validaspects = new ArrayList<Aspect>();
        for (Aspect prim : this.aspects.getAspects()) {
            if (filter.getAmount(prim) <= 0 || this.aspects.getAmount(prim) <= min) continue;
            validaspects.add(prim);
        }
        if (validaspects.size() == 0) {
            return null;
        }
        Aspect asp = (Aspect)validaspects.get(this.field_145850_b.field_73012_v.nextInt(validaspects.size()));
        if (asp != null && this.aspects.getAmount(asp) > min) {
            return asp;
        }
        return null;
    }

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
        this.drainEntity = null;
        this.drainCollision = null;
    }

    @Override
    public String getId() {
        return "SYNTHETIC";
    }

    @Override
    public AspectList getAspectsBase() {
        return this.aspectsMax;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.NORMAL;
    }

    @Override
    public void setNodeType(NodeType nodeType) {
    }

    @Override
    public void setNodeModifier(NodeModifier nodeModifier) {
    }

    @Override
    public NodeModifier getNodeModifier() {
        return null;
    }

    @Override
    public int getNodeVisBase(Aspect aspect) {
        return this.aspectsMax.getAmount(aspect);
    }

    @Override
    public void setNodeVisBase(Aspect aspect, short nodeVisBase) {
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
    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            for (Aspect aspect : this.aspectsMax.getAspectsSortedAmount()) {
                if (aspect == null || this.aspects.getAmount(aspect) >= this.aspectsMax.getAmount(aspect)) continue;
                if (aspect.isPrimal()) {
                    int drained = VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, aspect, 10);
                    if (drained <= 0) continue;
                    this.addFractionalToContainer(aspect, drained);
                    continue;
                }
                AspectList primalComponents = ResearchManager.reduceToPrimals((AspectList)new AspectList().add(aspect, 1));
                int actuallyDrained = 100;
                for (Aspect primal : primalComponents.getAspects()) {
                    int drained = VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, primal, 10);
                    if (drained >= actuallyDrained) continue;
                    actuallyDrained = drained;
                }
                if (actuallyDrained <= 0) continue;
                this.addFractionalToContainer(aspect, actuallyDrained);
            }
        } else {
            this.rotation += this.increment;
            if (this.rotation > 360.0f) {
                this.rotation -= 360.0f;
            }
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        NBTTagList tlist = new NBTTagList();
        nbttagcompound.func_74782_a("AspectsMax", (NBTBase)tlist);
        for (Aspect aspect : this.aspectsMax.getAspects()) {
            if (aspect == null) continue;
            NBTTagCompound f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.aspectsMax.getAmount(aspect));
            tlist.func_74742_a((NBTBase)f);
        }
        NBTTagList tlist2 = new NBTTagList();
        nbttagcompound.func_74782_a("Aspects", (NBTBase)tlist2);
        for (Aspect aspect : this.aspects.getAspects()) {
            if (aspect == null) continue;
            NBTTagCompound f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.aspects.getAmount(aspect));
            tlist2.func_74742_a((NBTBase)f);
        }
        NBTTagList tlist3 = new NBTTagList();
        nbttagcompound.func_74782_a("AspectsFractional", (NBTBase)tlist3);
        for (Aspect aspect : this.fractionalAspects.getAspects()) {
            if (aspect == null) continue;
            NBTTagCompound f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.fractionalAspects.getAmount(aspect));
            tlist3.func_74742_a((NBTBase)f);
        }
        if (this.drainEntity != null && this.drainEntity instanceof EntityPlayer) {
            nbttagcompound.func_74778_a("drainer", this.drainEntity.func_70005_c_());
        }
        nbttagcompound.func_74768_a("draincolor", this.drainColor);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        AspectList al = new AspectList();
        NBTTagList tlist = nbttagcompound.func_150295_c("AspectsMax", 10);
        for (int j = 0; j < tlist.func_74745_c(); ++j) {
            NBTTagCompound rs = tlist.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            al.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        this.aspectsMax = al.copy();
        AspectList al2 = new AspectList();
        NBTTagList tlist2 = nbttagcompound.func_150295_c("Aspects", 10);
        for (int j = 0; j < tlist2.func_74745_c(); ++j) {
            NBTTagCompound rs = tlist2.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            al2.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        this.aspects = al2.copy();
        AspectList al3 = new AspectList();
        NBTTagList tlist3 = nbttagcompound.func_150295_c("AspectsFractional", 10);
        for (int j = 0; j < tlist3.func_74745_c(); ++j) {
            NBTTagCompound rs = tlist3.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            al3.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        this.fractionalAspects = al3.copy();
        String de = nbttagcompound.func_74779_i("drainer");
        if (de != null && de.length() > 0 && this.func_145831_w() != null) {
            this.drainEntity = this.func_145831_w().func_72924_a(de);
            if (this.drainEntity != null) {
                this.drainCollision = new MovingObjectPosition(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, Vec3.func_72443_a((double)this.drainEntity.field_70165_t, (double)this.drainEntity.field_70163_u, (double)this.drainEntity.field_70161_v));
            }
        }
        this.drainColor = nbttagcompound.func_74762_e("draincolor");
    }

    public void addEssence(EntityPlayer player) {
        Item essence = player.func_70694_bm().func_77973_b();
        if (essence == ConfigItems.itemWispEssence && ((ItemWispEssence)essence).getAspects(player.func_70694_bm()) != null && ((ItemWispEssence)essence).getAspects(player.func_70694_bm()).getAspects().length > 0) {
            Aspect asp = ((ItemWispEssence)essence).getAspects(player.func_70694_bm()).getAspects()[0];
            this.aspectsMax.add(asp, 4);
            this.aspects.add(asp, 0);
            this.fractionalAspects.add(asp, 0);
            --player.func_70694_bm().field_77994_a;
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
    }
}

