/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package thaumcraft.common.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileManaPod;

public class ItemManaBean
extends ItemFood
implements IEssentiaContainerItem {
    public final int field_77855_a;
    public IIcon icon;
    Random rand = new Random();
    static Aspect[] displayAspects = Aspect.aspects.values().toArray(new Aspect[0]);

    public ItemManaBean() {
        super(1, 0.5f, true);
        this.field_77855_a = 10;
        this.func_77625_d(64);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
        this.func_77848_i();
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return this.field_77855_a;
    }

    protected void func_77849_c(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K) {
            AspectList al;
            Potion p = Potion.field_76425_a[world.field_73012_v.nextInt(Potion.field_76425_a.length)];
            if (p != null) {
                if (p.func_76403_b()) {
                    p.func_76402_a((EntityLivingBase)player, (EntityLivingBase)player, 2, 3.0);
                } else {
                    player.func_70690_d(new PotionEffect(p.field_76415_H, 160 + world.field_73012_v.nextInt(80), 0));
                }
            }
            if (world.field_73012_v.nextFloat() < 0.25f && (al = ((ItemManaBean)stack.func_77973_b()).getAspects(stack)) != null && al.size() > 0) {
                Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), al.getAspects()[0], (short)1);
                ResearchManager.scheduleSave(player);
                PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(al.getAspects()[0].getTag(), (short)1, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), al.getAspects()[0])), (EntityPlayerMP)player);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:mana_bean");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        AspectList aspects = this.getAspects(stack);
        if (aspects != null && aspects.size() > 0) {
            for (Aspect tag : aspects.getAspectsSorted()) {
                if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
                    list.add(tag.getName() + " x" + aspects.getAmount(tag));
                    continue;
                }
                list.add(StatCollector.func_74838_a((String)"tc.aspect.unknown"));
            }
        }
        super.func_77624_a(stack, player, list, par4);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int par2) {
        if (this.getAspects(stack) != null) {
            return this.getAspects(stack).getAspects()[0].getColor();
        }
        int idx = (int)(System.currentTimeMillis() / 500L % (long)displayAspects.length);
        return displayAspects[idx].getColor();
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (!par2World.field_72995_K && !par1ItemStack.func_77942_o()) {
            this.setAspects(par1ItemStack, new AspectList().add(displayAspects[this.rand.nextInt(displayAspects.length)], 1));
        }
        super.func_77663_a(par1ItemStack, par2World, par3Entity, par4, par5);
    }

    public void func_77622_d(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par1ItemStack.func_77942_o()) {
            this.setAspects(par1ItemStack, new AspectList().add(displayAspects[this.rand.nextInt(displayAspects.length)], 1));
        }
    }

    @Override
    public AspectList getAspects(ItemStack itemstack) {
        if (itemstack.func_77942_o()) {
            AspectList aspects = new AspectList();
            aspects.readFromNBT(itemstack.func_77978_p());
            return aspects.size() > 0 ? aspects : null;
        }
        return null;
    }

    @Override
    public void setAspects(ItemStack itemstack, AspectList aspects) {
        if (!itemstack.func_77942_o()) {
            itemstack.func_77982_d(new NBTTagCompound());
        }
        aspects.writeToNBT(itemstack.func_77978_p());
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (!par2EntityPlayer.func_82247_a(par4, par5, par6, par7, par1ItemStack) || par7 != 0) {
            return false;
        }
        BiomeGenBase biome = par3World.func_72807_a(par4, par6);
        boolean magicBiome = false;
        if (biome != null) {
            magicBiome = BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.MAGICAL);
        }
        if (!magicBiome) {
            return false;
        }
        Block i1 = par3World.func_147439_a(par4, par5, par6);
        if (i1 == Blocks.field_150364_r || i1 == Blocks.field_150363_s || i1 == ConfigBlocks.blockMagicalLog) {
            if (par3World.func_147437_c(par4, --par5, par6)) {
                int k1 = ConfigBlocks.blockManaPod.func_149660_a(par3World, par4, par5, par6, par7, par8, par9, par10, 0);
                par3World.func_147465_d(par4, par5, par6, ConfigBlocks.blockManaPod, k1, 2);
                TileEntity tile = par3World.func_147438_o(par4, par5, par6);
                if (tile != null && tile instanceof TileManaPod && this.getAspects(par1ItemStack) != null && this.getAspects(par1ItemStack).size() > 0) {
                    ((TileManaPod)tile).aspect = this.getAspects(par1ItemStack).getAspects()[0];
                }
                if (!par2EntityPlayer.field_71075_bZ.field_75098_d) {
                    --par1ItemStack.field_77994_a;
                }
            }
            return true;
        }
        return false;
    }
}

