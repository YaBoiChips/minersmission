package yaboichips.minersmission.client.renderers;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.PathfinderMob;

public class HelperRenderer<T extends PathfinderMob, M extends HumanoidModel<T>> extends HumanoidMobRenderer<T, M> {
    public HelperRenderer(EntityRendererProvider.Context context, M model, M inner, M outer) {
        super(context, model, 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, outer, inner));
    }
}
