/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.common.item.rod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.item.IAvatarTile;
import vazkii.botania.api.item.IAvatarWieldable;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.ItemMod;

public class ItemDiviningRod
extends ItemMod
implements IManaUsingItem,
IAvatarWieldable {
    private static final ResourceLocation avatarOverlay = new ResourceLocation("botania:textures/model/avatarDivining.png");
    static final int COST = 3000;

    public ItemDiviningRod() {
        this.func_77625_d(1);
        this.func_77655_b("diviningRod");
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer p) {
        if (ManaItemHandler.requestManaExactForTool(stack, p, 3000, true)) {
            if (world.field_72995_K) {
                int x = MathHelper.func_76128_c((double)p.field_70165_t);
                int y = MathHelper.func_76128_c((double)p.field_70163_u);
                int z = MathHelper.func_76128_c((double)p.field_70161_v);
                int range = IManaProficiencyArmor.Helper.hasProficiency(p) ? 20 : 15;
                long seedxor = world.field_73012_v.nextLong();
                this.doHighlight(world, x, y, z, range, seedxor);
                p.func_71038_i();
            } else {
                world.func_72956_a((Entity)p, "botania:divinationRod", 1.0f, 1.0f);
            }
        }
        return stack;
    }

    public void doHighlight(World world, int x, int y, int z, int range, long seedxor) {
        Botania.proxy.setWispFXDepthTest(false);
        for (int i = -range; i < range + 1; ++i) {
            for (int j = -range; j < range + 1; ++j) {
                block2: for (int k = -range; k < range + 1; ++k) {
                    int xp = x + i;
                    int yp = y + j;
                    int zp = z + k;
                    Block block = world.func_147439_a(xp, yp, zp);
                    int meta = world.func_72805_g(xp, yp, zp);
                    ItemStack orestack = new ItemStack(block, 1, meta);
                    for (int id : OreDictionary.getOreIDs((ItemStack)orestack)) {
                        String s = OreDictionary.getOreName((int)id);
                        if (!s.matches("^ore[A-Z].+")) continue;
                        Random rand = new Random((long)s.hashCode() ^ seedxor);
                        Botania.proxy.wispFX(world, (float)xp + world.field_73012_v.nextFloat(), (float)yp + world.field_73012_v.nextFloat(), (float)zp + world.field_73012_v.nextFloat(), rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 0.25f, 0.0f, 8.0f);
                        continue block2;
                    }
                }
            }
        }
        Botania.proxy.setWispFXDepthTest(true);
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    @Override
    public void onAvatarUpdate(IAvatarTile tile, ItemStack stack) {
        TileEntity te = (TileEntity)tile;
        World world = te.func_145831_w();
        if (tile.getCurrentMana() >= 3000 && tile.getElapsedFunctionalTicks() % 200 == 0 && tile.isEnabled()) {
            this.doHighlight(world, te.field_145851_c, te.field_145848_d, te.field_145849_e, 18, te.field_145851_c ^ te.field_145848_d ^ te.field_145849_e);
            tile.recieveMana(-3000);
        }
    }

    @Override
    public ResourceLocation getOverlayResource(IAvatarTile tile, ItemStack stack) {
        return avatarOverlay;
    }
}

