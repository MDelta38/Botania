/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.ai.attributes.RangedAttribute
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package thaumcraft.common.lib.utils;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.common.entities.EntitySpecialItem;
import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.lib.utils.Utils;

public class EntityUtils {
    public static final IAttribute CHAMPION_MOD = new RangedAttribute("tc.mobmod", -2.0, -2.0, 100.0).func_111117_a("Champion modifier").func_111112_a(true);
    public static final AttributeModifier CHAMPION_HEALTH = new AttributeModifier(UUID.fromString("a62bef38-48cc-42a6-ac5e-ef913841c4fd"), "Champion health buff", 30.0, 0);
    public static final AttributeModifier CHAMPION_DAMAGE = new AttributeModifier(UUID.fromString("a340d2db-d881-4c25-ac62-f0ad14cd63b0"), "Champion damage buff", 2.0, 2);
    public static final AttributeModifier BOLDBUFF = new AttributeModifier(UUID.fromString("4b1edd33-caa9-47ae-a702-d86c05701037"), "Bold speed boost", 0.3, 1);
    public static final AttributeModifier MIGHTYBUFF = new AttributeModifier(UUID.fromString("7163897f-07f5-49b3-9ce4-b74beb83d2d3"), "Mighty damage boost", 3.0, 2);
    public static final AttributeModifier[] HPBUFF = new AttributeModifier[]{new AttributeModifier(UUID.fromString("54d621c1-dd4d-4b43-8bd2-5531c8875797"), "HEALTH BUFF 1", 50.0, 0), new AttributeModifier(UUID.fromString("f51257dc-b7fa-4f7a-92d7-75d68e8592c4"), "HEALTH BUFF 2", 50.0, 0), new AttributeModifier(UUID.fromString("3d6b2e42-4141-4364-b76d-0e8664bbd0bb"), "HEALTH BUFF 3", 50.0, 0), new AttributeModifier(UUID.fromString("02c97a08-801c-4131-afa2-1427a6151934"), "HEALTH BUFF 4", 50.0, 0), new AttributeModifier(UUID.fromString("0f354f6a-33c5-40be-93be-81b1338567f1"), "HEALTH BUFF 5", 50.0, 0)};
    public static final AttributeModifier[] DMGBUFF = new AttributeModifier[]{new AttributeModifier(UUID.fromString("534f8c57-929a-48cf-bbd6-0fd851030748"), "DAMAGE BUFF 1", 0.5, 0), new AttributeModifier(UUID.fromString("d317a76e-0e7c-4c61-acfd-9fa286053b32"), "DAMAGE BUFF 2", 0.5, 0), new AttributeModifier(UUID.fromString("ff462d63-26a2-4363-830e-143ed97e2a4f"), "DAMAGE BUFF 3", 0.5, 0), new AttributeModifier(UUID.fromString("cf1eb39e-0c67-495f-887c-0d3080828d2f"), "DAMAGE BUFF 4", 0.5, 0), new AttributeModifier(UUID.fromString("3cfab9da-2701-43d8-ac07-885f16fa4117"), "DAMAGE BUFF 5", 0.5, 0)};

    public static Entity getPointedEntity(World world, Entity entityplayer, double minrange, double range, float padding) {
        return EntityUtils.getPointedEntity(world, entityplayer, minrange, range, padding, false);
    }

