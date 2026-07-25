/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.nodes.INode
 *  thaumcraft.api.research.IScanEventHandler
 *  thaumcraft.api.research.ScanResult
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.lib.research.ScanManager
 *  thaumcraft.common.lib.utils.BlockUtils
 *  thaumcraft.common.lib.utils.EntityUtils
 */
package witchinggadgets.common.items.tools;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.EntityUtils;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.util.Utilities;

public class ItemScanCamera
extends Item {
    public ItemScanCamera() {
        this.func_77625_d(1);
        this.func_77664_n();
        this.func_77637_a(WitchingGadgets.tabWG);
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("thaumcraft:blank");
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        ItemStack photo = new ItemStack(WGContent.ItemMaterial, 1, 9);
        photo.func_77982_d(new NBTTagCompound());
        ScanResult scan = this.doScan(stack, world, player, 0);
        if (scan != null && ScanManager.validScan((AspectList)ScanManager.getScanAspects((ScanResult)scan, (World)world), (EntityPlayer)player)) {
            NBTTagCompound scanTag = Utilities.writeScanResultToNBT(scan);
            photo.func_77978_p().func_74782_a("scanResult", (NBTBase)scanTag);
            boolean takePhoto = false;
            for (int slot = 0; slot < player.field_71071_by.field_70462_a.length; ++slot) {
                ItemStack item = player.field_71071_by.field_70462_a[slot];
                if (item == null || item.func_77973_b() != ConfigItems.itemResource || item.func_77960_j() != 10) continue;
                player.field_71071_by.func_70298_a(slot, 1);
                takePhoto = true;
                break;
            }
            if (takePhoto) {
                if (player.field_71071_by.func_70441_a(photo)) {
                    player.func_71019_a(photo, false);
                }
                player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:cameradone", 0.3f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:cameraticks", 0.3f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
            }
        }
        return stack;
    }

    private ScanResult doScan(ItemStack stack, World world, EntityPlayer p, int count) {
        Entity pointedEntity = EntityUtils.getPointedEntity((World)p.field_70170_p, (Entity)p, (double)0.5, (double)10.0, (float)0.0f, (boolean)true);
        if (pointedEntity != null) {
            ScanResult sr = new ScanResult(2, 0, 0, pointedEntity, "");
            Thaumcraft.proxy.blockRunes(world, pointedEntity.field_70165_t - 0.5, pointedEntity.field_70163_u + (double)(pointedEntity.func_70047_e() / 2.0f), pointedEntity.field_70161_v - 0.5, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 0.0f, 0.3f + world.field_73012_v.nextFloat() * 0.7f, (int)(pointedEntity.field_70131_O * 15.0f), 0.03f);
            return sr;
        }
        MovingObjectPosition mop = this.func_77621_a(p.field_70170_p, p, true);
        if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            TileEntity tile = world.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            if (tile instanceof INode) {
                ScanResult sr = new ScanResult(3, 0, 0, null, "NODE" + ((INode)tile).getId());
                Thaumcraft.proxy.blockRunes(world, (double)mop.field_72311_b, (double)mop.field_72312_c + 0.25, (double)mop.field_72309_d, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 0.0f, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 15, 0.03f);
                return sr;
            }
            Block b = world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            if (b != null) {
                int bi = Block.func_149682_b((Block)p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d));
                int md = b.func_149643_k(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                ItemStack is = Utilities.getPickedBlock(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                ScanResult sr = null;
                try {
                    if (is == null) {
                        is = BlockUtils.createStackedBlock((Block)b, (int)md);
                    }
                }
                catch (Exception e) {
                    // empty catch block
                }
                sr = is == null ? new ScanResult(1, bi, md, null, "") : new ScanResult(1, Item.func_150891_b((Item)is.func_77973_b()), is.func_77960_j(), null, "");
                Thaumcraft.proxy.blockRunes(world, (double)mop.field_72311_b, (double)mop.field_72312_c + 0.25, (double)mop.field_72309_d, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 0.0f, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 15, 0.03f);
                return sr;
            }
        }
        for (IScanEventHandler seh : ThaumcraftApi.scanEventhandlers) {
            ScanResult scan = seh.scanPhenomena(stack, world, p);
            if (scan == null) continue;
            return scan;
        }
        return null;
    }
}

