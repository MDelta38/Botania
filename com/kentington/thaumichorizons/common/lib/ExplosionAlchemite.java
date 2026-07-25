/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.EnchantmentProtection
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityTNTPrimed
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 *  thaumcraft.common.blocks.BlockAiry
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.ItemCrystalEssence
 *  thaumcraft.common.lib.crafting.ThaumcraftCraftingManager
 *  thaumcraft.common.tiles.TileNode
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityItemInvulnerable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.common.blocks.BlockAiry;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemCrystalEssence;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.tiles.TileNode;

public class ExplosionAlchemite
extends Explosion {
    public boolean field_77286_a;
    public boolean field_82755_b = true;
    private int field_77289_h = 16;
    private Random explosionRNG = new Random();
    private World worldObj;
    public double field_77284_b;
    public double field_77285_c;
    public double field_77282_d;
    public Entity field_77283_e;
    public float field_77280_f;
    public List field_77281_g = new ArrayList();
    private Map field_77288_k = new HashMap();
    private static final String __OBFID = "CL_00000134";

    public ExplosionAlchemite(World p_i1948_1_, Entity p_i1948_2_, double p_i1948_3_, double p_i1948_5_, double p_i1948_7_, float p_i1948_9_) {
        super(p_i1948_1_, p_i1948_2_, p_i1948_3_, p_i1948_5_, p_i1948_7_, p_i1948_9_);
        this.worldObj = p_i1948_1_;
        this.field_77283_e = p_i1948_2_;
        this.field_77280_f = p_i1948_9_;
        this.field_77284_b = p_i1948_3_;
        this.field_77285_c = p_i1948_5_;
        this.field_77282_d = p_i1948_7_;
    }

    public void func_77278_a() {
        double d7;
        double d6;
        double d5;
        int k;
        int j;
        int i;
        float f = this.field_77280_f;
        HashSet<ChunkPosition> hashset = new HashSet<ChunkPosition>();
        for (i = 0; i < this.field_77289_h; ++i) {
            for (j = 0; j < this.field_77289_h; ++j) {
                for (k = 0; k < this.field_77289_h; ++k) {
                    if (i != 0 && i != this.field_77289_h - 1 && j != 0 && j != this.field_77289_h - 1 && k != 0 && k != this.field_77289_h - 1) continue;
                    double d0 = (float)i / ((float)this.field_77289_h - 1.0f) * 2.0f - 1.0f;
                    double d1 = (float)j / ((float)this.field_77289_h - 1.0f) * 2.0f - 1.0f;
                    double d2 = (float)k / ((float)this.field_77289_h - 1.0f) * 2.0f - 1.0f;
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    d0 /= d3;
                    d1 /= d3;
                    d2 /= d3;
                    d5 = this.field_77284_b;
                    d6 = this.field_77285_c;
                    d7 = this.field_77282_d;
                    float f2 = 0.3f;
                    for (float f1 = this.field_77280_f * (0.7f + this.worldObj.field_73012_v.nextFloat() * 0.6f); f1 > 0.0f; f1 -= f2 * 0.75f) {
                        int j1 = MathHelper.func_76128_c((double)d5);
                        int k1 = MathHelper.func_76128_c((double)d6);
                        int l1 = MathHelper.func_76128_c((double)d7);
                        Block block = this.worldObj.func_147439_a(j1, k1, l1);
                        if (this.worldObj.func_147438_o(j1, k1, l1) != null && this.worldObj.func_147438_o(j1, k1, l1) instanceof TileNode) {
                            hashset.add(new ChunkPosition(j1, k1, l1));
                        } else if (block.func_149688_o() != Material.field_151579_a) {
                            float f3 = this.field_77283_e != null ? this.field_77283_e.func_145772_a((Explosion)this, this.worldObj, j1, k1, l1, block) : block.getExplosionResistance(this.field_77283_e, this.worldObj, j1, k1, l1, this.field_77284_b, this.field_77285_c, this.field_77282_d);
                            f1 -= (f3 + 0.3f) * f2;
                        }
                        if (f1 > 0.0f && (this.field_77283_e == null || this.field_77283_e.func_145774_a((Explosion)this, this.worldObj, j1, k1, l1, block, f1))) {
                            hashset.add(new ChunkPosition(j1, k1, l1));
                        }
                        d5 += d0 * (double)f2;
                        d6 += d1 * (double)f2;
                        d7 += d2 * (double)f2;
                    }
                }
            }
        }
        this.field_77281_g.addAll(hashset);
        this.field_77280_f *= 2.0f;
        i = MathHelper.func_76128_c((double)(this.field_77284_b - (double)this.field_77280_f - 1.0));
        j = MathHelper.func_76128_c((double)(this.field_77284_b + (double)this.field_77280_f + 1.0));
        k = MathHelper.func_76128_c((double)(this.field_77285_c - (double)this.field_77280_f - 1.0));
        int i2 = MathHelper.func_76128_c((double)(this.field_77285_c + (double)this.field_77280_f + 1.0));
        int l = MathHelper.func_76128_c((double)(this.field_77282_d - (double)this.field_77280_f - 1.0));
        int j2 = MathHelper.func_76128_c((double)(this.field_77282_d + (double)this.field_77280_f + 1.0));
        List list = this.worldObj.func_72839_b(this.field_77283_e, AxisAlignedBB.func_72330_a((double)i, (double)k, (double)l, (double)j, (double)i2, (double)j2));
        Vec3 vec3 = Vec3.func_72443_a((double)this.field_77284_b, (double)this.field_77285_c, (double)this.field_77282_d);
        for (int i1 = 0; i1 < list.size(); ++i1) {
            double d9;
            Entity entity = (Entity)list.get(i1);
            double d4 = entity.func_70011_f(this.field_77284_b, this.field_77285_c, this.field_77282_d) / (double)this.field_77280_f;
            if (!(d4 <= 1.0) || (d9 = (double)MathHelper.func_76133_a((double)((d5 = entity.field_70165_t - this.field_77284_b) * d5 + (d6 = entity.field_70163_u + (double)entity.func_70047_e() - this.field_77285_c) * d6 + (d7 = entity.field_70161_v - this.field_77282_d) * d7))) == 0.0) continue;
            d5 /= d9;
            d6 /= d9;
            d7 /= d9;
            double d10 = this.worldObj.func_72842_a(vec3, entity.field_70121_D);
            double d11 = (1.0 - d4) * d10;
            entity.func_70097_a(DamageSourceThaumcraft.dissolve, (float)((int)((d11 * d11 + d11) / 2.0 * 16.0 * (double)this.field_77280_f + 1.0)));
            double d8 = EnchantmentProtection.func_92092_a((Entity)entity, (double)d11);
            entity.field_70159_w += d5 * d8;
            entity.field_70181_x += d6 * d8;
            entity.field_70179_y += d7 * d8;
            if (!(entity instanceof EntityPlayer)) continue;
            this.field_77288_k.put((EntityPlayer)entity, Vec3.func_72443_a((double)(d5 * d11), (double)(d6 * d11), (double)(d7 * d11)));
        }
        this.field_77280_f = f;
    }

    public void func_77279_a(boolean p_77279_1_) {
        Block block;
        int k;
        int j;
        int i;
        this.worldObj.func_72908_a(this.field_77284_b, this.field_77285_c, this.field_77282_d, "random.explode", 4.0f, (1.0f + (this.worldObj.field_73012_v.nextFloat() - this.worldObj.field_73012_v.nextFloat()) * 0.2f) * 0.7f);
        if (this.field_77280_f >= 2.0f && this.field_82755_b) {
            this.worldObj.func_72869_a("hugeexplosion", this.field_77284_b, this.field_77285_c, this.field_77282_d, 1.0, 0.0, 0.0);
        } else {
            this.worldObj.func_72869_a("largeexplode", this.field_77284_b, this.field_77285_c, this.field_77282_d, 1.0, 0.0, 0.0);
        }
        ThaumicHorizons.proxy.alchemiteFX(this.worldObj, this.field_77284_b, this.field_77285_c, this.field_77282_d);
        if (this.field_82755_b) {
            for (ChunkPosition chunkposition : this.field_77281_g) {
                ItemStack stack;
                AspectList aspects;
                i = chunkposition.field_151329_a;
                j = chunkposition.field_151327_b;
                k = chunkposition.field_151328_c;
                block = this.worldObj.func_147439_a(i, j, k);
                if (this.worldObj.func_147438_o(i, j, k) != null && this.worldObj.func_147438_o(i, j, k) instanceof TileNode) {
                    TileNode node = (TileNode)this.worldObj.func_147438_o(i, j, k);
                    double d = Math.random();
                    if (d < 0.25) {
                        aspects = node.getAspects();
                        if (aspects != null) {
                            for (Aspect asp : aspects.getAspects()) {
                                stack = new ItemStack(ConfigItems.itemCrystalEssence, aspects.getAmount(asp));
                                ((ItemCrystalEssence)stack.func_77973_b()).setAspects(stack, new AspectList().add(asp, 1));
                                this.worldObj.func_72838_d((Entity)new EntityItemInvulnerable(this.worldObj, i, j, k, stack));
                            }
                            ThaumicHorizons.proxy.disintegrateExplodeFX(this.worldObj, i, j, k);
                        }
                        BlockAiry.explodify((World)this.worldObj, (int)i, (int)j, (int)k);
                        continue;
                    }
                    if (d < 0.5) {
                        node.setNodeModifier(NodeModifier.FADING);
                        continue;
                    }
                    node.setNodeModifier(null);
                    node.setNodeType(NodeType.UNSTABLE);
                    continue;
                }
                if (block.func_149688_o() == Material.field_151579_a) continue;
                aspects = this.getAspects(Item.func_150898_a((Block)block), this.worldObj.func_72805_g(i, j, k));
                if (aspects != null && aspects.size() > 0) {
                    for (Aspect asp : aspects.getAspects()) {
                        stack = new ItemStack(ConfigItems.itemCrystalEssence, aspects.getAmount(asp));
                        ((ItemCrystalEssence)stack.func_77973_b()).setAspects(stack, new AspectList().add(asp, 1));
                        this.worldObj.func_72838_d((Entity)new EntityItemInvulnerable(this.worldObj, i, j, k, stack));
                    }
                    ThaumicHorizons.proxy.disintegrateExplodeFX(this.worldObj, i, j, k);
                } else {
                    block.func_149690_a(this.worldObj, i, j, k, this.worldObj.func_72805_g(i, j, k), 1.0f, 0);
                }
                block.onBlockExploded(this.worldObj, i, j, k, (Explosion)this);
            }
        }
        if (this.field_77286_a) {
            for (ChunkPosition chunkposition : this.field_77281_g) {
                i = chunkposition.field_151329_a;
                j = chunkposition.field_151327_b;
                k = chunkposition.field_151328_c;
                block = this.worldObj.func_147439_a(i, j, k);
                Block block1 = this.worldObj.func_147439_a(i, j - 1, k);
                if (block.func_149688_o() != Material.field_151579_a || !block1.func_149730_j() || this.explosionRNG.nextInt(3) != 0) continue;
                this.worldObj.func_147449_b(i, j, k, (Block)Blocks.field_150480_ab);
            }
        }
    }

    private AspectList getAspects(Item block, int meta) {
        ItemStack tmpStack = new ItemStack(block, 1, meta);
        AspectList tmp = ThaumcraftCraftingManager.getObjectTags((ItemStack)tmpStack);
        if ((tmp = ThaumcraftCraftingManager.getBonusTags((ItemStack)tmpStack, (AspectList)tmp)) == null || tmp.size() == 0) {
            tmp = ThaumcraftApi.objectTags.get(Arrays.asList(block, Short.MAX_VALUE));
            if (meta == Short.MAX_VALUE && tmp == null) {
                int index = 0;
                do {
                    tmp = ThaumcraftApi.objectTags.get(Arrays.asList(block, index));
                } while (++index < 16 && tmp == null);
            }
        }
        return tmp;
    }

    public Map func_77277_b() {
        return this.field_77288_k;
    }

    public EntityLivingBase func_94613_c() {
        return this.field_77283_e == null ? null : (this.field_77283_e instanceof EntityTNTPrimed ? ((EntityTNTPrimed)this.field_77283_e).func_94083_c() : (this.field_77283_e instanceof EntityLivingBase ? (EntityLivingBase)this.field_77283_e : null));
    }
}

