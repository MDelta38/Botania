/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.network.internal.FMLProxyPacket
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufOutputStream
 *  io.netty.buffer.Unpooled
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  thaumcraft.client.fx.bolt.FXLightningBolt
 */
package flaxbeard.thaumicexploration.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import flaxbeard.thaumicexploration.ThaumicExploration;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import thaumcraft.client.fx.bolt.FXLightningBolt;

public class TXTickHandler {
    private EntityLivingBase target;
    private MovingObjectPosition objectMouseOver;
    private Entity pointedEntity;
    private EntityLivingBase pointedEntityLiving;
    private EntityLivingBase lastPointedEntityLiving;
    private int watchTicks = 0;
    public static int ticks = 0;

    @SubscribeEvent
    public void tickStart(TickEvent.ClientTickEvent event) {
        ++ticks;
        if (Minecraft.func_71410_x().field_71439_g != null) {
            EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
            ItemStack item = player.func_82169_q(3);
            if (player.field_71071_by.func_70440_f(0) != null && player.field_71071_by.func_70440_f(0).func_77973_b() == ThaumicExploration.bootsMeteor) {
                FMLProxyPacket packet;
                ByteBufOutputStream out;
                ByteBuf buf;
                if (Minecraft.func_71410_x().field_71474_y.field_74311_E.func_151468_f()) {
                    buf = Unpooled.buffer();
                    out = new ByteBufOutputStream(buf);
                    try {
                        out.writeByte(4);
                        out.writeInt(Minecraft.func_71410_x().field_71439_g.field_70170_p.field_73011_w.field_76574_g);
                        out.writeInt(Minecraft.func_71410_x().field_71439_g.func_145782_y());
                        packet = new FMLProxyPacket(buf, "tExploration");
                        ThaumicExploration.channel.sendToServer(packet);
                        out.close();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    buf = Unpooled.buffer();
                    out = new ByteBufOutputStream(buf);
                    try {
                        out.writeByte(5);
                        out.writeInt(Minecraft.func_71410_x().field_71439_g.field_70170_p.field_73011_w.field_76574_g);
                        out.writeInt(Minecraft.func_71410_x().field_71439_g.func_145782_y());
                        packet = new FMLProxyPacket(buf, "tExploration");
                        ThaumicExploration.channel.sendToServer(packet);
                        out.close();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if (item != null && item.func_77973_b() == ThaumicExploration.maskEvil) {
                this.lastPointedEntityLiving = this.pointedEntityLiving;
                this.getMouseOver(0.0f);
                if (this.pointedEntityLiving != null) {
                    FXLightningBolt bolt;
                    if (this.pointedEntityLiving != this.lastPointedEntityLiving) {
                        this.watchTicks = 0;
                    }
                    ++this.watchTicks;
                    if (this.watchTicks > 0) {
                        if (this.watchTicks % 5 == 0) {
                            float offset = 0.0f;
                            if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 0) {
                                offset = 0.3f;
                            }
                            bolt = new FXLightningBolt(player.field_70170_p, player.field_70165_t, player.field_70121_D.field_72338_b + (double)(player.field_70131_O / 2.0f) + 0.75 - (double)offset, player.field_70161_v, this.pointedEntityLiving.field_70165_t, this.pointedEntityLiving.field_70121_D.field_72337_e - 0.5, this.pointedEntityLiving.field_70161_v, player.field_70170_p.field_73012_v.nextLong(), 6, 0.5f, 5);
                            bolt.defaultFractal();
                            bolt.setType(5);
                            bolt.setWidth(0.125f);
                            bolt.finalizeBolt();
                            ByteBuf buf = Unpooled.buffer();
                            ByteBufOutputStream out = new ByteBufOutputStream(buf);
                            try {
                                out.writeByte(2);
                                out.writeInt(Minecraft.func_71410_x().field_71439_g.field_70170_p.field_73011_w.field_76574_g);
                                out.writeInt(this.pointedEntityLiving.func_145782_y());
                                out.writeInt(Minecraft.func_71410_x().field_71439_g.func_145782_y());
                                FMLProxyPacket packet = new FMLProxyPacket(buf, "tExploration");
                                ThaumicExploration.channel.sendToServer(packet);
                                out.close();
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    } else if (this.watchTicks % 5 == 0) {
                        float offset = 0.0f;
                        if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 0) {
                            offset = 0.3f;
                        }
                        bolt = new FXLightningBolt(player.field_70170_p, player.field_70165_t, player.field_70121_D.field_72338_b + (double)(player.field_70131_O / 2.0f) + 0.75 - (double)offset, player.field_70161_v, this.pointedEntityLiving.field_70165_t, this.pointedEntityLiving.field_70121_D.field_72337_e - 0.5, this.pointedEntityLiving.field_70161_v, player.field_70170_p.field_73012_v.nextLong(), 6, 0.5f, 5);
                        bolt.defaultFractal();
                        bolt.setType(5);
                        bolt.setWidth(0.0625f);
                        bolt.finalizeBolt();
                    }
                } else {
                    this.watchTicks = 0;
                }
            }
        }
    }

    public void getMouseOver(float par1) {
        if (Minecraft.func_71410_x().field_71451_h != null && Minecraft.func_71410_x().field_71441_e != null) {
            this.pointedEntityLiving = null;
            double d0 = 24.0;
            this.objectMouseOver = Minecraft.func_71410_x().field_71451_h.func_70614_a(d0, par1);
            double d1 = d0;
            Vec3 vec3 = Minecraft.func_71410_x().field_71451_h.func_70666_h(par1);
            if (this.objectMouseOver != null) {
                d1 = this.objectMouseOver.field_72307_f.func_72438_d(vec3);
            }
            Vec3 vec31 = Minecraft.func_71410_x().field_71451_h.func_70676_i(par1);
            Vec3 vec32 = vec3.func_72441_c(vec31.field_72450_a * d0, vec31.field_72448_b * d0, vec31.field_72449_c * d0);
            this.pointedEntity = null;
            float f1 = 1.0f;
            List list = Minecraft.func_71410_x().field_71441_e.func_72839_b((Entity)Minecraft.func_71410_x().field_71451_h, Minecraft.func_71410_x().field_71451_h.field_70121_D.func_72321_a(vec31.field_72450_a * d0, vec31.field_72448_b * d0, vec31.field_72449_c * d0).func_72314_b((double)f1, (double)f1, (double)f1));
            double d2 = d1;
            for (int i = 0; i < list.size(); ++i) {
                double d3;
                Entity entity = (Entity)list.get(i);
                if (!entity.func_70067_L()) continue;
                float f2 = entity.func_70111_Y();
                AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b((double)f2, (double)f2, (double)f2);
                MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3, vec32);
                if (axisalignedbb.func_72318_a(vec3)) {
                    if (!(0.0 < d2) && d2 != 0.0) continue;
                    this.pointedEntity = entity;
                    d2 = 0.0;
                    continue;
                }
                if (movingobjectposition == null || !((d3 = vec3.func_72438_d(movingobjectposition.field_72307_f)) < d2) && d2 != 0.0) continue;
                if (entity == Minecraft.func_71410_x().field_71451_h.field_70154_o && !entity.canRiderInteract()) {
                    if (d2 != 0.0) continue;
                    this.pointedEntity = entity;
                    continue;
                }
                this.pointedEntity = entity;
                d2 = d3;
            }
            if (this.pointedEntity != null && (d2 < d1 || this.objectMouseOver == null)) {
                this.objectMouseOver = new MovingObjectPosition(this.pointedEntity);
                if (this.pointedEntity instanceof EntityLivingBase) {
                    this.pointedEntityLiving = (EntityLivingBase)this.pointedEntity;
                }
            }
        }
    }
}

