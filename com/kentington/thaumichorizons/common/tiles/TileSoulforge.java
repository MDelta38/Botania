/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.VillagerRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.lib.utils.InventoryUtils
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.ISoulReceiver;
import cpw.mods.fml.common.registry.VillagerRegistry;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.InventoryUtils;

public class TileSoulforge
extends TileThaumcraft
implements ISoulReceiver,
IEssentiaTransport,
IAspectContainer {
    int progress = 0;
    static final int PROGRESS_MAX = 9600;
    public int souls = 0;
    int essentia = 0;
    static final int ESSENTIA_MAX = 4000;
    public float rota;
    public int forging = 3;

    public boolean activate(World world, EntityPlayer player) {
        if (player.func_70694_bm() != null && player.func_70694_bm().func_77960_j() == 0 && player.func_70694_bm().func_77973_b() == Item.func_150898_a((Block)ConfigBlocks.blockJar)) {
            if (this.souls > 0) {
                ItemStack soul = new ItemStack(ThaumicHorizons.blockJar);
                soul.func_77982_d(new NBTTagCompound());
                soul.func_77978_p().func_74757_a("isSoul", true);
                Integer[] newVillagerTypes = new Integer[VillagerRegistry.getRegisteredVillagers().size()];
                int pointer = 0;
                Iterator it = VillagerRegistry.getRegisteredVillagers().iterator();
                while (it.hasNext()) {
                    newVillagerTypes[pointer] = (Integer)it.next();
                    ++pointer;
                }
                Integer[] villagerTypes = new Integer[newVillagerTypes.length + 5];
                for (int i = 0; i < 5; ++i) {
                    villagerTypes[i] = i;
                }
                for (int j = 0; j < newVillagerTypes.length; ++j) {
                    villagerTypes[j + 5] = newVillagerTypes[j];
                }
                int which = world.field_73012_v.nextInt(villagerTypes.length);
                soul.func_77978_p().func_74768_a("villagerType", villagerTypes[which].intValue());
                EntityVillager dummyVillager = new EntityVillager(this.field_145850_b);
                dummyVillager.func_70938_b(villagerTypes[which].intValue());
                soul.func_77978_p().func_74778_a("jarredCritterName", dummyVillager.func_70005_c_());
                player.field_71071_by.func_70298_a(InventoryUtils.isPlayerCarrying((EntityPlayer)player, (ItemStack)new ItemStack(ConfigBlocks.blockJar, 1, 0)), 1);
                if (!player.field_71071_by.func_70441_a(soul)) {
                    player.func_70099_a(soul, 1.0f);
                }
                --this.souls;
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void addSoulBits(int bits) {
        this.progress += bits;
        this.essentia -= bits;
        if (this.progress >= 9600) {
            this.progress -= 9600;
            ++this.souls;
        }
        this.forging = 3;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public boolean canAcceptSouls() {
        return this.essentia > 0 && this.souls < 16;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.essentia < 3000) {
            this.drawEssentia();
        }
        if (this.forging > 0) {
            this.rota += 1.0f;
            if (this.rota > 360.0f) {
                this.rota -= 360.0f;
            }
            --this.forging;
        }
    }

    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : p_70300_1_.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    void drawEssentia() {
        ForgeDirection dir = ForgeDirection.UP;
        TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, dir);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport)te;
            ForgeDirection opposite = ForgeDirection.DOWN;
            if (!ic.canOutputTo(opposite)) {
                return;
            }
            Aspect ta = null;
            if (ic.getEssentiaAmount(opposite) > 0 && ic.getSuctionAmount(opposite) < this.getSuctionAmount(dir) && this.getSuctionAmount(dir) >= ic.getMinimumSuction()) {
                ta = ic.getEssentiaType(opposite);
            }
            if (ta != null && ta.getTag().equals(Aspect.MIND.getTag()) && ic.takeEssentia(ta, 1, opposite) == 1) {
                this.addEssentia(ta, 1, dir);
                return;
            }
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("souls", this.souls);
        nbttagcompound.func_74768_a("progress", this.progress);
        nbttagcompound.func_74768_a("essentia", this.essentia);
        nbttagcompound.func_74768_a("forging", this.forging);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.souls = nbttagcompound.func_74762_e("souls");
        this.progress = nbttagcompound.func_74762_e("progress");
        this.essentia = nbttagcompound.func_74762_e("essentia");
        this.forging = nbttagcompound.func_74762_e("forging");
    }

    @Override
    public AspectList getAspects() {
        if (this.essentia <= 0) {
            return null;
        }
        return new AspectList().add(Aspect.MIND, (int)Math.ceil((float)this.essentia / 1000.0f));
    }

    @Override
    public void setAspects(AspectList aspects) {
        this.essentia = aspects.getAmount(Aspect.MIND) * 1000;
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
        return tag.getTag().equals(Aspect.MIND.getTag()) && this.essentia / 1000 >= amount;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        if (tag.getTag().equals(Aspect.MIND.getTag())) {
            return this.essentia / 1000;
        }
        return 0;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face == ForgeDirection.UP;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return face == ForgeDirection.UP;
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
        return Aspect.MIND;
    }

    @Override
    public int getSuctionAmount(ForgeDirection face) {
        return this.essentia < 3000 ? 128 : 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        if (this.essentia < 3000) {
            this.essentia += 1000;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return 1;
        }
        return 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection face) {
        return Aspect.MIND;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection face) {
        return this.essentia / 1000;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }
}

