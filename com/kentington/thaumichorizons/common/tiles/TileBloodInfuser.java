/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.Potion
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.common.config.Config
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import java.awt.Color;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.config.Config;

public class TileBloodInfuser
extends TileThaumcraft
implements IAspectContainer,
IEssentiaTransport,
ISidedInventory {
    public AspectList aspectsSelected = new AspectList();
    public AspectList aspectsAcquired = new AspectList();
    Aspect currentlySucking = null;
    public int mode = 0;
    public ItemStack syringe = null;
    public ItemStack[] output = new ItemStack[9];
    private HashMap<Aspect, HashMap<Integer, Integer>> effectWeights = new HashMap();
    private HashMap<Integer, Float> duration = new HashMap();
    private Color color;

    public TileBloodInfuser() {
        int speed = Potion.field_76424_c.field_76415_H;
        int slow = Potion.field_76421_d.field_76415_H;
        int haste = Potion.field_76422_e.field_76415_H;
        int fatigue = Potion.field_76419_f.field_76415_H;
        int strength = Potion.field_76420_g.field_76415_H;
        int health = Potion.field_76432_h.field_76415_H;
        int harm = Potion.field_76433_i.field_76415_H;
        int jump = Potion.field_76430_j.field_76415_H;
        int nausea = 9;
        int regen = Potion.field_76428_l.field_76415_H;
        int resist = Potion.field_76429_m.field_76415_H;
        int fireres = Potion.field_76426_n.field_76415_H;
        int water = Potion.field_76427_o.field_76415_H;
        int invis = Potion.field_76441_p.field_76415_H;
        int blind = Potion.field_76440_q.field_76415_H;
        int night = Potion.field_76439_r.field_76415_H;
        int hunger = Potion.field_76438_s.field_76415_H;
        int weak = Potion.field_76437_t.field_76415_H;
        int poison = Potion.field_76436_u.field_76415_H;
        int wither = Potion.field_82731_v.field_76415_H;
        int hboost = 21;
        int satur = 23;
        int taint = Config.potionTaintPoisonID;
        int visBoost = Config.potionVisExhaustID;
        int visRegen = ThaumicHorizons.potionVisRegenID;
        int vacuum = ThaumicHorizons.potionVacuumID;
        int shock = ThaumicHorizons.potionShockID;
        int synth = ThaumicHorizons.potionSynthesisID;
        this.duration.put(speed, Float.valueOf(1.2f));
        this.duration.put(slow, Float.valueOf(0.8f));
        this.duration.put(haste, Float.valueOf(1.2f));
        this.duration.put(fatigue, Float.valueOf(0.8f));
        this.duration.put(strength, Float.valueOf(1.2f));
        this.duration.put(jump, Float.valueOf(1.2f));
        this.duration.put(nausea, Float.valueOf(0.2f));
        this.duration.put(regen, Float.valueOf(0.6f));
        this.duration.put(resist, Float.valueOf(1.2f));
        this.duration.put(fireres, Float.valueOf(1.2f));
        this.duration.put(water, Float.valueOf(1.2f));
        this.duration.put(invis, Float.valueOf(1.2f));
        this.duration.put(blind, Float.valueOf(0.2f));
        this.duration.put(night, Float.valueOf(1.2f));
        this.duration.put(hunger, Float.valueOf(0.4f));
        this.duration.put(weak, Float.valueOf(0.8f));
        this.duration.put(poison, Float.valueOf(0.6f));
        this.duration.put(wither, Float.valueOf(0.4f));
        this.duration.put(hboost, Float.valueOf(1.2f));
        this.duration.put(satur, Float.valueOf(0.4f));
        this.duration.put(taint, Float.valueOf(0.6f));
        this.duration.put(visBoost, Float.valueOf(1.2f));
        this.duration.put(visRegen, Float.valueOf(0.6f));
        this.duration.put(vacuum, Float.valueOf(1.2f));
        this.duration.put(shock, Float.valueOf(0.8f));
        this.duration.put(synth, Float.valueOf(1.5f));
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(speed, 3);
        map.put(jump, 4);
        map.put(water, 3);
        this.effectWeights.put(Aspect.AIR, map);
        map = new HashMap();
        map.put(slow, 3);
        map.put(haste, 4);
        map.put(strength, 1);
        map.put(resist, 2);
        this.effectWeights.put(Aspect.EARTH, map);
        map = new HashMap();
        map.put(speed, 1);
        map.put(strength, 2);
        map.put(fireres, 6);
        map.put(night, 1);
        this.effectWeights.put(Aspect.FIRE, map);
        map = new HashMap();
        map.put(speed, 2);
        map.put(haste, 2);
        map.put(regen, 1);
        map.put(fireres, 2);
        map.put(water, 3);
        this.effectWeights.put(Aspect.WATER, map);
        map = new HashMap();
        map.put(slow, 2);
        map.put(fatigue, 3);
        map.put(resist, 3);
        map.put(hboost, 2);
        this.effectWeights.put(Aspect.ORDER, map);
        map = new HashMap();
        map.put(harm, 1);
        map.put(nausea, 1);
        map.put(hunger, 1);
        map.put(weak, 2);
        map.put(poison, 2);
        map.put(wither, 3);
        this.effectWeights.put(Aspect.ENTROPY, map);
        map = new HashMap();
        map.put(invis, 4);
        map.put(hunger, 2);
        map.put(vacuum, 4);
        this.effectWeights.put(Aspect.VOID, map);
        map = new HashMap();
        map.put(blind, 2);
        map.put(night, 8);
        this.effectWeights.put(Aspect.LIGHT, map);
        map = new HashMap();
        map.put(speed, 1);
        map.put(jump, 2);
        map.put(blind, 1);
        map.put(shock, 8);
        this.effectWeights.put(Aspect.WEATHER, map);
        map = new HashMap();
        map.put(speed, 5);
        map.put(jump, 5);
        this.effectWeights.put(Aspect.MOTION, map);
        map = new HashMap();
        map.put(slow, 2);
        map.put(fatigue, 2);
        map.put(resist, 2);
        map.put(invis, 2);
        map.put(weak, 2);
        this.effectWeights.put(Aspect.COLD, map);
        map = new HashMap();
        map.put(fatigue, 2);
        map.put(strength, 2);
        map.put(invis, 6);
        this.effectWeights.put(Aspect.CRYSTAL, map);
        map = new HashMap();
        map.put(health, 2);
        map.put(regen, 6);
        map.put(hboost, 2);
        this.effectWeights.put(Aspect.LIFE, map);
        map = new HashMap();
        map.put(fatigue, 1);
        map.put(nausea, 1);
        map.put(hunger, 1);
        map.put(poison, 6);
        map.put(weak, 1);
        this.effectWeights.put(Aspect.POISON, map);
        map = new HashMap();
        map.put(speed, 2);
        map.put(haste, 2);
        map.put(strength, 1);
        map.put(jump, 1);
        map.put(visRegen, 1);
        map.put(shock, 3);
        this.effectWeights.put(Aspect.ENERGY, map);
        map = new HashMap();
        map.put(speed, 2);
        map.put(haste, 2);
        map.put(jump, 1);
        map.put(weak, 2);
        map.put(vacuum, 3);
        this.effectWeights.put(Aspect.EXCHANGE, map);
        map = new HashMap();
        map.put(slow, 2);
        map.put(haste, 2);
        map.put(strength, 2);
        map.put(resist, 4);
        this.effectWeights.put(Aspect.METAL, map);
        map = new HashMap();
        map.put(harm, 6);
        map.put(nausea, 1);
        map.put(hunger, 1);
        map.put(weak, 1);
        map.put(wither, 1);
        this.effectWeights.put(Aspect.DEATH, map);
        map = new HashMap();
        map.put(speed, 2);
        map.put(jump, 8);
        this.effectWeights.put(Aspect.FLIGHT, map);
        map = new HashMap();
        map.put(invis, 4);
        map.put(blind, 5);
        map.put(night, 1);
        this.effectWeights.put(Aspect.DARKNESS, map);
        map = new HashMap();
        map.put(health, 2);
        map.put(regen, 1);
        map.put(invis, 3);
        map.put(night, 2);
        map.put(hboost, 1);
        map.put(visRegen, 1);
        this.effectWeights.put(Aspect.SOUL, map);
        map = new HashMap();
        map.put(health, 6);
        map.put(regen, 2);
        map.put(hboost, 2);
        this.effectWeights.put(Aspect.HEAL, map);
        map = new HashMap();
        map.put(speed, 8);
        map.put(jump, 2);
        this.effectWeights.put(Aspect.TRAVEL, map);
        map = new HashMap();
        map.put(invis, 2);
        map.put(blind, 2);
        map.put(night, 2);
        map.put(visBoost, 2);
        map.put(visRegen, 2);
        this.effectWeights.put(Aspect.ELDRITCH, map);
        map = new HashMap();
        map.put(visBoost, 5);
        map.put(visRegen, 5);
        this.effectWeights.put(Aspect.MAGIC, map);
        map = new HashMap();
        map.put(visRegen, 10);
        this.effectWeights.put(Aspect.AURA, map);
        map = new HashMap();
        map.put(visBoost, 2);
        map.put(wither, 2);
        map.put(taint, 6);
        this.effectWeights.put(Aspect.TAINT, map);
        map = new HashMap();
        map.put(slow, 4);
        map.put(fatigue, 2);
        map.put(nausea, 2);
        map.put(poison, 2);
        this.effectWeights.put(Aspect.SLIME, map);
        map = new HashMap();
        map.put(satur, 2);
        map.put(synth, 8);
        this.effectWeights.put(Aspect.PLANT, map);
        map = new HashMap();
        map.put(slow, 2);
        map.put(resist, 2);
        map.put(synth, 6);
        this.effectWeights.put(Aspect.TREE, map);
        map = new HashMap();
        map.put(speed, 1);
        map.put(strength, 5);
        map.put(regen, 1);
        map.put(water, 1);
        map.put(night, 1);
        map.put(hboost, 1);
        this.effectWeights.put(Aspect.BEAST, map);
        map = new HashMap();
        map.put(regen, 2);
        map.put(hboost, 2);
        map.put(satur, 6);
        this.effectWeights.put(Aspect.FLESH, map);
        map = new HashMap();
        map.put(hunger, 2);
        map.put(wither, 2);
        map.put(harm, 6);
        this.effectWeights.put(Aspect.UNDEAD, map);
        map = new HashMap();
        map.put(night, 2);
        map.put(visBoost, 3);
        map.put(visRegen, 3);
        map.put(vacuum, 2);
        this.effectWeights.put(Aspect.MIND, map);
        map = new HashMap();
        map.put(invis, 4);
        map.put(night, 6);
        this.effectWeights.put(Aspect.SENSES, map);
        map = new HashMap();
        map.put(health, 3);
        map.put(regen, 3);
        map.put(hboost, 4);
        this.effectWeights.put(Aspect.MAN, map);
        map = new HashMap();
        map.put(satur, 6);
        map.put(synth, 4);
        this.effectWeights.put(Aspect.CROP, map);
        map = new HashMap();
        map.put(haste, 10);
        this.effectWeights.put(Aspect.MINE, map);
        map = new HashMap();
        map.put(speed, 1);
        map.put(haste, 8);
        map.put(resist, 1);
        this.effectWeights.put(Aspect.TOOL, map);
        map = new HashMap();
        map.put(haste, 2);
        map.put(health, 1);
        map.put(satur, 5);
        map.put(synth, 2);
        this.effectWeights.put(Aspect.HARVEST, map);
        map = new HashMap();
        map.put(haste, 2);
        map.put(strength, 8);
        this.effectWeights.put(Aspect.WEAPON, map);
        map = new HashMap();
        map.put(resist, 8);
        map.put(fireres, 2);
        this.effectWeights.put(Aspect.ARMOR, map);
        map = new HashMap();
        map.put(weak, 1);
        map.put(satur, 8);
        map.put(vacuum, 1);
        this.effectWeights.put(Aspect.HUNGER, map);
        map = new HashMap();
        map.put(satur, 2);
        map.put(vacuum, 8);
        this.effectWeights.put(Aspect.GREED, map);
        map = new HashMap();
        map.put(fatigue, 2);
        map.put(health, 2);
        map.put(resist, 2);
        map.put(hboost, 4);
        this.effectWeights.put(Aspect.CRAFT, map);
        map = new HashMap();
        map.put(fatigue, 2);
        map.put(resist, 4);
        map.put(blind, 2);
        map.put(weak, 2);
        this.effectWeights.put(Aspect.CLOTH, map);
        map = new HashMap();
        map.put(speed, 2);
        map.put(haste, 4);
        map.put(strength, 4);
        this.effectWeights.put(Aspect.MECHANISM, map);
        map = new HashMap();
        map.put(slow, 8);
        map.put(fatigue, 2);
        this.effectWeights.put(Aspect.TRAP, map);
    }

    public void func_145845_h() {
        super.func_145845_h();
        this.currentlySucking = null;
        if (this.mode > 0 && this.syringe != null && this.syringe.field_77994_a > 0 && this.emptyOutputSlot()) {
            for (Aspect asp : this.aspectsSelected.getAspects()) {
                if (this.aspectsAcquired.getAmount(asp) >= this.aspectsSelected.getAmount(asp)) continue;
                this.currentlySucking = asp;
                break;
            }
            if (this.currentlySucking == null && this.aspectsAcquired != null && (this.aspectsAcquired.size() > 0 && this.aspectsAcquired.getAspects()[0] != null || this.aspectsAcquired.size() > 1 && this.aspectsAcquired.getAspects()[1] != null)) {
                ItemStack theInjection = new ItemStack(ThaumicHorizons.itemSyringeInjection);
                theInjection.func_77964_b(this.syringe.func_77960_j());
                this.func_70298_a(0, 1);
                NBTTagCompound tag = new NBTTagCompound();
                tag.func_74782_a("CustomPotionEffects", (NBTBase)this.getCurrentEffects());
                tag.func_74768_a("color", this.color.getRGB());
                theInjection.func_77982_d(tag);
                for (int i = 0; i < 9; ++i) {
                    if (this.output[i] != null) continue;
                    this.output[i] = theInjection;
                    break;
                }
                this.aspectsAcquired = new AspectList();
                this.func_70296_d();
                if (this.mode == 1) {
                    this.mode = 0;
                }
            } else {
                this.tryDrawEssentia();
            }
        }
    }

    public NBTTagList getCurrentEffects() {
        NBTTagList effectList = new NBTTagList();
        if (this.aspectsSelected == null) {
            return effectList;
        }
        HashMap<Integer, Integer> effects = new HashMap<Integer, Integer>();
        int totalEssentia = 0;
        int green = 0;
        int red = 0;
        int blue = 0;
        for (Aspect asp : this.aspectsSelected.getAspects()) {
            if (this.effectWeights.get(asp) == null) continue;
            for (Integer in : this.effectWeights.get(asp).keySet()) {
                if (effects.get(in) != null) {
                    effects.put(in, (Integer)effects.get(in) + this.aspectsSelected.getAmount(asp) * this.effectWeights.get(asp).get(in));
                    continue;
                }
                effects.put(in, this.aspectsSelected.getAmount(asp) * this.effectWeights.get(asp).get(in));
            }
            totalEssentia += this.aspectsSelected.getAmount(asp);
            Color col = new Color(asp.getColor());
            red += col.getRed() * this.aspectsSelected.getAmount(asp);
            blue += col.getBlue() * this.aspectsSelected.getAmount(asp);
            green += col.getGreen() * this.aspectsSelected.getAmount(asp);
        }
        if (totalEssentia > 0) {
            red /= totalEssentia;
            green /= totalEssentia;
            blue /= totalEssentia;
        }
        for (int i = 0; i < (totalEssentia + 1) / 2; ++i) {
            Integer largestWeight = 0;
            Integer largestEffect = 0;
            for (Integer key : effects.keySet()) {
                if ((Integer)effects.get(key) <= largestWeight) continue;
                largestWeight = (Integer)effects.get(key);
                largestEffect = key;
            }
            NBTTagCompound potTag = new NBTTagCompound();
            potTag.func_74774_a("Id", (byte)largestEffect.intValue());
            potTag.func_74774_a("Amplifier", (byte)(largestWeight / 30));
            if (largestEffect != Potion.field_76433_i.field_76415_H && largestEffect != Potion.field_76432_h.field_76415_H) {
                potTag.func_74768_a("Duration", 100 * largestWeight);
            } else {
                potTag.func_74768_a("Duration", 1);
            }
            potTag.func_74757_a("Ambient", false);
            effectList.func_74742_a((NBTBase)potTag);
            effects.remove(largestEffect);
        }
        this.color = new Color(red, green, blue);
        return effectList;
    }

    public HashMap<Integer, Integer> getEffects(Aspect asp) {
        return this.effectWeights.get(asp);
    }

    public void setEssentiaSelected(AspectList as) {
        this.aspectsSelected = as.copy();
    }

    public boolean emptyOutputSlot() {
        for (int i = 0; i < 9; ++i) {
            if (this.output[i] != null) continue;
            return true;
        }
        return false;
    }

    void tryDrawEssentia() {
        TileEntity te = null;
        IEssentiaTransport ic = null;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            int ess;
            if (dir == ForgeDirection.UP || (te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, dir)) == null || (ic = (IEssentiaTransport)te).getEssentiaAmount(dir.getOpposite()) <= 0 || ic.getSuctionAmount(dir.getOpposite()) >= this.getSuctionAmount(null) || this.getSuctionAmount(null) < ic.getMinimumSuction() || (ess = ic.takeEssentia(this.currentlySucking, 1, dir.getOpposite())) <= 0) continue;
            this.addToContainer(this.currentlySucking, ess);
            return;
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        NBTTagCompound f;
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74768_a("mode", this.mode);
        if (this.currentlySucking != null) {
            nbttagcompound.func_74778_a("sucking", this.currentlySucking.getTag());
        } else {
            nbttagcompound.func_74778_a("sucking", "");
        }
        NBTTagList tlist = new NBTTagList();
        nbttagcompound.func_74782_a("AspectsSelected", (NBTBase)tlist);
        for (Aspect aspect : this.aspectsSelected.getAspects()) {
            if (aspect == null) continue;
            f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.aspectsSelected.getAmount(aspect));
            tlist.func_74742_a((NBTBase)f);
        }
        tlist = new NBTTagList();
        nbttagcompound.func_74782_a("AspectsAcquired", (NBTBase)tlist);
        for (Aspect aspect : this.aspectsAcquired.getAspects()) {
            if (aspect == null) continue;
            f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.aspectsAcquired.getAmount(aspect));
            tlist.func_74742_a((NBTBase)f);
        }
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        if (this.syringe != null) {
            this.syringe.func_77955_b(nbttagcompound1);
        }
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        for (int i = 0; i < 9; ++i) {
            nbttagcompound1 = new NBTTagCompound();
            if (this.output[i] != null) {
                this.output[i].func_77955_b(nbttagcompound1);
            }
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        NBTTagCompound rs;
        int j;
        super.readCustomNBT(nbttagcompound);
        this.mode = nbttagcompound.func_74762_e("mode");
        this.currentlySucking = Aspect.getAspect(nbttagcompound.func_74779_i("sucking"));
        AspectList al = new AspectList();
        NBTTagList tlist = nbttagcompound.func_150295_c("AspectsSelected", 10);
        for (j = 0; j < tlist.func_74745_c(); ++j) {
            rs = tlist.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            al.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        this.aspectsSelected = al.copy();
        al = new AspectList();
        tlist = nbttagcompound.func_150295_c("AspectsAcquired", 10);
        for (j = 0; j < tlist.func_74745_c(); ++j) {
            rs = tlist.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            al.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        this.aspectsAcquired = al.copy();
        NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(0);
        this.syringe = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        for (int i = 0; i < 9; ++i) {
            nbttagcompound1 = nbttaglist.func_150305_b(i + 1);
            this.output[i] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face != ForgeDirection.UP;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return face != ForgeDirection.UP;
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
        if (face == ForgeDirection.UP) {
            return null;
        }
        return this.currentlySucking;
    }

    @Override
    public int getSuctionAmount(ForgeDirection face) {
        if (face == ForgeDirection.UP) {
            return 0;
        }
        return this.currentlySucking != null ? 128 : 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return this.canInputFrom(face) ? amount - this.addToContainer(aspect, amount) : 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection face) {
        return null;
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
    public AspectList getAspects() {
        if (this.aspectsAcquired.getAspects().length > 0 && this.aspectsAcquired.getAspects()[0] != null) {
            return this.aspectsAcquired;
        }
        return null;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return tag.getTag().equals(this.currentlySucking.getTag());
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        this.aspectsAcquired.add(tag, amount);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
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
        return this.aspectsAcquired.getAmount(tag) >= amount;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return this.aspectsAcquired.getAmount(tag);
    }

    public int func_70302_i_() {
        return 10;
    }

    public ItemStack func_70301_a(int slot) {
        if (slot == 0) {
            return this.syringe;
        }
        if (slot <= 9) {
            return this.output[slot - 1];
        }
        return null;
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
        ItemStack theStack = p_70298_1_ == 0 ? this.syringe : this.output[p_70298_1_ - 1];
        if (theStack != null) {
            if (theStack.field_77994_a <= p_70298_2_) {
                ItemStack outStack;
                if (p_70298_1_ == 0) {
                    outStack = this.syringe.func_77946_l();
                    this.syringe = null;
                } else {
                    outStack = this.output[p_70298_1_ - 1].func_77946_l();
                    this.output[p_70298_1_ - 1] = null;
                }
                return outStack;
            }
            ItemStack outStack = theStack.func_77979_a(p_70298_2_);
            if (theStack.field_77994_a == 0) {
                if (p_70298_1_ == 0) {
                    this.syringe = null;
                } else {
                    this.output[p_70298_1_ - 1] = null;
                }
            }
            return outStack;
        }
        return null;
    }

    public ItemStack func_70304_b(int p_70304_1_) {
        return null;
    }

    public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
        if (p_70299_1_ == 0) {
            this.syringe = p_70299_2_;
        } else if (p_70299_1_ < 10) {
            this.output[p_70299_1_ - 1] = p_70299_2_;
        }
    }

    public String func_145825_b() {
        return "container.bloodInfuser";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer p_70300_1_) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : p_70300_1_.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
        return p_94041_1_ == 0 && p_94041_2_.func_77969_a(new ItemStack(ThaumicHorizons.itemSyringeHuman));
    }

    public int[] func_94128_d(int p_94128_1_) {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    }

    public boolean func_102007_a(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return p_102007_1_ == 0 && p_102007_2_.func_77969_a(new ItemStack(ThaumicHorizons.itemSyringeHuman));
    }

    public boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return p_102008_1_ > 0 && p_102008_2_ != null;
    }

    public boolean hasBlood() {
        if (this.syringe != null && this.syringe.field_77994_a > 0) {
            return true;
        }
        for (int i = 0; i < 9; ++i) {
            if (this.output[i] == null || this.output[i].field_77994_a <= 0) continue;
            return true;
        }
        return false;
    }
}

