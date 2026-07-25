/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityMultiPart
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.boss.EntityDragonPart
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.potion.Potion
 *  net.minecraft.stats.AchievementList
 *  net.minecraft.stats.StatBase
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 */
package thaumcraft.common.items.equipment;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import thaumcraft.api.IRepairable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.lib.utils.Utils;

public class ItemElementalSword
extends ItemSword
implements IRepairable {
    public IIcon icon;

    public ItemElementalSword(Item.ToolMaterial enumtoolmaterial) {
        super(enumtoolmaterial);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:elementalsword");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.rare;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        List targets;
        super.onUsingTick(stack, player, count);
        int ticks = this.func_77626_a(stack) - count;
        if (player.field_70181_x < 0.0) {
            player.field_70181_x /= (double)1.2f;
            player.field_70143_R /= 1.2f;
        }
        player.field_70181_x += (double)0.08f;
        if (player.field_70181_x > 0.5) {
            player.field_70181_x = 0.2f;
        }
        if (player instanceof EntityPlayerMP) {
            Utils.resetFloatCounter((EntityPlayerMP)player);
        }
        if ((targets = player.field_70170_p.func_72839_b((Entity)player, player.field_70121_D.func_72314_b(2.5, 2.5, 2.5))).size() > 0) {
            for (int var9 = 0; var9 < targets.size(); ++var9) {
                Entity entity = (Entity)targets.get(var9);
                if (entity instanceof EntityPlayer || entity.field_70128_L || player.field_70154_o != null && player.field_70154_o == entity) continue;
                Vec3 p = Vec3.func_72443_a((double)player.field_70165_t, (double)player.field_70163_u, (double)player.field_70161_v);
                Vec3 t = Vec3.func_72443_a((double)entity.field_70165_t, (double)entity.field_70163_u, (double)entity.field_70161_v);
                double distance = p.func_72438_d(t) + 0.1;
                Vec3 r = Vec3.func_72443_a((double)(t.field_72450_a - p.field_72450_a), (double)(t.field_72448_b - p.field_72448_b), (double)(t.field_72449_c - p.field_72449_c));
                entity.field_70159_w += r.field_72450_a / 2.5 / distance;
                entity.field_70181_x += r.field_72448_b / 2.5 / distance;
                entity.field_70179_y += r.field_72449_c / 2.5 / distance;
            }
        }
        if (player.field_70170_p.field_72995_K) {
            int miny = (int)(player.field_70121_D.field_72338_b - 2.0);
            if (player.field_70122_E) {
                miny = MathHelper.func_76128_c((double)player.field_70121_D.field_72338_b);
            }
            for (int a = 0; a < 5; ++a) {
                Thaumcraft.proxy.smokeSpiral(player.field_70170_p, player.field_70165_t, player.field_70121_D.field_72338_b + (double)(player.field_70131_O / 2.0f), player.field_70161_v, 1.5f, player.field_70170_p.field_73012_v.nextInt(360), miny, 0xDDDDDD);
            }
            if (player.field_70122_E) {
                float r1 = player.field_70170_p.field_73012_v.nextFloat() * 360.0f;
                float mx = -MathHelper.func_76126_a((float)(r1 / 180.0f * (float)Math.PI)) / 5.0f;
                float mz = MathHelper.func_76134_b((float)(r1 / 180.0f * (float)Math.PI)) / 5.0f;
                player.field_70170_p.func_72869_a("smoke", player.field_70165_t, player.field_70121_D.field_72338_b + (double)0.1f, player.field_70161_v, (double)mx, 0.0, (double)mz);
            }
        } else if (ticks == 0 || ticks % 20 == 0) {
            player.field_70170_p.func_72956_a((Entity)player, "thaumcraft:wind", 0.5f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f);
        }
        if (ticks % 20 == 0) {
            stack.func_77972_a(1, (EntityLivingBase)player);
        }
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity.func_70089_S()) {
            List targets = player.field_70170_p.func_72839_b((Entity)player, entity.field_70121_D.func_72314_b(1.2, 1.1, 1.2));
            int count = 0;
            if (targets.size() > 1) {
                for (int var9 = 0; var9 < targets.size(); ++var9) {
                    Entity var10 = (Entity)targets.get(var9);
                    if (var10.field_70128_L || var10 instanceof EntityGolemBase && ((EntityGolemBase)var10).getOwnerName().equals(player.func_70005_c_()) || var10 instanceof EntityTameable && ((EntityTameable)var10).func_152113_b().equals(player.func_70005_c_()) || !(var10 instanceof EntityLiving) || var10.func_145782_y() == entity.func_145782_y() || var10 instanceof EntityPlayer && ((EntityPlayer)var10).func_70005_c_() == player.func_70005_c_() || !var10.func_70089_S()) continue;
                    this.attackTargetEntityWithCurrentItem(var10, player);
                    ++count;
                }
                if (count > 0 && !player.field_70170_p.field_72995_K) {
                    player.field_70170_p.func_72956_a(entity, "thaumcraft:swing", 1.0f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f);
                }
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    public void attackTargetEntityWithCurrentItem(Entity par1Entity, EntityPlayer player) {
        if (MinecraftForge.EVENT_BUS.post((Event)new AttackEntityEvent(player, par1Entity))) {
            return;
        }
        if (par1Entity.func_70075_an() && !par1Entity.func_85031_j((Entity)player)) {
            float f = (float)player.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
            int i = 0;
            float f1 = 0.0f;
            if (par1Entity instanceof EntityLivingBase) {
                f1 = EnchantmentHelper.func_77512_a((EntityLivingBase)player, (EntityLivingBase)((EntityLivingBase)par1Entity));
                i += EnchantmentHelper.func_77507_b((EntityLivingBase)player, (EntityLivingBase)((EntityLivingBase)par1Entity));
            }
            if (player.func_70051_ag()) {
                ++i;
            }
            if (f > 0.0f || f1 > 0.0f) {
                IEntityMultiPart ientitymultipart;
                boolean flag2;
                boolean flag;
                boolean bl = flag = player.field_70143_R > 0.0f && !player.field_70122_E && !player.func_70617_f_() && !player.func_70090_H() && !player.func_70644_a(Potion.field_76440_q) && player.field_70154_o == null && par1Entity instanceof EntityLivingBase;
                if (flag && f > 0.0f) {
                    f *= 1.5f;
                }
                f += f1;
                boolean flag1 = false;
                int j = EnchantmentHelper.func_90036_a((EntityLivingBase)player);
                if (par1Entity instanceof EntityLivingBase && j > 0 && !par1Entity.func_70027_ad()) {
                    flag1 = true;
                    par1Entity.func_70015_d(1);
                }
                if (flag2 = par1Entity.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), f)) {
                    if (i > 0) {
                        par1Entity.func_70024_g((double)(-MathHelper.func_76126_a((float)(player.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(player.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f));
                        player.field_70159_w *= 0.6;
                        player.field_70179_y *= 0.6;
                        player.func_70031_b(false);
                    }
                    if (flag) {
                        player.func_71009_b(par1Entity);
                    }
                    if (f1 > 0.0f) {
                        player.func_71047_c(par1Entity);
                    }
                    if (f >= 18.0f) {
                        player.func_71029_a((StatBase)AchievementList.field_75999_E);
                    }
                    player.func_130011_c(par1Entity);
                    if (par1Entity instanceof EntityLivingBase) {
                        EnchantmentHelper.func_151384_a((EntityLivingBase)((EntityLivingBase)par1Entity), (Entity)player);
                    }
                }
                ItemStack itemstack = player.func_71045_bC();
                Entity object = par1Entity;
                if (par1Entity instanceof EntityDragonPart && (ientitymultipart = ((EntityDragonPart)par1Entity).field_70259_a) != null && ientitymultipart instanceof EntityLivingBase) {
                    object = (EntityLivingBase)ientitymultipart;
                }
                if (itemstack != null && object instanceof EntityLivingBase) {
                    itemstack.func_77961_a((EntityLivingBase)object, player);
                    if (itemstack.field_77994_a <= 0) {
                        player.func_71028_bD();
                    }
                }
                if (par1Entity instanceof EntityLivingBase) {
                    player.func_71064_a(StatList.field_75951_w, Math.round(f * 10.0f));
                    if (j > 0 && flag2) {
                        par1Entity.func_70015_d(j * 4);
                    } else if (flag1) {
                        par1Entity.func_70066_B();
                    }
                }
                player.func_71020_j(0.3f);
            }
        }
    }
}

