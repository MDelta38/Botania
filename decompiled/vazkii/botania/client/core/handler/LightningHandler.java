/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.RedStringRenderer;
import vazkii.botania.client.fx.ParticleRenderDispatcher;
import vazkii.botania.common.core.helper.Vector3;

public class LightningHandler {
    private static final ResourceLocation outsideResource = new ResourceLocation("botania:textures/misc/wispLarge.png");
    private static final ResourceLocation insideResource = new ResourceLocation("botania:textures/misc/wispSmall.png");
    static double interpPosX;
    static double interpPosY;
    static double interpPosZ;

    private static Vector3 getRelativeViewVector(Vector3 pos) {
        EntityLivingBase renderEntity = Minecraft.func_71410_x().field_71451_h;
        return new Vector3((double)((float)renderEntity.field_70165_t) - pos.x, (double)((float)renderEntity.field_70163_u + renderEntity.func_70047_e()) - pos.y, (double)((float)renderEntity.field_70161_v) - pos.z);
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Profiler profiler = Minecraft.func_71410_x().field_71424_I;
        profiler.func_76320_a("botania-particles");
        ParticleRenderDispatcher.dispatch();
        profiler.func_76320_a("redString");
        RedStringRenderer.renderAll();
        profiler.func_76318_c("lightning");
        float frame = event.partialTicks;
        EntityClientPlayerMP entity = Minecraft.func_71410_x().field_71439_g;
        TextureManager render = Minecraft.func_71410_x().field_71446_o;
        interpPosX = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * (double)frame;
        interpPosY = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)frame;
        interpPosZ = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * (double)frame;
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-interpPosX), (double)(-interpPosY), (double)(-interpPosZ));
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        ParticleRenderDispatcher.lightningCount = 0;
        render.func_110577_a(outsideResource);
        tessellator.func_78382_b();
        tessellator.func_78380_c(0xF000F0);
        for (LightningBolt bolt : LightningBolt.boltlist) {
            this.renderBolt(bolt, tessellator, frame, ActiveRenderInfo.field_74588_d, ActiveRenderInfo.field_74589_e, ActiveRenderInfo.field_74586_f, ActiveRenderInfo.field_74596_h, 0, false);
        }
        tessellator.func_78381_a();
        render.func_110577_a(insideResource);
        tessellator.func_78382_b();
        tessellator.func_78380_c(0xF000F0);
        for (LightningBolt bolt : LightningBolt.boltlist) {
            this.renderBolt(bolt, tessellator, frame, ActiveRenderInfo.field_74588_d, ActiveRenderInfo.field_74589_e, ActiveRenderInfo.field_74586_f, ActiveRenderInfo.field_74596_h, 1, true);
        }
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glTranslated((double)interpPosX, (double)interpPosY, (double)interpPosZ);
        GL11.glPopMatrix();
        profiler.func_76319_b();
        profiler.func_76319_b();
    }

    public static void spawnLightningBolt(World world, Vector3 vectorStart, Vector3 vectorEnd, float ticksPerMeter, long seed, int colorOuter, int colorInner) {
        LightningBolt bolt = new LightningBolt(world, vectorStart, vectorEnd, ticksPerMeter, seed, colorOuter, colorInner);
        bolt.defaultFractal();
        bolt.finalizeBolt();
        LightningBolt.boltlist.add(bolt);
    }

    private void renderBolt(LightningBolt bolt, Tessellator tessellator, float partialframe, float cosyaw, float cospitch, float sinyaw, float cossinpitch, int pass, boolean inner) {
        ++ParticleRenderDispatcher.lightningCount;
        float boltage = bolt.particleAge < 0 ? 0.0f : (float)bolt.particleAge / (float)bolt.particleMaxAge;
        float mainalpha = 1.0f;
        mainalpha = pass == 0 ? (1.0f - boltage) * 0.4f : 1.0f - boltage * 0.5f;
        int expandTime = (int)(bolt.length * (double)bolt.speed);
        int renderstart = (int)((float)(expandTime / 2 - bolt.particleMaxAge + bolt.particleAge) / (float)(expandTime / 2) * (float)bolt.numsegments0);
        int renderend = (int)((float)(bolt.particleAge + expandTime) / (float)expandTime * (float)bolt.numsegments0);
        for (LightningBolt.Segment rendersegment : bolt.segments) {
            Vector3 roundend;
            if (rendersegment.segmentno < renderstart || rendersegment.segmentno > renderend) continue;
            Vector3 playervec = LightningHandler.getRelativeViewVector(rendersegment.startpoint.point).multiply(-1.0);
            double width = (double)0.025f * (playervec.mag() / 5.0 + 1.0) * (double)(1.0f + rendersegment.light) * 0.5;
            Vector3 diff1 = playervec.copy().crossProduct(rendersegment.prevdiff).normalize().multiply(width / (double)rendersegment.sinprev);
            Vector3 diff2 = playervec.copy().crossProduct(rendersegment.nextdiff).normalize().multiply(width / (double)rendersegment.sinnext);
            Vector3 startvec = rendersegment.startpoint.point;
            Vector3 endvec = rendersegment.endpoint.point;
            int color = inner ? bolt.colorInner : bolt.colorOuter;
            tessellator.func_78384_a(color, (int)(mainalpha * rendersegment.light * (float)new Color(color).getAlpha()));
            tessellator.func_78374_a(endvec.x - diff2.x, endvec.y - diff2.y, endvec.z - diff2.z, 0.5, 0.0);
            tessellator.func_78374_a(startvec.x - diff1.x, startvec.y - diff1.y, startvec.z - diff1.z, 0.5, 0.0);
            tessellator.func_78374_a(startvec.x + diff1.x, startvec.y + diff1.y, startvec.z + diff1.z, 0.5, 1.0);
            tessellator.func_78374_a(endvec.x + diff2.x, endvec.y + diff2.y, endvec.z + diff2.z, 0.5, 1.0);
            if (rendersegment.next == null) {
                roundend = rendersegment.endpoint.point.copy().add(rendersegment.diff.copy().normalize().multiply(width));
                tessellator.func_78374_a(roundend.x - diff2.x, roundend.y - diff2.y, roundend.z - diff2.z, 0.0, 0.0);
                tessellator.func_78374_a(endvec.x - diff2.x, endvec.y - diff2.y, endvec.z - diff2.z, 0.5, 0.0);
                tessellator.func_78374_a(endvec.x + diff2.x, endvec.y + diff2.y, endvec.z + diff2.z, 0.5, 1.0);
                tessellator.func_78374_a(roundend.x + diff2.x, roundend.y + diff2.y, roundend.z + diff2.z, 0.0, 1.0);
            }
            if (rendersegment.prev != null) continue;
            roundend = rendersegment.startpoint.point.copy().subtract(rendersegment.diff.copy().normalize().multiply(width));
            tessellator.func_78374_a(startvec.x - diff1.x, startvec.y - diff1.y, startvec.z - diff1.z, 0.5, 0.0);
            tessellator.func_78374_a(roundend.x - diff1.x, roundend.y - diff1.y, roundend.z - diff1.z, 0.0, 0.0);
            tessellator.func_78374_a(roundend.x + diff1.x, roundend.y + diff1.y, roundend.z + diff1.z, 0.0, 1.0);
            tessellator.func_78374_a(startvec.x + diff1.x, startvec.y + diff1.y, startvec.z + diff1.z, 0.5, 1.0);
        }
    }

    public static class LightningBolt {
        public ArrayList<Segment> segments = new ArrayList();
        public Vector3 start;
        public Vector3 end;
        ChunkCoordinates target;
        HashMap<Integer, Integer> splitparents = new HashMap();
        public double length;
        public int numsegments0;
        private int numsplits;
        private boolean finalized;
        private Random rand;
        public long seed;
        public int particleAge;
        public int particleMaxAge;
        public boolean isDead;
        private AxisAlignedBB boundingBox;
        private World world;
        private Entity source;
        public static ConcurrentLinkedQueue<LightningBolt> boltlist = new ConcurrentLinkedQueue();
        public float speed = 1.5f;
        public static final int fadetime = 20;
        public static int playerdamage = 1;
        public static int entitydamage = 1;
        public int colorOuter;
        public int colorInner;

        public LightningBolt(World world, Vector3 sourcevec, Vector3 targetvec, float ticksPerMeter, long seed, int colorOuter, int colorInner) {
            this.world = world;
            this.seed = seed;
            this.rand = new Random(seed);
            this.start = sourcevec;
            this.end = targetvec;
            this.speed = ticksPerMeter;
            this.colorOuter = colorOuter;
            this.colorInner = colorInner;
            this.numsegments0 = 1;
            this.length = this.end.copy().subtract(this.start).mag();
            this.particleMaxAge = 20 + this.rand.nextInt(20) - 10;
            this.particleAge = -((int)(this.length * (double)this.speed));
            this.boundingBox = AxisAlignedBB.func_72330_a((double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0);
            this.boundingBox.func_72328_c(AxisAlignedBB.func_72330_a((double)Math.min(this.start.x, this.end.x), (double)Math.min(this.start.y, this.end.y), (double)Math.min(this.start.z, this.end.z), (double)Math.max(this.start.x, this.end.x), (double)Math.max(this.start.y, this.end.y), (double)Math.max(this.start.z, this.end.z)).func_72314_b(this.length / 2.0, this.length / 2.0, this.length / 2.0));
            this.segments.add(new Segment(this.start, this.end));
        }

        public static Vector3 getFocalPoint(TileEntity tile) {
            return Vector3.fromTileEntityCenter(tile);
        }

        public LightningBolt(World world, Vector3 sourcevec, TileEntity target, float ticksPerMeter, long seed, int colorOuter, int colorInner) {
            this(world, sourcevec, LightningBolt.getFocalPoint(target), ticksPerMeter, seed, colorOuter, colorInner);
            this.target = new ChunkCoordinates(target.field_145851_c, target.field_145848_d, target.field_145849_e);
        }

        public void setWrapper(Entity entity) {
            this.source = entity;
        }

        public void fractal(int splits, double amount, double splitchance, double splitlength, double splitangle) {
            if (this.finalized) {
                return;
            }
            ArrayList<Segment> oldsegments = this.segments;
            this.segments = new ArrayList();
            Segment prev = null;
            for (Segment segment : oldsegments) {
                int i;
                prev = segment.prev;
                Vector3 subsegment = segment.diff.copy().multiply(1.0f / (float)splits);
                BoltPoint[] newpoints = new BoltPoint[splits + 1];
                Vector3 startpoint = segment.startpoint.point;
                newpoints[0] = segment.startpoint;
                newpoints[splits] = segment.endpoint;
                for (i = 1; i < splits; ++i) {
                    Vector3 randoff = segment.diff.copy().perpendicular().normalize().rotate(this.rand.nextFloat() * 360.0f, segment.diff);
                    randoff.multiply((double)(this.rand.nextFloat() - 0.5f) * amount * 2.0);
                    Vector3 basepoint = startpoint.copy().add(subsegment.copy().multiply(i));
                    newpoints[i] = new BoltPoint(basepoint, randoff);
                }
                for (i = 0; i < splits; ++i) {
                    Segment next = new Segment(newpoints[i], newpoints[i + 1], segment.light, segment.segmentno * splits + i, segment.splitno);
                    next.prev = prev;
                    if (prev != null) {
                        prev.next = next;
                    }
                    if (i != 0 && (double)this.rand.nextFloat() < splitchance) {
                        Vector3 splitrot = next.diff.copy().xCrossProduct().rotate(this.rand.nextFloat() * 360.0f, next.diff);
                        Vector3 diff = next.diff.copy().rotate((double)(this.rand.nextFloat() * 0.66f + 0.33f) * splitangle, splitrot).multiply(splitlength);
                        ++this.numsplits;
                        this.splitparents.put(this.numsplits, next.splitno);
                        Segment split = new Segment(newpoints[i], new BoltPoint(newpoints[i + 1].basepoint, newpoints[i + 1].offsetvec.copy().add(diff)), segment.light / 2.0f, next.segmentno, this.numsplits);
                        split.prev = prev;
                        this.segments.add(split);
                    }
                    prev = next;
                    this.segments.add(next);
                }
                if (segment.next == null) continue;
                segment.next.prev = prev;
            }
            this.numsegments0 *= splits;
        }

        public void defaultFractal() {
            this.fractal(2, this.length / 1.5, 0.7f, 0.7f, 45.0);
            this.fractal(2, this.length / 4.0, 0.5, 0.8f, 50.0);
            this.fractal(2, this.length / 15.0, 0.5, 0.9f, 55.0);
            this.fractal(2, this.length / 30.0, 0.5, 1.0, 60.0);
            this.fractal(2, this.length / 60.0, 0.0, 0.0, 0.0);
            this.fractal(2, this.length / 100.0, 0.0, 0.0, 0.0);
            this.fractal(2, this.length / 400.0, 0.0, 0.0, 0.0);
        }

        private float rayTraceResistance(Vector3 start, Vector3 end, float prevresistance) {
            MovingObjectPosition mop = this.world.func_72933_a(start.toVec3D(), end.toVec3D());
            if (mop == null) {
                return prevresistance;
            }
            if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                Block block = this.world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                if (this.world.func_147437_c(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) {
                    return prevresistance;
                }
                return prevresistance + block.func_149638_a(this.source) + 0.3f;
            }
            return prevresistance;
        }

        private void calculateCollisionAndDiffs() {
            HashMap<Integer, Integer> lastactivesegment = new HashMap<Integer, Integer>();
            Collections.sort(this.segments, new SegmentSorter());
            int lastsplitcalc = 0;
            int lastactiveseg = 0;
            float splitresistance = 0.0f;
            for (Segment segment : this.segments) {
                if (segment.splitno > lastsplitcalc) {
                    lastactivesegment.put(lastsplitcalc, lastactiveseg);
                    lastsplitcalc = segment.splitno;
                    lastactiveseg = (Integer)lastactivesegment.get(this.splitparents.get(segment.splitno));
                    float f = splitresistance = lastactiveseg < segment.segmentno ? 50.0f : 0.0f;
                }
                if (splitresistance >= 40.0f * segment.light) continue;
                splitresistance = this.rayTraceResistance(segment.startpoint.point, segment.endpoint.point, splitresistance);
                lastactiveseg = segment.segmentno;
            }
            lastactivesegment.put(lastsplitcalc, lastactiveseg);
            lastsplitcalc = 0;
            lastactiveseg = (Integer)lastactivesegment.get(0);
            Iterator<Segment> iterator = this.segments.iterator();
            while (iterator.hasNext()) {
                Segment segment;
                segment = iterator.next();
                if (lastsplitcalc != segment.splitno) {
                    lastsplitcalc = segment.splitno;
                    lastactiveseg = (Integer)lastactivesegment.get(segment.splitno);
                }
                if (segment.segmentno > lastactiveseg) {
                    iterator.remove();
                }
                segment.calcEndDiffs();
            }
        }

        public void finalizeBolt() {
            if (this.finalized) {
                return;
            }
            this.finalized = true;
            this.calculateCollisionAndDiffs();
            Collections.sort(this.segments, new SegmentLightSorter());
            boltlist.add(this);
        }

        public void onUpdate() {
            ++this.particleAge;
            if (this.particleAge >= this.particleMaxAge) {
                this.isDead = true;
            }
        }

        public static void update() {
            Iterator<LightningBolt> iterator = boltlist.iterator();
            while (iterator.hasNext()) {
                LightningBolt bolt = iterator.next();
                bolt.onUpdate();
                if (!bolt.isDead) continue;
                iterator.remove();
            }
        }

        public class Segment {
            public BoltPoint startpoint;
            public BoltPoint endpoint;
            public Vector3 diff;
            public Segment prev;
            public Segment next;
            public Vector3 nextdiff;
            public Vector3 prevdiff;
            public float sinprev;
            public float sinnext;
            public float light;
            public int segmentno;
            public int splitno;

            public Segment(BoltPoint start, BoltPoint end, float light, int segmentnumber, int splitnumber) {
                this.startpoint = start;
                this.endpoint = end;
                this.light = light;
                this.segmentno = segmentnumber;
                this.splitno = splitnumber;
                this.calcDiff();
            }

            public Segment(Vector3 start, Vector3 end) {
                this(this$0.new BoltPoint(start, new Vector3(0.0, 0.0, 0.0)), this$0.new BoltPoint(end, new Vector3(0.0, 0.0, 0.0)), 1.0f, 0, 0);
            }

            public void calcDiff() {
                this.diff = this.endpoint.point.copy().subtract(this.startpoint.point);
            }

            public void calcEndDiffs() {
                Vector3 thisdiffnorm;
                if (this.prev != null) {
                    Vector3 prevdiffnorm = this.prev.diff.copy().normalize();
                    thisdiffnorm = this.diff.copy().normalize();
                    this.prevdiff = thisdiffnorm.copy().add(prevdiffnorm).normalize();
                    this.sinprev = (float)Math.sin(thisdiffnorm.angle(prevdiffnorm.multiply(-1.0)) / 2.0);
                } else {
                    this.prevdiff = this.diff.copy().normalize();
                    this.sinprev = 1.0f;
                }
                if (this.next != null) {
                    Vector3 nextdiffnorm = this.next.diff.copy().normalize();
                    thisdiffnorm = this.diff.copy().normalize();
                    this.nextdiff = thisdiffnorm.add(nextdiffnorm).normalize();
                    this.sinnext = (float)Math.sin(thisdiffnorm.angle(nextdiffnorm.multiply(-1.0)) / 2.0);
                } else {
                    this.nextdiff = this.diff.copy().normalize();
                    this.sinnext = 1.0f;
                }
            }

            public String toString() {
                return this.startpoint.point.toString() + " " + this.endpoint.point.toString();
            }
        }

        public class SegmentLightSorter
        implements Comparator<Segment> {
            @Override
            public int compare(Segment o1, Segment o2) {
                return Float.compare(o2.light, o1.light);
            }
        }

        public class SegmentSorter
        implements Comparator<Segment> {
            @Override
            public int compare(Segment o1, Segment o2) {
                int comp = Integer.valueOf(o1.splitno).compareTo(o2.splitno);
                if (comp == 0) {
                    return Integer.valueOf(o1.segmentno).compareTo(o2.segmentno);
                }
                return comp;
            }
        }

        public class BoltPoint {
            public Vector3 point;
            Vector3 basepoint;
            Vector3 offsetvec;

            public BoltPoint(Vector3 basepoint, Vector3 offsetvec) {
                this.point = basepoint.copy().add(offsetvec);
                this.basepoint = basepoint;
                this.offsetvec = offsetvec;
            }
        }
    }
}

