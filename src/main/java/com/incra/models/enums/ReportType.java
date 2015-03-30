package com.incra.models.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jeff Risberg
 * @since 3/23/2015
 */
public enum ReportType {
    Donation("Donations"),
    GiftCertificate("Gift Certificates");

    protected String name;

    ReportType(String name) {
        this.name = name;

        ObjectRepo.shared_objectList.add(this);
        ObjectRepo.shared_keyToObjectMap.put(this.name(), this);
    }

    String getName() {
        return name;
    }

    static public List<ReportType> selectAll() {
        return ObjectRepo.shared_objectList;
    }

    static public ReportType findByKey(String key) {
        return ObjectRepo.shared_keyToObjectMap.get(key);
    }

    static private class ObjectRepo {
        static protected Map<String, ReportType> shared_keyToObjectMap = new HashMap<String, ReportType>();
        static protected List<ReportType> shared_objectList = new ArrayList<ReportType>();
    }
}
