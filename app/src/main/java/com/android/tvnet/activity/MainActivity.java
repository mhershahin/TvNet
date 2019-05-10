package com.android.tvnet.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.android.tvnet.R;
import com.android.tvnet.done.adapter.TaskAdapter;
import com.android.tvnet.done.DoneActivity;
import com.android.tvnet.listeners.IReloadListener;
import com.android.tvnet.listeners.ITaskListener;
import com.android.tvnet.models.Customer;
import com.android.tvnet.models.Task;
import com.android.tvnet.util.CustomTypefaceSpan;
import com.android.tvnet.util.PhoneUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.tvnet.key.ConsKey.NO_INTERNET_CARD;
import static com.android.tvnet.key.ConsKey.TASK_DATA;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, IReloadListener, ITaskListener {

    @BindView(R.id.task_menu)
    LinearLayout buttonMenu;
    @BindView(R.id.task_title)
    AppCompatTextView taskTitle;
    @BindView(R.id.task_sort)
    LinearLayout sort;
    @BindView(R.id.liner_toolbar)
    LinearLayout linerToolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.task_recycler)
    RecyclerView taskRecycler;

    private LinearLayoutManager mLayoutManager;
    private TaskAdapter adapter;
    private PopupMenu popup;

    private List<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        buttonMenu.setOnClickListener(this);
        sort.setOnClickListener(this);


        initMainMenu();
        initSortMenu();

        getTasks();

        mLayoutManager = new LinearLayoutManager(this);
        adapter = new TaskAdapter(this, tasks, this, this);
        taskRecycler.setLayoutManager(mLayoutManager);
        taskRecycler.setAdapter(adapter);


    }

    private void getTasks() {
        //TODO Delete this function
        tasks.clear();

        List<String> comments = new ArrayList<>();
        comments.add("be careful");
        comments.add("The customer very stupid");
        if (PhoneUtil.getInstance().isInternetAvailable(this)) {


            tasks.add(new Task(new Customer("Hasmik Grigoryan", "Komistas 55/2", "077590505"), 1234, "Change lines", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 10.06.19", "12:00 10.06.19", 5, comments));
            tasks.add(new Task(new Customer("Varduhi Mnacakanyan", "Miasnikyan 5/2", "077590505"), 12213, "Spoiled equipment", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 9.06.19", "12:00 9.06.19", 2, comments));
            tasks.add(new Task(new Customer("Karen Vardanyan", "Dexatan  100/2", "077590505"), 435345, "Change lines", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "18:00 11.06.19", "13:00 10.06.19", 1, comments));
            tasks.add(new Task(new Customer("Siranush Papikyan", "Komistas 55/2", "077590505"), 647546, "New Conection", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 10.06.19", "14:00 10.06.18", 5, comments));
            tasks.add(new Task(new Customer("Serj Sargsyan", "Vardananc 3/5", "077590505"), 234234, "Change lines", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 10.06.19", "16:30 6.06.19", 6, comments));
            tasks.add(new Task(new Customer("Nikol pashinyan", "Komistas 55/2", "077590505"), 54645, "New Conection", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 10.06.19", "17:40 9.06.19", 7, comments));
            tasks.add(new Task(new Customer("Vachagan Andreasyan", "mayisi 9", "077590505"), 67907, "Change lines", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 10.06.19", "12:00 5.06.19", 9, comments));
            tasks.add(new Task(new Customer("Armen vardapetyan", "Arshakunyac 55/2", "077590505"), 34895, "New Conection", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 10.06.19", "16:00 13.06.19", 1, comments));
        } else {
            Task noInternet = new Task();
            noInternet.setViewType(NO_INTERNET_CARD);
            tasks.add(noInternet);
        }

    }

    private void initMainMenu() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }

    private void initSortMenu() {
        //creating a popup menu
        Context wrapper = new ContextThemeWrapper(this, R.style.popupStyle);
        popup = new PopupMenu(wrapper, sort);
        //inflating menu from xml resource
        popup.inflate(R.menu.sort_menu);
        //change font
        Menu menu = popup.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            String menuTitle = menuItem.getTitle().toString();
//            Typeface typeface =  Typeface.createFromAsset(getAssets(),"rial_bold.ttf");
            Typeface typeface = ResourcesCompat.getFont(this, R.font.rial_bold);
            SpannableString spannableString = new SpannableString(menuTitle);
            // For demonstration purposes only, if you need to support < API 28 just use the CustomTypefaceSpan class only.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                TypefaceSpan typefaceSpan = typeface != null ?
                        new TypefaceSpan(typeface) :
                        new TypefaceSpan("sans-serif");
                spannableString.setSpan(typefaceSpan, 0, menuTitle.length(),
                        Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            } else {
                CustomTypefaceSpan customTypefaceSpan = typeface != null ?
                        new CustomTypefaceSpan(typeface) :
                        new CustomTypefaceSpan(Typeface.defaultFromStyle(Typeface.NORMAL));
                spannableString.setSpan(customTypefaceSpan, 0, menuTitle.length(),
                        Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            menuItem.setTitle(spannableString);
        }


//        //adding click listener
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//
//                    ///that IDes corresponding with server vor sorting
//                    case R.id.action_near_by_me:
//                        return true;
//                    case R.id.action_priority:
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//        });


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.sort_menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.task_sort) {

            // sort items
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_item_done:
                openDoneActivit();
                break;
            case R.id.nav_item_settings:
                openSettingsActivity();
                break;
            case R.id.nav_item_logout:
                //ToDo Log Out
                break;

            case R.id.action_near_by_me:
                Log.e("near", "near by me");
                break;
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openSettingsActivity() {
        Intent intent= new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    private void openDoneActivit() {
        Intent intent= new Intent(MainActivity.this, DoneActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.task_menu:
                openMenu();
                break;
            case R.id.task_sort:
                popup.show();
                break;
        }
    }

    private void openMenu() {
        drawer.openDrawer(Gravity.LEFT);
    }

    @Override
    public void reloadData() {
        getTasks();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void cardClick(int position) {
        Intent intent = new Intent(MainActivity.this, SingleTaskActivity.class);
        intent.putExtra(TASK_DATA, (Parcelable) tasks.get(position));
        startActivity(intent);

    }
}
