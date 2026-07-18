/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntityPixie;
import vazkii.botania.common.entity.EntitySignalFlare;
import vazkii.botania.common.item.ItemMod;

public class ItemBottledMana
extends ItemMod {
    IIcon[] icons;
    private static final String TAG_SEED = "randomSeed";

    public ItemBottledMana() {
        this.func_77655_b("manaBottle");
        this.func_77625_d(1);
        this.func_77656_e(6);
    }

    public void effect(EntityPlayer player, int id) {
        block0 : switch (id) {
            case 0: {
                player.field_70159_w = (Math.random() - 0.5) * 3.0;
                player.field_70179_y = (Math.random() - 0.5) * 3.0;
                break;
            }
            case 1: {
                if (player.field_70170_p.field_72995_K || player.field_70170_p.field_73011_w.field_76575_d) break;
                player.field_70170_p.func_147449_b(MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v), (Block)Blocks.field_150358_i);
                break;
            }
            case 2: {
                if (player.field_70170_p.field_72995_K) break;
                player.func_70015_d(4);
                break;
            }
            case 3: {
                if (player.field_70170_p.field_72995_K) break;
                player.field_70170_p.func_72876_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, 0.25f, false);
                break;
            }
            case 4: {
                if (player.field_70170_p.field_73011_w.field_76575_d) break;
                if (!player.field_70170_p.field_72995_K) {
                    player.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 300, 5));
                }
                player.field_70181_x = 6.0;
                break;
            }
            case 5: {
                if (player.field_70170_p.field_72995_K) break;
                player.func_70606_j((float)(player.field_70170_p.field_73012_v.nextInt(19) + 1));
                break;
            }
            case 6: {
                if (player.field_70170_p.field_72995_K) break;
                player.func_70690_d(new PotionEffect(Potion.field_76444_x.field_76415_H, 2400, 9));
                break;
            }
            case 7: {
                if (player.field_70170_p.field_72995_K) break;
                for (int i = 0; i < player.field_71071_by.func_70302_i_(); ++i) {
                    if (i == player.field_71071_by.field_70461_c) continue;
                    ItemStack stackAt = player.field_71071_by.func_70301_a(i);
                    if (stackAt != null) {
                        player.func_71019_a(stackAt, true);
                    }
                    player.field_71071_by.func_70299_a(i, null);
                }
                break;
            }
            case 8: {
                player.field_70125_A = (float)Math.random() * 360.0f;
                player.field_70177_z = (float)Math.random() * 180.0f;
                break;
            }
            case 9: {
                int x = MathHelper.func_76128_c((double)player.field_70165_t);
                MathHelper.func_76128_c((double)player.field_70163_u);
                int z = MathHelper.func_76128_c((double)player.field_70161_v);
                for (int i = 256; i > 0; --i) {
                    Block block = player.field_70170_p.func_147439_a(x, i, z);
                    if (block.isAir((IBlockAccess)player.field_70170_p, x, i, z)) continue;
                    if (!(player instanceof EntityPlayerMP)) break block0;
                    EntityPlayerMP mp = (EntityPlayerMP)player;
                    mp.field_71135_a.func_147364_a(player.field_70165_t, (double)i + 1.6, player.field_70161_v, player.field_70177_z, player.field_70125_A);
                    break block0;
                }
                break;
            }
            case 10: {
                if (player.field_70170_p.field_72995_K) break;
                player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 60, 200));
                break;
            }
            case 11: {
                if (player.field_70170_p.field_72995_K) break;
                player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 6000, 0));
                break;
            }
            case 12: {
                if (player.field_70170_p.field_72995_K) break;
                EntitySignalFlare flare = new EntitySignalFlare(player.field_70170_p);
                flare.func_70107_b(player.field_70165_t, player.field_70163_u, player.field_70161_v);
                flare.setColor(player.field_70170_p.field_73012_v.nextInt(16));
                player.field_70170_p.func_72956_a((Entity)player, "random.explode", 40.0f, (1.0f + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.2f) * 0.7f);
                player.field_70170_p.func_72838_d((Entity)flare);
                int range = 5;
                List entities = player.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)(player.field_70165_t - (double)range), (double)(player.field_70163_u - (double)range), (double)(player.field_70161_v - (double)range), (double)(player.field_70165_t + (double)range), (double)(player.field_70163_u + (double)range), (double)(player.field_70161_v + (double)range)));
                for (EntityLivingBase entity : entities) {
                    if (entity == player || entity instanceof EntityPlayer && MinecraftServer.func_71276_C() != null && !MinecraftServer.func_71276_C().func_71219_W()) continue;
                    entity.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 50, 5));
                }
                break;
            }
            case 13: {
                if (player.field_70170_p.field_72995_K) break;
                EntityPixie pixie = new EntityPixie(player.field_70170_p);
                pixie.func_70107_b(player.field_70165_t, player.field_70163_u + 1.5, player.field_70161_v);
                player.field_70170_p.func_72838_d((Entity)pixie);
                break;
            }
            case 14: {
                if (player.field_70170_p.field_72995_K) break;
                player.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 160, 3));
                player.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 160, 0));
                break;
            }
            case 15: {
                if (player.field_70170_p.field_72995_K) break;
                player.func_70097_a(DamageSource.field_76376_m, player.func_110143_aJ() - 1.0f);
                ItemStack stack = new ItemStack(Items.field_151144_bL, 1, 3);
                ItemNBTHelper.setString(stack, "SkullOwner", player.func_70005_c_());
                player.func_71019_a(stack, true);
            }
        }
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        this.getSeed(par1ItemStack);
    }

    public void randomEffect(EntityPlayer player, ItemStack stack) {
        this.effect(player, new Random(this.getSeed(stack)).nextInt(16));
    }

    long getSeed(ItemStack stack) {
        long seed = ItemNBTHelper.getLong(stack, TAG_SEED, -1L);
        if (seed == -1L) {
            return this.randomSeed(stack);
        }
        return seed;
    }

    long randomSeed(ItemStack stack) {
        long seed = Math.abs(field_77697_d.nextLong());
        ItemNBTHelper.setLong(stack, TAG_SEED, seed);
        return seed;
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(StatCollector.func_74838_a((String)"botaniamisc.bottleTooltip"));
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[6];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public IIcon func_77617_a(int par1) {
        return this.icons[Math.min(this.icons.length - 1, par1)];
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        this.randomEffect(par3EntityPlayer, par1ItemStack);
        par1ItemStack.func_77964_b(par1ItemStack.func_77960_j() + 1);
        this.randomSeed(par1ItemStack);
        if (par1ItemStack.func_77960_j() == 6) {
            return new ItemStack(Items.field_151069_bo);
        }
        return par1ItemStack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 20;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.drink;
    }
}

