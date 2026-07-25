/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigItems
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.entities.ai.EntityAIEatStone;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;

public class EntityOrePig
extends EntityPig {
    int nuggetPercent;

    public EntityOrePig(World p_i1689_1_) {
        super(p_i1689_1_);
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIEatStone(this));
        this.nuggetPercent = 0;
    }

    public void eatStone() {
        this.nuggetPercent += this.field_70170_p.field_73012_v.nextInt(15) + 1;
        if (this.nuggetPercent >= 100) {
            this.nuggetPercent -= 100;
            this.excreteNugget();
        }
    }

    void excreteNugget() {
        int type = this.field_70170_p.field_73012_v.nextInt(75);
        if (type < 6) {
            this.func_70099_a(new ItemStack(Items.field_151074_bl), 0.3f);
        } else if (type < 12) {
            if (Config.foundSilverIngot) {
                this.func_70099_a(new ItemStack(ConfigItems.itemNugget, 1, 3), 0.3f);
            } else {
                this.excreteNugget();
            }
        } else if (type < 20) {
            if (Config.foundCopperIngot) {
                this.func_70099_a(new ItemStack(ConfigItems.itemNugget, 1, 1), 0.3f);
            } else {
                this.excreteNugget();
            }
        } else if (type < 30) {
            if (Config.foundTinIngot) {
                this.func_70099_a(new ItemStack(ConfigItems.itemNugget, 1, 2), 0.3f);
            } else {
                this.excreteNugget();
            }
        } else if (type < 40) {
            if (Config.foundLeadIngot) {
                this.func_70099_a(new ItemStack(ConfigItems.itemNugget, 1, 4), 0.3f);
            } else {
                this.excreteNugget();
            }
        } else if (type < 50) {
            this.func_70099_a(new ItemStack(ConfigItems.itemNugget, 1, 5), 0.3f);
        } else {
            this.func_70099_a(new ItemStack(ConfigItems.itemNugget, 1, 0), 0.3f);
        }
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
        int j = this.field_70146_Z.nextInt(3) + 1 + this.field_70146_Z.nextInt(1 + p_70628_2_);
        for (int k = 0; k < j; ++k) {
            this.func_70099_a(new ItemStack(Blocks.field_150348_b), 1.0f);
        }
        if (this.func_70901_n()) {
            this.func_145779_a(Items.field_151141_av, 1);
        }
    }

    public void func_70014_b(NBTTagCompound p_70014_1_) {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74768_a("percent", this.nuggetPercent);
    }

    public void func_70037_a(NBTTagCompound p_70037_1_) {
        super.func_70037_a(p_70037_1_);
        this.nuggetPercent = p_70037_1_.func_74762_e("percent");
    }
}

