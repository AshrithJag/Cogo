package com.example.cogo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.menu;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.MatrixCursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.IntToString;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

public class MainActivity extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
    static CustomList adapter;
    static CustomList semiadapter;
    static ListView list1;
    int currenttab=0;
    static List<ListView> list = new ArrayList<ListView>();
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    static Context context;
    
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    
    String[] web = {
    	  "Dominos",
   	      "Pizza Hut",
   	      "Sub Way",
   	      "Pizza Hut",
   	      "KFC",
   	      "Dominos",
   	      
    	  } ;
    int[] im = {
    	     R.drawable.dominos,
    	      R.drawable.pizzahut,
    	      R.drawable.subway,
    	      R.drawable.pizzahut,
    	      R.drawable.kfc,
    	      R.drawable.dominos,
    	      
    	  } ;
    String[] statement = {
    	      "Dominos",
    	      "Pizza Hut",
    	      "Sub Way",
    	      "Pizza Hut",
    	      "KFC",
    	      "Dominos",
    	    
    	  } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load_all_coupon();
        context = this;
        adapter = new CustomList(MainActivity.this,web,im,statement);
        
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        
        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#339900")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6600")));
        actionBar.setTitle("  Food");
        
        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);	

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
        
    }
    
    
    
    private void load_all_coupon() {
		// TODO Auto-generated method stub
    	
    	 File folder = new File(Environment.getExternalStorageDirectory() + "/COGO");
         File file = new File(folder,"all_coupons.txt");
         BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line=br.readLine();
			System.out.println(R.drawable.dominos);
			JSONArray data = new JSONArray(line);  
			System.out.println(data);
			String[] ww=new String[data.length()];
			int[] wi=new int[data.length()];
			String[] ws=new String[data.length()];
			for (int i = 0; i < data.length(); i++) {
				 
                JSONObject c = data.getJSONObject(i);
                ww[i] = c.getString("comp_name");
                wi[i] = R.drawable.dominos;
                ws[i] = c.getString("small_desc");
         
                
            }
				//web=ww;
				//im=wi;
				//statement=ws;
			
			} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
    	
		
	}


	public void newpage(View v)
    {
    	System.out.println("ok working");
    	int position=(Integer)v.getTag();
    	Intent intent = new Intent(context, viewcoupon.class);
    	intent.putExtra("Name", web[position]);
    	intent.putExtra("Validity", "30-12-14");
    	intent.putExtra("Statement", "Coupon");
    	intent.putExtra("Id", "1");
    	intent.putExtra("Source",web[position]);
    	intent.putExtra("Code","MOB06");
    	intent.putExtra("Image",Integer.toString(im[position]));
        startActivity(intent); 
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        search.setOnQueryTextListener(new OnQueryTextListener() { 

            @Override 
            public boolean onQueryTextChange(String query) {

                System.out.println(query);
                
                if(!TextUtils.isEmpty(query))
                {
                
                	System.out.println("query");
                int len=web.length;
                int ind=0;
                List<String> sw = new ArrayList<String>();
                List<Integer> si = new ArrayList<Integer>();
                List<String> ss = new ArrayList<String>();
                while(ind<len)
                {
                	if(match(web[ind].toLowerCase(),query.toLowerCase()))
                	{
                		sw.add(web[ind]);
                		si.add(im[ind]);
                		ss.add(statement[ind]);
                	}
                	ind++;
                }
               
              
                int[] semiim=new int[sw.size()];
                String[] semiweb=new String[sw.size()];
                String[] semistatement=new String[sw.size()];
                int y=0;
                while(y<sw.size())
                {
                	semiim[y]=si.get(y);
                	semiweb[y]=sw.get(y);
                	semistatement[y]=ss.get(y);
                	y++;
                }
                
                semiadapter = new CustomList(MainActivity.this,semiweb,semiim,semistatement);
                list.get( currenttab).setAdapter(semiadapter);
                }
                else
                {
                	list.get(currenttab).setAdapter(adapter);
                }
                return true; 

            }

            private Boolean match(String haystack, String needle) {
				// TODO Auto-generated method stub
            	
            	int needleLen = needle.length();
            	int haystackLen = haystack.length();
             
            	if (needleLen == haystackLen && needleLen == 0)
            		return true;
             
            	if (needleLen == 0)
            		return true;
             
            	for (int i = 0; i < haystackLen; i++) {
            		
            		if (haystackLen - i + 1 < needleLen)
            			return false;
             
            		int k = i;
            		int j = 0;
             
            		while (j < needleLen && k < haystackLen && needle.charAt(j) == haystack.charAt(k)) {
            			j++;
            			k++;
            			if (j == needleLen)
            				return true;
            		}
             
            	}
             
            	return false;
            	
            	
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				 System.out.println(query);
				return false;
			}

        });

        return true;
    }

  
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.action_Wallet)
        {
        	 Intent newpage = new Intent(context,Mywallet.class);
             startActivity(newpage);
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if(id==R.id.action_notification)
        {
        	 Intent newpage = new Intent(context,Notification.class);
             startActivity(newpage);
        }
        if(id==R.id.action_GPS)
        {
        	 Intent newpage = new Intent(context,Gps.class);
             startActivity(newpage);
        }
        if(id==R.id.action_Log)
        {
        	File folder = new File(Environment.getExternalStorageDirectory() + "/COGO");
        	File file = new File(folder,"Login.txt");
        	file.delete();
        	this.finish();
        	Intent newpage = new Intent(context,Login.class);
            startActivity(newpage);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
    	currenttab=tab.getPosition();
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
    
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        /*Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();*/
   

        // update selected item and title, then close the drawer
    	System.out.println("YOLO");
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
     protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.planets_array)[i];

            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                            "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
            getActivity().setTitle(planet);
            return rootView;
        }
    } 
    
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            list1 = (ListView)rootView.findViewById(R.id.listView1);
            list1.setAdapter(adapter);
            list.add(list1);
            return rootView;
        }
    }
    

}
