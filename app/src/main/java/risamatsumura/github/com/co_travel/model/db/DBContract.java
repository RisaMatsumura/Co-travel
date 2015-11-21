package risamatsumura.github.com.co_travel.model.db;

/**
 * Created by risam on 28/09/2015.
 */
public final class DBContract {

    public static final  int DATABASE_VERSION  = 1;
    public static final  String DATABASE_NAME = "cotravel.db";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private DBContract() {}

    public static final String[] TABLES = {
            TripContract.TripEntry.CREATE_TABLE,
            PhotoContract.PhotoEntry.CREATE_TABLE,
            PlaceContract.PlaceEntry.CREATE_TABLE
    };
}
