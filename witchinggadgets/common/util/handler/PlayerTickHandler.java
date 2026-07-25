/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.lib.potions.PotionWarpWard
 */
package witchinggadgets.common.util.handler;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.UUID;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.potions.PotionWarpWard;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.blocks.tiles.TileEntitySaunaStove;
import witchinggadgets.common.util.Utilities;

public class PlayerTickHandler {
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (player != null && event.phase.equals((Object)TickEvent.Phase.START)) {
            World world = player.field_70170_p;
            if (TileEntitySaunaStove.targetedPlayers.containsKey(player.func_145782_y())) {
                TileEntitySaunaStove stove = TileEntitySaunaStove.targetedPlayers.get(player.func_145782_y());
                boolean flag = false;
                for (AxisAlignedBB aabb : stove.boundingBoxes) {
                    if (!world.func_72872_a(EntityPlayer.class, aabb).contains(player)) continue;
                    flag = true;
                }
                if (flag && !stove.func_145837_r() && stove.tick > 0) {
                    if (world.field_73012_v.nextInt(100) == 0) {
                        WGModCompat.enviromineDoSaunaStuff((EntityLivingBase)player, 0.01f, 0.01f);
                        if (player.func_71024_bL().func_75116_a() > 6) {
                            player.func_71024_bL().func_75122_a(-1, 0.1f);
                        }
                    }
                    if (world.field_72995_K && world.field_73012_v.nextInt(3) == 0) {
                        WitchingGadgets.proxy.createSweatFx(player);
                    }
                    if (!world.func_72830_b(player.field_70121_D, Material.field_151586_h)) {
                        player.func_70690_d(new PotionEffect(PotionWarpWard.instance.field_76415_H, 20, 0, true));
                    }
                    if (world.field_73012_v.nextInt(200) == 0) {
                        Thaumcraft.addWarpToPlayer((EntityPlayer)player, (int)-1, (boolean)true);
                    }
                } else {
                    TileEntitySaunaStove.targetedPlayers.remove(player.func_145782_y());
                }
            }
            if (!player.field_70170_p.field_72995_K && player.field_70153_n != null && player.field_70153_n instanceof EntityLivingBase && EnchantmentHelper.func_77506_a((int)WGContent.enc_rideProtect.field_77352_x, (ItemStack)player.func_82169_q(3)) > 0) {
                player.field_70153_n.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), 1.0f);
                player.field_70153_n.func_70024_g((double)(player.func_70681_au().nextFloat() * 0.4f), (double)0.1f, (double)(player.func_70681_au().nextFloat() * 0.4f));
                ((EntityLivingBase)player.field_70153_n).func_110145_l((Entity)player);
                player.field_70153_n.field_70154_o = null;
                player.field_70153_n = null;
            }
            IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
            if (Utilities.isPlayerUsingBow(player) && baubles != null && (OreDictionary.itemMatches((ItemStack)new ItemStack(WGContent.ItemMagicalBaubles, 1, 6), (ItemStack)baubles.func_70301_a(1), (boolean)true) || OreDictionary.itemMatches((ItemStack)new ItemStack(WGContent.ItemMagicalBaubles, 1, 6), (ItemStack)baubles.func_70301_a(2), (boolean)true)) && !Utilities.livingHasAttributeMod((EntityLivingBase)player, SharedMonsterAttributes.field_111263_d, new UUID(109406L, 6L))) {
                Utilities.addAttributeModToLivingUnsaved((EntityLivingBase)player, SharedMonsterAttributes.field_111263_d, new UUID(109406L, 6L), "WGBowBonus", 2.75, 1);
            }
        } else if (player != null && event.phase.equals((Object)TickEvent.Phase.END) && Utilities.livingHasAttributeMod((EntityLivingBase)player, SharedMonsterAttributes.field_111263_d, new UUID(109406L, 6L))) {
            Utilities.removeAttributeModFromLiving((EntityLivingBase)player, SharedMonsterAttributes.field_111263_d, new UUID(109406L, 6L), "WGBowBonus", 2.75, 1);
        }
    }
}

