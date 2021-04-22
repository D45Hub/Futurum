package com.futurumgame.base.serialization.parsing;

import android.util.Log;

import com.futurumgame.base.collections.CollectionHelper;
import com.futurumgame.base.unlockables.Unlockable;

import java.util.HashMap;
import java.util.HashSet;

public class UnlockableParseRule extends BaseParseRule<Unlockable> {

    private final HashMap<String, Unlockable> allUnlockables = new HashMap<>();

    public UnlockableParseRule(HashSet<Unlockable>... categories) {
        for (HashSet<Unlockable> category : categories) {
            allUnlockables.putAll(CollectionHelper.toMap(category, u->u.getName()));
        }
    }

    @Override
    public ParseResult<Unlockable> next(String string) {
        super.next(string);
        if (getReadChars().isEmpty()) {
            return ParseResult.failResult();
        }
        Unlockable unlockable = allUnlockables.get(string);
        if (unlockable == null) {
            Log.e(UnlockableParseRule.class.getSimpleName(), "unknown unlockable: " + string);
            return ParseResult.failResult();
        }
        return ParseResult.create(unlockable);
    }

    @Override
    public String getParsingValue(Unlockable obj) {
        return obj.getName();
    }
}
