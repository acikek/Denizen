package com.denizenscript.denizen.tags.core;

import com.denizenscript.denizen.objects.InventoryTag;
import com.denizenscript.denizencore.tags.TagManager;

public class InventoryTagBase {

    public InventoryTagBase() {

        // <--[tag]
        // @attribute <inventory[<inventory>]>
        // @returns InventoryTag
        // @description
        // Returns an inventory object constructed from the input value.
        // Refer to <@link objecttype InventoryTag>.
        // -->
        TagManager.registerTagHandler(InventoryTag.class, "inventory", (attribute) -> { // non-static due to notes
            if (!attribute.hasParam()) {
                attribute.echoError("Inventory tag base must have input.");
                return null;
            }
            return InventoryTag.valueOf(attribute.getParam(), attribute.context);
        });
    }
}
