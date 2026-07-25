/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagByte
 *  net.minecraft.nbt.NBTTagByteArray
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagIntArray
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.client.lib.PlayerNotifications
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.entities.golems.EntityGolemBase
 *  thaumcraft.common.entities.golems.EntityTravelingTrunk
 *  thaumcraft.common.entities.golems.Marker
 */
package thaumcraft.common.entities.golems;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.client.lib.PlayerNotifications;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;
import thaumcraft.common.entities.golems.Marker;

public class ItemGolemBell
extends Item {
    public IIcon icon;

    public ItemGolemBell() {
        this.func_77627_a(false);
        this.func_77637_a(Thaumcraft.tabTC);
        this.func_77625_d(1);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:ironbell");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public boolean func_77651_p() {
        return true;
    }

    public static int getGolemId(ItemStack stack) {
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("golemid")) {
            return stack.field_77990_d.func_74762_e("golemid");
        }
        return -1;
    }

    public static int getGolemHomeFace(ItemStack stack) {
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("golemhomeface")) {
            return stack.field_77990_d.func_74762_e("golemhomeface");
        }
        return -1;
    }

    public static ChunkCoordinates getGolemHomeCoords(ItemStack stack) {
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("golemhomex")) {
            return new ChunkCoordinates(stack.field_77990_d.func_74762_e("golemhomex"), stack.field_77990_d.func_74762_e("golemhomey"), stack.field_77990_d.func_74762_e("golemhomez"));
        }
        return null;
    }

    public static ArrayList<Marker> getMarkers(ItemStack stack) {
        ArrayList<Marker> markers = new ArrayList<Marker>();
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("markers")) {
            NBTTagList tl = stack.field_77990_d.func_150295_c("markers", 10);
            for (int i = 0; i < tl.func_74745_c(); ++i) {
                NBTTagCompound nbttagcompound1 = tl.func_150305_b(i);
                int x = nbttagcompound1.func_74762_e("x");
                int y = nbttagcompound1.func_74762_e("y");
                int z = nbttagcompound1.func_74762_e("z");
                int dim = nbttagcompound1.func_74762_e("dim");
                byte s = nbttagcompound1.func_74771_c("side");
                byte c = nbttagcompound1.func_74771_c("color");
                markers.add(new Marker(x, y, z, (int)((byte)dim), s, c));
            }
        }
        return markers;
    }

    public static void resetMarkers(ItemStack stack, World world, EntityPlayer player) {
        Entity golem = null;
        int gid = ItemGolemBell.getGolemId(stack);
        if (gid > -1 && (golem = world.func_73045_a(gid)) != null && golem instanceof EntityGolemBase) {
            stack.func_77983_a("markers", (NBTBase)new NBTTagList());
            ((EntityGolemBase)golem).setMarkers(new ArrayList());
            world.func_72956_a((Entity)player, "random.orb", 0.7f, 1.0f + world.field_73012_v.nextFloat() * 0.1f);
        }
    }

    public static void changeMarkers(ItemStack stack, EntityPlayer player, World world, int par4, int par5, int par6, int side) {
        Entity golem = null;
        ArrayList<Marker> markers = ItemGolemBell.getMarkers(stack);
        boolean markMultipleColors = false;
        int gid = ItemGolemBell.getGolemId(stack);
        if (gid > -1 && (golem = world.func_73045_a(gid)) != null && golem instanceof EntityGolemBase && ((EntityGolemBase)golem).getUpgradeAmount(4) > 0) {
            markMultipleColors = true;
        }
        int count = markers.size();
        int index = -1;
        int color = 0;
        if (!markMultipleColors) {
            index = markers.indexOf(new Marker(par4, par5, par6, world.field_73011_w.field_76574_g, (byte)side, -1));
        } else {
            int a = -1;
            while (a < 16) {
                index = markers.indexOf(new Marker(par4, par5, par6, world.field_73011_w.field_76574_g, (byte)side, (byte)a));
                color = a++;
                if (index != -1) break;
            }
        }
        if (index >= 0) {
            markers.remove(index);
            if (markMultipleColors && !player.func_70093_af() && ++color <= 15) {
                markers.add(new Marker(par4, par5, par6, world.field_73011_w.field_76574_g, (byte)side, (byte)color));
                ++count;
                if (world.field_72995_K) {
                    String text = StatCollector.func_74838_a((String)"tc.markerchange");
                    text = color > -1 ? text.replaceAll("%n", UtilsFX.colorNames[color]) : StatCollector.func_74838_a((String)"tc.markerchangeany");
                    PlayerNotifications.addNotification((String)text);
                }
            }
        } else {
            markers.add(new Marker(par4, par5, par6, world.field_73011_w.field_76574_g, (byte)side, -1));
        }
        if (count != markers.size()) {
            NBTTagList tl = new NBTTagList();
            for (Marker l : markers) {
                NBTTagCompound nbtc = new NBTTagCompound();
                nbtc.func_74768_a("x", l.x);
                nbtc.func_74768_a("y", l.y);
                nbtc.func_74768_a("z", l.z);
                nbtc.func_74768_a("dim", l.dim);
                nbtc.func_74774_a("side", l.side);
                nbtc.func_74774_a("color", l.color);
                tl.func_74742_a((NBTBase)nbtc);
            }
            stack.func_77983_a("markers", (NBTBase)tl);
            if (gid > -1) {
                if (golem == null || !(golem instanceof EntityGolemBase)) {
                    stack.func_77978_p().func_82580_o("golemid");
                    stack.func_77978_p().func_82580_o("markers");
                    stack.func_77978_p().func_82580_o("golemhomex");
                    stack.func_77978_p().func_82580_o("golemhomey");
                    stack.func_77978_p().func_82580_o("golemhomez");
                    stack.func_77978_p().func_82580_o("golemhomeface");
                } else {
                    ((EntityGolemBase)golem).setMarkers(markers);
                }
            }
        }
        world.func_72908_a((double)par4, (double)par5, (double)par6, "random.orb", 0.7f, 1.0f + world.field_73012_v.nextFloat() * 0.1f);
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int par4, int par5, int par6, int side, float par8, float par9, float par10) {
        MovingObjectPosition movingobjectposition = this.func_77621_a(world, player, true);
        if (movingobjectposition == null) {
            return true;
        }
        if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            int i = movingobjectposition.field_72311_b;
            int j = movingobjectposition.field_72312_c;
            int k = movingobjectposition.field_72309_d;
            ItemGolemBell.changeMarkers(stack, player, world, i, j, k, movingobjectposition.field_72310_e);
        }
        return !world.field_72995_K;
    }

    public boolean func_111207_a(ItemStack stack, EntityPlayer player, EntityLivingBase target) {
        if (target instanceof EntityGolemBase) {
            if (stack.func_77942_o()) {
                stack.func_77978_p().func_82580_o("golemid");
                stack.func_77978_p().func_82580_o("markers");
                stack.func_77978_p().func_82580_o("golemhomex");
                stack.func_77978_p().func_82580_o("golemhomey");
                stack.func_77978_p().func_82580_o("golemhomez");
                stack.func_77978_p().func_82580_o("golemhomeface");
            }
            if (target.field_70170_p.field_72995_K) {
                if (player != null) {
                    player.func_71038_i();
                }
            } else {
                ArrayList markers = ((EntityGolemBase)target).getMarkers();
                NBTTagList tl = new NBTTagList();
                for (Marker l : markers) {
                    NBTTagCompound nbtc = new NBTTagCompound();
                    nbtc.func_74768_a("x", l.x);
                    nbtc.func_74768_a("y", l.y);
                    nbtc.func_74768_a("z", l.z);
                    nbtc.func_74768_a("dim", l.dim);
                    nbtc.func_74774_a("side", l.side);
                    nbtc.func_74774_a("color", l.color);
                    tl.func_74742_a((NBTBase)nbtc);
                }
                stack.func_77983_a("markers", (NBTBase)tl);
                stack.func_77978_p().func_74768_a("golemid", target.func_145782_y());
                stack.func_77978_p().func_74768_a("golemhomex", ((EntityGolemBase)target).func_110172_bL().field_71574_a);
                stack.func_77978_p().func_74768_a("golemhomey", ((EntityGolemBase)target).func_110172_bL().field_71572_b);
                stack.func_77978_p().func_74768_a("golemhomez", ((EntityGolemBase)target).func_110172_bL().field_71573_c);
                stack.func_77978_p().func_74768_a("golemhomeface", ((EntityGolemBase)target).homeFacing);
                target.field_70170_p.func_72956_a((Entity)target, "random.orb", 0.7f, 1.0f + target.field_70170_p.field_73012_v.nextFloat() * 0.1f);
                if (player != null && player.field_71075_bZ.field_75098_d) {
                    player.func_70062_b(0, stack.func_77946_l());
                }
            }
            return true;
        }
        return false;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity instanceof EntityTravelingTrunk && !entity.field_70128_L) {
            byte upgrade = (byte)((EntityTravelingTrunk)entity).getUpgrade();
            if (upgrade == 3 && !((EntityTravelingTrunk)entity).func_152113_b().equals(player.func_70005_c_())) {
                return false;
            }
            if (entity.field_70170_p.field_72995_K && entity instanceof EntityLiving) {
                ((EntityLiving)entity).func_70656_aK();
                return false;
            }
            ItemStack dropped = new ItemStack(ConfigItems.itemTrunkSpawner);
            if (player.func_70093_af()) {
                if (upgrade > -1 && entity.field_70170_p.field_73012_v.nextBoolean()) {
                    ((EntityTravelingTrunk)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemUpgrade, 1, (int)upgrade), 0.5f);
                }
            } else {
                if (((EntityTravelingTrunk)entity).func_94056_bM()) {
                    dropped.func_151001_c(((EntityTravelingTrunk)entity).func_94057_bL());
                }
                dropped.func_77983_a("upgrade", (NBTBase)new NBTTagByte(upgrade));
                if (upgrade == 4) {
                    dropped.func_77983_a("inventory", (NBTBase)((EntityTravelingTrunk)entity).inventory.writeToNBT(new NBTTagList()));
                }
            }
            ((EntityTravelingTrunk)entity).func_70099_a(dropped, 0.5f);
            if (upgrade != 4 || player.func_70093_af()) {
                ((EntityTravelingTrunk)entity).inventory.dropAllItems();
            }
            entity.field_70170_p.func_72956_a(entity, "thaumcraft:zap", 0.5f, 1.0f);
            entity.func_70106_y();
            return true;
        }
        if (entity instanceof EntityGolemBase && !(entity instanceof EntityGolemTH) && !entity.field_70128_L) {
            if (entity.field_70170_p.field_72995_K && entity instanceof EntityLiving) {
                ((EntityLiving)entity).func_70656_aK();
                return false;
            }
            int type = ((EntityGolemBase)entity).golemType.ordinal();
            String deco = ((EntityGolemBase)entity).decoration;
            byte core = ((EntityGolemBase)entity).getCore();
            byte[] upgrades = ((EntityGolemBase)entity).upgrades;
            boolean advanced = ((EntityGolemBase)entity).advanced;
            ItemStack dropped = new ItemStack(ConfigItems.itemGolemPlacer, 1, type);
            if (advanced) {
                dropped.func_77983_a("advanced", (NBTBase)new NBTTagByte(1));
            }
            if (player.func_70093_af()) {
                if (core > -1) {
                    ((EntityGolemBase)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemCore, 1, (int)core), 0.5f);
                }
                for (byte b : upgrades) {
                    if (b <= -1 || !entity.field_70170_p.field_73012_v.nextBoolean()) continue;
                    ((EntityGolemBase)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemUpgrade, 1, (int)b), 0.5f);
                }
            } else {
                if (((EntityGolemBase)entity).func_94056_bM()) {
                    dropped.func_151001_c(((EntityGolemBase)entity).func_94057_bL());
                }
                if (deco.length() > 0) {
                    dropped.func_77983_a("deco", (NBTBase)new NBTTagString(deco));
                }
                if (core > -1) {
                    dropped.func_77983_a("core", (NBTBase)new NBTTagByte(core));
                }
                dropped.func_77983_a("upgrades", (NBTBase)new NBTTagByteArray(upgrades));
                ArrayList markers = ((EntityGolemBase)entity).getMarkers();
                NBTTagList tl = new NBTTagList();
                for (Marker l : markers) {
                    NBTTagCompound nbtc = new NBTTagCompound();
                    nbtc.func_74768_a("x", l.x);
                    nbtc.func_74768_a("y", l.y);
                    nbtc.func_74768_a("z", l.z);
                    nbtc.func_74768_a("dim", l.dim);
                    nbtc.func_74774_a("side", l.side);
                    nbtc.func_74774_a("color", l.color);
                    tl.func_74742_a((NBTBase)nbtc);
                }
                dropped.func_77983_a("markers", (NBTBase)tl);
                dropped.func_77983_a("Inventory", (NBTBase)((EntityGolemBase)entity).inventory.writeToNBT(new NBTTagList()));
            }
            ((EntityGolemBase)entity).func_70099_a(dropped, 0.5f);
            ((EntityGolemBase)entity).dropStuff();
            entity.field_70170_p.func_72956_a(entity, "thaumcraft:zap", 0.5f, 1.0f);
            entity.func_70106_y();
            return true;
        }
        if (entity instanceof EntityGolemTH && !entity.field_70128_L) {
            if (entity.field_70170_p.field_72995_K && entity instanceof EntityLiving) {
                ((EntityLiving)entity).func_70656_aK();
                return false;
            }
            EntityGolemTH golem = (EntityGolemTH)entity;
            if (golem.getCore() == -1) {
                golem.ticksAlive = 0;
                return true;
            }
            int type = golem.type.ordinal();
            String deco = golem.decoration;
            byte core = golem.getCore();
            byte[] upgrades = golem.upgrades;
            int[] nArray = new int[2];
            Block cfr_ignored_0 = golem.blocky;
            nArray[0] = Block.func_149682_b((Block)golem.blocky);
            nArray[1] = golem.md;
            int[] blockData = nArray;
            boolean advanced = golem.advanced;
            ItemStack dropped = new ItemStack(ThaumicHorizons.itemGolemPlacer, 1, type);
            if (advanced) {
                dropped.func_77983_a("advanced", (NBTBase)new NBTTagByte(1));
            }
            if (player.func_70093_af()) {
                if (core > -1) {
                    ((EntityGolemBase)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemCore, 1, (int)core), 0.5f);
                }
                for (byte b : upgrades) {
                    if (b <= -1 || !entity.field_70170_p.field_73012_v.nextBoolean()) continue;
                    ((EntityGolemBase)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemUpgrade, 1, (int)b), 0.5f);
                }
                golem.die();
                return true;
            }
            if (((EntityGolemBase)entity).func_94056_bM()) {
                dropped.func_151001_c(((EntityGolemBase)entity).func_94057_bL());
            }
            if (deco.length() > 0) {
                dropped.func_77983_a("deco", (NBTBase)new NBTTagString(deco));
            }
            if (core > -1) {
                dropped.func_77983_a("core", (NBTBase)new NBTTagByte(core));
            }
            dropped.func_77983_a("upgrades", (NBTBase)new NBTTagByteArray(upgrades));
            dropped.func_77983_a("block", (NBTBase)new NBTTagIntArray(blockData));
            dropped.field_77990_d.func_74757_a("berserk", golem.berserk);
            dropped.field_77990_d.func_74757_a("explosive", golem.kaboom);
            ArrayList markers = ((EntityGolemBase)entity).getMarkers();
            NBTTagList tl = new NBTTagList();
            for (Marker l : markers) {
                NBTTagCompound nbtc = new NBTTagCompound();
                nbtc.func_74768_a("x", l.x);
                nbtc.func_74768_a("y", l.y);
                nbtc.func_74768_a("z", l.z);
                nbtc.func_74768_a("dim", l.dim);
                nbtc.func_74774_a("side", l.side);
                nbtc.func_74774_a("color", l.color);
                tl.func_74742_a((NBTBase)nbtc);
            }
            dropped.func_77983_a("markers", (NBTBase)tl);
            dropped.func_77983_a("Inventory", (NBTBase)((EntityGolemBase)entity).inventory.writeToNBT(new NBTTagList()));
            ((EntityGolemBase)entity).func_70099_a(dropped, 0.5f);
            ((EntityGolemBase)entity).dropStuff();
            entity.field_70170_p.func_72956_a(entity, "thaumcraft:zap", 0.5f, 1.0f);
            entity.func_70106_y();
            return true;
        }
        return false;
    }
}

