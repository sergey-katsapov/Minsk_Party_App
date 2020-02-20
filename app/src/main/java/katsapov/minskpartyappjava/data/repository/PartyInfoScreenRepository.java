package katsapov.minskpartyappjava.data.repository;

import android.os.Handler;

import java.util.ArrayList;

import katsapov.minskpartyappjava.R;
import katsapov.minskpartyappjava.data.entities.Picture;


public class PartyInfoScreenRepository implements InfoScreenRepository {

    private final static String[] pictureNames = {
            "Rocket",
            "London",
            "Mountains",
            "Moon",
            "Sun",
            "Mountains",
            "River",
            "Trees",
            "Town",
            "Vulcans"
    };

    private final static int[] pictureImages = {
            R.drawable.cohete_flat,
            R.drawable.london_flat,
            R.drawable.material_flat,
            R.drawable.moon_flat,
            R.drawable.mountain_flat,
            R.drawable.mountain_mo_flat,
            R.drawable.moutain_go_flat,
            R.drawable.pine_flat,
            R.drawable.towers_flat,
            R.drawable.vulcan_flat
    };

    @Override
    public void loadItems(final LoaderListener loaderListener) {
        new Handler().postDelayed(() -> loaderListener.onFinished(createCollectionPictures()), 2000);
    }

    private ArrayList<Picture> createCollectionPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Picture picture = new Picture(pictureNames[i], pictureImages[i]);
            pictures.add(picture);
        }
        return pictures;
    }
}
