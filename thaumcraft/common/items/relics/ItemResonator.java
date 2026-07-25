/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.items.relics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.codechicken.lib.raytracer.RayTracer;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.tiles.TileTubeBuffer;

public class ItemResonator
extends Item {
    private IIcon icon;

    public ItemResonator() {
        this.func_77625_d(1);
        this.func_77627_a(false);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icon = par1IconRegister.func_94245_a("thaumcraft:resonator");
    }

    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this));
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    public boolean func_77636_d(ItemStack par1ItemStack) {
        return par1ItemStack.func_77942_o();
    }

    public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof IEssentiaTransport) {
            if (world.field_72995_K) {
                player.func_71038_i();
                return super.onItemUseFirst(itemstack, player, world, x, y, z, side, par8, par9, par10);
            }
            IEssentiaTransport et = (IEssentiaTransport)tile;
            ForgeDirection face = ForgeDirection.getOrientation((int)side);
            MovingObjectPosition hit = RayTracer.retraceBlock(world, player, x, y, z);
            if (hit != null && hit.subHit >= 0 && hit.subHit < 6) {
                face = ForgeDirection.getOrientation((int)hit.subHit);
            }
            if (!(tile instanceof TileTubeBuffer) && et.getEssentiaType(face) != null) {
                player.func_145747_a((IChatComponent)new ChatComponentTranslation("tc.resonator1", new Object[]{"" + et.getEssentiaAmount(face), et.getEssentiaType(face).getName()}));
            } else if (tile instanceof TileTubeBuffer && ((IAspectContainer)tile).getAspects().size() > 0) {
                for (Aspect aspect : ((IAspectContainer)tile).getAspects().getAspectsSorted()) {
                    player.func_145747_a((IChatComponent)new ChatComponentTranslation("tc.resonator1", new Object[]{"" + ((IAspectContainer)tile).getAspects().getAmount(aspect), aspect.getName()}));
                }
            }
            String s = StatCollector.func_74838_a((String)"tc.resonator3");
            if (et.getSuctionType(face) != null) {
                s = et.getSuctionType(face).getName();
            }
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("tc.resonator2", new Object[]{"" + et.getSuctionAmount(face), s}));
            world.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:alembicknock", 0.5f, 1.9f + world.field_73012_v.nextFloat() * 0.1f);
            return true;
        }
        return false;
    }
}

