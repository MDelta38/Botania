/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package witchinggadgets.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import witchinggadgets.common.items.EntityItemReforming;

public class ItemRelic
extends Item {
    public IIcon[] icon = new IIcon[64];
    private static final String[] subNames = new String[]{"hourglass", "dawnStone", "duskStone", "homewardBone"};

    public ItemRelic() {
        this.func_77627_a(true);
        this.func_77625_d(1);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        player.func_71008_a(stack, this.func_77626_a(stack));
        return stack;
    }

    public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int useTime) {
        double[] itemPos = new double[]{player.field_70165_t, player.field_70163_u, player.field_70161_v};
        if (useTime > 0) {
            return;
        }
        switch (stack.func_77960_j()) {
            case 0: {
                List l = world.func_72839_b((Entity)player, AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 4.0), (double)(player.field_70163_u - 3.0), (double)(player.field_70161_v - 4.0), (double)(player.field_70165_t + 4.0), (double)(player.field_70163_u + 3.0), (double)(player.field_70161_v + 4.0)));
                for (Entity ent : l) {
                    if (!(ent instanceof EntityLiving)) continue;
                    ((EntityLiving)ent).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 120, 6));
                }
                player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "random.glass", 0.4f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "fireworks.twinkle_far", 0.05f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                break;
            }
            case 1: {
                if (!world.field_72995_K) {
                    MinecraftServer.func_71276_C().field_71305_c[player.field_71093_bK].func_72877_b(0L);
                }
                player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "random.glass", 0.4f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "fireworks.twinkle_far", 0.05f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                break;
            }
            case 2: {
                if (!world.field_72995_K) {
                    MinecraftServer.func_71276_C().field_71305_c[player.field_71093_bK].func_72877_b(12500L);
                }
                player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "random.glass", 0.4f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "fireworks.twinkle_far", 0.05f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                break;
            }
            case 3: {
                ChunkCoordinates cc = player.getBedLocation(player.field_71093_bK);
                player.func_70634_a((double)cc.field_71574_a, (double)cc.field_71572_b, (double)cc.field_71573_c);
                player.field_70170_p.func_72980_b((double)cc.field_71574_a, (double)cc.field_71572_b, (double)cc.field_71573_c, "portal.trigger", 0.4f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                player.field_70170_p.func_72980_b((double)cc.field_71574_a, (double)cc.field_71572_b, (double)cc.field_71573_c, "fireworks.twinkle_far", 0.05f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                itemPos = new double[]{cc.field_71574_a, cc.field_71572_b, cc.field_71573_c};
            }
        }
        if (!world.field_72995_K) {
            world.func_72838_d((Entity)new EntityItemReforming(world, itemPos[0], itemPos[1], itemPos[2], stack.func_77946_l()));
        }
        --stack.field_77994_a;
    }

    public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71034_by();
        return par1ItemStack;
    }

    public int func_77626_a(ItemStack stack) {
        return 60;
    }

    public void func_94581_a(IIconRegister iconRegister) {
        for (int i = 0; i < subNames.length; ++i) {
            this.icon[i] = iconRegister.func_94245_a("witchinggadgets:relic_" + subNames[i]);
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
            itemList.add(new ItemStack((Item)this, 1, i));
        }
    }
}

