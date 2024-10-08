package net.ltxprogrammer.changed.client.renderer.animate.arm;

import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.minecraft.client.model.AnimationUtils;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.NotNull;

public class DoubleArmBobAnimator<T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> extends HumanoidAnimator.Animator<T, M> {
    public final ModelPart upperLeftArm;
    public final ModelPart upperRightArm;
    public final ModelPart lowerLeftArm;
    public final ModelPart lowerRightArm;

    public DoubleArmBobAnimator(ModelPart upperLeftArm, ModelPart upperRightArm, ModelPart lowerLeftArm, ModelPart lowerRightArm) {
        this.upperLeftArm = upperLeftArm;
        this.upperRightArm = upperRightArm;
        this.lowerLeftArm = lowerLeftArm;
        this.lowerRightArm = lowerRightArm;
    }

    @Override
    public HumanoidAnimator.AnimateStage preferredStage() {
        return HumanoidAnimator.AnimateStage.BOB;
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (core.rightArmPose != HumanoidModel.ArmPose.SPYGLASS) {
            AnimationUtils.bobModelPart(upperRightArm, ageInTicks, 1.0F);
        }

        if (core.leftArmPose != HumanoidModel.ArmPose.SPYGLASS) {
            AnimationUtils.bobModelPart(upperLeftArm, ageInTicks, -1.0F);
        }

        AnimationUtils.bobModelPart(lowerRightArm, ageInTicks * 0.8f, -0.5F);
        AnimationUtils.bobModelPart(lowerLeftArm, ageInTicks * 0.8f, 0.5F);
    }
}
