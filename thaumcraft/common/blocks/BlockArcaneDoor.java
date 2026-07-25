/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.IconFlipped
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.tiles.TileOwned;

public class BlockArcaneDoor
extends BlockContainer {
    public IIcon[] icon;

    public BlockArcaneDoor() {
        super(Material.field_151573_f);
        this.func_149672_a(field_149777_j);
        this.func_149649_H();
        this.func_149752_b(999.0f);
        this.func_149711_c(Config.wardedStone ? -1.0f : 15.0f);
        float var3 = 0.5f;
        float var4 = 1.0f;
        this.func_149676_a(0.5f - var3, 0.0f, 0.5f - var3, 0.5f + var3, var4, 0.5f + var3);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = new IIcon[4];
        this.icon[0] = ir.func_94245_a("thaumcraft:adoorbot");
        this.icon[1] = ir.func_94245_a("thaumcraft:adoortop");
        this.icon[2] = new IconFlipped(this.icon[0], true, false);
        this.icon[3] = new IconFlipped(this.icon[1], true, false);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return this.icon[1];
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        if (par5 != 0 && par5 != 1) {
            boolean flag2;
            int i1 = this.getFullMetadata(par1IBlockAccess, par2, par3, par4);
            int j1 = i1 & 3;
            boolean flag = (i1 & 4) != 0;
            boolean flag1 = false;
            boolean bl = flag2 = (i1 & 8) != 0;
            if (flag) {
                if (j1 == 0 && par5 == 2) {
                    flag1 = !flag1;
                } else if (j1 == 1 && par5 == 5) {
                    flag1 = !flag1;
                } else if (j1 == 2 && par5 == 3) {
                    flag1 = !flag1;
                } else if (j1 == 3 && par5 == 4) {
                    flag1 = !flag1;
                }
            } else {
                if (j1 == 0 && par5 == 5) {
                    flag1 = !flag1;
                } else if (j1 == 1 && par5 == 3) {
                    flag1 = !flag1;
                } else if (j1 == 2 && par5 == 4) {
                    flag1 = !flag1;
                } else if (j1 == 3 && par5 == 2) {
                    boolean bl2 = flag1 = !flag1;
                }
                if ((i1 & 0x10) != 0) {
                    flag1 = !flag1;
                }
            }
            return this.icon[(flag1 ? 2 : 0) + (flag2 ? 1 : 0)];
        }
        return this.icon[0];
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int var5 = this.getFullMetadata(par1IBlockAccess, par2, par3, par4);
        return (var5 & 4) != 0;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return 7;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
        this.func_149719_a((IBlockAccess)par1World, par2, par3, par4);
        return super.func_149633_g(par1World, par2, par3, par4);
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        this.func_149719_a((IBlockAccess)par1World, par2, par3, par4);
        return super.func_149668_a(par1World, par2, par3, par4);
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        this.setDoorRotation(this.getFullMetadata(par1IBlockAccess, par2, par3, par4));
    }

    public int getDoorOrientation(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        return this.getFullMetadata(par1IBlockAccess, par2, par3, par4) & 3;
    }

    public boolean isDoorOpen(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        return (this.getFullMetadata(par1IBlockAccess, par2, par3, par4) & 4) != 0;
    }

    private void setDoorRotation(int par1) {
        boolean var5;
        float var2 = 0.1875f;
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f);
        int var3 = par1 & 3;
        boolean var4 = (par1 & 4) != 0;
        boolean bl = var5 = (par1 & 0x10) != 0;
        if (var3 == 0) {
            if (var4) {
                if (!var5) {
                    this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, var2);
                } else {
                    this.func_149676_a(0.0f, 0.0f, 1.0f - var2, 1.0f, 1.0f, 1.0f);
                }
            } else {
                this.func_149676_a(0.0f, 0.0f, 0.0f, var2, 1.0f, 1.0f);
            }
        } else if (var3 == 1) {
            if (var4) {
                if (!var5) {
                    this.func_149676_a(1.0f - var2, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                } else {
                    this.func_149676_a(0.0f, 0.0f, 0.0f, var2, 1.0f, 1.0f);
                }
            } else {
                this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, var2);
            }
        } else if (var3 == 2) {
            if (var4) {
                if (!var5) {
                    this.func_149676_a(0.0f, 0.0f, 1.0f - var2, 1.0f, 1.0f, 1.0f);
                } else {
                    this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, var2);
                }
            } else {
                this.func_149676_a(1.0f - var2, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            }
        } else if (var3 == 3) {
            if (var4) {
                if (!var5) {
                    this.func_149676_a(0.0f, 0.0f, 0.0f, var2, 1.0f, 1.0f);
                } else {
                    this.func_149676_a(1.0f - var2, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                }
            } else {
                this.func_149676_a(0.0f, 0.0f, 1.0f - var2, 1.0f, 1.0f, 1.0f);
            }
        }
    }

    public void func_149699_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
    }

    public boolean func_149727_a(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9) {
        TileEntity tile;
        if (!w.field_72995_K && (tile = w.func_147438_o(x, y, z)) != null && tile instanceof TileOwned) {
            if (p.func_70005_c_().equals(((TileOwned)tile).owner) || ((TileOwned)tile).accessList.contains("0" + p.func_70005_c_()) || ((TileOwned)tile).accessList.contains("1" + p.func_70005_c_())) {
                int var10 = this.getFullMetadata((IBlockAccess)w, x, y, z);
                int var11 = var10 & 7;
                var11 ^= 4;
                if ((var10 & 8) == 0) {
                    w.func_72921_c(x, y, z, var11, 2);
                    w.func_147458_c(x, y, z, x, y, z);
                    this.playDoorSound(w, x, y, z);
                } else {
                    w.func_72921_c(x, y - 1, z, var11, 2);
                    w.func_147458_c(x, y - 1, z, x, y, z);
                    this.playDoorSound(w, x, y, z);
                }
            } else {
                p.func_145747_a((IChatComponent)new ChatComponentTranslation("The door refuses to budge.", new Object[0]));
                w.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:doorfail", 0.66f, 1.0f);
            }
        }
        return true;
    }

    private void playDoorSound(World w, int x, int y, int z) {
        if (Math.random() < 0.5) {
            w.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.door_open", 1.0f, w.field_73012_v.nextFloat() * 0.1f + 0.9f);
        } else {
            w.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.door_close", 1.0f, w.field_73012_v.nextFloat() * 0.1f + 0.9f);
        }
    }

    public void onPoweredBlockChange(World par1World, int par2, int par3, int par4, boolean par5) {
        boolean var7;
        int var6 = this.getFullMetadata((IBlockAccess)par1World, par2, par3, par4);
        boolean bl = var7 = (var6 & 4) != 0;
        if (var7 != par5) {
            int var8 = var6 & 7;
            var8 ^= 4;
            if ((var6 & 8) == 0) {
                par1World.func_72921_c(par2, par3, par4, var8, 2);
                par1World.func_147458_c(par2, par3, par4, par2, par3, par4);
            } else {
                par1World.func_72921_c(par2, par3 - 1, par4, var8, 2);
                par1World.func_147458_c(par2, par3 - 1, par4, par2, par3, par4);
            }
            par1World.func_72889_a((EntityPlayer)null, 1003, par2, par3, par4, 0);
        }
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        int var6 = par1World.func_72805_g(par2, par3, par4);
        if (par5 == ConfigBlocks.blockWoodenDevice) {
            ArrayList<String> users = new ArrayList<String>();
            TileEntity tile = par1World.func_147438_o(par2, par3, par4);
            if (tile != null && tile instanceof TileOwned) {
                users.add(((TileOwned)tile).owner);
                for (String u : ((TileOwned)tile).accessList) {
                    users.add(u.substring(1));
                }
            }
            int open = 0;
            block1: for (int a = 2; a <= 5; ++a) {
                TileOwned to;
                ForgeDirection dir = ForgeDirection.getOrientation((int)a);
                Block bi = par1World.func_147439_a(par2 + dir.offsetX, par3 + dir.offsetY, par4 + dir.offsetZ);
                int md = par1World.func_72805_g(par2 + dir.offsetX, par3 + dir.offsetY, par4 + dir.offsetZ);
                if (bi == ConfigBlocks.blockWoodenDevice && md == 3) {
                    to = (TileOwned)par1World.func_147438_o(par2 + dir.offsetX, par3 + dir.offsetY, par4 + dir.offsetZ);
                    if (to == null || !(to instanceof TileOwned)) continue;
                    for (String u : users) {
                        if (!to.owner.equals(u) && !to.accessList.contains(u)) continue;
                        open = 1;
                        break block1;
                    }
                    continue;
                }
                if (bi != ConfigBlocks.blockWoodenDevice || md != 2 || (to = (TileOwned)par1World.func_147438_o(par2 + dir.offsetX, par3 + dir.offsetY, par4 + dir.offsetZ)) == null || !(to instanceof TileOwned)) continue;
                for (String u : users) {
                    if (!to.owner.equals(u) && !to.accessList.contains(u)) continue;
                    open = -1;
                    continue block1;
                }
            }
            if (open != 0) {
                this.onPoweredBlockChange(par1World, par2, par3, par4, open == 1);
            }
        } else if ((var6 & 8) == 0) {
            boolean var7 = false;
            if (par1World.func_147439_a(par2, par3 + 1, par4) != this) {
                par1World.func_147468_f(par2, par3, par4);
                var7 = true;
            }
            if (var7 && !par1World.field_72995_K) {
                this.func_149697_b(par1World, par2, par3, par4, var6, 0);
            }
        } else {
            if (par1World.func_147439_a(par2, par3 - 1, par4) != this) {
                par1World.func_147468_f(par2, par3, par4);
            }
            if (par5 != Blocks.field_150350_a && par5 != this) {
                this.func_149695_a(par1World, par2, par3 - 1, par4, par5);
            }
        }
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Config.wardedStone ? Item.func_150899_d((int)0) : ((par1 & 8) != 0 ? Item.func_150899_d((int)0) : ConfigItems.itemArcaneDoor);
    }

    public MovingObjectPosition func_149731_a(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3) {
        this.func_149719_a((IBlockAccess)par1World, par2, par3, par4);
        return super.func_149731_a(par1World, par2, par3, par4, par5Vec3, par6Vec3);
    }

    public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
        return par3 >= 255 ? false : World.func_147466_a((IBlockAccess)par1World, (int)par2, (int)(par3 - 1), (int)par4) && super.func_149742_c(par1World, par2, par3, par4) && super.func_149742_c(par1World, par2, par3 + 1, par4);
    }

    public int func_149656_h() {
        return 2;
    }

    public int getFullMetadata(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int var8;
        int var7;
        boolean var6;
        int var5 = par1IBlockAccess.func_72805_g(par2, par3, par4);
        boolean bl = var6 = (var5 & 8) != 0;
        if (var6) {
            var7 = par1IBlockAccess.func_72805_g(par2, par3 - 1, par4);
            var8 = var5;
        } else {
            var7 = var5;
            var8 = par1IBlockAccess.func_72805_g(par2, par3 + 1, par4);
        }
        boolean var9 = (var8 & 1) != 0;
        return var7 & 7 | (var6 ? 8 : 0) | (var9 ? 16 : 0);
    }

    public TileEntity func_149915_a(World var1, int m) {
        return new TileOwned();
    }

    public boolean canHarvestBlock(EntityPlayer player, int meta) {
        return true;
    }

    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return false;
    }

    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
    }
}

