package deint.jroldan.manageproductdatabase;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.AbsListView;
import android.widget.Toast;

public class Home_Activity extends AppCompatActivity {

    public final static long MAXTIME = 3500;

    private long mBackPressed = 0;

    private AbsListView.MultiChoiceModeListener listProductFragment;
    private ManageProduct_Fragment manageProductFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listProductFragment = new ListProduct_Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.framehome,
                listProductFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public void showManageProduct(Bundle bundle) {
        manageProductFragment = new ManageProduct_Fragment(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framehome, manageProductFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void showListProduct() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framehome, listProductFragment);
        //ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(mBackPressed + MAXTIME > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_LONG).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}
