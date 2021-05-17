package com.futurumgame.base.serialization.parsing;

import com.futurumgame.base.util.CollectionHelper;
import com.futurumgame.base.unlockables.Unlockable;
import com.futurumgame.base.util.Logger;

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
            Logger.cannotParse(getClass(), "unlockable", string);
            return ParseResult.failResult();
        }
        return ParseResult.create(unlockable);
    }

    @Override
    public String getParsingValue(Unlockable obj) {
        return obj.getName();
    }
}
