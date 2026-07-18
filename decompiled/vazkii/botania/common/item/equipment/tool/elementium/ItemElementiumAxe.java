/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 */
package vazkii.botania.common.item.equipment.tool.elementium;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelAxe;

public class ItemElementiumAxe
extends ItemManasteelAxe {
    public ItemElementiumAxe() {
        super(BotaniaAPI.elementiumToolMaterial, "elementiumAxe");
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onEntityDrops(LivingDropsEvent event) {
        ItemStack weapon;
        if (event.recentlyHit && event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityPlayer && (weapon = ((EntityPlayer)event.source.func_76346_g()).func_71045_bC()) != null && weapon.func_77973_b() == this) {
            Random rand = event.entity.field_70170_p.field_73012_v;
            int looting = EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)weapon);
            if (event.entityLiving instanceof EntitySkeleton && rand.nextInt(26) <= 3 + looting) {
                this.addDrop(event, new ItemStack(Items.field_151144_bL, 1, ((EntitySkeleton)event.entityLiving).func_82202_m()));
            } else if (event.entityLiving instanceof EntityZombie && !(event.entityLiving instanceof EntityPigZombie) && rand.nextInt(26) <= 2 + 2 * looting) {
                this.addDrop(event, new ItemStack(Items.field_151144_bL, 1, 2));
            } else if (event.entityLiving instanceof EntityCreeper && rand.nextInt(26) <= 2 + 2 * looting) {
                this.addDrop(event, new ItemStack(Items.field_151144_bL, 1, 4));
            } else if (event.entityLiving instanceof EntityPlayer && rand.nextInt(11) <= 1 + looting) {
                ItemStack stack = new ItemStack(Items.field_151144_bL, 1, 3);
                ItemNBTHelper.setString(stack, "SkullOwner", ((EntityPlayer)event.entityLiving).func_70005_c_());
                this.addDrop(event, stack);
            } else if (event.entityLiving instanceof EntityDoppleganger && rand.nextInt(13) < 1 + looting) {
                this.addDrop(event, new ItemStack(ModItems.gaiaHead));
            }
        }
    }

    private void addDrop(LivingDropsEvent event, ItemStack drop) {
        EntityItem entityitem = new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, drop);
        entityitem.field_145804_b = 10;
        event.drops.add(entityitem);
    }
}

