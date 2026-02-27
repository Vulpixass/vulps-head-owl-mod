package net.vulpixass.headowl.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class owl extends EntityModel<EntityRenderState> {
	public final ModelPart bb_main;
	public final ModelPart root;
	public final ModelPart head;
	public owl(ModelPart root) {
		super (root);
		this.root = root;
		this.bb_main = root.getChild("bb_main");
		this.head = root.getChild("head_r1");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0, 0, 0));

		ModelPartData upperLeftWing_r1 = bb_main.addChild("upperLeftWing_r1", ModelPartBuilder.create().uv(6, 18).cuboid(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 18).cuboid(2.6F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.3F, -3.0F, 0.1F, 0.1745F, 0.0F, 0.0F));

		ModelPartData lowerLeftWing_r1 = bb_main.addChild("lowerLeftWing_r1", ModelPartBuilder.create().uv(12, 19).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 19).cuboid(2.6F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.3F, -1.1F, 0.4F, 0.1745F, 0.0F, 0.0F));

		ModelPartData BackFeathers_r1 = bb_main.addChild("BackFeathers_r1", ModelPartBuilder.create().uv(16, 18).cuboid(-1.0F, -5.0F, -1.0F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.7F, 2.9F, 0.2443F, 0.0F, 0.0F));

		ModelPartData head_r1 = modelPartData.addChild("head_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.2F, -5.3F, -0.7F, 0.5436F, -0.7289F, -0.3592F));

		ModelPartData Torso_r1 = bb_main.addChild("Torso_r1", ModelPartBuilder.create().uv(0, 5).cuboid(-1.0F, -5.0F, -2.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, 1.5F, 0.1745F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(EntityRenderState state) {}
}