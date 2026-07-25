/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.crash.CrashReport
 *  net.minecraft.crash.CrashReportCategory
 *  net.minecraft.util.ReportedException
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.fx;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

public class ParticleEngine {
    public static ParticleEngine instance = new ParticleEngine();
    public static final ResourceLocation particleTexture = new ResourceLocation("thaumcraft", "textures/misc/particles.png");
    public static final ResourceLocation particleTexture2 = new ResourceLocation("thaumcraft", "textures/misc/particles2.png");
    protected World worldObj;
    private HashMap<Integer, ArrayList<EntityFX>>[] particles = new HashMap[]{new HashMap(), new HashMap(), new HashMap(), new HashMap()};
    private Random rand = new Random();

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        float frame = event.partialTicks;
        EntityClientPlayerMP entity = Minecraft.func_71410_x().field_71439_g;
        TextureManager renderer = Minecraft.func_71410_x().field_71446_o;
        int dim = Minecraft.func_71410_x().field_71441_e.field_73011_w.field_76574_g;
        renderer.func_110577_a(particleTexture);
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        boolean rebound = false;
        for (int layer = 0; layer < 4; ++layer) {
            ArrayList<EntityFX> parts;
            if (!this.particles[layer].containsKey(dim) || (parts = this.particles[layer].get(dim)).size() == 0) continue;
            if (!rebound && layer >= 2) {
                renderer.func_110577_a(particleTexture2);
                rebound = true;
            }
            GL11.glPushMatrix();
            switch (layer) {
                case 0: 
                case 2: {
                    GL11.glBlendFunc((int)770, (int)1);
                    break;
                }
                case 1: 
                case 3: {
                    GL11.glBlendFunc((int)770, (int)771);
                }
            }
            float f1 = ActiveRenderInfo.field_74588_d;
            float f2 = ActiveRenderInfo.field_74586_f;
            float f3 = ActiveRenderInfo.field_74587_g;
            float f4 = ActiveRenderInfo.field_74596_h;
            float f5 = ActiveRenderInfo.field_74589_e;
            EntityFX.field_70556_an = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * (double)frame;
            EntityFX.field_70554_ao = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)frame;
            EntityFX.field_70555_ap = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * (double)frame;
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            for (int j = 0; j < parts.size(); ++j) {
                final EntityFX entityfx = parts.get(j);
                if (entityfx == null) continue;
                tessellator.func_78380_c(entityfx.func_70070_b(frame));
                try {
                    entityfx.func_70539_a(tessellator, frame, f1, f5, f2, f3, f4);
                    continue;
                }
                catch (Throwable throwable) {
                    CrashReport crashreport = CrashReport.func_85055_a((Throwable)throwable, (String)"Rendering Particle");
                    CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being rendered");
                    crashreportcategory.func_71500_a("Particle", new Callable(){

                        public String call() {
                            return entityfx.toString();
                        }
                    });
                    crashreportcategory.func_71500_a("Particle Type", new Callable(){

                        public String call() {
                            return "ENTITY_PARTICLE_TEXTURE";
                        }
                    });
                    throw new ReportedException(crashreport);
                }
            }
            tessellator.func_78381_a();
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
    }

    public void addEffect(World world, EntityFX fx) {
        ArrayList<EntityFX> parts;
        if (!this.particles[fx.func_70537_b()].containsKey(world.field_73011_w.field_76574_g)) {
            this.particles[fx.func_70537_b()].put(world.field_73011_w.field_76574_g, new ArrayList());
        }
        if ((parts = this.particles[fx.func_70537_b()].get(world.field_73011_w.field_76574_g)).size() >= 2000) {
            parts.remove(0);
        }
        parts.add(fx);
        this.particles[fx.func_70537_b()].put(world.field_73011_w.field_76574_g, parts);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void updateParticles(TickEvent.ClientTickEvent event) {
        if (event.side == Side.SERVER) {
            return;
        }
        Minecraft mc = FMLClientHandler.instance().getClient();
        WorldClient world = mc.field_71441_e;
        if (mc.field_71441_e == null) {
            return;
        }
        int dim = world.field_73011_w.field_76574_g;
        if (event.phase == TickEvent.Phase.START) {
            for (int layer = 0; layer < 4; ++layer) {
                if (!this.particles[layer].containsKey(dim)) continue;
                ArrayList<EntityFX> parts = this.particles[layer].get(dim);
                for (int j = 0; j < parts.size(); ++j) {
                    final EntityFX entityfx = parts.get(j);
                    try {
                        if (entityfx != null) {
                            entityfx.func_70071_h_();
                        }
                    }
                    catch (Throwable throwable) {
                        CrashReport crashreport = CrashReport.func_85055_a((Throwable)throwable, (String)"Ticking Particle");
                        CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being ticked");
                        crashreportcategory.func_71500_a("Particle", new Callable(){

                            public String call() {
                                return entityfx.toString();
                            }
                        });
                        crashreportcategory.func_71500_a("Particle Type", new Callable(){

                            public String call() {
                                return "ENTITY_PARTICLE_TEXTURE";
                            }
                        });
                        throw new ReportedException(crashreport);
                    }
                    if (entityfx != null && !entityfx.field_70128_L) continue;
                    parts.remove(j--);
                    this.particles[layer].put(dim, parts);
                }
            }
        }
    }
}

