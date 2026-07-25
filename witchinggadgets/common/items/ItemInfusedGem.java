/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.lib.utils.EntityUtils
 *  thaumcraft.common.tiles.TileMirror
 *  thaumcraft.common.tiles.TileMirrorEssentia
 *  thaumcraft.common.tiles.TileNode
 *  thaumcraft.common.tiles.TileVisRelay
 */
package witchinggadgets.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.tiles.TileMirror;
import thaumcraft.common.tiles.TileMirrorEssentia;
import thaumcraft.common.tiles.TileNode;
import thaumcraft.common.tiles.TileVisRelay;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.api.IInfusedGem;
import witchinggadgets.client.ClientTickHandler;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.blocks.tiles.TileEntityTempLight;
import witchinggadgets.common.util.Utilities;

public class ItemInfusedGem
extends Item
implements IInfusedGem {
    IIcon[] icons = new IIcon[GemCut.values().length];
    static HashMap<Integer, Object> powerBeams = new HashMap();

    public ItemInfusedGem() {
        this.func_77637_a(WitchingGadgets.tabWG);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        boolean dmg;
        int brittle = EnchantmentHelper.func_77506_a((int)WGContent.enc_gemstoneBrittle.field_77352_x, (ItemStack)stack);
        if (brittle >= 3 || world.field_73012_v.nextInt(20) < brittle) {
            if (!world.field_72995_K) {
                if (!player.field_71071_by.func_70441_a(new ItemStack(WGContent.ItemMaterial, 1, 13))) {
                    player.func_71019_a(new ItemStack(WGContent.ItemMaterial, 1, 13), false);
                }
                player.func_145747_a((IChatComponent)new ChatComponentTranslation("wg.chat.gem.shatter", new Object[0]));
            }
            --stack.field_77994_a;
            return stack;
        }
        int potency = EnchantmentHelper.func_77506_a((int)WGContent.enc_gemstonePotency.field_77352_x, (ItemStack)stack);
        if (ItemInfusedGem.getCut(stack) == GemCut.POINT) {
            if (!this.performEffect(GemCut.POINT.toString(), ItemInfusedGem.getAspect(stack), potency, brittle, player)) {
                return stack;
            }
            if (!world.field_72995_K && !player.field_71071_by.func_70441_a(new ItemStack(WGContent.ItemMaterial, 1, 13))) {
                player.func_71019_a(new ItemStack(WGContent.ItemMaterial, 1, 13), false);
            }
            --stack.field_77994_a;
        }
        if (ItemInfusedGem.getCut(stack) == GemCut.OVAL && ItemInfusedGem.getAspect(stack) != null && this.getDamage(stack) + this.getConsumedCharge(GemCut.OVAL.toString(), ItemInfusedGem.getAspect(stack), player) <= this.getMaxDamage(stack) && (dmg = this.performEffect(GemCut.OVAL.toString(), ItemInfusedGem.getAspect(stack), potency, brittle, player)) && !player.field_71075_bZ.field_75098_d) {
            this.setDamage(stack, this.getDamage(stack) + this.getConsumedCharge(GemCut.OVAL.toString(), ItemInfusedGem.getAspect(stack), player));
        }
        return stack;
    }

    @Override
    public boolean performEffect(String cut, Aspect aspect, int potency, int brittle, EntityPlayer player) {
        if (aspect == null || !aspect.isPrimal()) {
            return false;
        }
        World world = player.field_70170_p;
        if (cut == GemCut.POINT.toString()) {
            int x = (int)Math.floor(player.field_70165_t);
            int y = (int)player.field_70163_u + 1;
            int z = (int)Math.floor(player.field_70161_v);
            if (aspect.equals(Aspect.AIR)) {
                player.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 300 + 20 * potency, 4 + (potency > 1 ? 1 : 0)));
            }
            if (aspect.equals(Aspect.FIRE)) {
                TileEntity te;
                int[] dist;
                int[] nArray;
                if (potency > 2) {
                    int[] nArray2 = new int[6];
                    nArray2[0] = -8;
                    nArray2[1] = -6;
                    nArray2[2] = -4;
                    nArray2[3] = 4;
                    nArray2[4] = 6;
                    nArray = nArray2;
                    nArray2[5] = 8;
                } else if (potency > 1) {
                    int[] nArray3 = new int[4];
                    nArray3[0] = -6;
                    nArray3[1] = -4;
                    nArray3[2] = 4;
                    nArray = nArray3;
                    nArray3[3] = 6;
                } else if (potency > 0) {
                    int[] nArray4 = new int[2];
                    nArray4[0] = -4;
                    nArray = nArray4;
                    nArray4[1] = 4;
                } else {
                    int[] nArray5 = new int[2];
                    nArray5[0] = -2;
                    nArray = nArray5;
                    nArray5[1] = 2;
                }
                for (int xoff : dist = nArray) {
                    for (int zoff : dist) {
                        if (!world.func_147437_c(x + xoff, y, z + zoff) || world.func_72957_l(x + xoff, y, z + zoff) >= 10) continue;
                        if (!world.field_72995_K) {
                            world.func_147449_b(x + xoff, y, z + zoff, WGContent.BlockCustomAiry);
                        }
                        world.func_147464_a(x + xoff, y, z + zoff, WGContent.BlockCustomAiry, 10);
                        te = world.func_147438_o(x + xoff, y, z + zoff);
                        if (te == null || !(te instanceof TileEntityTempLight)) continue;
                        ((TileEntityTempLight)te).tickMax = 3600 + potency * 800;
                    }
                }
                if (world.func_147437_c(x, y + 2, z) && world.func_72957_l(x, y + 2, z) < 10) {
                    if (!world.field_72995_K) {
                        world.func_147449_b(x, y + 2, z, WGContent.BlockCustomAiry);
                    }
                    world.func_147464_a(x, y + 2, z, WGContent.BlockCustomAiry, 10);
                    te = world.func_147438_o(x, y + 2, z);
                    if (te != null && te instanceof TileEntityTempLight) {
                        ((TileEntityTempLight)te).tickMax = 3600 + potency * 800;
                    }
                }
            }
            if (aspect.equals(Aspect.WATER)) {
                int dist = 2 + potency;
                for (int yOff = -3; yOff <= 0; ++yOff) {
                    for (int xOff = -dist; xOff <= dist; ++xOff) {
                        for (int zOff = -dist; zOff <= dist; ++zOff) {
                            if (world.func_147439_a(x + xOff, y + yOff, z + zOff) == null) continue;
                            if (world.func_147439_a(x + xOff, y + yOff, z + zOff).equals(Blocks.field_150355_j)) {
                                world.func_147449_b(x + xOff, y + yOff, z + zOff, Blocks.field_150432_aD);
                            }
                            if (world.func_147439_a(x + xOff, y + yOff, z + zOff).equals(Blocks.field_150353_l)) {
                                world.func_147449_b(x + xOff, y + yOff, z + zOff, Blocks.field_150343_Z);
                            }
                            if (!world.func_147439_a(x + xOff, y + yOff, z + zOff).equals(Blocks.field_150356_k)) continue;
                            world.func_147449_b(x + xOff, y + yOff, z + zOff, Blocks.field_150347_e);
                        }
                    }
                }
            }
            if (aspect.equals(Aspect.EARTH) && !world.field_72995_K) {
                player.func_70690_d(new PotionEffect(WGContent.pot_knockbackRes.field_76415_H, 100 + potency * 100, 1 + (potency > 0 ? 1 : 0)));
            }
            if (aspect.equals(Aspect.ORDER)) {
                player.func_70691_i((float)(6 + potency * 6));
            }
            if (aspect.equals(Aspect.ENTROPY)) {
                Thaumcraft.addWarpToPlayer((EntityPlayer)player, (int)(3 + potency * 3), (boolean)true);
            }
            return true;
        }
        if (cut == GemCut.OVAL.toString()) {
            int z;
            int y;
            int x;
            String node;
            int targetY;
            int targetX;
            MovingObjectPosition mop = EntityUtils.getMovingObjectPositionFromPlayer((World)world, (EntityPlayer)player, (boolean)false);
            int n = mop == null ? 0 : (targetX = mop.field_72311_b + (mop.field_72310_e == 4 ? -1 : (mop.field_72310_e == 5 ? 1 : 0)));
            int n2 = mop == null ? 0 : (targetY = mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : (mop.field_72310_e == 1 ? 1 : 0)));
            int targetZ = mop == null ? 0 : mop.field_72309_d + (mop.field_72310_e == 2 ? -1 : (mop.field_72310_e == 3 ? 1 : 0));
            boolean dmg = false;
            if (aspect.equals(Aspect.FIRE) && mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && world.func_147437_c(targetX, targetY, targetZ)) {
                world.func_72908_a((double)((float)targetX + 5.0f), (double)((float)targetY + 5.0f), (double)((float)targetZ + 0.5f), "mob.ghast.fireball", 1.0f, field_77697_d.nextFloat() * 0.4f + 0.8f);
                world.func_147465_d(targetX, targetY, targetZ, (Block)Blocks.field_150480_ab, 0, 3);
                dmg = true;
            }
            if (aspect.equals(Aspect.WATER) && mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && (world.func_147437_c(targetX, targetY, targetZ) || world.func_147439_a(targetX, targetY, targetZ).isReplaceable((IBlockAccess)world, targetX, targetY, targetZ))) {
                if (world.field_73011_w.field_76575_d) {
                    world.func_72908_a((double)((float)targetX + 5.0f), (double)((float)targetY + 5.0f), (double)((float)targetZ + 0.5f), "random.fizz", 1.0f, field_77697_d.nextFloat() * 0.4f + 0.8f);
                    for (int l = 0; l < 8; ++l) {
                        world.func_72869_a("largesmoke", (double)targetX + Math.random(), (double)targetY + Math.random(), (double)targetZ + Math.random(), 0.0, 0.0, 0.0);
                    }
                } else {
                    if (!world.field_72995_K && !world.func_147439_a(targetX, targetY, targetZ).func_149688_o().func_76220_a() && world.func_147439_a(targetX, targetY, targetZ).func_149688_o().func_76224_d()) {
                        world.func_147480_a(targetX, targetY, targetZ, true);
                    }
                    world.func_147465_d(targetX, targetY, targetZ, Blocks.field_150355_j, 0, 3);
                }
                dmg = true;
            }
            if (aspect.equals(Aspect.EARTH) && mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                List<ChunkCoordinates> ores = this.getOres(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                if (world.field_72995_K) {
                    for (ChunkCoordinates cc : ores) {
                        ClientTickHandler.oreHighlightMap.put(cc, 1000);
                    }
                }
                dmg = true;
            }
            if (aspect.equals(Aspect.AIR)) {
                Vec3 lookVec = player.func_70040_Z();
                float mod = 1 + potency;
                player.func_70024_g(lookVec.field_72450_a * (double)mod, lookVec.field_72448_b * (double)mod, lookVec.field_72449_c * (double)mod);
                player.field_70143_R = 0.0f;
                dmg = true;
            }
            if (aspect.equals(Aspect.ORDER) && TileNode.locations.get(node = Utilities.findCloseNode(world, new ChunkCoordinates(x = (int)Math.floor(player.field_70165_t), y = (int)player.field_70163_u + 1, z = (int)Math.floor(player.field_70161_v)))) != null) {
                int nX = (Integer)((ArrayList)TileNode.locations.get(node)).get(1);
                int nY = (Integer)((ArrayList)TileNode.locations.get(node)).get(2);
                int nZ = (Integer)((ArrayList)TileNode.locations.get(node)).get(3);
                float fnX = (float)nX + 0.5f;
                float fnY = (float)nY + 0.5f;
                float fnZ = (float)nZ + 0.5f;
                double xOff = (double)fnX - player.field_70165_t;
                double zOff = (double)fnZ - player.field_70161_v;
                double yOff = (double)fnY - (player.field_70163_u + (double)player.func_70047_e());
                double d3 = MathHelper.func_76133_a((double)(xOff * xOff + zOff * zOff));
                float yaw = (float)(Math.atan2(zOff, xOff) * 180.0 / Math.PI) - 90.0f;
                float f3 = MathHelper.func_76142_g((float)(yaw - player.field_70177_z));
                yaw = player.field_70177_z + f3;
                float pitch = (float)(-(Math.atan2(yOff, d3) * 180.0 / Math.PI));
                f3 = MathHelper.func_76142_g((float)(pitch - player.field_70125_A));
                player.field_70125_A = pitch = player.field_70125_A + f3;
                player.field_70177_z = yaw;
                if (world.field_72995_K) {
                    AspectList al = world.func_147438_o(nX, nY, nZ) instanceof TileNode ? ((TileNode)world.func_147438_o(nX, nY, nZ)).getAspects() : new AspectList();
                    int col = 0xFFFFFF;
                    for (Aspect a : al.getAspects()) {
                        if (a == null) continue;
                        col = ClientUtilities.blendColoursToInt(col, a.getColor());
                    }
                    double[] hand = ClientUtilities.getPlayerHandPos(player, true);
                    WitchingGadgets.proxy.createTargetedWispFx(player.field_70170_p, hand[0], hand[1], hand[2], fnX, fnY, fnZ, col, 0.5f, 0.0f, true, true);
                }
                dmg = true;
            }
            if (aspect.equals(Aspect.ENTROPY)) {
                if (Config.allowMirrors) {
                    TileEntity tile;
                    if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && ((tile = world.func_147438_o(targetX, targetY, targetZ)) instanceof TileMirror || tile instanceof TileMirrorEssentia)) {
                        boolean link;
                        boolean bl = link = tile instanceof TileMirror ? ((TileMirror)world.func_147438_o((int)targetX, (int)targetY, (int)targetZ)).linked : ((TileMirrorEssentia)world.func_147438_o((int)targetX, (int)targetY, (int)targetZ)).linked;
                        if (link) {
                            float rot;
                            int tz;
                            int ty;
                            int dim = tile instanceof TileMirror ? ((TileMirror)world.func_147438_o((int)targetX, (int)targetY, (int)targetZ)).linkDim : ((TileMirrorEssentia)world.func_147438_o((int)targetX, (int)targetY, (int)targetZ)).linkDim;
                            int tx = tile instanceof TileMirror ? ((TileMirror)world.func_147438_o((int)targetX, (int)targetY, (int)targetZ)).linkX : ((TileMirrorEssentia)world.func_147438_o((int)targetX, (int)targetY, (int)targetZ)).linkX;
                            ForgeDirection fd = ForgeDirection.getOrientation((int)(world.func_72805_g(tx, ty = tile instanceof TileMirror ? ((TileMirror)world.func_147438_o((int)targetX, (int)targetY, (int)targetZ)).linkY : ((TileMirrorEssentia)world.func_147438_o((int)targetX, (int)targetY, (int)targetZ)).linkY, tz = tile instanceof TileMirror ? ((TileMirror)world.func_147438_o((int)targetX, (int)targetY, (int)targetZ)).linkZ : ((TileMirrorEssentia)world.func_147438_o((int)targetX, (int)targetY, (int)targetZ)).linkZ) % 6));
                            float f = fd.ordinal() == 2 ? 180.0f : (fd.ordinal() == 4 ? 90.0f : (rot = fd.ordinal() == 5 ? 270.0f : 0.0f));
                            if (player.field_71093_bK != dim) {
                                player.func_71027_c(dim);
                            }
                            player.func_70012_b((double)tx + 0.5 + (double)fd.offsetX * 0.5, (double)(ty + fd.offsetY), (double)tz + 0.5 + (double)fd.offsetZ * 0.5, rot, player.field_70125_A);
                            dmg = true;
                        }
                    }
                } else {
                    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 4.0), (double)(player.field_70163_u - 2.0), (double)(player.field_70161_v - 4.0), (double)(player.field_70165_t + 4.0), (double)(player.field_70163_u + 2.0), (double)(player.field_70161_v + 4.0));
                    for (EntityLivingBase entT : world.func_72872_a(EntityLivingBase.class, aabb)) {
                        if (entT == null || entT.equals((Object)player)) continue;
                        entT.func_70024_g((entT.field_70165_t - player.field_70165_t) * 0.4, 0.4, (entT.field_70161_v - player.field_70161_v) * 0.4);
                        entT.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 15, 0));
                    }
                    dmg = true;
                }
            }
            return dmg;
        }
        return false;
    }

    public boolean showDurabilityBar(ItemStack stack) {
        return this.getDamage(stack) > 0;
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        return (float)this.getDamage(stack) / (float)this.getMaxDamage(stack);
    }

    public int getMaxDamage(ItemStack stack) {
        return 32;
    }

    @Override
    public int getConsumedCharge(String cut, Aspect aspect, EntityPlayer player) {
        if (cut == GemCut.OVAL.toString()) {
            return aspect == Aspect.FIRE || aspect == Aspect.AIR || aspect == Aspect.EARTH ? 1 : (aspect == Aspect.WATER || aspect == Aspect.ORDER ? 2 : (aspect == Aspect.ENTROPY ? (Config.allowMirrors ? 16 : 2) : 0));
        }
        return 32;
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.func_77942_o() && stack.func_77978_p().func_74771_c("GemCut") > 1) {
            stack.func_77978_p().func_74774_a("GemCut", (byte)1);
        }
        int brittle = EnchantmentHelper.func_77506_a((int)WGContent.enc_gemstoneBrittle.field_77352_x, (ItemStack)stack);
        if (entity instanceof EntityPlayer && (world.field_73012_v.nextInt(1000) < brittle || ItemInfusedGem.getCut(stack) == GemCut.POINT && stack.func_77960_j() != 0)) {
            if (!world.field_72995_K) {
                if (!((EntityPlayer)entity).field_71071_by.func_70441_a(new ItemStack(WGContent.ItemMaterial, 1, 13))) {
                    ((EntityPlayer)entity).func_71019_a(new ItemStack(WGContent.ItemMaterial, 1, 13), false);
                }
                ((EntityPlayer)entity).func_145747_a((IChatComponent)new ChatComponentTranslation("wg.chat.gem.shatter", new Object[0]));
            }
            --stack.field_77994_a;
            if (stack.field_77994_a <= 0) {
                stack = null;
            }
            ((EntityPlayer)entity).field_71071_by.func_70299_a(slot, stack);
            return;
        }
        if (world.field_73012_v.nextInt(20) < brittle) {
            return;
        }
        if (ItemInfusedGem.getCut(stack) == GemCut.OVAL && ItemInfusedGem.getAspect(stack) != null && entity instanceof EntityPlayer && selected && entity.field_70173_aa % 4 == 0 && this.getDamage(stack) > 0 && TileVisRelay.nearbyPlayers.containsKey(entity.func_145782_y())) {
            if (((WeakReference)TileVisRelay.nearbyPlayers.get(entity.func_145782_y())).get() != null && ((TileVisRelay)((WeakReference)TileVisRelay.nearbyPlayers.get(entity.func_145782_y())).get()).func_145835_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v) < 26.0) {
                Aspect aspect = ItemInfusedGem.getAspect(stack);
                int amt = ((TileVisRelay)((WeakReference)TileVisRelay.nearbyPlayers.get(entity.func_145782_y())).get()).consumeVis(aspect, 1);
                if (amt > 0) {
                    this.setDamage(stack, this.getDamage(stack) - amt);
                    ((TileVisRelay)((WeakReference)TileVisRelay.nearbyPlayers.get(entity.func_145782_y())).get()).triggerConsumeEffect(aspect);
                    if (world.field_72995_K) {
                        ForgeDirection d2 = ForgeDirection.getOrientation((int)((TileVisRelay)((WeakReference)TileVisRelay.nearbyPlayers.get((Object)Integer.valueOf((int)entity.func_145782_y()))).get()).orientation);
                        double x = (double)((TileVisRelay)((WeakReference)TileVisRelay.nearbyPlayers.get((Object)Integer.valueOf((int)entity.func_145782_y()))).get()).field_145851_c + 0.5 + (double)d2.offsetX * 0.05;
                        double y = (double)((TileVisRelay)((WeakReference)TileVisRelay.nearbyPlayers.get((Object)Integer.valueOf((int)entity.func_145782_y()))).get()).field_145848_d + 0.5 + (double)d2.offsetY * 0.05;
                        double z = (double)((TileVisRelay)((WeakReference)TileVisRelay.nearbyPlayers.get((Object)Integer.valueOf((int)entity.func_145782_y()))).get()).field_145849_e + 0.5 + (double)d2.offsetZ * 0.05;
                        double[] playerPos = ClientUtilities.getPlayerHandPos((EntityPlayer)entity, true);
                        powerBeams.put(entity.func_145782_y(), Thaumcraft.proxy.beamPower(world, x, y, z, playerPos[0], playerPos[1], playerPos[2], (float)(aspect.getColor() >> 16 & 0xFF) / 255.0f, (float)(aspect.getColor() >> 8 & 0xFF) / 255.0f, (float)(aspect.getColor() & 0xFF) / 255.0f, false, powerBeams.get(entity.func_145782_y())));
                    }
                }
            } else {
                powerBeams.remove(entity.func_145782_y());
                TileVisRelay.nearbyPlayers.remove(entity.func_145782_y());
            }
        }
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        list.add(ItemInfusedGem.createGem(null, GemCut.POINT, true));
        list.add(ItemInfusedGem.createGem(Aspect.ORDER, GemCut.POINT, false));
        list.add(ItemInfusedGem.createGem(Aspect.ENTROPY, GemCut.POINT, false));
        list.add(ItemInfusedGem.createGem(Aspect.FIRE, GemCut.POINT, false));
        list.add(ItemInfusedGem.createGem(Aspect.WATER, GemCut.POINT, false));
        list.add(ItemInfusedGem.createGem(Aspect.AIR, GemCut.POINT, false));
        list.add(ItemInfusedGem.createGem(Aspect.EARTH, GemCut.POINT, false));
        list.add(ItemInfusedGem.createGem(Aspect.ORDER, GemCut.OVAL, false));
        list.add(ItemInfusedGem.createGem(Aspect.ENTROPY, GemCut.OVAL, false));
        list.add(ItemInfusedGem.createGem(Aspect.FIRE, GemCut.OVAL, false));
        list.add(ItemInfusedGem.createGem(Aspect.WATER, GemCut.OVAL, false));
        list.add(ItemInfusedGem.createGem(Aspect.AIR, GemCut.OVAL, false));
        list.add(ItemInfusedGem.createGem(Aspect.EARTH, GemCut.OVAL, false));
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        if (ItemInfusedGem.getCut(stack) != null) {
            list.add(StatCollector.func_74838_a((String)("wg.desc.gemcut." + (Object)((Object)ItemInfusedGem.getCut(stack)))));
        }
    }

    public String func_77653_i(ItemStack stack) {
        return super.func_77653_i(stack) + (ItemInfusedGem.getAspect(stack) != null ? " (" + ItemInfusedGem.getAspect(stack).getName() + ")" : "");
    }

    public String func_77667_c(ItemStack stack) {
        return super.func_77667_c(stack) + (ItemInfusedGem.getAspect(stack) == null ? ".innert" : "");
    }

    public int func_82790_a(ItemStack stack, int pass) {
        Aspect a = ItemInfusedGem.getAspect(stack);
        if (a != null) {
            return a.getColor();
        }
        return 0xFFFFFF;
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.func_77650_f(stack);
    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        return this.func_77650_f(stack);
    }

    public IIcon func_77650_f(ItemStack stack) {
        if (ItemInfusedGem.getCut(stack) != null) {
            return this.icons[ItemInfusedGem.getCut(stack).ordinal()];
        }
        return this.icons[0];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = ir.func_94245_a("witchinggadgets:infusedGem_" + GemCut.values()[i].toString().toLowerCase());
        }
    }

    public int getItemEnchantability(ItemStack stack) {
        return 10;
    }

    public boolean func_77616_k(ItemStack stack) {
        return stack.field_77994_a == 1;
    }

    List<ChunkCoordinates> getOres(World world, int x, int y, int z) {
        Block search = world.func_147439_a(x, y, z);
        ArrayList<ChunkCoordinates> ores = new ArrayList<ChunkCoordinates>();
        ArrayList<ChunkCoordinates> openList = new ArrayList<ChunkCoordinates>();
        ArrayList<ChunkCoordinates> closedList = new ArrayList<ChunkCoordinates>();
        ArrayList checked = new ArrayList();
        openList.add(new ChunkCoordinates(x, y, z));
        ChunkCoordinates next = null;
        int closedListMax = 400;
        while (closedList.size() < 400 && !openList.isEmpty()) {
            next = (ChunkCoordinates)openList.get(0);
            closedList.add(next);
            if (Utilities.isOre(world, next.field_71574_a, next.field_71572_b, next.field_71573_c)) {
                ores.add(next);
            }
            for (ChunkCoordinates cc2 : this.getConnected(next.field_71574_a, next.field_71572_b, next.field_71573_c)) {
                if (checked.contains(cc2) || closedList.contains(cc2) || openList.contains(cc2) || !world.func_147439_a(cc2.field_71574_a, cc2.field_71572_b, cc2.field_71573_c).equals(search) && !Utilities.isOre(world, cc2.field_71574_a, cc2.field_71572_b, cc2.field_71573_c) || !(cc2.func_71569_e(x, y, z) < 32.0f)) continue;
                openList.add(cc2);
            }
            openList.remove(0);
        }
        return ores;
    }

    ChunkCoordinates[] getConnected(int x, int y, int z) {
        return new ChunkCoordinates[]{new ChunkCoordinates(x - 1, y, z), new ChunkCoordinates(x + 1, y, z), new ChunkCoordinates(x, y, z - 1), new ChunkCoordinates(x, y, z + 1), new ChunkCoordinates(x, y - 1, z), new ChunkCoordinates(x, y + 1, z)};
    }

    public static ItemStack createGem(Aspect asp, GemCut cut, boolean forceCreate) {
        if (!(forceCreate || asp != null && cut != null)) {
            return null;
        }
        ItemStack stack = new ItemStack(WGContent.ItemInfusedGem);
        stack.func_77982_d(new NBTTagCompound());
        if (cut != null) {
            stack.func_77978_p().func_74774_a("GemCut", (byte)cut.ordinal());
        }
        if (asp != null) {
            stack.func_77978_p().func_74778_a("Aspect", asp.getTag());
        }
        return stack;
    }

    public static Aspect getAspect(ItemStack stack) {
        if (!stack.func_77942_o() || !stack.func_77978_p().func_74764_b("Aspect")) {
            return null;
        }
        return Aspect.getAspect((String)stack.func_77978_p().func_74779_i("Aspect"));
    }

    public static GemCut getCut(ItemStack stack) {
        if (!stack.func_77942_o() || !stack.func_77978_p().func_74764_b("GemCut")) {
            return null;
        }
        return GemCut.getValue(stack.func_77978_p().func_74771_c("GemCut"));
    }

    @Override
    public boolean isGemEnchantable(ItemStack stack) {
        return ItemInfusedGem.getCut(stack) != null && ItemInfusedGem.getAspect(stack) != null;
    }

    public static enum GemCut {
        POINT,
        OVAL;


        public static GemCut getValue(byte b) {
            if (b >= 0 && b < GemCut.values().length) {
                return GemCut.values()[b];
            }
            return GemCut.values()[0];
        }
    }
}

