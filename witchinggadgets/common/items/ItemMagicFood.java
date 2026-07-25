/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.lib.network.PacketHandler
 *  thaumcraft.common.lib.network.playerdata.PacketAspectPool
 *  thaumcraft.common.lib.research.ResearchManager
 */
package witchinggadgets.common.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.research.ResearchManager;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGModCompat;

public class ItemMagicFood
extends ItemFood {
    private int[] hungerHealed = new int[]{2, 6, 6};
    private float[] saturation = new float[]{0.4f, 1.5f, 1.0f};
    private static final String[] subNames = new String[]{"sweetwart", "nethercake", "brainjerky"};
    private IIcon[] icon = new IIcon[subNames.length];

    public ItemMagicFood() {
        super(0, 0.0f, false);
        this.func_77637_a(WitchingGadgets.tabWG);
        this.func_77627_a(true);
    }

    public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
        --stack.field_77994_a;
        player.func_71024_bL().func_151686_a((ItemFood)this, stack);
        world.func_72956_a((Entity)player, "random.burp", 0.5f, world.field_73012_v.nextFloat() * 0.1f + 0.9f);
        this.func_77849_c(stack, world, player);
        return stack;
    }

    public int func_150905_g(ItemStack stack) {
        return this.hungerHealed[Math.min(this.hungerHealed.length - 1, stack.func_77960_j())];
    }

    public float func_150906_h(ItemStack stack) {
        return this.saturation[Math.min(this.saturation.length - 1, stack.func_77960_j())];
    }

    public void func_77849_c(ItemStack stack, World world, EntityPlayer player) {
        if (stack.func_77960_j() == 2 && !world.field_72995_K) {
            int luck = world.field_73012_v.nextInt(3);
            for (int pass = 0; pass <= luck; ++pass) {
                Aspect a = (Aspect)Aspect.getPrimalAspects().get(world.field_73012_v.nextInt(Aspect.getPrimalAspects().size()));
                short q = (short)(world.field_73012_v.nextInt(2) + 1);
                Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), a, q);
                Thaumcraft.proxy.getResearchManager();
                ResearchManager.scheduleSave((EntityPlayer)player);
                PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(a.getTag(), Short.valueOf(q), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), a))), (EntityPlayerMP)player);
            }
        }
    }

    public void func_94581_a(IIconRegister iconRegister) {
        for (int i = 0; i < subNames.length; ++i) {
            this.icon[i] = iconRegister.func_94245_a("witchinggadgets:food_" + subNames[i]);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int metadata) {
        return this.icon[metadata];
    }

    public String func_77667_c(ItemStack itemstack) {
        return this.func_77658_a() + "." + subNames[itemstack.func_77960_j()];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item item, CreativeTabs tab, List itemList) {
        for (int i = 0; i < subNames.length; ++i) {
            if (i == 2 && !WGModCompat.loaded_TCon) continue;
            itemList.add(new ItemStack(item, 1, i));
        }
    }
}

