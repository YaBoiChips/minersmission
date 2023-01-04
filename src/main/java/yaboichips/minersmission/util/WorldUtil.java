package yaboichips.minersmission.util;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class WorldUtil {

    public static List<PlacementModifier> oceanFloorSquaredWithCount(int $$0, PlacementModifier... modifiers) {
        return oceanFloorSquaredWithCountAndMaxDepth($$0, OptionalInt.empty(), modifiers);
    }

    public static List<PlacementModifier> oceanFloorSquaredWithCountAndMaxDepth(int $$0, OptionalInt maxDepth, PlacementModifier... modifiers) {
        ArrayList<PlacementModifier> placementModifiers = new ArrayList<>(List.of(CountPlacement.of($$0), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        if (maxDepth.isPresent()) {
            placementModifiers.add(SurfaceWaterDepthFilter.forMaxDepth(maxDepth.getAsInt()));
        }
        placementModifiers.addAll(Arrays.asList(modifiers));
        return placementModifiers;
    }
}
