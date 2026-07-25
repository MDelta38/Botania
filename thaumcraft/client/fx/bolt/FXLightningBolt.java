/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.fx.bolt;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.fx.WRVector3;
import thaumcraft.client.fx.bolt.FXLightningBoltCommon;
import thaumcraft.client.lib.UtilsFX;

public class FXLightningBolt
extends EntityFX {
    private int type = 0;
    private float width = 0.03f;
    private FXLightningBoltCommon main;

    public FXLightningBolt(World world, WRVector3 jammervec, WRVector3 targetvec, long seed) {
        super(world, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.main = new FXLightningBoltCommon(world, jammervec, targetvec, seed);
        this.setupFromMain();
    }

    public FXLightningBolt(World world, Entity detonator, Entity target, long seed) {
        super(world, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.main = new FXLightningBoltCommon(world, detonator, target, seed);
        this.setupFromMain();
    }

    public FXLightningBolt(World world, Entity detonator, Entity target, long seed, int speed) {
        super(world, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.main = new FXLightningBoltCommon(world, detonator, target, seed, speed);
        this.setupFromMain();
    }

    public FXLightningBolt(World world, TileEntity detonator, Entity target, long seed) {
        super(world, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.main = new FXLightningBoltCommon(world, detonator, target, seed);
        this.setupFromMain();
    }

    public FXLightningBolt(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration, float multi) {
        super(world, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.main = new FXLightningBoltCommon(world, x1, y1, z1, x, y, z, seed, duration, multi);
        this.setupFromMain();
    }

    public FXLightningBolt(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration, float multi, int speed) {
        super(world, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.main = new FXLightningBoltCommon(world, x1, y1, z1, x, y, z, seed, duration, multi, speed);
        this.setupFromMain();
    }

    public FXLightningBolt(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration) {
        super(world, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.main = new FXLightningBoltCommon(world, x1, y1, z1, x, y, z, seed, duration, 1.0f);
        this.setupFromMain();
    }

    public FXLightningBolt(World world, TileEntity detonator, double x, double y, double z, long seed) {
        super(world, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.main = new FXLightningBoltCommon(world, detonator, x, y, z, seed);
        this.setupFromMain();
    }

    private void setupFromMain() {
        this.field_70546_d = this.main.particleMaxAge;
        this.func_70107_b(this.main.start.x, this.main.start.y, this.main.start.z);
        this.func_70016_h(0.0, 0.0, 0.0);
    }

    public void defaultFractal() {
        this.main.defaultFractal();
    }

    public void fractal(int splits, float amount, float splitchance, float splitlength, float splitangle) {
        this.main.fractal(splits, amount, splitchance, splitlength, splitangle);
    }

    public void finalizeBolt() {
        this.main.finalizeBolt();
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)this);
    }

    public void setType(int type) {
        this.type = type;
        this.main.type = type;
    }

    public void setMultiplier(float m) {
        this.main.multiplier = m;
    }

    public void setWidth(float m) {
        this.width = m;
    }

    public void func_70071_h_() {
        this.main.onUpdate();
        if (this.main.particleAge >= this.main.particleMaxAge) {
            this.func_70106_y();
        }
    }

    private static WRVector3 getRelativeViewVector(WRVector3 pos) {
        EntityClientPlayerMP renderentity = FMLClientHandler.instance().getClient().field_71439_g;
        return new WRVector3((float)renderentity.field_70165_t - pos.x, (float)renderentity.field_70163_u - pos.y, (float)renderentity.field_70161_v - pos.z);
    }

    private void renderBolt(Tessellator tessellator, float partialframe, float cosyaw, float cospitch, float sinyaw, float cossinpitch, int pass, float mainalpha) {
        WRVector3 playervec = new WRVector3(sinyaw * -cospitch, -cossinpitch / cosyaw, cosyaw * cospitch);
        float boltage = this.main.particleAge >= 0 ? (float)this.main.particleAge / (float)this.main.particleMaxAge : 0.0f;
        mainalpha = pass == 0 ? (1.0f - boltage) * 0.4f : 1.0f - boltage * 0.5f;
        int renderlength = (int)(((float)this.main.particleAge + partialframe + (float)((int)(this.main.length * 3.0f))) / (float)((int)(this.main.length * 3.0f)) * (float)this.main.numsegments0);
        for (FXLightningBoltCommon.Segment rendersegment : this.main.segments) {
            float rz3;
            float ry3;
            float rx3;
            WRVector3 roundend;
            if (rendersegment.segmentno > renderlength) continue;
            float width = this.width * (FXLightningBolt.getRelativeViewVector(rendersegment.startpoint.point).length() / 5.0f + 1.0f) * (1.0f + rendersegment.light) * 0.5f;
            WRVector3 diff1 = WRVector3.crossProduct(playervec, rendersegment.prevdiff).scale(width / rendersegment.sinprev);
            WRVector3 diff2 = WRVector3.crossProduct(playervec, rendersegment.nextdiff).scale(width / rendersegment.sinnext);
            WRVector3 startvec = rendersegment.startpoint.point;
            WRVector3 endvec = rendersegment.endpoint.point;
            float rx1 = (float)((double)startvec.x - field_70556_an);
            float ry1 = (float)((double)startvec.y - field_70554_ao);
            float rz1 = (float)((double)startvec.z - field_70555_ap);
            float rx2 = (float)((double)endvec.x - field_70556_an);
            float ry2 = (float)((double)endvec.y - field_70554_ao);
            float rz2 = (float)((double)endvec.z - field_70555_ap);
            tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, mainalpha * rendersegment.light);
            tessellator.func_78374_a((double)(rx2 - diff2.x), (double)(ry2 - diff2.y), (double)(rz2 - diff2.z), 0.5, 0.0);
            tessellator.func_78374_a((double)(rx1 - diff1.x), (double)(ry1 - diff1.y), (double)(rz1 - diff1.z), 0.5, 0.0);
            tessellator.func_78374_a((double)(rx1 + diff1.x), (double)(ry1 + diff1.y), (double)(rz1 + diff1.z), 0.5, 1.0);
            tessellator.func_78374_a((double)(rx2 + diff2.x), (double)(ry2 + diff2.y), (double)(rz2 + diff2.z), 0.5, 1.0);
            if (rendersegment.next == null) {
                roundend = rendersegment.endpoint.point.copy().add(rendersegment.diff.copy().normalize().scale(width));
                rx3 = (float)((double)roundend.x - field_70556_an);
                ry3 = (float)((double)roundend.y - field_70554_ao);
                rz3 = (float)((double)roundend.z - field_70555_ap);
                tessellator.func_78374_a((double)(rx3 - diff2.x), (double)(ry3 - diff2.y), (double)(rz3 - diff2.z), 0.0, 0.0);
                tessellator.func_78374_a((double)(rx2 - diff2.x), (double)(ry2 - diff2.y), (double)(rz2 - diff2.z), 0.5, 0.0);
                tessellator.func_78374_a((double)(rx2 + diff2.x), (double)(ry2 + diff2.y), (double)(rz2 + diff2.z), 0.5, 1.0);
                tessellator.func_78374_a((double)(rx3 + diff2.x), (double)(ry3 + diff2.y), (double)(rz3 + diff2.z), 0.0, 1.0);
            }
            if (rendersegment.prev != null) continue;
            roundend = rendersegment.startpoint.point.copy().sub(rendersegment.diff.copy().normalize().scale(width));
            rx3 = (float)((double)roundend.x - field_70556_an);
            ry3 = (float)((double)roundend.y - field_70554_ao);
            rz3 = (float)((double)roundend.z - field_70555_ap);
            tessellator.func_78374_a((double)(rx1 - diff1.x), (double)(ry1 - diff1.y), (double)(rz1 - diff1.z), 0.5, 0.0);
            tessellator.func_78374_a((double)(rx3 - diff1.x), (double)(ry3 - diff1.y), (double)(rz3 - diff1.z), 0.0, 0.0);
            tessellator.func_78374_a((double)(rx3 + diff1.x), (double)(ry3 + diff1.y), (double)(rz3 + diff1.z), 0.0, 1.0);
            tessellator.func_78374_a((double)(rx1 + diff1.x), (double)(ry1 + diff1.y), (double)(rz1 + diff1.z), 0.5, 1.0);
        }
    }

    public void func_70539_a(Tessellator tessellator, float partialframe, float cosyaw, float cospitch, float sinyaw, float sinsinpitch, float cossinpitch) {
        EntityClientPlayerMP renderentity = FMLClientHandler.instance().getClient().field_71439_g;
        int visibleDistance = 100;
        if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) {
            visibleDistance = 50;
        }
        if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > (double)visibleDistance) {
            return;
        }
        tessellator.func_78381_a();
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        this.field_70551_j = 1.0f;
        this.field_70553_i = 1.0f;
        this.field_70552_h = 1.0f;
        float ma = 1.0f;
        switch (this.type) {
            case 0: {
                this.field_70552_h = 0.6f;
                this.field_70553_i = 0.3f;
                this.field_70551_j = 0.6f;
                GL11.glBlendFunc((int)770, (int)1);
                break;
            }
            case 1: {
                this.field_70552_h = 0.6f;
                this.field_70553_i = 0.6f;
                this.field_70551_j = 0.1f;
                GL11.glBlendFunc((int)770, (int)1);
                break;
            }
            case 2: {
                this.field_70552_h = 0.1f;
                this.field_70553_i = 0.1f;
                this.field_70551_j = 0.6f;
                GL11.glBlendFunc((int)770, (int)1);
                break;
            }
            case 3: {
                this.field_70552_h = 0.1f;
                this.field_70553_i = 1.0f;
                this.field_70551_j = 0.1f;
                GL11.glBlendFunc((int)770, (int)1);
                break;
            }
            case 4: {
                this.field_70552_h = 0.6f;
                this.field_70553_i = 0.1f;
                this.field_70551_j = 0.1f;
                GL11.glBlendFunc((int)770, (int)1);
                break;
            }
            case 5: {
                this.field_70552_h = 0.6f;
                this.field_70553_i = 0.2f;
                this.field_70551_j = 0.6f;
                GL11.glBlendFunc((int)770, (int)771);
                break;
            }
            case 6: {
                this.field_70552_h = 0.75f;
                this.field_70553_i = 1.0f;
                this.field_70551_j = 1.0f;
                ma = 0.2f;
                GL11.glBlendFunc((int)770, (int)771);
            }
        }
        UtilsFX.bindTexture("textures/misc/p_large.png");
        tessellator.func_78382_b();
        tessellator.func_78380_c(0xF000F0);
        this.renderBolt(tessellator, partialframe, cosyaw, cospitch, sinyaw, cossinpitch, 0, ma);
        tessellator.func_78381_a();
        switch (this.type) {
            case 0: {
                this.field_70552_h = 1.0f;
                this.field_70553_i = 0.6f;
                this.field_70551_j = 1.0f;
                break;
            }
            case 1: {
                this.field_70552_h = 1.0f;
                this.field_70553_i = 1.0f;
                this.field_70551_j = 0.1f;
                break;
            }
            case 2: {
                this.field_70552_h = 0.1f;
                this.field_70553_i = 0.1f;
                this.field_70551_j = 1.0f;
                break;
            }
            case 3: {
                this.field_70552_h = 0.1f;
                this.field_70553_i = 0.6f;
                this.field_70551_j = 0.1f;
                break;
            }
            case 4: {
                this.field_70552_h = 1.0f;
                this.field_70553_i = 0.1f;
                this.field_70551_j = 0.1f;
                break;
            }
            case 5: {
                this.field_70552_h = 0.0f;
                this.field_70553_i = 0.0f;
                this.field_70551_j = 0.0f;
                GL11.glBlendFunc((int)770, (int)771);
                break;
            }
            case 6: {
                this.field_70552_h = 0.75f;
                this.field_70553_i = 1.0f;
                this.field_70551_j = 1.0f;
                ma = 0.2f;
                GL11.glBlendFunc((int)770, (int)771);
            }
        }
        UtilsFX.bindTexture("textures/misc/p_small.png");
        tessellator.func_78382_b();
        tessellator.func_78380_c(0xF000F0);
        this.renderBolt(tessellator, partialframe, cosyaw, cospitch, sinyaw, cossinpitch, 1, ma);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
        tessellator.func_78382_b();
    }

    public int getRenderPass() {
        return 2;
    }
}

