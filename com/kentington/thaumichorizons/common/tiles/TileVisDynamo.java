/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.utils.EntityUtils
 */
package com.kentington.thaumichorizons.common.tiles;

import java.awt.Color;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.visnet.TileVisNode;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.EntityUtils;

public class TileVisDynamo
extends TileVisNode
implements IAspectContainer,
IWandable {
    AspectList primalsActuallyProvided = new AspectList();
    AspectList primalsProvided = new AspectList();
    public boolean provideAer = true;
    public boolean provideAqua = true;
    public boolean provideIgnis = true;
    public boolean provideOrdo = true;
    public boolean providePerditio = true;
    public boolean provideTerra = true;
    public int ticksProvided = 0;
    public float rise = 0.0f;
    public float rotation = 0.0f;
    public float rotation2 = 0.0f;
    public Entity drainEntity = null;
    public MovingObjectPosition drainCollision = null;
    public int drainColor = 0xFFFFFF;
    public Color targetColor = new Color(0xFFFFFF);
    public Color color = new Color(0xFFFFFF);

    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : p_70300_1_.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

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
        return amount;
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
    public void func_145845_h() {
        super.func_145845_h();
        if (this.ticksProvided > 0) {
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
            --this.ticksProvided;
            if (this.ticksProvided == 0) {
                this.primalsProvided = new AspectList();
                this.func_70296_d();
            }
            this.primalsActuallyProvided = this.primalsProvided.copy();
        } else if (this.ticksProvided == 0) {
            --this.ticksProvided;
            if (VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g) != null) {
                VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).remove(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g));
            }
            this.removeThisNode();
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
    }

    public void killMe() {
        if (VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g) != null) {
            VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).remove(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g));
        }
        this.removeThisNode();
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74768_a("ticks", this.ticksProvided);
        nbttagcompound.func_74757_a("aer", this.provideAer);
        nbttagcompound.func_74757_a("aqua", this.provideAqua);
        nbttagcompound.func_74757_a("ignis", this.provideIgnis);
        nbttagcompound.func_74757_a("ordo", this.provideOrdo);
        nbttagcompound.func_74757_a("perditio", this.providePerditio);
        nbttagcompound.func_74757_a("terra", this.provideTerra);
        NBTTagList tlist = new NBTTagList();
        nbttagcompound.func_74782_a("AspectsProvided", (NBTBase)tlist);
        for (Aspect aspect : this.primalsProvided.getAspects()) {
            if (aspect == null) continue;
            NBTTagCompound f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.primalsProvided.getAmount(aspect));
            tlist.func_74742_a((NBTBase)f);
        }
        if (this.drainEntity != null && this.drainEntity instanceof EntityPlayer) {
            nbttagcompound.func_74778_a("drainer", this.drainEntity.func_70005_c_());
        }
        nbttagcompound.func_74768_a("draincolor", this.drainColor);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.ticksProvided = nbttagcompound.func_74762_e("ticks");
        this.provideAer = nbttagcompound.func_74767_n("aer");
        this.provideAqua = nbttagcompound.func_74767_n("aqua");
        this.provideIgnis = nbttagcompound.func_74767_n("ignis");
        this.provideOrdo = nbttagcompound.func_74767_n("ordo");
        this.providePerditio = nbttagcompound.func_74767_n("perditio");
        this.provideTerra = nbttagcompound.func_74767_n("terra");
        AspectList al = new AspectList();
        NBTTagList tlist = nbttagcompound.func_150295_c("AspectsProvided", 10);
        for (int j = 0; j < tlist.func_74745_c(); ++j) {
            NBTTagCompound rs = tlist.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            al.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        this.primalsProvided = al.copy();
        String de = nbttagcompound.func_74779_i("drainer");
        if (de != null && de.length() > 0 && this.func_145831_w() != null) {
            this.drainEntity = this.func_145831_w().func_72924_a(de);
            if (this.drainEntity != null) {
                this.drainCollision = new MovingObjectPosition(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, Vec3.func_72443_a((double)this.drainEntity.field_70165_t, (double)this.drainEntity.field_70163_u, (double)this.drainEntity.field_70161_v));
            }
        }
        this.drainColor = nbttagcompound.func_74762_e("draincolor");
    }

    @Override
    public int onWandRightClick(World paramWorld, ItemStack paramItemStack, EntityPlayer paramEntityPlayer, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        return -1;
    }

    @Override
    public ItemStack onWandRightClick(World paramWorld, ItemStack wandstack, EntityPlayer player) {
        player.func_71008_a(wandstack, Integer.MAX_VALUE);
        ItemWandCasting wand = (ItemWandCasting)wandstack.func_77973_b();
        wand.setObjectInUse(wandstack, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        if (this.provideAer || this.provideAqua || this.provideIgnis || this.provideOrdo || this.providePerditio || this.provideTerra) {
            if (VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g) == null) {
                VisNetHandler.sources.put(this.field_145850_b.field_73011_w.field_76574_g, new HashMap());
            }
            if (VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).get(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g)) == null) {
                VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).put(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g), new WeakReference<TileVisDynamo>(this));
            } else if (VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).get(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g)).get() == null) {
                VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).remove(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g));
                VisNetHandler.sources.get(this.field_145850_b.field_73011_w.field_76574_g).put(new WorldCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73011_w.field_76574_g), new WeakReference<TileVisDynamo>(this));
            }
        }
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
            boolean success = false;
            AspectList aspectsToProvide = new AspectList();
            this.primalsProvided = new AspectList();
            if (this.provideAer) {
                aspectsToProvide = aspectsToProvide.add(Aspect.AIR, 25);
            }
            if (this.provideAqua) {
                aspectsToProvide = aspectsToProvide.add(Aspect.WATER, 25);
            }
            if (this.provideIgnis) {
                aspectsToProvide = aspectsToProvide.add(Aspect.FIRE, 25);
            }
            if (this.provideOrdo) {
                aspectsToProvide = aspectsToProvide.add(Aspect.ORDER, 25);
            }
            if (this.providePerditio) {
                aspectsToProvide = aspectsToProvide.add(Aspect.ENTROPY, 25);
            }
            if (this.provideTerra) {
                aspectsToProvide = aspectsToProvide.add(Aspect.EARTH, 25);
            }
            for (Aspect asp : aspectsToProvide.getAspects()) {
                if (!wand.consumeVis(wandstack, player, asp, 40, false)) continue;
                this.primalsProvided = this.primalsProvided.add(asp, 5);
                success = true;
            }
            if (success) {
                this.ticksProvided = 10;
                this.drainEntity = player;
                this.drainCollision = movingobjectposition;
                int r = 0;
                int g = 0;
                int b = 0;
                int num = 0;
                for (Aspect asp : this.primalsProvided.getAspects()) {
                    Color col = new Color(asp.getColor());
                    r += col.getRed();
                    g += col.getGreen();
                    b += col.getBlue();
                    ++num;
                }
                this.drainColor = new Color(r /= num, g /= num, b /= num).getRGB();
                this.targetColor = new Color(this.drainColor);
                if (!this.field_145850_b.field_72995_K) {
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    this.func_70296_d();
                }
            } else {
                this.drainEntity = null;
                this.drainCollision = null;
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

    @Override
    public void onWandStoppedUsing(ItemStack paramItemStack, World paramWorld, EntityPlayer paramEntityPlayer, int paramInt) {
    }
}

