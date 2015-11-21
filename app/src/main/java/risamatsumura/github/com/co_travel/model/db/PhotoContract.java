package risamatsumura.github.com.co_travel.model.db;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;

import risamatsumura.github.com.co_travel.R;
import risamatsumura.github.com.co_travel.control.ImageHandler;

/**
 * Created by risam on 28/09/2015.
 */
public final class PhotoContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String BLOB_TYPE = " BLOB";
    private static final String COMMA_SEP = ",";
    private static final String SPACE_SEP = " ";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public PhotoContract() {
    }

    public static abstract class PhotoEntry implements BaseColumns {
        public static final String TABLE_NAME = "photos";
        public static final String COLUMN_BINARY_DATA = "binary_data";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_LOC = "location";
        public static final String COLUMN_DESCRIPTION = "description";


        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_BINARY_DATA + BLOB_TYPE + COMMA_SEP +
                COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
                COLUMN_LOC + TEXT_TYPE + COMMA_SEP +
                COLUMN_DESCRIPTION + TEXT_TYPE + " )";

        private static final String COLUMNS =
                " (" + COLUMN_TITLE + COMMA_SEP +
                        COLUMN_BINARY_DATA + COMMA_SEP +
                        COLUMN_TITLE + COMMA_SEP +
                        COLUMN_DATE + COMMA_SEP +
                        COLUMN_LOC + COMMA_SEP +
                        COLUMN_DESCRIPTION + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String[] LIST_COLUMNS = {
                _ID,
                COLUMN_BINARY_DATA,
                COLUMN_TITLE,
                COLUMN_DATE,
                COLUMN_LOC,
                COLUMN_DESCRIPTION
        };
    }

}
