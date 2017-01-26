package deint.jroldan.manageproductdatabase;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;

public class SimpleMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener {

    private Context context;
    private MultiChoicePresenter presenter;
    private int statusBarColor;
    private int cont = 0;

    public SimpleMultiChoiceModeListener(Context context, MultiChoicePresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        if(checked) {
            cont++;
            presenter.setNewSelection(position,checked);
        } else {
            cont--;
            presenter.removeSelection(position);
        }
        mode.setTitle(cont);
    }

    /**
     * When it creates
     * @param mode
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.menu_context,menu);
        return true;
    }

    /**
     * When it updates
     * @param mode
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            statusBarColor=((AppCompatActivity)context).getWindow().getStatusBarColor();
            ((AppCompatActivity)context).getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.colorError));
            return false;
        }
        for(View v: listView) {
            v.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_product {
                presenter.deleteSelectedProduct();
            }
        }
        mode.finish();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            ((AppCompatActivity)context).getWindow().setStatusBarColor(statusBarColor);
        }
        presenter.clearSelection();
        for(View v: listView) {
            v.setVisibility(View.VISIBLE);
        }
        cont = 0;
    }
}
