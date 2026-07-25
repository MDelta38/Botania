/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.stats.AchievementList
 *  net.minecraft.stats.StatBase
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  thaumcraft.api.IRepairable
 *  thaumcraft.api.aspects.Aspect
 *  travellersgear.api.IActiveAbility
 *  travellersgear.api.IEventGear
 */
package witchinggadgets.common.items.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import thaumcraft.api.IRepairable;
import thaumcraft.api.aspects.Aspect;
import travellersgear.api.IActiveAbility;
import travellersgear.api.IEventGear;
import witchinggadgets.api.IPrimordialCrafting;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.items.tools.IPrimordialGear;
import witchinggadgets.common.util.Utilities;

public class ItemPrimordialHammer
extends ItemPickaxe
implements IPrimordialCrafting,
IActiveAbility,
IRepairable,
IEventGear,
IPrimordialGear {
    IIcon overlay;
    public static Material[] validMats = new Material[]{Material.field_151574_g, Material.field_151571_B, Material.field_151596_z, Material.field_151592_s, Material.field_151577_b, Material.field_151578_c, Material.field_151588_w, Material.field_151573_f, Material.field_151598_x, Material.field_76233_E, Material.field_151576_e, Material.field_151595_p, Material.field_151597_y};

    public ItemPrimordialHammer(Item.ToolMaterial mat) {
        super(mat);
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int slot, boolean equipped) {
        super.func_77663_a(stack, world, entity, slot, equipped);
        if (stack.func_77951_h() && entity != null && entity.field_70173_aa % 40 == 0 && entity instanceof EntityLivingBase) {
            stack.func_77972_a(-1, (EntityLivingBase)entity);
        }
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity target) {
        if (target instanceof EntityLivingBase) {
            ((EntityLivingBase)target).func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 20));
            if (this.getAbility(stack) == 0) {
                for (EntityLivingBase e : player.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)(target.field_70165_t - 2.0), (double)(target.field_70163_u - 2.0), (double)(target.field_70161_v - 2.0), (double)(target.field_70165_t + 2.0), (double)(target.field_70163_u + 2.0), (double)(target.field_70161_v + 2.0)))) {
                    boolean flag2;
                    boolean flag;
                    if (!e.func_70075_an() || e.func_85031_j((Entity)player) || e.equals((Object)player)) continue;
                    float f = (float)player.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
                    int i = EnchantmentHelper.func_77507_b((EntityLivingBase)player, (EntityLivingBase)e);
                    float f1 = EnchantmentHelper.func_77512_a((EntityLivingBase)player, (EntityLivingBase)e);
                    if (player.func_70051_ag()) {
                        ++i;
                    }
                    if (!(f > 0.0f) && !(f1 > 0.0f)) continue;
                    boolean bl = flag = player.field_70143_R > 0.0f && !player.field_70122_E && !player.func_70617_f_() && !player.func_70090_H() && !player.func_70644_a(Potion.field_76440_q) && player.field_70154_o == null && e instanceof EntityLivingBase;
                    if (flag && f > 0.0f) {
                        f *= 1.5f;
                    }
                    f += f1;
                    boolean flag1 = false;
                    int j = EnchantmentHelper.func_90036_a((EntityLivingBase)player);
                    if (j > 0 && !e.func_70027_ad()) {
                        flag1 = true;
                        e.func_70015_d(1);
                    }
                    if (flag2 = e.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), f)) {
                        if (i > 0) {
                            e.func_70024_g((double)(-MathHelper.func_76126_a((float)(player.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(player.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f));
                            player.field_70159_w *= 0.6;
                            player.field_70179_y *= 0.6;
                            player.func_70031_b(false);
                        }
                        if (flag) {
                            player.func_71009_b((Entity)e);
                        }
                        if (f1 > 0.0f) {
                            player.func_71047_c((Entity)e);
                        }
                        if (f >= 18.0f) {
                            player.func_71029_a((StatBase)AchievementList.field_75999_E);
                        }
                        player.func_130011_c((Entity)e);
                        EnchantmentHelper.func_151384_a((EntityLivingBase)e, (Entity)player);
                        EnchantmentHelper.func_151385_b((EntityLivingBase)player, (Entity)e);
                        player.func_71064_a(StatList.field_75951_w, Math.round(f * 10.0f));
                        if (j > 0) {
                            e.func_70015_d(j * 4);
                        }
                        player.func_71020_j(0.3f);
                        continue;
                    }
                    if (!flag1) continue;
                    e.func_70066_B();
                }
            }
            if (this.getAbility(stack) == 2) {
                ((EntityLivingBase)target).func_70690_d(new PotionEffect(WGContent.pot_cinderCoat.field_76415_H, 80, 1));
                target.func_70015_d(4);
            }
            if (this.getAbility(stack) == 3) {
                ((EntityLivingBase)target).func_70690_d(new PotionEffect(WGContent.pot_dissolve.field_76415_H, 80, 2));
            }
            if (this.getAbility(stack) == 5) {
                ((EntityLivingBase)target).func_70690_d(new PotionEffect(Potion.field_76437_t.func_76396_c(), 60));
                ((EntityLivingBase)target).func_70690_d(new PotionEffect(Potion.field_76438_s.func_76396_c(), 120));
            }
        }
        return false;
    }

    public void onUserDamaged(LivingHurtEvent event, ItemStack stack) {
        if (this.getAbility(stack) == 1 && ((EntityPlayer)event.entityLiving).func_70632_aY()) {
            int time = event.entityLiving.func_70660_b(Potion.field_76429_m) != null ? event.entityLiving.func_70660_b(Potion.field_76429_m).func_76459_b() : 0;
            time = Math.min(time + 30, 80);
            int amp = event.entityLiving.func_70660_b(Potion.field_76429_m) != null ? event.entityLiving.func_70660_b(Potion.field_76429_m).func_76458_c() : -1;
            amp = Math.min(amp + 1, 2);
            event.entityLiving.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, time, amp));
        }
    }

    public boolean canActivate(EntityPlayer player, ItemStack stack, boolean isInHand) {
        return true;
    }

    public void activate(EntityPlayer player, ItemStack stack) {
        if (!player.field_70170_p.field_72995_K) {
            this.cycleAbilities(stack);
        }
    }

    @Override
    public int getReturnedPearls(ItemStack stack) {
        return 2;
    }

    public String func_77653_i(ItemStack stack) {
        int ab = this.getAbility(stack);
        String add = ab >= 0 && ab < 6 ? " " + EnumChatFormatting.DARK_GRAY + "- \u00a7" + ((Aspect)Aspect.getPrimalAspects().get(ab)).getChatcolor() + ((Aspect)Aspect.getPrimalAspects().get(ab)).getName() + EnumChatFormatting.RESET : "";
        return super.func_77653_i(stack) + add;
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("witchinggadgets:primordialHammer");
        this.overlay = iconRegister.func_94245_a("witchinggadgets:primordialHammer_overlay");
    }

    public boolean func_77623_v() {
        return true;
    }

    public int getRenderPasses(int meta) {
        return 2;
    }

    public IIcon func_77618_c(int par1, int pass) {
        if (pass == 0) {
            return this.field_77791_bV;
        }
        return this.overlay;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int pass) {
        int ab;
        if (pass == 1 && (ab = this.getAbility(stack)) >= 0 && ab < 6) {
            return ((Aspect)Aspect.getPrimalAspects().get(this.getAbility(stack))).getColor();
        }
        return 0xFFFFFF;
    }

    public boolean onBlockStartBreak(ItemStack stack, int ix, int iy, int iz, EntityPlayer player) {
        World world = player.field_70170_p;
        MovingObjectPosition mop = this.func_77621_a(world, player, true);
        if (mop == null) {
            return false;
        }
        int side = mop.field_72310_e;
        int[] range = new int[3];
        range[0] = side == 4 || side == 5 ? 0 : 1;
        range[1] = side == 0 || side == 1 ? 0 : 1;
        int n = range[2] = side == 2 || side == 3 ? 0 : 1;
        if (!player.func_70093_af()) {
            for (int yy = -range[1]; yy <= range[1]; ++yy) {
                for (int zz = -range[2]; zz <= range[2]; ++zz) {
                    for (int xx = -range[0]; xx <= range[0]; ++xx) {
                        int x = ix + xx;
                        int y = iy + yy;
                        int z = iz + zz;
                        if (!world.func_72899_e(x, y, z)) continue;
                        Block block = world.func_147439_a(x, y, z);
                        int meta = world.func_72805_g(x, y, z);
                        Material mat = world.func_147439_a(x, y, z).func_149688_o();
                        if (world.field_72995_K || block == null || block.isAir((IBlockAccess)world, x, y, z) || block.func_149737_a(player, world, x, y, z) == 0.0f || !block.canHarvestBlock(player, meta) || !Utilities.isRightMaterial(mat, validMats)) continue;
                        if (!player.field_71075_bZ.field_75098_d && block != Blocks.field_150357_h) {
                            int localMeta = world.func_72805_g(x, y, z);
                            if (block.removedByPlayer(world, player, x, y, z, true)) {
                                block.func_149664_b(world, x, y, z, localMeta);
                            }
                            block.func_149681_a(world, x, y, z, localMeta, player);
                            block.func_149636_a(world, player, x, y, z, localMeta);
                        } else {
                            world.func_147468_f(x, y, z);
                        }
                        if (world.field_72995_K) continue;
                        world.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)block) + (meta << 12));
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void cycleAbilities(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int cur = stack.func_77978_p().func_74762_e("currentMode");
        if (++cur >= 6) {
            cur = 0;
        }
        stack.func_77978_p().func_74768_a("currentMode", cur);
    }

    @Override
    public int getAbility(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        return stack.func_77978_p().func_74762_e("currentMode");
    }

    public EnumAction func_77661_b(ItemStack stack) {
        return EnumAction.block;
    }

    public int func_77626_a(ItemStack stack) {
        return 72000;
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        player.func_71008_a(stack, this.func_77626_a(stack));
        return stack;
    }

    public boolean func_82789_a(ItemStack stack1, ItemStack stack2) {
        return Utilities.compareToOreName(stack2, "ingotVoid");
    }

    public void onUserAttacking(AttackEntityEvent event, ItemStack stack) {
    }

    public void onUserJump(LivingEvent.LivingJumpEvent event, ItemStack stack) {
    }

    public void onUserFall(LivingFallEvent event, ItemStack stack) {
    }

    public void onUserTargeted(LivingSetAttackTargetEvent event, ItemStack stack) {
    }
}

