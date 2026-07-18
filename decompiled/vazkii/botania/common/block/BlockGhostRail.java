/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockRailBase
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.minecart.MinecartUpdateEvent
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.block.ItemBlockMod;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockGhostRail
extends BlockRailBase
implements ILexiconable {
    private static final String TAG_FLOAT_TICKS = "Botania_FloatTicks";

    public BlockGhostRail() {
        super(true);
        this.func_149647_a(BotaniaCreativeTab.INSTANCE);
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.func_149663_c("ghostRail");
    }

    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockMod.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = IconHelper.forBlock(par1IconRegister, (Block)this);
    }

    @SubscribeEvent
    public void onMinecartUpdate(MinecartUpdateEvent event) {
        int x = MathHelper.func_76128_c((double)event.entity.field_70165_t);
        int y = MathHelper.func_76128_c((double)event.entity.field_70163_u);
        int z = MathHelper.func_76128_c((double)event.entity.field_70161_v);
        Block block = event.entity.field_70170_p.func_147439_a(x, y, z);
        boolean air = block.isAir((IBlockAccess)event.entity.field_70170_p, x, y, z);
        int floatTicks = event.entity.getEntityData().func_74762_e(TAG_FLOAT_TICKS);
        if (block == this) {
            event.entity.getEntityData().func_74768_a(TAG_FLOAT_TICKS, 20);
        } else if (block instanceof BlockRailBase || block == ModBlocks.dreamwood) {
            event.entity.getEntityData().func_74768_a(TAG_FLOAT_TICKS, 0);
            if (floatTicks > 0) {
                event.entity.field_70170_p.func_72926_e(2003, x, y, z, 0);
            }
        }
        floatTicks = event.entity.getEntityData().func_74762_e(TAG_FLOAT_TICKS);
        if (floatTicks > 0) {
            Block blockBelow = event.entity.field_70170_p.func_147439_a(x, y - 1, z);
            boolean airBelow = blockBelow.isAir((IBlockAccess)event.entity.field_70170_p, x, y - 1, z);
            if (air && airBelow || !air && !airBelow) {
                event.entity.field_70145_X = true;
            }
            event.entity.field_70181_x = 0.2;
            event.entity.field_70159_w *= 1.4;
            event.entity.field_70179_y *= 1.4;
            event.entity.getEntityData().func_74768_a(TAG_FLOAT_TICKS, floatTicks - 1);
            event.entity.field_70170_p.func_72926_e(2000, x, y, z, 0);
        } else {
            event.entity.field_70145_X = false;
        }
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.ghostRail;
    }
}

