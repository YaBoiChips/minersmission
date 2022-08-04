package yaboichips.minersmission.client.renderers;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import yaboichips.minersmission.MinersMission;
import yaboichips.minersmission.client.model.ForemanModel;
import yaboichips.minersmission.common.entites.villagers.Foreman;

public class ForemanRenderer extends HelperRenderer<Foreman, ForemanModel<Foreman>>{
    public ForemanRenderer(EntityRendererProvider.Context context) {
        this(context, ForemanModel.LAYER_LOCATION, ForemanModel.LAYER_LOCATION_INNER, ForemanModel.LAYER_LOCATION_OUTER);
    }
    public ForemanRenderer(EntityRendererProvider.Context context, ModelLayerLocation model, ModelLayerLocation inner, ModelLayerLocation outer) {
        super(context, new ForemanModel<>(context.bakeLayer(model)), new ForemanModel<>(context.bakeLayer(inner)), new ForemanModel<>(context.bakeLayer(outer)));
    }

    @Override
    public ResourceLocation getTextureLocation(Foreman entity) {
        return MinersMission.createLocation("textures/entity/foreman.png");
    }
}
