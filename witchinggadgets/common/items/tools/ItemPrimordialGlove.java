/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.nodes.INode
 *  thaumcraft.api.nodes.NodeModifier
 *  thaumcraft.api.nodes.NodeType
 *  thaumcraft.common.lib.research.ResearchManager
 *  thaumcraft.common.tiles.TileNode
 */
package witchinggadgets.common.items.tools;

import com.google.common.collect.Multimap;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileNode;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.api.IInfusedGem;
import witchinggadgets.api.IPrimordialCrafting;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.gui.ContainerPrimordialGlove;
import witchinggadgets.common.gui.InventoryPrimordialGlove;
import witchinggadgets.common.items.ItemInfusedGem;

public class ItemPrimordialGlove
extends Item
implements IPrimordialCrafting {
    public ItemPrimordialGlove() {
        this.func_77625_d(1);
        this.func_77664_n();
        this.func_77637_a(WitchingGadgets.tabWG);
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("witchinggadgets:researchIcon");
    }

    public void func_77624_a(ItemStack item, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        super.func_77624_a(item, par2EntityPlayer, list, par4);
    }

    public static ItemStack[] getSetGems(ItemStack bracelet) {
        ItemStack[] gems = new ItemStack[5];
        if (!(bracelet.func_77973_b() instanceof ItemPrimordialGlove) || bracelet.func_77978_p() == null) {
            return gems;
        }
        NBTTagList list = bracelet.func_77978_p().func_150295_c("gems", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound tag = list.func_150305_b(i);
            int slot = tag.func_74771_c("Slot") & 0xFF;
            if (slot < 0 || slot >= gems.length) continue;
            gems[slot] = ItemStack.func_77949_a((NBTTagCompound)tag);
        }
        return gems;
    }

    public static ItemStack setSetGems(ItemStack bracelet, ItemStack[] gems) {
        if (bracelet == null || !(bracelet.func_77973_b() instanceof ItemPrimordialGlove) || gems == null || gems.length > 5) {
            return bracelet;
        }
        if (bracelet.func_77978_p() == null) {
            bracelet.func_77982_d(new NBTTagCompound());
        }
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < gems.length; ++i) {
            if (gems[i] == null) continue;
            NBTTagCompound tag = new NBTTagCompound();
            tag.func_74774_a("Slot", (byte)i);
            gems[i].func_77955_b(tag);
            list.func_74742_a((NBTBase)tag);
        }
        bracelet.func_77978_p().func_74782_a("gems", (NBTBase)list);
        return bracelet;
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        super.func_77663_a(stack, world, entity, par4, par5);
        if (entity.field_70173_aa % 10 == 0 && stack.func_77942_o() && stack.func_77978_p().func_74764_b("storedNode")) {
            NBTTagCompound nodeTag = stack.func_77978_p().func_74775_l("storedNode");
            AspectList al = new AspectList();
            al.readFromNBT(nodeTag);
            ItemStack[] gems = ItemPrimordialGlove.getSetGems(stack);
            if (!world.field_72995_K && ContainerPrimordialGlove.map.containsKey(entity.func_145782_y())) {
                gems = ((InventoryPrimordialGlove)ContainerPrimordialGlove.map.get((Object)Integer.valueOf((int)entity.func_145782_y())).input).stackList;
            }
            for (ItemStack g : gems) {
                if (g == null || !g.func_77951_h()) continue;
                int restored = al.getAmount(ItemInfusedGem.getAspect(g));
                int newDmg = Math.max(g.func_77960_j() - restored, 0);
                g.func_77964_b(newDmg);
            }
            ItemPrimordialGlove.setSetGems(stack, gems);
            if (!world.field_72995_K && ContainerPrimordialGlove.map.containsKey(entity.func_145782_y())) {
                ContainerPrimordialGlove c = ContainerPrimordialGlove.map.get(entity.func_145782_y());
                ((InventoryPrimordialGlove)c.input).stackList = gems;
                ContainerPrimordialGlove.map.get(entity.func_145782_y()).func_75130_a(c.input);
                c.func_75142_b();
            }
        }
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        ItemStack gem;
        int sel = stack.func_77942_o() ? stack.func_77978_p().func_74762_e("selected") : 0;
        ItemStack[] gems = ItemPrimordialGlove.getSetGems(stack);
        boolean b = false;
        if (gems != null && sel >= 0 && sel < gems.length && gems[sel] != null && (gem = gems[sel]).func_77973_b() instanceof IInfusedGem && gem.func_77960_j() + ((IInfusedGem)gem.func_77973_b()).getConsumedCharge(ItemInfusedGem.getCut(gem).toString(), ItemInfusedGem.getAspect(gem), player) <= gem.func_77958_k()) {
            int brittle = EnchantmentHelper.func_77506_a((int)WGContent.enc_gemstoneBrittle.field_77352_x, (ItemStack)gem);
            int potency = EnchantmentHelper.func_77506_a((int)WGContent.enc_gemstonePotency.field_77352_x, (ItemStack)gem);
            b = ((IInfusedGem)gem.func_77973_b()).performEffect(ItemInfusedGem.getCut(gem).toString(), ItemInfusedGem.getAspect(gem), potency, brittle, player);
            if (b && !player.field_71075_bZ.field_75098_d) {
                gem.func_77973_b().setDamage(gem, gem.func_77960_j() + ((IInfusedGem)gem.func_77973_b()).getConsumedCharge(ItemInfusedGem.getCut(gem).toString(), ItemInfusedGem.getAspect(gem), player));
                gems[sel] = gem;
                ItemPrimordialGlove.setSetGems(stack, gems);
            }
        }
        if (player.func_70093_af() && !b) {
            player.openGui((Object)WitchingGadgets.instance, 7, world, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
        }
        return super.func_77659_a(stack, world, player);
    }

    public Multimap getAttributeModifiers(ItemStack stack) {
        Multimap multimap = super.getAttributeModifiers(stack);
        multimap.put((Object)SharedMonsterAttributes.field_111264_e.func_111108_a(), (Object)new AttributeModifier(field_111210_e, "Weapon modifier", (double)(5.0f + ThaumcraftApi.toolMatVoid.func_78000_c()), 0));
        return multimap;
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (!world.field_72995_K && tile != null && tile.getClass().equals(TileNode.class)) {
            INode node = (INode)tile;
            AspectList primals = new AspectList();
            AspectList temp = ResearchManager.reduceToPrimals((AspectList)node.getAspects(), (boolean)true);
            for (Aspect aspect : temp.getAspects()) {
                int amt = temp.getAmount(aspect);
                if (node.getNodeModifier() == NodeModifier.BRIGHT) {
                    amt = (int)((float)amt * 1.2f);
                }
                if (node.getNodeModifier() == NodeModifier.PALE) {
                    amt = (int)((float)amt * 0.8f);
                }
                if (node.getNodeModifier() == NodeModifier.FADING) {
                    amt = (int)((float)amt * 0.5f);
                }
                amt = MathHelper.func_76128_c((double)MathHelper.func_76133_a((double)amt));
                if (node.getNodeType() == NodeType.UNSTABLE) {
                    amt += world.field_73012_v.nextInt(5) - 2;
                }
                if (amt < 1) continue;
                primals.merge(aspect, amt);
            }
            this.setContainedNode(stack, node.getNodeType(), node.getNodeModifier(), primals);
            world.func_147468_f(x, y, z);
            return true;
        }
        return false;
    }

    public ItemStack setContainedNode(ItemStack bracelet, NodeType type, NodeModifier modifier, AspectList aspects) {
        if (!bracelet.func_77942_o()) {
            bracelet.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound nodeTag = new NBTTagCompound();
        nodeTag.func_74768_a("type", type.ordinal());
        if (modifier != null) {
            nodeTag.func_74768_a("modifier", modifier.ordinal());
        }
        aspects.writeToNBT(nodeTag);
        bracelet.func_77978_p().func_74782_a("storedNode", (NBTBase)nodeTag);
        bracelet.func_77978_p().func_74768_a("visCapacity", aspects.visSize());
        return bracelet;
    }

    @Override
    public int getReturnedPearls(ItemStack stack) {
        return 1;
    }
}

