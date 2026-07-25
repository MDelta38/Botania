/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.relics;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketScannedToServer;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.EntityUtils;

public class ItemThaumometer
extends Item {
    public IIcon icon;
    ScanResult startScan = null;

    public ItemThaumometer() {
        this.func_77625_d(1);
        this.setNoRepair();
        this.func_77637_a(Thaumcraft.tabTC);
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:blank");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public int func_77626_a(ItemStack itemstack) {
        return 25;
    }

    public EnumAction func_77661_b(ItemStack itemstack) {
        return EnumAction.none;
    }

    private ScanResult doScan(ItemStack stack, World world, EntityPlayer p, int count) {
        Entity pointedEntity = EntityUtils.getPointedEntity(p.field_70170_p, (Entity)p, 0.5, 10.0, 0.0f, true);
        if (pointedEntity != null) {
            ScanResult sr = new ScanResult(2, 0, 0, pointedEntity, "");
            if (ScanManager.isValidScanTarget(p, sr, "@")) {
                Thaumcraft.proxy.blockRunes(world, pointedEntity.field_70165_t - 0.5, pointedEntity.field_70163_u + (double)(pointedEntity.func_70047_e() / 2.0f), pointedEntity.field_70161_v - 0.5, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 0.0f, 0.3f + world.field_73012_v.nextFloat() * 0.7f, (int)(pointedEntity.field_70131_O * 15.0f), 0.03f);
                return sr;
            }
            return null;
        }
        MovingObjectPosition mop = this.func_77621_a(p.field_70170_p, p, true);
        if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            TileEntity tile = world.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            if (tile instanceof INode) {
                ScanResult sr = new ScanResult(3, 0, 0, null, "NODE" + ((INode)tile).getId());
                if (ScanManager.isValidScanTarget(p, sr, "@")) {
                    Thaumcraft.proxy.blockRunes(world, mop.field_72311_b, (double)mop.field_72312_c + 0.25, mop.field_72309_d, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 0.0f, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 15, 0.03f);
                    return sr;
                }
                return null;
            }
            Block bi = world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            if (bi != Blocks.field_150350_a) {
                int md = bi.func_149643_k(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                ItemStack is = bi.getPickBlock(mop, p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                ScanResult sr = null;
                try {
                    if (is == null) {
                        is = BlockUtils.createStackedBlock(bi, md);
                    }
                }
                catch (Exception e) {
                    // empty catch block
                }
                try {
                    sr = is == null ? new ScanResult(1, Block.func_149682_b((Block)bi), md, null, "") : new ScanResult(1, Item.func_150891_b((Item)is.func_77973_b()), is.func_77960_j(), null, "");
                }
                catch (Exception e) {
                    // empty catch block
                }
                if (ScanManager.isValidScanTarget(p, sr, "@")) {
                    Thaumcraft.proxy.blockRunes(world, mop.field_72311_b, (double)mop.field_72312_c + 0.25, mop.field_72309_d, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 0.0f, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 15, 0.03f);
                    return sr;
                }
                return null;
            }
        }
        for (IScanEventHandler seh : ThaumcraftApi.scanEventhandlers) {
            ScanResult scan = seh.scanPhenomena(stack, world, p);
            if (scan == null) continue;
            return scan;
        }
        return null;
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer p) {
        ScanResult scan;
        if (world.field_72995_K && (scan = this.doScan(stack, world, p, 0)) != null) {
            this.startScan = scan;
        }
        p.func_71008_a(stack, this.func_77626_a(stack));
        return stack;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer p, int count) {
        if (p.field_70170_p.field_72995_K && p.func_70005_c_() == Minecraft.func_71410_x().field_71439_g.func_70005_c_()) {
            ScanResult scan = this.doScan(stack, p.field_70170_p, p, count);
            if (scan != null && scan.equals(this.startScan)) {
                if (count <= 5) {
                    this.startScan = null;
                    p.func_71034_by();
                    if (ScanManager.completeScan(p, scan, "@")) {
                        PacketHandler.INSTANCE.sendToServer((IMessage)new PacketScannedToServer(scan, p, "@"));
                    }
                }
                if (count % 2 == 0) {
                    p.field_70170_p.func_72980_b(p.field_70165_t, p.field_70163_u, p.field_70161_v, "thaumcraft:cameraticks", 0.2f, 0.45f + p.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
                }
            } else {
                this.startScan = null;
            }
        }
    }

    public void func_77615_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
        super.func_77615_a(par1ItemStack, par2World, par3EntityPlayer, par4);
        this.startScan = null;
    }
}

