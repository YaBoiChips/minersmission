package yaboichips.minersmission.core.world;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import static yaboichips.minersmission.util.WorldUtil.oceanFloorSquaredWithCount;

public class MPlacedFeatures {

    public static final Holder<PlacedFeature> RED_CRYSTAL_SPIKE = PlacementUtils.register("red_crystal_spike", MConfiguredFeatures.RED_CRYSTAL_SPIKE, PlacementUtils.FULL_RANGE);

}
