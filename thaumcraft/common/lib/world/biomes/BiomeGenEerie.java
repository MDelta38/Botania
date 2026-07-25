/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntityWitch
 *  net.minecraft.entity.passive.EntityBat
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 */
package thaumcraft.common.lib.world.biomes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.world.biome.BiomeGenBase;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.EntityBrainyZombie;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
import thaumcraft.common.entities.monster.EntityWisp;

public class BiomeGenEerie
extends BiomeGenBase {
    public BiomeGenEerie(int par1) {
        super(par1);
        this.field_76762_K.clear();
        this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 3, 1, 1));
        this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 8, 1, 1));
        this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 4, 1, 1));
        if (Config.spawnAngryZombie) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityBrainyZombie.class, 32, 1, 1));
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityGiantBrainyZombie.class, 8, 1, 1));
        }
        if (Config.spawnWisp) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityWisp.class, 3, 1, 1));
        }
        if (Config.spawnElder) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityEldritchGuardian.class, 1, 1, 1));
        }
        this.field_76760_I.field_76832_z = 2;
        this.field_76760_I.field_76802_A = 1;
        this.field_76760_I.field_76803_B = 2;
        this.func_76732_a(0.5f, 0.5f);
        this.func_76735_a("Eerie");
        this.func_76739_b(0x404840);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_150558_b(int x, int y, int z) {
        return 0x404840;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_150571_c(int x, int y, int z) {
        return 4215616;
    }

    public int func_76731_a(float par1) {
        return 0x222299;
    }

    public int getWaterColorMultiplier() {
        return 3035999;
    }

    public BiomeGenBase func_150566_k() {
        return null;
    }
}

