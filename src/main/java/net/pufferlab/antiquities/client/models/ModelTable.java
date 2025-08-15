package net.pufferlab.antiquities.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.pufferlab.antiquities.Constants;

public class ModelTable extends ModelBase {

    private final ModelRenderer bb_main;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;

    public ModelRenderer side1;
    public ModelRenderer side2;
    public ModelRenderer side3;
    public ModelRenderer side4;

    public ModelRenderer top1;
    public ModelRenderer top2;
    public ModelRenderer top3;
    public ModelRenderer top4;

    Models texture = Models.TABLE;

    public ModelTable() {
        textureWidth = 128;
        textureHeight = 128;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 0.0F, 0.0F);

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg1.cubeList.add(new ModelBox(bb_main, 0, 0, 6.0F, -14.0F + 8F, -8.0F, 2, 14, 2, 0.0F));
        bb_main.addChild(leg1);

        leg2 = new ModelRenderer(this);
        leg2.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg2.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -14.0F + 8F, -8.0F, 2, 14, 2, 0.0F));
        bb_main.addChild(leg2);

        leg3 = new ModelRenderer(this);
        leg3.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg3.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -14.0F + 8F, 6.0F, 2, 14, 2, 0.0F));
        bb_main.addChild(leg3);

        leg4 = new ModelRenderer(this);
        leg4.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg4.cubeList.add(new ModelBox(bb_main, 0, 0, 6.0F, -14.0F + 8F, 6.0F, 2, 14, 2, 0.0F));
        bb_main.addChild(leg4);

        side1 = new ModelRenderer(this);
        side1.setRotationPoint(0.0F, 0.0F, 0.0F);
        side1.cubeList.add(new ModelBox(bb_main, 0, 41, -6.0F, -14.0F + 8F, -7.0F, 14, 2, 0, 0.0F));
        bb_main.addChild(side1);

        side2 = new ModelRenderer(this);
        side2.setRotationPoint(0.0F, 0.0F, 0.0F);
        side2.cubeList.add(new ModelBox(bb_main, 0, 41, -7.0F, -14.0F + 8F, -6.0F, 0, 2, 14, 0.0F));
        bb_main.addChild(side2);

        side3 = new ModelRenderer(this);
        side3.setRotationPoint(0.0F, 0.0F, 0.0F);
        side3.cubeList.add(new ModelBox(bb_main, 0, 41, -6.0F, -14.0F + 8F, 7.0F, 14, 2, 0, 0.0F));
        bb_main.addChild(side3);

        side4 = new ModelRenderer(this);
        side4.setRotationPoint(0.0F, 0.0F, 0.0F);
        side4.cubeList.add(new ModelBox(bb_main, 0, 41, 7.0F, -14.0F + 8F, -6.0F, 0, 2, 14, 0.0F));
        bb_main.addChild(side4);

        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -15.99F + 8F, -8.0F, 16, 2, 16, 0.0F));

        top1 = new ModelRenderer(this);
        top1.setRotationPoint(0.0F, 0.0F, 0.0F);
        top1.cubeList.add(new ModelBox(bb_main, 0, 18, 8.0F, -15.99F + 8F, -9.0F, 1, 2, 17, 0.0F));
        bb_main.addChild(top1);

        top2 = new ModelRenderer(this);
        top2.setRotationPoint(0.0F, 0.0F, 0.0F);
        top2.cubeList.add(new ModelBox(bb_main, 36, 18, -9.0F, -15.99F + 8F, -8.0F, 1, 2, 17, 0.0F));
        bb_main.addChild(top2);

        top3 = new ModelRenderer(this);
        top3.setRotationPoint(0.0F, 0.0F, 0.0F);
        top3.cubeList.add(new ModelBox(bb_main, 0, 37, -9.0F, -15.99F + 8F, -9.0F, 17, 2, 1, 0.0F));
        bb_main.addChild(top3);

        top4 = new ModelRenderer(this);
        top4.setRotationPoint(0.0F, 0.0F, 0.0F);
        top4.cubeList.add(new ModelBox(bb_main, 36, 37, -8.0F, -15.99F + 8F, 8.0F, 17, 2, 1, 0.0F));
        bb_main.addChild(top4);
    }

    public void render() {
        texture.bind();
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        bb_main.render(Constants.ModelConstant);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
