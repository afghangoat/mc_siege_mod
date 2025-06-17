package hu.AfghanGoat.SiegeMod.Mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCatapult extends ModelBase { //RenderBiped is player shape
    private final ModelRenderer wheel1;
    private final ModelRenderer wheel2;
    private final ModelRenderer wheel3;
    private final ModelRenderer wheel4;
    private final ModelRenderer plank1;
    private final ModelRenderer plank2;
    private final ModelRenderer plank3;
    private final ModelRenderer plank4;
    private final ModelRenderer plank5;
    private final ModelRenderer plank6;
    private final ModelRenderer stonea;

    public ModelCatapult() {
        this.textureWidth=128;
        this.textureHeight=128;

        this.wheel1 = new ModelRenderer(this, 0, 0);
        this.wheel1.addBox(-12.0F, 2.0F, -20.0F, 12, 12, 12);
        this.wheel1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wheel1.setTextureOffset(0, 0);

        this.wheel2 = new ModelRenderer(this, 0, 0);
        this.wheel2.addBox(-12.0F, 2.0F, 20.0F, 12, 12, 12);
        this.wheel2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wheel2.setTextureOffset(0, 0);

        this.wheel3 = new ModelRenderer(this, 0, 0);
        this.wheel3.addBox(6.0F, 2.0F, -20.0F, 12, 12, 12);
        this.wheel3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wheel3.setTextureOffset(0, 0);

        this.wheel4 = new ModelRenderer(this, 0, 0);
        this.wheel4.addBox(6.0F, 2.0F, 20.0F, 12, 12, 12);
        this.wheel4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wheel4.setTextureOffset(0, 0);

        this.plank1 = new ModelRenderer(this, 64, 1);
        this.plank1.addBox(-2.0F, 5.0F, -18.0F, 10, 8, 42);
        this.plank1.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.plank2 = new ModelRenderer(this, 64, 0);
        this.plank2.addBox(-4.0F, -8.0F, 0.0F, 6, 30, 5);
        this.plank2.setRotationPoint(-3.0F, 15.0F, 0.0F);

        this.plank3 = new ModelRenderer(this, 64, 0);
        this.plank3.addBox(-2.0F, -8.0F, 0.0F, 6, 30, 5);
        this.plank3.setRotationPoint(9.0F, 15.0F, 0.0F);

        this.plank4 = new ModelRenderer(this, 64, 0);
        this.plank4.addBox(-3.9F, -10.0F, -20.0F, 6, 30, 5);
        this.plank4.setRotationPoint(-3.0F, 15.0F, 5.0F);
        setRotation(this.plank4,1.04f,0.0f,0.0f);

        this.plank5 = new ModelRenderer(this, 64, 0);
        this.plank5.addBox(-2.1F, -10.0F, -20.0F, 6, 30, 5);
        this.plank5.setRotationPoint(9.0F, 15.0F, 5.0F);
        setRotation(this.plank5,1.04f,0.0f,0.0f);

        this.plank6 = new ModelRenderer(this, 64, 0);
        this.plank6.addBox(-8F, -20.0F, -10.0F, 6, 40, 5);
        this.plank6.setRotationPoint(9.0F, 15.0F, 5.0F);
        setRotation(this.plank6,+0.8f,0.0f,0.0f);

        this.stonea = new ModelRenderer(this, 0, 32);
        this.stonea.addBox(-10F, 6.0F, -27.0F, 10, 10, 10);
        this.stonea.setRotationPoint(9.0F, 15.0F, 20.0F);
        setRotation(this.stonea,+0.8f,0.0f,0.0f);
    }

    private void setRotation(ModelRenderer mbase, float x, float y, float z){
        mbase.rotateAngleX=x;
        mbase.rotateAngleY=y;
        mbase.rotateAngleZ=z;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        wheel1.render(f5);
        wheel2.render(f5);
        wheel3.render(f5);
        wheel4.render(f5);
        plank1.render(f5);
        plank2.render(f5);
        plank3.render(f5);
        plank4.render(f5);
        plank5.render(f5);
        plank6.render(f5);
        stonea.render(f5);
    }
}
