package com.denizenscript.denizen.tags.core;

import com.denizenscript.denizen.objects.PolygonTag;
import com.denizenscript.denizencore.tags.TagManager;

public class PolygonTagBase {

    public PolygonTagBase() {

        // <--[tag]
        // @attribute <polygon[<polygon>]>
        // @returns PolygonTag
        // @description
        // Returns a polygon object constructed from the input value.
        // Refer to <@link objecttype PolygonTag>.
        // -->
        TagManager.registerTagHandler(PolygonTag.class, "polygon", (attribute) -> { // non-static due to notes
            if (!attribute.hasParam()) {
                attribute.echoError("Polygon tag base must have input.");
                return null;
            }
            return PolygonTag.valueOf(attribute.getParam(), attribute.context);
        });
    }
}
