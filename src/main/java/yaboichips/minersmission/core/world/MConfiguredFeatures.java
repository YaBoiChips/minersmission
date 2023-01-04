package yaboichips.minersmission.core.world;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import yaboichips.minersmission.common.world.config.SimpleBlockProviderConfig;


public class MConfiguredFeatures {

    public static final Holder<ConfiguredFeature<?, ?>> RED_CRYSTAL_SPIKE = Holder.direct(new ConfiguredFeature<>(MFeatures.CRYSTAL_SPIKE.get(),
            new SimpleBlockProviderConfig(SimpleStateProvider.simple(Blocks.REDSTONE_BLOCK))));
}
