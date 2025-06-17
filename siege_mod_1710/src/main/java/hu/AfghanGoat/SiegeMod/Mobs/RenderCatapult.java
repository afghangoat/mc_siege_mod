package hu.AfghanGoat.SiegeMod.Mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderCatapult extends Render {

    private static final ResourceLocation texture = new ResourceLocation("siege_mod:textures/entity/catapult.png"); // Replace with your texture path
    private final ModelCatapult model;

    public RenderCatapult() {
        this.model = new ModelCatapult(); // Initialize your custom cube model
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        // Standard OpenGL calls for rendering, if required
        bindEntityTexture(entity);

        // Push and pop OpenGL transformations
        GL11.glPushMatrix();

        // Move to the entity's position
        GL11.glTranslated(x, y, z);

        // Rotate to face the correct direction
        GL11.glRotatef(entityYaw, 0.0F, 1.0F, 0.0F);

        // Scale model to fit (adjust scale as needed)
        GL11.glScalef(1.0F, 1.0F, 1.0F);

        // Render the model with scaling factor
        model.render(entity, 0.0F, 0.0F, -0.1F, entityYaw, entity.rotationPitch, 0.0625F);

        GL11.glPopMatrix();
    }
}
