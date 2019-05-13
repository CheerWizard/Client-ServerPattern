package com.example.mockup.constants;

public final class SqlStorage {
    //db
    public static final class Databases {
        public static final String wed_codes_db = "connect_db";
    }
    //versions
    public static final class Version {
        public static final int v_1 = 2;
    }
    //tables
    public static final class Entities {
        public static final String users = "users";
        public static final String events = "events";
    }
    //columns for tables
    public static final class Columns {
        public static final String user_id = "userId";
        public static final String event_userId = "eventUserId";
        public static final String event_code_id = "eventCodeId";
    }
}