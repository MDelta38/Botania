/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerRespawnEvent
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.PlayerDropsEvent
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 */
package vazkii.botania.common.item;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.oredict.RecipeSorter;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.crafting.recipe.KeepIvyRecipe;
import vazkii.botania.common.item.ItemMod;

public class ItemKeepIvy
extends ItemMod {
    public static final String TAG_KEEP = "Botania_keepIvy";
    private static final String TAG_PLAYER_KEPT_DROPS = "Botania_playerKeptDrops";
    private static final String TAG_DROP_COUNT = "dropCount";
    private static final String TAG_DROP_PREFIX = "dropPrefix";

    public ItemKeepIvy() {
        this.func_77655_b("keepIvy");
        GameRegistry.addRecipe((IRecipe)new KeepIvyRecipe());
        RecipeSorter.register((String)"botania:keepIvy", KeepIvyRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    @SubscribeEvent
    public void onPlayerDrops(PlayerDropsEvent event) {
        ArrayList<EntityItem> keeps = new ArrayList<EntityItem>();
        for (EntityItem item : event.drops) {
            ItemStack stack = item.func_92059_d();
            if (stack == null || !ItemNBTHelper.detectNBT(stack) || !ItemNBTHelper.getBoolean(stack, TAG_KEEP, false)) continue;
            keeps.add(item);
        }
        if (keeps.size() > 0) {
            event.drops.removeAll(keeps);
            NBTTagCompound cmp = new NBTTagCompound();
            cmp.func_74768_a(TAG_DROP_COUNT, keeps.size());
            int i = 0;
            for (EntityItem keep : keeps) {
                ItemStack stack = keep.func_92059_d();
                NBTTagCompound cmp1 = new NBTTagCompound();
                stack.func_77955_b(cmp1);
                cmp.func_74782_a(TAG_DROP_PREFIX + i, (NBTBase)cmp1);
                ++i;
            }
            NBTTagCompound data = event.entityPlayer.getEntityData();
            if (!data.func_74764_b("PlayerPersisted")) {
                data.func_74782_a("PlayerPersisted", (NBTBase)new NBTTagCompound());
            }
            NBTTagCompound persist = data.func_74775_l("PlayerPersisted");
            persist.func_74782_a(TAG_PLAYER_KEPT_DROPS, (NBTBase)cmp);
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        NBTTagCompound data = event.player.getEntityData();
        if (data.func_74764_b("PlayerPersisted")) {
            NBTTagCompound cmp = data.func_74775_l("PlayerPersisted");
            NBTTagCompound cmp1 = cmp.func_74775_l(TAG_PLAYER_KEPT_DROPS);
            int count = cmp1.func_74762_e(TAG_DROP_COUNT);
            for (int i = 0; i < count; ++i) {
                NBTTagCompound cmp2 = cmp1.func_74775_l(TAG_DROP_PREFIX + i);
                ItemStack stack = ItemStack.func_77949_a((NBTTagCompound)cmp2);
                if (stack == null) continue;
                ItemStack copy = stack.func_77946_l();
                ItemNBTHelper.setBoolean(copy, TAG_KEEP, false);
                event.player.field_71071_by.func_70441_a(copy);
            }
            cmp.func_74782_a(TAG_PLAYER_KEPT_DROPS, (NBTBase)new NBTTagCompound());
        }
    }
}

