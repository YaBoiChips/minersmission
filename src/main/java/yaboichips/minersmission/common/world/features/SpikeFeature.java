package yaboichips.minersmission.common.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Fluids;
import yaboichips.minersmission.common.world.config.SimpleBlockProviderConfig;
import yaboichips.minersmission.util.FastNoise;


public class SpikeFeature extends Feature<SimpleBlockProviderConfig> {

    FastNoise fnPerlin = null;

    public SpikeFeature(Codec<SimpleBlockProviderConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleBlockProviderConfig> featurePlaceContext) {
        return place(featurePlaceContext.level(), featurePlaceContext.chunkGenerator(), featurePlaceContext.random(), featurePlaceContext.origin(), featurePlaceContext.config());
    }

    public boolean place(WorldGenLevel world, ChunkGenerator generator, RandomSource rand, BlockPos pos, SimpleBlockProviderConfig config) {
        setSeed(world.getSeed());

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        double baseRadius = 2;
        int height = 13;
        int startHeight = height - 2;

        for (double y = -height; y <= -1; y++) {
            for (double x = -height; x <= height; x++) {
                for (double z = -height; z <= height; z++) {
                    mutable.set(pos).move((int) x, (int) y + startHeight, (int) z);
                    double noise = fnPerlin.GetNoise((float) mutable.getX(), (float) mutable.getZ()) * 12;
                    double scaledNoise = (noise / 11) * (-(y * baseRadius) / ((x * x) + (z * z)));
                    double threshold = 0.5;

                    if (y == -height) {
                        if (scaledNoise >= threshold)
                            if (!world.getBlockState(mutable.relative(Direction.DOWN)).canOcclude())
                                return false;
                    }
                    if (world.getBlockState(mutable).is(Blocks.BEDROCK)){
                        return false;
                    }

                    if (scaledNoise >= threshold) {
                        if (!world.getBlockState(mutable).canOcclude()) {
                            BlockState blockState = config.getBlockProvider().getState(rand, mutable);
                            world.setBlock(mutable, blockState, 2);

                            if (blockState.getBlock() == Blocks.LAVA)
                                world.scheduleTick(mutable, Fluids.LAVA, 0);
                        }
                    }
                }
            }
        }
        return true;
    }


    public void setSeed(long seed) {
        if (fnPerlin == null) {
            fnPerlin = new FastNoise((int) seed);
            fnPerlin.SetNoiseType(FastNoise.NoiseType.PerlinFractal);
            fnPerlin.SetFractalType(FastNoise.FractalType.FBM);
            fnPerlin.SetFrequency(0.2F);
        }
    }
}
