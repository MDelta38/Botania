/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.item.IManaDissolvable;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.ItemMod;

public class ItemBlackLotus
extends ItemMod
implements IManaDissolvable {
    private static final int MANA_PER = 8000;
    private static final int MANA_PER_T2 = 100000;

    public ItemBlackLotus() {
        this.func_77655_b("blackLotus");
        this.func_77627_a(true);
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 2; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return par1ItemStack.func_77960_j() > 0;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack) + par1ItemStack.func_77960_j();
    }

    @Override
    public void onDissolveTick(IManaPool pool, ItemStack stack, EntityItem item) {
        boolean t2;
        if (pool.isFull() || pool.getCurrentMana() == 0) {
            return;
        }
        TileEntity tile = (TileEntity)pool;
        boolean bl = t2 = stack.func_77960_j() > 0;
        if (!item.field_70170_p.field_72995_K) {
            pool.recieveMana(t2 ? 100000 : 8000);
            --stack.field_77994_a;
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(item.field_70170_p, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
        }
        for (int i = 0; i < 50; ++i) {
            float r = (float)Math.random() * 0.25f;
            float g = 0.0f;
            float b = (float)Math.random() * 0.25f;
            float s = 0.45f * (float)Math.random() * 0.25f;
            float m = 0.045f;
            float mx = ((float)Math.random() - 0.5f) * m;
            float my = (float)Math.random() * m;
            float mz = ((float)Math.random() - 0.5f) * m;
            Botania.proxy.wispFX(item.field_70170_p, item.field_70165_t, (float)tile.field_145848_d + 0.5f, item.field_70161_v, r, g, b, s, mx, my, mz);
        }
        item.field_70170_p.func_72956_a((Entity)item, "botania:blackLotus", 0.5f, t2 ? 0.1f : 1.0f);
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        list.add(StatCollector.func_74838_a((String)"botaniamisc.lotusDesc"));
    }
}

