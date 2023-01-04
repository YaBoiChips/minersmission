package yaboichips.minersmission.core.world;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yaboichips.minersmission.common.world.config.SimpleBlockProviderConfig;
import yaboichips.minersmission.common.world.features.SpikeFeature;

import static yaboichips.minersmission.MinersMission.MODID;

public class MFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MODID);

    public static final RegistryObject<Feature<SimpleBlockProviderConfig>> CRYSTAL_SPIKE = FEATURES.register("spike_feature", () -> new SpikeFeature(SimpleBlockProviderConfig.CODEC.stable()));

}
