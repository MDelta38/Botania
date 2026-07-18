/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.tileentity.TileEntity
 */
package vazkii.botania.common.block.tile;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.Vector3;

public class TilePylon
extends TileEntity {
    boolean activated = false;
    int centerX;
    int centerY;
    int centerZ;
    int ticks = 0;

    public void func_145845_h() {
        ++this.ticks;
        int meta = this.func_145832_p();
        if (this.activated && this.field_145850_b.field_72995_K) {
            if (this.field_145850_b.func_147439_a(this.centerX, this.centerY, this.centerZ) != this.getBlockForMeta() || meta != 0 && this.field_145850_b.func_72805_g(this.centerX, this.centerY, this.centerZ) == 0) {
                this.activated = false;
                return;
            }
            Vector3 centerBlock = new Vector3((double)this.centerX + 0.5, (double)this.centerY + 0.75 + (Math.random() - 0.125), (double)this.centerZ + 0.5);
            if (meta == 1) {
                if (ConfigHandler.elfPortalParticlesEnabled) {
                    double worldTime = this.ticks;
                    worldTime += (double)new Random(this.field_145851_c ^ this.field_145848_d ^ this.field_145849_e).nextInt(1000);
                    float r = 0.75f + (float)Math.random() * 0.05f;
                    double x = (double)this.field_145851_c + 0.5 + Math.cos(worldTime /= 5.0) * (double)r;
                    double z = (double)this.field_145849_e + 0.5 + Math.sin(worldTime) * (double)r;
                    Vector3 ourCoords = new Vector3(x, (double)this.field_145848_d + 0.25, z);
                    centerBlock.sub(new Vector3(0.0, 0.5, 0.0));
                    Vector3 movementVector = centerBlock.sub(ourCoords).normalize().multiply(0.2);
                    Botania.proxy.wispFX(this.field_145850_b, x, (double)this.field_145848_d + 0.25, z, (float)Math.random() * 0.25f, 0.75f + (float)Math.random() * 0.25f, (float)Math.random() * 0.25f, 0.25f + (float)Math.random() * 0.1f, -0.075f - (float)Math.random() * 0.015f);
                    if (this.field_145850_b.field_73012_v.nextInt(3) == 0) {
                        Botania.proxy.wispFX(this.field_145850_b, x, (double)this.field_145848_d + 0.25, z, (float)Math.random() * 0.25f, 0.75f + (float)Math.random() * 0.25f, (float)Math.random() * 0.25f, 0.25f + (float)Math.random() * 0.1f, (float)movementVector.x, (float)movementVector.y, (float)movementVector.z);
                    }
                }
            } else {
                Vector3 ourCoords = Vector3.fromTileEntityCenter(this).add(0.0, 1.0 + (Math.random() - 0.125), 0.0);
                Vector3 movementVector = centerBlock.sub(ourCoords).normalize().multiply(0.2);
                Block block = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
                if (block == ModBlocks.flower || block == ModBlocks.shinyFlower) {
                    int fmeta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
                    float[] color = EntitySheep.field_70898_d[fmeta];
                    if (this.field_145850_b.field_73012_v.nextInt(4) == 0) {
                        Botania.proxy.sparkleFX(this.field_145850_b, centerBlock.x + (Math.random() - 0.5) * 0.5, centerBlock.y, centerBlock.z + (Math.random() - 0.5) * 0.5, color[0], color[1], color[2], (float)Math.random(), 8);
                    }
                    Botania.proxy.wispFX(this.field_145850_b, (double)this.field_145851_c + 0.5 + (Math.random() - 0.5) * 0.25, (double)this.field_145848_d - 0.5, (double)this.field_145849_e + 0.5 + (Math.random() - 0.5) * 0.25, color[0], color[1], color[2], (float)Math.random() / 3.0f, -0.04f);
                    Botania.proxy.wispFX(this.field_145850_b, (double)this.field_145851_c + 0.5 + (Math.random() - 0.5) * 0.125, (double)this.field_145848_d + 1.5, (double)this.field_145849_e + 0.5 + (Math.random() - 0.5) * 0.125, color[0], color[1], color[2], (float)Math.random() / 5.0f, -0.001f);
                    Botania.proxy.wispFX(this.field_145850_b, (double)this.field_145851_c + 0.5 + (Math.random() - 0.5) * 0.25, (double)this.field_145848_d + 1.5, (double)this.field_145849_e + 0.5 + (Math.random() - 0.5) * 0.25, color[0], color[1], color[2], (float)Math.random() / 8.0f, (float)movementVector.x, (float)movementVector.y, (float)movementVector.z);
                }
            }
        }
        if (this.field_145850_b.field_73012_v.nextBoolean() && this.field_145850_b.field_72995_K) {
            Botania.proxy.sparkleFX(this.field_145850_b, (double)this.field_145851_c + Math.random(), (double)this.field_145848_d + Math.random() * 1.5, (double)this.field_145849_e + Math.random(), meta == 2 ? 1.0f : 0.5f, meta == 1 ? 1.0f : 0.5f, meta == 1 ? 0.5f : 1.0f, (float)Math.random(), 2);
        }
    }

    private Block getBlockForMeta() {
        return this.func_145832_p() == 0 ? ModBlocks.enchanter : ModBlocks.alfPortal;
    }
}

