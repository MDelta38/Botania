/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.TinyPotatoRenderEvent;
import vazkii.botania.client.core.handler.ContributorFancinessHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.model.ModelTinyPotato;
import vazkii.botania.common.block.tile.TileTinyPotato;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.bauble.ItemFlightTiara;
import vazkii.botania.common.item.material.ItemManaResource;
import vazkii.botania.common.item.relic.ItemInfiniteFruit;

public class RenderTileTinyPotato
extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/tinyPotato.png");
    private static final ResourceLocation textureGrayscale = new ResourceLocation("botania:textures/model/tinyPotatoGray.png");
    private static final ResourceLocation textureHalloween = new ResourceLocation("botania:textures/model/tinyPotato_halloween.png");
    private static final ModelTinyPotato model = new ModelTinyPotato();

    public void func_147500_a(TileEntity var1, double d0, double d1, double d2, float var8) {
        boolean render;
        TileTinyPotato potato = (TileTinyPotato)var1;
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        Minecraft mc = Minecraft.func_71410_x();
        mc.field_71446_o.func_110577_a(ClientProxy.dootDoot ? textureHalloween : texture);
        String name = potato.name.toLowerCase();
        boolean usedShader = false;
        if (name.startsWith("gaia ")) {
            ShaderHelper.useShader(ShaderHelper.doppleganger);
            name = name.substring(5);
            usedShader = true;
        } else if (name.startsWith("hot ")) {
            ShaderHelper.useShader(ShaderHelper.halo);
            name = name.substring(4);
            usedShader = true;
        } else if (name.startsWith("magic ")) {
            ShaderHelper.useShader(ShaderHelper.enchanterRune);
            name = name.substring(6);
            usedShader = true;
        } else if (name.startsWith("gold ")) {
            ShaderHelper.useShader(ShaderHelper.gold);
            name = name.substring(5);
            usedShader = true;
        } else if (name.startsWith("snoop ")) {
            ShaderHelper.useShader(ShaderHelper.terraPlateRune);
            name = name.substring(6);
            usedShader = true;
        }
        GL11.glTranslatef((float)0.5f, (float)1.5f, (float)0.5f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        int meta = potato.func_145831_w() == null ? 3 : potato.func_145832_p();
        float rotY = (float)meta * 90.0f - 180.0f;
        GL11.glRotatef((float)rotY, (float)0.0f, (float)1.0f, (float)0.0f);
        float jump = potato.jumpTicks;
        if (jump > 0.0f) {
            jump -= var8;
        }
        float up = (float)(-Math.abs(Math.sin((double)(jump / 10.0f) * Math.PI))) * 0.2f;
        float rotZ = (float)Math.sin((double)(jump / 10.0f) * Math.PI) * 2.0f;
        GL11.glTranslatef((float)0.0f, (float)up, (float)0.0f);
        GL11.glRotatef((float)rotZ, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glPushMatrix();
        if (name.equals("pahimar")) {
            GL11.glScalef((float)1.0f, (float)0.3f, (float)1.0f);
            GL11.glTranslatef((float)0.0f, (float)3.5f, (float)0.0f);
        } else if (name.equals("kyle hyde")) {
            mc.field_71446_o.func_110577_a(textureGrayscale);
        } else if (name.equals("dinnerbone") || name.equals("grumm")) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)0.0f, (float)-2.625f, (float)0.0f);
        } else if (name.equals("aureylian")) {
            GL11.glColor3f((float)1.0f, (float)0.5f, (float)1.0f);
        }
        boolean bl = render = !name.equals("mami") && !name.equals("soaryn") && (!name.equals("eloraam") || jump == 0.0f);
        if (render) {
            model.render();
        }
        if (name.equals("kingdaddydmac")) {
            GL11.glTranslated((double)0.5, (double)0.0, (double)0.0);
            model.render();
        }
        if (usedShader) {
            ShaderHelper.releaseShader();
        }
        GL11.glPopMatrix();
        if (!name.isEmpty()) {
            IIcon icon;
            GL11.glPushMatrix();
            mc.field_71446_o.func_110577_a(TextureMap.field_110576_c);
            ContributorFancinessHandler.firstStart();
            float scale = 0.25f;
            GL11.glTranslatef((float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glScalef((float)scale, (float)scale, (float)scale);
            if (name.equals("phi") || name.equals("vazkii")) {
                GL11.glTranslatef((float)0.45f, (float)0.0f, (float)0.4f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)1.0f);
                this.renderIcon(((ItemManaResource)ModItems.manaResource).phiFlowerIcon);
                if (name.equals("vazkii")) {
                    GL11.glRotatef((float)-20.0f, (float)1.0f, (float)0.0f, (float)1.0f);
                    GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)-1.5f, (float)-1.3f, (float)-0.75f);
                    this.renderIcon(((ItemManaResource)ModItems.manaResource).nerfBatIcon);
                }
            } else if (name.equals("skull kid")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.2f, (float)-0.4f);
                this.renderIcon(ModItems.cosmetic.func_77617_a(23));
            } else if (name.equals("kamina")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.1f, (float)-0.4f);
                this.renderIcon(ModItems.cosmetic.func_77617_a(26));
            } else if (name.equals("haighyorkie")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.2f, (float)-0.4f);
                this.renderIcon(((ItemManaResource)ModItems.manaResource).goldfishIcon);
            } else if (name.equals("chitoge")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.7f, (float)0.1f);
                this.renderIcon(ModItems.cosmetic.func_77617_a(7));
            } else if (name.equals("direwolf20")) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-2.2f, (float)-0.5f);
                this.renderIcon(ModItems.cosmetic.func_77617_a(0));
            } else if (name.equals("doctor")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.15f, (float)-0.4f);
                this.renderIcon(ModItems.cosmetic.func_77617_a(25));
            } else if (name.equals("snoo")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.7f, (float)0.1f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                this.renderIcon(ModItems.cosmetic.func_77617_a(24));
            } else if (name.equals("charlotte")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.2f, (float)-0.4f);
                this.renderIcon(ModItems.cosmetic.func_77617_a(12));
            } else if (name.equals("greg") || name.equals("gregorioust")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.5f, (float)-0.4f);
                this.renderIcon(Items.field_151122_aG.func_77617_a(0));
                mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
                GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.0f);
                GL11.glScalef((float)0.3f, (float)0.3f, (float)0.3f);
                RenderBlocks.getInstance().func_147800_a(Blocks.field_150366_p, 0, 1.0f);
            } else if (name.equals("profmobius")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.2f, (float)-0.4f);
                this.renderIcon(Items.field_151025_P.func_77617_a(0));
            } else if (name.equals("martysgames") || name.equals("marty")) {
                GL11.glScalef((float)0.7f, (float)0.7f, (float)0.7f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.75f, (float)-2.4f, (float)-0.7f);
                GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                this.renderIcon(ItemInfiniteFruit.dasBootIcon);
            } else if (name.equals("tromped")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.2f, (float)-0.4f);
                this.renderIcon(ModItems.cacophonium.func_77617_a(0));
            } else if (name.equals("kain vinosec")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.3f, (float)-1.5f, (float)-0.4f);
                this.renderIcon(ModItems.recordGaia1.func_77617_a(0));
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.85f);
                this.renderIcon(ModItems.recordGaia2.func_77617_a(0));
            } else if (name.equals("mankrik")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.2f, (float)-0.1f);
                this.renderIcon(ModItems.cosmetic.func_77617_a(31));
            } else if (name.equals("kurumi")) {
                GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.9f, (float)-2.5f, (float)-1.3f);
                this.renderIcon(ModItems.cosmetic.func_77617_a(17));
            } else if (name.equals("ichun")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.2f, (float)-0.4f);
                this.renderIcon(ModItems.cosmetic.func_77617_a(15));
            } else if (name.equals("wiiv") || name.equals("dylan4ever") || name.equals("dylankaiser")) {
                GL11.glScalef((float)1.5f, (float)1.5f, (float)1.5f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.1f, (float)-0.325f);
                this.renderIcon(Items.field_151159_an.func_77617_a(0));
            } else if (name.equals("jibril")) {
                GL11.glScalef((float)1.5f, (float)1.5f, (float)1.5f);
                GL11.glTranslatef((float)0.0f, (float)0.7f, (float)0.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                ItemFlightTiara.renderHalo(null, var8);
            } else if (name.equals("nebris")) {
                mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                RenderBlocks.getInstance().func_147800_a(Blocks.field_150426_aN, 0, 1.0f);
            } else if (name.equals("ible")) {
                mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
                GL11.glScalef((float)1.2f, (float)1.2f, (float)1.2f);
                GL11.glTranslatef((float)0.0f, (float)0.7f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                RenderBlocks.getInstance().func_147800_a((Block)Blocks.field_150427_aO, 0, 1.0f);
            } else if (name.equals("razz") || name.equals("razzleberryfox")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.0f, (float)0.45f);
                this.renderIcon(ModItems.cosmetic.func_77617_a(8));
            } else if (name.equals("etho") || name.equals("ethoslab")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.2f, (float)-0.4f);
                this.renderIcon(Items.field_151106_aX.func_77617_a(0));
            } else if (name.equals("sethbling")) {
                mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
                GL11.glScalef((float)1.2f, (float)1.2f, (float)1.2f);
                GL11.glTranslatef((float)0.0f, (float)0.9f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                RenderBlocks.getInstance().func_147800_a(Blocks.field_150483_bI, 0, 1.0f);
            } else if (name.equals("bdoubleo100") || name.equals("bdoubleo")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-1.0f, (float)-1.1f, (float)-0.1f);
                this.renderIcon(Items.field_151055_y.func_77617_a(0));
            } else if (name.equals("kingdaddydmac")) {
                GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.3f, (float)-2.5f, (float)1.075f);
                this.renderIcon(ModItems.manaRing.func_77617_a(0));
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-4.0f);
                this.renderIcon(ModItems.manaRing.func_77617_a(0));
                mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
                GL11.glScalef((float)1.5f, (float)1.5f, (float)1.5f);
                GL11.glTranslatef((float)1.5f, (float)-0.5f, (float)0.7f);
                RenderBlocks.getInstance().func_147800_a(Blocks.field_150414_aQ, 0, 1.0f);
            } else if (name.equals("sjin")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-1.27f, (float)-0.4f);
                this.renderIcon(ModItems.cosmetic.func_77617_a(27));
            } else if (name.equals("martyn") || name.equals("inthelittlewood")) {
                GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.45f, (float)-0.1f);
                mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
                this.renderIcon(Blocks.field_150345_g.func_149691_a(0, 0));
            } else if (ContributorFancinessHandler.flowerMap != null && ContributorFancinessHandler.flowerMap.containsKey(name) && (icon = ContributorFancinessHandler.flowerMap.get(name)) != null) {
                mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)0.0f);
                ShaderHelper.useShader(ShaderHelper.gold);
                this.renderIcon(icon);
                ShaderHelper.releaseShader();
            }
            GL11.glPopMatrix();
        }
        MinecraftForge.EVENT_BUS.post((Event)new TinyPotatoRenderEvent(potato, potato.name, d0, d1, d2, var8));
        GL11.glRotatef((float)(-rotZ), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(-rotY), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        MovingObjectPosition pos = mc.field_71476_x;
        if (!name.isEmpty() && pos != null && pos.field_72311_b == potato.field_145851_c && pos.field_72312_c == potato.field_145848_d && pos.field_72309_d == potato.field_145849_e) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)-0.6f, (float)0.0f);
            GL11.glRotatef((float)(-RenderManager.field_78727_a.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)RenderManager.field_78727_a.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
            float f = 1.6f;
            float f1 = 0.016666668f * f;
            GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
            GL11.glDisable((int)2896);
            GL11.glTranslatef((float)0.0f, (float)(0.0f / f1), (float)0.0f);
            GL11.glDepthMask((boolean)false);
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            Tessellator tessellator = Tessellator.field_78398_a;
            GL11.glDisable((int)3553);
            tessellator.func_78382_b();
            int i = mc.field_71466_p.func_78256_a(potato.name) / 2;
            tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 0.25f);
            tessellator.func_78377_a((double)(-i - 1), -1.0, 0.0);
            tessellator.func_78377_a((double)(-i - 1), 8.0, 0.0);
            tessellator.func_78377_a((double)(i + 1), 8.0, 0.0);
            tessellator.func_78377_a((double)(i + 1), -1.0, 0.0);
            tessellator.func_78381_a();
            GL11.glEnable((int)3553);
            GL11.glDepthMask((boolean)true);
            mc.field_71466_p.func_78276_b(potato.name, -mc.field_71466_p.func_78256_a(potato.name) / 2, 0, 0xFFFFFF);
            if (name.equals("pahimar") || name.equals("soaryn")) {
                GL11.glTranslatef((float)0.0f, (float)14.0f, (float)0.0f);
                String s = name.equals("pahimar") ? "[WIP]" : "(soon)";
                GL11.glDepthMask((boolean)false);
                GL11.glEnable((int)3042);
                OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                GL11.glDisable((int)3553);
                tessellator.func_78382_b();
                i = mc.field_71466_p.func_78256_a(s) / 2;
                tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 0.25f);
                tessellator.func_78377_a((double)(-i - 1), -1.0, 0.0);
                tessellator.func_78377_a((double)(-i - 1), 8.0, 0.0);
                tessellator.func_78377_a((double)(i + 1), 8.0, 0.0);
                tessellator.func_78377_a((double)(i + 1), -1.0, 0.0);
                tessellator.func_78381_a();
                GL11.glEnable((int)3553);
                GL11.glDepthMask((boolean)true);
                mc.field_71466_p.func_78276_b(s, -mc.field_71466_p.func_78256_a(s) / 2, 0, 0xFFFFFF);
            }
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glScalef((float)(1.0f / -f1), (float)(1.0f / -f1), (float)(1.0f / f1));
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    public void renderIcon(IIcon icon) {
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
    }
}

