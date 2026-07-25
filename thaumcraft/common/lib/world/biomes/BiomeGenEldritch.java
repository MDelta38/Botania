/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 */
package thaumcraft.common.lib.world.biomes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.EntityInhabitedZombie;

public class BiomeGenEldritch
extends BiomeGenBase {
    public BiomeGenEldritch(int p_i1990_1_) {
        super(p_i1990_1_);
        this.field_76761_J.clear();
        this.field_76762_K.clear();
        this.field_76755_L.clear();
        this.field_82914_M.clear();
        this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityInhabitedZombie.class, 1, 1, 1));
        this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityEldritchGuardian.class, 1, 1, 1));
        this.field_76752_A = Blocks.field_150346_d;
        this.field_76753_B = Blocks.field_150346_d;
        this.func_76735_a("Eldritch");
        this.func_76745_m();
    }

    @SideOnly(value=Side.CLIENT)
    public int func_76731_a(float p_76731_1_) {
        return 0;
    }

    public void func_76728_a(World world, Random random, int x, int z) {
    }

    public BiomeGenBase func_150566_k() {
        return null;
    }
}

