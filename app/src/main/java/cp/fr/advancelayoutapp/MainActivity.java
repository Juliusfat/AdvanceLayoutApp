package cp.fr.advancelayoutapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * ajout d'un fragment
     * @param view
     */

    public void onAddFragment(View view) {
        //instancier un fragment
        FragmentB fragmentB = new FragmentB();
        //recuperer une instance du getionnaire de fragments
        FragmentManager manager = getFragmentManager();
        //début de la transaction
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentContainer,fragmentB);
        transaction.commit();

    }

    public void onReplaceFragment(View view) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment1,new FragmentB())
                .commit();

    }
}
