/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemIcePendant
extends ItemBauble
implements IBaubleRender {
    IIcon gemIcon;
    public static Map<String, List<IceRemover>> playerIceBlocks = new HashMap<String, List<IceRemover>>();

    public ItemIcePendant() {
        super("icePendant");
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        this.gemIcon = IconHelper.forItem(par1IconRegister, (Item)this, "Gem");
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase entity) {
        super.onWornTick(stack, entity);
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (!player.field_70170_p.field_72995_K) {
                this.tickIceRemovers(player);
            }
            if (!(player.func_70093_af() || player.func_70055_a(Material.field_151586_h) || player.field_70170_p.field_72995_K)) {
                int x = MathHelper.func_76128_c((double)player.field_70165_t);
                int y = MathHelper.func_76128_c((double)(player.field_70163_u - (double)(!player.func_70090_H() ? 1 : 0)));
                int z = MathHelper.func_76128_c((double)player.field_70161_v);
                int range = 3;
                for (int i = -range; i < range + 1; ++i) {
                    for (int j = -range; j < range + 1; ++j) {
                        int x1 = x + i;
                        int z1 = z + j;
                        this.addIceBlock(player, new ChunkCoordinates(x1, y, z1));
                    }
                }
            }
        }
    }

    private void addIceBlock(EntityPlayer player, ChunkCoordinates coords) {
        String user = player.func_70005_c_();
        if (!playerIceBlocks.containsKey(user)) {
            playerIceBlocks.put(user, new ArrayList());
        }
        List<IceRemover> ice = playerIceBlocks.get(user);
        if (player.field_70170_p.func_147439_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c) == Blocks.field_150355_j && player.field_70170_p.func_72805_g(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c) == 0) {
            player.field_70170_p.func_147449_b(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, Blocks.field_150432_aD);
            if (!player.field_70170_p.field_72995_K) {
                ice.add(new IceRemover(coords));
            }
        }
    }

    private void tickIceRemovers(EntityPlayer player) {
        String user = player.func_70005_c_();
        if (!playerIceBlocks.containsKey(user)) {
            return;
        }
        List<IceRemover> removers = playerIceBlocks.get(user);
        for (IceRemover ice : new ArrayList<IceRemover>(removers)) {
            ice.tick(player.field_70170_p, removers);
        }
    }

    @Override
    public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, IBaubleRender.RenderType type) {
        if (type == IBaubleRender.RenderType.BODY) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            IBaubleRender.Helper.rotateIfSneaking(event.entityPlayer);
            boolean armor = event.entityPlayer.func_82169_q(2) != null;
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.36f, (float)-0.3f, (float)(armor ? 0.2f : 0.15f));
            GL11.glRotatef((float)-45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            float f = this.gemIcon.func_94209_e();
            float f1 = this.gemIcon.func_94212_f();
            float f2 = this.gemIcon.func_94206_g();
            float f3 = this.gemIcon.func_94210_h();
            ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)this.gemIcon.func_94211_a(), (int)this.gemIcon.func_94216_b(), (float)0.03125f);
        }
    }

    class IceRemover {
        int time = 30;
        final ChunkCoordinates coords;

        public IceRemover(ChunkCoordinates coords) {
            this.coords = coords;
        }

        public void tick(World world, List<IceRemover> list) {
            if (world.func_147439_a(this.coords.field_71574_a, this.coords.field_71572_b, this.coords.field_71573_c) == Blocks.field_150432_aD) {
                if (this.time-- != 0) {
                    return;
                }
                world.func_147465_d(this.coords.field_71574_a, this.coords.field_71572_b, this.coords.field_71573_c, Blocks.field_150355_j, 0, 3);
                list.remove(this);
            }
        }
    }
}

