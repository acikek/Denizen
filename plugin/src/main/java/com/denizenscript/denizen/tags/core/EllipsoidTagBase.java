package com.denizenscript.denizen.tags.core;

import com.denizenscript.denizen.objects.EllipsoidTag;
import com.denizenscript.denizencore.tags.TagManager;

public class EllipsoidTagBase {

    public EllipsoidTagBase() {

        // <--[tag]
        // @attribute <ellipsoid[<ellipsoid>]>
        // @returns EllipsoidTag
        // @description
        // Returns an ellipsoid object constructed from the input value.
        // Refer to <@link objecttype EllipsoidTag>.
        // -->
        TagManager.registerTagHandler(EllipsoidTag.class, "ellipsoid", (attribute) -> { // non-static due to notes
            if (!attribute.hasParam()) {
                attribute.echoError("Ellipsoid tag base must have input.");
                return null;
            }
            return EllipsoidTag.valueOf(attribute.getParam(), attribute.context);
        });
    }
}
