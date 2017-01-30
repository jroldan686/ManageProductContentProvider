package deint.jroldan.manageproductdatabase.repository;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import deint.jroldan.manageproductdatabase.R;
import deint.jroldan.manageproductdatabase.database.ManageProductContract;
import deint.jroldan.manageproductdatabase.interfaces.PharmacyPresenter;
import deint.jroldan.manageproductdatabase.presenter.PharmacyPresenterImpl;

public class ListPharmacyFragment extends Fragment implements PharmacyPresenter.View {
    private static PharmacyPresenterImpl presenter;
    private PharmacyAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new PharmacyPresenterImpl(this);
        adapter = new PharmacyAdapter(getActivity(), , ManageProductContract.PharmacyEntry.ALL_COLUMNS,
                {R.id.txvCif, R.id.txvAddress, R.id.txvPhone, R.id.txvEmail});
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_list_pharmacy, container);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getAllPharmacy();
    }
}
