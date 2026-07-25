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
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.projectile.EntityAlumentum;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.InventoryUtils;

public class ItemResource
extends Item
implements IEssentiaContainerItem {
    public IIcon[] icon = new IIcon[19];
    public IIcon iconOverlay;

    public ItemResource() {
        this.func_77625_d(64);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:alumentum");
        this.icon[1] = ir.func_94245_a("thaumcraft:nitor");
        this.icon[2] = ir.func_94245_a("thaumcraft:thaumiumingot");
        this.icon[3] = ir.func_94245_a("thaumcraft:quicksilver");
        this.icon[4] = ir.func_94245_a("thaumcraft:tallow");
        this.icon[5] = ir.func_94245_a("thaumcraft:brain");
        this.icon[6] = ir.func_94245_a("thaumcraft:amber");
        this.icon[7] = ir.func_94245_a("thaumcraft:cloth");
        this.icon[8] = ir.func_94245_a("thaumcraft:filter");
        this.icon[9] = ir.func_94245_a("thaumcraft:knowledgefragment");
        this.icon[10] = ir.func_94245_a("thaumcraft:mirrorglass");
        this.icon[11] = ir.func_94245_a("thaumcraft:taint_slime");
        this.icon[12] = ir.func_94245_a("thaumcraft:taint_tendril");
        this.icon[13] = ir.func_94245_a("thaumcraft:label");
        this.iconOverlay = ir.func_94245_a("thaumcraft:label_over");
        this.icon[14] = ir.func_94245_a("thaumcraft:dust");
        this.icon[15] = ir.func_94245_a("thaumcraft:charm");
        this.icon[16] = ir.func_94245_a("thaumcraft:voidingot");
        this.icon[17] = ir.func_94245_a("thaumcraft:voidseed");
        this.icon[18] = ir.func_94245_a("thaumcraft:coin");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon[par1];
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderPasses(int metadata) {
        return metadata == 13 ? 2 : 1;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int pass) {
        return pass == 0 || this.getAspects(stack) == null ? this.func_77617_a(stack.func_77960_j()) : this.iconOverlay;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int par2) {
        if (par2 == 1 && stack.func_77960_j() == 13 && this.getAspects(stack) != null) {
            return this.getAspects(stack).getAspects()[0].getColor();
        }
        return 0xFFFFFF;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int a = 0; a <= 18; ++a) {
            if (a == 5) continue;
            par3List.add(new ItemStack((Item)this, 1, a));
        }
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        super.func_77663_a(stack, world, entity, par4, par5);
        if (!(entity.field_70170_p.field_72995_K || stack.func_77960_j() != 11 && stack.func_77960_j() != 12 || !(entity instanceof EntityLivingBase) || ((EntityLivingBase)entity).func_70662_br() || ((EntityLivingBase)entity).func_82165_m(Config.potionTaintPoisonID) || world.field_73012_v.nextInt(4321) > stack.field_77994_a)) {
            ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 120, 0, false));
            if (entity instanceof EntityPlayer) {
                String s = StatCollector.func_74838_a((String)"tc.taint_item_poison").replace("%s", "\u00a75\u00a7o" + stack.func_82833_r() + "\u00a7r");
                ((EntityPlayer)entity).func_145747_a((IChatComponent)new ChatComponentTranslation(s, new Object[0]));
                InventoryUtils.consumeInventoryItem((EntityPlayer)entity, stack.func_77973_b(), stack.func_77960_j());
            }
        } else if (!entity.field_70170_p.field_72995_K && stack.func_77960_j() == 15) {
            int r = world.field_73012_v.nextInt(20000);
            if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("blurb")) {
                stack.field_77990_d.func_82580_o("blurb");
            }
            if (r < 20) {
                Aspect aspect = null;
                switch (world.field_73012_v.nextInt(6)) {
                    case 0: {
                        aspect = Aspect.AIR;
                        break;
                    }
                    case 1: {
                        aspect = Aspect.EARTH;
                        break;
                    }
                    case 2: {
                        aspect = Aspect.FIRE;
                        break;
                    }
                    case 3: {
                        aspect = Aspect.WATER;
                        break;
                    }
                    case 4: {
                        aspect = Aspect.ORDER;
                        break;
                    }
                    case 5: {
                        aspect = Aspect.ENTROPY;
                    }
                }
                if (aspect != null) {
                    EntityAspectOrb orb = new EntityAspectOrb(world, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, aspect, 1);
                    world.func_72838_d((Entity)orb);
                }
            } else if (r == 42 && entity instanceof EntityPlayer && !ResearchManager.isResearchComplete(((EntityPlayer)entity).func_70005_c_(), "FOCUSPRIMAL") && !ResearchManager.isResearchComplete(((EntityPlayer)entity).func_70005_c_(), "@FOCUSPRIMAL")) {
                ((EntityPlayer)entity).func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"tc.primalcharm.trigger"), new Object[0]));
                PacketHandler.INSTANCE.sendTo((IMessage)new PacketResearchComplete("@FOCUSPRIMAL"), (EntityPlayerMP)entity);
                Thaumcraft.proxy.getResearchManager().completeResearch((EntityPlayer)entity, "@FOCUSPRIMAL");
            }
        }
    }

    public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (itemstack.func_77960_j() == 1) {
            Block var11 = world.func_147439_a(x, y, z);
            if (var11 == Blocks.field_150431_aC && (world.func_72805_g(x, y, z) & 7) < 1) {
                par7 = 1;
            } else if (var11 != Blocks.field_150395_bd && var11 != Blocks.field_150329_H && var11 != Blocks.field_150330_I && !var11.isReplaceable((IBlockAccess)world, x, y, z)) {
                if (par7 == 0) {
                    --y;
                }
                if (par7 == 1) {
                    ++y;
                }
                if (par7 == 2) {
                    --z;
                }
                if (par7 == 3) {
                    ++z;
                }
                if (par7 == 4) {
                    --x;
                }
                if (par7 == 5) {
                    ++x;
                }
            }
            if (itemstack.field_77994_a == 0) {
                return false;
            }
            if (!player.func_82247_a(x, y, z, par7, itemstack)) {
                return false;
            }
            if (world.func_147472_a(ConfigBlocks.blockAiry, x, y, z, false, par7, (Entity)player, itemstack)) {
                if (this.placeBlockAt(itemstack, player, world, x, y, z, par7, par8, par9, par10, ConfigBlocks.blockAiry, itemstack.func_77960_j())) {
                    world.func_72908_a((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), ConfigBlocks.blockAiry.field_149762_H.func_150498_e(), (ConfigBlocks.blockAiry.field_149762_H.func_150497_c() + 1.0f) / 2.0f, ConfigBlocks.blockAiry.field_149762_H.func_150494_d() * 0.8f);
                    --itemstack.field_77994_a;
                    return true;
                }
                return false;
            }
            return false;
        }
        return super.func_77648_a(itemstack, player, world, x, y, z, par7, par8, par9, par10);
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, Block bid, int metadata) {
        if (!world.func_147465_d(x, y, z, bid, metadata, 3)) {
            return false;
        }
        if (world.func_147439_a(x, y, z) == bid) {
            bid.func_149689_a(world, x, y, z, (EntityLivingBase)player, stack);
            bid.func_149714_e(world, x, y, z, metadata);
        }
        return true;
    }

    public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player) {
        if (itemstack.func_77960_j() == 0) {
            if (!player.field_71075_bZ.field_75098_d) {
                --itemstack.field_77994_a;
            }
            world.func_72956_a((Entity)player, "random.bow", 0.3f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
            if (!world.field_72995_K) {
                world.func_72838_d((Entity)new EntityAlumentum(world, (EntityLivingBase)player));
            }
        } else if (itemstack.func_77960_j() == 9) {
            if (!player.field_71075_bZ.field_75098_d) {
                --itemstack.field_77994_a;
            }
            if (!world.field_72995_K) {
                for (Aspect a : Aspect.getPrimalAspects()) {
                    short q = (short)(world.field_73012_v.nextInt(2) + 1);
                    Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), a, q);
                    ResearchManager.scheduleSave(player);
                    PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(a.getTag(), q, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), a)), (EntityPlayerMP)player);
                }
            }
        }
        return itemstack;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        Random rand;
        int r;
        AspectList aspects = this.getAspects(stack);
        if (aspects != null && aspects.size() > 0) {
            for (Aspect tag : aspects.getAspectsSorted()) {
                if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
                    list.add(tag.getName());
                    continue;
                }
                list.add(StatCollector.func_74838_a((String)"tc.aspect.unknown"));
            }
        }
        if (stack.func_77960_j() == 15 && (r = (rand = new Random(stack.hashCode() + player.field_70173_aa / 120)).nextInt(200)) < 25) {
            list.add("\u00a76" + StatCollector.func_74838_a((String)("tc.primalcharm." + rand.nextInt(5))));
        }
        super.func_77624_a(stack, player, list, par4);
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

    public int getItemStackLimit(ItemStack stack) {
        return stack.func_77960_j() == 15 ? 1 : super.getItemStackLimit(stack);
    }
}

