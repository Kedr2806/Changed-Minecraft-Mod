package net.ltxprogrammer.changed.client.renderer.animate.upperbody;

import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.NotNull;

public class DragonUpperBodyCrouchAnimator<T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> extends AbstractUpperBodyAnimator<T, M> {
    public DragonUpperBodyCrouchAnimator(ModelPart head, ModelPart torso, ModelPart leftArm, ModelPart rightArm) {
        super(head, torso, leftArm, rightArm);
    }

    @Override
    public HumanoidAnimator.AnimateStage preferredStage() {
        return HumanoidAnimator.AnimateStage.CROUCH;
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        torso.z = -1.0f;//Mth.lerp(core.ageLerp, -1.0f, -1.5f);
        head.z = torso.z;

        torso.xRot = 0.5f;//Mth.lerp(core.ageLerp, 0.5f, 0.6f);
        rightArm.xRot += 0.3F;
        leftArm.xRot += 0.3F;

        torso.y = 3.2f/*Mth.lerp(core.ageLerp, 3.2f, 4.0f)*/ + core.calculateTorsoPositionY();
        head.y = torso.y + 0.5f;
        leftArm.y = torso.y + 2.25f;
        rightArm.y = torso.y + 2.25f;
    }
}
