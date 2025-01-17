package com.denizenscript.denizen.tags.core;

import com.denizenscript.denizen.objects.BiomeTag;
import com.denizenscript.denizencore.tags.TagManager;

public class BiomeTagBase {

    public BiomeTagBase() {

        // <--[tag]
        // @attribute <biome[<biome>]>
        // @returns BiomeTag
        // @description
        // Returns a biome object constructed from the input value.
        // Refer to <@link objecttype BiomeTag>.
        // -->
        TagManager.registerStaticTagBaseHandler(BiomeTag.class, "biome", (attribute) -> {
            if (!attribute.hasParam()) {
                attribute.echoError("Biome tag base must have input.");
                return null;
            }
            return BiomeTag.valueOf(attribute.getParam(), attribute.context);
        });
    }
}
