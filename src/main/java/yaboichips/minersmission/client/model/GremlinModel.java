package yaboichips.minersmission.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import yaboichips.minersmission.MinersMission;
import yaboichips.minersmission.common.entites.Gremlin;

import javax.annotation.Nullable;

public class GremlinModel extends AnimatedGeoModel<Gremlin> {
    @Override
    public void setLivingAnimations(Gremlin entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }

    @Override
    public ResourceLocation getModelResource(Gremlin object) {
        return new ResourceLocation(MinersMission.MODID, "geo/gremlin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Gremlin object) {
        return new ResourceLocation(MinersMission.MODID, "textures/entity/gremlin.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Gremlin animatable) {
        return new ResourceLocation(MinersMission.MODID, "animations/gremlin.animation.json");
    }
}
