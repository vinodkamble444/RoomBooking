package com.example.roombooking.data.db;

/**
 * Created by Vinod  on 19-11-2017.
 *
 */

public class TableMaster {


    public static final String TYPE_TEXT = " TEXT ",
            TYPE_INTEGER = " INTEGER ",
            _ID = "_id";
    public static final String _id = _ID + TYPE_INTEGER
            + " PRIMARY KEY AUTOINCREMENT ";

    public interface tblRooms {

        String TABLE_NAME = "tblRooms",
                NAME = "name",
                SIZE = "size",
                LOCATION = "location",
                CAPACITY = "capacity";

        String CREATE_TABLE = "create TABLE IF NOT EXISTS " + TABLE_NAME
                + "(" + NAME + TYPE_TEXT + ", "
                + SIZE + TYPE_TEXT + ", "
                + LOCATION + TYPE_TEXT + ", "
                + _id + ", "
                + CAPACITY + TYPE_INTEGER + ")";
    }

    public interface tblEquipment {

        String TABLE_NAME = "tblEquipment",
                Equipment = "Equipment",
                NAME = "name";
        String CREATE_TABLE = "create TABLE IF NOT EXISTS " + TABLE_NAME
                + "(" + Equipment + TYPE_TEXT + ", "
                + _id + ", "
                + NAME + TYPE_TEXT + ")";
    }

    public interface tblImages {

        String TABLE_NAME = "tblmages",
                IMAGE_URL = "lmagesUrl",
                NAME = "name";
        String CREATE_TABLE = "create TABLE IF NOT EXISTS " + TABLE_NAME
                + "(" + IMAGE_URL + TYPE_TEXT + ", "
                + _id + ", "
                + NAME + TYPE_TEXT + ")";
    }

    public interface tblAvail {

        String TABLE_NAME = "tblAvail",
                AVAIL= "Avail",
                NAME = "name";
        String CREATE_TABLE = "create TABLE IF NOT EXISTS " + TABLE_NAME
                + "(" + AVAIL + TYPE_TEXT + ", "
                + _id + ", "
                + NAME + TYPE_TEXT + ")";
    }
    public interface tblAtendee {

        String TABLE_NAME = "tblAtendee",
                ATENDEE_NAME= "atendee_name",
                EMAIL= "email",
                PHONE= "pnone",
                ROOM_NAME = "room_name";
        String CREATE_TABLE = "create TABLE IF NOT EXISTS " + TABLE_NAME
                + "(" + ATENDEE_NAME + TYPE_TEXT + ", "
                + EMAIL + TYPE_TEXT + ", "
                + PHONE + TYPE_TEXT + ", "
                + _id + ", "
                + ROOM_NAME + TYPE_TEXT + ")";
    }
}
