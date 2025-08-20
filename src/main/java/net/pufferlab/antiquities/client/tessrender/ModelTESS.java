package net.pufferlab.antiquities.client.tessrender;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;

public class ModelTESS {

    public static void render(RenderBlocks renderblocks, Tessellator tess, Block block, ModelRenderer renderer,
        float scale, int x, int y, int z, int meta) {
        if (!renderer.isHidden && renderer.showModel) {

            // Render children first
            if (renderer.childModels != null) {
                for (int i = 0; i < renderer.childModels.size(); ++i) {
                    render(
                        renderblocks,
                        tess,
                        block,
                        (ModelRenderer) renderer.childModels.get(i),
                        scale,
                        x,
                        y,
                        z,
                        meta);
                }
            }

            if (renderer.cubeList != null) {
                for (int i = 0; i < renderer.cubeList.size(); ++i) {
                    ModelBox box = renderer.cubeList.get(i);

                    for (int j = 0; j < box.quadList.length; ++j) {
                        TexturedQuad quad = box.quadList[j];

                        // --- Base normal (unrotated) ---
                        Vec3 v1 = quad.vertexPositions[1].vector3D.subtract(quad.vertexPositions[0].vector3D);
                        Vec3 v2 = quad.vertexPositions[1].vector3D.subtract(quad.vertexPositions[2].vector3D);
                        Vec3 normal = v2.crossProduct(v1)
                            .normalize();

                        // --- Block brightness + color ---
                        tess.setBrightness(block.getMixedBrightnessForBlock(renderblocks.blockAccess, x, y, z));
                        int i1 = block.colorMultiplier(renderblocks.blockAccess, x, y, z);
                        float f = (float) (i1 >> 16 & 255) / 255.0F;
                        float f1 = (float) (i1 >> 8 & 255) / 255.0F;
                        float f2 = (float) (i1 & 255) / 255.0F;

                        if (EntityRenderer.anaglyphEnable) {
                            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
                            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
                            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
                            f = f3;
                            f1 = f4;
                            f2 = f5;
                        }

                        // --- Rotation angles (cos/sin) ---
                        double cosX = Math.cos(renderer.rotateAngleX), sinX = Math.sin(renderer.rotateAngleX);
                        double cosY = Math.cos(renderer.rotateAngleY), sinY = Math.sin(renderer.rotateAngleY);
                        double cosZ = Math.cos(renderer.rotateAngleZ), sinZ = Math.sin(renderer.rotateAngleZ);

                        // --- Rotate the normal ---
                        double nx = normal.xCoord, ny = normal.yCoord, nz = normal.zCoord;

                        double ny1 = ny * cosX - nz * sinX;
                        double nz1 = ny * sinX + nz * cosX;

                        double nx2 = nx * cosY + nz1 * sinY;
                        double nz2 = -nx * sinY + nz1 * cosY;

                        double nx3 = nx2 * cosZ - ny1 * sinZ;
                        double ny3 = nx2 * sinZ + ny1 * cosZ;

                        double len = Math.sqrt(nx3 * nx3 + ny3 * ny3 + nz2 * nz2);
                        if (len > 0) {
                            nx3 /= len;
                            ny3 /= len;
                            nz2 /= len;
                        }

                        tess.setNormal((float) nx3, (float) ny3, (float) nz2);

                        // --- Shading based on rotated normal ---
                        float shade = 1.0F;
                        if (ny3 > 0.5F) shade = 1.0F; // top
                        else if (ny3 < -0.5F) shade = 0.5F; // bottom
                        else if (nx3 > 0.5F || nx3 < -0.5F) shade = 0.6F; // east/west
                        else if (nz2 > 0.5F || nz2 < -0.5F) shade = 0.8F; // north/south

                        tess.setColorOpaque_F(f * shade, f1 * shade, f2 * shade);

                        // --- Texture ---
                        IIcon icon = block.getIcon(99, meta);

                        // --- Add rotated vertices ---
                        for (int p = 0; p < 4; ++p) {
                            PositionTextureVertex pos = quad.vertexPositions[p];
                            double u = icon.getMinU() + pos.texturePositionX * (icon.getMaxU() - icon.getMinU());
                            double v = icon.getMinV() + pos.texturePositionY * (icon.getMaxV() - icon.getMinV());

                            double px = pos.vector3D.xCoord * scale;
                            double py = pos.vector3D.yCoord * scale;
                            double pz = pos.vector3D.zCoord * scale;

                            // --- Translate into pivot space ---
                            px -= renderer.rotationPointX * scale;
                            py -= renderer.rotationPointY * scale;
                            pz -= renderer.rotationPointZ * scale;

                            // --- Rotate vertex same as normal ---
                            double y1 = py * cosX - pz * sinX;
                            double z1 = py * sinX + pz * cosX;

                            double x2 = px * cosY + z1 * sinY;
                            double z2v = -px * sinY + z1 * cosY;

                            double x3 = x2 * cosZ - y1 * sinZ;
                            double y3 = x2 * sinZ + y1 * cosZ;

                            // --- Translate back from pivot ---
                            x3 += renderer.rotationPointX * scale;
                            y3 += renderer.rotationPointY * scale;
                            z2v += renderer.rotationPointZ * scale;

                            // --- Apply world offset ---
                            double vx = x3 + x + 0.5;
                            double vy = y3 + y + 0.5;
                            double vz = z2v + z + 0.5;

                            tess.addVertexWithUV(vx, vy, vz, u, v);
                        }
                    }
                }
            }
        }
    }
}
