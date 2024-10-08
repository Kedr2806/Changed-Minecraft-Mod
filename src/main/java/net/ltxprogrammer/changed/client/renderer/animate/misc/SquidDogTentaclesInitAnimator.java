package net.ltxprogrammer.changed.client.renderer.animate.misc;

import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.entity.SpringType;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SquidDogTentaclesInitAnimator<T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> extends AbstractTentaclesAnimator<T, M> {
    public static final float SWAY_RATE = 0.33333334F * 0.25F;
    public static final float SWAY_SCALE = 0.025F;
    public static final float DRAG_SCALE = 0.75F;

    public SquidDogTentaclesInitAnimator(List<ModelPart> upperLeftTentacle, List<ModelPart> upperRightTentacle, List<ModelPart> lowerLeftTentacle, List<ModelPart> lowerRightTentacle) {
        super(upperLeftTentacle, upperRightTentacle, lowerLeftTentacle, lowerRightTentacle);
    }

    @Override
    public HumanoidAnimator.AnimateStage preferredStage() {
        return HumanoidAnimator.AnimateStage.INIT;
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 1.0F;
        if (entity.getFallFlyingTicks() > 4) {
            f = (float)entity.getDeltaMovement().lengthSqr();
            f /= 0.2F;
            f *= f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        float tentacleSway = SWAY_SCALE * Mth.cos(ageInTicks * SWAY_RATE + (((float)Math.PI / 3.0F) * 0.75f));
        float tentacleBalance = Mth.cos(limbSwing * 0.6662F) * 0.125F * limbSwingAmount / f;
        float tentacleDrag = entity.getTailDragAmount(this.core.partialTicks);
        float verticalDrag = entity.getSimulatedSpring(SpringType.HEAVY_NORMAL, SpringType.Direction.VERTICAL, this.core.partialTicks) * 0.30f;
        float horizontalDrag = entity.getSimulatedSpring(SpringType.HEAVY_NORMAL, SpringType.Direction.FORWARDS, this.core.partialTicks) * 0.35f;

        resetTentacle(upperLeftTentacle);
        resetTentacle(upperRightTentacle);
        resetTentacle(lowerLeftTentacle);
        resetTentacle(lowerRightTentacle);

        idleTentacle(upperLeftTentacle, limbSwingAmount, ageInTicks, tentacleSway, tentacleBalance, tentacleDrag);
        idleTentacle(upperRightTentacle, limbSwingAmount, ageInTicks, tentacleSway, tentacleBalance, tentacleDrag);
        idleTentacle(lowerLeftTentacle, limbSwingAmount, ageInTicks, tentacleSway, tentacleBalance, tentacleDrag);
        idleTentacle(lowerRightTentacle, limbSwingAmount, ageInTicks, tentacleSway, tentacleBalance, tentacleDrag);

        bendVerticalTentacle(upperLeftTentacle, -12.5f * Mth.DEG_TO_RAD);
        bendVerticalTentacle(upperRightTentacle, 12.5f * Mth.DEG_TO_RAD);

        bendVerticalTentacle(upperLeftTentacle, verticalDrag);
        bendVerticalTentacle(upperRightTentacle, -verticalDrag);
        bendVerticalTentacle(lowerLeftTentacle, verticalDrag);
        bendVerticalTentacle(lowerRightTentacle, -verticalDrag);

        bendInTentacle(upperLeftTentacle, -horizontalDrag);
        bendInTentacle(upperRightTentacle, horizontalDrag);
        bendInTentacle(lowerLeftTentacle, -horizontalDrag);
        bendInTentacle(lowerRightTentacle, horizontalDrag);
    }
}
