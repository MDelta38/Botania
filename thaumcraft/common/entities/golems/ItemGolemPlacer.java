/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.Facing
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.golems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.EnumGolemType;
import thaumcraft.common.entities.golems.ItemGolemBell;

public class ItemGolemPlacer
extends Item {
    public IIcon[] iconGolem = new IIcon[8];
    public IIcon iconAdvanced;
    public IIcon iconCore;
    private IIcon iconBlank;

    public ItemGolemPlacer() {
        this.func_77627_a(true);
        this.func_77637_a(Thaumcraft.tabTC);
        this.func_77625_d(1);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.iconGolem[0] = ir.func_94245_a("thaumcraft:golem_straw");
        this.iconGolem[1] = ir.func_94245_a("thaumcraft:golem_wood");
        this.iconGolem[2] = ir.func_94245_a("thaumcraft:golem_tallow");
        this.iconGolem[3] = ir.func_94245_a("thaumcraft:golem_clay");
        this.iconGolem[4] = ir.func_94245_a("thaumcraft:golem_flesh");
        this.iconGolem[5] = ir.func_94245_a("thaumcraft:golem_stone");
        this.iconGolem[6] = ir.func_94245_a("thaumcraft:golem_iron");
        this.iconGolem[7] = ir.func_94245_a("thaumcraft:golem_thaumium");
        this.iconAdvanced = ir.func_94245_a("thaumcraft:golem_over_adv");
        this.iconCore = ir.func_94245_a("thaumcraft:golem_over_core");
        this.iconBlank = ir.func_94245_a("thaumcraft:blank");
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderPasses(int metadata) {
        return 3;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.iconGolem[par1];
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        if (pass == 0) {
            return super.getIcon(stack, pass);
        }
        if (pass == 1 && stack.func_77942_o() && stack.field_77990_d.func_74764_b("advanced")) {
            return this.iconAdvanced;
        }
        if (pass == 2 && stack.func_77942_o() && stack.field_77990_d.func_74764_b("core")) {
            return this.iconCore;
        }
        return this.iconBlank;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        if (stack.func_77942_o()) {
            if (stack.field_77990_d.func_74764_b("core")) {
                list.add(StatCollector.func_74838_a((String)"item.ItemGolemCore.name") + ": \u00a76" + StatCollector.func_74838_a((String)("item.ItemGolemCore." + stack.field_77990_d.func_74771_c("core") + ".name")));
            }
            if (stack.field_77990_d.func_74764_b("advanced")) {
                list.add(StatCollector.func_74838_a((String)"tc.adv"));
            }
            if (stack.field_77990_d.func_74764_b("upgrades")) {
                byte[] ba = stack.field_77990_d.func_74770_j("upgrades");
                String text = "\u00a79";
                for (byte b : ba) {
                    if (b <= -1) continue;
                    text = text + StatCollector.func_74838_a((String)("item.ItemGolemUpgrade." + b + ".name")) + " ";
                }
                list.add(text);
            }
            if (stack.field_77990_d.func_74764_b("markers")) {
                NBTTagList tl = stack.field_77990_d.func_150295_c("markers", 10);
                list.add("\u00a75" + tl.func_74745_c() + " " + StatCollector.func_74838_a((String)"tc.markedloc"));
            }
            if (stack.field_77990_d.func_74764_b("deco")) {
                String decoDesc = "\u00a72";
                String deco = stack.field_77990_d.func_74779_i("deco");
                if (deco.contains("H")) {
                    decoDesc = decoDesc + StatCollector.func_74838_a((String)"item.ItemGolemDecoration.0.name") + " ";
                }
                if (deco.contains("G")) {
                    decoDesc = decoDesc + StatCollector.func_74838_a((String)"item.ItemGolemDecoration.1.name") + " ";
                }
                if (deco.contains("B")) {
                    decoDesc = decoDesc + StatCollector.func_74838_a((String)"item.ItemGolemDecoration.2.name") + " ";
                }
                if (deco.contains("F")) {
                    decoDesc = decoDesc + StatCollector.func_74838_a((String)"item.ItemGolemDecoration.3.name") + " ";
                }
                if (deco.contains("R")) {
                    decoDesc = decoDesc + StatCollector.func_74838_a((String)"item.ItemGolemDecoration.4.name") + " ";
                }
                if (deco.contains("V")) {
                    decoDesc = decoDesc + StatCollector.func_74838_a((String)"item.ItemGolemDecoration.5.name") + " ";
                }
                if (deco.contains("P")) {
                    decoDesc = decoDesc + StatCollector.func_74838_a((String)"item.ItemGolemDecoration.6.name") + " ";
                }
                list.add(decoDesc);
            }
        }
    }

    public boolean func_77651_p() {
        return true;
    }

    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        return true;
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int par4, int par5, int par6, int side, float par8, float par9, float par10) {
        if (world.field_72995_K || player.func_70093_af()) {
            return false;
        }
        Block var11 = world.func_147439_a(par4, par5, par6);
        par4 += Facing.field_71586_b[side];
        par5 += Facing.field_71587_c[side];
        par6 += Facing.field_71585_d[side];
        double var12 = 0.0;
        if (side == 1 && var11 == Blocks.field_150422_aJ || var11 == Blocks.field_150386_bk) {
            var12 = 0.5;
        }
        if (this.spawnCreature(world, (double)par4 + 0.5, (double)par5 + var12, (double)par6 + 0.5, side, stack, player) && !player.field_71075_bZ.field_75098_d) {
            --stack.field_77994_a;
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int a = 0; a <= 7; ++a) {
            par3List.add(new ItemStack((Item)this, 1, a));
        }
    }

    public boolean spawnCreature(World par0World, double par2, double par4, double par6, int side, ItemStack stack, EntityPlayer player) {
        EntityGolemBase golem;
        boolean adv = false;
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("advanced")) {
            adv = true;
        }
        if ((golem = new EntityGolemBase(par0World, EnumGolemType.getType(stack.func_77960_j()), adv)) != null) {
            golem.func_70012_b(par2, par4, par6, par0World.field_73012_v.nextFloat() * 360.0f, 0.0f);
            golem.func_70642_aH();
            golem.func_110171_b(MathHelper.func_76128_c((double)par2), MathHelper.func_76128_c((double)par4), MathHelper.func_76128_c((double)par6), 32);
            if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("core")) {
                golem.setCore(stack.field_77990_d.func_74771_c("core"));
            }
            if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("upgrades")) {
                int ul = golem.upgrades.length;
                golem.upgrades = stack.field_77990_d.func_74770_j("upgrades");
                if (ul != golem.upgrades.length) {
                    int a;
                    byte[] tt = new byte[ul];
                    for (a = 0; a < ul; ++a) {
                        tt[a] = -1;
                    }
                    for (a = 0; a < golem.upgrades.length; ++a) {
                        if (a >= ul) continue;
                        tt[a] = golem.upgrades[a];
                    }
                    golem.upgrades = tt;
                }
            }
            String deco = "";
            if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("deco")) {
                golem.decoration = deco = stack.field_77990_d.func_74779_i("deco");
            }
            golem.setup(side);
            par0World.func_72838_d((Entity)golem);
            golem.setGolemDecoration(deco);
            golem.setOwner(player.func_70005_c_());
            golem.setMarkers(ItemGolemBell.getMarkers(stack));
            int a = 0;
            for (byte b : golem.upgrades) {
                golem.setUpgrade(a, b);
                ++a;
            }
            if (stack.func_82837_s()) {
                golem.func_94058_c(stack.func_82833_r());
                golem.func_110163_bv();
            }
            if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("Inventory")) {
                NBTTagList nbttaglist2 = stack.field_77990_d.func_150295_c("Inventory", 10);
                golem.inventory.readFromNBT(nbttaglist2);
            }
        }
        return golem != null;
    }
}

