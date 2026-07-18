/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.item.ItemMod;

public class ItemSpawnerMover
extends ItemMod {
    public static final String TAG_SPAWNER = "spawner";
    private static final String TAG_PLACE_DELAY = "placeDelay";
    IIcon iconNormal;
    IIcon iconSpawner;

    public ItemSpawnerMover() {
        this.func_77655_b("spawnerMover");
        this.func_77625_d(1);
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.iconNormal = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.iconSpawner = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.func_77650_f(stack);
    }

    public IIcon func_77650_f(ItemStack par1ItemStack) {
        return ItemSpawnerMover.hasData(par1ItemStack) ? this.iconSpawner : this.iconNormal;
    }

    public static NBTTagCompound getSpawnerTag(ItemStack stack) {
        NBTTagCompound tag = stack.func_77978_p();
        if (tag != null) {
            if (tag.func_74764_b(TAG_SPAWNER)) {
                return tag.func_74775_l(TAG_SPAWNER);
            }
            if (tag.func_74764_b("EntityId")) {
                return tag;
            }
        }
        return null;
    }

    private static String getEntityId(ItemStack stack) {
        NBTTagCompound tag = ItemSpawnerMover.getSpawnerTag(stack);
        if (tag != null) {
            return tag.func_74779_i("EntityId");
        }
        return null;
    }

    public static boolean hasData(ItemStack stack) {
        return ItemSpawnerMover.getEntityId(stack) != null;
    }

    private static int getDelay(ItemStack stack) {
        NBTTagCompound tag = stack.func_77978_p();
        if (tag != null) {
            return tag.func_74762_e(TAG_PLACE_DELAY);
        }
        return 0;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips) {
        String id = ItemSpawnerMover.getEntityId(stack);
        if (id != null) {
            infoList.add(StatCollector.func_74838_a((String)("entity." + id + ".name")));
        }
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        NBTTagCompound tag = stack.func_77978_p();
        if (tag != null && tag.func_74764_b(TAG_PLACE_DELAY) && tag.func_74762_e(TAG_PLACE_DELAY) > 0) {
            tag.func_74768_a(TAG_PLACE_DELAY, tag.func_74762_e(TAG_PLACE_DELAY) - 1);
        }
    }

    public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
        if (ItemSpawnerMover.getEntityId(itemstack) == null) {
            if (world.func_147439_a(x, y, z).equals(Blocks.field_150474_ac)) {
                TileEntity te = world.func_147438_o(x, y, z);
                NBTTagCompound tag = new NBTTagCompound();
                tag.func_74782_a(TAG_SPAWNER, (NBTBase)new NBTTagCompound());
                te.func_145841_b(tag.func_74775_l(TAG_SPAWNER));
                tag.func_74768_a(TAG_PLACE_DELAY, 20);
                itemstack.func_77982_d(tag);
                world.func_147468_f(x, y, z);
                player.func_70669_a(itemstack);
                for (int i = 0; i < 50; ++i) {
                    float red = (float)Math.random();
                    float green = (float)Math.random();
                    float blue = (float)Math.random();
                    Botania.proxy.wispFX(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, red, green, blue, (float)Math.random() * 0.1f + 0.05f, (float)(Math.random() - 0.5) * 0.15f, (float)(Math.random() - 0.5) * 0.15f, (float)(Math.random() - 0.5) * 0.15f);
                }
                return true;
            }
            return false;
        }
        return ItemSpawnerMover.getDelay(itemstack) <= 0 && this.placeBlock(itemstack, player, world, x, y, z, side, xOffset, yOffset, zOffset);
    }

    private boolean placeBlock(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
        Block block = world.func_147439_a(x, y, z);
        if (block == Blocks.field_150431_aC) {
            side = 1;
        } else if (block != Blocks.field_150395_bd && block != Blocks.field_150329_H && block != Blocks.field_150330_I && !block.isReplaceable((IBlockAccess)world, x, y, z)) {
            switch (side) {
                case 0: {
                    --y;
                    break;
                }
                case 1: {
                    ++y;
                    break;
                }
                case 2: {
                    --z;
                    break;
                }
                case 3: {
                    ++z;
                    break;
                }
                case 4: {
                    --x;
                    break;
                }
                case 5: {
                    ++x;
                }
            }
        }
        if (itemstack.field_77994_a == 0) {
            return false;
        }
        if (!player.func_82247_a(x, y, z, side, itemstack)) {
            return false;
        }
        if (y == 255 && block.func_149688_o().func_76220_a()) {
            return false;
        }
        if (world.func_147472_a(Blocks.field_150474_ac, x, y, z, false, side, (Entity)player, itemstack)) {
            int meta = block.func_149660_a(world, x, y, z, side, xOffset, yOffset, zOffset, 0);
            if (this.placeBlockAt(itemstack, player, world, x, y, z, side, xOffset, yOffset, zOffset, meta)) {
                world.func_72908_a((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), block.field_149762_H.func_150496_b(), (block.field_149762_H.func_150497_c() + 1.0f) / 2.0f, block.field_149762_H.func_150494_d() * 0.8f);
                player.func_70669_a(itemstack);
                player.func_71064_a((StatBase)ModAchievements.spawnerMoverUse, 1);
                for (int i = 0; i < 100; ++i) {
                    Botania.proxy.sparkleFX(world, (double)x + Math.random(), (double)y + Math.random(), (double)z + Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random(), 0.45f + 0.2f * (float)Math.random(), 6);
                }
                --itemstack.field_77994_a;
            }
            return true;
        }
        return false;
    }

    private boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        if (!world.func_147465_d(x, y, z, Blocks.field_150474_ac, metadata, 3)) {
            return false;
        }
        Block block = world.func_147439_a(x, y, z);
        if (block.equals(Blocks.field_150474_ac)) {
            TileEntity te = world.func_147438_o(x, y, z);
            NBTTagCompound tag = stack.func_77978_p();
            if (tag.func_74764_b(TAG_SPAWNER)) {
                tag = tag.func_74775_l(TAG_SPAWNER);
            }
            tag.func_74768_a("x", x);
            tag.func_74768_a("y", y);
            tag.func_74768_a("z", z);
            te.func_145839_a(tag);
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(world, x, y, z);
        }
        return true;
    }
}

