package net.vulpixass.headowl.client.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.vulpixass.headowl.client.model.owl;
import net.vulpixass.headowl.command.client.OwlTypesEnum;

import static net.vulpixass.headowl.command.client.ClientCommands.FoxType;

public class OwlHatFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    private long nextTwitchTime = 0;
    private float twitchAmount = 0;
    private final owl owlModel;
    public static int randomSoundTimer = 0;

    private FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context;

    private static Identifier OWL_TEXTURE = Identifier.of("headowl", "textures/entity/regular_owl.png");

    public OwlHatFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
        ModelPart part = owl.getTexturedModelData().createModel();
        this.context = context;
        this.owlModel = new owl(part);
        System.out.println("Owl model children: " + context.getModel().getParts());
        System.out.println("Owl texture: " + OWL_TEXTURE);

    }
    @Override
    public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, PlayerEntityRenderState state, float limbAngle, float limbDistance) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity me = client.player;
        owl owl = this.owlModel;
        PlayerEntity target = (PlayerEntity) client.world.getEntityById(state.id);
        switch (FoxType) {
            case OwlTypesEnum.REGULAR: OWL_TEXTURE = Identifier.of("headowl", "textures/entity/regular_owl.png"); break;
        }
        if (me == null) return;
        if (target == null || !target.getUuid().equals(me.getUuid())) {return;}
        ItemStack helmet = me.getEquippedStack(EquipmentSlot.HEAD);
        boolean hasHelmet = !helmet.isEmpty();
        matrices.push();
        this.getContextModel().head.applyTransform(matrices);
        matrices.translate(-0.0152F, -0.475F, -0.1152F);
        matrices.scale(0.5F, 0.5F, 0.5F);
        long time = me.age;
        if (time >= nextTwitchTime) {
            twitchAmount = 0.3F;
            nextTwitchTime = (time + 20 + client.world.random.nextInt(10))*20;
        }
        twitchAmount *= 0.8F;
        float breathe = 1.0F + (float)(Math.sin(time * 0.1F) * 0.025F);
        owlModel.bb_main.xScale = breathe;
        owlModel.bb_main.yScale = breathe;
        owlModel.bb_main.zScale = breathe;
        float growth = (breathe - 1.0F);
        if (hasHelmet) {matrices.translate(0.0, -0.098, 0.0);}
        matrices.scale(breathe, breathe, breathe);
        matrices.translate(0.0F, -growth * 0.6F, 0.2F);

        owlModel.head.yaw = twitchAmount * (client.world.random.nextBoolean() ? 1 : -1);
        owlModel.head.pitch = twitchAmount * (client.world.random.nextBoolean() ? 1 : -1);
        float sway = (float)Math.sin(time * (2 * Math.PI / 200.0)) * 0.10F;
        sway = Math.max(-0.15F, Math.min(0.15F, sway));
        owlModel.head.roll = sway;
        //Render Fox
        owl.head.resetTransform();
        int finalLight = (light == 0) ? 15728880 : light;
        queue.submitModel(owlModel, state, matrices, RenderLayer.getEntityTranslucent(OWL_TEXTURE), finalLight, OverlayTexture.DEFAULT_UV, 0, null);
        matrices.pop();
    }
}
