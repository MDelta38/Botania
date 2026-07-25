/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package thaumic.tinkerer.common.item.kami.armor;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import thaumic.tinkerer.client.core.handler.kami.ToolModeHUDHandler;
import thaumic.tinkerer.common.item.kami.armor.ItemIchorclothArmor;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public abstract class ItemIchorclothArmorAdv
extends ItemIchorclothArmor {
    public ItemIchorclothArmorAdv(int par2) {
        super(par2);
        this.func_77627_a(true);
        if (this.ticks()) {
            MinecraftForge.EVENT_BUS.register((Object)this);
        }
    }

    public ItemIchorclothArmorAdv() {
        this(0);
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.func_70093_af()) {
            int dmg = par1ItemStack.func_77960_j();
            par1ItemStack.func_77964_b(~dmg & 1);
            par2World.func_72956_a((Entity)par3EntityPlayer, "random.orb", 0.3f, 0.1f);
            ToolModeHUDHandler.setTooltip(StatCollector.func_74838_a((String)("ttmisc.awakenedArmor" + par1ItemStack.func_77960_j())));
            return par1ItemStack;
        }
        return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
    }

    @Override
    public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        super.func_77624_a(stack, par2EntityPlayer, list, par4);
        if (stack.func_77960_j() == 1) {
            list.add(StatCollector.func_74838_a((String)"ttmisc.awakenedArmor1"));
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return slot == 2 ? "ttinkerer:textures/model/ichorGem2.png" : "ttinkerer:textures/model/ichorGem1.png";
    }

    boolean ticks() {
        return false;
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        EntityPlayer player;
        ItemStack armor;
        if (event.entityLiving instanceof EntityPlayer && (armor = (player = (EntityPlayer)event.entityLiving).func_82169_q(3 - this.field_77881_a)) != null && armor.func_77973_b() == this) {
            this.tickPlayer(player);
        }
    }

    void tickPlayer(EntityPlayer player) {
    }

    @Override
    public abstract String getItemName();

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return null;
    }
}

