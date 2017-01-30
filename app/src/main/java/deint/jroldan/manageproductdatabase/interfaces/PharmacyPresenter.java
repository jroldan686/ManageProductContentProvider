package deint.jroldan.manageproductdatabase.interfaces;

import android.content.Context;

import deint.jroldan.manageproductdatabase.model.Pharmacy;

public interface PharmacyPresenter {

    interface View {
        Context getContext();


    }

    Pharmacy getAllPharmacy();
}
