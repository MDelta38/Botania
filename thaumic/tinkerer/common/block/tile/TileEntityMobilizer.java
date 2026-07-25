/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumic.tinkerer.common.block.tile;

import appeng.api.AEApi;
import appeng.api.IAppEngApi;
import appeng.api.movable.IMovableTile;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ForgeDirection;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.mobilizer.BlockMobilizer;
import thaumic.tinkerer.common.block.tile.TileEntityRelay;

public class TileEntityMobilizer
extends TileEntity {
    public boolean linked;
    public int firstRelayX;
    public int secondRelayX;
    public int firstRelayZ;
    public int secondRelayZ;
    public boolean dead = false;
    public ForgeDirection movementDirection;

    public void func_145841_b(NBTTagCompound nbt) {
        super.func_145841_b(nbt);
        nbt.func_74757_a("Linked", this.linked);
        nbt.func_74768_a("FirstRelayX", this.firstRelayX);
        nbt.func_74768_a("FirstRelayZ", this.firstRelayZ);
        nbt.func_74768_a("SecondRelayX", this.secondRelayX);
        nbt.func_74768_a("SecondRelayZ", this.secondRelayZ);
        nbt.func_74768_a("Direction", this.movementDirection != null ? this.movementDirection.ordinal() : 0);
    }

    public void func_145839_a(NBTTagCompound nbt) {
        super.func_145839_a(nbt);
        this.linked = nbt.func_74767_n("Linked");
        this.firstRelayX = nbt.func_74762_e("FirstRelayX");
        this.firstRelayZ = nbt.func_74762_e("FirstRelayZ");
        this.secondRelayX = nbt.func_74762_e("SecondRelayX");
        this.secondRelayZ = nbt.func_74762_e("SecondRelayZ");
        this.movementDirection = ForgeDirection.VALID_DIRECTIONS[nbt.func_74762_e("Direction")];
    }

    public void verifyRelay() {
        TileEntity te = this.field_145850_b.func_147438_o(this.firstRelayX, this.field_145848_d, this.firstRelayZ);
        if (te instanceof TileEntityRelay) {
            ((TileEntityRelay)te).verifyPartner();
        }
        if (!this.linked || !(te instanceof TileEntityRelay) || ((TileEntityRelay)te).partnerX != this.secondRelayX || ((TileEntityRelay)te).partnerZ != this.secondRelayZ) {
            this.linked = false;
        }
    }

    public void func_145845_h() {
        int targetZ;
        int targetX;
        if (this.dead) {
            return;
        }
        this.verifyRelay();
        if (this.linked && this.field_145850_b.func_82737_E() % 100L == 0L && !this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e) && (this.field_145850_b.func_147439_a(targetX = this.field_145851_c + this.movementDirection.offsetX, this.field_145848_d, targetZ = this.field_145849_e + this.movementDirection.offsetZ) != Block.func_149684_b((String)"air") || this.field_145850_b.func_147439_a(targetX, this.field_145848_d + 1, targetZ) != Block.func_149684_b((String)"air"))) {
            this.movementDirection = this.movementDirection.getOpposite();
        }
        if (this.linked && this.field_145850_b.func_82737_E() % 100L == 1L && !this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
            targetX = this.field_145851_c + this.movementDirection.offsetX;
            targetZ = this.field_145849_e + this.movementDirection.offsetZ;
            if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) != ThaumicTinkerer.registry.getFirstBlockFromClass(BlockMobilizer.class)) {
                return;
            }
            if ((this.field_145850_b.func_147437_c(targetX, this.field_145848_d, targetZ) || this.field_145850_b.func_147439_a(targetX, this.field_145848_d, targetZ).isAir((IBlockAccess)this.field_145850_b, targetX, this.field_145848_d, targetZ) && (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) || this.field_145850_b.func_147437_c(targetX, this.field_145848_d + 1, targetZ) || this.field_145850_b.func_147439_a(targetX, this.field_145848_d + 1, targetZ).isAir((IBlockAccess)this.field_145850_b, targetX, this.field_145848_d + 1, targetZ))) && !this.field_145850_b.field_72995_K) {
                TileEntity passenger = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
                IAppEngApi api = AEApi.instance();
                this.field_145850_b.func_147465_d(targetX, this.field_145848_d, targetZ, Block.func_149684_b((String)"stone"), 0, 0);
                Block passengerId = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
                if (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) || passengerId.func_149742_c(this.field_145850_b, targetX, this.field_145848_d + 1, targetZ)) {
                    if (passenger == null) {
                        if (passengerId != Block.func_149684_b((String)"bedrock") && passengerId != Block.func_149684_b((String)"")) {
                            this.field_145850_b.func_147465_d(targetX, this.field_145848_d + 1, targetZ, passengerId, this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e), 3);
                            if (passengerId != Block.func_149684_b((String)"air") && passengerId != Block.func_149684_b((String)"piston_head")) {
                                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, Block.func_149684_b((String)"air"), 0, 2);
                            }
                        }
                    } else if (api != null) {
                        if (api.registries().movable().askToMove(passenger)) {
                            this.field_145850_b.func_147465_d(targetX, this.field_145848_d + 1, targetZ, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e), this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e), 3);
                            passenger.func_145843_s();
                            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
                            api.registries().movable().getHandler(passenger).moveTile(passenger, this.field_145850_b, targetX, this.field_145848_d + 1, targetZ);
                            api.registries().movable().doneMoving(passenger);
                            passenger.func_145829_t();
                        }
                    } else if (passenger instanceof IMovableTile || passenger.getClass().getName().startsWith("net.minecraft.tileentity")) {
                        boolean imovable = passenger instanceof IMovableTile;
                        if (imovable) {
                            ((IMovableTile)passenger).prepareToMove();
                        }
                        this.field_145850_b.func_147465_d(targetX, this.field_145848_d + 1, targetZ, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e), this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e), 3);
                        passenger.func_145843_s();
                        this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
                        Chunk c = this.field_145850_b.func_72938_d(targetX, targetZ);
                        c.func_150812_a(targetX & 0xF, this.field_145848_d + 1, targetZ & 0xF, passenger);
                        if (c.field_76636_d) {
                            this.field_145850_b.addTileEntity(passenger);
                            this.field_145850_b.func_147471_g(targetX, this.field_145848_d + 1, targetZ);
                        }
                        if (imovable) {
                            ((IMovableTile)passenger).doneMoving();
                        }
                        passenger.func_145829_t();
                    }
                }
                this.func_145843_s();
                this.field_145850_b.func_147475_p(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, Block.func_149684_b((String)"air"), 0, 2);
                this.field_145850_b.func_147449_b(targetX, this.field_145848_d, targetZ, ThaumicTinkerer.registry.getFirstBlockFromClass(BlockMobilizer.class));
                int oldX = this.field_145851_c;
                int oldZ = this.field_145849_e;
                this.field_145851_c = targetX;
                this.field_145849_e = targetZ;
                this.func_145829_t();
                this.field_145850_b.addTileEntity((TileEntity)this);
                this.field_145850_b.func_147475_p(oldX, this.field_145848_d, oldZ);
                this.field_145850_b.func_147444_c(oldX, this.field_145848_d, oldZ, Block.func_149684_b((String)"air"));
            }
        }
    }
}

