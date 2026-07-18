/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockFelPumpkin
extends BlockMod
implements ILexiconable {
    private static final String TAG_FEL_SPAWNED = "Botania-FelSpawned";
    IIcon top;
    IIcon face;

    public BlockFelPumpkin() {
        super(Material.field_151572_C);
        this.func_149663_c("felPumpkin");
        this.func_149711_c(1.0f);
        this.func_149672_a(field_149766_f);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return p_149691_1_ == 1 ? this.top : (p_149691_1_ == 0 ? this.top : (p_149691_2_ == 2 && p_149691_1_ == 2 ? this.face : (p_149691_2_ == 3 && p_149691_1_ == 5 ? this.face : (p_149691_2_ == 0 && p_149691_1_ == 3 ? this.face : (p_149691_2_ == 1 && p_149691_1_ == 4 ? this.face : this.field_149761_L)))));
    }

    public void func_149726_b(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
        super.func_149726_b(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
        if (!p_149726_1_.field_72995_K && p_149726_1_.func_147439_a(p_149726_2_, p_149726_3_ - 1, p_149726_4_) == Blocks.field_150411_aY && p_149726_1_.func_147439_a(p_149726_2_, p_149726_3_ - 2, p_149726_4_) == Blocks.field_150411_aY) {
            p_149726_1_.func_147465_d(p_149726_2_, p_149726_3_, p_149726_4_, BlockFelPumpkin.func_149729_e((int)0), 0, 2);
            p_149726_1_.func_147465_d(p_149726_2_, p_149726_3_ - 1, p_149726_4_, BlockFelPumpkin.func_149729_e((int)0), 0, 2);
            p_149726_1_.func_147465_d(p_149726_2_, p_149726_3_ - 2, p_149726_4_, BlockFelPumpkin.func_149729_e((int)0), 0, 2);
            EntityBlaze blaze = new EntityBlaze(p_149726_1_);
            blaze.func_70012_b((double)p_149726_2_ + 0.5, (double)p_149726_3_ - 1.95, (double)p_149726_4_ + 0.5, 0.0f, 0.0f);
            blaze.getEntityData().func_74757_a(TAG_FEL_SPAWNED, true);
            p_149726_1_.func_72838_d((Entity)blaze);
            p_149726_1_.func_147444_c(p_149726_2_, p_149726_3_, p_149726_4_, BlockFelPumpkin.func_149729_e((int)0));
            p_149726_1_.func_147444_c(p_149726_2_, p_149726_3_ - 1, p_149726_4_, BlockFelPumpkin.func_149729_e((int)0));
            p_149726_1_.func_147444_c(p_149726_2_, p_149726_3_ - 2, p_149726_4_, BlockFelPumpkin.func_149729_e((int)0));
        }
    }

    public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        int l = MathHelper.func_76128_c((double)((double)(p_149689_5_.field_70177_z * 4.0f / 360.0f) + 2.5)) & 3;
        p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, l, 2);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_) {
        this.face = IconHelper.forBlock(p_149651_1_, this);
        this.top = Blocks.field_150423_aK.func_149691_a(0, 0);
        this.field_149761_L = Blocks.field_150423_aK.func_149691_a(2, 0);
    }

    @SubscribeEvent
    public void onDrops(LivingDropsEvent event) {
        if (event.entity instanceof EntityBlaze && event.entity.getEntityData().func_74767_n(TAG_FEL_SPAWNED)) {
            if (event.drops.isEmpty()) {
                event.drops.add(new EntityItem(event.entity.field_70170_p, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, new ItemStack(Items.field_151065_br, 6)));
            } else {
                for (EntityItem item : event.drops) {
                    ItemStack stack = item.func_92059_d();
                    if (stack.func_77973_b() != Items.field_151072_bj) continue;
                    item.func_92058_a(new ItemStack(Items.field_151065_br, stack.field_77994_a * 10));
                }
            }
        }
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.gardenOfGlass;
    }
}

