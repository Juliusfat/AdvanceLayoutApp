package sm.fr.advancedlayoutapp;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import sm.fr.advancedlayoutapp.model.RandowUser;
import sm.fr.advancedlayoutapp.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class RandomUserFragment extends Fragment {

    private List<RandowUser> userList;
    private ListView userListView;


    public RandomUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDataFromHttp();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_random_user, container, false);

        userListView = view.findViewById(R.id.randowUserListView);


        return view;
    }

    private void processResponse(String response){

        //conversion la liste de RandomUser en un tableau de string comportant uniquement les noms

        userList = responseToList(response);
        String[] data = new String[userList.size()];
        for(int i = 0 ; i< userList.size(); i++  ){
            data[i] = userList.get(i).getName();
        }
        //definir un ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.getActivity(),
                android.R.layout.simple_list_item_1,
                data
        );

        userListView.setAdapter(adapter);


    }

    private void getDataFromHttp(){
        String url = "https://jsonplaceholder.typicode.com/users";

        //Définition de la requête
        StringRequest request = new StringRequest(
                //Méthode de la requête http
                Request.Method.GET,
                url,
                //Gestionnaire de succès
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("HTTP", response);
                        processResponse(response);

                    }
                },
                //Gestionnaire d'erreur
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("HTTP", error.getMessage());

                    }
                }
        );

        //Ajout de la requête à la file d'exécution
        Volley.newRequestQueue(this.getActivity())
                .add(request);
    }

    //Conversion d'une réponse json(chaine de caractère) en une liste RandomUser
    private List<RandowUser> responseToList(String response){
        List<RandowUser> list= new ArrayList<>();
        try {
            JSONArray jsonUsers = new JSONArray(response);
            JSONObject item;
            for(int i=0; i<jsonUsers.length(); i++){
                item=(JSONObject)jsonUsers.get(i);
                RandowUser user = new RandowUser();
                user.setName(item.getString("name"));
                user.setEmail(item.getString("email"));

                JSONObject geo = item.getJSONObject("address").getJSONObject("geo");
                user.setLatitude(geo.getDouble("lat"));
                user.setLongitude(geo.getDouble("lng"));

                //ajout de l'utilisateur à la liste
                list.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
