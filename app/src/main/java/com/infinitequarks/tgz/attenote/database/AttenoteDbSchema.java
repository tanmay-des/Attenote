package com.infinitequarks.tgz.attenote.database;

/**
 * Created by m on 10/18/2016.
 */

public class AttenoteDbSchema {

    public static final class SubjectTable {
        public static final String NAME = "subjects";

        public static final class Cols {
            public static final String ID ="ID";
            public static final String subjectName ="subjectName";
            public static final String isTheory ="isTheory";

        }
    }

    public static final class TimeTable {

        public static final String NAME = "timetable";

        public static final class Cols {
            public static final String ID = "ID";
            public static final String day_no = "day_no";
            public static final String subjectName = "subjectName";
            public static final String isTrue = "isTrue";
            public static final String startTime = "startTime";
            public static final String endTime = "endTime";

        }
    }

    public static  final class Attendance {

        public static final String NAME = "attendance";

        public static final class Cols{
            public static final String ID = "ID";
            public static final String date = "date";
            public static final String subjectName = "subjectName";
            public static final String attended = "attended";

        }


    }

}

