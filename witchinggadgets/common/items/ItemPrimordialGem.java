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
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 */
package witchinggadgets.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import witchinggadgets.WitchingGadgets;

public class ItemPrimordialGem
extends Item {
    String[] subNames = new String[]{"perfodio", "vacuos", "sano", "aer"};
    HashMap<String, Object[]> subItems = new HashMap();
    IIcon iconFrame;

    public ItemPrimordialGem() {
        this.func_77625_d(1);
        this.func_77627_a(true);
        this.func_77637_a(WitchingGadgets.tabWG);
        this.subItems.put(this.subNames[0], new Object[]{true, false});
        this.subItems.put(this.subNames[1], new Object[]{false, true});
        this.subItems.put(this.subNames[2], new Object[]{true, true});
        this.subItems.put(this.subNames[3], new Object[]{true, false});
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        String sA = null;
        String sP = null;
        if (this.isActiveGem(stack)) {
            sA = StatCollector.func_74838_a((String)"wg.gui.primordialGem.activeAbility") + " " + StatCollector.func_74838_a((String)("wg.gui.primordialGem." + this.subNames[stack.func_77960_j()] + ".desc.active"));
        }
        if (this.isPassiveGem(stack)) {
            sP = StatCollector.func_74838_a((String)"wg.gui.primordialGem.passiveAbility") + " " + StatCollector.func_74838_a((String)("wg.gui.primordialGem." + this.subNames[stack.func_77960_j()] + ".desc.passive"));
        }
        if (sA != null) {
            list.add(sA);
        }
        if (sP != null) {
            list.add(sP);
        }
    }

    public int func_82790_a(ItemStack stack, int pass) {
        if (pass == 0) {
            String key = this.subNames[stack.func_77960_j()];
            Aspect a = Aspect.getAspect((String)key);
            if (a != null) {
                return a.getColor();
            }
            if (this.subItems.get(key).length > 2 && this.subItems.get(key)[2] != null && this.subItems.get(key)[2] instanceof Integer) {
                return (Integer)this.subItems.get(key)[2];
            }
            return 0xFFFFFF;
        }
        return super.func_82790_a(stack, pass);
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("witchinggadgets:primordialGem");
        this.iconFrame = iconRegister.func_94245_a("witchinggadgets:primordialGem_frame");
    }

    public boolean func_77623_v() {
        return true;
    }

    public int getRenderPasses(int meta) {
        return 2;
    }

    public IIcon func_77618_c(int par1, int pass) {
        if (pass == 0) {
            return this.field_77791_bV;
        }
        return this.iconFrame;
    }

    public void tickGem(World world, ItemStack bracelet, ItemStack gem, EntityLivingBase living) {
        String tag = this.subNames[gem.func_77960_j()];
        if (tag.equalsIgnoreCase("sano") && living.func_70660_b(Potion.field_76428_l) == null) {
            living.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 20));
        }
    }

    public boolean useGem(World world, ItemStack bracelet, ItemStack gem, EntityPlayer player) {
        String tag = this.subNames[gem.func_77960_j()];
        if (tag.equalsIgnoreCase("aer")) {
            AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 6.0), (double)(player.field_70163_u - 2.0), (double)(player.field_70161_v - 6.0), (double)(player.field_70165_t + 6.0), (double)(player.field_70163_u + 3.0), (double)(player.field_70161_v + 6.0));
            aabb.func_72314_b(6.0, 6.0, 6.0);
            List targets = world.func_72839_b((Entity)player, aabb);
            for (Entity ent : targets) {
                if (!player.func_70685_l(ent)) continue;
                double distX = ent.field_70165_t - player.field_70165_t;
                double distZ = ent.field_70161_v - player.field_70161_v;
                double dist = Math.sqrt(distX * distX + distZ * distZ);
                double force = Math.max(0.0, 1.0 - dist / 10.0);
                Vec3 look = player.func_70040_Z();
                ent.field_70159_w += force * look.field_72450_a;
                ent.field_70181_x = 0.25;
                ent.field_70179_y += force * look.field_72449_c;
            }
        }
        return false;
    }

    public boolean useGemOnEntity(World world, ItemStack bracelet, ItemStack gem, EntityPlayer player, Entity target) {
        return false;
    }

    public boolean useGemOnBlock(World world, ItemStack bracelet, ItemStack gem, EntityPlayer player, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        String tag = this.subNames[gem.func_77960_j()];
        if (tag.equalsIgnoreCase("perfodio")) {
            int xMin = side == 0 || side == 1 || side == 2 || side == 3 ? x - 1 : x;
            int xMax = side == 0 || side == 1 || side == 2 || side == 3 ? x + 1 : x;
            int yMin = side == 2 || side == 3 || side == 4 || side == 5 ? y - 1 : y;
            int yMax = side == 2 || side == 3 || side == 4 || side == 5 ? y + 1 : y;
            int zMin = side == 0 || side == 1 || side == 4 || side == 5 ? z - 1 : z;
            int zMax = side == 0 || side == 1 || side == 4 || side == 5 ? z + 1 : z;
            for (int xx = xMin; xx <= xMax; ++xx) {
                for (int yy = yMin; yy <= yMax; ++yy) {
                    for (int zz = zMin; zz <= zMax; ++zz) {
                        Block b = world.func_147439_a(xx, yy, zz);
                        int bMeta = world.func_72805_g(xx, yy, zz);
                        if (b == null) continue;
                        if (!player.field_71075_bZ.field_75098_d && b.canEntityDestroy((IBlockAccess)world, xx, yy, zz, (Entity)player)) {
                            if (b.removedByPlayer(world, player, xx, yy, zz, true)) {
                                b.func_149664_b(world, xx, yy, zz, bMeta);
                            }
                            b.func_149636_a(world, player, xx, yy, zz, bMeta);
                            b.func_149681_a(world, xx, yy, zz, bMeta, player);
                            continue;
                        }
                        world.func_147468_f(xx, yy, zz);
                    }
                }
            }
        }
        return false;
    }

    public boolean isActiveGem(ItemStack gem) {
        return (Boolean)this.subItems.get(this.subNames[gem.func_77960_j()])[0];
    }

    public boolean isPassiveGem(ItemStack gem) {
        return (Boolean)this.subItems.get(this.subNames[gem.func_77960_j()])[1];
    }

    public String func_77667_c(ItemStack itemstack) {
        return this.func_77658_a() + "." + this.subNames[itemstack.func_77960_j()];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item item, CreativeTabs tab, List itemList) {
        for (int i = 0; i < this.subNames.length; ++i) {
            itemList.add(new ItemStack((Item)this, 1, i));
        }
    }
}

