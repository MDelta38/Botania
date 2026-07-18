/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.relic;

import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityBabylonWeapon;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemKingKey
extends ItemRelic
implements IManaUsingItem {
    private static final String TAG_WEAPONS_SPAWNED = "weaponsSpawned";
    private static final String TAG_CHARGING = "charging";
    private static final int WEAPON_TYPES = 12;
    public static IIcon[] weaponIcons;

    public ItemKingKey() {
        super("kingKey");
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        weaponIcons = new IIcon[12];
        for (int i = 0; i < 12; ++i) {
            ItemKingKey.weaponIcons[i] = IconHelper.forName(par1IconRegister, "gateWeapon" + i);
        }
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        ItemKingKey.setCharging(par1ItemStack, true);
        return par1ItemStack;
    }

    public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int time) {
        int spawned = ItemKingKey.getWeaponsSpawned(stack);
        if (spawned == 20) {
            ItemKingKey.setCharging(stack, false);
            ItemKingKey.setWeaponsSpawned(stack, 0);
        }
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        int spawned = ItemKingKey.getWeaponsSpawned(stack);
        if (count != this.func_77626_a(stack) && spawned < 20 && !player.field_70170_p.field_72995_K && ManaItemHandler.requestManaExact(stack, player, 150, true)) {
            Vector3 look = new Vector3(player.func_70040_Z());
            look.y = 0.0;
            look.normalize().negate().multiply(2.0);
            int div = spawned / 5;
            int mod = spawned % 5;
            Vector3 pl = look.copy().add(Vector3.fromEntityCenter((Entity)player)).add(0.0, 1.6, (double)div * 0.1);
            Random rand = player.field_70170_p.field_73012_v;
            Vector3 axis = look.copy().normalize().crossProduct(new Vector3(-1.0, 0.0, -1.0)).normalize();
            Vector3 axis1 = axis.copy();
            double rot = (double)mod * Math.PI / 4.0 - 1.5707963267948966;
            axis1.multiply((double)div * 3.5 + 5.0).rotate(rot, look);
            if (axis1.y < 0.0) {
                axis1.y = -axis1.y;
            }
            Vector3 end = pl.copy().add(axis1);
            EntityBabylonWeapon weapon = new EntityBabylonWeapon(player.field_70170_p, (EntityLivingBase)player);
            weapon.field_70165_t = end.x;
            weapon.field_70163_u = end.y;
            weapon.field_70161_v = end.z;
            weapon.field_70177_z = player.field_70177_z;
            weapon.setVariety(rand.nextInt(12));
            weapon.setDelay(spawned);
            weapon.setRotation(MathHelper.func_76142_g((float)(-player.field_70177_z + 180.0f)));
            player.field_70170_p.func_72838_d((Entity)weapon);
            player.field_70170_p.func_72956_a((Entity)weapon, "botania:babylonSpawn", 1.0f, 1.0f + player.field_70170_p.field_73012_v.nextFloat() * 3.0f);
            ItemKingKey.setWeaponsSpawned(stack, spawned + 1);
        }
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public static boolean isCharging(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_CHARGING, false);
    }

    public static int getWeaponsSpawned(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_WEAPONS_SPAWNED, 0);
    }

    public static void setCharging(ItemStack stack, boolean charging) {
        ItemNBTHelper.setBoolean(stack, TAG_CHARGING, charging);
    }

    public static void setWeaponsSpawned(ItemStack stack, int count) {
        ItemNBTHelper.setInt(stack, TAG_WEAPONS_SPAWNED, count);
    }

    public boolean func_77662_d() {
        return true;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }
}

