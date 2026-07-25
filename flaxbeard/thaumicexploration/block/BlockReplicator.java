/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.apache.commons.lang3.tuple.MutablePair
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.utils.InventoryUtils
 */
package flaxbeard.thaumicexploration.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.tile.TileEntityReplicator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.MutablePair;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.InventoryUtils;

public class BlockReplicator
extends BlockContainer {
    public IIcon[] icon = new IIcon[3];

    public BlockReplicator(int par1) {
        super(Material.field_151573_f);
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
        TileEntity tileEntity = par1World.func_147438_o(par2, par3, par4);
        if (tileEntity != null && tileEntity instanceof TileEntityReplicator && ((TileEntityReplicator)tileEntity).func_70301_a(0) != null && ((TileEntityReplicator)tileEntity).func_70301_a((int)0).field_77994_a > 0) {
            InventoryUtils.dropItems((World)par1World, (int)par2, (int)par3, (int)par4);
        }
    }

    public boolean func_149662_c() {
        return false;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, int par5) {
        boolean flag = world.func_72864_z(x, y, z);
        TileEntity tileEntity = world.func_147438_o(x, y, z);
        if (tileEntity != null && tileEntity instanceof TileEntityReplicator) {
            TileEntityReplicator ped = (TileEntityReplicator)tileEntity;
            ped.updateRedstoneState(flag);
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9) {
        if (world.field_72995_K) {
            return true;
        }
        int metadata = world.func_72805_g(x, y, z);
        TileEntity tileEntity = world.func_147438_o(x, y, z);
        if (tileEntity instanceof TileEntityReplicator) {
            TileEntityReplicator ped = (TileEntityReplicator)tileEntity;
            if (ped.crafting && (player.func_71045_bC() == null || !(player.func_71045_bC().func_77973_b() instanceof ItemWandCasting))) {
                ped.cancelCrafting();
            }
            if (!(ped.func_70301_a(0) == null || player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof ItemWandCasting)) {
                ItemStack itemstack = ped.func_70301_a(0);
                if (itemstack.field_77994_a > 0) {
                    float f = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                    float f1 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                    float f2 = world.field_73012_v.nextFloat();
                    int k1 = world.field_73012_v.nextInt(21) + 10;
                    k1 = itemstack.field_77994_a;
                    EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
                    float f3 = 0.05f;
                    entityitem.field_70159_w = (float)world.field_73012_v.nextGaussian() * f3;
                    entityitem.field_70181_x = (float)world.field_73012_v.nextGaussian() * f3 + 0.2f;
                    entityitem.field_70179_y = (float)world.field_73012_v.nextGaussian() * f3;
                    if (itemstack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                    }
                    world.func_72838_d((Entity)entityitem);
                }
                TileEntity te = world.func_147438_o(x, y, z);
                ItemStack template = ((TileEntityReplicator)te).func_70301_a(0).func_77946_l();
                if (template.field_77994_a > 0) {
                    template.field_77994_a = --template.field_77994_a;
                    ((TileEntityReplicator)te).func_70299_a(0, template);
                } else {
                    ((TileEntityReplicator)te).func_70299_a(0, null);
                }
                world.func_147471_g(x, y, z);
                world.func_72908_a((double)x, (double)y, (double)z, "random.pop", 0.2f, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 1.5f);
                return true;
            }
            if (player.func_71045_bC() != null && (ThaumicExploration.allowedItems.contains(MutablePair.of((Object)player.func_71045_bC().func_77973_b(), (Object)player.func_71045_bC().func_77960_j())) || ThaumicExploration.allowedItems.contains(MutablePair.of((Object)player.func_71045_bC().func_77973_b(), (Object)Short.MAX_VALUE)))) {
                ItemStack i = player.func_71045_bC().func_77946_l();
                i.field_77994_a = 0;
                ped.func_70299_a(0, i);
                if (player.func_71045_bC().field_77994_a == 0) {
                    player.func_70062_b(0, null);
                }
                world.func_147471_g(x, y, z);
                world.func_72908_a((double)x, (double)y, (double)z, "random.pop", 0.2f, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 1.6f);
                return true;
            }
        }
        return true;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ThaumicExploration.replicatorRenderID;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        super.func_149651_a(ir);
        this.icon[0] = ir.func_94245_a("thaumicexploration:replicatorBottom");
        this.icon[1] = ir.func_94245_a("thaumicexploration:replicator");
        this.icon[2] = ir.func_94245_a("thaumicexploration:replicatorTop");
    }

    public IIcon func_149673_e(IBlockAccess iblockaccess, int i, int j, int k, int side) {
        if (side == 0 || side == 1) {
            return this.icon[0];
        }
        return this.icon[1];
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileEntityReplicator();
    }
}

