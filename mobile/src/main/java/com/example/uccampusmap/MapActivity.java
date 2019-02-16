package com.example.uccampusmap;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    //DONE: Add menu as per assignment instructions
    //TODO: Add UC Logo to menu
    //DONE: Add Alternative Map Options

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable drawable = getDrawable(R.drawable.uc_menu);
        toolbar.setOverflowIcon(drawable);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Change the map type depending on the object selected
        switch (id) {
            case R.id.action_normal:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.action_hybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.action_none:
                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
            case R.id.action_satellite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.action_terrain:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;

        //DONE: Update markers with proper formatting
        //TODO: Add WebView events for clicking on marker pictures
        //TODO: Fix spacing (by hand)

        // Set values for UC border polygon
        ArrayList<LatLng> coordinates = addCoordinates();
        PolygonOptions po = new PolygonOptions()
                .addAll(coordinates)
                .fillColor(Color.argb(75, 153,214,255))
                .strokeColor(Color.rgb(0,153,255))
                .clickable(true);
        // Add the border polygon to the map
        final Polygon border = mMap.addPolygon(po);
        // Add event handler for UC border
        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                border.setStrokeColor(Color.rgb(0, 51, 153));
                Toast.makeText(getApplicationContext(), "University of Canberra",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Add various markers
        LatLng ucSc = new LatLng(-35.238886, 149.084777);
        final Marker scMarker = mMap.addMarker(new MarkerOptions()
            .title("UC Student Centre")
            .snippet("Your gateway to access support and advice")
            .position(ucSc)
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.uc_sc)));

        LatLng ucHub = new LatLng(-35.237165, 149.083904);
        final Marker hubMarker = mMap.addMarker(new MarkerOptions()
                .title("The Hub")
                .snippet("Below Concourse level between Building 1 and Building 8")
                .position(ucHub)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.uc_hub)));

        LatLng ucLib = new LatLng(-35.238259, 149.083410);
        final Marker libMarker = mMap.addMarker(new MarkerOptions()
                .title("UC Library")
                .snippet("24 hr access for all students and staff.")
                .position(ucLib)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.uc_lib)));

        final LatLng coffeeGrounds = new LatLng(-35.239052, 149.082289);
        final Marker coffeeMarker = mMap.addMarker(new MarkerOptions()
                .title("Coffee Grounds")
                .snippet("The best coffee on campus, underneath Cooper Lodge.")
                .position(coffeeGrounds)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.uc_coffee)));

        LatLng ucGym = new LatLng(-35.238382, 149.088285);
        final Marker gymMarker = mMap.addMarker(new MarkerOptions()
                .title("UC Gym")
                .snippet("Open to students, staff and the general public.")
                .position(ucGym)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.uc_gym)));

        LatLng ucParking = new LatLng(-35.240789, 149.084578);
        final Marker parkingMarker = mMap.addMarker(new MarkerOptions()
                .title("Main Parking Area")
                .snippet("Several hundred parks available")
                .position(ucParking)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.uc_parking)));

        LatLng natsem = new LatLng(-35.240685, 149.086949);
        final Marker natsemMarker = mMap.addMarker(new MarkerOptions()
                .title("NATSEM Centre")
                .snippet("The National Centre for Social and Economic Modelling")
                .position(natsem)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.uc_natsem)));

        final LatLng svNth = new LatLng(-35.234098, 149.087013);
        final Marker svNthMarker = mMap.addMarker(new MarkerOptions()
                .position(svNth)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.uc_pegman)));

        LatLng svEast = new LatLng(-35.238494, 149.089437);
        final Marker svEastMarker = mMap.addMarker(new MarkerOptions()
                .position(svEast)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.uc_pegman)));

        // Move the camera to UC. Set maximum zoom while showing the whole of UC.
        // Src: https://stackoverflow.com/questions/1016896/get-screen-dimensions-in-pixels
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        LatLng neCorner = new LatLng(-35.230008, 149.094136);
        LatLng swCorner = new LatLng(-35.243384, 149.074134);
        LatLngBounds bounds = new LatLngBounds(swCorner, neCorner);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, 50));

        // Set the info window
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View infoWindow = getLayoutInflater().inflate(R.layout.infowindow_with_image, null);
                TextView title = infoWindow.findViewById(R.id.textViewTitle);
                TextView snippet = infoWindow.findViewById(R.id.textViewSnippet);
                ImageView image = infoWindow.findViewById(R.id.imageView);

                String id = marker.getId();

                // Start the Street View Activity
                if (id.equals(svEastMarker.getId()) || id.equals(svNthMarker.getId())) {
                    Intent intent = new Intent(getApplicationContext(), StreetViewActivity.class);
                    double latitude = marker.getPosition().latitude;
                    double longitude = marker.getPosition().longitude;
                    intent.putExtra("latitude", latitude);
                    intent.putExtra("longitude", longitude);
                    String streetName = "";
                    if (id.equals(svEastMarker.getId())) {
                        streetName = "University Drive";
                    } else if (id.equals(svNthMarker.getId())) {
                        streetName = "Ginninderra Drive";
                    }
                    intent.putExtra("name", streetName);
                    startActivity(intent);
                    return null;
                }

                title.setText(marker.getTitle());
                snippet.setText(marker.getSnippet());

                if (id.equals(scMarker.getId())) {
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.image_sc, getTheme()));
                } else if (id.equals(hubMarker.getId())) {
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.image_hub, getTheme()));
                } else if (id.equals(libMarker.getId())) {
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.image_library, getTheme()));
                } else if (id.equals(coffeeMarker.getId())) {
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.image_coffee, getTheme()));
                } else if (id.equals(gymMarker.getId())) {
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.image_gym, getTheme()));
                } else if (id.equals(parkingMarker.getId())) {
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.image_parking, getTheme()));
                } else if (id.equals(natsemMarker.getId())) {
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.image_natsem, getTheme()));
                }

                return infoWindow;
            }
        });

        //TODO: Set website based on Intent
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String id = marker.getId();
                if (id.equals(svNthMarker.getId()) || id.equals(svEastMarker.getId())) {
                    return;
                } else {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    String url = "";
                    if (id.equals(scMarker.getId())) {
                        url = "http://www.canberra.edu.au/current-students/canberra-students/student-centre";
                    } else if (id.equals(coffeeMarker.getId())) {
                        url = "https://www.canberra.edu.au/maps/buildings-directory/cooper-lodge";
                    } else if (id.equals(libMarker.getId())) {
                        url = "http://www.canberra.edu.au/library";
                    } else if (id.equals(hubMarker.getId())) {
                        url = "https://www.canberra.edu.au/maps/buildings-directory/the-hub";
                    } else if (id.equals(gymMarker.getId())) {
                        url = "http://www.ucunion.com.au/fitness-centre/";
                    } else if (id.equals(parkingMarker.getId())) {
                        url = "https://www.canberra.edu.au/maps/parking";
                    } else if (id.equals(natsemMarker.getId())) {
                        url = "http://www.natsem.canberra.edu.au/";
                    }
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Adds the coordinates for the campus border
     */
    public ArrayList<LatLng> addCoordinates() {

        ArrayList<LatLng> coordinates = new ArrayList<>();

        coordinates.add(new LatLng(-35.24097565763,149.0748181417421));
        coordinates.add(new LatLng(-35.24155582439154, 149.0758447356745));
        coordinates.add(new LatLng(-35.24242903303799, 149.0774978031126));
        coordinates.add(new LatLng(-35.24243904261016, 149.0791073660519));
        coordinates.add(new LatLng(-35.24243082404109, 149.0804594347761));
        coordinates.add(new LatLng(-35.24240259362011, 149.0815310306532));
        coordinates.add(new LatLng(-35.24237129180954, 149.0825500567663));
        coordinates.add(new LatLng(-35.24242951793273, 149.0835020675435));
        coordinates.add(new LatLng(-35.2424661050791, 149.0845155427032));
        coordinates.add(new LatLng(-35.24251487470117, 149.086425610332));
        coordinates.add(new LatLng(-35.24243520988466, 149.0879118992935));
        coordinates.add(new LatLng(-35.24233903379558, 149.0891111607013));
        coordinates.add(new LatLng(-35.242149977598, 149.0903640804021));
        coordinates.add(new LatLng(-35.24103093007724, 149.0901309771382));
        coordinates.add(new LatLng(-35.24023760814883, 149.0902266221693));
        coordinates.add(new LatLng(-35.23928709224025, 149.0904001380308));
        coordinates.add(new LatLng(-35.23831709459449, 149.0906956349587));
        coordinates.add(new LatLng(-35.23734376493635, 149.091045970322));
        coordinates.add(new LatLng(-35.23645598032832, 149.0914478715026));
        coordinates.add(new LatLng(-35.23576551709031, 149.0916024647171));
        coordinates.add(new LatLng(-35.23469343530793, 149.0919991433691));
        coordinates.add(new LatLng(-35.23457057669359, 149.0907633775599));
        coordinates.add(new LatLng(-35.23424945251512, 149.0893758054891));
        coordinates.add(new LatLng(-35.23385778180813, 149.0878259665929));
        coordinates.add(new LatLng(-35.23332777674832, 149.086866187557));
        coordinates.add(new LatLng(-35.23289078066738, 149.0860239400661));
        coordinates.add(new LatLng(-35.2323341074263, 149.0850685206119));
        coordinates.add(new LatLng(-35.23186174786609, 149.083924280494));
        coordinates.add(new LatLng(-35.23149728365172, 149.0829269671677));
        coordinates.add(new LatLng(-35.23124632834637, 149.0817714357989));
        coordinates.add(new LatLng(-35.2311204797355, 149.0811906892381));
        coordinates.add(new LatLng(-35.23096635292994, 149.0804488577425));
        coordinates.add(new LatLng(-35.23183814681428, 149.0801180621057));
        coordinates.add(new LatLng(-35.23252699621172, 149.0798342492829));
        coordinates.add(new LatLng(-35.2329217556409, 149.0794351010864));
        coordinates.add(new LatLng(-35.23358216167856, 149.0789099677778));
        coordinates.add(new LatLng(-35.23408134141361, 149.0785307905727));
        coordinates.add(new LatLng(-35.23480990221768, 149.0781054588925));
        coordinates.add(new LatLng(-35.23568377569637, 149.0776858970622));
        coordinates.add(new LatLng(-35.23650325657815, 149.0774117876339));
        coordinates.add(new LatLng(-35.23730375230738, 149.0770657272949));
        coordinates.add(new LatLng(-35.23782432348462, 149.0769840001478));
        coordinates.add(new LatLng(-35.23848165067864, 149.0766343359089));
        coordinates.add(new LatLng(-35.23898649833539, 149.0762686583373));
        coordinates.add(new LatLng(-35.23976428298076, 149.075734523781));
        coordinates.add(new LatLng(-35.24027869015939, 149.0754844454466));
        coordinates.add(new LatLng(-35.24097565763, 149.0748181417421));

        return coordinates;
    }
}