    public static Entity getPointedEntity(World world, Entity entityplayer, double minrange, double range, float padding, boolean nonCollide) {
        Entity pointedEntity = null;
        double d = range;
        Vec3 vec3d = Vec3.func_72443_a((double)entityplayer.field_70165_t, (double)(entityplayer.field_70163_u + (double)entityplayer.func_70047_e()), (double)entityplayer.field_70161_v);
        Vec3 vec3d1 = entityplayer.func_70040_Z();
        Vec3 vec3d2 = vec3d.func_72441_c(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d);
        float f1 = padding;
        List list = world.func_72839_b(entityplayer, entityplayer.field_70121_D.func_72321_a(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d).func_72314_b((double)f1, (double)f1, (double)f1));
        double d2 = 0.0;
        for (int i = 0; i < list.size(); ++i) {
            double d3;
            Entity entity = (Entity)list.get(i);
            if ((double)entity.func_70032_d(entityplayer) < minrange || !entity.func_70067_L() && !nonCollide || world.func_147447_a(Vec3.func_72443_a((double)entityplayer.field_70165_t, (double)(entityplayer.field_70163_u + (double)entityplayer.func_70047_e()), (double)entityplayer.field_70161_v), Vec3.func_72443_a((double)entity.field_70165_t, (double)(entity.field_70163_u + (double)entity.func_70047_e()), (double)entity.field_70161_v), false, true, false) != null) continue;
            float f2 = Math.max(0.8f, entity.func_70111_Y());
            AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b((double)f2, (double)f2, (double)f2);
            MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3d, vec3d2);
            if (axisalignedbb.func_72318_a(vec3d)) {
                if (!(0.0 < d2) && d2 != 0.0) continue;
                pointedEntity = entity;
                d2 = 0.0;
                continue;
            }
            if (movingobjectposition == null || !((d3 = vec3d.func_72438_d(movingobjectposition.field_72307_f)) < d2) && d2 != 0.0) continue;
            pointedEntity = entity;
            d2 = d3;
        }
        return pointedEntity;
    }

    public static Entity getPointedEntity(World world, EntityPlayer entityplayer, double range, Class<?> clazz) {
        Entity pointedEntity = null;
        double d = range;
        Vec3 vec3d = Vec3.func_72443_a((double)entityplayer.field_70165_t, (double)(entityplayer.field_70163_u + (double)entityplayer.func_70047_e()), (double)entityplayer.field_70161_v);
        Vec3 vec3d1 = entityplayer.func_70040_Z();
        Vec3 vec3d2 = vec3d.func_72441_c(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d);
        float f1 = 1.1f;
        List list = world.func_72839_b((Entity)entityplayer, entityplayer.field_70121_D.func_72321_a(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d).func_72314_b((double)f1, (double)f1, (double)f1));
        double d2 = 0.0;
        for (int i = 0; i < list.size(); ++i) {
            double d3;
            Entity entity = (Entity)list.get(i);
            if (!entity.func_70067_L() || world.func_147447_a(Vec3.func_72443_a((double)entityplayer.field_70165_t, (double)(entityplayer.field_70163_u + (double)entityplayer.func_70047_e()), (double)entityplayer.field_70161_v), Vec3.func_72443_a((double)entity.field_70165_t, (double)(entity.field_70163_u + (double)entity.func_70047_e()), (double)entity.field_70161_v), false, true, false) != null || clazz.isInstance(entity)) continue;
            float f2 = Math.max(0.8f, entity.func_70111_Y());
            AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b((double)f2, (double)f2, (double)f2);
            MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3d, vec3d2);
            if (axisalignedbb.func_72318_a(vec3d)) {
                if (!(0.0 < d2) && d2 != 0.0) continue;
                pointedEntity = entity;
                d2 = 0.0;
                continue;
            }
            if (movingobjectposition == null || !((d3 = vec3d.func_72438_d(movingobjectposition.field_72307_f)) < d2) && d2 != 0.0) continue;
            pointedEntity = entity;
            d2 = d3;
        }
        return pointedEntity;
    }

    public static boolean canEntityBeSeen(Entity entity, TileEntity te) {
        return te.func_145831_w().func_72901_a(Vec3.func_72443_a((double)((double)te.field_145851_c + 0.5), (double)((double)te.field_145848_d + 1.25), (double)((double)te.field_145849_e + 0.5)), Vec3.func_72443_a((double)entity.field_70165_t, (double)entity.field_70163_u, (double)entity.field_70161_v), false) == null;
    }

    public static boolean canEntityBeSeen(Entity entity, double x, double y, double z) {
        return entity.field_70170_p.func_72901_a(Vec3.func_72443_a((double)x, (double)y, (double)z), Vec3.func_72443_a((double)entity.field_70165_t, (double)entity.field_70163_u, (double)entity.field_70161_v), false) == null;
    }

    public static boolean canEntityBeSeen(Entity entity, Entity entity2) {
        return entity.field_70170_p.func_72901_a(Vec3.func_72443_a((double)entity.field_70165_t, (double)entity.field_70163_u, (double)entity.field_70161_v), Vec3.func_72443_a((double)entity2.field_70165_t, (double)entity2.field_70163_u, (double)entity2.field_70161_v), false) == null;
    }

    public static void setRecentlyHit(EntityLivingBase ent, int hit) {
        try {
            ObfuscationReflectionHelper.setPrivateValue(EntityLivingBase.class, (Object)ent, (Object)hit, (String[])new String[]{"recentlyHit", "field_70718_bc"});
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static int getRecentlyHit(EntityLivingBase ent) {
        try {
            return (Integer)ReflectionHelper.getPrivateValue(EntityLivingBase.class, (Object)ent, (String[])new String[]{"recentlyHit", "field_70718_bc"});
        }
        catch (Exception e) {
            return 0;
        }
    }

    public static MovingObjectPosition getMovingObjectPositionFromPlayer(World par1World, EntityPlayer par2EntityPlayer, boolean par3) {
        float f = 1.0f;
        float f1 = par2EntityPlayer.field_70127_C + (par2EntityPlayer.field_70125_A - par2EntityPlayer.field_70127_C) * f;
        float f2 = par2EntityPlayer.field_70126_B + (par2EntityPlayer.field_70177_z - par2EntityPlayer.field_70126_B) * f;
        double d0 = par2EntityPlayer.field_70169_q + (par2EntityPlayer.field_70165_t - par2EntityPlayer.field_70169_q) * (double)f;
        double d1 = par2EntityPlayer.field_70167_r + (par2EntityPlayer.field_70163_u - par2EntityPlayer.field_70167_r) * (double)f + (double)(par1World.field_72995_K ? par2EntityPlayer.func_70047_e() - par2EntityPlayer.getDefaultEyeHeight() : par2EntityPlayer.func_70047_e());
        double d2 = par2EntityPlayer.field_70166_s + (par2EntityPlayer.field_70161_v - par2EntityPlayer.field_70166_s) * (double)f;
        Vec3 vec3 = Vec3.func_72443_a((double)d0, (double)d1, (double)d2);
        float f3 = MathHelper.func_76134_b((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f4 = MathHelper.func_76126_a((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f5 = -MathHelper.func_76134_b((float)(-f1 * ((float)Math.PI / 180)));
        float f6 = MathHelper.func_76126_a((float)(-f1 * ((float)Math.PI / 180)));
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0;
        if (par2EntityPlayer instanceof EntityPlayerMP) {
            d3 = ((EntityPlayerMP)par2EntityPlayer).field_71134_c.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.func_72441_c((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return par1World.func_147447_a(vec3, vec31, par3, !par3, false);
    }

    public static ArrayList<Entity> getEntitiesInRange(World world, double x, double y, double z, Entity entity, Class clazz, double range) {
        ArrayList<Entity> out = new ArrayList<Entity>();
        List list = world.func_72872_a(clazz, AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)x, (double)y, (double)z).func_72314_b(range, range, range));
        if (list.size() > 0) {
            for (Object e : list) {
                Entity ent = (Entity)e;
                if (entity != null && entity.func_145782_y() == ent.func_145782_y()) continue;
                out.add(ent);
            }
        }
        return out;
    }

    public static boolean isVisibleTo(float fov, Entity ent, Entity ent2, float range) {
        double[] x = new double[]{ent2.field_70165_t, ent2.field_70121_D.field_72338_b + (double)(ent2.field_70131_O / 2.0f), ent2.field_70161_v};
        double[] t = new double[]{ent.field_70165_t, ent.field_70121_D.field_72338_b + (double)ent.func_70047_e(), ent.field_70161_v};
        Vec3 q = ent.func_70040_Z();
        q.field_72450_a *= (double)range;
        q.field_72448_b *= (double)range;
        q.field_72449_c *= (double)range;
        Vec3 l = q.func_72441_c(ent.field_70165_t, ent.field_70121_D.field_72338_b + (double)ent.func_70047_e(), ent.field_70161_v);
        double[] b = new double[]{l.field_72450_a, l.field_72448_b, l.field_72449_c};
        return Utils.isLyingInCone(x, t, b, fov);
    }

    public static boolean isVisibleTo(float fov, Entity ent, double xx, double yy, double zz, float range) {
        double[] x = new double[]{xx, yy, zz};
        double[] t = new double[]{ent.field_70165_t, ent.field_70121_D.field_72338_b + (double)ent.func_70047_e(), ent.field_70161_v};
        Vec3 q = ent.func_70040_Z();
        q.field_72450_a *= (double)range;
        q.field_72448_b *= (double)range;
        q.field_72449_c *= (double)range;
        Vec3 l = q.func_72441_c(ent.field_70165_t, ent.field_70121_D.field_72338_b + (double)ent.func_70047_e(), ent.field_70161_v);
        double[] b = new double[]{l.field_72450_a, l.field_72448_b, l.field_72449_c};
        return Utils.isLyingInCone(x, t, b, fov);
    }

    public static EntityItem entityDropSpecialItem(Entity entity, ItemStack stack, float dropheight) {
        if (stack.field_77994_a != 0 && stack.func_77973_b() != null) {
            EntitySpecialItem entityitem = new EntitySpecialItem(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u + (double)dropheight, entity.field_70161_v, stack);
            entityitem.field_145804_b = 10;
            entityitem.field_70181_x = 0.1f;
            entityitem.field_70159_w = 0.0;
            entityitem.field_70179_y = 0.0;
            if (entity.captureDrops) {
                entity.capturedDrops.add(entityitem);
            } else {
                entity.field_70170_p.func_72838_d((Entity)entityitem);
            }
            return entityitem;
        }
        return null;
    }

    public static void makeChampion(EntityMob entity, boolean persist) {
        int type = entity.field_70170_p.field_73012_v.nextInt(ChampionModifier.mods.length);
        if (entity instanceof EntityCreeper) {
            type = 0;
        }
        IAttributeInstance modai = entity.func_110148_a(CHAMPION_MOD);
        modai.func_111124_b(ChampionModifier.mods[type].attributeMod);
        modai.func_111121_a(ChampionModifier.mods[type].attributeMod);
        if (!(entity instanceof EntityThaumcraftBoss)) {
            IAttributeInstance iattributeinstance = entity.func_110148_a(SharedMonsterAttributes.field_111267_a);
            iattributeinstance.func_111124_b(CHAMPION_HEALTH);
            iattributeinstance.func_111121_a(CHAMPION_HEALTH);
            IAttributeInstance iattributeinstance2 = entity.func_110148_a(SharedMonsterAttributes.field_111264_e);
            iattributeinstance2.func_111124_b(CHAMPION_DAMAGE);
            iattributeinstance2.func_111121_a(CHAMPION_DAMAGE);
            entity.func_70691_i(25.0f);
            entity.func_94058_c(ChampionModifier.mods[type].getModNameLocalized() + " " + entity.func_70005_c_());
        } else {
            ((EntityThaumcraftBoss)entity).generateName();
        }
        if (persist) {
            entity.func_110163_bv();
        }
        switch (type) {
            case 0: {
                IAttributeInstance sai = entity.func_110148_a(SharedMonsterAttributes.field_111263_d);
                sai.func_111124_b(BOLDBUFF);
                sai.func_111121_a(BOLDBUFF);
                break;
            }
            case 3: {
                IAttributeInstance mai = entity.func_110148_a(SharedMonsterAttributes.field_111264_e);
                mai.func_111124_b(MIGHTYBUFF);
                mai.func_111121_a(MIGHTYBUFF);
                break;
            }
            case 5: {
                int bh = (int)entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
                entity.func_110149_m(entity.func_110139_bj() + (float)bh);
            }
        }
    }
}

