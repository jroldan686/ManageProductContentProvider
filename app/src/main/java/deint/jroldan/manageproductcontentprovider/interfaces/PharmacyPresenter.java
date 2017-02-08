package deint.jroldan.manageproductcontentprovider.interfaces;

import android.content.Context;

import deint.jroldan.manageproductcontentprovider.model.Pharmacy;

public interface PharmacyPresenter {

    interface View {
        Context getContext();


    }

    Pharmacy getAllPharmacy();
}
