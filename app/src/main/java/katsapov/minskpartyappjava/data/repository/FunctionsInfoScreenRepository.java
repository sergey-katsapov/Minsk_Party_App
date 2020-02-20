package katsapov.minskpartyappjava.data.repository;

import android.os.Handler;

import java.util.ArrayList;

import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.data.entities.Picture;


public class FunctionsInfoScreenRepository implements InfoScreenRepository {

    private final static String[] pictureNames = {
            "GPS\nTracking",
            "S.O.S",
            "Telephone\nsecurity"
    };

    private final static int[] pictureImages = {
            R.drawable.gps_button,
            R.drawable.sos_button,
            R.drawable.telephone_security
    };

    @Override
    public void loadItems(final LoaderListener loaderListener) {
        new Handler().postDelayed(() -> loaderListener.onFinished(createCollectionPictures()), 2000);
    }

    private ArrayList<Picture> createCollectionPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Picture picture = new Picture(pictureNames[i], pictureImages[i]);
            pictures.add(picture);
        }
        return pictures;
    }
}
